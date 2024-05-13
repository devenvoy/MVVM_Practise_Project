package com.example.interviewpractise.domain.repository

import com.example.interviewpractise.data.models.ProductsResponse
import retrofit2.Response

interface RetrofitFragmentRepository {

    suspend fun getAllProducts(): Response<ProductsResponse>

    suspend fun getSearchProducts(query: String): Response<ProductsResponse>

    suspend fun getAllCategories(): Response<List<String>>

    suspend fun getProductsFromCategory(cat: String): Response<ProductsResponse>

}