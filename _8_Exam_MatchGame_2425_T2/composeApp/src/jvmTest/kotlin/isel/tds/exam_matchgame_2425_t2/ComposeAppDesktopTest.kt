package isel.tds.exam_matchgame_2425_t2

import kotlin.test.Test
import kotlin.test.assertEquals

class ComposeAppDesktopTest {

    @Test
    fun testMatched() {
        val game = MatchGame(8)
        val pairToFind = 4
        val idx1 = game.cards.indexOfFirst { card -> card.pair == pairToFind }
        val idx2 = game.cards.indexOfLast { card -> card.pair == pairToFind }

        assertEquals(Face.DOWN, game.cards[idx1].state)
        assertEquals(pairToFind, game.cards[idx1].pair)
        assertEquals(Face.DOWN, game.cards[idx2].state)
        assertEquals(pairToFind, game.cards[idx2].pair)
        val afterFlipsGame = game.flipCard(idx1).flipCard(idx2)
        assertEquals(Face.MATCHED, afterFlipsGame.cards[idx1].state)
        assertEquals(pairToFind, afterFlipsGame.cards[idx1].pair)
        assertEquals(Face.MATCHED, afterFlipsGame.cards[idx2].state)
        assertEquals(pairToFind, afterFlipsGame.cards[idx2].pair)
    }
}