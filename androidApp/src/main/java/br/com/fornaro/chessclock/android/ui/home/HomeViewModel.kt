package br.com.fornaro.chessclock.android.ui.home

import androidx.lifecycle.ViewModel
import br.com.fornaro.chessclock.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private var totalTime = 1 * 60L
    private var incrementalTime = 0L

    private val _game: MutableStateFlow<Game> = MutableStateFlow(Game(totalTime, incrementalTime))
    val game get() = _game

    init {
        startGame(totalTime, incrementalTime)
    }

    fun startGame(totalTime: Long, incrementalTime: Long = 0L) {
        this.totalTime = totalTime
        this.incrementalTime = incrementalTime
        _game.value = Game(totalTime, incrementalTime)
    }

    private fun updateGame(action: Game.() -> Unit = {}) {
        _game.value = _game.value.copy(totalTime = totalTime, increment = incrementalTime)
            .apply { action() }
    }

    fun changePlayPause() = updateGame { changePlayPause() }

    fun onWhitePressedClock() = updateGame { onWhitePressedClock() }

    fun onBlackPressedClock() = updateGame { onBlackPressedClock() }

    fun decreaseWhiteTime() = updateGame { decreaseWhiteTime() }

    fun decreaseBlackTime() = updateGame { decreaseBlackTime() }

}