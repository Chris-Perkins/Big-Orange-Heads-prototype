package effects.wishes

import effects.TurnBasedExpirationEffect
import gamestate.GameManager
import gamestate.GameStateChange

class AddGold(
    private val goldAmount: Long,
    expireOnTurn: Int
): TurnBasedExpirationEffect(
    expireOnTurn
) {
    override fun getGameStateChange(gameManager: GameManager)
        = GameStateChange(playerGoldGain = goldAmount)
}