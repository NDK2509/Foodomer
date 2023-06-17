package com.example.foodomer.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.foodomer.navigation.Destinations
import com.example.foodomer.ui.AppViewModelProvider
import com.example.foodomer.ui.components.core.CommonDialog
import com.example.foodomer.ui.components.core.HeaderBar
import com.example.foodomer.ui.components.home.HorizontalCategoryList
import com.example.foodomer.ui.components.home.VerticalFoodList
import com.example.foodomer.ui.components.home.WelcomeParagraph
import com.example.foodomer.ui.theme.OrangePrimary
import com.example.foodomer.ui.viewmodels.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavController? = null,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {}

    LaunchedEffect(null) {
        permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        permissionLauncher.launch(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    val foods by viewModel.foodList.collectAsState(initial = emptyList())
    val categories by viewModel.categories.collectAsState(initial = emptyList())
    val isDeleteDialogOpen by viewModel.isDeleteDialogOpen
    val willDeleteFood by viewModel.willDeleteFood

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
            onAddClick = { navController?.navigate(Destinations.CreateFood.route) },
            onItemDelete = {
                viewModel.setDeletedFood(it)
                viewModel.openDeleteDialog()
            }
        )
    }

    CommonDialog(
        isOpen = isDeleteDialogOpen,
        modifier = Modifier.fillMaxWidth().aspectRatio(1f),
        onConfirm = { viewModel.deleteFood() },
        onDismiss = { viewModel.closeDeleteDialog() }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                Uri.parse(willDeleteFood?.img),
                "",
                modifier =
                Modifier
                    .width(100.dp)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .border(2.dp, OrangePrimary, CircleShape),
                contentScale = ContentScale.Crop
            )
            Column {
                Text("Do you want to delete", color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                willDeleteFood?.name?.let {
                    Text(
                        "$it?",
                        color = OrangePrimary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}