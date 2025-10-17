package isel.tds.isel.tds.ttt.model.ui.console

import isel.tds.isel.tds.ttt.model.BOARD_TOTAL_CELLS
import isel.tds.isel.tds.ttt.model.Game
import isel.tds.isel.tds.ttt.model.restartGame
import isel.tds.isel.tds.ttt.model.play
import isel.tds.isel.tds.ttt.model.toPosition
import isel.tds.isel.tds.ttt.model.toPositionOrNull
import kotlin.system.exitProcess


//abstract class Command(val commandHelpMsg: String) {
//    open fun execute(args: List<String>, game: Game?): Game? = game
//}
//
//object Play: Command(commandHelpMsg = "Play <positionIndex> - plays the game in the position. ex: PLAY 0") {
//    override fun execute(args: List<String>, game: Game?): Game {
//        val arg = requireNotNull(args.firstOrNull()) {"Missing position"}
//        val pos = arg.toPosition()
//        return checkNotNull(game){ "Game not started" }.play(pos)
//    }
//}
//
//object New: Command(commandHelpMsg = "New - creates a new game or restarts an ongoing game"){
//    override fun execute(args: List<String>, game: Game?): Game =
//        if(game==null) Game() else game.restartGame()
//}
//
//object Help: Command(commandHelpMsg = "Help - prints a list of commands"){
//    override fun execute(args: List<String>, game: Game?): Game? {
//        println("")
//        getAllCommands().forEach { _, command -> println(command.commandHelpMsg) }
//        return game
//    }
//}
//object Exit: Command("exit") {
//    override fun execute(args: List<String>, game: Game?): Game {
//        exitProcess(0)
//    }
//}
//
fun getAllCommands(): Map<String, Command> = mapOf(
    "PLAY" to Play,
    "NEW" to New,
    "HELP" to Help,
    "SCORE" to Score,
    "EXIT" to Exit
)


class Command(
    val commandHelpMsg: String = "",
    val execute: (args: List<String>, Game?) -> Game? = { _, g -> g },
)

private val Play = Command(  commandHelpMsg = "PLAY <position> - plays the game in the position. ex: PLAY 0")
    { args, game ->
        val arg = requireNotNull(args.firstOrNull()) { "Missing position" }
        val pos = requireNotNull(arg.toIntOrNull()?.toPositionOrNull())
        { "Invalid position $arg, must be in 0..${BOARD_TOTAL_CELLS -1}" }
        checkNotNull(game){"Before playing create a game using the command - New"}
        game.play(pos)
    }

private val Help = Command(  commandHelpMsg = "HELP - print the commands")
    { args, game ->
        println("")
        getAllCommands().forEach { key: String, cmd: Command -> println(cmd.commandHelpMsg) }
        println("")
        game
    }

private val Exit = Command(commandHelpMsg = "EXIT - exit the game")
    { _, _ -> exitProcess(0) }

private val New = Command(commandHelpMsg = "New - creates a new game or restarts an ongoing game")
{ _, game ->  if(game==null) Game() else game.restartGame() }

private val Score = Command(  commandHelpMsg = "SCORE - shows the score")
{ _, game ->
    game.also { game -> game?.showScore() }
}