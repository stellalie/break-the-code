package demo

import com.github.shiguruikai.combinatoricskt.Combinatorics

val ALL_CARDS = TwoPlayerSetup.cards

class Player(val name: String, val cardsCombinations: MutableList<out List<Tile>>) {
    override fun toString() = "[$name]: \n" +
            cardsCombinations.joinToString("\n") + "\n" +
            "Count: ${cardsCombinations.size}"

    fun hasTile(pos: Int, value: Int) {
        cardsCombinations.removeIf { it[pos].number != value }
    }

    fun noTile(value: Int) {
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

    fun neighbouringSameColor(value: String) = neighbouringSameColor(value.toPosList())

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

    fun consecutive(value: String) = consecutive(value.toPosList())
}

class Me(val tiles: Set<Tile>) {
    override fun toString() = "[ME]: $tiles"
}

class Board(val me: Me, val players: List<Player>) {
    companion object {
        fun build(meTiles: String, playerNames: List<String>): Board {
            val me = Me(meTiles.toTiles())
            val combos = Combinatorics.combinations(ALL_CARDS.subtract(me.tiles), 5).toMutableList()
            return Board(me = me, players = playerNames.map { name ->
                Player(name, combos)
            })
        }
    }
}

fun main() {
    val board = Board.build(
        meTiles = "1w 3b 4b 4w 9b",
        playerNames = listOf("christina")
    )
    val versus = board.players[0]

    // Elimination time
    versus.sumOfCentral(13)
    versus.consecutive("ab")
    versus.neighbouringSameColor("ab cd")
    versus.sumOfRightMost(17)

    // Print
    println(versus)
}