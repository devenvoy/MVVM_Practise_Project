package com.example.interviewpractise.data.repository

import com.example.interviewpractise.data.models.ProductsResponse
import com.example.interviewpractise.data.retrofit.ApiServices
import com.example.interviewpractise.domain.repository.RetrofitFragmentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Response
import javax.inject.Inject

class RetrofitFragmentRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices
) : RetrofitFragmentRepository {

    override suspend fun getAllProducts(): Response<ProductsResponse> {
        return CoroutineScope(Dispatchers.IO).async {
            apiServices.getAllProducts()
        }.await()
    }

    override suspend fun getSearchProducts(query: String): Response<ProductsResponse> {
        return CoroutineScope(Dispatchers.IO).async {
            apiServices.getSearchProducts(query)
        }.await()
    }

    override suspend fun getAllCategories(): Response<List<String>> {
        return CoroutineScope(Dispatchers.IO).async {
            apiServices.getAllCategories()
        }.await()
    }

    override suspend fun getProductsFromCategory(cat: String): Response<ProductsResponse> {
        return CoroutineScope(Dispatchers.IO).async {
            apiServices.getProductsFromCategory(cat)
        }.await()
    }
}