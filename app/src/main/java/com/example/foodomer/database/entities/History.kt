package com.example.foodomer.database.entities

import androidx.room.*
import com.example.foodomer.utils.now

@Entity(
    tableName = "history",
    foreignKeys = [
        ForeignKey(
            entity = Food::class,
            parentColumns = ["id"],
            childColumns = ["food_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class History(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "food_id")
    val foodId: Int,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = now().time
)

data class HistoryWithFood(
    val id: Int,
    @ColumnInfo(name = "food_id")
    val foodId: Int,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = now().time,
    @Relation(
        parentColumn = "food_id",
        entityColumn = "id"
    )
    val food: Food
)