package com.example.foodomer.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.foodomer.database.repositories.FoodRepository

class HomeViewModel(val foodRepository: FoodRepository): ViewModel() {
    val foodList = foodRepository.getAll()
}