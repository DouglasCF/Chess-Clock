package br.com.fornaro.chessclock.model

data class GameMode(
    val time: Long,
    val increment: Long = 0L,
    var isSelected: Boolean = false,
) {
    val text: String
        get() {
            if (increment > 0) {
                return "$time | $increment"
            }
            return "$time min"
        }
}
