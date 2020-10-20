import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

internal class IntegrationTest {

    fun check(testNumber: Int) {
        val strNumber = testNumber.toString().padStart(3, '0')
        assertEquals(File("testData/expected${strNumber}.out").readLines(), File("testData/test${strNumber}.out").readLines())
    }

    @Test
    fun smallestCasesIntegrationTest() {
        main(arrayOf("testData/test001.txt"))
        check(1)
    }
    @Test
    fun wrongCasesIntegrationTest() {
        main(arrayOf("testData/test002.txt"))
        check(2)
    }
    @Test
    fun wrongFileIntegrationTest() {
        main(arrayOf("testData/test003.jpg"))
        check(3)
    }
    @Test
    fun midRandomCasesIntegrationTest() {
        main(arrayOf("testData/multiTest", "-output-prefix=testData"))
        check(4)
        check(5)
    }
    @Test
    fun smallRandomCasesIntegrationTest() {
        main(arrayOf("testData/test006.txt"))
        check(6)
    }
    @Test
    fun bigRandomCasesIntegrationTest() {
        main(arrayOf("testData/test007.txt"))
        check(7)
    }
    @Test
    fun weirdCasesIntegrationTest() {
        main(arrayOf("testData/test008.txt", "-output-prefix=testData/"))
        check(8)
    }
}