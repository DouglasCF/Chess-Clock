package br.com.fornaro.chessclock.usecases

import br.com.fornaro.chessclock.constants.Constants
import com.russhwolf.settings.Settings

class GetFullScreenModeUseCase internal constructor(
    private val settings: Settings
) {

    companion object {
        fun create() = GetFullScreenModeUseCase(settings = Settings())
    }

    operator fun invoke() = settings.getBoolean(Constants.settingsFullScreenMode, false)
}