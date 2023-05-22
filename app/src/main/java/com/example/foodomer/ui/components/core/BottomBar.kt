package com.example.foodomer.ui.components.core

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.foodomer.R
import com.example.foodomer.ui.theme.BlackPrimary
import com.example.foodomer.ui.theme.OrangePrimary
import com.example.foodomer.utils.getScreenWidth
import com.example.foodomer.utils.slideByOffsetX

data class BottomBarItemProps(val icon: Painter, val destination: String)

val ICON_FONT_ACTIVE = 40 .dp

@Composable
fun BottomBarIndicator(
    width: Dp,
    offsetX: Dp,
) {

    Column(
        modifier = Modifier.padding(vertical = 8.dp).width(width).zIndex(1f).slideByOffsetX(offsetX, duration = 200)

    ) {
        Column(
            modifier = Modifier.fillMaxSize().clip(CircleShape).background(OrangePrimary)
        ) {}
    }
}

@Composable
fun BottomBarItem(
    props: BottomBarItemProps,
    onClick: () -> Unit = {},
    isActive: Boolean = true,
) {

    Column(modifier = Modifier.clickable(
        interactionSource = MutableInteractionSource(), indication = null, enabled = !isActive
    ) {
        onClick()
    }) {
        Icon(
            painter = props.icon, "", tint = Color.White, modifier = Modifier.width(ICON_FONT_ACTIVE).aspectRatio(1f)
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BottomBar(
    navController: NavController,
    items: List<BottomBarItemProps>,
    isShown: Boolean = true
) {
    val numOfItems = items.size
    val bottomBarWidth = (getScreenWidth() * 0.9).toInt() + 1
    val indicatorWidth = (bottomBarWidth / (numOfItems + 0.5)) + 1

    var currentIndex by remember { mutableStateOf(0) }
    var indicatorPosition by remember { mutableStateOf(8.dp) }

    AnimatedVisibility(
        isShown,
        enter = scaleIn(),
        exit = scaleOut()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier =
                Modifier
                    .fillMaxWidth(0.9f)
                    .height(64.dp)
                    .padding(vertical = 4.dp)
                    .clip(CircleShape)
                    .background(BlackPrimary)
            ) {
                BottomBarIndicator(
                    width = indicatorWidth.dp, offsetX = indicatorPosition
                )

                val iconsPadding = indicatorWidth / 2 + 8 - ICON_SIZE.value / 2
                val indicatorPositionUnit = ((bottomBarWidth - 2 * iconsPadding - ICON_SIZE.value) / (numOfItems - 1)).dp

                Row(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(horizontal = iconsPadding.dp)
                        .zIndex(2f),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items.forEachIndexed { idx, it ->
                        BottomBarItem(
                            props = it,
                            onClick = {
                                if (idx > currentIndex) {
                                    indicatorPosition += (indicatorPositionUnit) * (idx - currentIndex)
                                } else if (idx < currentIndex) {
                                    indicatorPosition -= (indicatorPositionUnit) * (currentIndex - idx)
                                }

                                currentIndex = idx
                                navController.navigate(it.destination)
                            },
                            isActive = idx == currentIndex
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun BottomBarPreview() {
    BottomBar(
        navController = rememberNavController(), items = listOf(
            BottomBarItemProps(
                icon = painterResource(R.drawable.icon_home), destination = "home"
            ),
            BottomBarItemProps(
                icon = painterResource(R.drawable.icon_cube), destination = "home"
            ),
            BottomBarItemProps(
                icon = painterResource(R.drawable.icon_clock), destination = "home"
            ),
        )
    )
}