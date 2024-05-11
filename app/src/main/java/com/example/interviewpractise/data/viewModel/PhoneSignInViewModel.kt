package com.example.interviewpractise.data.viewModel

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.interviewpractise.domain.repository.PhoneSignInRepository
import com.example.interviewpractise.domain.repository.OnStateChanged
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhoneSignInViewModel @Inject constructor(
    private val phoneSignInRepository: PhoneSignInRepository
) : ViewModel() {

    val auth = Firebase.auth
    var onStateChanged: OnStateChanged? = null
    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken


    fun verifyPhoneNumberWithCode(code: String) {
        // [START verify_with_code]
        val credential = PhoneAuthProvider.getCredential(storedVerificationId!!, code)

        signInWithPhoneAuthCredential(credential = credential)
        // [END verify_with_code]
    }

    fun startVerification(activity: Activity, phoneNumber: String) {
        phoneSignInRepository.startPhoneNumberVerification(
            activity = activity,
            phoneNumber = phoneNumber,
            auth = auth,
            callback = callbacks
        )
    }

    fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("TAG111", "signInWithCredential:success")
                    val user = task.result?.user

                    onStateChanged?.onLoginSuccess("Authentication Success")

                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w("TAG111", "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                }
            }
    }

    var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d("TAG111", "onVerificationCompleted:$credential")
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w("TAG111", "onVerificationFailed", e)

                when (e) {
                    is FirebaseAuthInvalidCredentialsException -> {
                        // Invalid request
                    }

                    is FirebaseTooManyRequestsException -> {
                        // The SMS quota for the project has been exceeded
                    }

                    is FirebaseAuthMissingActivityForRecaptchaException -> {
                        // reCAPTCHA verification attempted with null Activity
                        Log.d("===", "onVerificationFailed: ")
                    }
                }
                // Show a message and update the UI
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken,
            ) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d("TAG111", "onCodeSent:$verificationId")

                // Save verification ID and resending token so we can use them later
                onStateChanged?.onCodeSent()

                storedVerificationId = verificationId
                resendToken = token
            }
        }


    fun resendVerificationCode(
        phoneNumber: String,
    ) {
        phoneSignInRepository.resendVerificationCode(
            auth = auth,
            phoneNumber = phoneNumber,
            resendToken = resendToken,
            callbacks = callbacks
        )
    }

}