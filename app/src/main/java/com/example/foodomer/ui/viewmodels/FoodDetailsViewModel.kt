package com.example.foodomer.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodomer.database.entities.FoodWithCategory
import com.example.foodomer.database.repositories.FoodRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FoodDetailsViewModel(private val foodRepository: FoodRepository) : ViewModel() {
    private val _food: MutableStateFlow<FoodWithCategory?> = MutableStateFlow(null)
    val food = _food.asStateFlow()

    fun getFood(id: Int) {
        viewModelScope.launch {
            foodRepository.getWithCategoryById(id).collect {
                _food.value = it
            }
        }
    }
}