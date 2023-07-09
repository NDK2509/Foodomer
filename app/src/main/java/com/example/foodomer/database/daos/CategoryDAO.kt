package com.example.foodomer.database.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.foodomer.database.entities.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDAO : BaseDAO<Category, Int> {
    @Query("SELECT * from categories WHERE is_deleted = 0")
    override fun getAll(): Flow<List<Category>>

    @Query("SELECT * from categories WHERE id = :id")
    override fun getById(id: Int): Flow<Category?>

    @Query("UPDATE categories SET is_deleted = 1 WHERE id = :id")
    override fun softDelete(id: Int)

    @Query("SELECT EXISTS (SELECT * FROM foods WHERE category_id = :categoryId)")
    suspend fun existsFoodsInCategory(categoryId: Int): Boolean
}