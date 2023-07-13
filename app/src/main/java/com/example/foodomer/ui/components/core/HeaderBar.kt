package com.example.foodomer.ui.components.core

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodomer.R
import com.example.foodomer.navigation.Destinations
import com.example.foodomer.ui.theme.OrangePrimary

val ICON_SIZE = 40.dp

@Composable
fun HeaderIcon(
    resourceId: Int,
    iconColor: Color = OrangePrimary,
    onClick: () -> Unit = {}
) {
    Icon(
        painterResource(resourceId),
        "",
        tint = iconColor,
        modifier =
        Modifier
            .width(ICON_SIZE)
            .clickable {
                onClick()
            }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HeaderBar(
    navController: NavController? = null,
    showBackButton: Boolean = false,
    iconColor: Color = OrangePrimary,
    leftItems: @Composable () -> Unit = {
        HeaderIcon(
            resourceId = R.drawable.icon_settings,
            iconColor = iconColor,
            onClick = { navController?.navigate(Destinations.Settings.route) }
        )
    },
    title: @Composable () -> Unit = {},
    rightItems: @Composable () -> Unit = {
        HeaderIcon(
            resourceId = R.drawable.icon_search,
            iconColor = iconColor,
            onClick = { navController?.navigate(Destinations.Search.route) }
        )
    },
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (showBackButton) {
            HeaderIcon(
                resourceId = R.drawable.icon_angle_left,
                iconColor = iconColor,
                onClick = { navController?.popBackStack() }
            )
        } else leftItems()
        title()
        rightItems()
    }
}