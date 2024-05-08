package com.example.interviewpractise.presentation.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interviewpractise.data.models.Products
import com.example.interviewpractise.domain.repository.RetrofitFragmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RetrofitViewModel @Inject constructor(
    private val retrofitFragmentRepository: RetrofitFragmentRepository
) : ViewModel() {

    var responseListener: ResponseListener? = null

    private var _responseBody = MutableLiveData<List<Products>>()

    val response: LiveData<List<Products>>
        get() = _responseBody

    fun updateData() {
        responseListener?.onStarted()
        viewModelScope.launch {
            _responseBody.postValue(retrofitFragmentRepository.makeApiCall())
        }
        if (response.value != null) {
            responseListener?.onSuccess()
        } else {
            responseListener?.onFailure()
        }
    }

}