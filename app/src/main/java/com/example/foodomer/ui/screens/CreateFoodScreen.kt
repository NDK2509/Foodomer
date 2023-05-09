package com.example.foodomer.ui.screens

import android.widget.Toast
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
import com.example.foodomer.ui.components.createfood.ImageSelector
import com.example.foodomer.ui.theme.DEFAULT_PADDING
import com.example.foodomer.ui.theme.OrangePrimary
import com.example.foodomer.ui.viewmodels.CreateFoodViewModel

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
        Column(
            modifier =
            Modifier
                .fillMaxSize()
                .padding(DEFAULT_PADDING)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                CloseButton(onClick = {
                    navController?.navigate("home")
                })
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                ImageSelector(
                    imgUri
                ) {
                    viewModel.setImgUri(it)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(1f),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    CommonTextInput(
                        uiState.name,
                        onChange = viewModel::setName,
                        placeholder = {
                            Text("Food name", color = Color.Gray)
                        },
                        singleLine = true
                    )
                }
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    CommonTextInput(
                        uiState.price,
                        type = KeyboardType.Number,
                        onChange = { viewModel.setPrice(it) },
                        placeholder = {
                            Text("Price (đ)", color = Color.Gray)
                        },
                        singleLine = true
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                CommonSelectionBox(
                    categories.map {
                        Option(it.name, it.id)
                    },
                    onValueChange = viewModel::setCategoryId
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                CommonTextInput(
                    uiState.description,
                    onChange = viewModel::setDescription,
                    placeholder = {
                        Text("Some more description", color = Color.Gray)
                    },
                    minLines = 5
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CommonButton(
                    label = "Save",
                    textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                    icon = {
                        Icon(
                            painterResource(R.drawable.icon_angle_right),
                            "",
                            modifier = Modifier.width(30.dp).aspectRatio(1f),
                            tint = Color.White
                        )
                    },
                    onClick = {
                        try {
                            viewModel.saveFood(context)
                            navController?.navigate("home")
                            Toast.makeText(context, "You have just added ${uiState.name}!", Toast.LENGTH_SHORT).show()
                        } catch (exception: Exception) {
                            Toast.makeText(context, "There are some errors occurred!", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }
        }
    }
    else {
        Column(
            modifier = Modifier.fillMaxSize().padding(DEFAULT_PADDING),
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