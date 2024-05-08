package com.example.interviewpractise.utils

import com.example.interviewpractise.data.repository.PhoneSignInRepositoryImpl
import com.example.interviewpractise.data.repository.RetrofitFragmentRepositoryImpl
import com.example.interviewpractise.domain.repository.PhoneSignInRepository
import com.example.interviewpractise.domain.repository.RetrofitFragmentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
    fun providesRetrofitFragmentRepository(): RetrofitFragmentRepository {
        return RetrofitFragmentRepositoryImpl()
    }

}