package isel.tds.isel.tds.ttt.model

const val BOARD_SIZE = 3
private const val EMPTY = ' '

data class Game(
    val nextGamePlayer: Char? = null,
    val turn: Char = nextGamePlayer?: 'X',
    val board: List<Char> = listOf(
        EMPTY, EMPTY, EMPTY,
        EMPTY, EMPTY, EMPTY,
        EMPTY, EMPTY, EMPTY
        )
)

fun Game.play(pos: Int): Game {
    val newBoard = board.mapIndexed { idx, cellContent ->
        if (idx==pos ) turn else cellContent
    }
    return this.copy( turn = other(this.turn), board = newBoard)
}

fun Game.new() = if(nextGamePlayer==null) Game('X') else Game( nextGamePlayer = other(nextGamePlayer))

fun Game.canPLay(pos: Int) = board[pos] == EMPTY
private fun other(turn: Char): Char = if (turn == 'X') 'O' else 'X'

