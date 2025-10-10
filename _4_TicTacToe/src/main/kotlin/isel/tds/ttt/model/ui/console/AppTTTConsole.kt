package isel.tds.isel.tds.ttt.model.ui.console

import isel.tds.isel.tds.ttt.model.Game
import isel.tds.isel.tds.ttt.model.canPLay
import isel.tds.isel.tds.ttt.model.new
import isel.tds.isel.tds.ttt.model.play
import isel.tds.isel.tds.ttt.model.toPosition

class AppTTTConsole {
    fun run() {
        var game: Game? = null
        val commands: Map<String, Command> = getAllCommands()
        while (true) {
            print("$ ")
//            val input = readln().uppercase().split(' ')
//            val args: List<String> = input.drop(1)
            val (cmd, args) = readCommand()
            val command = commands[cmd]
            if (command != null) {
                game = command.execute(args, game)
            }else{
                println("Invalid command $cmd")
            }
//            when (input[0]) {
//                "NEW" -> game = game?.new() ?: Game()
//                "PLAY" -> {
//                    requireNotNull(game){"Game not started"}
//                    val pos = input[1].toPosition()
//                    if (game.canPLay(pos)) {
//                        game = game.play(pos)
//                    } else {
//                        println("play not valid")
//                    }
//                }
//
//                "EXIT" -> break
//                else -> println("Invalid input")
//            }
            if(game == null){
                println("create a game to start playing")
            }else {
                game?.show()
            }
        }
    }
}