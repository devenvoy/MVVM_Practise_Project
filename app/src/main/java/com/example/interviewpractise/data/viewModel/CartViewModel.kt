package com.example.interviewpractise.data.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interviewpractise.data.models.CartItem
import com.example.interviewpractise.data.models.CartWithProduct
import com.example.interviewpractise.data.models.Product
import com.example.interviewpractise.data.repository.RoomRepositoryImpl
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val roomRepository: RoomRepositoryImpl
) : ViewModel() {

    private var _cartList = MutableLiveData<List<CartWithProduct>>()

    val cartList: LiveData<List<CartWithProduct>>
        get() = _cartList


    init {
        fetchCartWithProducts()
    }

    val auth = Firebase.auth


    fun addCart(cartItem: CartItem) {
        viewModelScope.launch {
            roomRepository.addCart(cartItem)
        }
    }


    private fun fetchCartWithProducts() {
        roomRepository.getCartWithProducts().observeForever { cartWithProducts ->
            _cartList.postValue(cartWithProducts)
        }
    }
}