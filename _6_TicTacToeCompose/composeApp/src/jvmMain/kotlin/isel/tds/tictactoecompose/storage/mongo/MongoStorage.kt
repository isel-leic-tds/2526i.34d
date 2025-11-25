package isel.tds.tictactoecompose.storage.mongo

import com.mongodb.MongoWriteException
import isel.tds.tictactoecompose.storage.Serializer
import isel.tds.tictactoecompose.storage.Storage


class MongoStorage<Key, Data>(
    collectionName: String,
    driver: MongoDriver,
    val serializer: Serializer<Data>,
) : Storage<Key, Data> {


    data class Doc(val _id: String, val data: String)

    val docs = driver.getCollection<Doc>(collectionName)

    private fun toDoc(key: Key, data: Data): Doc =
        Doc(key.toString(), serializer.serialize(data))

    override fun create(k: Key, data: Data) {
        try {
            docs.insertDocument<Doc>(toDoc(k, data))
        } catch (e: MongoWriteException) {
            error("Document $k already exists")
        }
    }

    override fun read(k: Key): Data? =
        docs.getDocument(k.toString())?.let {
            serializer.deserialize(it.data)
        }


    override fun update(k: Key, data: Data) {
        check(docs.replaceDocument(k.toString(), toDoc(k, data)))
        { "Document $k does not exist to update" }
    }

    override fun delete(k: Key) {
        check(docs.deleteDocument(k.toString()))
        { "Document $k does not exist to delete" }
    }

}