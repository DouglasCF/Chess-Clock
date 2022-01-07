package br.com.fornaro.chessclock.android.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fornaro.chessclock.android.theme.Dimens

@Composable
fun GameModeItem(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .padding(8.dp)
            .background(Color.Red, RoundedCornerShape(100.dp))
            .padding(Dimens.default)
    )
}

@Preview
@Composable
fun ComposablePreview() {
    GameModeItem(text = "text")
}