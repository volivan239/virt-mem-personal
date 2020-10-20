package algo

class Fifo(maxPage: Int, memorySize: Int, name: String = "FIFO") : Algo(maxPage, memorySize, name){
    var toChange = 1  // number of page to be changed next

    override fun get(page: Int): Int {
        if (positionInMemory[page] != -1)
            return 0
        val res = toChange
        toChange += 1
        if (toChange == memorySize + 1)
            toChange = 1
        return set(res, page)
    }
}