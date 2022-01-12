package br.com.fornaro.chessclock.repositories

import br.com.fornaro.chessclock.model.GameMode
import kotlinx.coroutines.flow.MutableStateFlow

class GameModeRepository {

    private var gameModesList =
        mutableListOf(GameMode(1), GameMode(5, isSelected = true), GameMode(10, 10))

    private val _gameModes = MutableStateFlow<List<GameMode>>(gameModesList)
    val gameModes get() = _gameModes

    fun selectGameMode(index: Int) {
        val newList = gameModesList.toMutableList()

        val previousIndex = newList.indexOf(newList.first { it.isSelected })
        newList[previousIndex] = newList[previousIndex].copy(isSelected = false)
        newList[index] = newList[index].copy(isSelected = true)

        gameModesList = newList
        _gameModes.value = newList
    }
}