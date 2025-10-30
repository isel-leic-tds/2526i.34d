package isel.tds.isel.tds.ttt.model

import isel.tds.isel.tds.ttt.storage.Storage

typealias GameStorage = Storage<Name, Game>
open class Clash(val st: GameStorage)

class ClashRun(st: GameStorage,
               val name: Name,
               val sidePlayer: Player,
               val game: Game
): Clash(st){


}

fun ClashRun.play(pos: Position): ClashRun{
    val newGame = game.play(pos)
    st.update(name, newGame)
    return ClashRun(this.st, this.name, this.sidePlayer, newGame)
}

fun ClashRun.restartGame(): ClashRun {
    val newGame = this.game.restartGame()
    TODO()
}

fun Clash.start(name: Name):ClashRun{
    return ClashRun( st, name, Player.X, Game() ).also{
        st.create(name,it.game)
    }
}

fun Clash.join(name: Name): ClashRun{
    val readGame = st.read(name)
    checkNotNull(readGame){"Game with name=$name does not exist"}
    return ClashRun(st, name, Player.O, readGame)
}

fun Clash.refresh():ClashRun{
    check(this is ClashRun)
    val readGame = st.read(name)
    check( this.game != readGame ){"No Changes"}
    checkNotNull(readGame){"Game with name=$name does not exist"}
    return ClashRun(st, name, sidePlayer, readGame)
}

fun Clash.new(): ClashRun{
    check( this is ClashRun){"Game not started"}
    check( game.gameState is Run){"Game is not running"}
    if( sidePlayer == game.gameState.turn ){
        return ClashRun(st, name, sidePlayer, game.restartGame())
    }else{
        return this
    }
}