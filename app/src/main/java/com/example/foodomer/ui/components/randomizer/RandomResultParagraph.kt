package com.example.foodomer.ui.components.randomizer

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodomer.ui.theme.OrangePrimary

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun RandomResultParagraph(
    result: String? = ""
) {
    Column(
        modifier = Modifier.fillMaxHeight(0.6f).fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val hasResult = !result.isNullOrEmpty()

        AnimatedContent(
            targetState = hasResult,
            transitionSpec = {
                (fadeIn(animationSpec = tween(300, easing = EaseInOutCubic)) +
                        scaleIn(
                            initialScale = 0.8f,
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioMediumBouncy,
                                stiffness = Spring.StiffnessMediumLow
                            )
                        ))
                    .togetherWith(fadeOut(animationSpec = tween(150, easing = EaseInOutCubic)))
            },
            label = "resultTransition"
        ) { targetHasResult ->
            if (!targetHasResult) {
                Text(
                    "Let's Foodomer!!!!!!!",
                    color = OrangePrimary,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Congratulations!!!",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(3.dp))
                    Text(
                        "Enjoy your meal with ",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(3.dp))
                    Text(
                        result!!.uppercase(),
                        color = OrangePrimary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}