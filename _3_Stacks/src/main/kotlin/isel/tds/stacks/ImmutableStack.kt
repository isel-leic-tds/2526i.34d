package isel.tds.stacks

import java.util.NoSuchElementException

class ImmutableStack<T> private constructor (private val head: Node<T>? = null): Iterable<T> {
    private class Node<T>(val elem: T, val next: Node<T>?)
    constructor (): this(null)
    fun isEmpty(): Boolean = head == null
    private fun topNodeOrException():Node<T> = head ?: throw NoSuchElementException("Empty Stack")
    fun pop2() = ImmutableStack<T>( topNodeOrException().next)
    fun pop(): Pair<T, ImmutableStack<T>>
        = top() to pop2()
//        = Pair(top(), pop2())

    fun top(): T = topNodeOrException().elem
    fun push(elem: T)
        = ImmutableStack<T>( Node( elem, head))


    override fun equals(other: Any?): Boolean {
        if(other !is ImmutableStack<*>)
             return false
        var n1 = head
        var n2 = other.head
        while( n1!=null && n2!=null) {
            if( n1.elem != n2.elem)
                return false
            n1 = n1.next
            n2 = n2.next
        }
        return n1==null && n2==null
    }
    override fun hashCode(): Int  {
        var n = head
        var hash = 0
        while (n != null) {
            hash = 31 * hash + n.elem.hashCode()
            n = n.next
        }
        return hash
    }

    fun forEach(action: (T)->Unit ) {
        var node = head
        while (node != null) {
            action(node.elem)
            node = node.next
        }
    }
//    // 1st Version using nested class without access to the outer class state
//    private class It<T>(private var node : Node<T>?): Iterator<T> {
//        override fun next(): T
//            = ( node ?: throw NoSuchElementException("Reached the end") )
//                .also{node = it.next}
//                .elem
//        override fun hasNext() = node != null
//
//    }
//    override fun iterator(): Iterator<T> = It<T>( head)

    // 2ND Version using inner class with access to the outer class state
//    private inner class It: Iterator<T> {
//        private var node = head
//
//        override fun next(): T
//                = ( node ?: throw NoSuchElementException("Reached the end") )
//            .also{node = it.next}
//            .elem
//        override fun hasNext() = node != null
//
//    }
//    override fun iterator(): Iterator<T> = It()

    //3rd version using implicit inner class
//    override fun iterator(): Iterator<T> {
//        class It():Iterator<T>{
//            private var node :Node<T>? = head
//            override fun hasNext() = node != null
//            override fun next() :T = (node ?: throw NoSuchElementException()).also { node = it.next }.elem
//        }
//        return It()
//    }

    //4th version using anonymous object
    override fun iterator(): Iterator<T> = object : Iterator<T> {
        private var node: ImmutableStack.Node<T>? = head
        override fun hasNext() = node != null
        override fun next(): T = (node ?: throw NoSuchElementException()).also { node = it.next }.elem
    }
}