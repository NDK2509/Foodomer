package com.example.foodomer.ui.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodomer.R
import com.example.foodomer.database.entities.Category
import com.example.foodomer.ui.theme.OrangePrimary
import kotlin.random.Random

private val categoryImages = intArrayOf(R.drawable.category_illu_1, R.drawable.category_illu_2, R.drawable.category_illu_3, R.drawable.category_illu_4, R.drawable.category_illu_5)
private val arrLen = categoryImages.size
private fun randomCategoryIllustration(): Int {
    return categoryImages[Random.nextInt(arrLen)]
}

@Composable
fun CategoryItem(
    category: Category,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        modifier =
            modifier
                .clickable {
                    onClick()
                }
                .width(250.dp)
                .aspectRatio(1.8f)
                .clip(RoundedCornerShape(20.dp))
                .background(OrangePrimary)
                .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(randomCategoryIllustration()),
            "",
            modifier = Modifier.fillMaxWidth(0.5f)
        )

        Column {
            Text(category.name, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(2.dp))
            Text(category.description, color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold, maxLines = 3, overflow = TextOverflow.Ellipsis)
        }
    }
}

@Preview()
@Composable
fun PreviewCategoryItem() {
    CategoryItem(
        Category(1, "Breakfast", "The most important meal of the day!")
    )
}
