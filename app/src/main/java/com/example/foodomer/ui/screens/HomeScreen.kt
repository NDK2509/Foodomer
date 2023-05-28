package com.example.foodomer.ui.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.foodomer.navigation.Destinations
import com.example.foodomer.ui.AppViewModelProvider
import com.example.foodomer.ui.components.core.HeaderBar
import com.example.foodomer.ui.components.home.HorizontalCategoryList
import com.example.foodomer.ui.components.home.VerticalFoodList
import com.example.foodomer.ui.components.home.WelcomeParagraph
import com.example.foodomer.ui.viewmodels.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavController? = null,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {}

    LaunchedEffect(null){
        permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        permissionLauncher.launch(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    val foods by viewModel.foodList.collectAsState(initial = emptyList())
    val categories by viewModel.categories.collectAsState(initial = emptyList())

    Column {
        HeaderBar(navController)
        Spacer(Modifier.height(20.dp))

        WelcomeParagraph("Nguyen Dang Ky")
        HorizontalCategoryList(
            items = categories,
            onAddClick = { navController?.navigate(Destinations.CreateCategory.route) }
        )
        VerticalFoodList(
            items = foods,
            onAddClick = { navController?.navigate(Destinations.CreateFood.route) }
        )
    }
}