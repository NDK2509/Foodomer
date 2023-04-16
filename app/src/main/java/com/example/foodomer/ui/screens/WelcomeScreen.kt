package com.example.foodomer.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.foodomer.R
import com.example.foodomer.ui.components.core.CommonButton
import com.example.foodomer.ui.components.welcome.AbsoluteRow

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun WelcomeScreen(navController: NavController? = null) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxHeight(0.7f).padding(horizontal = 20.dp)
        ) {
            AbsoluteRow(
                offsetY = 20.dp
            ) {
                Image(
                    painterResource(R.drawable.noodle_1),
                    "",
                    modifier =
                    Modifier
                        .width(80.dp)
                        .aspectRatio(1f)
                        .rotate(-25.66f)
                )
            }
            AbsoluteRow(
                offsetY = 70.dp,
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    painterResource(R.drawable.instant_noodle_with_shrimp),
                    "",
                    modifier =
                    Modifier
                        .width(78.dp)
                        .aspectRatio(1f)
                        .rotate(27.64f)
                )
            }
            AbsoluteRow(
                offsetY = 130.dp,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painterResource(
                        R.drawable.ribs_dish
                    ),
                    "",
                    modifier =
                    Modifier
                        .width(150.dp)
                        .aspectRatio(2f)
                        .rotate(12f)
                )
            }

            AbsoluteRow(
                offsetY = 240.dp
            ) {
                Image(
                    painterResource(R.drawable.shrimp_salad),
                    "",
                    modifier =
                    Modifier
                        .width(90.dp)
                        .aspectRatio(2f)
                        .rotate(25f)
                )
            }
            AbsoluteRow(
                offsetY = 280.dp,
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    painterResource(R.drawable.dumpling),
                    "",
                    modifier =
                    Modifier
                        .width(90.dp)
                        .aspectRatio(1f)
                        .rotate(-30f)
                )
            }

            AbsoluteRow(
                offsetY = 250.dp,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painterResource(R.drawable.skeleton_app_icon),
                    "",
                    modifier =
                    Modifier
                        .width(150.dp)
                        .aspectRatio(1f)
                )
            }
            AbsoluteRow(
                offsetY = 360.dp
            ) {
                Image(
                    painterResource(R.drawable.rice),
                    "",
                    modifier =
                    Modifier
                        .width(120.dp)
                        .aspectRatio(1f)
                        .rotate(20f)
                )
            }
            AbsoluteRow(
                offsetY = 460.dp,
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    painterResource(R.drawable.fruit_salad),
                    "",
                    modifier =
                    Modifier
                        .width(140.dp)
                        .aspectRatio(2f)
                        .rotate(-8f)
                )
            }

        }
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "",
                modifier =
                Modifier
                    .width(300.dp)
                    .aspectRatio(3f)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            CommonButton(
                label = "Let's start!",
                icon = { Icon(painterResource(R.drawable.icon_arrow_forward), "", tint = Color.White) },
                textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight(600)),
                onClick = {
                    navController?.navigate("home")
                }
            )
        }
    }
}