package com.example.foodomer.ui.components.core

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.foodomer.R
import com.example.foodomer.ui.theme.OrangePrimary
import com.example.foodomer.utils.getScreenHeight
import com.example.foodomer.utils.getScreenWidth

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CommonDialog(
    isOpen: Boolean,
    modifier: Modifier = Modifier,
    onConfirm: () -> Unit = {},
    onDismiss: () -> Unit = {},
    dismissText: @Composable () -> Unit = {
        Text(
            "Noooooooo!",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = OrangePrimary
        )
    },
    confirmText: @Composable () -> Unit = {
        Text(
            "Yesssssss!",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    },
    content: @Composable () -> Unit = {}
) {
    val screenWidth = getScreenWidth()
    val screenHeight = getScreenHeight()

    AnimatedVisibility(
        isOpen,
        enter = scaleIn(),
        exit = scaleOut()
    ) {
        Surface(
            modifier =
            Modifier
                .width(screenWidth.dp)
                .height(screenHeight.dp)
                .zIndex(9999f)
                .clickable(interactionSource = MutableInteractionSource(), indication = null) {
                    onDismiss()
                },
            color = OrangePrimary.copy(alpha = 0f)
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier =
                    modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(7.dp))
                        .background(Color.White)
                        .border(2.dp, OrangePrimary, RoundedCornerShape(7.dp))
                        .padding(20.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.weight(3f)
                    ) {
                        content()
                    }
                    Row(
                        modifier = Modifier.weight(1f).fillMaxWidth().padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = onDismiss,
                            border = BorderStroke(2.dp, OrangePrimary),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                        ) {
                            dismissText()
                        }
                        Button(
                            onClick = onConfirm,
                            colors = ButtonDefaults.buttonColors(backgroundColor = OrangePrimary)
                        ) {
                            confirmText()
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewCommonDialog() {
    CommonDialog(true) {
        Row(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.8f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(R.drawable.fruit_salad),
                "",
                modifier =
                Modifier
                    .width(100.dp)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .border(2.dp, OrangePrimary, CircleShape)
            )
            Column {
                Text("Do you want to delete", color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(
                    "Cu Ke cute :v?",
                    color = OrangePrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}