package isel.tds.ttt.model

import isel.tds.isel.tds.ttt.model.Clash
import isel.tds.isel.tds.ttt.model.ClashRun
import isel.tds.isel.tds.ttt.model.Game
import isel.tds.isel.tds.ttt.model.Name
import isel.tds.isel.tds.ttt.model.Player
import isel.tds.isel.tds.ttt.model.Position
import isel.tds.isel.tds.ttt.model.Run
import isel.tds.isel.tds.ttt.model.join
import isel.tds.isel.tds.ttt.model.new
import isel.tds.isel.tds.ttt.model.play
import isel.tds.isel.tds.ttt.model.refresh
import isel.tds.isel.tds.ttt.model.start
import isel.tds.isel.tds.ttt.storage.GameSerializer
import isel.tds.isel.tds.ttt.storage.TextFileStorage
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertIs
import kotlin.test.assertIsNot
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class ClashTest {
    private val storage = TextFileStorage<Name, Game>(
        "test",
        GameSerializer
    )
    @AfterTest
    fun after() {
        storage.fs.delete(storage.basePath) // delete the test folder
    }


    @Test
    fun `use a clash not started`() {
        val clash = Clash(storage)
        assertIsNot<ClashRun>(clash)
//        assertFailsWith<IllegalStateException> { clash.play(Position(0)) }
        assertFailsWith<IllegalStateException> { clash.new() }
        assertFailsWith<IllegalStateException> { clash.refresh() }
    }
    @Test
    fun `start a clash`() {
        val name = Name("test1")
        val clash = Clash(storage).start(name)
        assertIs<ClashRun>(clash)
        assertEquals(name, clash.name)
        assertEquals(Player.X, clash.sidePlayer)
        assertEquals(Player.X, clash.game.currentGameStarterPlayer)
        val game = storage.read(name)
        assertNotNull(game)
        assertIs<Run>(game.gameState)
        assertEquals(Player.X, game.gameState.turn)

        storage.delete(name)
        assertNull(storage.read(name))
    }
    @Test
    fun `join a clash`() {
        val name = Name("test2")
        Clash(storage).start(name)
        val clash = Clash(storage).join(name)
        assertIs<ClashRun>(clash)
        assertEquals(Player.O, clash.sidePlayer)

        storage.delete(name)
    }
    @Test
    fun `play a clash`() {
        val name = Name("test3")
        Clash(storage).start(name).play(Position(0))
        val game = storage.read(name)
        assertNotNull(game)
        val state = game.gameState
        assertIs<Run>(state)
        assertEquals(Player.O, state.turn)
        assertEquals(Player.X, game.board[Position(0)])
        storage.delete(name)
    }

}
