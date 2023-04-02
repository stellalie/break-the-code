package demo

enum class Color {
    BLACK,
    GREEN,
    WHITE
}

abstract class Number(
    val number: Int,
    val color: Color
) {
    override fun toString() = "$number${color.toString().first()}"
}

object BLACK_0 : Number(0, Color.BLACK)
object WHITE_0: Number(0, Color.WHITE)

object BLACK_1 : Number(1, Color.BLACK)
object WHITE_1: Number(1, Color.WHITE)

object BLACK_2 : Number(2, Color.BLACK)
object WHITE_2: Number(2, Color.WHITE)

object BLACK_3 : Number(3, Color.BLACK)
object WHITE_3: Number(3, Color.WHITE)

object BLACK_4 : Number(4, Color.BLACK)
object WHITE_4: Number(4, Color.WHITE)

object GREEN_5a : Number(5, Color.GREEN)
object GREEN_5b: Number(5, Color.GREEN)

object BLACK_6 : Number(6, Color.BLACK)
object WHITE_6: Number(6, Color.WHITE)

object BLACK_7 : Number(7, Color.BLACK)
object WHITE_7: Number(7, Color.WHITE)

object BLACK_8 : Number(8, Color.BLACK)
object WHITE_8: Number(8, Color.WHITE)

object BLACK_9 : Number(9, Color.BLACK)
object WHITE_9: Number(9, Color.WHITE)


object TwoPlayerSetup {
    val cards = setOf(
        // Black
        BLACK_0, BLACK_1, BLACK_2, BLACK_3, BLACK_4, GREEN_5a, BLACK_6, BLACK_7, BLACK_8, BLACK_9,
        WHITE_0, WHITE_1, WHITE_2, WHITE_3, WHITE_4, GREEN_5b, WHITE_6, WHITE_7, WHITE_8, WHITE_9
    ).sort()
}

fun Collection<Number>.filterByColor(value: Color): Set<Number> {
    return this.filter { it.color == value }.toSet()
}

fun Collection<Number>.sum() = this.sumOf { n -> n.number }

fun Collection<Number>.sort() = this.sortedWith(compareBy(Number::number, Number::color))