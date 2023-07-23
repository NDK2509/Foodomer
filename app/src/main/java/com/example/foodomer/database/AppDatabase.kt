package com.example.foodomer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodomer.database.daos.CategoryDAO
import com.example.foodomer.database.daos.FoodDAO
import com.example.foodomer.database.daos.HistoryDAO
import com.example.foodomer.database.entities.Category
import com.example.foodomer.database.entities.Food
import com.example.foodomer.database.entities.History

@Database(entities = [Category::class, Food::class, History::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun foodDAO(): FoodDAO
    abstract fun categoryDAO(): CategoryDAO
    abstract fun historyDAO(): HistoryDAO

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