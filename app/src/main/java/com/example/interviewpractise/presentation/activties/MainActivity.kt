package com.example.interviewpractise.presentation.activties

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.interviewpractise.databinding.ActivityMainBinding
import com.example.interviewpractise.presentation.fragments.RetrofitFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

//
//    private lateinit var sharedPreferences: SharedPreferences
//    private lateinit var editor: Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        sharedPreferences = getSharedPreferences(SP_FILE, MODE_PRIVATE)
//        editor = sharedPreferences.edit()

//
//        supportFragmentManager.beginTransaction()
//            .replace(binding.fragmentContainer.id, RetrofitFragment()).commit()

        openFragment(RetrofitFragment(), "wr", binding.fragmentContainer.id)


    }

    fun openFragment(fragment: Fragment, fragmentName: String, fragmentContainerId: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(fragmentContainerId, fragment, fragmentName)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }

}