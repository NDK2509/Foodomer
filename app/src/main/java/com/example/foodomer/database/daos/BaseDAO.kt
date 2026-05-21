package com.example.foodomer.database.daos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

interface BaseDAO<E, ID> {
    @Insert
    suspend fun insert(entity: E)

    @Update
    suspend fun update(entity: E)

    @Delete
    suspend fun delete(entity: E)
}