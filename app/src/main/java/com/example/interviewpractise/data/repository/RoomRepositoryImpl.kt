package com.example.interviewpractise.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.interviewpractise.data.models.CartItem
import com.example.interviewpractise.data.models.CartWithProduct
import com.example.interviewpractise.data.models.Product
import com.example.interviewpractise.data.room.AppDao
import javax.inject.Inject

open class RoomRepositoryImpl @Inject constructor(
    private val appDao: AppDao
) {

    fun getAllProducts(): LiveData<List<Product>> {
        return appDao.getProductData()
    }

    suspend fun updateData(product: Product) {
        appDao.updateData(product)
    }

    suspend fun deleteAll() {
        appDao.deleteAll()
    }

    suspend fun addCart(cartItem: CartItem) {
        appDao.addCart(cartItem)
    }

    fun getCartWithProducts(): LiveData<List<CartWithProduct>> {
        return appDao.getCartData()
    }

}
