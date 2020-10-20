import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class InitKtTest {

    @Test
    fun testGauss() {
        for (i in 0..10000) {
            val x = gaussRandom()
            assertTrue(x in 0.0..1.0)
        }
    }

    @Test
    fun testGenRandom() {
        val maxPage = 30
        val queries = genRandom(maxPage, 1000) { java.util.Random().nextDouble() }
        for (q in queries)
            assertTrue(q in 1..maxPage)
    }
}