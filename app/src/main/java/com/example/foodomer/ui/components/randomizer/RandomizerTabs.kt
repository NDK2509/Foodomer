package com.example.foodomer.ui.components.randomizer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.foodomer.ui.theme.BlackPrimary
import com.example.foodomer.ui.theme.DEFAULT_PADDING
import com.example.foodomer.ui.theme.OrangePrimary
import com.example.foodomer.utils.getScreenWidth
import com.example.foodomer.utils.slideByOffsetX

@Composable
fun RandomizerTabIndicator(
    width: Dp,
    offsetX: Dp = 0.dp
) {
    Row(
        Modifier
            .fillMaxHeight()
            .width(width)
            .zIndex(1f)
            .slideByOffsetX(offsetX, duration = 300)
            .clip(CircleShape)
            .background(OrangePrimary)
    ) {}
}

@Preview(showBackground = true)
@Composable
fun RandomizerTab(
    onClickFreeStyle: () -> Unit = {},
    onClickCategoryStyle: () -> Unit = {}
) {
    val indicatorWidth = getScreenWidth() - 2 * DEFAULT_PADDING.value
    var indicatorOffsetX by remember { mutableStateOf(0.dp) }

    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(40.dp)
                .clip(CircleShape)
                .background(BlackPrimary)
                .zIndex(0f)
    ) {
        RandomizerTabIndicator(
            width = (indicatorWidth / 2 + 20 / 2f).dp,
            offsetX = indicatorOffsetX
        )
        Row(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10f.dp)
                    .zIndex(2f)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                modifier =
                    Modifier
                        .weight(1f)
                        .clickable(
                            interactionSource = MutableInteractionSource(), indication = null
                        ) {
                            indicatorOffsetX = 0.dp
                            onClickFreeStyle()
                        },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Free style", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
            }
            Column(
                modifier =
                    Modifier
                        .weight(1f)
                        .clickable(
                            interactionSource = MutableInteractionSource(), indication = null
                        ) {
                           indicatorOffsetX =  (indicatorWidth / 2  - 20/ 2 + 1).dp
                            onClickCategoryStyle()
                        },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Category", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
            }
        }
    }
}