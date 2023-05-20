package com.example.foodomer.ui.components.home

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.foodomer.database.entities.Food
import com.example.foodomer.ui.theme.OrangePrimary

@Composable
fun FoodItem(
    food: Food,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Column(
        modifier =
            modifier
                .width(200.dp)
                .aspectRatio(1.3f)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .border(1.dp,  Color.LightGray, RoundedCornerShape(10.dp))
                .padding(10.dp)
                .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = Uri.parse(food.img),
            "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.8f).clip(RectangleShape)
        )
        Spacer(Modifier.height(5.dp))
        Text(food.name, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, color = OrangePrimary)
    }
}
