package com.example.interviewpractise.data.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interviewpractise.data.models.Product
import com.example.interviewpractise.domain.repository.ResponseListener
import com.example.interviewpractise.domain.repository.RetrofitFragmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RetrofitViewModel @Inject constructor(
    private val retrofitFragmentRepository: RetrofitFragmentRepository
) : ViewModel() {

    var responseListener: ResponseListener? = null

    private var _productsResponse = MutableLiveData<List<Product>>()

    val productsResponse: LiveData<List<Product>>
        get() = _productsResponse

    fun getAllData() {
        responseListener?.onStarted()
        viewModelScope.launch {
            val response = retrofitFragmentRepository.getAllProducts()
            if (response.code() == 200) {
                _productsResponse.value = response.body()!!.products
                responseListener?.onSuccess()
            } else {
                responseListener?.onFailure(response.code().toString() + response.message())
            }
        }
    }

    fun searchProduct(query: String) {
        responseListener?.onStarted()
        viewModelScope.launch {
            val response = retrofitFragmentRepository.getSearchProducts(query)
            if (response.code() == 200) {
                _productsResponse.value = response.body()!!.products
                responseListener?.onSuccess()
            } else {
                responseListener?.onFailure(response.code().toString() + response.message())
            }
        }
    }
}

