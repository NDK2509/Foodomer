package com.example.foodomer.ui.components.createfood

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodomer.R
import com.example.foodomer.utils.blink

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun BlinkedWarning() {
    Image(
        painterResource(R.drawable.warning_illustration),
        "",
        modifier = Modifier.blink()
    )
}