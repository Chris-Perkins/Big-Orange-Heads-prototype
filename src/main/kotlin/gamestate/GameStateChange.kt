package gamestate

/**
 * Represents a change in the game state
 */
data class GameStateChange(
    val playerGoldGain: Long?,
) {
    operator fun plus(other: GameStateChange): GameStateChange {
        return GameStateChange(
            playerGoldGain = addNullableLongs(this.playerGoldGain, other.playerGoldGain)
        )
    }

    private fun addNullableLongs(v1: Long?, v2: Long?): Long? {
        val v1Val = v1 ?: return v2
        val v2Val = v2 ?: return v1
        return v1Val + v2Val
    }
}