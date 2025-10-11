package fr.tokazio.konsistksp.api

interface File : Declaration {
    val packageName: String
    val fileName: String
    val filePath: String
}
