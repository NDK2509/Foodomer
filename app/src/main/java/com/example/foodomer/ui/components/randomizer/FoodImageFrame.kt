package com.example.foodomer.ui.components.randomizer

import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.foodomer.ui.theme.OrangePrimary

@Preview
@Composable
fun FoodImageFrame(
    uri: String? = ""
) {
    AsyncImage(
        Uri.parse(uri),
        "",
        contentScale = ContentScale.Crop,
        modifier =
            Modifier
                .fillMaxWidth()
                .aspectRatio(1.25f)
                .clip(RoundedCornerShape(20.dp))
                .border(3.dp, OrangePrimary, RoundedCornerShape(20.dp))
    )
}