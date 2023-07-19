fun main() {
    val cards = Card.createOichoKabuSet().toMutableList()
    val deck = Deck()
    deck.addCards(cards)

    val player1 = Player(name = "Johnny", hand = Hand(), ai = false)
    val player2 = Player(name = "RobotA", hand = Hand(), ai = true)
    val player3 = Player(name = "RobotB", hand = Hand(), ai = true)
    val player4 = Player(name = "RobotC", hand = Hand(), ai = true)

    val game = Game(deck, mutableListOf(player1, player2, player3, player4))
    game.play()
}
