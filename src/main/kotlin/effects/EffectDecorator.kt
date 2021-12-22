package effects

import gamestate.GameManager
import gamestate.GameStateChange
import gamestate.Player

interface EffectDecorator: Effect {
    val baseEffect: Effect
}