package br.com.fornaro.chessclock.android.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.fornaro.chessclock.android.theme.Dimens
import br.com.fornaro.chessclock.android.theme.Lose
import br.com.fornaro.chessclock.android.theme.Victory

@Composable
fun VictoryText(
    winner: Boolean,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.BottomEnd
) {
    val text = if (winner) "You win!" else "You lose!"
    val backgroundColor = if (winner) Victory else Lose
    Box(
        modifier = Modifier
            .padding(Dimens.default)
            .fillMaxSize()
            .then(modifier),
        contentAlignment = contentAlignment
    ) {
        Text(
            text = text,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .background(backgroundColor, RoundedCornerShape(100.dp))
                .padding(12.dp)
        )
    }
}