package com.example.foodomer.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.foodomer.R
import com.example.foodomer.ui.AppViewModelProvider
import com.example.foodomer.ui.components.core.Header
import com.example.foodomer.ui.components.home.HorizontalFoodList
import com.example.foodomer.ui.components.welcome.AbsoluteRow
import com.example.foodomer.ui.theme.DEFAULT_PADDING
import com.example.foodomer.ui.viewmodels.HomeViewModel

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreen(
    navController: NavController? = null,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val foods by viewModel.foodList.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier.fillMaxSize().padding(top = DEFAULT_PADDING, start = DEFAULT_PADDING, end = DEFAULT_PADDING)
    ) {
        Box(
            modifier = Modifier.fillMaxHeight(0.3f)
        ) {
            Header(onClickMenu = {}, onCLickSearch = {})
            AbsoluteRow(
                offsetY = DEFAULT_PADDING,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painterResource(R.drawable.banner_1),
                    "",
                    modifier =
                    Modifier
                        .width(200.dp)
                        .aspectRatio(1f)
                )
            }
        }
        HorizontalFoodList(foods)
    }
}