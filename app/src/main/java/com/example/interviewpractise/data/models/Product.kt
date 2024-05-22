package com.example.interviewpractise.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "product")
data class Product(

    @PrimaryKey
    var id: Int,

    var title: String,

    var description: String,

    var price: Int,

    var discountPercentage: Double,

    var rating: Double,

    var stock: Int,

    var brand: String,

    var category: String,

    var thumbnail: String,

    var images: List<String> = listOf()

) : Parcelable