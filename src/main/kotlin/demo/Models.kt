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
    override fun toString() = when (color) {
        Color.BLACK -> "$ANSI_BLACK_BACKGROUND$ANSI_WHITE $number $ANSI_RESET"
        Color.WHITE -> "$ANSI_WHITE_BACKGROUND$ANSI_BLACK $number $ANSI_RESET"
        Color.GREEN -> "$ANSI_GREEN $number $ANSI_RESET"
    }
}

object BLACK_0 : Tile(0, Color.BLACK)
object WHITE_0 : Tile(0, Color.WHITE)

object BLACK_1 : Tile(1, Color.BLACK)
object WHITE_1 : Tile(1, Color.WHITE)

object BLACK_2 : Tile(2, Color.BLACK)
object WHITE_2 : Tile(2, Color.WHITE)

object BLACK_3 : Tile(3, Color.BLACK)
object WHITE_3 : Tile(3, Color.WHITE)

object BLACK_4 : Tile(4, Color.BLACK)
object WHITE_4 : Tile(4, Color.WHITE)

object GREEN_5a : Tile(5, Color.GREEN)
object GREEN_5b : Tile(5, Color.GREEN)

object BLACK_6 : Tile(6, Color.BLACK)
object WHITE_6 : Tile(6, Color.WHITE)

object BLACK_7 : Tile(7, Color.BLACK)
object WHITE_7 : Tile(7, Color.WHITE)

object BLACK_8 : Tile(8, Color.BLACK)
object WHITE_8 : Tile(8, Color.WHITE)

object BLACK_9 : Tile(9, Color.BLACK)
object WHITE_9 : Tile(9, Color.WHITE)


object TwoPlayerSetup {
    val cards = listOf(
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

const val ANSI_RESET = "\u001B[0m"
const val ANSI_BLACK = "\u001B[30m"
const val ANSI_RED = "\u001B[31m"
const val ANSI_GREEN = "\u001B[32m"
const val ANSI_YELLOW = "\u001B[33m"
const val ANSI_BLUE = "\u001B[34m"
const val ANSI_PURPLE = "\u001B[35m"
const val ANSI_CYAN = "\u001B[36m"
const val ANSI_WHITE = "\u001B[37m"
const val ANSI_BLACK_BACKGROUND = "\u001B[40m"
const val ANSI_RED_BACKGROUND = "\u001B[41m"
const val ANSI_GREEN_BACKGROUND = "\u001B[42m"
const val ANSI_YELLOW_BACKGROUND = "\u001B[43m"
const val ANSI_BLUE_BACKGROUND = "\u001B[44m"
const val ANSI_PURPLE_BACKGROUND = "\u001B[45m"
const val ANSI_CYAN_BACKGROUND = "\u001B[46m"
const val ANSI_WHITE_BACKGROUND = "\u001B[47m"