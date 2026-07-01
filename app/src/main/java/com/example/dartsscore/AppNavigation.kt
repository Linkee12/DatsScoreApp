package com.example.dartsscore

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dartsscore.components.Game
import com.example.dartsscore.components.HomeScreen
import com.example.dartsscore.components.Players
import com.example.dartsscore.viewmodel.PlayersViewModel

@Composable
fun AppNavigation(navController: NavHostController) {
    val playersViewModel: PlayersViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onNavigate = navController::navigate,
                viewModel = playersViewModel
            )
        }
        composable(
            "player",
        ) {
            Players(
                onNavigate = navController::navigate,
                viewModel = playersViewModel
            )
        }
        composable("game") { Game(viewModel = playersViewModel) }
    }
}