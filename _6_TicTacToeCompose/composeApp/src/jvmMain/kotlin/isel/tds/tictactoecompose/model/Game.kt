package isel.tds.tictactoecompose.model

const val BOARD_SIZE = 3
const val BOARD_TOTAL_CELLS = BOARD_SIZE * BOARD_SIZE
val EMPTY = null

typealias Board = Map<Position, Player>
typealias Score = Map<Player?, Int>

data class Game(
    val currentGameStarterPlayer: Player = Player.X,
    val board: Board = emptyMap(),
    val gameState: GameState = Run(turn = currentGameStarterPlayer),
    val score: Score = (Player.entries + null).associateWith { 0 }
)

private fun Score.advance(player: Player?): Score =
    this + (player to this.getValue(player) + 1)

sealed class GameState

data class Run(val turn: Player) : GameState()
data object Draw : GameState()
data class Win(val winner: Player) : GameState()

fun Game.play(pos: Position): Game = when (gameState) {
    is Run -> {
        check(canPlay(pos)) { "Invalid Play" }
        val newBoard = board + (pos to gameState.turn)
        val newGameState = when {
            newBoard.winnerIn(pos) -> Win(winner = gameState.turn)
            newBoard.isDraw() -> Draw
            else -> Run(turn = gameState.turn.other())
        }
        val newScore = when (newGameState) {
            is Win -> score.advance(newGameState.winner)
            is Draw -> score.advance(null)
            is Run -> score
        }
        this.copy(board = newBoard, gameState = newGameState, score = newScore)
    }

    is Win, is Draw -> error("Game is already finished")
}


fun Game.restartGame(): Game {
    return when (this.gameState) {
        is Run -> Game(
            currentGameStarterPlayer = currentGameStarterPlayer.other(),
            score = score.advance(this.gameState.turn.other())
        )

        is Win, is Draw -> Game(currentGameStarterPlayer = currentGameStarterPlayer.other(), score = score)
    }
}

fun Game.canPlay(pos: Position): Boolean =
    !board.containsKey(pos)

//fun Board.isWinner(p: Player): Boolean =
//    //Chack Rows
//    (0..6 step 3).any{ rowIdx -> (0..2).all{ offset -> this[rowIdx + offset] == p}} ||
//    //Check columns
//    (0..2).any{ colIdx -> (0..6 step 3).all{ offset -> this[colIdx + offset] == p}} ||
//    //check / diagonal
//    (2..6 step 2).all{ pos -> this[pos] == p} ||
//    //check \ diagonal
//    (0..8 step 4).all{ pos-> this[pos] == p}


fun Board.winnerIn(pos: Position): Boolean {
    if (size < BOARD_SIZE * 2 - 1) return false
    val player = this[pos]
    val playerPositions = this.filterValues { it == player }.keys
    return listOf<(Position) -> Boolean>( // Conditions to check
        { it.row == pos.row },
        { it.col == pos.col },
        { it.backSlash },
        { it.slash }
    ).any { playerPositions.count(it) == BOARD_SIZE }
}

/** Assuming isWinner is called before.
 *And isDraw is never called when we already have a winner
 */
fun Board.isDraw(): Boolean = this.size == BOARD_TOTAL_CELLS
