/* Functions of this file are used to
 * run algorithms on testcases
 */

import algo.Algo
import algo.Optimal

// Asks one query to algorithm and modifies memory corresponding way
fun runOneIteration(algorithm: Algo, query: Int, memory: MutableList<Int>): Int {
    val pos = algorithm.get(query)
    if (pos == 0) {
        if (!memory.contains(query))
            throw Exception("Algo ${algorithm.name} said that cell is in memory while it is not") // shouldn't happen with default algorithms
        return pos
    }
    if (pos < 1 || pos > algorithm.memorySize)
        throw Exception("Algo ${algorithm.name} returned incorrect cell") // shouldn't happen with default algorithms
    memory[pos] = query
    return pos
}

// Runs one algorithm on one case and returns all its answers
fun run(algorithm: Algo, queries: List<Int>): Log {
    val memory = MutableList(algorithm.memorySize + 1) { 0 }
    if (algorithm is Optimal)
        algorithm.initQueries(queries)
    return Log(algorithm.name, queries.map { runOneIteration(algorithm, it, memory) })
}
