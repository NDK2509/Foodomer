package com.example.foodomer.ui.components.core

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
        enter = fadeIn(animationSpec = tween(300, easing = EaseInOutCubic)),
        exit = fadeOut(animationSpec = tween(250, easing = EaseInOutCubic))
    ) {
        Box(
            modifier = Modifier
                .width(screenWidth.dp)
                .height(screenHeight.dp)
                .background(Color.Black.copy(alpha = 0.4f))
                .zIndex(9999f)
                .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null) {
                    onDismiss()
                },
            contentAlignment = Alignment.Center
        ) {
            var isDialogVisible by remember { mutableStateOf(false) }
            LaunchedEffect(isOpen) {
                isDialogVisible = isOpen
            }

            AnimatedVisibility(
                visible = isDialogVisible,
                enter = scaleIn(
                    initialScale = 0.8f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMediumLow
                    )
                ) + fadeIn(animationSpec = tween(200, easing = EaseInOutCubic)),
                exit = scaleOut(
                    targetScale = 0.8f,
                    animationSpec = tween(150, easing = EaseInCubic)
                ) + fadeOut(animationSpec = tween(150))
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth(0.85f)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .border(2.dp, OrangePrimary, RoundedCornerShape(16.dp))
                        .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null) {}
                        .padding(24.dp),
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
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                        ) {
                            dismissText()
                        }
                        Button(
                            onClick = onConfirm,
                            shape = RoundedCornerShape(10.dp),
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