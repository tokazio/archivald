package fr.tokazio.konsistksp.internal.model

interface File : Declaration {
    val packageName: String
    val fileName: String
    val filePath: String
}
