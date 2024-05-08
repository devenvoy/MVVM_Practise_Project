package com.example.interviewpractise.data.retrofit

import com.example.interviewpractise.data.models.ProductsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {

    @GET("products")
    suspend fun getData(): Response<ProductsResponse>

}