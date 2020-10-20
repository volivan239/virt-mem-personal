package algo

// Class is used to represent replacement algorithm with main function `get`
open class Algo(var maxPage: Int, var memorySize: Int, open var name: String) {
    open var memory = MutableList(memorySize + 1) { 0 }
    open var positionInMemory = MutableList(maxPage + 1) { -1 }

    // Sets pageNumber in memory and returns cellNumber
    open fun set(cellNumber: Int, pageNumber: Int): Int {
        if (memory[cellNumber] != -1)
            positionInMemory[memory[cellNumber]] = -1
        memory[cellNumber] = pageNumber
        positionInMemory[pageNumber] = cellNumber
        return cellNumber
    }

    // Returns number of frame in which page will be replaced or 0 if page is already in memory
    open fun get(page: Int): Int {
        return 0
    }
}