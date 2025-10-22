package isel.tds.isel.tds.ttt.storage


import okio.*
import okio.Path.Companion.toPath

class TextFileStorage<Key, Data>(
    baseDirectory: String,
    val serializer: Serializer<Data>,
) : Storage<Key,Data>{

    val fs = FileSystem.SYSTEM

    override fun create(k: Key, data: Data) {
        TODO("Not yet implemented")
    }

    override fun read(k: Key): Data? {

    }

    override fun update(k: Key, data: Data) {
        TODO("Not yet implemented")
    }

    override fun delete(k: Key) {
        TODO("Not yet implemented")
    }


}