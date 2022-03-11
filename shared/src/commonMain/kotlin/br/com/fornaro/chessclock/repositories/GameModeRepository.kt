package br.com.fornaro.chessclock.repositories

import br.com.fornaro.chessclock.model.GameMode
import kotlinx.coroutines.flow.MutableStateFlow

class GameModeRepository {

    private var gameModesList = mutableListOf(
        GameMode(1),
        GameMode(1, 1),
        GameMode(1, 2),
        GameMode(3),
        GameMode(3, 2),
        GameMode(5, isSelected = true),
        GameMode(5, 5),
        GameMode(10),
        GameMode(10, 10),
        GameMode(15),
        GameMode(15, 10),
        GameMode(30),
        GameMode(30, 10),
        GameMode(45),
        GameMode(45, 45),
    )

    private var customGameModesList = mutableListOf(
        GameMode(3, 3),
    )

    private val _gameModes = MutableStateFlow<List<GameMode>>(gameModesList)
    val gameModes get() = _gameModes

    private val _customGameModes = MutableStateFlow<List<GameMode>>(customGameModesList)
    val customGameModes get() = _customGameModes

    fun selectGameMode(index: Int) {
        cleanSelected()

        val new = gameModesList.toMutableList()
        new[index] = new[index].copy(isSelected = true)
        gameModesList = new
        _gameModes.value = new
    }

    fun selectCustomGameMode(index: Int) {
        cleanSelected()

        val new = customGameModesList.toMutableList()
        new[index] = new[index].copy(isSelected = true)
        customGameModesList = new
        _customGameModes.value = new
    }

    private fun cleanSelected() {
        var indexOfFirst = customGameModesList.indexOfFirst { it.isSelected }
        if (indexOfFirst != -1) {
            val list = customGameModesList.toMutableList()
            list[indexOfFirst] = list[indexOfFirst].copy(isSelected = false)
            customGameModesList = list
            _customGameModes.value = list
        } else {
            indexOfFirst = gameModesList.indexOfFirst { it.isSelected }
            if (indexOfFirst != -1) {
                val list = gameModesList.toMutableList()
                list[indexOfFirst] = list[indexOfFirst].copy(isSelected = false)
                gameModesList = list
                _gameModes.value = list
            }
        }
    }
}
