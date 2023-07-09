package com.example.foodomer.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodomer.database.entities.Category
import com.example.foodomer.database.entities.Food
import com.example.foodomer.database.repositories.CategoryRepository
import com.example.foodomer.database.repositories.FoodRepository
import com.example.foodomer.utils.deleteImageByUri
import kotlinx.coroutines.launch

class HomeViewModel(
    private val foodRepository: FoodRepository,
    private val categoryRepository: CategoryRepository
): ViewModel() {
    val foodList = foodRepository.getAll()
    val categories = categoryRepository.getAll()
    val isDeleteDialogOpen = mutableStateOf(false)
    val willDeleteFood = mutableStateOf<Food?>(null)

    fun openDeleteDialog() {
        isDeleteDialogOpen.value = true
    }

    fun closeDeleteDialog() {
        isDeleteDialogOpen.value = false
    }

    fun setDeletedFood(food: Food) {
        willDeleteFood.value = food
    }

    fun deleteFood() {
        viewModelScope.launch {
            willDeleteFood.value?.id?.let { foodRepository.deleteById(it) }
            willDeleteFood.value?.img?.let { deleteImageByUri(it) }
            closeDeleteDialog()
        }
    }

    fun deleteCategory(category: Category) {
        viewModelScope.launch {
            categoryRepository.deleteById(category.id)
        }
    }
}