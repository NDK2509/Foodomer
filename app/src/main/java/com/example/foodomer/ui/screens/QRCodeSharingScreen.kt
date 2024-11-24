package com.example.foodomer.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.foodomer.ui.AppViewModelProvider
import com.example.foodomer.ui.components.core.HeaderBar
import com.example.foodomer.ui.components.qrcodesharing.QRCodeSquare
import com.example.foodomer.ui.theme.OrangePrimary
import com.example.foodomer.ui.viewmodels.QRCodeViewModel


@Composable
fun QRCodeSharingScreen(
    navController: NavController? = null,
    data: String,
    viewModel: QRCodeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val qrCode = viewModel.generateQRCode(data)
    Column {
        HeaderBar(
            navController,
            showBackButton = true,
            rightItems = {}
        )
        Spacer(Modifier.height(20.dp))
        Text(
            "Scan this QR code for adding the food!",
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = OrangePrimary,
            modifier = Modifier.fillMaxWidth()
        )
        QRCodeSquare(qrCode)
    }
}