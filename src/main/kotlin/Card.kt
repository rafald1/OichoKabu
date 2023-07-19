class Card(rank: Int) : Comparable<Card> {
    companion object {
        private val OICHO_KABU_RANKS = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        fun createOichoKabuSet(): List<Card> {
            return OICHO_KABU_RANKS.flatMap { rank -> List(4) { Card(rank) } }
        }
    }

    val rank: Int

    init {
        if (rank !in OICHO_KABU_RANKS) {
            throw IllegalArgumentException("Invalid rank. Correct ranks: $OICHO_KABU_RANKS.")
        }
        this.rank = rank
    }

    override fun compareTo(other: Card): Int {
        return if (this.rank > other.rank) 1
        else if (this.rank < other.rank) -1
        else 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Card
        return rank == other.rank
    }

    override fun hashCode(): Int {
        return rank
    }

    override fun toString(): String {
        return "Card('$rank')"
    }
}