package com.example.foodomer.ui.components.randomhistory

import android.net.Uri
import coil.compose.AsyncImage
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextOverflow
import com.example.foodomer.database.entities.Food
import com.example.foodomer.database.entities.HistoryWithFood
import com.example.foodomer.ui.theme.OrangePrimary
import com.example.foodomer.utils.thousandGroupByDot
import com.example.foodomer.utils.timeOnlyFormatter
import java.util.*

@Composable
fun HistoryBlock(
    date: String,
    historyList: List<HistoryWithFood>,
    modifier: Modifier = Modifier
) {
    val isDividerShowed = historyList.size != 1
    Column(
        modifier.fillMaxWidth()
    ) {
        Text(date, color = Color.Black, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(10.dp))
        Column(
            Modifier.padding(start = 10.dp)
        ) {
            historyList.map { HistoryElement(it, isDividerShowed) }
        }
    }
}

@Composable
fun HistoryElement(
    history: HistoryWithFood,
    showDivider: Boolean = false
) {
    val time = timeOnlyFormatter.format(Date(history.createdAt))
    val style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 12.sp)
    Row(
        Modifier.fillMaxWidth().height(70.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Text(time, style = style, modifier = Modifier.width(60.dp))
            HistoryDivider(showDivider)
            Spacer(Modifier.width(8.dp))
            Column {
                Text(
                    history.food.name,
                    color = Color.Black,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    "${history.food.price.thousandGroupByDot()} đ",
                    color = Color.Gray,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        AsyncImage(
            Uri.parse(history.food.img),
            "",
            contentScale = ContentScale.Crop,
            modifier =
            Modifier
                .width(48.dp)
                .aspectRatio(1f)
                .clip(CircleShape)
                .border(2.dp, OrangePrimary, CircleShape)
        )
    }

}

@Composable
fun HistoryDivider(showHorizontalLine: Boolean = true) {
    Box(
        Modifier,
        contentAlignment = Alignment.Center
    ) {
        Box(Modifier.size(8.dp).clip(CircleShape).background(OrangePrimary))
        if (showHorizontalLine)
            Divider(
                color = OrangePrimary,
                modifier = Modifier.fillMaxHeight().width(1.dp)
            )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HistoryBlockPreview() {
    HistoryBlock(
        "23/7/2023",
        listOf(
            HistoryWithFood(1, 1, 1689446918, Food(1, 1, "nbfsjhz", 234323, "msvlkdj", "shjfdsh")),
            HistoryWithFood(1, 1, 1689446918, Food(1, 1, "nbfsjhz", 234323, "msvlkdj", "shjfdsh")),
            HistoryWithFood(1, 1, 1689446918, Food(1, 1, "nbfsjhz", 234323, "msvlkdj", "shjfdsh")),
        )
    )
}