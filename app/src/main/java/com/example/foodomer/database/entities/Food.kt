package com.example.foodomer.database.entities

import androidx.room.*
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
    ],
    indices = [
        Index("category_id")
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
    val description: String,
    @ColumnInfo(name = "is_deleted", defaultValue = "false")
    val deleted: Boolean = false,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = now().time,
    @ColumnInfo(name = "updated_at")
    val updatedAt: Long? = null
)

data class FoodWithCategory(
    @Embedded
    val food: Food,
    @Relation(
        parentColumn = "category_id",
        entityColumn = "id"
    )
    val category: Category
)
