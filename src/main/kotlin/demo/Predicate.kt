package demo

object Predicate {
    // Has tile
    fun hasTile(pos: Char, value: Int) = hasTile(pos.toPos(), value)
    private fun hasTile(pos: Int, value: Int): (t: List<Tile>) -> Boolean =
        { it -> it[pos].number == value && it.filter { it.number == value }.size == 1 }


    // Consecutive
    fun consecutive(value: String) = consecutive(value.toPosList())
    private fun consecutive(value: List<List<Int>>): (t: List<Tile>) -> Boolean = {
        groupConsecutiveList(it.map(Tile::number)).filter { it.size > 1 } == value.map { ns -> ns.map { i -> it[i].number } }
    }

    fun noConsecutive(): (t: List<Tile>) -> Boolean = {
        groupConsecutiveList(it.map(Tile::number)).none { it.size > 1 }
    }

    private fun groupConsecutiveList(list: List<Int>): List<List<Int>> {
        return list.fold(mutableListOf<MutableList<Int>>()) { acc, i ->
            if (acc.isEmpty() || acc.last().last() != i - 1) {
                acc.add(mutableListOf(i))
            } else acc.last().add(i)
            acc
        }
    }

    // Neighbouring color
    fun neighbouringSameColor(value: String) = neighbouringSameColor(value.toPosList())

    private fun neighbouringSameColor(value: List<List<Int>>): (t: List<Tile>) -> Boolean = { numbers ->
        groupConsecutiveColours(numbers.map(Tile::color)).filter { it.size > 1 } == value.map { ns -> ns.map { i -> numbers[i].color } }
    }

    fun noNeighbouringSameColor(): (t: List<Tile>) -> Boolean = {
        groupConsecutiveColours(it.map(Tile::color)).none { it.size > 1 }
    }

    private fun groupConsecutiveColours(list: List<Color>): List<List<Color>> {
        return list.fold(mutableListOf<MutableList<Color>>()) { acc, i ->
            if (acc.isEmpty() || acc.last().last() != i) {
                acc.add(mutableListOf(i))
            } else acc.last().add(i)
            acc
        }
    }
}