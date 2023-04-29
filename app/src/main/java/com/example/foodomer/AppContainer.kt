package com.example.foodomer

import android.content.Context
import com.example.foodomer.database.AppDatabase
import com.example.foodomer.database.repositories.CategoryRepository
import com.example.foodomer.database.repositories.FoodRepository

interface IAppContainer {
    val foodRepository: FoodRepository
    val categoryRepository: CategoryRepository
}

class AppContainer(context: Context) : IAppContainer {
    private val appDB = AppDatabase.getInstance(context)
    override val foodRepository by lazy {
        FoodRepository(appDB.foodDAO())
    }
    override val categoryRepository by lazy {
        CategoryRepository(appDB.categoryDAO())
    }

}