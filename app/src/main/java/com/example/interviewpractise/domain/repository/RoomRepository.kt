package com.example.interviewpractise.domain.repository

import androidx.lifecycle.LiveData
import com.example.interviewpractise.data.models.Product

interface RoomRepository {

    suspend fun insertProduct(product: Product): Unit

    suspend fun deleteProduct(product: Product): Unit

    suspend fun getAllProducts(): LiveData<List<Product>>

    suspend fun updateData(product: Product): Unit

    suspend fun deleteAll(): Unit
}