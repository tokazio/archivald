package fr.tokazio.konsistksp.kotlin

import fr.tokazio.konsistksp.api.Logger
import fr.tokazio.konsistksp.logger.KonsistKspLogger
import org.jetbrains.kotlin.cli.common.ExitCode
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments
import org.jetbrains.kotlin.cli.common.messages.MessageRenderer
import org.jetbrains.kotlin.cli.common.messages.PrintingMessageCollector
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler
import org.jetbrains.kotlin.config.Services
import java.io.File
import java.io.OutputStream
import java.io.PrintStream
import kotlin.reflect.KFunction
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.jvm.isAccessible

class KotlinCompiler(
    projectBase: File,
    private val logger: Logger,
    private val options: Map<String, String>,
) {
    val rule_classes_path = "${projectBase.absolutePath}/build/ksp/konsist/classes"

    fun compile(files: Collection<File>) {
        var cp = options["konsistKspClasspath"]
        cp += ":$rule_classes_path" // Own compilation folder
        logger.debug("Konsist ksp compilation using classpath $cp")
        logger.debug("Konsist ksp compilation to $rule_classes_path")

        // Create an instance of K2JVMCompilerArguments
        val compilerArgs = K2JVMCompilerArguments().apply {
            // Specify the source files to compile
            freeArgs = files.map { it.absolutePath }

            // Set the classpath (if needed)
            classpath = cp

            // Specify the output directory for compiled classes
            destination = rule_classes_path

            // Additional compiler options can be set here
            noStdlib = true
            noReflect = true
        }

        // Create an instance of the compiler
        val compiler = K2JVMCompiler()

        val exec: KFunction<*> by lazy {
            compiler::class.declaredFunctions.first {
                it.name == "shouldRunK2"
            }.apply {
                isAccessible = true
            }
        }

        val myPrintStream = PrintStream(object : OutputStream() {
            private val buf = StringBuilder()

            override fun write(b: Int) {
                val c = b.toChar()
                if (c == '\n') {
                    logger.debug("Konsist ksp compiler: $buf")
                    buf.clear()
                } else {
                    buf.append(c)
                }
            }
        })

        val compilerMessageCollector = PrintingMessageCollector(
            myPrintStream,
            MessageRenderer.GRADLE_STYLE,
            true,
        )

        // Compile the source files
        val exitCode = exec.call(
            compiler,
            compilerMessageCollector,
            Services.EMPTY,
            compilerArgs,
        )

        // Check the result
        if (exitCode == ExitCode.OK) {
            logger.debug("Konsist ksp successfully compiled $files")
        } else {
            logger.debug("Konsist ksp $files compilation failed with exit code $exitCode")
        }
    }
}

