package br.com.fornaro.chessclock.android.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fornaro.chessclock.Game
import br.com.fornaro.chessclock.model.GameMode
import br.com.fornaro.chessclock.repositories.GameModeRepository
import br.com.fornaro.chessclock.usecases.GetFullScreenModeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val gameModeRepository: GameModeRepository,
    private val navigation: HomeNavigation,
    private val getFullScreenModeUseCase: GetFullScreenModeUseCase,
) : ViewModel() {

    private var totalTime = 5 * 60L * Game.milliConst
    private var incrementalTime = 0L

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState get() = _uiState

    init {
        startGame(totalTime, incrementalTime)
        observeGameMode()
        loadFullScreenGameMode()
    }

    private fun observeGameMode() {
        viewModelScope.launch {
            gameModeRepository.gameModes.map { it.firstOrNull { it.isSelected } }.collect {
                if (it != null) {
                    updateGameMode(it)
                }
            }
        }
        viewModelScope.launch {
            gameModeRepository.customGameModes.map { it.firstOrNull { it.isSelected } }.collect {
                if (it != null) {
                    updateGameMode(it)
                }
            }
        }
    }

    private fun updateGameMode(gameMode: GameMode) {
        totalTime = gameMode.time * 60 * Game.milliConst
        incrementalTime = gameMode.increment * Game.milliConst
    }

    fun loadFullScreenGameMode() {
        val value = getFullScreenModeUseCase()
        _uiState.value = uiState.value.copy(fullScreen = value)
    }

    fun startGame(totalTime: Long = this.totalTime, incrementalTime: Long = this.incrementalTime) {
        this.totalTime = totalTime
        this.incrementalTime = incrementalTime
        _uiState.value = uiState.value.copy(game = Game(totalTime, incrementalTime))
    }

    private fun updateGame(action: Game.() -> Unit = {}) {
        _uiState.value = uiState.value.copy(game = uiState.value.game.copy().apply { action() })
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

data class UiState(
    val game: Game = Game(0, 0),
    val fullScreen: Boolean = false
)
