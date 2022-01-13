package br.com.fornaro.chessclock.android.ui.settings

import androidx.lifecycle.ViewModel
import br.com.fornaro.chessclock.repositories.GameModeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val navigation: SettingsNavigation,
    private val gameModeRepository: GameModeRepository,
) : ViewModel() {

    val gameModes get() = gameModeRepository.gameModes

    fun onBackButtonClicked() {
        navigation.back()
    }

    fun onGameModeButtonClicked(index: Int) {
        gameModeRepository.selectGameMode(index)
    }
}