package effects.cards

enum class CardRarity(val generationWeight: Long) {
    LEGENDARY(1L),
    RARE(2L),
    COMMON(3L),
}