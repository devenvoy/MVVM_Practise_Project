package com.example.interviewpractise.data.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.interviewpractise.data.models.Product
import com.example.interviewpractise.domain.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val roomRepository: RoomRepository
) : ViewModel() {

    private lateinit var _cartList: MutableLiveData<List<Product>>

    private val cartList: LiveData<List<Product>>
        get() = _cartList

    fun getAllProduct() {
        CoroutineScope(Dispatchers.IO).launch {
            _cartList.postValue(roomRepository.getAllProducts().value)
        }
    }

    fun insertProduct(product: Product) {
        CoroutineScope(Dispatchers.IO).launch {
            roomRepository.insertProduct(product)
        }
    }

    fun updateProduct(product: Product) {
        CoroutineScope(Dispatchers.IO).launch {
            roomRepository.updateData(product)
        }
    }

    fun deleteProduct(product: Product) {
        CoroutineScope(Dispatchers.IO).launch {
            roomRepository.deleteProduct(product)
        }
    }

    fun deleteAllProducts() {
        CoroutineScope(Dispatchers.IO).launch {
            roomRepository.deleteAll()
        }
    }
}