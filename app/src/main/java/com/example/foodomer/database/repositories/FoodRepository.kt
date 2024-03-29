package com.example.foodomer.database.repositories

import com.example.foodomer.database.daos.FoodDAO
import com.example.foodomer.database.entities.Food
import com.example.foodomer.database.entities.FoodWithCategory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FoodRepository(
    private val foodDAO: FoodDAO, private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) {
    fun getAll(): Flow<List<Food>> = foodDAO.getAll()

    fun getAllWithCategory(): Flow<List<FoodWithCategory>> = foodDAO.getAllWithCategory()

    fun getById(id: Int): Flow<Food?> = foodDAO.getById(id)

    fun getWithCategoryById(id: Int): Flow<FoodWithCategory?> = foodDAO.getWithCategoryById(id)

    fun getByCategoryId(categoryId: Int): Flow<List<Food>> = foodDAO.getByCategoryId(categoryId)

    fun insert(food: Food) = coroutineScope.launch(Dispatchers.IO) {
        foodDAO.insert(food)
    }

    fun update(food: Food) = coroutineScope.launch(Dispatchers.IO) {
        foodDAO.update(food)
    }

    fun delete(food: Food) = coroutineScope.launch(Dispatchers.IO) {
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