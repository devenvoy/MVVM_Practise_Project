package com.example.interviewpractise.data.repository

import com.example.interviewpractise.data.models.Products
import com.example.interviewpractise.data.retrofit.RetrofitInstance
import com.example.interviewpractise.domain.repository.RetrofitFragmentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class RetrofitFragmentRepositoryImpl : RetrofitFragmentRepository {

    override suspend fun makeApiCall(): ArrayList<Products> {
        val job = CoroutineScope(Dispatchers.IO).async {
            RetrofitInstance.myInterface.getData()
        }
        val response = job.await()
        return response.body()!!.products
    }
}