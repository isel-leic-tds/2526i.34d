package isel.tds.isel.tds.ttt.ui.console

import isel.tds.isel.tds.ttt.model.BOARD_TOTAL_CELLS
import isel.tds.isel.tds.ttt.model.Clash
import isel.tds.isel.tds.ttt.model.ClashRun
import isel.tds.isel.tds.ttt.model.Game
import isel.tds.isel.tds.ttt.model.Name
import isel.tds.isel.tds.ttt.model.join
import isel.tds.isel.tds.ttt.model.new
import isel.tds.isel.tds.ttt.model.restartGame
import isel.tds.isel.tds.ttt.model.play
import isel.tds.isel.tds.ttt.model.refresh
import isel.tds.isel.tds.ttt.model.start
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

private fun beginCommand(commandHelpMsg: String, exec: Clash.(Name) -> Clash) =
    Command(commandHelpMsg=commandHelpMsg) { args ->
        val word = requireNotNull(args.firstOrNull()) { "Missing name" }
        this.exec(Name(word))
    }
fun getAllCommands(): Map<String, Command> = mapOf(
    "PLAY" to Play,
    "NEW" to New,
    "HELP" to Help,
    "SCORE" to Score,
    "START" to beginCommand("START <name> - Starts a game with the name ") { name -> start(name) },
    "JOIN" to beginCommand("JOIN <name> - Joins a game with the name ") { name -> join(name) },
    "REFRESH" to Command("REFRESH - Refresh the state of the game from storage"){ refresh()} ,
    //    "SAVE" to Command("SAVE <name> - Save the game state to file")
//    { args, game ->
//        val name = requireNotNull(args.firstOrNull()) { "Missing name" }
//        val isValidName = name.matches( Regex("[a-zA-Z][a-zA-Z0-9_]*") )
//        require(isValidName) { "Invalid name $name" }
//        game?.also{ st.create(name, game) }
////            st.create(name, game)
////            return game
//    },
//    "LOAD" to Command("LOAD <name> - Loads the game state from file")
//    { args, game ->
//        val name = requireNotNull(args.firstOrNull()) { "Missing name" }
//        val isValidName = name.matches( Regex("[a-zA-Z][a-zA-Z0-9_]*") )
//        require(isValidName) { "Invalid name $name" }
//        checkNotNull(st.read(name)){"Game $name not found!"}
//    },
    "EXIT" to Exit
)


class Command(
    val commandHelpMsg: String = "",
    val execute: Clash.(args: List<String>) -> Clash = { _,-> this },
)

private val Play = Command(  commandHelpMsg = "PLAY <position> - plays the game in the position. ex: PLAY 0")
    { args ->
        val arg = requireNotNull(args.firstOrNull()) { "Missing position" }
        val pos = requireNotNull(arg.toIntOrNull()?.toPositionOrNull())
        { "Invalid position $arg, must be in 0..${BOARD_TOTAL_CELLS -1}" }
        checkNotNull(this){"Before playing create a clash using the command - New"}
        check(this is ClashRun)
        play(pos)
    }



private val Help = Command(  commandHelpMsg = "HELP - print the commands")
    { args ->
        println("")
        getAllCommands().forEach { key: String, cmd: Command -> println(cmd.commandHelpMsg) }
        println("")
        this
    }

private val Exit = Command(commandHelpMsg = "EXIT - exit the game")
    { _ -> exitProcess(0) }

private val New = Command(commandHelpMsg = "New - creates a new game or restarts an ongoing game")
{ _ ->  new() }

private val Score = Command(  commandHelpMsg = "SCORE - shows the score")
{ _ ->
    this.also { clash -> (clash as ClashRun).game.showScore() }
}
