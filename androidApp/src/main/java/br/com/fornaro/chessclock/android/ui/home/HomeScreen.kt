package br.com.fornaro.chessclock.android.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.fornaro.chessclock.Game
import br.com.fornaro.chessclock.android.theme.Dimens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val game by viewModel.game.collectAsState()

    LaunchedEffect(LocalContext.current) {
        launch(Dispatchers.IO) {
        while (true) {
            if (game.whiteTimeRemaining > 0 && game.isWhiteMove && game.isPlaying) {
                delay(Game.delay)
                viewModel.decreaseWhiteTime()
            }
        }}
    }

    LaunchedEffect(LocalContext.current) {
        launch(Dispatchers.IO) {
        while (true) {
            if (game.blackTimeRemaining > 0 && !game.isWhiteMove && game.isPlaying) {
                delay(Game.delay)
                viewModel.decreaseBlackTime()
            }
        }}
    }

    Content(
        game = game,
        changePlayPause = viewModel::changePlayPause,
        onWhitePressedClock = viewModel::onWhitePressedClock,
        onBlackPressedClock = viewModel::onBlackPressedClock,
    )
}

@Composable
private fun Content(
    game: Game,
    changePlayPause: () -> Unit = {},
    onWhitePressedClock: () -> Unit = {},
    onBlackPressedClock: () -> Unit = {},
) {

    val modifier = Modifier
        .fillMaxWidth()
        .padding(Dimens.default)

    Column {
        Box(
            modifier = Modifier
                .background(Color.White)
                .clickable { onWhitePressedClock() }
                .then(if (game.isWhiteMove) modifier.weight(1f) else modifier)
                .rotate(180f),
            contentAlignment = Alignment.BottomEnd
        ) {
            Text(
                text = game.whiteTimeRemainingString,
                fontWeight = FontWeight.Black,
                fontSize = 32.sp,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(Dimens.default),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            OutlinedButton(
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Gray),
                onClick = { /*TODO*/ }) {
                Image(imageVector = Icons.Default.Settings, contentDescription = null)
            }
            OutlinedButton(
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Gray),
                onClick = { changePlayPause() }) {
                Image(
                    imageVector = if (game.isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                    contentDescription = null
                )
            }
        }

        Box(
            modifier = Modifier
                .background(Color.Black)
                .clickable { onBlackPressedClock() }
                .then(if (!game.isWhiteMove) modifier.weight(1f) else modifier),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                text = game.blackTimeRemainingString,
                fontWeight = FontWeight.Black,
                fontSize = 32.sp,
                color = Color.White,
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ComposablePreview() {
    Content(Game(5 * 60))
}