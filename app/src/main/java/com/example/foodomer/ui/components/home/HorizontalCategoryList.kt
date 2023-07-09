package com.example.foodomer.ui.components.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodomer.database.entities.Category
import com.example.foodomer.ui.components.core.HorizontalScrollList
import com.example.foodomer.ui.theme.OrangePrimary

@Composable
fun HorizontalCategoryList(
    items: List<Category>,
    onItemClick: (Category) -> Unit = {},
    onAddClick: () -> Unit = {},
    onDeleteItem: (Category) -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp)
    ) {
        ListTitle("Categories", onAddClick = onAddClick)
        Spacer(Modifier.height(10.dp))
        if (items.isNotEmpty()) {
            HorizontalScrollList {
                items.map {
                    CategoryItem(it, onClick = { onItemClick(it) }, onDelete = { onDeleteItem(it) })
                }
                Spacer(Modifier.width(0.5f.dp))
            }
        } else {
            Text(
                "No category was founded!",
                textAlign = TextAlign.Center, fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = OrangePrimary,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}