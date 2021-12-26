package effects

interface EffectDecorator: Effect {
    val baseEffect: Effect
}