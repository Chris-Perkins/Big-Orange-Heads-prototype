package effects

import gamestate.GameManager
import gamestate.Player

/**
 * An effect modifier that can be destroyed once the given turn is passed.
 */
abstract class TurnBasedModifier(
    private val endingTurn: Int
): EffectModifier {
    override fun isModifierDestroyable(gameManager: GameManager, player: Player): Boolean =
        gameManager.getCurrentTurn() > endingTurn
}