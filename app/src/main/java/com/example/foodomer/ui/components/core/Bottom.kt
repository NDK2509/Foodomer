package com.example.foodomer.ui.components.core

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodomer.R
import com.example.foodomer.ui.theme.OrangePrimary
import com.example.foodomer.utils.flipInfinite
import com.example.foodomer.utils.rotateWithAnimation

@Composable
fun FoodomerButton(
    onClick: () -> Unit
) {
    Box(
        modifier =
            Modifier
                .width(100.dp)
                .aspectRatio(1f)
                .background(OrangePrimary, CircleShape)
                .clickable {
                    onClick()
                }
    ) {
        Image(
            painterResource(R.drawable.lid_and_dish_close),
            "",
            modifier =
                Modifier
                    .width(100.dp)
                    .aspectRatio(1f)
                    .flipInfinite(duration = 1500)
        )
    }
}

@Composable
fun AddNewFoodButton(
    isOpen: Boolean = false, onClickToOpen: () -> Unit, onClickToClose: () -> Unit
) {
    var _isOpen by remember { mutableStateOf(isOpen) }

    Box(
        modifier =
            Modifier
                .width(50.dp)
                .aspectRatio(1f)
                .clip(CircleShape)
                .background(OrangePrimary, CircleShape)
    ) {
        Icon(Icons.Rounded.Add,
            "",
            tint = Color.White,
            modifier =
                Modifier
                    .rotateWithAnimation(if (_isOpen) 45f else 0f, 300)
                    .width(50.dp).aspectRatio(1f)
                    .semantics { role = Role.Button }
                    .clickable {
                        if (_isOpen) onClickToClose() else onClickToOpen()
                        _isOpen = !_isOpen
                    })
    }
}

@Preview
@Composable
fun PreviewBottom() {
    Column {
        FoodomerButton { }
        AddNewFoodButton(onClickToClose = {}, onClickToOpen = {})
    }
}