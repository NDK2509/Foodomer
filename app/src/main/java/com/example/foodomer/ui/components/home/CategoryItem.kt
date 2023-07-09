package com.example.foodomer.ui.components.home

import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodomer.R
import com.example.foodomer.database.entities.Category
import com.example.foodomer.ui.components.core.DeleteButton
import com.example.foodomer.ui.theme.OrangePrimary
import com.example.foodomer.utils.shadow
import kotlin.random.Random

private val categoryImages = intArrayOf(R.drawable.category_illu_1, R.drawable.category_illu_2, R.drawable.category_illu_3, R.drawable.category_illu_4, R.drawable.category_illu_5)
private val arrLen = categoryImages.size
private fun randomCategoryIllustration(): Int {
    return categoryImages[Random.nextInt(arrLen)]
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryItem(
    category: Category,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onDelete: () -> Unit = {}
) {
    var isLongPressed by remember { mutableStateOf(false) }
    val illustrationImg = randomCategoryIllustration()
    Row(
        modifier =
        modifier
            .width(250.dp)
            .aspectRatio(1.8f)
            .shadow(
                offsetX = 4.dp,
                offsetY = 4.dp,
                blurRadius = 3.dp,
                borderRadius = with(LocalDensity.current) { 20.dp.toPx() }
            )
            .clip(RoundedCornerShape(20.dp))
            .background(OrangePrimary)
            .combinedClickable(
                onClick = {
                    if (isLongPressed) {
                        isLongPressed = false
                    } else {
                        onClick()
                    }
                },
                onLongClick = {
                    isLongPressed = true
                }
            )
            .padding(20.dp),
    ) {
        AnimatedVisibility(
            isLongPressed,
            enter = slideInHorizontally(),
            exit = slideOutHorizontally() + fadeOut()
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DeleteButton(onDelete)
            }
        }

        AnimatedVisibility(
            !isLongPressed,
            enter = slideInHorizontally() + fadeIn(),
            exit = slideOutHorizontally()
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(illustrationImg),
                    "",
                    modifier = Modifier.fillMaxWidth(0.5f).aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )

                Column {
                    Text(
                        category.name,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(Modifier.height(2.dp))
                    Text(
                        category.description,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCategoryItem() {
    CategoryItem(
        Category(1, "Breakfast", "The most important meal of the day!")
    )
}
