package com.example.interviewpractise.domain.repository

import com.example.interviewpractise.data.models.Products
import com.example.interviewpractise.data.models.ProductsResponse
import retrofit2.Response

interface RetrofitFragmentRepository {

    suspend fun makeApiCall(): ArrayList<Products>

}