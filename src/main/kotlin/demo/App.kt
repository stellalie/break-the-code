package demo

import com.github.shiguruikai.combinatoricskt.Combinatorics
import java.lang.IllegalStateException

val ALL_CARDS = TwoPlayerSetup.cards

class Player(val name: String, val cardsCombinations: MutableList<out List<Tile>>) {
    override fun toString(): String {
        val distinctMap = (0..4).map { columnIndex -> cardsCombinations.map { it[columnIndex] }.distinct() }
//        val distinctMapSize = distinctMap.fold(0) { acc, i -> if (i.size > acc) i.size else acc }
//        val printing = (0..distinctMapSize).map { columnIndex ->
//            if (distinctMap.getOrNull(columnIndex))
//        }
        val distinctPrint = distinctMap
            .mapIndexed { i, tile -> "$i: $tile" }
        return "[$name]: \n" +
                cardsCombinations.joinToString("\n") + "\n\n" +
                "Distinct: \n" +
                distinctPrint.joinToString("\n") + "\n\n" +
                "Count: ${cardsCombinations.size}"
    }

    fun hasTile(pos: Int, value: Int) {
        if (value == 5) throw IllegalStateException("Can't input 5")
        cardsCombinations.removeIf { it[pos].number != value }
    }

    fun hasTile(pos: Char, value: Int) = hasTile(pos.toPos(), value)

    fun has5Tile(pos: Int) {
        cardsCombinations.removeIf { it ->
            it[pos].number != 5 || it.filter { it.number == 5 }.size > 1
        }
    }

    fun has5Tile(pos: Char) = has5Tile(pos.toPos())

    fun has5Tiles(pos1: Int, pos2: Int) {
        cardsCombinations.removeIf { !(it[pos1].number == 5 && it[pos2].number == 5) }
    }

    fun has5Tiles(pos1: Char, pos2: Char) = has5Tiles(pos1.toPos(), pos2.toPos())

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

    fun countOfBlack(value: Int) {
        cardsCombinations.removeIf { it.filterByColor(Color.BLACK).size != value }
    }

    fun countOfWhite(value: Int) {
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

    fun countOfEven(value: Int) {
        cardsCombinations.removeIf { it.filter { n -> n.number % 2 == 0 }.size != value }
    }

    fun hasSameNumber(value: Int) {
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
            val combos = Combinatorics.combinations(ALL_CARDS.subtract(me.tiles), 5)
                .toMutableList()
                // To remove multiple of 5s (green)
                .distinctBy { it.toString() }
                .toMutableList()
            return Board(me = me, players = playerNames.map { name ->
                Player(name, combos)
            })
        }
    }
}

fun main() {
    val board = Board.build(
        meTiles = "0w 2w 7b 8b 8w",
        playerNames = listOf("CL")
    )
    val v = board.players[0]
    v.sumOfWhite(7)
    v.maxMinGap(9)
    v.neighbouringSameColor("ab cd")
    v.consecutive("cd")

    println(v)
}