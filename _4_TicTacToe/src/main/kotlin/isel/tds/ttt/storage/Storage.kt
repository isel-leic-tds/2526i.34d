package isel.tds.isel.tds.ttt.storage

interface Storage<Key, Data> {
    // CRUD Operations
    /**
     * Creates a new entry in the storage.
     * @param k the key of the entry.
     * @param data the data to store.
     * @throws IllegalStateException if the key already exists.
     */
    fun create(k: Key, data: Data): Unit
    /**
     * Reads an entry from the storage.
     * @param k the key of the entry.
     * @return the data stored or null if the key does not exist.
     */
    fun read(k: Key): Data?
    /**
     * Updates an entry in the storage.
     * @param k the key of the entry.
     * @param data the new data to store.
     * @throws IllegalStateException if the key does not exist.
     */
    fun update(k: Key, data: Data)
    /**
     * Deletes an entry from the storage.
     * @param k the key of the entry.
     * @throws IllegalStateException if the key does not exist.
     */
    fun delete(k: Key)
}

