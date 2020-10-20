package algo

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class OptimalTest {

    @Test
    fun testOptimal() {
        val algo = Optimal(10, 3)
        val queries = listOf(1, 2, 3, 4, 3, 5, 6, 1, 2)
        val expected = listOf(1, 2, 3, 2, 0, 2, 2, 0, 1)
        algo.initQueries(queries)
        // {0, 0, 0} -> {1, 0, 0} -> {1, 2, 0} -> {1, 2, 3} -> {1, 4, 3} ->
        // -> {1, 4, 3} -> {1, 5, 3} -> {1, 6, 3} -> {1, 6, 3} -> {2, 6, 3}
        assertEquals(expected, queries.map { algo.get(it) })
    }
}