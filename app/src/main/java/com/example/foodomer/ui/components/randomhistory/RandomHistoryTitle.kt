package com.example.foodomer.ui.components.randomhistory

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.foodomer.ui.theme.OrangePrimary

@Preview
@Composable
fun RandomHistoryTitle() {
    val style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
    Row {
        Text("FOODOMER ", color = OrangePrimary, style = style)
        Text("Album...", color = Color.Black, style = style)
    }
}