package br.com.fornaro.chessclock.android.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fornaro.chessclock.model.GameMode
import br.com.fornaro.chessclock.repositories.GameModeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val navigation: SettingsNavigation,
    private val gameModeRepository: GameModeRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiModel())
    val uiState get() = _uiState

    init {
        observeGameModes()
    }

    private fun observeGameModes() {
        viewModelScope.launch {
            gameModeRepository.gameModes.collect {
                _uiState.value = uiState.value.copy(gameModes = it)
            }
        }
    }

    fun onBackButtonClicked() {
        navigation.back()
    }

    fun onGameModeButtonClicked(index: Int) {
        gameModeRepository.selectGameMode(index)
    }

    fun onFullScreenOptionClicked() {
        // persist option
        _uiState.value = uiState.value.copy(fullScreen = !uiState.value.fullScreen)
        // apply full screen option
    }
}

data class UiModel(
    val gameModes: List<GameMode> = emptyList(),
    val fullScreen: Boolean = false
)