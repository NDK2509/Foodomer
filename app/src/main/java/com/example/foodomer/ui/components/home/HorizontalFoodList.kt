package com.example.foodomer.ui.components.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foodomer.database.entities.Food
import com.example.foodomer.ui.components.core.HorizontalScrollList

@Composable
fun HorizontalFoodList(
    foods: List<Food>, onTitleClick: () -> Unit = {}, onItemClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp)
    ) {
        ListTitle("List of foods", onClick = onTitleClick)
        HorizontalScrollList {
            foods.map {
                FoodItem(it, onClick = onItemClick)
            }
        }
    }
}
