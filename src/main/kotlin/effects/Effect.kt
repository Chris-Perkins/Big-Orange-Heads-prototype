package effects

import gamestate.GameManager
import gamestate.Player

abstract class Effect {
    open var baseGold: Long = 0

    abstract fun getText(): String
    abstract fun performEffect(gameManager: GameManager, player: Player)
}