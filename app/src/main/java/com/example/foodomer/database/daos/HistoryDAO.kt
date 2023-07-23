package com.example.foodomer.database.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.foodomer.database.entities.History
import com.example.foodomer.database.entities.HistoryWithFood
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDAO : BaseDAO<History, Int> {
    @Query("SELECT * from history")
    override fun getAll(): Flow<List<History>>

    @Query("SELECT * from history WHERE id = :id")
    override fun getById(id: Int): Flow<History?>

    @Query("SELECT * from history")
    fun getAllWithFood(): Flow<List<HistoryWithFood>>

    @Query("SELECT * from history WHERE id = :id")
    fun getWithFoodById(id: Int): Flow<HistoryWithFood?>

    @Query("DELETE FROM history")
    fun deleteAll()
}