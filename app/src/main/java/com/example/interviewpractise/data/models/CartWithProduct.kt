package com.example.interviewpractise.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class CartWithProduct(

    @Embedded
    val cartItem: CartItem,

    @Relation(
        parentColumn = "productId",
        entityColumn = "id"
    )
    val product: Product
)
