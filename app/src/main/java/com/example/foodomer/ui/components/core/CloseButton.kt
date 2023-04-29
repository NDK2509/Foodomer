package com.example.foodomer.ui.components.core

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.foodomer.ui.theme.OrangePrimary

@Composable
fun CloseButton(
    onClick: () -> Unit
) {
    Box(
        modifier =
        Modifier
            .width(50.dp)
            .aspectRatio(1f)
            .clip(CircleShape)
            .background(OrangePrimary, CircleShape)
    ) {
        Icon(
            Icons.Rounded.Add,
            "",
            tint = Color.White,
            modifier =
            Modifier
                .width(50.dp).aspectRatio(1f)
                .semantics { role = Role.Button }
                .rotate(45f)
                .clickable {
                    onClick()
                }
        )
    }
}