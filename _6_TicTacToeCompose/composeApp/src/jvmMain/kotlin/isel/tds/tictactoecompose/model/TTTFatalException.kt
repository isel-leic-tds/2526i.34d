package isel.tds.tictactoecompose.model

open class TTTFatalException(msg: String) : Exception(msg)


class NoStorageException : TTTFatalException("No storage")

class NoChangesException : IllegalStateException("No Changes")