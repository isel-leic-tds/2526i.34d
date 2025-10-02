package isel.tds.stacks

class MutableStackImmutableList<T> {
    private var elems = emptyList<T>()
    fun isEmpty(): Boolean = elems.isEmpty()
    fun pop(): T {
        val elem = elems.last()
        elems = elems.dropLast(1)
        return elem
    }
    fun top(): T = elems.last()
    fun push(elem: T) {
        elems = elems + elem
    }

    override fun equals(other: Any?): Boolean {
        return other is MutableStackImmutableList<*>
                && elems == other.elems
    }
    override fun hashCode(): Int = elems.hashCode()
}