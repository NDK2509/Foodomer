package com.example.foodomer.ui.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.foodomer.R
import com.example.foodomer.ui.AppViewModelProvider
import com.example.foodomer.ui.components.core.*
import com.example.foodomer.ui.components.createfood.BlinkedWarning
import com.example.foodomer.ui.components.createfood.CreateFoodTitle
import com.example.foodomer.ui.components.createfood.ImageSelector
import com.example.foodomer.ui.theme.OrangePrimary
import com.example.foodomer.ui.viewmodels.CreateFoodViewModel
import com.example.foodomer.utils.getScreenHeight
import com.example.foodomer.utils.getScreenWidth

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CreateFoodScreen(
    navController: NavController? = null,
    viewModel: CreateFoodViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val uiState by viewModel.uiState.collectAsState()
    val categories by viewModel.categories.collectAsState(initial = emptyList())
    val imgUri by viewModel.imgUri.collectAsState()

    val context = LocalContext.current

    if (categories.isNotEmpty()) {
        val inputLabelFontSize = 16.sp
        val screenWidth = getScreenWidth()
        val screenHeight = getScreenHeight()

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            AbsoluteCircle(150.dp, offsetX = (-200).dp, offsetY = (-230).dp)
            AbsoluteCircle(50.dp, offsetX = (screenWidth - 80).dp, offsetY = (screenHeight * 0.1).dp, OrangePrimary)
            AbsoluteCircle(138.dp, offsetX = (-130).dp, offsetY = (screenHeight * 0.2).dp, OrangePrimary)
            AbsoluteCircle(95.dp, offsetX = (screenWidth * 0.7).dp, offsetY = (screenHeight * 0.35).dp)
            AbsoluteCircle(100.dp, offsetX = (-30).dp, offsetY = (screenHeight - 100).dp, OrangePrimary)
            AbsoluteCircle(150.dp, offsetX = (screenWidth - 280).dp, offsetY = (screenHeight * 0.8).dp)
        }
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            HeaderBar(
                navController,
                showBackButton = true,
                rightItems = {
                    Icon(
                        painterResource(R.drawable.icon_qr_code_scanner),
                        "",
                        tint = OrangePrimary,
                        modifier = Modifier.clickable {  }
                    )
                }
            )
            Spacer(Modifier.height(20.dp))

            CreateFoodTitle()
            Spacer(Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    ImageSelector(
                        uri = imgUri,
                        width = 150.dp,
                        onSelected = viewModel::setImgUri
                    )
                }
                Column(
                    Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    ScrollSelectionBox(
                        options = categories.map {
                            Option(it.name, it.id)
                        },
                        onValueChange = viewModel::setCategoryId
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            Text("What will the food be called?", color = Color.Black, fontSize = inputLabelFontSize, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(10.dp))
            CommonTextInput(
                uiState.name,
                onChange = viewModel::setName,
                placeholder = {
                    Text("Type the food name...", color = Color.Gray)
                }
            )
            Spacer(Modifier.height(20.dp))

            Text("How much is it?", color = Color.Black, fontSize = inputLabelFontSize, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(10.dp))
            CommonTextInput(
                uiState.price,
                type = KeyboardType.Number,
                onChange = viewModel::setPrice,
                placeholder = {
                    Text("Price (đ)...", color = Color.Gray)
                }
            )
            Spacer(Modifier.height(20.dp))

            Text("Some description right there!", color = Color.Black, fontSize = inputLabelFontSize, fontWeight = FontWeight.Bold)
            Text("(Location, your feeling...)", color = Color.Black, fontSize = inputLabelFontSize, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(10.dp))
            CommonTextInput(
                uiState.description,
                onChange = viewModel::setDescription,
                placeholder = {
                    Text("Description about the category...", color = Color.Gray)
                },
                minLines = 5,
                maxLines = 5
            )

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CommonButton(
                    label = "Save",
                    reversedColor = true,
                    textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                    icon = {
                        Icon(
                            painterResource(R.drawable.icon_angle_right),
                            "",
                            modifier = Modifier.width(30.dp).aspectRatio(1f),
                            tint = OrangePrimary
                        )
                    },
                    onClick = {
                        try {
                            val result = viewModel.saveFood(context)
                            if (result) {
                                navController?.navigate("home")
                                Toast.makeText(context, "You have just added ${uiState.name}!", Toast.LENGTH_SHORT).show()
                            }
                            else {
                                Toast.makeText(context, "Please fill all of fields above!", Toast.LENGTH_SHORT).show()
                            }
                        } catch (exception: Exception) {
                            Toast.makeText(context, "There are some errors occurred!", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }
        }
    }
    else {
        HeaderBar(
            navController,
            showBackButton = true,
            iconColor = Color.White
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BlinkedWarning()
            Spacer(Modifier.height(20.dp))
            Text("You haven't got any categories!", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = OrangePrimary)
            Text("Please add some categories first!", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = OrangePrimary)
        }
    }
}