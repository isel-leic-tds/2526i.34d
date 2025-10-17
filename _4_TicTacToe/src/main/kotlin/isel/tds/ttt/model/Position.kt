package isel.tds.isel.tds.ttt.model


class Position private constructor(val index: Int) {

    val row: Int get() = index / BOARD_SIZE  // row in (0..<BOARD_SIZE)
    val col: Int get() = index % BOARD_SIZE  // col in (0..<BOARD_SIZE)
    val backSlash get() = row == col         // Is in principal diagonal
    val slash get() = row+col == BOARD_SIZE-1// Is in secondary diagonal


    companion object {
        val positionvalues: List<Position>
            = List(BOARD_TOTAL_CELLS)
                {idx -> Position(idx)}

        operator fun invoke(idx: Int): Position{
            require(idx < BOARD_TOTAL_CELLS){"Invalid index position"}
            require(idx >= 0){"Invalid index position"}
            return positionvalues[idx]
        }

//            = positionvalues.run{
//            require(idx < BOARD_TOTAL_CELLS){"Invalid index position"}
//            require(idx >= 0){"Invalid index position"}
//            get(idx)
//            }
    }
}

fun Int.toPositionOrNull() = Position.positionvalues.getOrNull(this)
fun String.toPosition() = Position(this.toInt())
fun Int.toPosition() = Position(this)
