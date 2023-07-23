package com.example.foodomer.database.daos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

interface BaseDAO<E, ID> {
    fun getAll(): Flow<List<E>>
    fun getById(id: ID): Flow<E?>

    @Insert
    suspend fun insert(entity: E)

    @Update
    suspend fun update(entity: E)

    @Delete
    suspend fun delete(entity: E)
}

interface SoftDelete<ID> {
    fun softDelete(id: ID)
}