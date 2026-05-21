package com.example.foodomer.database.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.foodomer.database.entities.Food
import com.example.foodomer.database.entities.FoodWithCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDAO : BaseDAO<Food, Int> {
    @Query("SELECT * from foods WHERE is_deleted = 0")
    fun getAll(): Flow<List<Food>>

    @Query("SELECT * from foods WHERE id = :id")
    fun getById(id: Int): Flow<Food?>

    @Query("UPDATE foods SET is_deleted = 1 WHERE id = :id")
    fun softDelete(id: Int)

    @Query("SELECT * FROM foods WHERE is_deleted = 0 AND category_id = :categoryId")
    fun getByCategoryId(categoryId: Int): Flow<List<Food>>

    @Transaction
    @Query("SELECT * from foods WHERE is_deleted = 0")
    fun getAllWithCategory(): Flow<List<FoodWithCategory>>

    @Transaction
    @Query("SELECT * from foods WHERE is_deleted = 0 and id = :id")
    fun getWithCategoryById(id: Int): Flow<FoodWithCategory?>
}
