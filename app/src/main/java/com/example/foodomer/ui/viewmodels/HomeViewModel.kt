package com.example.foodomer.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.foodomer.database.repositories.TaskRepository

class HomeViewModel(val taskRepository: TaskRepository): ViewModel() {
    val foodList = taskRepository.getAll()
}