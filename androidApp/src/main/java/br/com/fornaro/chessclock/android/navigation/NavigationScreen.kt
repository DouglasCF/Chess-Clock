package br.com.fornaro.chessclock.android.navigation

import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationScreen(
    val route: String,
    val labelText: String? = null,
    val icon: ImageVector? = null
) {
    object Home : NavigationScreen("home")
    object Settings : NavigationScreen("settings")
}
