package com.example.foodomer.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodomer.utils.now

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = now().time,
    @ColumnInfo(name = "updated_at")
    val updatedAt: Long? = null
)