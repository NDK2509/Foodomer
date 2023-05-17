package com.example.foodomer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.foodomer.navigation.MainNavigation
import com.example.foodomer.ui.theme.FoodomerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodomerTheme {
                MainNavigation()
            }
        }
    }
}
