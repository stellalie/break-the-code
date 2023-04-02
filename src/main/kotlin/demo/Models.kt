package demo

enum class Color {
    BLACK,
    GREEN,
    WHITE
}

abstract class Tile(
    val number: Int,
    val color: Color
) {
    override fun toString() = "$number${color.toString().first()}"
}

object BLACK_0 : Tile(0, Color.BLACK)
object WHITE_0: Tile(0, Color.WHITE)

object BLACK_1 : Tile(1, Color.BLACK)
object WHITE_1: Tile(1, Color.WHITE)

object BLACK_2 : Tile(2, Color.BLACK)
object WHITE_2: Tile(2, Color.WHITE)

object BLACK_3 : Tile(3, Color.BLACK)
object WHITE_3: Tile(3, Color.WHITE)

object BLACK_4 : Tile(4, Color.BLACK)
object WHITE_4: Tile(4, Color.WHITE)

object GREEN_5a : Tile(5, Color.GREEN)
object GREEN_5b: Tile(5, Color.GREEN)

object BLACK_6 : Tile(6, Color.BLACK)
object WHITE_6: Tile(6, Color.WHITE)

object BLACK_7 : Tile(7, Color.BLACK)
object WHITE_7: Tile(7, Color.WHITE)

object BLACK_8 : Tile(8, Color.BLACK)
object WHITE_8: Tile(8, Color.WHITE)

object BLACK_9 : Tile(9, Color.BLACK)
object WHITE_9: Tile(9, Color.WHITE)


object TwoPlayerSetup {
    val cards = setOf(
        // Black
        BLACK_0, BLACK_1, BLACK_2, BLACK_3, BLACK_4, GREEN_5a, BLACK_6, BLACK_7, BLACK_8, BLACK_9,
        WHITE_0, WHITE_1, WHITE_2, WHITE_3, WHITE_4, GREEN_5b, WHITE_6, WHITE_7, WHITE_8, WHITE_9
    ).sort()
}

fun Collection<Tile>.filterByColor(value: Color): Set<Tile> {
    return this.filter { it.color == value }.toSet()
}

fun Collection<Tile>.sum() = this.sumOf { n -> n.number }

fun Collection<Tile>.sort() = this.sortedWith(compareBy(Tile::number, Tile::color))