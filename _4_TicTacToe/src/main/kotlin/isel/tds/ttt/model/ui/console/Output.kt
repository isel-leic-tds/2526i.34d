package isel.tds.isel.tds.ttt.model.ui.console

import isel.tds.isel.tds.ttt.model.BOARD_SIZE
import isel.tds.isel.tds.ttt.model.Draw
import isel.tds.isel.tds.ttt.model.Game
import isel.tds.isel.tds.ttt.model.Player
import isel.tds.isel.tds.ttt.model.Run
import isel.tds.isel.tds.ttt.model.Win
import isel.tds.isel.tds.ttt.model.isDraw
import isel.tds.isel.tds.ttt.model.isWinner


fun Game.show( ){
    board.chunked(BOARD_SIZE).forEachIndexed { rowIdx, rowList ->
        println( " "+rowList.map{ p-> p?.toString()?:" "}.joinToString ( " | "))
        if( rowIdx < BOARD_SIZE-1) println("---+---+---")
    }
    println( when(gameState){
        is Win -> "Winner ${gameState.winner}"
        is Draw -> "Draw"
        is Run -> "Turn: ${gameState.turn}"
    })
}