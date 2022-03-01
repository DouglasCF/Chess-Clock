package br.com.fornaro.chessclock.repositories

import br.com.fornaro.chessclock.model.GameMode
import kotlinx.coroutines.flow.MutableStateFlow

class GameModeRepository {

    private var gameModesList =
        mutableListOf(
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