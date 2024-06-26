package com.example.interviewpractise.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.interviewpractise.data.models.ProductsResponse
import com.example.interviewpractise.data.retrofit.ApiServices
import com.example.interviewpractise.data.room.AppDao
import com.example.interviewpractise.utils.NetworkUtils
import javax.inject.Inject

class RetrofitFragmentRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices,
    private val appDao: AppDao,
    private val context: Context
) {

    private val productLiveData = MutableLiveData<ProductsResponse>()

    val productsResponse: LiveData<ProductsResponse>
        get() = productLiveData


    private val categoryLiveData = MutableLiveData<List<String>>()

    val categories: LiveData<List<String>>
        get() = categoryLiveData

    suspend fun getAllProducts() {
        if (NetworkUtils.isInternetAvailable(context)) {
            val result = apiServices.getAllProducts()
            if (result.body() != null) {
                appDao.addProducts(result.body()!!.products)
                productLiveData.postValue(result.body())
            }
        } else {
            val products = appDao.getProductData()
            if (products.value != null) {
                val response = ProductsResponse(products = products.value!!)
                productLiveData.postValue(response)
            }
        }
    }

    suspend fun getSearchProducts(query: String?) {
        val result = apiServices.getSearchProducts(query)
        if (result.body() != null) {
            productLiveData.postValue(result.body())
        }
    }

    suspend fun getAllCategories() {
        val result = apiServices.getAllCategories()
        if (result.body() != null) {
            categoryLiveData.postValue(result.body())
        }
    }

    suspend fun getProductsFromCategory(cat: String) {
        val result = apiServices.getProductsFromCategory(cat)
        if (result.body() != null) {
            productLiveData.postValue(result.body())
        }
    }
}