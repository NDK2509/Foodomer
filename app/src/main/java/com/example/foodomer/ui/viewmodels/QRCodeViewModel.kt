package com.example.foodomer.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel

class QRCodeViewModel() : ViewModel() {
    fun generateQRCode(data: String): BitMatrix {
        val hints = hashMapOf<EncodeHintType, Any>().apply {
            put(EncodeHintType.CHARACTER_SET, "UTF-8")
            put(EncodeHintType.MARGIN, 1) // Optional, to reduce the margin
            put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H) // High error correction
        }
        val writer = QRCodeWriter()
        return writer.encode(data, BarcodeFormat.QR_CODE, 200, 200, hints)
    }
}