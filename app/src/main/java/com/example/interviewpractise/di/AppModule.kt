package com.example.interviewpractise.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.interviewpractise.data.repository.PhoneSignInRepositoryImpl
import com.example.interviewpractise.domain.repository.PhoneSignInRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
//        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val connectivityManager =
            context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        return connectivityManager
    }



//    @Provides
//    @Singleton
//    fun provideCheckConnection(connectivityManager: ConnectivityManager): CheckConnection {
//        return CheckConnection(connectivityManager)
//    }


}