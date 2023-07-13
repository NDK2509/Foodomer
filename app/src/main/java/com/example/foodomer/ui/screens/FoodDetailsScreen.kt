package com.example.foodomer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.foodomer.R
import com.example.foodomer.ui.AppViewModelProvider
import com.example.foodomer.ui.components.core.*
import com.example.foodomer.ui.components.randomizer.FoodImageFrame
import com.example.foodomer.ui.theme.OrangePrimary
import com.example.foodomer.ui.viewmodels.FoodDetailsViewModel
import com.example.foodomer.utils.capitalize
import com.example.foodomer.utils.commonDateFormatter
import com.example.foodomer.utils.thousandGroupByDot
import java.util.*

@Composable
fun FoodDetailsScreen(
    navController: NavController? = null,
    foodId: Int,
    viewModel: FoodDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    LaunchedEffect(Unit) {
        viewModel.getFood(foodId)
    }

    val food by viewModel.food.collectAsState()

    Column {
        HeaderBar(
            navController,
            showBackButton = true,
            rightItems = {
                HeaderIcon(
                    resourceId = R.drawable.icon_share,
                    onClick = {}
                )
            }
        )
        Spacer(Modifier.height(20.dp))

        ScrollableColumn(Modifier.fillMaxHeight(0.9f)) {
            food?.let {
                CenteredColumn(
                    modifier = Modifier.fillMaxHeight(0.8f)
                ) {
                    Text(
                        it.food.name.capitalize(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color = OrangePrimary
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        "Created at: ${commonDateFormatter.format(Date(it.food.createdAt))}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Spacer(Modifier.height(20.dp))
                    FoodImageFrame(it.food.img)
                }

                Spacer(Modifier.height(30.dp))
                Column {
                    val commonStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
                    Row {
                        Text("Price: ", style = commonStyle)
                        Text("${it.food.price.thousandGroupByDot()} đ", color = OrangePrimary, style = commonStyle)
                    }
                    Spacer(Modifier.height(20.dp))
                    Text(it.food.description, style = commonStyle)
                }
            }
        }
        CenteredColumn {
            CommonButton(
                label = "Update information",
                textStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                icon = {
                    Icon(
                        painterResource(R.drawable.icon_angle_right),
                        "",
                        modifier = Modifier.width(20.dp).aspectRatio(1f),
                        tint = Color.White
                    )
                },
                onClick = {}
            )
        }
    }
}