package com.example.interviewpractise.di

import android.content.Context
import androidx.room.Room
import com.example.interviewpractise.data.repository.RoomRepositoryImpl
import com.example.interviewpractise.data.room.AppDao
import com.example.interviewpractise.data.room.AppDatabase
import com.example.interviewpractise.utils.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

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

}