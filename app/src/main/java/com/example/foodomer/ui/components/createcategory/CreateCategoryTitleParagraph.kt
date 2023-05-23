package com.example.foodomer.ui.components.createcategory

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodomer.ui.theme.OrangePrimary

@Preview(showBackground = true)
@Composable
fun CreateCategoryTitleParagraph() {
    val titleFontSize = 26.sp
    val subTitleFontSize = 12.sp

    Column {
        Row {
            Text("Let's ", color = Color.Black, fontSize = titleFontSize, fontWeight = FontWeight.Bold)
            Text("add ", color = OrangePrimary, fontSize = titleFontSize, fontWeight = FontWeight.Bold)
            Text("a new ", color = Color.Black, fontSize = titleFontSize, fontWeight = FontWeight.Bold)
            Text("Category!", color = OrangePrimary, fontSize = titleFontSize, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(10.dp))
        Row(
            Modifier.padding(start = 40.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text("***", color = OrangePrimary, fontSize = subTitleFontSize, fontWeight = FontWeight.Bold)
            Column {
                Text("A category may be a meal or some groups of foods",
                    color = Color.Black,
                    fontSize = subTitleFontSize,
                    fontWeight = FontWeight.Bold
                )
                Text("that you want to randomize for meal.",
                    color = Color.Black,
                    fontSize = subTitleFontSize,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}