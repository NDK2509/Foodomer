package com.example.foodomer.database.repositories

import com.example.foodomer.database.daos.FoodDAO
import com.example.foodomer.database.entities.Food
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TaskRepository(
    private val foodDAO: FoodDAO, private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) {
    fun getAll(): Flow<List<Food>> = foodDAO.getAll()
    fun getById(id: Int): Flow<Food?> = foodDAO.getById(id)
    fun insert(food: Food) = coroutineScope.launch(Dispatchers.IO) {
        foodDAO.insert(food)
    }
    fun updateItem(food: Food) = coroutineScope.launch(Dispatchers.IO) {
        foodDAO.update(food)
    }
    suspend fun delete(food: Food) = coroutineScope.launch(Dispatchers.IO) {
        foodDAO.delete(food)
    }
    fun deleteById(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            val food = foodDAO.getById(id)
            food.collect {
                if (it != null) {
                    foodDAO.delete(it)
                }
            }
        }
    }
}