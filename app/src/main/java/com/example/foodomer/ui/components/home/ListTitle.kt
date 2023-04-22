package com.example.foodomer.ui.components.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodomer.R
import com.example.foodomer.ui.theme.OrangePrimary

@Composable
fun ListTitle(
    label: String,
    iconPainter: Painter = painterResource(R.drawable.icon_angle_right),
    onClick: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        modifier =
        Modifier
            .padding(vertical = 10.dp)
            .clickable { onClick() }
            .semantics { Role.Button },
    ) {
        Text(label, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = OrangePrimary)
        Icon(
            iconPainter,
            "",
            tint = OrangePrimary,
            modifier = Modifier.width(20.dp).aspectRatio(1f)
        )
    }
}