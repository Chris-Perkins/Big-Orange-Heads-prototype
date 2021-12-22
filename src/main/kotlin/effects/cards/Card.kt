package effects.cards

import gamestate.GameManager
import gamestate.Player

interface Card {
    val priority: CardRarity

    fun getText(): String
    fun playCard(gameManager: GameManager, player: Player)
}