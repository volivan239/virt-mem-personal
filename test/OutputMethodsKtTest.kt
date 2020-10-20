import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class OutputMethodsKtTest {

    @Test
    fun getOutputFileName() {
        val fileName = "data/samples/inp001.txt"
        assertEquals("data/samples/inp001.out", getOutputFileName(fileName, Settings()))
    }

}