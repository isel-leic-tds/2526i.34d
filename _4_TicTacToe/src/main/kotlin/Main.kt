package isel.tds

import isel.tds.isel.tds.ttt.model.Game
import isel.tds.isel.tds.ttt.model.canPLay
import isel.tds.isel.tds.ttt.model.new
import isel.tds.isel.tds.ttt.model.play
import isel.tds.isel.tds.ttt.model.ui.console.show

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    var game: Game = Game()
    while(true){
        print("$ ")
        val input = readln().uppercase().split(' ')
        when( input[0]){
            "NEW" -> game = game.new()
            "PLAY" -> {
                val pos = input[1].toInt()
                if( game.canPLay(pos)) {
                    game = game.play(pos)
                }else{
                    println("play not valid")
                }
            }
            "EXIT"-> break
            else -> println("Invalid input")
        }
        game.show()
    }
}

