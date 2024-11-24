package com.example.foodomer.ui.components.qrcodesharing

import android.graphics.Bitmap
import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.zxing.common.BitMatrix

@Composable
fun QRCodeSquare(bitMatrix: BitMatrix) {
    val width = bitMatrix.width
    val height = bitMatrix.height
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
    for (x in 0 until width) {
        for (y in 0 until height) {
            bitmap.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5F)
            .padding(vertical = 22.dp),
    ) {
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentScale = ContentScale.Fit,
            contentDescription = "QR Code",
            modifier = Modifier
                .fillMaxSize(1f)
        )
    }
}