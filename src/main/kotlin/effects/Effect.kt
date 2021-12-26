package effects

import gamestate.GameManager
import gamestate.GameStateChange
import gamestate.Player

interface Effect {
    /**
     * The player who played this effect
     */
    val sourcePlayer: Player

    /**
     * @return An object representing how the GameState should
     * be changed as a result of this effect being performed
     */
    fun getGameStateChange(gameManager: GameManager): GameStateChange

    /**
     * @return Whether the effect is expired and should be deleted
     */
    fun isExpired(gameManager: GameManager): Boolean
}