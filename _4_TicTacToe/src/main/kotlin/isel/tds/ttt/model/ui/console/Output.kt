package isel.tds.isel.tds.ttt.model.ui.console

import isel.tds.isel.tds.ttt.model.BOARD_SIZE
import isel.tds.isel.tds.ttt.model.Game


fun Game.show( ){
    board.chunked(BOARD_SIZE).forEachIndexed { rowIdx, rowList ->
        println( rowList.joinToString ( " | "))
        if( rowIdx < BOARD_SIZE-1) println("---+---+---")
    }
    println("Turn: $turn")
}