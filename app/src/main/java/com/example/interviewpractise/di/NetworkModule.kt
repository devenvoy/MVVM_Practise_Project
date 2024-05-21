package com.example.interviewpractise.di

import com.example.interviewpractise.data.retrofit.ApiServices
import com.example.interviewpractise.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun providesBaseUrl() = BASE_URL


    @Provides
    @Singleton
    fun providesApiServices(baseUrl: String) =
        Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiServices::class.java)

}