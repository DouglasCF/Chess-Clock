package br.com.fornaro.chessclock.android.navigation

import androidx.navigation.NavHostController
import br.com.fornaro.chessclock.android.ui.home.HomeNavigation
import javax.inject.Inject

class NavigationManager @Inject constructor(
    private val navHostController: NavHostController
) : BackNavigation, HomeNavigation {

    override fun back() {
        navHostController.navigateUp()
    }
}
