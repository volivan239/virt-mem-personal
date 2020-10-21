/* Functions of this file are used to
 * read all the data needed from input files
 * The only function that is used outside of
 * this file is `readCases`
 * First/Second line of input == first/second line for each case in input
 */

import java.io.File

// Converts both lines of input to a testcase or returns null if input was incorrect
fun parseCase(line: String): Case {
    val lexems = line.split(" ")
    val result = Case(lexems[0].toInt(), lexems[1].toInt(), lexems[2].toInt())
    if (lexems.size >= 4)
        result.generationDepth = lexems[3].toInt()
    return result
}

// Reads all cases from input file (FileName)
fun readCases(fileName: String): List<Case?> {
    val input = File(fileName).readLines().filter{ it != "" }
    val res = mutableListOf<Case?>()
    for (line in input) {
        try {
            res.add(parseCase(line))
        } catch (e: Exception) {
            println("Unable to parse Case $line, skipping")
            res.add(null)
        }
    }
    return res
}