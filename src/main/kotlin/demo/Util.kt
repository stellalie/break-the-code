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
fun String.toTiles(): Set<Tile> {
    val tiles = this.split(" ").map {
        when (it) {
            "5" -> GREEN_5a
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
    return tiles.toSet()
}