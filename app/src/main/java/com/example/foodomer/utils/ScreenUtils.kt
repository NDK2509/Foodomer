package com.example.foodomer.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun getScreenWidth(): Int {
    return LocalConfiguration.current.screenWidthDp
}
@Composable
fun getScreenHeight(): Int {
    return LocalConfiguration.current.screenHeightDp
}