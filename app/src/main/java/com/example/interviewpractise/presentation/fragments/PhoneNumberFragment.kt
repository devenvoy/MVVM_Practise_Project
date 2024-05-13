package com.example.interviewpractise.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.interviewpractise.databinding.FragmentPhoneNumberBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhoneNumberFragment : Fragment() {


    private lateinit var binding: FragmentPhoneNumberBinding

    private lateinit var phoneNumber: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPhoneNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.generateOtp.setOnClickListener {
            phoneNumber = "+91${binding.edtPhone.text}"
            binding.progressBar.visibility = View.VISIBLE
            findNavController().navigate(
                PhoneNumberFragmentDirections.actionPhoneNumberFragmentToOtpFragment(
                    phoneNumber,
                )
            )
            binding.progressBar.visibility = View.GONE
        }
    }
}