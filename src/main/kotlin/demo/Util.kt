package demo

/**
 * Transform "ab cd" to -> listOf(listOf(0, 1), listOf(2, 3))
 */
fun String.toPosList(): List<List<Int>> {
    return this.split(" ").map { it.map { it.toPos() } }
}

/**
 * Transform "a" to 0, "b" to 1
 */
fun Char.toPos(): Int {
    return when (this) {
        'a' -> 0
        'b' -> 1
        'c' -> 2
        'd' -> 3
        'e' -> 4
        else -> throw IllegalArgumentException("Invalid pos $this")
    }
}

/**
 * Transform string such "0b 5 1b 2b 3b" to its respective tile object
 */
fun String.toTilesSet(): Set<Tile> = this.toTilesList().toSet()

fun String.toTilesList(): List<Tile> = this.split(" ").map {
    when (it) {
        // greens
        // and, this shouldn't matter. We would know where to look if there's flakiness
        "5" -> listOf(GREEN_5a, GREEN_5b).shuffled()[0]
        // blacks
        "0b" -> BLACK_0
        "1b" -> BLACK_1
        "2b" -> BLACK_2
        "3b" -> BLACK_3
        "4b" -> BLACK_4
        "6b" -> BLACK_6
        "7b" -> BLACK_7
        "8b" -> BLACK_8
        "9b" -> BLACK_9
        // whites
        "0w" -> WHITE_0
        "1w" -> WHITE_1
        "2w" -> WHITE_2
        "3w" -> WHITE_3
        "4w" -> WHITE_4
        "6w" -> WHITE_6
        "7w" -> WHITE_7
        "8w" -> WHITE_8
        "9w" -> WHITE_9
        else -> throw IllegalArgumentException("Invalid tile $this")
    }
}

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