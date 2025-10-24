package isel.tds.isel.tds.ttt.storage

interface Serializer<Data>{
    fun serialize(d: Data): String
    fun deserialize(txt: String): Data
}