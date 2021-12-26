package effects.wishes

import effects.Effect
import gamestate.GameManager
import gamestate.GameStateChange
import gamestate.Player

class AddGold(
    override val sourcePlayer: Player,
    private val goldAmount: Long,
): Effect {
    override fun getGameStateChange(gameManager: GameManager)
        = GameStateChange(goldGain = goldAmount)

    override fun isExpired(gameManager: GameManager): Boolean
        = false
}