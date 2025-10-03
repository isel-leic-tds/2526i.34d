package isel.tds.stacks

class MutableStackWithCustomList<T> {
    private class Node<T>(val elem: T, val next: Node<T>?)
    private var head: Node<T>? = null
    fun isEmpty(): Boolean = head == null
    private fun topNodeOrException():Node<T> = head ?: throw NoSuchElementException("Empty Stack")
    fun pop(): T
        = topNodeOrException()
            .also{ head = it.next}
            .elem
//    {
//        val node = topNodeOrException()
//        head = node.next
//        return node.elem
//    }

    fun top(): T = topNodeOrException().elem
    fun push(elem: T) {
        head = Node(elem, head)
    }

    override fun equals(other: Any?): Boolean {
        if(other !is MutableStackWithCustomList<*>)
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
}