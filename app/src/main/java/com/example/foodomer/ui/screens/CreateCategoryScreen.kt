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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.foodomer.R
import com.example.foodomer.ui.AppViewModelProvider
import com.example.foodomer.ui.components.core.AbsoluteCircle
import com.example.foodomer.ui.components.core.CommonButton
import com.example.foodomer.ui.components.core.CommonTextInput
import com.example.foodomer.ui.components.core.HeaderBar
import com.example.foodomer.ui.components.createcategory.CreateCategoryTitleParagraph
import com.example.foodomer.ui.theme.OrangePrimary
import com.example.foodomer.ui.viewmodels.CreateCategoryViewModel
import com.example.foodomer.utils.getScreenHeight
import com.example.foodomer.utils.getScreenWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.example.foodomer.utils.bobbing
import com.example.foodomer.utils.scaleInOnShow

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CreateCategoryScreen(
    navController: NavController? = null,
    viewModel: CreateCategoryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val screenWidth = getScreenWidth()
    val screenHeight = getScreenHeight()
    val inputLabelFontSize = 13.sp
    val context = LocalContext.current
    val uiState by viewModel.uiSate.collectAsState()
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AbsoluteCircle(145.dp, offsetX = (screenWidth - 145 - 20).dp, offsetY = (-97).dp, modifier = Modifier.scaleInOnShow(delay = 100).bobbing(amplitude = 12f, duration = 2400, delay = 0))
        AbsoluteCircle(90.dp, offsetX = (-150).dp, offsetY = 75.dp, color = OrangePrimary, modifier = Modifier.scaleInOnShow(delay = 200).bobbing(amplitude = 8f, duration = 2000, delay = 100))
        AbsoluteCircle(103.dp, offsetX = (screenWidth - 103).dp, offsetY = (screenHeight * 0.6).dp, color = OrangePrimary, modifier = Modifier.scaleInOnShow(delay = 300).bobbing(amplitude = 10f, duration = 2200, delay = 200))
        AbsoluteCircle(153.dp, offsetX = (-134).dp, offsetY = (screenHeight * 0.7).dp, modifier = Modifier.scaleInOnShow(delay = 400).bobbing(amplitude = 14f, duration = 2600, delay = 300))
    }
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        HeaderBar(navController, showBackButton = true)
        Spacer(Modifier.height((screenHeight*0.1).dp))

        CreateCategoryTitleParagraph()
        Spacer(Modifier.height((screenHeight*0.05).dp))

        Text("What will the category be called?", color = OrangePrimary, fontSize = inputLabelFontSize, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(10.dp))
        CommonTextInput(
            uiState.name,
            onChange = viewModel::setName,
            placeholder = {
                Text("Type the category name...", color = Color.Gray)
            }
        )
        Spacer(Modifier.height(30.dp))

        Text("Some description right there!", color = OrangePrimary, fontSize = inputLabelFontSize, fontWeight = FontWeight.Bold)
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
            modifier = Modifier.fillMaxWidth().padding(vertical = 30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CommonButton(label = "Save", textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold), icon = {
                Icon(
                    painterResource(R.drawable.icon_angle_right),
                    "",
                    modifier = Modifier.width(30.dp).aspectRatio(1f),
                    tint = Color.White
                )
            }, onClick = {
                    try {
                        if (viewModel.save()) {
                            navController?.navigate("home")
                            Toast.makeText(context, "You have just added '${uiState.name}' category!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Please fill all of fields above!", Toast.LENGTH_SHORT).show()
                        }
                    } catch (exception: Exception) {
                        Toast.makeText(context, "There are some errors occurred!", Toast.LENGTH_SHORT).show()
                    }
            })
        }
    }
}