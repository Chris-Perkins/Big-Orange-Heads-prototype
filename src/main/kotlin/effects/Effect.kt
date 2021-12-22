package effects

import gamestate.GameManager
import gamestate.GameStateChange
import gamestate.Player

interface Effect {
    fun getGameStateChange(gameManager: GameManager): GameStateChange
    fun isExpired(gameManager: GameManager): Boolean
}