class Deck {
    val cards = mutableListOf<Card>()

    fun addCards(cards: MutableList<Card>) {
        this.cards.addAll(cards)
    }

    fun shuffle() {
        this.cards.shuffle()
    }

    fun removeCard(): Card {
        return this.cards.removeFirst()
    }
}