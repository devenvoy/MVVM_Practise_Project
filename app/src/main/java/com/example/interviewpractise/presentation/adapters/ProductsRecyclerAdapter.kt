package com.example.interviewpractise.presentation.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.interviewpractise.R
import com.example.interviewpractise.data.models.Products
import com.example.interviewpractise.databinding.ProductItemBinding

class ProductsRecyclerAdapter(val activity: Activity, private val productList: List<Products>) :
    RecyclerView.Adapter<ProductsRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(product: Products) {
            binding.imageView.load(product.thumbnail) {
                crossfade(true)
                crossfade(300)
                placeholder(R.drawable.placeholder)
                transformations(
                    RoundedCornersTransformation(12f)
                )
            }
            binding.titleText.text = product.title
            binding.titlePrice.text = "₹ ${product.price}"
        }
    }

    @SuppressLint("ResourceType")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ProductItemBinding.inflate(
                LayoutInflater.from(activity),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(productList[position])
    }

    override fun getItemCount(): Int = productList.size
}