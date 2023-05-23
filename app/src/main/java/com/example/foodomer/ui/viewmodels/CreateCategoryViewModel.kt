package com.example.foodomer.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodomer.database.entities.Category
import com.example.foodomer.database.repositories.CategoryRepository
import com.example.foodomer.utils.capitalize
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class CategoryState(
    val name: String = "",
    var description: String = ""
)
class CreateCategoryViewModel(private val categoryRepository: CategoryRepository): ViewModel() {
    private val _uiState: MutableStateFlow<CategoryState> = MutableStateFlow(CategoryState())
    val uiSate = _uiState.asStateFlow()

    fun setName(name: String) {
        _uiState.value = _uiState.value.copy(name = name)
    }

    fun setDescription(description: String) {
        _uiState.value = _uiState.value.copy(description = description)
    }

    fun save(): Boolean {
        var result = true
        viewModelScope.launch {
            if (validate()) {
                val categoryState = uiSate.value
                categoryRepository.insert(Category(name = categoryState.name, description = categoryState.description))
            } else {
                result = false
            }
        }

        return result
    }

    private fun validate(): Boolean {
        val categoryState = uiSate.value
        val name = categoryState.name.trim()
        val description = categoryState.description.trim()

        _uiState.value = CategoryState(name = name.capitalize(), description = description)
        return !(name == "" || description == "")
    }
}