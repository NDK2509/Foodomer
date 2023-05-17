package com.example.foodomer.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.foodomer.R
import com.example.foodomer.ui.AppViewModelProvider
import com.example.foodomer.ui.components.core.FoodomerButton
import com.example.foodomer.ui.components.core.Header
import com.example.foodomer.ui.components.createfood.BlinkedWarning
import com.example.foodomer.ui.theme.DEFAULT_PADDING
import com.example.foodomer.ui.theme.OrangePrimary
import com.example.foodomer.ui.viewmodels.RandomizerViewModel
import java.util.*

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RandomizerScreen(
    navController: NavController? = null,
    viewModel: RandomizerViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val foodList by viewModel.foodList.collectAsState(emptyList())

    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp), horizontalArrangement = Arrangement.Center
            ) {
                FoodomerButton {
                    if (foodList.isNotEmpty()) viewModel.randomFood()
                }
            }
        }, backgroundColor = Color.White, drawerBackgroundColor = Color.White
    ) { padding ->
        if (foodList.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BlinkedWarning()
                Spacer(Modifier.height(20.dp))
                Text("You haven't got any food!", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = OrangePrimary)
                Text("Please add some foods first!", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = OrangePrimary)
            }
        } else {
            val food by viewModel.chosenFood.collectAsState()

            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(top = DEFAULT_PADDING, start = DEFAULT_PADDING, end = DEFAULT_PADDING)
            ) {
                Header(onClickMenu = {}, onCLickSearch = {}, modifier = Modifier.padding(bottom = DEFAULT_PADDING)
                )
                Column(
                    modifier = Modifier.fillMaxHeight(0.6f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painterResource(R.drawable.random_result_bg),
                        "",
                        modifier = Modifier.fillMaxWidth().aspectRatio(2 / 3f)
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(0.4f),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (food == null) {
                        Text("No food has chosen!")
                    } else {
                        Text(
                            "Congratulation!\nEnjoy your meal with",
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = OrangePrimary,
                            modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp)
                        )
                        food?.name?.let {
                            Text(
                                it.uppercase(Locale.ROOT),
                                textAlign = TextAlign.Center,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                color = OrangePrimary,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    }
}