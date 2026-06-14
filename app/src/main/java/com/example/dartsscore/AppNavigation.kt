package com.example.dartsscore

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dartsscore.components.HomeScreen
import com.example.dartsscore.components.Players

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { HomeScreen(onNavigate = navController::navigate) }
        composable("player") { Players() }
    }
}