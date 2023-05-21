package com.example.foodomer.ui.components.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodomer.ui.theme.OrangePrimary

@Composable
fun WelcomeParagraph(
    username: String
) {
    val fontSizeLarge = 32.sp

    Column {
        Row(
           modifier = Modifier.padding(start = 10.dp)
        ) { Text("Hi,", color = OrangePrimary, fontWeight = FontWeight.Bold, fontSize = fontSizeLarge) }
        Row(
            modifier = Modifier.padding(start = 40.dp)
        ) { Text(username, color = OrangePrimary, fontWeight = FontWeight.Bold, fontSize = fontSizeLarge) }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text("Welcome to ", color = Color.Black, fontWeight = FontWeight.Bold)
            Text("FOODOMER!", color = OrangePrimary, fontWeight = FontWeight.Bold)
        }
    }
}