package effects

import effects.Effect

interface EffectDecorator: Effect {
    val baseEffect: Effect
}