/* Functions of this file are used to
 * print all the data to output files
 * and standard output
 */

import java.io.File
import java.io.OutputStreamWriter

// Returns name of file in which answer will be printed
fun getOutputFileName(fileName: String, settings: Settings): String {
    if (settings.outputPrefix == "")
        return fileName.substringBeforeLast(".") + ".out"
    return settings.outputPrefix + File(fileName).nameWithoutExtension + ".out"
}

// Prints brief summary of algorithms' results to stdout
fun printResults(caseNumber: Int, maxPage: Int, memorySize: Int, numOfQueries: Int, results: List<Log>) {
    val raw = "+---------+------------+------------+-----------+"
    println("\nCase #${caseNumber}: maxPage = $maxPage, memorySize = $memorySize, $numOfQueries queries\n")
    println(raw)
    println("| Algo    | Bad Queries| Rel.Score  | Norm.Score|")
    println(raw)
    val best = results.minOf { it.badQueries }
    for (i in results.indices) {
        val goodQueries = numOfQueries - results[i].badQueries
        val relScore = (goodQueries * 100 / numOfQueries).toString() + "%"
        val normScore = if (best == numOfQueries)
            "0%"
        else
            (goodQueries * 100 / (numOfQueries - best)).toString() + "%"
        println("| %-7s | %-10d | %-10s | %-10s|".format(results[i].algoName, results[i].badQueries, relScore, normScore))
    }
    println(raw)
}

// Prints detailed results of algorithms to specified file (outputWriter)
fun printLog(outputWriter: OutputStreamWriter, caseNumber: Int, maxPage: Int, memorySize: Int, numOfQueries: Int, results: List <Log>) {
    outputWriter.write("Case #$caseNumber: maxPage = $maxPage, memorySize = $memorySize, $numOfQueries queries\n\n")
    for (result in results) {
        outputWriter.write("Output of algo ${result.algoName}: (${result.badQueries} bad Queries out of $numOfQueries)\n")
        result.answers.forEach { outputWriter.write("$it ") }
        outputWriter.write("\n")
    }
    outputWriter.write("\n")
}

// Prints message that testCase was wrong to both stdout and output file
fun printMessage(outputWriter: OutputStreamWriter, msg: String) {
    outputWriter.write(msg)
    print(msg)
}