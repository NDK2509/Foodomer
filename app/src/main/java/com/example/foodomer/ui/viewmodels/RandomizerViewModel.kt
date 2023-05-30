package com.example.foodomer.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodomer.database.entities.Food
import com.example.foodomer.database.repositories.FoodRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.concurrent.timer
import kotlin.random.Random

class RandomizerViewModel(foodRepository: FoodRepository) : ViewModel() {
    val foodList = foodRepository.getAll()
    val isRandomizing = mutableStateOf(false)

    private var _randomizingFood = MutableStateFlow<Food?>(null)
    val randomizingFood = _randomizingFood.asStateFlow()
    private var _chosenFood = MutableStateFlow<Food?>(null)
    val chosenFood: StateFlow<Food?> = _chosenFood.asStateFlow()

    fun randomFood() {
        viewModelScope.launch {
            val foods = foodList.first()
            val numOfFoods = foods.size
            var duration = 0
            var randomIndex: Int

            isRandomizing.value = true
            timer(initialDelay = 0, period = 150) {
                randomIndex = Random.nextInt(0, numOfFoods)
                _randomizingFood.update {
                    foods[randomIndex]
                }
                duration += 150
                if (duration >= 5000) {
                    cancel()
                    _chosenFood.update {
                        foods[randomIndex]
                    }
                    isRandomizing.value = false
                }
            }
        }
    }
}