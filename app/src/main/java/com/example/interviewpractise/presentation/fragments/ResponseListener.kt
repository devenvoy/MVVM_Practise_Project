package com.example.interviewpractise.presentation.fragments

interface ResponseListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure()
}