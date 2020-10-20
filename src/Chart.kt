/* Functions in this file are used to
 * build charts showing algorithm's results
 * Only `displayChart` is used outside of this file
 */

import org.jfree.chart.*
import org.jfree.chart.plot.PlotOrientation
import org.jfree.chart.ui.ApplicationFrame
import org.jfree.data.category.DefaultCategoryDataset
import org.jfree.ui.RefineryUtilities

// Adds result of one algorithm to dataset
fun addResult(result: Log, dataset: DefaultCategoryDataset) {
    // Good == when page was already in memory
    val goodQueries = result.answers.runningFold(0) { cntGood, answer -> if (answer == 0) cntGood + 1 else cntGood }.drop(1)
    for (i in result.answers.indices) {
        val relScore = (goodQueries[i] * 100.0) / (i + 1)   // (in per cent)
        dataset.addValue(relScore, result.algoName, i + 1)
    }
}

// Converts algorithms' output to Dataset for chart
fun createDataset(results: List<Log>): DefaultCategoryDataset {
    val dataset = DefaultCategoryDataset()
    results.forEach { addResult(it, dataset) }
    return dataset
}

// Creates new window with chart for one case
fun displayChart(fileName: String, results: List<Log>, caseNumber: Int) {
    val chart = ChartFactory.createLineChart("Case #$caseNumber", "Number of queries", "Rel. Score, %",
        createDataset(results), PlotOrientation.VERTICAL, true, true, false)
    val frame = ApplicationFrame(fileName)
    frame.contentPane = ChartPanel(chart).apply { isMouseWheelEnabled = true }
    chart.categoryPlot.rangeAxis.setRange(0.0, 100.0)
    frame.pack()
    RefineryUtilities.centerFrameOnScreen(frame)
    frame.isVisible = true
}