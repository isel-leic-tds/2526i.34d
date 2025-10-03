package isel.tds.stacks

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class ImmutableStackTest {
    @Test
    fun `test empty stack`(){
        var stack = ImmutableStack<Char>()
        assertTrue( stack.isEmpty())
        assertFailsWith<NoSuchElementException> {  stack.pop() }
        assertFailsWith<NoSuchElementException> {  stack.top() }
    }

    @Test
    fun `test not empty stack`(){
        val stackEmpty = ImmutableStack<Char>()

        val stackWithA = stackEmpty.push('A')
        assertTrue( stackEmpty.isEmpty())
        assertFalse( stackWithA.isEmpty())
        assertEquals('A', stackWithA.top())
        val stackWithAB = stackWithA.push('B')
        assertFalse( stackWithAB.isEmpty())
        assertEquals('B', stackWithAB.top())
        val pairWithABPopB = stackWithAB.pop()
        assertEquals('B', pairWithABPopB.first)
        assertEquals('A', pairWithABPopB.second.top())
        val pairWithABPopBA = pairWithABPopB.second.pop()
        assertEquals('A', pairWithABPopBA.first)

        assertTrue( pairWithABPopBA.second.isEmpty())
        assertFailsWith<NoSuchElementException> {  pairWithABPopBA.second.pop() }
        assertFailsWith<NoSuchElementException> {  pairWithABPopBA.second.top() }
    }

    @Test
    fun testEqualityOfStacks(){
        val sut = ImmutableStack<Char>()
        val stackWithA =sut.push('A')
        val stackWithAB =stackWithA.push('B')
        val sut2 = ImmutableStack<Char>()
        val stackWithA2 = sut2.push('A')
        val stackWithAB2 = stackWithA2.push('B')
        assertEquals(stackWithAB, stackWithAB2)
        assertEquals(stackWithAB.hashCode(), stackWithAB2.hashCode())
        val stackWithABPopB2 = stackWithAB2.pop().second
        assertNotEquals(stackWithAB, stackWithABPopB2)
        assertNotEquals(stackWithAB.hashCode(), stackWithABPopB2.hashCode())
    }

    @Test
    fun `test stack iteration`(){
//        val testList = listOf('A','B')
//
//        for (elem in testList){
//            println(elem)
//        }

        val sut = ImmutableStack<Char>()
        val stackWithA =sut.push('A')
        val stackWithAB =stackWithA.push('B')

        //stackWithAB.forEach(::println)

        for (e in stackWithAB){
            println(e)
        }
    }
}