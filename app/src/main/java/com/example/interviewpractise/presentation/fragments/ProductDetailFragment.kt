package com.example.interviewpractise.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.interviewpractise.data.models.CartItem
import com.example.interviewpractise.data.models.Product
import com.example.interviewpractise.data.viewModel.CartViewModel
import com.example.interviewpractise.databinding.FragmentProductDetailBinding
import com.example.interviewpractise.presentation.adapters.ProductImagesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailBinding

    private val args: ProductDetailFragmentArgs by navArgs()

    private val cartViewModel: CartViewModel by viewModels()

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

        binding.addToCart.setOnClickListener {
            cartViewModel.addCart(
                CartItem(
                    0,
                    cartViewModel.auth.currentUser?.uid ?: "vuvu",
                    currentProduct.id,
                    1
                )
            )
        }
    }

    private fun updateData() {
        val imageAdapter = ProductImagesAdapter()
        imageAdapter.differ.submitList(currentProduct.images)
        binding.productImages.adapter = imageAdapter
        binding.springDotsIndicator.attachTo(binding.productImages)
        binding.productname.text = currentProduct.title
        binding.productPrice.text = currentProduct.price.toString()
        binding.productDescription.text = currentProduct.description
    }

}