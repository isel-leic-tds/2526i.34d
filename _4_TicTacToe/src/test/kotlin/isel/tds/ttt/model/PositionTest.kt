package isel.tds.ttt.model

import isel.tds.isel.tds.ttt.model.BOARD_TOTAL_CELLS
import isel.tds.isel.tds.ttt.model.Position
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class PositionTest {
    @Test
    fun `Test valid positions`() {
        assertEquals(0, Position(0).index)
        assertEquals(8, Position(8).index)
    }

    @Test
    fun `Test invalid positions`() {
        assertFailsWith<IllegalArgumentException> { Position(-1)}
        assertFailsWith<IllegalArgumentException> { Position(BOARD_TOTAL_CELLS)}
    }
}