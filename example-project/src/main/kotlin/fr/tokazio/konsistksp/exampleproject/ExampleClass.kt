package fr.tokazio.konsistksp.exampleproject.fail

import java.io.File

class ExampleClass {
    companion object {
        // should fail the rule 'Companion object is last declaration in the class'
    }

    fun doNothingWithAFile(file: File) {
        // Nothing
    }

    val propertyInWrongOrder: String = "should fail"
}
