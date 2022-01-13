package br.com.fornaro.chessclock.android.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.fornaro.chessclock.android.theme.Dimens
import br.com.fornaro.chessclock.model.GameMode

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel()) {

    val gameModes by viewModel.gameModes.collectAsState(emptyList())

    Scaffold(
        topBar = { TopBar(backAction = viewModel::onBackButtonClicked) },
        content = {
            Content(
                gameModes = gameModes,
                gameModeClickAction = viewModel::onGameModeButtonClicked
            )
        }
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
fun Content(
    gameModes: List<GameMode>,
    gameModeClickAction: (Int) -> Unit,
) = Column {
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
        itemsIndexed(gameModes) { index, gameMode ->
            GameModeItem(
                gameMode = gameMode,
                clickAction = { gameModeClickAction(index) }
            )
        }
    }
}