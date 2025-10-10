package isel.tds.isel.tds.ttt.model


class Position private constructor(val index: Int) {

    companion object {
        private val positionvalues: List<Position>
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

fun String.toPosition() = Position(this.toInt())
fun Int.toPosition() = Position(this)
