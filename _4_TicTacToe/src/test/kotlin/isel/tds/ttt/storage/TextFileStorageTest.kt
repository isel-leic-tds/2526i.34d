package isel.tds.ttt.storage

import isel.tds.ttt.storage.Serializer
import isel.tds.ttt.storage.Storage
import isel.tds.isel.tds.ttt.storage.TextFileStorage
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

class TextFileStorageTest{
    @Test
    fun `CRUD operations`(){

        val testSerializer = object: Serializer<Double>{
            override fun serialize(data: Double): String = data.toString()
            override fun deserialize(txt: String): Double = txt.toDouble()
        }
        val st: Storage<String,Double> = TextFileStorage<String, Double>( "testBaseDir", testSerializer)
        val key = "fileToSave1.save"
        st.create(key, 3.14)
        val readValue = st.read(key)
        assertEquals(3.14, readValue)

        st.delete(key)
        assertNull(st.read(key))
        assertFailsWith<IllegalStateException> { st.delete(key) }
        assertFailsWith<IllegalStateException> { st.update(key,0.0) }
    }
}