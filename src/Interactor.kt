/* Functions of this file are used to
 * run algorithms on testcases
 */

import algo.Algo
import algo.Optimal
import java.util.*
import kotlin.math.min

fun run(algorithms: List<Algo>, case: Case, rnd: () -> Double) {
    val queries: Queue<Int> = LinkedList()
    for (i in 0 until case.numOfQueries) {
        while (queries.size < case.generationDepth) {
            val query = (rnd() * case.maxPage).toInt() + 1
            algorithms.forEach { if (it is Optimal) it.addQuery(query) }
            queries.add(query)
        }
        val query = queries.peek()
        queries.remove()
        algorithms.forEach { it.result.add(it.get(query)) }
        printResults(i + 1, query, algorithms)
    }
}