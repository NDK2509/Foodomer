package com.example.foodomer.database.repositories

import com.example.foodomer.database.daos.CategoryDAO
import com.example.foodomer.database.entities.Category
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CategoryRepository(
    private val categoryDAO: CategoryDAO,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) {
    fun getAll(): Flow<List<Category>> = categoryDAO.getAll()
    fun getById(id: Int): Flow<Category?> = categoryDAO.getById(id)
    fun insert(category: Category) = coroutineScope.launch(Dispatchers.IO) {
        categoryDAO.insert(category)
    }

    fun updateItem(category: Category) = coroutineScope.launch(Dispatchers.IO) {
        categoryDAO.update(category)
    }

    fun delete(category: Category) = coroutineScope.launch(Dispatchers.IO) {
        categoryDAO.delete(category)
    }

    fun deleteById(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            val category = categoryDAO.getById(id)
            category.collect {
                if (it == null) {
                    return@collect
                }
                val isAnyFoodsInCategory = categoryDAO.existsFoodsInCategory(id)
                if (isAnyFoodsInCategory) {
                    categoryDAO.softDelete(it.id)
                } else {
                    categoryDAO.delete(it)
                }
            }
        }
    }
}