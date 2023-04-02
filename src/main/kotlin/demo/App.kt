package demo

import com.github.shiguruikai.combinatoricskt.Combinatorics

val ALL_CARDS = TwoPlayerSetup.cards

class Player(val name: String, val cardsCombinations: MutableList<out List<Number>>) {
    override fun toString() = "[$name]: \n" +
            cardsCombinations.joinToString("\n") + "\n" +
            "Count: ${cardsCombinations.size}"

    fun number(pos: Int, value: Int) {
        cardsCombinations.removeIf { it[pos].number != value }
    }

    fun noNumber(value: Int) {
        cardsCombinations.removeIf { it.filter { n -> n.number == value }.isNotEmpty() }
    }

    fun tileCGreaterThan4() {
        cardsCombinations.removeIf { it[2].number <= 4 }
    }

    fun tileCIsNotGreaterThan4() {
        cardsCombinations.removeIf { it[2].number > 4 }
    }

    fun sumOfTiles(value: Int) {
        cardsCombinations.removeIf { it.sum() != value }
    }

    fun sumOfCentral(value: Int) {
        cardsCombinations.removeIf { it.slice(1..3).sum() != value }
    }

    fun sumOfLeftMost(value: Int) {
        cardsCombinations.removeIf { it.take(3).sum() != value }
    }

    fun sumOfRightMost(value: Int) {
        cardsCombinations.removeIf { it.slice(2..4).sum() != value }
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

    fun countOfOdd(value: Int) {
        cardsCombinations.removeIf { it.filter { n -> n.number % 2 != 0 }.size != value }
    }

    fun tilesHaveSameNumber(value: Int) {
        cardsCombinations.removeIf {
            val countMap = it.groupingBy { it.number }.eachCount()
            countMap.toList().filter { it.second >= 2 }.size != value
        }
    }

    fun noNeighbouringSameColor() {
        cardsCombinations.removeIf {
            for (pair in it.zipWithNext()) {
                if (pair.first.color == pair.second.color) {
                    return@removeIf true
                }
            }
            return@removeIf false
        }
    }

    fun neighbouringSameColor(value: List<List<Int>>) {
        cardsCombinations.removeIf { numbers ->
            value.forEach {
                // Group all the same color
                if (it.map { i -> numbers[i].color }.distinct().size != 1) {
                    return@removeIf true
                }
            }
            return@removeIf false
        }
    }

    fun noConsecutive() {
        cardsCombinations.removeIf {
            it.zipWithNext().forEach { pair ->
                if (pair.first.number + 1 == pair.second.number) {
                    return@removeIf true
                }
            }
            return@removeIf false
        }
    }

    fun consecutive(value: List<List<Int>>) {
        cardsCombinations.removeIf { numbers ->
            value.forEach { posToCheck ->
                posToCheck.dropLast(1).forEach { pos ->
                    if (numbers[pos].number + 1 != numbers[pos + 1].number) {
                        return@removeIf true
                    }
                }
            }
            return@removeIf false
        }
    }
}

class Me(val numbers: Set<Number>) {
    override fun toString() = "[ME]: $numbers"
}

fun main() {
    val me = Me(numbers = setOf(WHITE_0, WHITE_1, WHITE_2, GREEN_5a, BLACK_6))
    val combinations = Combinatorics.combinations(ALL_CARDS.subtract(me.numbers), 5).toMutableList()
    val versus = Player("Christina", combinations)

    // Elimination time
    versus.sumOfBlack(16)
    versus.tilesHaveSameNumber(0)
    versus.consecutive(listOf(listOf(1, 2, 3)))
    versus.sumOfTiles(30)
    versus.number(1, 5)
    versus.noNumber(8)

    // Print
    println(versus)
}