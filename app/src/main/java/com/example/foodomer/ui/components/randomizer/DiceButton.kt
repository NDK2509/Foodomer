package com.example.foodomer.ui.components.randomizer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.foodomer.R
import com.example.foodomer.ui.components.home.shadow
import com.example.foodomer.ui.theme.OrangePrimary

@Preview
@Composable
fun DiceButton(
    modifier: Modifier = Modifier,
    width: Dp = 100.dp,
    onClick: () -> Unit = {}
) {
    Row (
        modifier =
            modifier
                .width(width)
                .aspectRatio(1f)
                .padding(2.dp)
                .shadow(
                    offsetX = 2.dp,
                    offsetY = 2.dp,
                    blurRadius = 3.dp,
                    borderRadius = with(LocalDensity.current) { ( width / 2 ).toPx()}
                )
                .clip(CircleShape)
                .background(OrangePrimary)
                .clickable {
                    onClick()
                }
    ) {
        Image(
            painterResource(R.drawable.icon_dice),"",
            modifier = Modifier.fillMaxWidth().aspectRatio(1f)
        )
    }
}