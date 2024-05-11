package com.example.interviewpractise.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.interviewpractise.data.viewModel.PhoneSignInViewModel
import com.example.interviewpractise.databinding.FragmentPhoneNumberBinding
import com.example.interviewpractise.domain.repository.OnStateChanged
import com.example.interviewpractise.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhoneNumberFragment : Fragment(), OnStateChanged {

    private val phoneSignInViewModel: PhoneSignInViewModel by viewModels()

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

        phoneSignInViewModel.onStateChanged = this

        binding.generateOtp.setOnClickListener {
            phoneNumber = "+91${binding.edtPhone.text}"
            phoneSignInViewModel.startVerification(requireActivity(), phoneNumber)
            binding.progressBar.visibility = View.VISIBLE
        }

    }

    override fun onCodeSent() {
        activity?.toast("Otp sent Successfully")
        binding.progressBar.visibility = View.GONE
        findNavController().navigate(
            PhoneNumberFragmentDirections.actionPhoneNumberFragmentToOtpFragment(phoneNumber)
        )
    }

    override fun onLoginSuccess(message: String) {

    }


}