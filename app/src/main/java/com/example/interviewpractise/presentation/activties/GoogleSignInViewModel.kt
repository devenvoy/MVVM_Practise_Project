package com.example.interviewpractise.presentation.activties

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.interviewpractise.presentation.OnStateChanged
import com.example.interviewpractise.utils.REQUEST_ID_TOKEN
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel

class GoogleSignInViewModel : ViewModel() {

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(REQUEST_ID_TOKEN)
        .requestEmail()
        .build()

    val auth = Firebase.auth

    var onStateChanged: OnStateChanged? = null

    fun firebaseAuthWithGoogle(activity: Activity, idToken: String) {

        val credential = GoogleAuthProvider.getCredential(idToken, null)

        auth.signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("=+=+=+", "signInWithCredential:success")
                    //  Go to Main Page if Login Success
                    onStateChanged?.onLoginSuccess("Sign In SuccessFull")
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("=+=+=+", "signInWithCredential:failure", task.exception)
                    onStateChanged?.onLoginSuccess("Error Occurred")
                }
            }
    }

}