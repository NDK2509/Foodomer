package com.example.foodomer.ui.components.createfood

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.foodomer.ui.theme.OrangePrimary

@Preview
@Composable
fun CreateFoodTitle() {
    val fontSize = 25.sp
    Row {
        Text("A new ", color = Color.Black, fontSize = fontSize, fontWeight = FontWeight.Bold)
        Text("Food ", color = OrangePrimary, fontSize = fontSize, fontWeight = FontWeight.Bold)
        Text("with ", color = Color.Black, fontSize = fontSize, fontWeight = FontWeight.Bold)
        Text("Foodomer!", color = OrangePrimary, fontSize = fontSize, fontWeight = FontWeight.Bold)
    }
}