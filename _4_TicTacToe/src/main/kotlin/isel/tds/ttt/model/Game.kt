package isel.tds.isel.tds.ttt.model

import kotlin.collections.none

const val BOARD_SIZE = 3
const val BOARD_TOTAL_CELLS = BOARD_SIZE * BOARD_SIZE
val EMPTY = null

data class Game(
    val currentGameStarterPlayer: Player = Player.X,
//    val turn: Player = currentGameStarterPlayer,
    val board: List<Player?> = listOf(
        EMPTY, EMPTY, EMPTY,
        EMPTY, EMPTY, EMPTY,
        EMPTY, EMPTY, EMPTY
        ),
    val gameState: GameState = Run(turn=currentGameStarterPlayer)
)

sealed class GameState

class Run(val turn: Player): GameState()
object Draw: GameState()
class Win(val winner: Player): GameState()

fun Game.play(pos: Position): Game = when(gameState){
    is Run -> {
        check(canPLay(pos)) { "Invalid Play" }
        val newBoard = board.mapIndexed { idx, cellContent ->
            if (idx == pos.index) this.gameState.turn else cellContent
        }
        this.copy(board = newBoard, gameState=when{
            isWinner(gameState.turn) -> Win(winner=gameState.turn )
            isDraw() -> Draw
            else -> Run(turn = gameState.turn.other())
        })
    }
    is Win, Draw -> error("Game is already finished")
}

fun Game.restartGame(): Game
= Game( currentGameStarterPlayer = currentGameStarterPlayer.other())

fun Game.canPLay(pos: Position): Boolean =
    board[pos.index] == EMPTY &&
        !isWinner(Player.X) &&
        !isWinner(Player.O)

fun Game.isWinner(p: Player): Boolean =
    //Chack Rows
    (0..6 step 3).any{ rowIdx -> (0..2).all{ offset -> board[rowIdx + offset] == p}} ||
    //Check columns
    (0..2).any{ colIdx -> (0..6 step 3).all{ offset -> board[colIdx + offset] == p}} ||
    //check / diagonal
    (2..6 step 2).all{ pos -> board[pos] == p} ||
    //check \ diagonal
    (0..8 step 4).all{ pos-> board[pos] == p}

/** Assuming isWinner is called before.
 *And isDraw is never called when we already have a winner
 */
fun Game.isDraw(): Boolean  = board.none{ elem -> elem==EMPTY }
