package com.example.foodomer.ui.components.randomizer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.foodomer.ui.theme.OrangePrimary


@Composable
fun RandomizerTitle() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text("Randomize ", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
        Text("by", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = OrangePrimary)
    }
}