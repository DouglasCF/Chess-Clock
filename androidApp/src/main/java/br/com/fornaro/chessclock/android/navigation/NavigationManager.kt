package br.com.fornaro.chessclock.android.navigation

import androidx.navigation.NavHostController
import br.com.fornaro.chessclock.android.ui.home.HomeNavigation
import br.com.fornaro.chessclock.android.ui.settings.SettingsNavigation
import java.lang.ref.WeakReference

object NavigationManager : SetupNavigation, BackNavigation, HomeNavigation, SettingsNavigation {

    private lateinit var navHostController: WeakReference<NavHostController>
    private val navController get() = navHostController.get()!!

    override fun init(navController: NavHostController) {
        this.navHostController = WeakReference(navController)
    }

    override fun back() {
        navController.navigateUp()
    }

    override fun settings() {
        navController.navigate(NavigationScreen.Settings.route)
    }
}
