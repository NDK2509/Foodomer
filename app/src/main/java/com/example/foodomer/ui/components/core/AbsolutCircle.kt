package com.example.foodomer.ui.components.core

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times

@Composable
fun AbsoluteCircle(
    radius: Dp,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    color: Color = Color.Black
) {
    Surface(
        modifier =
            Modifier
                .offset(offsetX, offsetY)
                .width(2 * radius)
                .aspectRatio(1f)
                .clip(CircleShape),
        color = color
    ) {}
}