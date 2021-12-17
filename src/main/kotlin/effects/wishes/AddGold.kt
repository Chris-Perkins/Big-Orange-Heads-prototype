package effects.wishes

import effects.Effect
import gamestate.GameManager
import gamestate.Player

class AddGold(goldAmount: Long): Effect() {
    override var baseGold = goldAmount

    override fun getText(): String
        = "Receive $baseGold gold"

    override fun performEffect(gameManager: GameManager, player: Player) {
        gameManager.addGoldAmount(baseGold)
    }
}