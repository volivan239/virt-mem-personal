/* This is the main file of
 * VirtualMemory project that can be used
 * to compare cache replacements algorithms
 */

data class AlgoResult(var lastValue: Int = -1, var totalQueries: Int = 0, var goodQueries: Int = 0) {
    fun getEfficiency() = (goodQueries * 100) / totalQueries

    fun add(value: Int) {
        lastValue = value
        totalQueries++
        if (value == 0)
            goodQueries++
    }

    fun get() = "$lastValue (${getEfficiency()}%)"
}

// Stores one testcase (= list of queries) on which algorithms will be tested
data class Case(var maxPage: Int = 0, var memorySize: Int = 0, var numOfQueries: Int = 0, var generationDepth: Int = 10)

// Launches all available algorithms on one testcase and share results:
// prints them to stdout, to outputWriter (output File) and build charts (if asked)
fun processCase(case: Case?, caseNumber: Int) {
    if (case == null) {
        println("Case #${caseNumber} was skipped due to being invalid\n")
        return
    }
    val algorithms = getAlgorithms(case.maxPage, case.memorySize)
    val raw = "+------+------+----------+----------+----------+"
    println("Case #${caseNumber}:")
    println(raw)
    println("|No.   |Query | OPT      | FIFO     | LRU      |")
    run(algorithms, case)
    println(raw)
    println()
}

// Runs all algorithms on all cases for one file (fileName)
fun processFile(fileName: String) {
    println("\nFrom file $fileName:")
    val cases = readCases(fileName)
    cases.forEachIndexed { ind, case -> processCase(case, ind + 1) }
}

fun main(args: Array<String>) {
    println("To see all the information about algorithms' output, see .out files\n")
    val files = applyArgs(args)
    files.forEach { processFile(it.toString()) }
}