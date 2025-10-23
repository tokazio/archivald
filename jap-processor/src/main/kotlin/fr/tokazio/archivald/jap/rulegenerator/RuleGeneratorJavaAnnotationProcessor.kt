package fr.tokazio.archivald.jap.rulegenerator

import com.google.auto.service.AutoService
import fr.tokazio.archivald.internal.ARCHIVALD_DEBUG_OPTION
import fr.tokazio.archivald.jap.bridge.ArchivaldJapSymbolResolver
import fr.tokazio.archivald.jap.bridge.JapLogger
import fr.tokazio.archivald.rulegenerator.RuleGenerator
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement

@SupportedAnnotationTypes("fr.tokazio.archivald.ArchitectureRule")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
@AutoService(Processor::class)
class RuleGeneratorJavaAnnotationProcessor : AbstractProcessor() {
    private lateinit var ruleGenerator: RuleGenerator

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
