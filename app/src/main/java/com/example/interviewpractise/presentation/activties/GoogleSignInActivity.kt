package com.example.interviewpractise.presentation.activties

import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.interviewpractise.databinding.ActivityGoogleSignInBinding
import com.example.interviewpractise.presentation.OnStateChanged
import com.example.interviewpractise.utils.toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException

class GoogleSignInActivity : AppCompatActivity() {


    private val binding: ActivityGoogleSignInBinding by lazy {
        ActivityGoogleSignInBinding.inflate(layoutInflater)
    }

    private val googleSignInViewModel: GoogleSignInViewModel by viewModels()

    lateinit var googleSignInClient: GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.signInButton.setSize(SignInButton.SIZE_ICON_ONLY)

        googleSignInClient =
            GoogleSignIn.getClient(this@GoogleSignInActivity, googleSignInViewModel.gso)


        binding.btnLogout.setOnClickListener {
            if (googleSignInViewModel.auth.currentUser != null) {
                googleSignInViewModel.auth.signOut()
            } else {
                toast("Login Or Sign In First")
            }
        }

        binding.signInButton.setOnClickListener {
          signInWithGoogle()
        }

        googleSignInViewModel.onStateChanged = object : OnStateChanged {
            override fun onCodeSent() {}

            override fun onLoginSuccess(message: String) {
                toast(message)
            }
        }
    }

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        googleActivityResult.launch(signInIntent)
    }

    private val googleActivityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d("=+=+=+", "firebaseAuthWithGoogle:" + account.id)
                googleSignInViewModel.firebaseAuthWithGoogle(
                    activity = this@GoogleSignInActivity,
                    idToken = account.idToken!!
                )
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("=+=+=+", "Google sign in failed", e)
            }
        }


}