package isel.tds.isel.tds.ttt.storage

import isel.tds.isel.tds.ttt.model.Draw
import isel.tds.isel.tds.ttt.model.Game
import isel.tds.isel.tds.ttt.model.GameState
import isel.tds.isel.tds.ttt.model.Run
import isel.tds.isel.tds.ttt.model.Win
import isel.tds.isel.tds.ttt.model.toPlayer
import isel.tds.isel.tds.ttt.model.toPlayerOrNull
import isel.tds.isel.tds.ttt.model.toPosition
import kotlin.collections.emptyMap

object GameSerializer: Serializer<Game> {
    override fun serialize(game: Game): String = buildString{
        appendLine(game.currentGameStarterPlayer.name)
        appendLine(game.board.entries.joinToString(" ")
        {
            (pos, player) -> "${pos.index}:${player.name}"
        })
        appendLine(GameStateSerializer.serialize(game.gameState))
        appendLine(game.score.entries.joinToString(" ")
        { (player, score) -> "${player?.name ?: "null"}=$score" }
        )
    }

    override fun deserialize(txt: String): Game =
        txt.split('\n').let{  (l1, l2, l3, l4) ->
            Game(
                currentGameStarterPlayer = l1.toPlayer(),
                board = if( l2.isBlank()) emptyMap()
                    else l2.split(' ')
                    .map { boardEntry -> boardEntry.split(':') }
                    .associate { (index, player) -> (index.toPosition() to player.toPlayer()) },
                gameState = GameStateSerializer.deserialize(l3),
                score = l4.split(' ')
                    .map{ scoreEntry -> scoreEntry.split('=')}
                    .associate { (player, score) -> (player.toPlayerOrNull() to score.toInt()) }
            )
        }
}

object GameStateSerializer: Serializer<GameState> {
    override fun serialize(gameState: GameState): String = when(gameState){
        is Run -> "Run:${gameState.turn.name}"
        is Win -> "Win:${gameState.winner.name}"
        is Draw -> "Draw:"
    }

    override fun deserialize(txt: String): GameState =
        txt.split(":").let { (type, player) ->
            when(type){
                "Run"-> Run(player.toPlayer())
                "Win"->Win(player.toPlayer())
                "Draw"->Draw
                else -> error("Unkown game state: $type")
            }
        }

}