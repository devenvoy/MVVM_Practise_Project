package com.example.interviewpractise.data.repository

import androidx.lifecycle.LiveData
import com.example.interviewpractise.data.models.Product
import com.example.interviewpractise.data.room.AppDao
import com.example.interviewpractise.domain.repository.RoomRepository
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val appDao: AppDao
) : RoomRepository {
    override suspend fun insertProduct(product: Product) {
        appDao.insertProduct(product)
    }

    override suspend fun addProducts(products: List<Product>) {
        appDao.addProducts(products)
    }


    override suspend fun deleteProduct(product: Product) {
        appDao.deleteProduct(product)
    }

    override fun getAllProducts(): LiveData<List<Product>> {
        return appDao.getAllProducts()
    }

    override suspend fun updateData(product: Product) {
        appDao.updateData(product)
    }

    override suspend fun deleteAll() {
        appDao.deleteAll()
    }
}