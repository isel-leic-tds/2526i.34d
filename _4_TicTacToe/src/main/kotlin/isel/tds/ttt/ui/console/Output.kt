package isel.tds.isel.tds.ttt.ui.console

import isel.tds.isel.tds.ttt.model.BOARD_SIZE
import isel.tds.isel.tds.ttt.model.Clash
import isel.tds.isel.tds.ttt.model.ClashRun
import isel.tds.isel.tds.ttt.model.Draw
import isel.tds.isel.tds.ttt.model.Game
import isel.tds.isel.tds.ttt.model.Position
import isel.tds.isel.tds.ttt.model.Run
import isel.tds.isel.tds.ttt.model.Win


private val separator = "---+".repeat(BOARD_SIZE-1)+"---"
fun Game.show( ){
//    board.chunked(BOARD_SIZE).forEachIndexed { rowIdx, rowList ->
//        println( " "+rowList.map{ p-> p?.toString()?:" "}.joinToString ( " | "))
//        if( rowIdx < BOARD_SIZE-1) println("---+---+---")
//    }
//    println( when(gameState){
//        is Win -> "Winner ${gameState.winner}"
//        is Draw -> "Draw"
//        is Run -> "Turn: ${gameState.turn}"
//    })
    Position.positionvalues.chunked(BOARD_SIZE).forEach { line ->
        val plays = line.map { pos -> board[pos]?.name ?: " " }
        println(plays.joinToString(separator = " | ", prefix = " "))
        if (line.first().row < BOARD_SIZE - 1) println(separator)
    }
    // Show the game status
    println( when(gameState) {
        is Win -> "Winner: ${gameState.winner}"
        is Draw -> "Draw"
        is Run -> "Turn: ${gameState.turn}"
    })
}

fun Game.showScore() {
    score.entries.joinToString(",", "Scores:") { (player,points) ->
        " ${player?:"Draws"}=$points"
    }.let(::println)
}

fun Clash.show(){
    if (this is ClashRun) {
        println("Clash: $name Player: $sidePlayer")
        game.show()
    }
    else println("Clash not started")
}