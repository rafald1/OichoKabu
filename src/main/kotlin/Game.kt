import kotlin.random.Random

class Game(private val deck: Deck, private var players: MutableList<Player>) {
    private fun shuffleDeck() {
        deck.shuffle()
    }

    private fun dealOneCardToEachPlayer() {
        for (player in players) {
            val card = deck.removeCard()
            player.addCard(card)
        }
    }

    private fun chooseDealer(): Player {
        val dealer = players.toMutableList()
        while (dealer.size > 1) {
            val playerCardMap = dealer.associateWith { deck.cards.random() }
            val minValue = playerCardMap.values.min()
            for ((key, value) in playerCardMap) {
                if (value != minValue) dealer.remove(key)
            }
        }
        return dealer[0]
    }

    private fun chooseNewDealer(dealer: Player? = null) {
        val i = if (dealer != null) players.indexOf(dealer) else 1
        val tempPlayers = players.slice(i until players.size) + players.slice(0 until i)
        players = tempPlayers.toMutableList()
    }

    private fun calculatePlayerHands(): MutableList<Pair<String, Int>> {
        val handValues = mutableListOf(players[0].currentHand(isDealer = true))
        for (player in players.slice(1 until players.size)) {
            handValues.add(player.currentHand(isDealer = false))
        }
        return handValues
    }

    private fun dealThirdCard(handValues: MutableList<Pair<String, Int>>) {
        handValues.forEachIndexed { index, value ->
            var drawThirdCard = false
            if (value.second <=3) drawThirdCard = true
            else if (value.second < 7) {
                if (players[index].ai) {
                    // An AI player has a 50% chance to draw the third card
                    if (Random.nextBoolean()) drawThirdCard = true
                }
                else {
                    if (askIfNotAiPlayerWantsToDrawThirdCard()) drawThirdCard = true
                }
            }
            if (drawThirdCard) {
                val card = deck.removeCard()
                players[index].addCard(card)
            }
        }
    }

    private fun askIfNotAiPlayerWantsToDrawThirdCard(): Boolean {
        print("Do you want to draw the third card? [y/n] ")
        val inputValue = readlnOrNull()
        return inputValue == "Y" || inputValue == "y"
    }

    private fun printPlayerNamesAndTheirHands() {
        println("${players[0].name} (dealer) | ${players[1].name} | ${players[2].name} | ${players[3].name}")
        println("${players[0].hand} | ${players[1].hand} | ${players[2].hand} | ${players[3].hand}")
    }

    private fun determineWhoWins(handValues: MutableList<Pair<String, Int>>): Player {
        for (i in 1..3) {
            if (handValues[0].second < handValues[i].second) {
                return players[i]
            }
        }
        return players[0]
    }

    fun play() {
        shuffleDeck()
        val dealer = chooseDealer()
        chooseNewDealer(dealer)
        dealOneCardToEachPlayer()
        dealOneCardToEachPlayer()
        printPlayerNamesAndTheirHands()
        var playerHands = calculatePlayerHands()
        println(playerHands)
        dealThirdCard(playerHands)
        printPlayerNamesAndTheirHands()
        playerHands = calculatePlayerHands()
        println(playerHands)
        val winner = determineWhoWins(playerHands)
        println("${winner.name} won the game.")
    }
}