package algo

import java.util.*

class Lru(maxPage: Int, memorySize: Int, name: String = "LRU") : Algo(maxPage, memorySize, name){
    var lastAsked = MutableList(maxPage + 1) { -1 }    // time page was asked last time

    var pastQueries: Queue <Pair<Int, Int>> = (LinkedList(List(memorySize) {Pair(it + 1, -1)}))
    // This queue stores all known queries as Pairs <Memory cell with page asked, Time of query> sorted by time
    // If queue.peek() was asked any time after that query, we can delete it
    // If not - then it contains LRU element
    var time = 0
    override fun get(page: Int): Int {
        lastAsked[page] = time++
        if (positionInMemory[page] != -1) {
            pastQueries.add(Pair(positionInMemory[page], time - 1))
            return 0
        }
        val res: Int
        while (true) {
            val (pos, time) = pastQueries.peek()
            if (memory[pos] == -1 || lastAsked[memory[pos]] == time) {
                res = pos
                break
            }
            pastQueries.remove()
        }
        pastQueries.add(Pair(res, time - 1))
        return set(res, page)
    }
}