package com.example.foodomer.database.daos

import androidx.room.*
import com.example.foodomer.database.entities.Food
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDAO {
    @Query("SELECT * from foods")
    fun getAll(): Flow<List<Food>>
    @Query("SELECT * from foods WHERE id = :id")
    fun getById(id: Int): Flow<Food?>
    @Insert
    suspend fun insert(food: Food)
    @Update
    suspend fun update(food: Food)
    @Delete
    suspend fun delete(food: Food)
}