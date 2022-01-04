package br.com.fornaro.chessclock.android.ui.home

import androidx.lifecycle.ViewModel
import br.com.fornaro.chessclock.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private var totalTime = 5 * 60L
    private var incremental = 2L

    private val _game: MutableStateFlow<Game> = MutableStateFlow(Game(totalTime, incremental))
    val game get() = _game

    init {
        startGame(totalTime, incremental)
    }

    fun startGame(startTime: Long = 5 * 60L, incremental: Long = 0L) {
        this.totalTime = startTime
        this.incremental = incremental
        _game.value = Game(startTime, incremental)
    }

    private fun updateGame(action: Game.() -> Unit = {}) {
        _game.value = _game.value.copy(totalTime = totalTime, increment = incremental)
            .apply { action() }
    }

    fun changePlayPause() = updateGame { changePlayPause() }

    fun onWhitePressedClock() = updateGame { onWhitePressedClock() }

    fun onBlackPressedClock() = updateGame { onBlackPressedClock() }

    fun decreaseWhiteTime() = updateGame { decreaseWhiteTime() }

    fun decreaseBlackTime() = updateGame { decreaseBlackTime() }

}