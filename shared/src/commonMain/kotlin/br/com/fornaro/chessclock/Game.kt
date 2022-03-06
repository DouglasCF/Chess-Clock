package br.com.fornaro.chessclock

data class Game(
    var totalTime: Long,
    var increment: Long = 0,
    var isPlaying: Boolean = false,
    var isWhiteMove: Boolean = true,
    var whiteTimeRemaining: Long = totalTime,
    var blackTimeRemaining: Long = totalTime,
) {
    companion object {
        const val milliConst = 1000L
        const val delay = 100L
    }

    var isFinished = false

    val isWhiteWinner: Boolean
        get() = blackTimeRemaining <= 0

    val isBlackWinner: Boolean
        get() = whiteTimeRemaining <= 0

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

    private fun initNewGame() {
        blackTimeRemaining = totalTime
        whiteTimeRemaining = totalTime
        isPlaying = true
        isWhiteMove = true
        isFinished = false
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
        if (isBlackWinner) {
            isPlaying = false
            isFinished = true
        }
    }

    private fun checkWhiteWins() {
        if (isWhiteWinner) {
            isPlaying = false
            isFinished = true
        }
    }

    fun changeTurn() {
        if (!isPlaying) return
        isWhiteMove = !isWhiteMove
    }

    fun changePlayPause() {
        isPlaying = !isPlaying
        if (isWhiteWinner || isBlackWinner) {
            initNewGame()
        }
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
