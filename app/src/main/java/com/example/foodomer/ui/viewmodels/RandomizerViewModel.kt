package com.example.foodomer.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodomer.database.entities.Food
import com.example.foodomer.database.repositories.TaskRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.random.Random

class RandomizerViewModel(val taskRepository: TaskRepository) : ViewModel() {
    private var _chosenFood = MutableStateFlow<Food?>(null)
    private val _foodList = taskRepository.getAll()

    val chosenFood: StateFlow<Food?> = _chosenFood.asStateFlow()
    fun randomFood() {
        viewModelScope.launch {
            val foods = _foodList.first()
            val numOfFoods = foods.size
            _chosenFood.update {
                val food = foods[Random.nextInt(0, numOfFoods)]
                food
            }
        }
    }
}