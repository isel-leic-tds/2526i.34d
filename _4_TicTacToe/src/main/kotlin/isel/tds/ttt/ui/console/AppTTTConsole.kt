package isel.tds.isel.tds.ttt.ui.console

import isel.tds.isel.tds.ttt.model.Game
import isel.tds.isel.tds.ttt.storage.GameSerializer
import isel.tds.isel.tds.ttt.storage.TextFileStorage

class AppTTTConsole {
    fun run() {
        var game: Game? = null

        val commands: Map<String, Command> = getAllCommands(TextFileStorage("savedGames", GameSerializer))
        while (true) {
            try{
                val (cmd, args) = readCommand()
                val command = commands[cmd]
                if (command != null) {
                    game = command.execute(args, game)
                }else{
                    println("Invalid command $cmd")
                }
                if(game == null){
                    println("create a game to start playing")
                }else {
                    game?.show()
                }
            }catch(e: Exception){
                e.printStackTrace()
                println("Error: ${e.message} - ${e.javaClass.simpleName}")
            }
        }
    }
}