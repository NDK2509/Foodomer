package com.example.foodomer.ui.components.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodomer.FakeData.fakeFood
import com.example.foodomer.R
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
                .aspectRatio(1.5f)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .border(1.dp,  Color.LightGray, RoundedCornerShape(10.dp))
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(R.drawable.ribs_dish), ""
        )
        Text(food.name, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, color = OrangePrimary)
    }
}

@Preview
@Composable
fun PreviewFoodItem() {
    FoodItem(food = fakeFood)
}