package com.example.interviewpractise.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Entity(tableName = "product")
@Parcelize
data class Product(

    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id") var id: Int? = null,

    @SerializedName("title")
    @ColumnInfo(name = "title") var title: String? = null,

    @SerializedName("description")
    @ColumnInfo(name = "description")
    var description: String? = null,

    @SerializedName("price")
    @ColumnInfo(name = "price")
    var price: Int? = null,

    @SerializedName("discountPercentage")
    @ColumnInfo(name = "discountPercentage")
    var discountPercentage: Double? = null,

    @SerializedName("rating")
    @ColumnInfo(name = "rating")
    var rating: Double? = null,

    @SerializedName("stock")
    @ColumnInfo(name = "stock")
    var stock: Int? = null,

    @SerializedName("brand")
    @ColumnInfo(name = "brand")
    var brand: String? = null,

    @SerializedName("category")
    @ColumnInfo(name = "category")
    var category: String? = null,

    @SerializedName("thumbnail")
    @ColumnInfo(name = "thumbnail")
    var thumbnail: String? = null,

    @SerializedName("images")
    @ColumnInfo(name = "images")
    var images: List<String> = listOf()

) : Parcelable