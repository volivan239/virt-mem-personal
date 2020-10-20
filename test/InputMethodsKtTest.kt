import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class InputMethodsKtTest {

    @Test
    fun parseFirstLine() {
        val line = "10 20 30"
        assertEquals(Case(10, 20, List(30) {0}), parseFirstLine(line, 1))
    }
    @Test
    fun parseFirstLineNull() {
        val line = "10 abacaba"
        assertNull(parseFirstLine(line, 1))
    }

    @Test
    fun parseQueries() {
        val queries = listOf("23", "32", "abc", "--")
        val expected = listOf(23, 32)
        assertEquals(expected, parseQueries(42, 2, queries, 1))
    }
    @Test
    fun parseQueriesWrongNumber() {
        val queries = listOf("23", "32", "abc", "--")
        assertNull(parseQueries(42, 3, queries, 1))
    }
    @Test
    fun parseQueriesWrongRange() {
        val queries = listOf("23", "32", "abc", "--")
        assertNull(parseQueries(31, 2, queries, 1))
    }

    @Test
    fun parseSecondLineGauss() {
        val secondLine = "g   "
        assertEquals(100, parseSecondLine(10, 100, secondLine, 1)?.size)
    }
    @Test
    fun parseSecondLineManual() {
        val secondLine = "m 2, 3,   4 7 [end]"
        val expected = listOf(2, 3, 4, 7)
        assertEquals(expected, parseSecondLine(10, 4, secondLine, 1))
    }

    @Test
    fun parseCase() {
        val firstLine = "5  6  7 "
        val secondLine = "m 5, 4, 3, 2, 1 2 3 end of test"
        val expected = Case(5, 6, listOf(5, 4, 3, 2, 1, 2, 3))
        assertEquals(expected, parseCase(firstLine, secondLine, 1))
    }
    @Test
    fun parseCaseWithNoTestsNumber() {
        val firstLine = "5 6"
        val secondLine = "m 5 4 3 2 1 2 3"
        val expected = Case(5, 6, listOf(5, 4, 3, 2, 1, 2, 3))
        assertEquals(expected, parseCase(firstLine, secondLine, 1))
    }
    @Test
    fun parseTestEmpty() {
        val firstLine = "5 6"
        val secondLine = "g"
        assertNull(parseCase(firstLine, secondLine, 1))
    }
    @Test
    fun readCases() {
        val cases = readCases("data/samples/inp001.txt")
        assertEquals(3, cases.size)
        assertEquals(10000, cases[0]?.queries?.size)
        val secondCase = Case(10, 3, listOf(1, 2, 3, 4, 2, 3, 1, 4, 2, 5))
        assertEquals(secondCase, cases[1])
        assertEquals(10000, cases[2]?.queries?.size)
    }
}