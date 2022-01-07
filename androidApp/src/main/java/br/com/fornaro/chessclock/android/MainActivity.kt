package br.com.fornaro.chessclock.android

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.fornaro.chessclock.android.navigation.SetupNavigation
import br.com.fornaro.chessclock.android.navigation.appNavigation
import br.com.fornaro.chessclock.android.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigation: SetupNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        setContent {
            val navController = rememberNavController()
            val scaffoldState = rememberScaffoldState()
            navigation.init(navController)
            AppTheme {
                Content(navController, scaffoldState)
            }
        }
    }
}

@Composable
fun Content(navController: NavHostController, scaffoldState: ScaffoldState) = Scaffold(
    scaffoldState = scaffoldState,
    content = appNavigation(navController = navController)
)
