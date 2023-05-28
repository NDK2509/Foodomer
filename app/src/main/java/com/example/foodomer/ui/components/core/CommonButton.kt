package com.example.foodomer.ui.components.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodomer.R
import com.example.foodomer.ui.theme.OrangePrimary

@Composable
fun CommonButton(
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    onClick: () -> Unit = {},
    label: String = "",
    icon: @Composable () -> Unit = {},
    reversedColor: Boolean = false
) {

    Button(
        onClick,
        modifier = modifier.width(250.dp).padding(10.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(backgroundColor = if (reversedColor) Color.White else OrangePrimary)
    ) {
        Box(
            modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(label, modifier = textModifier.fillMaxWidth(), textAlign = TextAlign.Center, color = if (reversedColor) OrangePrimary else Color.White, style = textStyle)
            Box(
                modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                icon()
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    CommonButton(label = "Nguyen Dang Ky",
        icon = { Icon(painterResource(R.drawable.icon_arrow_forward), "", tint = Color.White) })
}