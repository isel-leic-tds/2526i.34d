package isel.tds.isel.tds.ttt.model.ui.console

data class LineCommand(val cmd: String, val args: List<String>)
fun readCommand(): LineCommand {
    print("$ ")
    val line = readln().uppercase().split(' ')
    return LineCommand(line[0], line.drop(1))
}