package com.example.foodomer.database.daos

import androidx.room.*
import com.example.foodomer.database.entities.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDAO {
    @Query("SELECT * from categories")
    fun getAll(): Flow<List<Category>>

    @Query("SELECT * from categories WHERE id = :id")
    fun getById(id: Int): Flow<Category?>

    @Insert
    suspend fun insert(category: Category)

    @Update
    suspend fun update(category: Category)

    @Delete
    suspend fun delete(category: Category)
}