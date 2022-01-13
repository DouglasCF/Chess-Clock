package br.com.fornaro.chessclock.android.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import br.com.fornaro.chessclock.android.theme.Dimens

@Composable
fun IncrementalText(
    value: String,
    modifier: Modifier = Modifier,
    color: Color,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.default)
            .alpha(if (value == "0") .3f else 1f)
            .then(modifier),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = horizontalArrangement
    ) {
        Image(
            imageVector = Icons.Default.ArrowUpward,
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = color),
            modifier = Modifier
                .size(20.dp)
                .padding(end = 2.dp)
        )
        Text(text = value, color = color)
    }
}