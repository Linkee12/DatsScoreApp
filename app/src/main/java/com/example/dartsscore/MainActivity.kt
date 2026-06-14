package com.example.dartsscore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.example.dartsscore.services.dataService
import com.example.dartsscore.ui.theme.DartsScoreTheme

val LocalDataService = compositionLocalOf<dataService> {
    error("DataService not provided")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val dataService = remember { dataService() }

            CompositionLocalProvider(
                LocalDataService provides dataService
            ) {
                DartsScoreTheme {
                    val navController = rememberNavController()
                    AppNavigation(navController = navController)
                }
            }
        }
    }
}

