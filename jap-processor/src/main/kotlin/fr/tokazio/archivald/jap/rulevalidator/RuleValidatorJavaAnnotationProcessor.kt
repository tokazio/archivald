package fr.tokazio.archivald.jap.rulevalidator

import com.google.auto.service.AutoService
import fr.tokazio.archivald.ArchitectureRule
import fr.tokazio.archivald.internal.ARCHIVALD_DEBUG_OPTION
import fr.tokazio.archivald.jap.bridge.ArchivaldJapSymbolResolver
import fr.tokazio.archivald.jap.bridge.JapLogger
import fr.tokazio.archivald.rulegenerator.RuleGenerator
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement

@AutoService(Processor::class)
class RuleValidatorJavaAnnotationProcessor : AbstractProcessor() {
    private lateinit var ruleGenerator: RuleGenerator

    override fun getSupportedAnnotationTypes(): Set<String> = setOf(ArchitectureRule::class.java.name)

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latestSupported()

    @Override
    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)
        ruleGenerator =
            RuleGenerator(
                logger = JapLogger(processingEnv.messager, processingEnv.options[ARCHIVALD_DEBUG_OPTION] == "true"),
                options = processingEnv.options,
            )
    }

    override fun process(
        annotations: Set<TypeElement?>?,
        roundEnv: RoundEnvironment,
    ): Boolean {
        ruleGenerator.process(ArchivaldJapSymbolResolver(roundEnv)).map {
            it
        }
        return true
    }
}
