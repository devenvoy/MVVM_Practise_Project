package com.example.interviewpractise.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.interviewpractise.data.models.Product


//@Database(entities = [Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    // Declare your data access objects as abstract
    abstract fun appDao(): AppDao?

    companion object {
        lateinit var databse: AppDatabase
        val DB_NAME = "MyDatabase"

        private fun createDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DB_NAME
            ).build()
        }
    }
}