package isel.tds;

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class DateTest {

    @Test
    fun testDateCreation(){
        val date = Date(2015, 11, 30)
        assertEquals(2015, date.year)
        assertEquals(11, date.month)
        assertEquals(30, date.day)
    }

    @Test
//    fun testDateCreationWithDefaultValues(){
    fun `test date creation with default values`(){
        val dateWithDefaultDay = Date(2015, 11)
        assertEquals(2015, dateWithDefaultDay.year)
        assertEquals(11, dateWithDefaultDay.month)
        assertEquals(1, dateWithDefaultDay.day)

        val dateWithDefaultMonth = Date(2015)
        assertEquals(2015, dateWithDefaultMonth.year)
        assertEquals(1, dateWithDefaultMonth.month)
        assertEquals(1, dateWithDefaultMonth.day)
    }

    @Test
    fun `test date creation with negative values`(){
        assertFailsWith<IllegalArgumentException>( block={Date(-1, 11, 15)})
        assertFailsWith<IllegalArgumentException>( block={Date(2025, -1, 15)})
        assertFailsWith<IllegalArgumentException>( block={Date(2025, 11, -1)})
    }

    @Test
    fun `test date creation with 0 values`(){
        assertFailsWith<IllegalArgumentException>( block={Date(0, 11, 15)})
        assertFailsWith<IllegalArgumentException>( block={Date(2025, 0, 15)})
        assertFailsWith<IllegalArgumentException>( block={Date(2025, 11, 0)})
    }

}