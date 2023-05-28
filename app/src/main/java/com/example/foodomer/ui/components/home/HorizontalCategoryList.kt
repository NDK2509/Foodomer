package com.example.foodomer.ui.components.home

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foodomer.database.entities.Category
import com.example.foodomer.ui.components.core.HorizontalScrollList

@Composable
fun HorizontalCategoryList(
    items: List<Category>,
    onItemClick: (Category) -> Unit = {},
    onAddClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp)
    ) {
        ListTitle("Categories", onAddClick = onAddClick)
        Spacer(Modifier.height(10.dp))
        HorizontalScrollList {
            items.map {
                CategoryItem(it, onClick = { onItemClick(it) })
            }
            Spacer(Modifier.width(0.5f.dp))
        }
    }
}