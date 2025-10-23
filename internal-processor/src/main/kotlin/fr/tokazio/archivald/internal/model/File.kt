package fr.tokazio.archivald.internal.model

interface File : Declaration {
    val fileName: String
    val filePath: String
}
