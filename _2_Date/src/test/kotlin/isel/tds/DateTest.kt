package isel.tds;

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

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

    @Test
    fun `test date is Leap Year`(){
        val date = Date(2024, 11, 30)
        assertTrue(date.isLeapYear)

        val dateLeapYear400Multiple = Date(2400, 11, 30)
        assertTrue(dateLeapYear400Multiple.isLeapYear)
    }
    @Test
    fun `test date isn't Leap Year`(){
        val date = Date(2025, 11, 30)
        assertFalse(date.isLeapYear)

        val dateNotLeapYear100Multiple = Date(2100, 11, 30)
        assertFalse(dateNotLeapYear100Multiple.isLeapYear)

        val dateLeapYear400MultiplePlusOne = Date(2401, 11, 30)
        assertFalse(dateLeapYear400MultiplePlusOne.isLeapYear)
    }

    @Test
    fun `test last day of month expecting true`(){
        val dateLastDayOfJan = Date(2025, 1, 31)
        assertTrue( dateLastDayOfJan.isLastDayOfMonth)
        val dateLastDayOfFebLeapYear = Date(2025, 2, 28)
        assertTrue( dateLastDayOfFebLeapYear.isLastDayOfMonth)
        val dateLastDayOfFebNotLeapYear = Date(2024, 2, 29)
        assertTrue( dateLastDayOfFebNotLeapYear.isLastDayOfMonth)
        val dateLastDayOfDecember = Date(2024, 12, 31)
        assertTrue( dateLastDayOfDecember.isLastDayOfMonth)
    }

    @Test
    fun `test last day of month expecting false`(){
        val dateFirstDayOfJan = Date(2025, 1, 1)
        assertFalse( dateFirstDayOfJan.isLastDayOfMonth)
        val dateLastDayOfJan = Date(2025, 1, 30)
        assertFalse( dateLastDayOfJan.isLastDayOfMonth)
        val dateLastDayOfFebLeapYear = Date(2025, 2, 27)
        assertFalse( dateLastDayOfFebLeapYear.isLastDayOfMonth)
        val dateLastDayOfFebNotLeapYear = Date(2024, 2, 28)
        assertFalse( dateLastDayOfFebNotLeapYear.isLastDayOfMonth)
        val dateLastDayOfDecember = Date(2024, 12, 30)
        assertFalse( dateLastDayOfDecember.isLastDayOfMonth)
    }

    @Test
    fun `test add days`(){
        val dateFromAddDays = Date(2024, 2, 1).addDays(5)
        assertEquals( 6, dateFromAddDays.day)

        val dateFromPlusOperator = Date(2024, 2, 1) + 5
        assertEquals( 6, dateFromPlusOperator.day)
    }
}


