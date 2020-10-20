/* Functions of this file are used to
 * read all the data needed from input files
 * The only function that is used outside of
 * this file is `readCases`
 * First/Second line of input == first/second line for each case in input
 */

import java.io.File

// Converts first line of input to Case with correct maxPage, memorySize and numOfQueries (== queries.size) but incorrect queries
// or returns null if line was incorrect
fun parseFirstLine(line: String, caseNumber: Int): Case? {
    val numbers = line.split(" ").mapNotNull { it.toIntOrNull() }
    if (numbers.size < 2) {
        println("Case #$caseNumber: $line is not a correct testcase: skipping")
        return null
    }
    val (maxPage, memorySize) = numbers.take(2)
    var numOfQueries = 0
    if (numbers.size > 2)
        numOfQueries = numbers[2]
    if (maxPage <= 0 || memorySize <= 0 || numOfQueries < 0) {
        println("Case #$caseNumber: $line is not a correct testcase: skipping")
        return null
    }
    return Case(maxPage, memorySize, List(numOfQueries) { 0 })
}

// Converts sequence of queries from second line of input to list of integers
fun parseQueries(maxPage: Int, numOfQueries: Int, sequence: List<String>, caseNumber: Int): List<Int>? {
    val queries = sequence.mapNotNull { it.toIntOrNull() }       // all non-integers words are ignored
    if (numOfQueries != 0 && queries.size != numOfQueries) {
        println("Case #$caseNumber: $numOfQueries queries were declared, but ${queries.size} where provided, skipping")
        return null
    }
    if (queries.any { it !in 1..maxPage } ) {
        println("Case #$caseNumber: Some queries are out of specified range, skipping")
        return null
    }
    return queries
}

// Converts second line of input to a list of queries or returns null if input was incorrect
fun parseSecondLine(maxPage: Int, numOfQueries: Int, line: String, caseNumber: Int): List<Int>? {
    val seq = line.split(" ", ",", ";").filter{ it != "" }
    if (seq.isEmpty()) {
        println("Case #$caseNumber: no sequence specified")
        return null
    }
    return when (seq[0]) {
        "r" -> genRandom(maxPage, numOfQueries) { java.util.Random().nextDouble() }
        "g" -> genRandom(maxPage, numOfQueries) { gaussRandom() }
        "m" -> parseQueries(maxPage, numOfQueries, seq, caseNumber)
        else -> {
            println("Case #$caseNumber: was not specified correct sequence: skipping")
            null
        }
    }
}

// Converts both lines of input to a testcase or returns null if input was incorrect
fun parseCase(firstLine: String, secondLine: String, caseNumber: Int): Case? {
    val res: Case = parseFirstLine(firstLine, caseNumber) ?: return null
    val queries = parseSecondLine(res.maxPage, res.queries.size, secondLine, caseNumber) ?: return null
    if (queries.isEmpty()) {
        println("Case #$caseNumber: empty sequence, skipping")
        return null
    }
    res.queries = queries
    return res
}

// Reads all cases from input file (FileName)
fun readCases(fileName: String): List<Case?> {
    val res = mutableListOf<Case?>()
    val input = File(fileName).readLines().filter{ it != "" }
    var nextInterestingIndex = 0   // index of line on which next testcase is expected to start
    for (i in input.indices) {
        if (input[i].filter { it != ' ' }.startsWith("#")) // # is a sign of new testcase
            nextInterestingIndex = i + 1
        if (i != nextInterestingIndex || i + 1 == input.size)
            continue
        val case = parseCase(input[i], input[i + 1], res.size + 1)
        res.add(case)
        nextInterestingIndex = i + 2
    }
    return res
}