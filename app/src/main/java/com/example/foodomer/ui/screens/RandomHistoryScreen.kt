package com.example.foodomer.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.foodomer.database.entities.HistoryWithFood
import com.example.foodomer.ui.AppViewModelProvider
import com.example.foodomer.ui.components.core.AbsoluteCircle
import com.example.foodomer.ui.components.core.CenteredColumn
import com.example.foodomer.ui.components.core.HeaderBar
import com.example.foodomer.ui.components.randomhistory.HistoryBlock
import com.example.foodomer.ui.components.randomhistory.RandomHistoryTitle
import com.example.foodomer.ui.theme.OrangePrimary
import com.example.foodomer.ui.viewmodels.RandomHistoryViewModel
import com.example.foodomer.utils.dateOnlyFormatter
import com.example.foodomer.utils.getScreenHeight
import com.example.foodomer.utils.getScreenWidth
import java.util.*

fun handleHistoryList(historyList: List<HistoryWithFood>): Map<String, MutableList<HistoryWithFood>> {
    val map = HashMap<String, MutableList<HistoryWithFood>>()
    historyList.forEach {
        val dateStr = dateOnlyFormatter.format(Date(it.createdAt))
        if (dateStr in map) {
            map[dateStr]?.add(it)
        } else {
            map[dateStr] = arrayOf(it).toMutableList()
        }
    }
    return map
}

@Composable
fun RandomHistoryScreen(
    navController: NavController? = null,
    viewModel: RandomHistoryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val historyList by viewModel.historyList.collectAsState(emptyList())
    var handledHistoryMap = handleHistoryList(historyList)

    LaunchedEffect(historyList.size) {
        Log.d("random:", "launch this")
        handledHistoryMap = handleHistoryList(historyList)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val screenWidth = getScreenWidth()
        val screenHeight = getScreenHeight()

        AbsoluteCircle(145.dp, offsetX = (screenWidth - 145 - 20).dp, offsetY = (-97).dp)
        AbsoluteCircle(120.dp, offsetX = (-150).dp, offsetY = 75.dp, OrangePrimary)
        AbsoluteCircle(90.dp, offsetX = (screenWidth - 90).dp, offsetY = (screenHeight * 0.5).dp, OrangePrimary)
        AbsoluteCircle(60.dp, offsetX = (-80).dp, offsetY = (screenHeight * 0.7).dp)
    }
    Column {
        HeaderBar(navController, showBackButton = true)
        Spacer(Modifier.height(20.dp))
        RandomHistoryTitle()
        Spacer(Modifier.height(20.dp))

        CenteredColumn(
            Modifier.padding(horizontal = 30.dp)
        ) {
            LazyColumn(
                state = rememberLazyListState(handledHistoryMap.size)
            ) {
                handledHistoryMap.forEach { (k, list) ->
                    item {
                        HistoryBlock(k, list)
                    }
                }
            }
        }
    }
}