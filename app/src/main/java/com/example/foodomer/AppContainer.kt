package com.example.foodomer

import android.content.Context
import com.example.foodomer.database.AppDatabase
import com.example.foodomer.database.repositories.CategoryRepository
import com.example.foodomer.database.repositories.FoodRepository
import com.example.foodomer.database.repositories.HistoryRepository

interface IAppContainer {
    val foodRepository: FoodRepository
    val categoryRepository: CategoryRepository
    val historyRepository: HistoryRepository
}

class AppContainer(context: Context) : IAppContainer {
    private val appDB = AppDatabase.getInstance(context)
    override val foodRepository by lazy {
        FoodRepository(appDB.foodDAO())
    }
    override val categoryRepository by lazy {
        CategoryRepository(appDB.categoryDAO())
    }
    override val historyRepository by lazy {
        HistoryRepository(appDB.historyDAO())
    }
}