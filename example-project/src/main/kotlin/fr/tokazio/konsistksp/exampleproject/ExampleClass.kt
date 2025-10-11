package fr.tokazio.konsistksp.exampleproject

import java.io.File

class ExampleClass {
    companion object {
        // should be at the end
    }

    fun doNothingWithAFile(file: File) {
        file.isFile
    }
}
