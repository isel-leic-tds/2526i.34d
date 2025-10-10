package isel.tds.ttt.model

import isel.tds.isel.tds.ttt.model.EMPTY
import isel.tds.isel.tds.ttt.model.Game
import isel.tds.isel.tds.ttt.model.Player
import isel.tds.isel.tds.ttt.model.Player.O
import isel.tds.isel.tds.ttt.model.Player.X
import isel.tds.isel.tds.ttt.model.canPLay
import isel.tds.isel.tds.ttt.model.isWinner
import isel.tds.isel.tds.ttt.model.play
import isel.tds.isel.tds.ttt.model.toPosition
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class GameTest {
    @Test
    fun `test game init`(){
        val game = Game()
        assertEquals(X, game.turn)
        assertTrue( game.board.all{it == EMPTY})
    }
    @Test
    fun `test game custom state`(){
        val game = Game(turn = O, board = listOf(
            EMPTY, O, X,
            EMPTY, O, X,
            EMPTY, EMPTY, X
        ))
        assertTrue( game.isWinner(X))
        assertFalse( game.isWinner(O))
        assertFalse( game.canPLay( 7.toPosition()) )
    }
}