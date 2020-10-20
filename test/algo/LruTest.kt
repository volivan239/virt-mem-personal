package algo

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class LruTest {

    @Test
    fun testLRU() {
        val alg = Lru(10, 3)
        assertEquals(1, alg.get(1)) // {0, 0, 0} -> {1, 0, 0}
        assertEquals(0, alg.get(1)) // {1, 0, 0} -> {1, 0, 0}
        assertEquals(2, alg.get(2)) // {1, 0, 0} -> {1, 2, 0}
        assertEquals(3, alg.get(3)) // {1, 2, 0} -> {1, 2, 3}
        assertEquals(0, alg.get(1)) // {1, 2, 3} -> {1, 2, 3}
        assertEquals(2, alg.get(4)) // {1, 2, 3} -> {1, 4, 3}
        assertEquals(0, alg.get(1)) // {1, 4, 3} -> {1, 4, 3}
        assertEquals(3, alg.get(2)) // {1, 4, 3} -> {1, 4, 2}
        assertEquals(2, alg.get(3)) // {1, 4, 2} -> {1, 3, 2}
        assertEquals(1, alg.get(5)) // {1, 3, 2} -> {5, 3, 2}
    }
}