package com.example.foodomer.ui.components.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.foodomer.R
import com.example.foodomer.ui.theme.OrangePrimary

data class Option (
    val label: String,
    val action: () -> Unit
)

@Composable
fun CreationOption(option: Option) {
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            option.action()
        }
    ) {
        Icon(
            painterResource(R.drawable.icon_arrow_forward),
            "",
            tint = OrangePrimary,
            modifier = Modifier.width(25.dp)
        )
        Text(option.label, color = OrangePrimary, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun CreationOptionsOverLay(
    options: List<Option>,
    modifier: Modifier = Modifier,
    isVisible: Boolean = false
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column(
            modifier =
            modifier
                .fillMaxSize()
                .background(Color(0x99FFFFFF))
                .offset(x = 0.dp, y = 0.dp)
                .zIndex(1f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            Row(
                modifier = Modifier.padding(end = 50.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    options.map {
                        CreationOption(it)
                    }
                }
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.25f))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewOverlay() {
    CreationOptionsOverLay(
        listOf(
            Option("New Food") {},
            Option("New Category") {}
        ),
        isVisible = true
    )
}