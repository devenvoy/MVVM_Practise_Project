package com.example.interviewpractise.presentation.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.interviewpractise.data.viewModel.PhoneSignInViewModel
import com.example.interviewpractise.databinding.FragmentOtpBinding
import com.example.interviewpractise.domain.repository.OnStateChanged
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtpFragment : Fragment(), OnStateChanged {

    private lateinit var binding: FragmentOtpBinding

    private val phoneViewModel: PhoneSignInViewModel by viewModels()

    private lateinit var phoneNumber: String


    private val args: OtpFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOtpBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        phoneNumber = args.phoneNumber
        resendOtp()

        phoneViewModel.onStateChanged = this

        binding.submitOtp.setOnClickListener {
            val code = binding.edtOtp.text.toString()
            phoneViewModel.verifyPhoneNumberWithCode(code)
        }


        binding.resendOtp.setOnClickListener {
            phoneViewModel.resendVerificationCode(phoneNumber)
            resendOtp()
        }
    }

    private fun resendOtp() {
        binding.resendOtp.isEnabled = false
        val countdown = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.resendOtp.text = "resend in: ${millisUntilFinished / 1000}s"
            }

            override fun onFinish() {
                binding.resendOtp.text = "resend otp"
                binding.resendOtp.isEnabled = true
            }
        }
        countdown.start()
    }

    override fun onCodeSent() {

    }

    override fun onLoginSuccess(message: String) {
        findNavController().navigate(
            OtpFragmentDirections.actionOtpFragmentToRetrofitFragment()
        )
    }
}