package com.example.foodomer.database.repositories

import com.example.foodomer.database.daos.HistoryDAO
import com.example.foodomer.database.entities.History
import com.example.foodomer.database.entities.HistoryWithFood
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HistoryRepository(
    private val historyDAO: HistoryDAO,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) {
    fun getAll(): Flow<List<History>> = historyDAO.getAll()

    fun getAllWithFood(): Flow<List<HistoryWithFood>> = historyDAO.getAllWithFood()

    fun getById(id: Int): Flow<History?> = historyDAO.getById(id)

    fun getWithFoodById(id: Int): Flow<HistoryWithFood?> = historyDAO.getWithFoodById(id)

    fun insert(history: History) = coroutineScope.launch(Dispatchers.IO) {
        historyDAO.insert(history)
    }

    fun update(history: History) = coroutineScope.launch(Dispatchers.IO) {
        historyDAO.update(history)
    }

    fun delete(history: History) = coroutineScope.launch(Dispatchers.IO) {
        historyDAO.delete(history)
    }

    fun deleteById(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            val history = historyDAO.getById(id)
            history.collect {
                if (it != null) {
                    historyDAO.delete(it)
                }
            }
        }
    }

    fun deleteAll() = historyDAO.deleteAll()
}