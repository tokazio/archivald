package fr.tokazio.konsistksp.processor

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.common.impl.CodeGeneratorImpl
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFunction
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import fr.tokazio.konsistksp.ArchitectureRule
import org.jetbrains.kotlin.incremental.createDirectory
import java.io.File
import kotlin.math.log
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible

class ArchitectureRuleSymbolProcessor(
    private val logger: KSPLogger,
    codeGenerator: CodeGeneratorImpl,
) : SymbolProcessor {

    private val projectBase: File = CodeGeneratorImpl::class
        .declaredMemberProperties
        .first {
            it.name == "projectBase"
        }.apply {
            isAccessible = true
        }.get(codeGenerator) as File

    @OptIn(KspExperimental::class)
    override fun process(resolver: Resolver): List<KSAnnotated> {
        logger.info("Add @ArchitectureRule to META-INF/architecture-rules...")
        val metaInf = File("${projectBase.absolutePath}${File.separatorChar}src${File.separatorChar}main${File.separatorChar}resources${File.separatorChar}META-INF")
        if(!metaInf.exists()){
            metaInf.createDirectory()
        }
        val file = File("${metaInf.absolutePath}/architecture-rules")
        resolver.getSymbolsWithAnnotation(ArchitectureRule::class.java.name).forEach {
            val ksFun = it as KSFunctionDeclaration
            val f = "${it.parent}::$ksFun::${ksFun.parameters.map { it.type }.joinToString()}"
            logger.info("@ArchitectureRule on $f")
            file.appendText("$f\n")
        }
        return emptyList()
    }

}
