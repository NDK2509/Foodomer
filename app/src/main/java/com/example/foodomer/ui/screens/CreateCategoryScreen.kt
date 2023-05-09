package com.example.foodomer.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
import com.example.foodomer.ui.components.core.CloseButton
import com.example.foodomer.ui.components.core.CommonButton
import com.example.foodomer.ui.components.core.CommonTextInput
import com.example.foodomer.ui.theme.DEFAULT_PADDING
import com.example.foodomer.ui.theme.OrangePrimary
import com.example.foodomer.ui.viewmodels.CreateCategoryViewModel
import com.example.foodomer.utils.capitalize

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CreateCategoryScreen(
    navController: NavController? = null,
    viewModel: CreateCategoryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(top = DEFAULT_PADDING),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(end = DEFAULT_PADDING),
            horizontalArrangement = Arrangement.End
        ) {
            CloseButton(onClick = {
                navController?.navigate("home")
            })
        }
        Image(
            painterResource(R.drawable.banner_2), "", modifier = Modifier.width(500.dp).aspectRatio(1f)
        )

        Text("Add a new Food Category", color = OrangePrimary, fontSize = 30.sp, fontWeight = FontWeight.Bold)

        Row(
            modifier = Modifier.fillMaxHeight(0.5f).padding(horizontal = DEFAULT_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CommonTextInput(name, onChange = { name = it }, placeholder = {
                Text("Type the category name...", color = Color.Gray)
            })
        }

        Row(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.6f), horizontalArrangement = Arrangement.Center
        ) {
            CommonButton(label = "Save", textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold), icon = {
                Icon(
                    painterResource(R.drawable.icon_angle_right),
                    "",
                    modifier = Modifier.width(30.dp).aspectRatio(1f),
                    tint = Color.White
                )
            }, onClick = {
                val formattedName = name.trim().capitalize()
                try {
                    viewModel.save(formattedName)
                    navController?.navigate("home")
                    Toast.makeText(context, "You have just added $formattedName category!", Toast.LENGTH_SHORT).show()
                } catch (exception: Exception) {
                    Toast.makeText(context, "There are some errors occurred!", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}