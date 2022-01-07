package br.com.fornaro.chessclock.android.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.fornaro.chessclock.android.ui.home.HomeScreen
import br.com.fornaro.chessclock.android.ui.settings.SettingsScreen

@Composable
fun appNavigation(navController: NavHostController): @Composable (PaddingValues) -> Unit = {
    NavHost(navController = navController, startDestination = NavigationScreen.Home.route) {
        composable(NavigationScreen.Home.route) { HomeScreen() }
        composable(NavigationScreen.Settings.route) { SettingsScreen() }
    }
}
