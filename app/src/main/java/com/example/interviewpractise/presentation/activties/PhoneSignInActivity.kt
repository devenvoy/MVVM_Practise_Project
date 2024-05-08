package com.example.interviewpractise.presentation.activties

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.interviewpractise.databinding.ActivityPhoneSignInBinding
import com.example.interviewpractise.presentation.OnStateChanged
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhoneSignInActivity : AppCompatActivity() {

    private val binding: ActivityPhoneSignInBinding by lazy {
        ActivityPhoneSignInBinding.inflate(layoutInflater)
    }


    private val viewModel: PhoneSignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.generateOtp.setOnClickListener {
            if (binding.generateOtp.text == "Generate OTP") {
                val phoneNumber = "+91${binding.edtPhone.text}"

                viewModel.startVerification(this, phoneNumber)

                resendOtp()
            } else {

                val code = binding.submitOtp.text.toString()
                viewModel.verifyPhoneNumberWithCode(code)

            }
        }

        binding.resendOtp.setOnClickListener {

            val phoneNumber = "+91${binding.edtPhone.text}"
            viewModel.resendVerificationCode(phoneNumber)
            resendOtp()

        }

        viewModel.onStateChanged = object : OnStateChanged {

            override fun onCodeSent() {
                binding.generateOtp.text = "Submit OTP"
                binding.otpLayout.visibility = View.VISIBLE
                binding.resendOtp.visibility = View.VISIBLE

            }

            override fun onLoginSuccess(message: String) {
                startActivity(
                    Intent(
                        this@PhoneSignInActivity,
                        MainActivity::class.java
                    )
                )
            }
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

    override fun onStart() {
        super.onStart()
        if (viewModel.auth.currentUser != null) {
            startActivity(
                Intent(
                    this@PhoneSignInActivity,
                    MainActivity::class.java
                )
            )
        }
    }


}