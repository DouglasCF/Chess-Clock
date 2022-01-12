package br.com.fornaro.chessclock.android.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fornaro.chessclock.android.theme.Dimens
import br.com.fornaro.chessclock.android.theme.GameMode
import br.com.fornaro.chessclock.android.theme.GameModeSelected
import br.com.fornaro.chessclock.model.GameMode

@Composable
fun GameModeItem(
    gameMode: GameMode,
    clickAction: () -> Unit = {}
) {
    Text(
        text = gameMode.text,
        modifier = Modifier
            .clickable(onClick = clickAction)
            .padding(8.dp)
            .background(
                if (gameMode.isSelected) GameModeSelected else GameMode,
                RoundedCornerShape(100.dp)
            )
            .padding(Dimens.default)
    )
}

@Preview
@Composable
fun ComposablePreview() {
    GameModeItem(GameMode(1, 1, true))
}

@Preview
@Composable
fun ComposablePreview2() {
    GameModeItem(GameMode(1, 0, true))
}

@Preview
@Composable
fun ComposablePreview3() {
    GameModeItem(GameMode(1, 0))
}