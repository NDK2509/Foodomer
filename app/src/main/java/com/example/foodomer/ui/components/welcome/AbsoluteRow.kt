package com.example.foodomer.ui.components.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AbsoluteRow(
    modifier: Modifier = Modifier,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    content: @Composable () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth().offset(offsetX, offsetY),
        horizontalArrangement
    ) {
        content()
    }
}