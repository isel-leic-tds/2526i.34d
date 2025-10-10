package isel.tds.isel.tds.ttt.model

enum class Player{
    X, O;

    fun other(): Player = if (this == X) O else X
}