/* Functions of this file are used to
 * print all the data to output files
 * and standard output
 */

import algo.Algo

fun printResults(queryNumber: Int, query: Int, algorithms: List<Algo>) {
    assert(algorithms.size == 3)
    println("| %-4s | %-4s | %-8s | %-8s | %-8s |".format(queryNumber, query, algorithms[0].result.get(), algorithms[1].result.get(), algorithms[2].result.get()))
}
