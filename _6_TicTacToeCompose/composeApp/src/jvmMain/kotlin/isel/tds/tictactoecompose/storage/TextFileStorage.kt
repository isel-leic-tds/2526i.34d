package isel.tds.tictactoecompose.storage


import okio.*
import okio.Path.Companion.toPath

class TextFileStorage<Key, Data>(
    baseDirectory: String,
    val serializer: Serializer<Data>,
) : Storage<Key, Data> {

    val fs = FileSystem.SYSTEM

    val basePath: Path = baseDirectory.toPath()

    init {
        with(basePath) {
            if (!exists()) createDirectory()
            else check(isDirectory())
        }
    }


    fun Path.exists() = fs.exists(this)

    private fun <R> withPath(key: Key, fx: Path.() -> R): R = (basePath / "$key.txt").fx()

    private fun <R> withPath2(key: Key, fx: (Path) -> R): R = fx((basePath / "$key.txt"))

    private fun Path.writeText(data: Data): Unit {
        fs.write(this) { this.writeUtf8(serializer.serialize(data)) }
    }

    private fun Path.readText(): Data? = fs.read(this) { serializer.deserialize(this.readUtf8()) }

    private fun Path.delete(): Unit = fs.delete(this)
    private fun Path.createDirectory() = fs.createDirectory(this)
    private fun Path.isDirectory() = fs.metadata(this).isDirectory

//    override fun create(key: Key, data: Data) {
//        val filePath = (basePath / "$key.txt")
//        check( !filePath.exists()) { "File already exists!" }
//        fs.write(filePath){ this.writeUtf8(serializer.serialize(data))}
//    }

//    override fun create(key: Key, data: Data): Unit = withPath(key){
//        check( !exists()) { "File already exists!" }
//        writeText(data)
//    }

    override fun create(key: Key, data: Data): Unit = withPath2(key) { filePath ->
        check(!filePath.exists()) { "File already exists!" }
        filePath.writeText(data)
    }

    //    override fun read(key: Key): Data? {
//        val filePath = (basePath / "$key.txt")
//        return if( !filePath.exists()) null
//            else fs.read(filePath) { serializer.deserialize(this.readUtf8())}
//    }
    override fun read(key: Key): Data? = withPath(key) {
        if (!this.exists()) null
        else readText()
    }

//    override fun update(k: Key, data: Data) {
//        val filePath =  (basePath / "$k.txt")
//        check(filePath.exists()) { "File $k does not exists" }
//        fs.write(filePath) { this.writeUtf8(serializer.serialize(data)) }
//    }

    override fun update(k: Key, data: Data) = withPath(k) {
        check(exists()) { "File $k does not exists" }
        writeText(data)
    }

//    override fun delete(k: Key) {
//        val filePath =  (basePath / "$k.txt")
//        check(filePath.exists()) { "File $k does not exists" }
//        fs.delete(filePath)
//    }

    override fun delete(k: Key) = withPath(k) {
        check(exists()) { "File $k does not exists" }
        delete()
    }


}