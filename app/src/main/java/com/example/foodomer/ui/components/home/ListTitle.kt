package com.example.foodomer.ui.components.home

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodomer.R
import com.example.foodomer.ui.theme.OrangePrimary

@Composable
fun AddButton(
    onClick: () -> Unit
) {
    Row(
        modifier =
            Modifier
                .clickable {
                    onClick()
                }
                .width(80.dp)
                .clip(RoundedCornerShape(5.dp))
                .border(2.dp, OrangePrimary, RoundedCornerShape(5.dp))
                .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Default.Add,
            "",
            tint = OrangePrimary,
            modifier = Modifier.height(20.dp).aspectRatio(1f)
        )
        Text("Add", color = OrangePrimary, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ListTitle(
    label: String,
    iconPainter: Painter = painterResource(R.drawable.icon_angle_right),
    onClick: () -> Unit = {},
    onAddClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
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
            Text(label, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = Color.Black)
            Icon(
                iconPainter,
                "",
                tint = Color.Black,
                modifier = Modifier.width(20.dp).aspectRatio(1f)
            )
        }
        AddButton(onAddClick)
    }
}

@Preview
@Composable
fun ReviewListTitle() {
    ListTitle("Category")
}