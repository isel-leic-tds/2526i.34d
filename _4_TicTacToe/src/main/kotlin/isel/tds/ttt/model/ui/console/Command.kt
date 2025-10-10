package isel.tds.isel.tds.ttt.model.ui.console

import isel.tds.isel.tds.ttt.model.Game
import sun.net.www.http.HttpClient.New


data class LineCommand(val cmd: String, val args: List<String>)
fun readCommand(): LineCommand {
    val line = readln().uppercase().split(' ')
    return LineCommand(line[0], line.drop(1))
}

abstract class Command(val commandHelpMsg: String) {
    open fun execute(args: List<String>, game: Game): Game = game
}

class Play: Command(commandHelpMsg = "Play <positionIndex> - plays the game in the position. ex: PLAY 0") {
    override fun execute(args: List<String>, game: Game): Game {
        TODO("Implement play")
    }
}

fun getAllCommands(): Map<String, Command> = mapOf(
    "PLAY" to Play(),
//    "NEW" to New(),
//    ...
)