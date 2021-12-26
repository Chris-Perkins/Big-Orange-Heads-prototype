package effects.wishes

import effects.Effect
import gamestate.GameState
import gamestate.GameStateChange
import gamestate.Player

class AddGold(
    override val sourcePlayer: Player,
    private val goldAmount: Long,
): Effect {
    override fun getGameStateChange(gameState: GameState)
        = GameStateChange(goldGain = goldAmount)

    override fun isExpired(gameState: GameState): Boolean
        = false
}