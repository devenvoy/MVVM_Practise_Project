package com.example.interviewpractise.data.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interviewpractise.data.models.ProductsResponse
import com.example.interviewpractise.data.repository.RetrofitFragmentRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RetrofitViewModel @Inject constructor(
    private val retrofitFragmentRepository: RetrofitFragmentRepositoryImpl
) : ViewModel() {


    init {
        getAllData()
    }

    val products: LiveData<ProductsResponse>
        get() = retrofitFragmentRepository.productsResponse

    fun getAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            retrofitFragmentRepository.getAllProducts()
        }
    }

    fun searchProduct(query: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            retrofitFragmentRepository.getSearchProducts(query)
        }
    }
}


