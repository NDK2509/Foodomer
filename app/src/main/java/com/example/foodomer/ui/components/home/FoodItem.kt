package com.example.foodomer.ui.components.home

import android.net.Uri
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.foodomer.database.entities.Food
import com.example.foodomer.ui.theme.LightOrange
import com.example.foodomer.ui.theme.OrangePrimary

@Composable
fun DeleteButton(
    onDelete: () -> Unit
) {
    Column(
        modifier =
            Modifier
                .width(40.dp)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .clickable {
                    onDelete()
                },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.Default.Add,
            "",
            tint = Color.Red,
            modifier = Modifier.rotate(45f).width(30.dp).aspectRatio(1f)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun FoodItem(
    food: Food,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onDelete: () -> Unit = {}
) {
    var isLongPressed by remember { mutableStateOf(false) }
    Row(
        modifier =
            modifier
                .combinedClickable(
                    onClick = {
                        if (isLongPressed) {
                            isLongPressed = false
                        } else {
                            onClick()
                        }
                    },
                    onLongClick = {
                        isLongPressed = true
                    }
                )
                .fillMaxWidth()
                .height(85.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(LightOrange)
                .padding(start = 30.dp, end = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            Uri.parse(food.img),
            "",
            modifier =
                Modifier
                    .width(65.dp)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .border(1.dp, OrangePrimary, CircleShape),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxWidth(0.5f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                food.name,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                "${String.format("%,d", food.price)} đ",
                color = Color.Red,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            modifier = Modifier.fillMaxHeight().fillMaxWidth().padding(vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility (
                isLongPressed,
                enter = slideInVertically(),
                exit = slideOutVertically() + fadeOut()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DeleteButton(onDelete)
                }
            }
            AnimatedVisibility (
                !isLongPressed,
                enter = slideInVertically(),
                exit = slideOutVertically() + fadeOut()
            ) {
                Text("You have enjoyed 99 times!", color = Color.Black, fontSize = 8.sp, modifier = Modifier.offset(0.dp, 0.dp))
            }
        }
    }
}

@Preview
@Composable
fun PreviewFoodItem() {
    FoodItem(
        Food(
            0,
            0,
            "Cu ke ngoc",
            9999999,
            "https://www.google.com/url?sa=i&url=https%3A%2F%2Fdepositphotos.com%2Fvector-images%2Fleek-cute-cartoon.html&psig=AOvVaw3dYypkjOHocO55o--csoLs&ust=1684775648244000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCIjUqMj0hv8CFQAAAAAdAAAAABAD",
            ""
        )
    )
}