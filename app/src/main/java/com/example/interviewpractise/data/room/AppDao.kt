package com.example.interviewpractise.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.interviewpractise.data.models.CartItem
import com.example.interviewpractise.data.models.CartWithProduct
import com.example.interviewpractise.data.models.Product

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProducts(prodcuts: List<Product>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCart(cartItem: CartItem)

    @Update
    suspend fun updateData(product: Product)

    @Query("delete from product")
    suspend fun deleteAll()

    @Query("Select * from product")
    fun getProductData(): LiveData<List<Product>>

    @Query("select * from cart")
    @Transaction
    fun getCartData(): LiveData<List<CartWithProduct>>

}