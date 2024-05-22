package com.example.interviewpractise.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "cart"
)
data class CartItem(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var userId: String,
    var productId: Int,
    var productCount: Int,
)


//    foreignKeys = [ForeignKey(
//        entity = Product::class,
//        parentColumns = ["id"],
//        childColumns = ["productId"],
//        onDelete = ForeignKey.CASCADE
//    )]