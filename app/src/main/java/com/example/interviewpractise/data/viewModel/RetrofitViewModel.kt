package com.example.interviewpractise.data.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interviewpractise.data.models.ProductsResponse
import com.example.interviewpractise.data.repository.RetrofitFragmentRepositoryImpl
import com.example.interviewpractise.domain.repository.ResponseListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RetrofitViewModel @Inject constructor(
    private val retrofitFragmentRepository: RetrofitFragmentRepositoryImpl,
    val checkConnection: CheckConnection
) : ViewModel() {

    var responseListener: ResponseListener? = null

    val products: LiveData<ProductsResponse>
        get() = retrofitFragmentRepository.productsResponse

    fun getAllData() {
        responseListener?.onStarted()
        if (checkConnection.value == true) {
            viewModelScope.launch(Dispatchers.IO) {
                retrofitFragmentRepository.getAllProducts()
            }
            responseListener?.onSuccess()
        } else {
            responseListener?.onFailure("Network Not Connected")
        }
    }

    fun searchProduct(query: String?) {
        responseListener?.onStarted()
        if (checkConnection.value == true) {
            viewModelScope.launch(Dispatchers.IO) {
                retrofitFragmentRepository.getSearchProducts(query)
            }
        } else {
            responseListener?.onFailure("Network Not Connected")
        }
    }
}

