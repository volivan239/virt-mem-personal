/* This is the main file of
 * VirtualMemory project that can be used
 * to compare cache replacements algorithms
 */

import java.io.File
import java.io.OutputStreamWriter

// Stores one testcase (= list of queries) on which algorithms will be tested
data class Case(var maxPage: Int = 0, var memorySize: Int = 0, var queries: List<Int> = emptyList())

// Stores output (answers) of algorithm (algoName) on one testcase
data class Log(var algoName: String, var answers: List<Int>, var badQueries: Int = answers.count { it != 0 })

// Stores additional parameters:
// * outputPrefix - directory in which output files will be saved
// * showChart - whether program will build a chart for algorithms or not
data class Settings(var outputPrefix: String = "", var showChart: Boolean = false)

// Launches all available algorithms on one testcase and share results:
// prints them to stdout, to outputWriter (output File) and build charts (if asked)
fun processCase(fileName: String, outputWriter: OutputStreamWriter, case: Case?, caseNumber: Int, settings: Settings) {
    if (case == null) {
        printMessage(outputWriter, "Case #${caseNumber} was skipped due to being invalid\n")
        return
    }
    val algorithms = getAlgorithms(case.maxPage, case.memorySize)
    val results: List<Log>
    try {
        results = algorithms.map { run(it, case.queries) }
    } catch (e: Exception) {
        printMessage(outputWriter, "Case #${caseNumber}: One or more algorithms had incorrect behaviour on testcase\n")
        return
    }
    printLog(outputWriter, caseNumber, case.maxPage, case.memorySize, case.queries.size, results)
    printResults(caseNumber, case.maxPage, case.memorySize, case.queries.size, results)
    if (settings.showChart)
        displayChart(fileName, results, caseNumber)
}

// Runs all algorithms on all cases for one file (fileName)
fun processFile(fileName: String, settings: Settings) {
    println("\nFrom file $fileName:")
    val cases = readCases(fileName)
    val outputWriter = File(getOutputFileName(fileName, settings)).writer()
    cases.forEachIndexed { ind, case -> processCase(fileName, outputWriter, case, ind + 1, settings) }
    outputWriter.close()
}

fun main(args: Array<String>) {
    println("To see all the information about algorithms' output, see .out files\n")
    val settings = Settings()
    val files = applyArgs(args, settings)
    files.forEach { processFile(it.toString(), settings) }
}