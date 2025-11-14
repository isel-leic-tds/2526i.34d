package isel.tds.tictactoecompose.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import isel.tds.tictactoecompose.model.BOARD_SIZE
import isel.tds.tictactoecompose.model.Board
import isel.tds.tictactoecompose.model.Position

val CELL_SIZE = 150.dp
val LINE_THICKNESS = 5.dp
val GRID_SIZE = CELL_SIZE * BOARD_SIZE + LINE_THICKNESS * (BOARD_SIZE - 1)

@Composable
fun BoardView(board: Board, onCellClickAction: (Position) -> Unit) {
    Column(
        modifier = Modifier.height(GRID_SIZE).background(Color.Black),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(BOARD_SIZE) { row ->
            Row(
                modifier = Modifier.width(GRID_SIZE),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(BOARD_SIZE) { col ->
                    val position = Position(row * BOARD_SIZE + col)
                    PlayerView(
                        board[position],
                        onClick = { onCellClickAction(position) },
                        modifier = Modifier.background(Color.White).size(CELL_SIZE)
                    )
                }
            }
        }
    }
}