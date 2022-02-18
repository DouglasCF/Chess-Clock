package br.com.fornaro.chessclock.android.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.fornaro.chessclock.android.theme.Dimens
import br.com.fornaro.chessclock.model.GameMode

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { TopBar(backAction = viewModel::onBackButtonClicked) },
        content = {
            Content(
                gameModes = uiState.gameModes,
                gameModeClickAction = viewModel::onGameModeButtonClicked,
                fullScreen = uiState.fullScreen,
                fullScreenClickAction = viewModel::onFullScreenOptionClicked,
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
    fullScreen: Boolean,
    gameModeClickAction: (Int) -> Unit,
    fullScreenClickAction: () -> Unit,
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
    Row(
        modifier = Modifier
            .padding(horizontal = Dimens.default)
            .padding(top = Dimens.default)
    ) {
        Text(
            text = "App in full screen mode",
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
        )
        Switch(
            checked = fullScreen,
            onCheckedChange = { fullScreenClickAction() },

            )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() = Content(
    gameModes = listOf(GameMode(1, 1, true), GameMode(2, 2, false)),
    fullScreen = true,
    gameModeClickAction = {},
    fullScreenClickAction = {},
)