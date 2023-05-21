package com.example.foodomer.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.foodomer.database.repositories.CategoryRepository
import com.example.foodomer.database.repositories.FoodRepository

class HomeViewModel(
    foodRepository: FoodRepository,
    categoryRepository: CategoryRepository
): ViewModel() {
    val foodList = foodRepository.getAll()
    val categories = categoryRepository.getAll()
}