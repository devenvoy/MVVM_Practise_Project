package com.example.interviewpractise.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.interviewpractise.data.viewModel.RetrofitViewModel
import com.example.interviewpractise.databinding.FragmentShopBinding
import com.example.interviewpractise.domain.repository.ResponseListener
import com.example.interviewpractise.presentation.adapters.ProductsRecyclerAdapter
import com.example.interviewpractise.utils.toast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ShopFragment : Fragment(), ResponseListener, SearchView.OnQueryTextListener {

    private lateinit var productsRecyclerAdapter: ProductsRecyclerAdapter
    private lateinit var binding: FragmentShopBinding

    private val myViewModel: RetrofitViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.search.setOnQueryTextListener(this)

        myViewModel.responseListener = this

        myViewModel.checkConnection.observe(viewLifecycleOwner) {
            try {
                if (it == true) {
                    binding.netImg.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    myViewModel.getAllData()
                    setAdapter()
                } else {
                    binding.netImg.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
            } catch (e: Exception) {
                Log.e("TAG111", "onViewCreated: " + e.localizedMessage)
            }

        }

        myViewModel.productsResponse.observe(viewLifecycleOwner) {
            productsRecyclerAdapter.differ.submitList(it)
        }

    }

    private fun setAdapter() {
        productsRecyclerAdapter =
            ProductsRecyclerAdapter(requireActivity())
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = productsRecyclerAdapter
        }
    }

    override fun onStarted() {
//        binding.progrssbar.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        binding.progrssbar.visibility = View.GONE
    }

    override fun onFailure(message: String?) {
        binding.progrssbar.visibility = View.GONE
        activity?.toast(message ?: "Process Failed")
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            searchProduct(newText)
        }
        return true
    }

    private fun searchProduct(newText: String) {
        if (newText.trim() != "") {
            myViewModel.searchProduct(newText)
        }
    }

}