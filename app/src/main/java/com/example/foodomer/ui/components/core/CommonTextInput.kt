package com.example.foodomer.ui.components.core

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodomer.ui.theme.OrangePrimary

@Composable
fun CommonTextInput(
    value: String,
    onChange: (text: String) -> Unit,
    placeholder: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    textStyle: TextStyle = LocalTextStyle.current,
) {
    TextField(
        value,
        onValueChange = onChange,
        placeholder = placeholder,
        enabled = enabled,
        singleLine = singleLine,
        maxLines = maxLines,
        textStyle = textStyle.merge(TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            textColor = OrangePrimary,
            cursorColor = OrangePrimary
        ),
        shape= RoundedCornerShape(10.dp),
        modifier =
            Modifier
                .fillMaxWidth()
                .border(2.dp, OrangePrimary, RoundedCornerShape(10.dp))
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTextField() {
    var value by remember { mutableStateOf("dnf,m") }
    CommonTextInput(
        value,
        onChange = {
            value = it
        },
        placeholder = { Text("Type your password") }
    )
}
