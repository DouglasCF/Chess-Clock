package br.com.fornaro.chessclock.android.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import br.com.fornaro.chessclock.android.theme.Dimens

@Composable
fun TimeText(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    onClickAction: () -> Unit = {},
    text: String,
    textColor: Color,
) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxWidth()
            .clickable { onClickAction() }
            .padding(Dimens.default)
            .then(modifier)) {
        Text(
            text = text,
            fontWeight = FontWeight.Black,
            fontSize = 32.sp,
            color = textColor,
        )
    }
}