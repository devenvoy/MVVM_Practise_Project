package com.example.interviewpractise.utils

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun Context.toast(message: String) {
    Toast.makeText(
        applicationContext,
        message,
        Toast.LENGTH_SHORT
    ).show()
}


fun openFragment(
    fragment: Fragment,
    fragmentName: String,
    fragmentContainerId: Int,
    supportFragmentManager: FragmentManager
) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(fragmentContainerId, fragment, fragmentName)
    transaction.disallowAddToBackStack()
    transaction.commit()
}