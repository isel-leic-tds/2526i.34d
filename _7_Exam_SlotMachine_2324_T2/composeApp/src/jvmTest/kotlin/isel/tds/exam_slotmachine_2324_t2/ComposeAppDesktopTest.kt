package isel.tds.exam_slotmachine_2324_t2

import isel.tds.exam_slotmachine_2324_t2.model.SlotState
import isel.tds.exam_slotmachine_2324_t2.model.isWinner
import org.junit.Assert
import kotlin.test.Test
import kotlin.test.assertEquals

class ComposeAppDesktopTest {

    @Test
    fun isWinnerTrue() {
        val slotState = SlotState(2, 2, 2)
        assertEquals(true, slotState.isWinner())
    }

    @Test
    fun isWinnerFalse() {
        val slotState = SlotState(3, 2, 2)
        assertEquals(false, slotState.isWinner())
    }
    @Test
    fun testSlotStateWithInvalidByte(){
//        Assert.assertThrows(IllegalArgumentException.class){SlotState(12,2,3)}
    }

    @Test
    fun testRandomSlotState(){
        val slotState1 = SlotState.random()
        Assert.assertNotNull( slotState1)
        Assert.assertTrue( slotState1.slot1 in 0..9)
        Assert.assertTrue( slotState1.slot2 in 0..9)
        Assert.assertTrue( slotState1.slot3 in 0..9)
        val slotState2 = SlotState.random()
        Assert.assertNotNull( slotState2)
        Assert.assertTrue( slotState2.slot1 in 0..9)
        Assert.assertTrue( slotState2.slot2 in 0..9)
        Assert.assertTrue( slotState2.slot3 in 0..9)

        Assert.assertNotEquals(slotState1, slotState2)

    }
}