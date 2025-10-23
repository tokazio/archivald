package fr.tokazio.archivald.exampleproject

import org.springframework.beans.factory.annotation.Autowired
import java.io.File
import javax.inject.Inject

class ExampleClass {
    @Inject
    lateinit var injected: String

    @Autowired
    lateinit var autowired: String

    companion object {
        // should fail the rule 'Companion object is last declaration in the class'
    }

    fun doNothingWithAFile(file: File) {
        // Nothing
    }

    val propertyInWrongOrder: String = "should fail"
}
