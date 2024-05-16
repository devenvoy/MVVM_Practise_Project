package com.example.interviewpractise.domain.repository

interface OnStateChanged {

    fun onCodeSent()

    fun onFailure(message: String)
    fun onLoginSuccess(message: String)

}