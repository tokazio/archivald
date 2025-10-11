package fr.tokazio.konsistksp.api

interface File : Symbol {
    val packageName: String
    val filePath: String
    val fileName: String
    val annotations: Sequence<Annotation>
}