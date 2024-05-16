package com.example.interviewpractise.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.example.interviewpractise.data.repository.PhoneSignInRepositoryImpl
import com.example.interviewpractise.data.repository.RetrofitFragmentRepositoryImpl
import com.example.interviewpractise.data.repository.RoomRepositoryImpl
import com.example.interviewpractise.data.retrofit.ApiServices
import com.example.interviewpractise.data.room.AppDao
import com.example.interviewpractise.data.room.AppDatabase
import com.example.interviewpractise.domain.repository.PhoneSignInRepository
import com.example.interviewpractise.domain.repository.RetrofitFragmentRepository
import com.example.interviewpractise.domain.repository.RoomRepository
import com.example.interviewpractise.utils.BASE_URL
import com.example.interviewpractise.utils.DB_NAME
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


    @Provides
    @Singleton
    fun providesApiServices(baseUrl: String) =
        Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiServices::class.java)

    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
//        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val connectivityManager =
            context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        return connectivityManager
    }

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, DB_NAME
        ).build()

    @Provides
    @Singleton
    fun providesDaoService(db: AppDatabase) = db.appDao()

//    @Provides
//    @Singleton
//    fun provideCheckConnection(connectivityManager: ConnectivityManager): CheckConnection {
//        return CheckConnection(connectivityManager)
//    }

    @Provides
    @Singleton
    fun providesRoomRepository(appDao: AppDao): RoomRepository = RoomRepositoryImpl(appDao)
}