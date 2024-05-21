package com.example.interviewpractise.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.interviewpractise.data.models.Product

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: List<Product>)

    @Insert
    suspend fun addProducts(prodcuts : List<Product>)

    @Delete
    suspend fun deleteProduct(product: Product)

    @Query("Select * from product")
    fun getAllProducts(): LiveData<List<Product>>

    @Update
    suspend fun updateData(product: Product)

    @Query("delete from product")
    fun deleteAll()

}