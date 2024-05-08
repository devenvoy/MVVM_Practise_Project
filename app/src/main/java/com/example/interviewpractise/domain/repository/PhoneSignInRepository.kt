package com.example.interviewpractise.domain.repository

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider

interface PhoneSignInRepository {

    fun startPhoneNumberVerification(
        activity: Activity,
        phoneNumber: String,
        auth: FirebaseAuth,
        callback: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    )

    fun resendVerificationCode(
        auth: FirebaseAuth,
        phoneNumber: String,
        resendToken: PhoneAuthProvider.ForceResendingToken,
        callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    )
}