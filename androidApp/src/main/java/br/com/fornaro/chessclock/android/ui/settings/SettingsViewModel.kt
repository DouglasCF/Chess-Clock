package br.com.fornaro.chessclock.android.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fornaro.chessclock.android.data.local.mappers.GameModeMapperAlias
import br.com.fornaro.chessclock.android.domain.models.GameModeModel
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
    private val gameModeMapper: GameModeMapperAlias,
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
                _uiState.value = uiState.value
                    .copy(gameModes = it.map { gameMode -> gameModeMapper.map(gameMode) })
            }
        }
        viewModelScope.launch {
            gameModeRepository.customGameModes.collect {
                _uiState.value = uiState.value
                    .copy(customGameModes = it.map { gameMode -> gameModeMapper.map(gameMode) })
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

    fun onCustomGameModeClicked(index: Int) {
        gameModeRepository.selectCustomGameMode(index)
    }

    fun onFullScreenOptionClicked() {
        toggleFullScreenModeUseCase()
        loadFullScreenGameMode()
    }

    fun onCreateNewGameModeClicked() {
        _uiState.value = uiState.value.copy(showCreateNewGameModeDialog = true)
    }

    fun onCreateNewGameModeDismissed() {
        _uiState.value = uiState.value.copy(showCreateNewGameModeDialog = false)
    }

    fun createNewGameMode(totalTime: Long, increment: Long) {
        gameModeRepository.addCustomGame(totalTime, increment)
    }
}

data class UiModel(
    val gameModes: List<GameModeModel> = emptyList(),
    val customGameModes: List<GameModeModel> = emptyList(),
    val fullScreen: Boolean = false,
    val showCreateNewGameModeDialog: Boolean = false,
)
