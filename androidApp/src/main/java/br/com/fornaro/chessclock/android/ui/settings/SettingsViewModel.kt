package br.com.fornaro.chessclock.android.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fornaro.chessclock.model.GameMode
import br.com.fornaro.chessclock.repositories.GameModeRepository
import br.com.fornaro.chessclock.usecases.GetFullScreenModeUseCase
import br.com.fornaro.chessclock.usecases.ToggleFullScreenModeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val navigation: SettingsNavigation,
    private val gameModeRepository: GameModeRepository,
    private val toggleFullScreenModeUseCase: ToggleFullScreenModeUseCase,
    private val getFullScreenModeUseCase: GetFullScreenModeUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiModel())
    val uiState get() = _uiState

    init {
        observeGameModes()
        loadFullScreenGameMode()
    }

    private fun observeGameModes() {
        viewModelScope.launch {
            gameModeRepository.gameModes.collect {
                _uiState.value = uiState.value.copy(gameModes = it)
            }
        }
    }

    private fun loadFullScreenGameMode() {
        val value = getFullScreenModeUseCase()
        _uiState.value = uiState.value.copy(fullScreen = value)
    }

    fun onBackButtonClicked() {
        navigation.back()
    }

    fun onGameModeButtonClicked(index: Int) {
        gameModeRepository.selectGameMode(index)
    }

    fun onFullScreenOptionClicked() {
        toggleFullScreenModeUseCase()
        loadFullScreenGameMode()
    }
}

data class UiModel(
    val gameModes: List<GameMode> = emptyList(),
    val fullScreen: Boolean = false
)