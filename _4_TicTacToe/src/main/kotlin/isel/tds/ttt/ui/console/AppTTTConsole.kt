package isel.tds.isel.tds.ttt.ui.console

import isel.tds.isel.tds.ttt.model.Clash
import isel.tds.isel.tds.ttt.model.Game
import isel.tds.isel.tds.ttt.model.Name
import isel.tds.isel.tds.ttt.storage.GameSerializer
import isel.tds.isel.tds.ttt.storage.TextFileStorage
import isel.tds.ttt.storage.Storage
import isel.tds.ttt.storage.mongo.MongoDriver
import isel.tds.ttt.storage.mongo.MongoStorage

class AppTTTConsole {
    fun run() {

        MongoDriver("JogoGalo34D").use { driver ->
//        var clash: Clash = Clash(TextFileStorage<Name, Game>("savedGames", GameSerializer))
//        val driver = MongoDriver("JogoGalo34D")
            val storage: Storage<Name, Game> = MongoStorage("savedGames", driver, GameSerializer)
            var clash: Clash = Clash(storage)

            val commands: Map<String, Command> = getAllCommands()
            while (true) {
                try {
                    val (cmd, args) = readCommand()
                    val command = commands[cmd]
                    if (command != null) {
                        clash = with(command) { clash.execute(args) }
                    } else {
                        println("Invalid command $cmd")
                    }
                    if (clash == null) {
                        println("create a game to start playing")
                    } else {
                        clash.show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    println("Error: ${e.message} - ${e.javaClass.simpleName}")
                }
            }
        }
    }
}