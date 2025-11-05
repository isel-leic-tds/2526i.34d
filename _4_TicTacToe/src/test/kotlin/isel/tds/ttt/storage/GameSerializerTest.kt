package isel.tds.ttt.storage

import isel.tds.isel.tds.ttt.model.Game
import isel.tds.isel.tds.ttt.model.GameState
import isel.tds.isel.tds.ttt.model.Player
import isel.tds.isel.tds.ttt.model.Player.O
import isel.tds.isel.tds.ttt.model.Player.X
import isel.tds.isel.tds.ttt.model.Run
import isel.tds.isel.tds.ttt.model.toPosition
import isel.tds.isel.tds.ttt.storage.GameSerializer
import isel.tds.ttt.storage.Serializer
import isel.tds.ttt.storage.Storage
import isel.tds.isel.tds.ttt.storage.TextFileStorage
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

class GameSerializerTest{
    @Test
    fun `test Game serializer`(){
        val game = Game(
            currentGameStarterPlayer = X,
            board= mapOf(
                (1.toPosition() to O),(2.toPosition() to X),
                (4.toPosition() to O),(5.toPosition() to X),
                (8.toPosition() to X)
            ),
            gameState= Run(O),
            score = mapOf((X to 2), (O to 4), (null to 9)))

        val serializedGame = GameSerializer.serialize(game)
        println(serializedGame)

        val readGame = GameSerializer.deserialize(serializedGame)
        assertEquals(game, readGame)
    }
}