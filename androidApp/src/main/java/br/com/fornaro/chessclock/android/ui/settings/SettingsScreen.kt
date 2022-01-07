package br.com.fornaro.chessclock.android.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.fornaro.chessclock.android.theme.Dimens

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel()) {
    Scaffold(
        topBar = { TopBar(backAction = viewModel::onBackButtonClicked) },
        content = { Content() }
    )
}

@Composable
fun TopBar(
    backAction: () -> Unit = {}
) = TopAppBar(
    title = { Text(text = "Settings") },
    navigationIcon = {
        IconButton(onClick = backAction) {
            Icon(Icons.Default.ArrowBack, contentDescription = "back")
        }
    },
    contentColor = Color.Black,
    elevation = Dimens.noElevation,
)

@Composable
fun Content() = Column {
    Text(
        text = "Game Mode",
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(start = Dimens.default, top = Dimens.default),
    )

    LazyRow(
        contentPadding = PaddingValues(start = 8.dp, end = 8.dp)
    ) {
        item {
            GameModeItem(text = "1 min")
            GameModeItem(text = "5 min")
            GameModeItem(text = "10 min")
        }
    }
}