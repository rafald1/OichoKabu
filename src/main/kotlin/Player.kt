class Player(val name: String, val hand: Hand, val ai: Boolean) {
    fun currentHand(isDealer: Boolean): Pair<String, Int> {
        return hand.calculateHand(isDealer)
    }

    fun addCard(card: Card) {
        hand.addCard(card)
    }

    override fun toString(): String {
        return name
    }
}