package isel.tds.ttt.model

import isel.tds.isel.tds.ttt.model.Player
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class PlayerTest {
    @Test
    fun `Test other function`(){
        assertEquals(Player.X,Player.O.other())
        assertEquals(Player.O,Player.X.other())
    }

}