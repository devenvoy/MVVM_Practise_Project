package com.example.interviewpractise.di

import com.example.interviewpractise.data.repository.PhoneSignInRepositoryImpl
import com.example.interviewpractise.data.repository.RetrofitFragmentRepositoryImpl
import com.example.interviewpractise.data.retrofit.ApiServices
import com.example.interviewpractise.domain.repository.PhoneSignInRepository
import com.example.interviewpractise.domain.repository.RetrofitFragmentRepository
import com.example.interviewpractise.utils.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun providesPhoneSignInRepository(): PhoneSignInRepository {
        return PhoneSignInRepositoryImpl()
    }

    @Provides
    @Singleton
    fun providesRetrofitFragmentRepository(apiServices: ApiServices): RetrofitFragmentRepository {
        return RetrofitFragmentRepositoryImpl(apiServices)
    }

    @Provides
    @Singleton
    fun providesBaseUrl() = BASE_URL

//    @Provides
//    @Singleton
//    fun providesGson() = GsonBuilder().setLenient().create()


    @Provides
    @Singleton
    fun providesApiServices(baseUrl: String) =
        Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiServices::class.java)

}