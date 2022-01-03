package br.com.fornaro.chessclock.android

import java.util.concurrent.TimeUnit

private const val milliConst = 1000L

data class Game(
    var totalTime: Long,
    var increment: Long = 0,
    var isPlaying: Boolean = false,
    var isWhiteMove: Boolean = true,
    var whiteTimeRemaining: Long = totalTime * milliConst,
    var blackTimeRemaining: Long = totalTime * milliConst,
) {

    val whiteTimeRemainingString: String
        get() = String.format(
            "%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(whiteTimeRemaining),
            TimeUnit.MILLISECONDS.toMinutes(whiteTimeRemaining) -
                    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(whiteTimeRemaining)),
            TimeUnit.MILLISECONDS.toSeconds(whiteTimeRemaining) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(whiteTimeRemaining))
        )

    val blackTimeRemainingString: String
        get() = String.format(
            "%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(blackTimeRemaining),
            TimeUnit.MILLISECONDS.toMinutes(blackTimeRemaining) -
                    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(blackTimeRemaining)),
            TimeUnit.MILLISECONDS.toSeconds(blackTimeRemaining) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(blackTimeRemaining))
        )

    init {
        totalTime *= milliConst
        increment *= milliConst
    }

    fun decreaseWhiteTime() {
        if (!isWhiteMove || !isPlaying) return
        if (whiteTimeRemaining > 0) {
            whiteTimeRemaining -= 100
        }
    }

    fun decreaseBlackTime() {
        if (isWhiteMove || !isPlaying) return
        if (blackTimeRemaining > 0) {
            blackTimeRemaining -= 100
        }
    }

    fun changeTurn() {
        if (!isPlaying) return
        isWhiteMove = !isWhiteMove
    }

    fun changePlayPause() {
        isPlaying = !isPlaying
    }

    fun onWhitePressedClock() {
        if (!whiteCanPlay()) return
        changeTurn()
        whiteTimeRemaining += increment
    }

    fun onBlackPressedClock() {
        if (!blackCanPlay()) return
        changeTurn()
        blackTimeRemaining += increment
    }

    fun blackCanPlay() = !isWhiteMove && isPlaying

    fun whiteCanPlay() = isWhiteMove && isPlaying
}