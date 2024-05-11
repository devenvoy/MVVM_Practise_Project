package com.example.interviewpractise.domain.repository

import com.example.interviewpractise.data.models.Product

interface RetrofitFragmentRepository {

    suspend fun makeApiCall(): ArrayList<Product>

}