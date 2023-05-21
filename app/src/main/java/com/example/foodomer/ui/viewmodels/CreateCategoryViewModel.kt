package com.example.foodomer.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodomer.database.entities.Category
import com.example.foodomer.database.repositories.CategoryRepository
import kotlinx.coroutines.launch

class CreateCategoryViewModel(val categoryRepository: CategoryRepository): ViewModel() {
    fun save(name: String) {
        viewModelScope.launch {
            categoryRepository.insert(Category(name = name, description = ""))
        }
    }
}