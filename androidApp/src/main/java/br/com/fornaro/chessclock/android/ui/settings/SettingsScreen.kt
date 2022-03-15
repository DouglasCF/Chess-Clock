package br.com.fornaro.chessclock.android.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.fornaro.chessclock.android.domain.models.GameModeModel
import br.com.fornaro.chessclock.android.theme.Dimens
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsState()

    val systemUiController = rememberSystemUiController()
    systemUiController.isSystemBarsVisible = !uiState.fullScreen

    Scaffold(
        topBar = { TopBar(backAction = viewModel::onBackButtonClicked) },
        content = {
            Content(
                gameModes = uiState.gameModes,
                gameModeClickAction = viewModel::onGameModeButtonClicked,
                customGameModes = uiState.customGameModes,
                customGameModeClickAction = viewModel::onCustomGameModeClicked,
                createNewGameModeClickAction = viewModel::onCreateNewGameModeClicked,
                fullScreen = uiState.fullScreen,
                fullScreenClickAction = viewModel::onFullScreenOptionClicked,
            )
        }
    )

    if (uiState.showCreateNewGameModeDialog) {
        NewGameModeDialog(
            onDismiss = viewModel::onCreateNewGameModeDismissed,
            createNewGameSuccessAction = viewModel::createNewGameMode
        )
    }
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
    backgroundColor = Color.White,
    contentColor = Color.Black,
    elevation = Dimens.noElevation,
)

@Composable
fun Content(
    gameModes: List<GameModeModel>,
    customGameModes: List<GameModeModel>,
    fullScreen: Boolean,
    gameModeClickAction: (Int) -> Unit,
    customGameModeClickAction: (Int) -> Unit,
    createNewGameModeClickAction: () -> Unit,
    fullScreenClickAction: () -> Unit,
) = Column(
    horizontalAlignment = Alignment.CenterHorizontally,
) {
    SectionText(text = "Game Mode")
    GameModeList(list = gameModes, clickAction = gameModeClickAction)
    GameModeList(list = customGameModes, clickAction = customGameModeClickAction)
    Button(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(top = Dimens.default),
        onClick = createNewGameModeClickAction
    ) {
        Text(
            text = "Create custom game mode",
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }

    SectionText(text = "General")
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = Dimens.default)
            .clickable { fullScreenClickAction() }
            .padding(horizontal = Dimens.default)
            .heightIn(0.dp, 48.dp)
            .fillMaxHeight()
    ) {
        Text(
            text = "App in full screen mode",
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked = fullScreen,
            onCheckedChange = { fullScreenClickAction() },
        )
    }
}

@Composable
private fun GameModeList(list: List<GameModeModel>, clickAction: (Int) -> Unit) = LazyRow(
    modifier = Modifier
        .fillMaxWidth()
        .padding(top = Dimens.default),
    contentPadding = PaddingValues(start = 8.dp, end = 8.dp)
) {
    itemsIndexed(list) { index, gameMode ->
        GameModeItem(
            gameMode = gameMode,
            clickAction = { clickAction(index) }
        )
    }
}

@Composable
private fun SectionText(text: String) = Text(
    text = text,
    color = Color.Black,
    fontWeight = FontWeight.Bold,
    fontSize = 20.sp,
    modifier = Modifier
        .fillMaxWidth()
        .padding(start = Dimens.default, top = Dimens.default),
)

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun NewGameModeDialog(
    onDismiss: () -> Unit,
    createNewGameSuccessAction: (Long, Long) -> Unit,
) {
    var totalTime by remember { mutableStateOf("") }
    var increment by remember { mutableStateOf("") }

    val (focusRequester) = FocusRequester.createRefs()

    val createAction = {
        if (totalTime.isNotEmpty()) {
            createNewGameSuccessAction(
                totalTime.toLong(),
                increment.ifEmpty { "0" }.toLong()
            )
            onDismiss()
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Create a new game mode") },
        text = {
            Column {
                OutlinedTextField(
                    value = totalTime,
                    onValueChange = { totalTime = it },
                    label = { Text(text = "Total time") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusRequester.requestFocus() }
                    )
                )
                OutlinedTextField(
                    value = increment,
                    onValueChange = { increment = it },
                    label = { Text(text = "Increment time") },
                    modifier = Modifier
                        .padding(top = Dimens.default)
                        .focusRequester(focusRequester),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { createAction() }
                    )
                )
            }
        },
        buttons = {
            Row(
                modifier = Modifier
                    .padding(end = Dimens.default)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = onDismiss) {
                    Text(text = "Cancel")
                }
                TextButton(onClick = createAction) {
                    Text(text = "Create", fontWeight = FontWeight.Bold)
                }
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun Preview() = Content(
    gameModes = listOf(GameModeModel("1 |  1", true), GameModeModel("2 | 2", false)),
    customGameModes = listOf(GameModeModel("1")),
    fullScreen = true,
    gameModeClickAction = {},
    customGameModeClickAction = {},
    createNewGameModeClickAction = {},
    fullScreenClickAction = {},
)
