package com.example.interviewpractise.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.interviewpractise.R
import com.example.interviewpractise.data.viewModel.RetrofitViewModel
import com.example.interviewpractise.databinding.FragmentShopBinding
import com.example.interviewpractise.domain.repository.ResponseListener
import com.example.interviewpractise.presentation.adapters.ProductsRecyclerAdapter
import com.example.interviewpractise.utils.toast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ShopFragment : Fragment(), ResponseListener, SearchView.OnQueryTextListener, MenuProvider {

    private lateinit var productsRecyclerAdapter: ProductsRecyclerAdapter
    private lateinit var binding: FragmentShopBinding

    private val myViewModel: RetrofitViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShopBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        Log.d("TAG++", "onViewCreated: ")

        myViewModel.responseListener = this
        setAdapter()
        myViewModel.getAllData()

//        val menuHost: MenuHost = requireActivity()
//        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)



        (activity as AppCompatActivity).supportActionBar?.apply {
            title = "3"
        }

        myViewModel.productsResponse.observe(viewLifecycleOwner) {
            productsRecyclerAdapter.differ.submitList(it)
        }
    }

    fun setAdapter() {
        productsRecyclerAdapter =
            ProductsRecyclerAdapter(requireActivity())
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = productsRecyclerAdapter
        }
    }

    override fun onStarted() {
        binding.progrssbar.visibility = View.VISIBLE
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
        myViewModel.searchProduct(newText)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.search_menu, menu)

        val menuSearch =
            menu.findItem(R.id.search_item).actionView as SearchView
        menuSearch.isSubmitButtonEnabled = true
        menuSearch.setOnQueryTextListener(this)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return false
    }

}