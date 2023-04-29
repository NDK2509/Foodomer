package com.example.foodomer.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.foodomer.utils.now

@Entity(
    tableName = "foods",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["category_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class Food(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "category_id")
    val categoryId: Int,
    val name: String,
    val price: Int,
    val img: String,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = now().time,
    @ColumnInfo(name = "updated_at")
    val updatedAt: Long? = null
)