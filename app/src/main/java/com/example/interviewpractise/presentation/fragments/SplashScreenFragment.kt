package com.example.interviewpractise.presentation.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.interviewpractise.databinding.FragmentSplashScreenBinding
import com.example.interviewpractise.presentation.activity.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : Fragment() {

    private lateinit var binding: FragmentSplashScreenBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth

        val navController = Navigation.findNavController(view)

        Handler(Looper.getMainLooper()).postDelayed(
            {
                if (auth.currentUser != null) {
                    Intent(requireActivity(), HomeActivity::class.java).also {
                        requireActivity().startActivity(it)
                        requireActivity().finish()
                    }
                } else {
                    navController.navigate(
                        SplashScreenFragmentDirections.actionSplashScreenFragmentToPhoneNumberFragment()
                    )
                }
            }, 2000
        )
    }
}
