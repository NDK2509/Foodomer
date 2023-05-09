package com.example.foodomer.ui.components.core

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.unit.dp
import com.example.foodomer.ui.theme.OrangePrimary

data class Option<V>(
    val name: String,
    val value: V
)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun <V>CommonSelectionBox(
    options: List<Option<V>>,
    onValueChange: (V) -> Unit,
    expanded: Boolean = false,
    placeholder: @Composable () -> Unit = {}
) {
    var _expanded by remember { mutableStateOf(expanded) }
    var selectedOption by remember { mutableStateOf(options[0]) }
    onValueChange(selectedOption.value)

    ExposedDropdownMenuBox(
        expanded = _expanded,
        onExpandedChange = {
            _expanded = !_expanded
        },
        modifier =
            Modifier
                .fillMaxWidth()
                .border(2.dp, OrangePrimary, RoundedCornerShape(10.dp))

    ) {
        OutlinedTextField(
            readOnly = true,
            placeholder = placeholder,
            value = selectedOption.name,
            onValueChange = {},
            trailingIcon = {
                IconButton(
                    onClick = {},
                    modifier = Modifier.clearAndSetSemantics { }
                ) {
                    Icon(
                        Icons.Filled.ArrowDropDown,
                        "Trailing icon for exposed dropdown menu",
                        Modifier.rotate(
                            if (_expanded)
                                180f
                            else
                                360f
                        ),
                        tint = OrangePrimary
                    )
                }
            },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    textColor = OrangePrimary,
                    cursorColor = OrangePrimary
                ),
        )
        ExposedDropdownMenu(
            expanded = _expanded,
            onDismissRequest = {
                _expanded = false
            }
        ) {
            options.map {
                DropdownMenuItem(
                    onClick = {
                        _expanded = false
                        selectedOption = it
                        onValueChange(it.value)
                    }
                ) {
                    Text(text = it.name)
                }
            }
        }
    }
}