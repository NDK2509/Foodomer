package com.example.foodomer.ui.components.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodomer.database.entities.Food
import com.example.foodomer.ui.components.core.VerticalScrollList
import com.example.foodomer.ui.theme.OrangePrimary

@Composable
fun VerticalFoodList(
    items: List<Food>,
    onTitleClick: () -> Unit = {},
    onItemClick: (food: Food) -> Unit = {},
    onItemDelete: (food: Food) -> Unit = {},
    onAddClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        ListTitle("Foods", onClick = onTitleClick, onAddClick = onAddClick)
        Spacer(Modifier.height(10.dp))
        if (items.isNotEmpty()) {
            VerticalScrollList {
                items.map {
                    FoodItem(it, onClick = { onItemClick(it) }, onDelete = { onItemDelete(it) })
                }
            }
        } else {
            Text(
                "No food was founded!",
                textAlign = TextAlign.Center, fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = OrangePrimary,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun PreviewFoodList() {
    VerticalFoodList(
        items = listOf(
            Food(
                0,
                0,
                "Cu ke ngoc",
                9999999,
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fdepositphotos.com%2Fvector-images%2Fleek-cute-cartoon.html&psig=AOvVaw3dYypkjOHocO55o--csoLs&ust=1684775648244000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCIjUqMj0hv8CFQAAAAAdAAAAABAD",
                ""
            ),
            Food(
                0,
                0,
                "Cu ke ngoc",
                9999999,
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fdepositphotos.com%2Fvector-images%2Fleek-cute-cartoon.html&psig=AOvVaw3dYypkjOHocO55o--csoLs&ust=1684775648244000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCIjUqMj0hv8CFQAAAAAdAAAAABAD",
                ""
            ),
            Food(
                0,
                0,
                "Cu ke ngoc",
                9999999,
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fdepositphotos.com%2Fvector-images%2Fleek-cute-cartoon.html&psig=AOvVaw3dYypkjOHocO55o--csoLs&ust=1684775648244000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCIjUqMj0hv8CFQAAAAAdAAAAABAD",
                ""
            ), Food(
                0,
                0,
                "Cu ke ngoc",
                9999999,
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fdepositphotos.com%2Fvector-images%2Fleek-cute-cartoon.html&psig=AOvVaw3dYypkjOHocO55o--csoLs&ust=1684775648244000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCIjUqMj0hv8CFQAAAAAdAAAAABAD",
                ""
            )
        )
    )
}