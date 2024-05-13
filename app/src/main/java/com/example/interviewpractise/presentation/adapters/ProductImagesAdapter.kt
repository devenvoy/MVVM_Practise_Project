package com.example.interviewpractise.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.interviewpractise.R
import com.example.interviewpractise.databinding.ProductImageBinding

class ProductImagesAdapter : RecyclerView.Adapter<ProductImagesAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ProductImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(s: String) {
            binding.productImages.load(s) {
                crossfade(true).crossfade(300).placeholder(R.drawable.placeholder)
            }
        }
    }


    private val differCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ProductImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(differ.currentList[position])
    }

}