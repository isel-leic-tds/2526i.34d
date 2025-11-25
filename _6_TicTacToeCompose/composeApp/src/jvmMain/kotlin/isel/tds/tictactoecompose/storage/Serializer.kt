package isel.tds.tictactoecompose.storage

interface Serializer<Data> {
    fun serialize(d: Data): String
    fun deserialize(txt: String): Data
}