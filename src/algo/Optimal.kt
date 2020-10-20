package algo

import java.util.*

class Optimal(maxPage: Int, memorySize: Int, name: String = "OPT") : Algo(maxPage, memorySize, name){
    var queries = emptyList<Int>()
    var nxt = MutableList<Queue<Int>>(maxPage + 1) { LinkedList() } // For each page stores queue with queries asking that page

    // initQueries should be called once before any get() to let algorithm know the queries
    fun initQueries(queries: List<Int>) {
        this.queries = queries
        for (i in queries.indices)
            nxt[queries[i]].add(i)
        for (i in 0..maxPage)
            nxt[i].add(queries.size + 1) // == INF time of next query
    }

    override fun get(page: Int): Int {
        nxt[page].remove()
        if (positionInMemory[page] != -1)
            return 0
        var best = 1
        for (i in 2..memorySize) {
            if (nxt[memory[best]].peek() < nxt[memory[i]].peek())
                best = i
        }
        return set(best, page)
    }
}