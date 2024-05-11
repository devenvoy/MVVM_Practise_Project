package com.example.interviewpractise.data.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interviewpractise.data.models.Product
import com.example.interviewpractise.domain.repository.RetrofitFragmentRepository
import com.example.interviewpractise.domain.repository.ResponseListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RetrofitViewModel @Inject constructor(
    private val retrofitFragmentRepository: RetrofitFragmentRepository
) : ViewModel() {

    var responseListener: ResponseListener? = null

    private var _responseBody = MutableLiveData<List<Product>>()

    val response: LiveData<List<Product>>
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