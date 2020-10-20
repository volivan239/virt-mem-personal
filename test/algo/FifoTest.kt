package algo

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class FifoTest {

    @Test
    fun testFifo() {
        val alg = Fifo(10, 3)
        assertEquals(1, alg.get(1)) // {0, 0, 0} -> {1, 0, 0}
        assertEquals(0, alg.get(1)) // {1, 0, 0} -> {1, 0, 0}
        assertEquals(2, alg.get(2)) // {1, 0, 0} -> {1, 2, 0}
        assertEquals(3, alg.get(3)) // {1, 2, 0} -> {1, 2, 3}
        assertEquals(0, alg.get(2)) // {1, 2, 3} -> {1, 2, 3}
        assertEquals(1, alg.get(4)) // {1, 2, 3} -> {4, 2, 3}
        assertEquals(2, alg.get(1)) // {4, 2, 3} -> {4, 1, 3}
        assertEquals(3, alg.get(2)) // {4, 1, 3} -> {4, 1, 2}
        assertEquals(1, alg.get(3)) // {4, 1, 2} -> {3, 1, 2}
        assertEquals(0, alg.get(1)) // {3, 1, 2} -> {3, 1, 2}
    }
}