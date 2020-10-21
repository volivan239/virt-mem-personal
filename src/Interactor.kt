/* Functions of this file are used to
 * run algorithms on testcases
 */

import algo.Algo
import algo.Optimal
import kotlin.math.min

fun run(algorithms: List<Algo>, case: Case) {
    for (i in 0 until case.numOfQueries step case.generationDepth) {
        val length = min(case.generationDepth, case.numOfQueries - i)
        val queries = (1..length).map { genRandom(case.maxPage) }
        algorithms.forEach { if (it is Optimal) it.initQueries(queries.toList()) }
        println("+------+------+----------+----------+----------+")
        for (num in queries.indices) {
            algorithms.forEach { it.result.add(it.get(queries[num])) }
            printResults(i + num + 1, queries[num], algorithms)
        }
    }
}