package com.example.foodomer.ui.components.core

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodomer.R
import com.example.foodomer.ui.theme.OrangePrimary

@Composable
fun MenuButton(onClick: () -> Unit = {}) {
    Box(
        modifier =
        Modifier
            .semantics { role = Role.Button }
            .width(40.dp)
            .aspectRatio(1f)
            .background(OrangePrimary, RoundedCornerShape(5.dp))
            .clickable {
                onClick()
            },

        ) {
        Icon(
            painter = painterResource(R.drawable.icon_menu),
            "",
            tint = Color.Black,
            modifier =
            Modifier
                .width(40.dp)
                .aspectRatio(1f)
                .offset(x = (-10).dp)
        )
    }
}

@Composable
fun SearchButton(
    onClick: () -> Unit = {}
) {
    Box(
        modifier =
        Modifier
            .semantics { role = Role.Button }
            .width(40.dp)
            .aspectRatio(1f)
            .background(OrangePrimary, CircleShape)
            .clickable {
                onClick()
            },

        ) {
        Icon(
            painter = painterResource(R.drawable.icon_search_outline),
            "",
            tint = Color.Black,
            modifier =
            Modifier
                .width(55.dp)
                .aspectRatio(1f)
                .offset(x = (-15).dp, y = (-5).dp)
        )
    }
}

@Composable
fun Header(
    onClickMenu: () -> Unit,
    onCLickSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        MenuButton(onClickMenu)
        SearchButton(onCLickSearch)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMenu() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(50.dp)
    ) {
        Header(onClickMenu = {}, onCLickSearch = {})
    }
}