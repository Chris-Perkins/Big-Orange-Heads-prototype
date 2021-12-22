package effects

import gamestate.GameManager

abstract class TurnBasedExpirationEffect(
    private val expireOnTurn: Int
): Effect {
    override fun isExpired(gameManager: GameManager): Boolean
        = gameManager.getCurrentTurn() >= expireOnTurn
}