package fr.tokazio.archivald.internal.model

interface Location {
    val fullFilename: String
    val lineNumber: Int

    fun asString(): String = "$fullFilename:$lineNumber"
}
