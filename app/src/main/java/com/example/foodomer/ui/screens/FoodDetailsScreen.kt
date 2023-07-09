package com.example.foodomer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.foodomer.R
import com.example.foodomer.ui.AppViewModelProvider
import com.example.foodomer.ui.components.core.CenteredColumn
import com.example.foodomer.ui.components.core.HeaderBar
import com.example.foodomer.ui.components.core.HeaderIcon
import com.example.foodomer.ui.theme.OrangePrimary
import com.example.foodomer.ui.viewmodels.FoodDetailsViewModel
import com.example.foodomer.utils.commonDateFormatter
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

        food?.let {
            CenteredColumn {
                Text(it.food.name.uppercase(), fontWeight = FontWeight.Bold, fontSize = 25.sp, color = OrangePrimary)
                Spacer(Modifier.height(10.dp))
                Text("Created at: ${commonDateFormatter.format(Date(it.food.createdAt))}", fontWeight = FontWeight.Bold)
            }
        }
    }
}