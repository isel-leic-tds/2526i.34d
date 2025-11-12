package isel.tds.tictactoecompose.model

enum class Player{
    X, O;

    fun other(): Player = if (this == X) O else X
}

fun String.toPlayerOrNull() = Player.entries.firstOrNull {it.name==this}

fun String.toPlayer() = //Player.valueOf(this)
    requireNotNull(toPlayerOrNull()) { "Invalid player $this" }