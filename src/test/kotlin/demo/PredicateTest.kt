package demo

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PredicateTest {
    data class TestData(
        val input: String,
        val expected: Boolean
    )

    companion object {
        @JvmStatic
        private fun testHasTileProvider() = Stream.of(
            TestData(input = "0b 0w 1b 2w 5", expected = true),
            TestData(input = "0b 0w 1w 2w 5", expected = true),
            TestData(input = "0w 1w 1b 1w 5", expected = false)
        )

        @JvmStatic
        private fun testConsecutive() = Stream.of(
            TestData(input = "2b 4b 5 7w 9b", expected = true),
            TestData(input = "2b 4w 5 7w 9b", expected = true),
            TestData(input = "2b 4b 5 6w 8b", expected = false),
            TestData(input = "2b 4b 5 6b 8b", expected = false),
            TestData(input = "3b 4b 5 7w 8b", expected = false),
            TestData(input = "0b 2b 3w 4w 9b", expected = false)
        )

        @JvmStatic
        private fun testNoConsecutive() = Stream.of(
            TestData(input = "2b 4b 6b 8w 8b", expected = true),
            TestData(input = "0b 0w 5 7w 9b", expected = true),
            TestData(input = "0b 2b 3w 4w 9b", expected = false)
        )

        @JvmStatic
        private fun testNeighbouringSameColor() = Stream.of(
            TestData(input = "2b 3w 4b 7b 9w", expected = true),
            TestData(input = "2w 4b 5 5 9b", expected = true),
            TestData(input = "2b 4b 5 5 9b", expected = false)
        )

        @JvmStatic
        private fun testNoNeighbouringSameColor() = Stream.of(
            TestData(input = "2b 3w 4b 7w 9b", expected = true),
            TestData(input = "2w 3w 4b 7w 9b", expected = false),
            TestData(input = "2w 4b 5 5 9b", expected = false)
        )
    }

    @ParameterizedTest
    @MethodSource("testHasTileProvider")
    fun testHasTile(data: TestData) {
        smartAssert(Predicate.hasTile('c', 1)(data.input.toTilesList()), data.expected)
    }

    @ParameterizedTest
    @MethodSource("testConsecutive")
    fun testConsecutive(data: TestData) {
        smartAssert(Predicate.consecutive("bc")(data.input.toTilesList()), data.expected)
    }

    @ParameterizedTest
    @MethodSource("testNoConsecutive")
    fun testNoConsecutive(data: TestData) {
        smartAssert(Predicate.noConsecutive()(data.input.toTilesList()), data.expected)
    }

    @ParameterizedTest
    @MethodSource("testNeighbouringSameColor")
    fun testNeighbouringSameColor(data: TestData) {
        smartAssert(Predicate.neighbouringSameColor("cd")(data.input.toTilesList()), data.expected)
    }

    @ParameterizedTest
    @MethodSource("testNoNeighbouringSameColor")
    fun testNoNeighbouringSameColor(data: TestData) {
        smartAssert(Predicate.noNeighbouringSameColor()(data.input.toTilesList()), data.expected)
    }

    private fun smartAssert(actual: Boolean, expected: Boolean) {
        when (expected) {
            true -> assertTrue(actual)
            false -> assertFalse(actual)
        }
    }
}