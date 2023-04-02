package demo

import com.github.shiguruikai.combinatoricskt.Combinatorics

val ALL_CARDS = TwoPlayerSetup.cards

class Player(val name: String, val cardsCombinations: MutableList<out List<Number>>) {
    override fun toString() = "[$name]: \n" +
            cardsCombinations.joinToString("\n") + "\n" +
            "Count: ${cardsCombinations.size}"

    fun sumOfCard(value: Int) {
        cardsCombinations.removeIf { it.sum() != value }
    }
    fun sumOfLeftMost(value: Int) {
        cardsCombinations.removeIf { it.take(3).sum() != value }
    }
    fun numOfBlack(value: Int) {
        cardsCombinations.removeIf { it.filterByColor(Color.BLACK).size != value }
    }
    fun numOfWhite(value: Int) {
        cardsCombinations.removeIf { it.filterByColor(Color.WHITE).size != value }
    }
    fun sumOfBlack(value: Int) {
        cardsCombinations.removeIf { it.filterByColor(Color.BLACK).sum() != value }
    }
    fun sumOfWhite(value: Int) {
        cardsCombinations.removeIf { it.filterByColor(Color.WHITE).sum() != value }
    }
    fun maxMinGap(value: Int) {
        cardsCombinations.removeIf { it.last().number - it.first().number != value }
    }
    fun noNeighbouringSameColor() {
        cardsCombinations.removeIf {
            var sameColor = false
            it.zipWithNext().forEach { pair ->
                if (pair.first.color == pair.second.color) {
                    sameColor = true
                }
            }
            sameColor
        }
    }
}

class Me(val numbers: Set<Number>) {
    override fun toString() = "[ME]: $numbers"
}


fun main() {
    val me = Me(numbers = setOf(BLACK_1, BLACK_4, GREEN_5a, BLACK_7, BLACK_8))
    val combinations = Combinatorics.combinations(ALL_CARDS.subtract(me.numbers), 5).toMutableList()
    val christina = Player("Christina", combinations)

    // Elimination time
    christina.sumOfCard(23)
    christina.sumOfLeftMost(9)
    christina.numOfBlack(2)
    christina.maxMinGap(7)
    christina.noNeighbouringSameColor()
    println(christina)
}