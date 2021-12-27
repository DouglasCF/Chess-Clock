package br.com.fornaro.chessclock.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.fornaro.chessclock.android.navigation.appNavigation
import br.com.fornaro.chessclock.android.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val scaffoldState = rememberScaffoldState()
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
