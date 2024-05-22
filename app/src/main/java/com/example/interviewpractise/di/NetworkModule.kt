package com.example.interviewpractise.di

import android.content.Context
import com.example.interviewpractise.data.repository.RetrofitFragmentRepositoryImpl
import com.example.interviewpractise.data.retrofit.ApiServices
import com.example.interviewpractise.data.room.AppDao
import com.example.interviewpractise.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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


    @Provides
    @Singleton
    fun providesRetroRepoImpl(
        apiServices: ApiServices,
        appDao: AppDao,
        @ApplicationContext context: Context
    ): RetrofitFragmentRepositoryImpl {
        return RetrofitFragmentRepositoryImpl(
            apiServices,
            appDao,
            context
        )
    }

}