package com.example.foodomer.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.foodomer.MainApplication
import com.example.foodomer.ui.viewmodels.*

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(
                getApplication().container.foodRepository,
                getApplication().container.categoryRepository
            )
        }

        initializer {
            RandomizerViewModel(
                getApplication().container.foodRepository,
                getApplication().container.categoryRepository,
                getApplication().container.historyRepository
            )
        }

        initializer {
            CreateCategoryViewModel(
                getApplication().container.categoryRepository
            )
        }

        initializer {
            val container = getApplication().container
            CreateFoodViewModel(
                container.foodRepository,
                container.categoryRepository
            )
        }

        initializer {
            FoodDetailsViewModel(
                getApplication().container.foodRepository
            )
        }

        initializer {
            RandomHistoryViewModel(
                getApplication().container.historyRepository
            )
        }
    }
}

fun CreationExtras.getApplication(): MainApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MainApplication)