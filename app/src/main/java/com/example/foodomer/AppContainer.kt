package com.example.foodomer

import android.content.Context
import com.example.foodomer.database.AppDatabase
import com.example.foodomer.database.repositories.FoodRepository

interface IAppContainer {
    val foodRepository: FoodRepository
}

class AppContainer(context: Context) : IAppContainer {
    private val appDB = AppDatabase.getInstance(context)
    override val foodRepository by lazy {
        FoodRepository(appDB.foodDAO())
    }
}