package isel.tds.ttt.model

import isel.tds.isel.tds.ttt.model.Player
import kotlin.test.Test
import kotlin.test.assertEquals

class PlayerTest {
    @Test
    fun `Test other function`(){
        assertEquals(Player.X,Player.O.other())
        assertEquals(Player.O,Player.X.other())
    }

}