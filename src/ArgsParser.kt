/* Functions from this file are used to apply
 * parameters that were given as arguments to the program, including
 * file with testcases and additional params
 * Only `applyArgs` is used outside of this file
 */

import java.io.File

// Changes outputPrefix value in settings
fun applyOutputPrefix(arg: String, settings: Settings) {
    val value = arg.substringAfter("=")
    if (!File(value).isDirectory) {
        println("Skipping argument $arg: $value is not a directory")
        return
    }
    settings.outputPrefix = value
    if (!settings.outputPrefix.endsWith("/"))
        settings.outputPrefix += "/"
}

// Applies any additional param
fun applyArg(arg: String, settings: Settings) {
    if (arg.startsWith("-output-prefix="))
        return applyOutputPrefix(arg, settings)
    if (arg.startsWith("--show-chart")) {
        settings.showChart = true
        return
    }
    println("Unknown argument: $arg")
}

// Returns files that were passed as arguments
fun getFiles(fileNames: List<String>): List<File> {
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
fun applyArgs(args: Array<String>, settings: Settings): List<File> {
    args.filter { it.startsWith("-") }.forEach { applyArg(it, settings) }
    return getFiles(args.filter { !it.startsWith("-") }).filter { it.extension != "out" }
}