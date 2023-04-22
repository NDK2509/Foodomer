package com.example.foodomer.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "foods")
data class Food(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val price: Int,
    val img: String,
    val createdAt: Long,
    val updatedAt: Long? = null
)