package com.example.interviewpractise.domain.repository

import androidx.lifecycle.LiveData
import com.example.interviewpractise.data.models.Product

interface RoomRepository {

    suspend fun insertProduct(product: Product)

    suspend fun addProducts(products: List<Product>)

    suspend fun deleteProduct(product: Product)

    fun getAllProducts(): LiveData<List<Product>>

    suspend fun updateData(product: Product)

    suspend fun deleteAll()
}