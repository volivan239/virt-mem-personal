import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

internal class ArgsParserKtTest {
    val settings = Settings()
    @Test
    fun applyArgShowChart() {
        applyArg("--show-chart", settings)
        assertEquals(true, settings.showChart)
    }
    @Test
    fun applyArgOutputPrefix() {
        applyArg("-output-prefix=data/samples/", settings)
        assertEquals("data/samples/", settings.outputPrefix)
    }
    @Test
    fun applyArgOutputPrefixWithoutSlash() {
        applyArg("-output-prefix=data/samples", settings)
        assertEquals("data/samples/", settings.outputPrefix)
    }
    @Test
    fun applyArgWithError() {
        applyArg("--random-argument", settings)
        assertEquals(settings, Settings())
    }
    @Test
    fun getFiles() {
        val fileNames = listOf("data/samples/inp001.txt", "data/samples/abc239.txt", "data/samples/inp002.txt")
        val files = listOf(File("data/samples/inp001.txt"), File("data/samples/inp002.txt"))
        assertEquals(files, getFiles(fileNames))
    }

    @Test
    fun applyEmptyArgs() {
        val files = applyArgs(arrayOf("--some-arg", "data/samples/abc239.txt"), settings)
        assertEquals(files, emptyList<File>())
    }

}