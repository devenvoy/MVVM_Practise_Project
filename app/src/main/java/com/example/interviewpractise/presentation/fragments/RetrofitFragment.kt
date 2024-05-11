package com.example.interviewpractise.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.interviewpractise.data.viewModel.RetrofitViewModel
import com.example.interviewpractise.databinding.FragmentRetrofitBinding
import com.example.interviewpractise.domain.repository.ResponseListener
import com.example.interviewpractise.presentation.adapters.ProductsRecyclerAdapter
import com.example.interviewpractise.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RetrofitFragment : Fragment(), ResponseListener {

    private lateinit var binding: FragmentRetrofitBinding

    private val myViewModel: RetrofitViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRetrofitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        Log.d("TAG++", "onViewCreated: ")

        myViewModel.responseListener = this

        myViewModel.response.observe(viewLifecycleOwner) {
            val productsRecyclerAdapter =
                ProductsRecyclerAdapter(activity = requireActivity(), it)

            binding.recyclerView.apply {
                layoutManager = GridLayoutManager(activity, 2)
                adapter = productsRecyclerAdapter
            }
        }

        myViewModel.updateData()
    }

    override fun onStarted() {
        binding.progrssbar.visibility = View.VISIBLE
//        activity?.toast("Process Started")
    }

    override fun onSuccess() {
        binding.progrssbar.visibility = View.GONE
//        activity?.toast("Process Success")
    }

    override fun onFailure() {
//        binding.progrssbar.visibility = View.GONE
        activity?.toast("Process Failed")
    }

}