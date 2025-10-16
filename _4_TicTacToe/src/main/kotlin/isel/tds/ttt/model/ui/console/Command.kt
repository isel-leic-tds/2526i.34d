package isel.tds.isel.tds.ttt.model.ui.console

import isel.tds.isel.tds.ttt.model.Game
import isel.tds.isel.tds.ttt.model.restartGame
import isel.tds.isel.tds.ttt.model.play
import isel.tds.isel.tds.ttt.model.toPosition
import kotlin.system.exitProcess


abstract class Command(val commandHelpMsg: String) {
    open fun execute(args: List<String>, game: Game?): Game? = game
}

object Play: Command(commandHelpMsg = "Play <positionIndex> - plays the game in the position. ex: PLAY 0") {
    override fun execute(args: List<String>, game: Game?): Game {
        val arg = requireNotNull(args.firstOrNull()) {"Missing position"}
        val pos = arg.toPosition()
        return checkNotNull(game){ "Game not started" }.play(pos)
    }
}

object New: Command(commandHelpMsg = "New - creates a new game or restarts an ongoing game"){
    override fun execute(args: List<String>, game: Game?): Game =
        if(game==null) Game() else game.restartGame()
}

object Help: Command(commandHelpMsg = "Help - prints a list of commands"){
    override fun execute(args: List<String>, game: Game?): Game? {
        println("")
        getAllCommands().forEach { _, command -> println(command.commandHelpMsg) }
        return game
    }
}
object Exit: Command("exit") {
    override fun execute(args: List<String>, game: Game?): Game {
        exitProcess(0)
    }
}

fun getAllCommands(): Map<String, Command> = mapOf(
    "PLAY" to Play,
    "NEW" to New,
    "HELP" to Help,
    "EXIT" to Exit
)