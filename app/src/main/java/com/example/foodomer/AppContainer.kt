package com.example.foodomer

import android.content.Context
import com.example.foodomer.database.AppDatabase
import com.example.foodomer.database.repositories.TaskRepository

interface IAppContainer {
    val taskRepository: TaskRepository
}

class AppContainer(context: Context) : IAppContainer {
    private val appDB = AppDatabase.getInstance(context)
    override val taskRepository by lazy {
        TaskRepository(appDB.foodDAO())
    }
}