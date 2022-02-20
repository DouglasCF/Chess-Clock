package br.com.fornaro.chessclock.usecases

import br.com.fornaro.chessclock.constants.Constants
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

class ToggleFullScreenModeUseCase internal constructor(
    private val settings: Settings
) {

    companion object {
        fun create() = ToggleFullScreenModeUseCase(Settings())
    }

    operator fun invoke(): Boolean {
        val newValue = !settings.getBoolean(Constants.settingsFullScreenMode, false)
        settings[Constants.settingsFullScreenMode] = newValue
        return newValue
    }
}