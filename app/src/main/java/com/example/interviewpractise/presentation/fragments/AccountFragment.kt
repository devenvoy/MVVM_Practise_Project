package com.example.interviewpractise.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.interviewpractise.databinding.FragmentAccountBinding
import com.example.interviewpractise.presentation.activity.MainActivity
import com.google.firebase.auth.FirebaseAuth


class AccountFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logout.setOnClickListener {
            if (auth.currentUser != null) {
                auth.signOut()
                Intent(requireActivity(), MainActivity::class.java).also {
                    requireActivity().startActivity(it)
                    requireActivity().finish()
                }
            }
        }
    }
}