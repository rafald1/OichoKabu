class Hand {
    private val cards = mutableListOf<Card>()

    fun addCard(card: Card) {
        cards.add(card)
    }

    private fun calculateValueOfCards(): Int {
        return cards.sumOf { it.rank } % 10
    }

    private fun arashi(): Boolean {
        if (cards.size < 3) {
            return false
        }
        return cards[0].rank == cards[1].rank && cards[0].rank == cards[2].rank
    }

    private fun kuppin(): Boolean {
        if (cards.size == 2) {
            return (cards[0].rank == 9 && cards[1].rank == 1) || (cards[0].rank == 1 && cards[1].rank == 9)
        }
        return false
    }

    private fun shippin(): Boolean {
        if (cards.size == 2) {
            return (cards[0].rank == 4 && cards[1].rank == 1) || (cards[0].rank == 1 && cards[1].rank == 4)
        }
        return false
    }

    fun calculateHand(dealer: Boolean): Pair<String, Int> {
        // To be able to evaluate winning hand shippin has fictional value of 10,
        // kuppin has fictional value of 11, and arashi has value increased by 12.
        if (arashi()) return Pair("arashi", 12 + calculateValueOfCards())
        if (kuppin() && dealer) return Pair("kuppin", 11)
        if (shippin() && !dealer) return Pair("shippin", 10)
        return Pair("regular", calculateValueOfCards())
    }

    override fun toString(): String {
        return cards.map { it }.toString()
    }
}