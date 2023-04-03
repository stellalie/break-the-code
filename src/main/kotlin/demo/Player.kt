package demo

class Player(val name: String, val cardsCombinations: MutableList<out List<Tile>>) {
    override fun toString(): String {
        val distinctMap = (0..4).map { columnIndex -> cardsCombinations.map { it[columnIndex] }.distinct() }
            .mapIndexed { i, tile -> "$i: $tile" }
        return "[$name]: \n" +
                cardsCombinations.joinToString("\n") + "\n\n" +
                "Distinct: \n" +
                distinctMap.joinToString("\n") + "\n\n" +
                "Count: ${cardsCombinations.size}"
    }

    fun hasTile(pos: Char, value: Int) = cardsCombinations.retainAll(Predicate.hasTile(pos, value))

    fun hasTiles(pos1: Int, pos2: Int, value: Int) =
        cardsCombinations.removeIf { !(it[pos1].number == value && it[pos2].number == value) }

    fun hasTiles(pos1: Char, pos2: Char, value: Int) = hasTiles(pos1.toPos(), pos2.toPos(), value)

    fun noTile(value: Int) = cardsCombinations.removeIf { it.any { n -> n.number == value } }

    fun tileCGreaterThan4() = cardsCombinations.removeIf { it[2].number <= 4 }

    fun tileCIsNotGreaterThan4() = cardsCombinations.removeIf { it[2].number > 4 }

    fun sumOfTiles(value: Int) = cardsCombinations.removeIf { it.sum() != value }

    fun sumOfCentral(value: Int) = cardsCombinations.removeIf { it.slice(1..3).sum() != value }

    fun sumOfLeftMost(value: Int) = cardsCombinations.removeIf { it.take(3).sum() != value }

    fun sumOfRightMost(value: Int) = cardsCombinations.removeIf { it.slice(2..4).sum() != value }

    fun countOfBlack(value: Int) = cardsCombinations.removeIf { it.filterByColor(Color.BLACK).size != value }

    fun countOfWhite(value: Int) = cardsCombinations.removeIf { it.filterByColor(Color.WHITE).size != value }

    fun sumOfBlack(value: Int) = cardsCombinations.removeIf { it.filterByColor(Color.BLACK).sum() != value }

    fun sumOfWhite(value: Int) = cardsCombinations.removeIf { it.filterByColor(Color.WHITE).sum() != value }

    fun maxMinGap(value: Int) = cardsCombinations.removeIf { it.last().number - it.first().number != value }

    fun countOfOdd(value: Int) = cardsCombinations.removeIf { it.filter { n -> n.number % 2 != 0 }.size != value }

    fun countOfEven(value: Int) = cardsCombinations.removeIf { it.filter { n -> n.number % 2 == 0 }.size != value }

    fun hasSameNumber(value: Int) {
        cardsCombinations.removeIf {
            val countMap = it.groupingBy { it.number }.eachCount()
            countMap.toList().filter { it.second >= 2 }.size != value
        }
    }

    fun noNeighbouringSameColor() = cardsCombinations.retainAll(Predicate.noNeighbouringSameColor())

    fun neighbouringSameColor(value: String) = cardsCombinations.retainAll(Predicate.neighbouringSameColor(value))

    fun noConsecutive() = cardsCombinations.retainAll(Predicate.noConsecutive())

    fun consecutive(value: String) = cardsCombinations.retainAll(Predicate.consecutive(value))
}