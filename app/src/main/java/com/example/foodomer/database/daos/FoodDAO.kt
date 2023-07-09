package com.example.foodomer.database.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.foodomer.database.entities.Food
import com.example.foodomer.database.entities.FoodWithCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDAO : BaseDAO<Food, Int> {
    @Query("SELECT * from foods WHERE is_deleted = 0")
    override fun getAll(): Flow<List<Food>>

    @Query("SELECT * from foods WHERE id = :id")
    override fun getById(id: Int): Flow<Food?>

    @Query("UPDATE foods SET is_deleted = 1 WHERE id = :id")
    override fun softDelete(id: Int)

    @Query("SELECT * FROM foods WHERE is_deleted = 0 AND category_id = :categoryId")
    fun getByCategoryId(categoryId: Int): Flow<List<Food>>

    @Query("SELECT * from foods WHERE is_deleted = 0")
    fun getAllWithCategory(): Flow<List<FoodWithCategory>>

    @Query("SELECT * from foods WHERE is_deleted = 0 and id = :id")
    fun getWithCategoryById(id: Int): Flow<FoodWithCategory?>
}
