package br.com.fornaro.chessclock

data class Game(
    var totalTime: Long,
    var increment: Long = 0,
    var isPlaying: Boolean = false,
    var isWhiteMove: Boolean = true,
    var whiteTimeRemaining: Long = totalTime * milliConst,
    var blackTimeRemaining: Long = totalTime * milliConst,
) {
    companion object {
        private const val milliConst = 1000L
        const val delay = 100L
    }

    val whiteTimeRemainingString: String
        get() = stringFromTimeInterval(whiteTimeRemaining)

    val blackTimeRemainingString: String
        get() = stringFromTimeInterval(blackTimeRemaining)

    val incrementTimeString: String
        get() = (increment / milliConst).toString()

    private fun stringFromTimeInterval(time: Long): String {
        val seconds = ((time / milliConst) % 60)
        val minutes = ((time / 60 / milliConst) % 60)
        val hours = (time / 3600 / milliConst)
        val ms = (time % milliConst)

        return when {
            hours > 0 -> "${hours.toString().padStart(2, '0')}:" +
                    "${minutes.toString().padStart(2, '0')}:" +
                    seconds.toString().padStart(2, '0')
            minutes > 0 -> "${minutes.toString().padStart(2, '0')}:" +
                    seconds.toString().padStart(2, '0')
            else -> seconds.toString().padStart(2, '0')
        }
    }

    init {
        totalTime *= milliConst
        increment *= milliConst
    }

    fun decreaseWhiteTime() {
        if (!isWhiteMove || !isPlaying) return
        if (whiteTimeRemaining > 0) {
            whiteTimeRemaining -= delay
            checkBlackWins()
        }
    }

    fun decreaseBlackTime() {
        if (isWhiteMove || !isPlaying) return
        if (blackTimeRemaining > 0) {
            blackTimeRemaining -= delay
            checkWhiteWins()
        }
    }

    private fun checkBlackWins() {
        if (whiteTimeRemaining <= 0) {
            isPlaying = false
        }
    }

    private fun checkWhiteWins() {
        if (blackTimeRemaining <= 0) {
            isPlaying = false
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