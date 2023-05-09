package com.example.foodomer.ui.components.createfood

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.foodomer.R
import com.example.foodomer.ui.theme.OrangePrimary

@Composable
fun ImageSelector(
    uri: Uri? = null,
    onSelected: (Uri?) -> Unit,
) {
    val singlePhotoPickerLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = onSelected
        )

    Row(
        modifier =
            Modifier
                .width(250.dp)
                .aspectRatio(1f).clip(CircleShape)
                .border(3.dp, OrangePrimary, CircleShape)
                .background(Color(0x18FB7833), CircleShape)
                .clickable {
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (uri == null) {
            Icon(
                painterResource(R.drawable.icon_camera_outline), "", tint = OrangePrimary
            )
        } else {
            AsyncImage(
                model = uri,
                contentDescription = "",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
    }
}
