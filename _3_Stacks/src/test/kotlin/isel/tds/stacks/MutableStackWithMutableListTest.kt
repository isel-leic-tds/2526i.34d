package isel.tds.stacks

import isel.tds.stacks.MutableStackWithMutableList
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class MutableStackWithMutableListTest {
    @Test
    fun `test empty stack`(){
        var stack = MutableStackWithMutableList<Char>()
        assertTrue( stack.isEmpty())
        assertFailsWith<NoSuchElementException> {  stack.pop() }
        assertFailsWith<NoSuchElementException> {  stack.top() }
    }

    @Test
    fun `test not empty stack`(){
        var stack = MutableStackWithMutableList<Char>()

        stack.push('A')
        assertFalse( stack.isEmpty())
        assertEquals('A', stack.top())
        stack.push('B')
        assertFalse( stack.isEmpty())
        assertEquals('B', stack.top())
        assertEquals('B', stack.pop())
        assertEquals('A', stack.pop())

        assertTrue( stack.isEmpty())
        assertFailsWith<NoSuchElementException> {  stack.pop() }
        assertFailsWith<NoSuchElementException> {  stack.top() }
    }

    @Test
    fun testEqualityOfStacks(){
        val sut = MutableStackWithMutableList<Char>()
        sut.push('A')
        sut.push('B')
        val sut2 = MutableStackWithMutableList<Char>()
        sut2.push('A')
        sut2.push('B')
        assertEquals(sut, sut2)
        assertEquals(sut.hashCode(), sut2.hashCode())
        sut2.pop()
        assertNotEquals(sut, sut2)
        assertNotEquals(sut.hashCode(), sut2.hashCode())
    }
}