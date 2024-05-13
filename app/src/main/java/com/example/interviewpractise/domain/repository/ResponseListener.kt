package com.example.interviewpractise.domain.repository

interface ResponseListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String?)
}