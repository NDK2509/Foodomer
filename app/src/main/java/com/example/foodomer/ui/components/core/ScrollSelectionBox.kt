package com.example.foodomer.ui.components.core

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodomer.ui.theme.OrangePrimary
import com.example.foodomer.utils.getScreenWidth

@Composable
fun <V> ScrollSelectionBox(
    options: List<Option<V>>,
    onValueChange: (V) -> Unit,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    activeColor: Color = OrangePrimary,
    inactiveColor: Color = Color.Black,
    orientation: Orientation = Orientation.Vertical,
) {
    var currentIndex by remember { mutableStateOf(0) }
    var chosenValue by remember { mutableStateOf(options[currentIndex].value) }
    val scrollState = rememberScrollState()
    val screenWidth = getScreenWidth()
    val verticalOptionPx = with(LocalDensity.current) { 20.dp.toPx().toInt() }
    val horizontalOptionPx = with(LocalDensity.current) { (screenWidth / 5).dp.toPx().toInt() }
    val gapBetween2Options = if (orientation == Orientation.Vertical) verticalOptionPx else horizontalOptionPx

    onValueChange(chosenValue)

    LaunchedEffect(scrollState.value) {
        val nextIndex = scrollState.value / gapBetween2Options
        if ( nextIndex != currentIndex && nextIndex < options.size) {
            currentIndex = nextIndex
            chosenValue = options[nextIndex].value
            onValueChange(chosenValue)
        }
    }
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.Filled.ArrowDropDown,
            "",
            tint = OrangePrimary,
            modifier = Modifier.size(40.dp)
        )

        when (orientation) {
            Orientation.Vertical -> {
                Column(
                    modifier =
                        Modifier
                            .height(80.dp)
                            .verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    options.mapIndexed { index, it ->
                        val isActive = currentIndex == index
                        Text(
                            it.name,
                            color = if (isActive) activeColor else inactiveColor,
                            fontSize = (if (isActive) 20 else 15).sp,
                            fontWeight = FontWeight.Bold,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            modifier = textModifier
                        )
                        Spacer(Modifier.height(5.dp))
                    }

                    Spacer(Modifier.height(50.dp))
                }
            }
            Orientation.Horizontal -> {
                val textWidth = (screenWidth / 5).dp
                val paddingStart = (screenWidth / 2).dp - textWidth / 2

                Row(
                    modifier =
                        Modifier
                            .padding(start = paddingStart)
                            .horizontalScroll(scrollState),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    options.mapIndexed { index, it ->
                        val isActive = currentIndex == index
                        Text(
                            it.name,
                            color = if (isActive) activeColor else inactiveColor,
                            fontSize = (if (isActive) 20 else 15).sp,
                            fontWeight = FontWeight.Bold,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            textAlign = TextAlign.Center,
                            modifier = textModifier.width(textWidth)
                        )
                        Spacer(Modifier.width(10.dp))
                    }
                    Spacer(Modifier.width(textWidth * 2))
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewScrollSelectionBox() {
    ScrollSelectionBox(
        options = listOf(
            Option("B", 1),
            Option("Lunch", 2),
            Option("Dinner", 3),
            Option("Breakfast", 3),
            Option("Breakfast", 3),
        ),
        onValueChange = {},
        orientation = Orientation.Horizontal
    )
}
