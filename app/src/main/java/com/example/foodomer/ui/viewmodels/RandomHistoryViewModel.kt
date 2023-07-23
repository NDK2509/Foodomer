package com.example.foodomer.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.foodomer.database.repositories.HistoryRepository

class RandomHistoryViewModel(
    private val historyRepository: HistoryRepository
) : ViewModel() {
    val historyList = historyRepository.getAllWithFood()
}