package com.example.foodomer.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.foodomer.ui.AppViewModelProvider
import com.example.foodomer.ui.components.core.HeaderBar
import com.example.foodomer.ui.components.createfood.BlinkedWarning
import com.example.foodomer.ui.components.randomizer.*
import com.example.foodomer.ui.theme.OrangePrimary
import com.example.foodomer.ui.viewmodels.RandomizerViewModel

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RandomizerScreen(
    navController: NavController? = null,
    viewModel: RandomizerViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val foodList by viewModel.foodList.collectAsState(emptyList())
    val chosenFood by viewModel.chosenFood.collectAsState()
    val randomizingFood by viewModel.randomizingFood.collectAsState()
    val isRandomizing by viewModel.isRandomizing
    val context = LocalContext.current

    if (foodList.isNotEmpty()) {
        Column(
            Modifier.fillMaxSize()
        ) {
            HeaderBar(
                navController,
                showBackButton = true
            )

            RandomizerTitle()
            Spacer(Modifier.height(10.dp))
            RandomizerTab()
            Spacer(Modifier.height(50.dp))

            FoodImageFrame(randomizingFood?.img ?: foodList[0].img)
            RandomResultParagraph(if (isRandomizing) null else chosenFood?.name)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                DiceButton(
                    onClick = {
                        if (!isRandomizing) {
                            viewModel.randomFood()
                        }
                        else {
                            Toast.makeText(context, "Foodomer is randomizing!!!", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }
        }
    } else {
        HeaderBar(
            navController,
            showBackButton = true,
            iconColor = OrangePrimary
        )
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
    }
}