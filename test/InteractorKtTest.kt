import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class InteractorKtTest {

    @Test
    fun runLRU() {
        val queries = listOf(1, 1, 2, 3, 1, 4, 1, 2, 3, 5)
        val expected = Log("LRU", listOf(1, 0, 2, 3, 0, 2, 0, 3, 2, 1))
        assertEquals(expected, run(algo.Lru(10, 3), queries))
    }
    @Test
    fun runOPT() {
        val queries = listOf(1, 2, 3, 4, 3, 5, 6, 1, 2)
        val expected = Log("OPT", listOf(1, 2, 3, 2, 0, 2, 2, 0, 1))
        assertEquals(expected, run(algo.Optimal(10, 3), queries))
    }
}