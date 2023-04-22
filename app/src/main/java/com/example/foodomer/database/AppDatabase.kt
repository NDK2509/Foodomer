package com.example.foodomer.database

import android.content.Context
import androidx.room.*
import com.example.foodomer.database.daos.FoodDAO
import com.example.foodomer.database.entities.Food

@Database(entities = [Food::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun foodDAO(): FoodDAO
    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "FoodomerDB")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}