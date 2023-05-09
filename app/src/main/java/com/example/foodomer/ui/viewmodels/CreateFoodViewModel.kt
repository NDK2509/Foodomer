package com.example.foodomer.ui.viewmodels

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodomer.database.entities.Food
import com.example.foodomer.database.repositories.CategoryRepository
import com.example.foodomer.database.repositories.FoodRepository
import com.example.foodomer.utils.saveUriIntoExternalStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class FoodState(
    var name: String = "", var price: String = "", var categoryId: Int? = null, var description: String = ""
)

class CreateFoodViewModel(
    private val foodRepository: FoodRepository, private val categoryRepository: CategoryRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(FoodState())
    private val _imgUri = MutableStateFlow<Uri?>(null)

    val categories = categoryRepository.getAll()
    val uiState = _uiState.asStateFlow()
    val imgUri = _imgUri.asStateFlow()

    fun setName(name: String) {
        _uiState.value = _uiState.value.copy(name = name)
    }

    fun setPrice(price: String) {
        _uiState.value = _uiState.value.copy(price = price)
    }

    fun setCategoryId(categoryId: Int) {
        _uiState.value = _uiState.value.copy(categoryId = categoryId)
    }

    fun setDescription(description: String) {
        _uiState.value = _uiState.value.copy(description = description)
    }

    fun setImgUri(uri: Uri?) {
        _imgUri.value = uri
    }

    fun saveFood(context: Context) {
        viewModelScope.launch {
            val foodState = uiState.value
            val img = saveUriIntoExternalStorage(context, _imgUri.value!!)
            foodRepository.insert(
                Food(
                    name = foodState.name,
                    categoryId = foodState.categoryId!!,
                    price = foodState.price.toInt(),
                    description = foodState.description,
                    img = img
                )
            )
        }
    }
}