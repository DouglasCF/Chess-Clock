package br.com.fornaro.chessclock.android.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fornaro.chessclock.Game
import br.com.fornaro.chessclock.repositories.GameModeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val gameModeRepository: GameModeRepository,
    private val navigation: HomeNavigation,
) : ViewModel() {

    private var totalTime = 5 * 60L * Game.milliConst
    private var incrementalTime = 0L

    private val _game: MutableStateFlow<Game> = MutableStateFlow(Game(totalTime, incrementalTime))
    val game get() = _game

    init {
        startGame(totalTime, incrementalTime)
        observeGameMode()
    }

    private fun observeGameMode() {
        viewModelScope.launch {
            gameModeRepository.gameModes.map { it.first { it.isSelected } }.collect {
                totalTime = it.time * 60 * Game.milliConst
                incrementalTime = it.increment * Game.milliConst
            }
        }
    }

    fun startGame(totalTime: Long = this.totalTime, incrementalTime: Long = this.incrementalTime) {
        this.totalTime = totalTime
        this.incrementalTime = incrementalTime
        _game.value = Game(totalTime, incrementalTime)
    }

    private fun updateGame(action: Game.() -> Unit = {}) {
        _game.value = _game.value.copy()
            .apply { action() }
    }

    fun changePlayPause() = updateGame { changePlayPause() }

    fun onWhitePressedClock() = updateGame { onWhitePressedClock() }

    fun onBlackPressedClock() = updateGame { onBlackPressedClock() }

    fun decreaseWhiteTime() = updateGame { decreaseWhiteTime() }

    fun decreaseBlackTime() = updateGame { decreaseBlackTime() }

    fun onSettingsClicked() {
        navigation.settings()
    }
}