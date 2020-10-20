/* Functions in this file are used to generate
 * data for further usage
 */

// Returns random value in (0..1) using normal distribution
fun gaussRandom(): Double {
    var x = 3.0                  // initial value to get inside while
    while (x < -2 || x > 2)      // we use only one interval of normal distribution
        x = java.util.Random().nextGaussian()
    return (x + 2) / 4
}

// Returns numOfQueries integers in range 1..maxPage using rnd() function (rnd() should return [0..1) value)
fun genRandom(maxPage: Int, numOfQueries: Int, rnd: ()->Double): List<Int> {
    return (1..numOfQueries).map {(rnd() * maxPage).toInt() + 1}
}

// Returns list of all available algorithms initialized with correct maxPage and memorySize
fun getAlgorithms(maxPage: Int, memorySize: Int): List<algo.Algo> {
    return listOf(algo.Optimal(maxPage, memorySize), algo.Fifo(maxPage, memorySize), algo.Lru(maxPage, memorySize))
}