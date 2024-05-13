package com.example.interviewpractise.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.interviewpractise.data.models.Product
import com.example.interviewpractise.databinding.FragmentProductDetailBinding
import com.example.interviewpractise.presentation.adapters.ProductImagesAdapter

class ProductDetailFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailBinding

    private val args: ProductDetailFragmentArgs by navArgs()

    private lateinit var currentProduct: Product

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentProduct = args.productItem!!

        (activity as AppCompatActivity).supportActionBar?.title = "${currentProduct.title}"

        updateData()

    }

    fun updateData() {
        val imageAdapter = ProductImagesAdapter()
        imageAdapter.differ.submitList(currentProduct.images)
        binding.productImages.adapter = imageAdapter
        binding.springDotsIndicator.attachTo(binding.productImages)
        binding.productname.text = currentProduct.title
        binding.productPrice.text = currentProduct.price.toString()
        binding.productDescription.text = currentProduct.description
    }

}