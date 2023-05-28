package com.example.foodomer.ui.components.createfood

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import coil.compose.AsyncImage
import com.example.foodomer.R
import com.example.foodomer.ui.theme.OrangePrimary

@Preview
@Composable
fun ImageSelector(
    modifier: Modifier = Modifier,
    label: String = "Choose the image",
    width: Dp = 250.dp,
    uri: Uri? = null,
    onSelected: (Uri?) -> Unit = {},
) {
    val singlePhotoPickerLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = onSelected
        )
    val innerColor = OrangePrimary.copy(alpha = 0.8f)

    Row(
        modifier =
            modifier
                .width(width)
                .aspectRatio(1f)
                .clip(CircleShape)
                .background(Color.White)
                .clickable {
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (uri == null) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painterResource(R.drawable.icon_picture),
                    "",
                    tint = innerColor,
                    modifier = Modifier.width(0.4*width)
                )
                Spacer(Modifier.height(10.dp))
                Text(label, color = innerColor, fontWeight = FontWeight.Bold, fontSize = (0.08*width.value).sp)
            }

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
