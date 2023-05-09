package com.example.foodomer.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import androidx.core.net.toUri
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

fun saveUriIntoExternalStorage(context: Context, uri: Uri): String {
    val bitmap = context.contentResolver.openInputStream(uri).use { data ->
        BitmapFactory.decodeStream(data)
    }
    val newFile = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "foodomer-${Date().time}.png" )
    newFile.createNewFile()

    try {
        FileOutputStream(newFile).use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
            out.flush()
            out.close()
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return newFile.toUri().toString()
}