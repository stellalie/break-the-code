package demo

import com.github.shiguruikai.combinatoricskt.Combinatorics

val ALL_CARDS = TwoPlayerSetup.cards

class Me(val tiles: Set<Tile>) {
    override fun toString() = "[ME]: $tiles"
}

class Board(val me: Me, val players: List<Player>) {
    companion object {
        fun build(meTiles: String, playerNames: List<String>): Board {
            val me = Me(meTiles.toTilesSet())
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