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

    override suspend fun makeApiCall() : Response<ProductsResponse>{
        return CoroutineScope(Dispatchers.IO).async {
            apiServices.getData()
        }.await()
    }
}