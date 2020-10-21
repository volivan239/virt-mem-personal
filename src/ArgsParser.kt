/* Functions from this file are used to apply
 * parameters that were given as arguments to the program, including
 * file with testcases and additional params
 * Only `applyArgs` is used outside of this file
 */

import java.io.File

// Returns files that were passed as arguments
fun getFiles(fileNames: Array<String>): List<File> {
    var files = mutableListOf<File>()
    for (fileName in fileNames) {
        if (!File(fileName).exists()) {
            println("${fileName}: No such file or directory - skipping $fileName")
            continue
        }
        val walk = File(fileName).walk()       // stores file and everything in its subdirectories (if it's a folder)
        val cur = walk.filter { it.isFile }    // removes all folders
        files = (files + cur).toMutableList()
    }
    return files
}

// Applies all given arguments
fun applyArgs(args: Array<String>): List<File> {
    return getFiles(args).filter { it.extension != "out" }
}