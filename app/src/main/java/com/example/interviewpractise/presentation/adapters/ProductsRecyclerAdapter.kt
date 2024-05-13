package com.example.interviewpractise.presentation.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.interviewpractise.R
import com.example.interviewpractise.data.models.Product
import com.example.interviewpractise.databinding.ProductItemBinding
import com.example.interviewpractise.presentation.fragments.RetrofitFragmentDirections

class ProductsRecyclerAdapter(val activity: Activity) :
    RecyclerView.Adapter<ProductsRecyclerAdapter.MyViewHolder>() {


    private val differCallback = object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)


    class MyViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(product: Product) {
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
        holder.onBind(differ.currentList[position])

        holder.itemView.setOnClickListener {
            it.findNavController().navigate(
                RetrofitFragmentDirections.actionRetrofitFragmentToProductDetailFragment(
                    differ.currentList[position]
                )
            )
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}