package com.example.interviewpractise.data.retrofit

import com.example.interviewpractise.data.models.ProductsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("products")
    suspend fun getAllProducts(): Response<ProductsResponse>

    @GET("products/search")
    suspend fun getSearchProducts(@Query("q") query: String): Response<ProductsResponse>

    @GET("products/categories")
    suspend fun getAllCategories(): Response<List<String>>

    @GET("products/categories/{category}")
    suspend fun getProductsFromCategory(@Path("category") cat: String): Response<ProductsResponse>

}