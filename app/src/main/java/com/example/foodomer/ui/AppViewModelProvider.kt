package com.example.foodomer.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.foodomer.MainApplication
import com.example.foodomer.ui.viewmodels.CreateCategoryViewModel
import com.example.foodomer.ui.viewmodels.CreateFoodViewModel
import com.example.foodomer.ui.viewmodels.HomeViewModel
import com.example.foodomer.ui.viewmodels.RandomizerViewModel

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
                getApplication().container.foodRepository
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
    }
}

fun CreationExtras.getApplication(): MainApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MainApplication)