package com.example.interviewpractise.domain.repository

interface OnStateChanged {

    fun onCodeSent()

    fun onLoginSuccess(message: String)

}