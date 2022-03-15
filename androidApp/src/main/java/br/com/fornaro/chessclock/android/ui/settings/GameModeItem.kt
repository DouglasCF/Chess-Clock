package br.com.fornaro.chessclock.android.ui.settings

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fornaro.chessclock.android.domain.models.GameModeModel
import br.com.fornaro.chessclock.android.theme.GameMode
import br.com.fornaro.chessclock.android.theme.GameModeSelected

@Composable
fun GameModeItem(
    gameMode: GameModeModel,
    clickAction: () -> Unit = {}
) {
    Button(
        onClick = clickAction,
        shape = RoundedCornerShape(100.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = if (gameMode.isSelected) GameModeSelected else GameMode),
        modifier = Modifier
            .widthIn(70.dp, 200.dp)
            .padding(horizontal = 8.dp)
    ) {
        Text(
            text = gameMode.text,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview
@Composable
fun ComposablePreview() {
    GameModeItem(GameModeModel("1 min", true))
}

@Preview
@Composable
fun ComposablePreview2() {
    GameModeItem(GameModeModel("1 min", false))
}

@Preview
@Composable
fun ComposablePreview3() {
    GameModeItem(GameModeModel("1 | 5"))
}
