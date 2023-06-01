package com.example.foodomer.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodomer.database.entities.Food
import com.example.foodomer.database.repositories.CategoryRepository
import com.example.foodomer.database.repositories.FoodRepository
import com.example.foodomer.ui.components.randomizer.RandomizerStatus
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.concurrent.timer
import kotlin.random.Random

class RandomizerViewModel(foodRepository: FoodRepository, categoryRepository: CategoryRepository) : ViewModel() {
    val foodList = foodRepository.getAll()
    val categoryList = categoryRepository.getAll()
    val isRandomizing = mutableStateOf(false)
    val randomizerStatus = mutableStateOf(RandomizerStatus.FREE_STYLE)
    private val categoryId = mutableStateOf<Int?>(null)

    private var _randomizingFood = MutableStateFlow<Food?>(null)
    val randomizingFood = _randomizingFood.asStateFlow()
    private var _chosenFood = MutableStateFlow<Food?>(null)
    val chosenFood: StateFlow<Food?> = _chosenFood.asStateFlow()

    fun randomFood() {
        viewModelScope.launch {
            val foods =
                if (categoryId.value == null) {
                    foodList.first()
                } else {
                    foodList.first().filter { it.categoryId == categoryId.value }
                }
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

    fun toggleRandomizerTab() {
        randomizerStatus.value =
            if (randomizerStatus.value == RandomizerStatus.FREE_STYLE) {
                RandomizerStatus.CATEGORY
            } else {
                categoryId.value = null
                RandomizerStatus.FREE_STYLE
            }
    }

    fun chooseCategory(id: Int) {
        categoryId.value = id
    }
}