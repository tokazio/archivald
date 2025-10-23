package fr.tokazio.archivald.internal.kotlin

import fr.tokazio.archivald.internal.ARCHIVALD_CLASSPATH_OPTION
import fr.tokazio.archivald.internal.logger.Logger
import org.jetbrains.kotlin.cli.common.CLICompiler
import org.jetbrains.kotlin.cli.common.ExitCode
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.cli.common.messages.MessageRenderer
import org.jetbrains.kotlin.cli.common.messages.PrintingMessageCollector
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler
import org.jetbrains.kotlin.config.Services
import java.io.File
import java.io.OutputStream
import java.io.PrintStream
import java.lang.reflect.Method

const val CLASSPATH_SEPARATOR = ":"

class KotlinCompiler(
    projectBase: File,
    private val logger: Logger,
    options: Map<String, String>,
) {
    val rule_classes_path = "${projectBase.absolutePath}/build/ksp/konsist/classes"
    private val cp =
        listOfNotNull(
            options[ARCHIVALD_CLASSPATH_OPTION],
            rule_classes_path,
        ).joinToString(CLASSPATH_SEPARATOR)
    val myPrintStream =
        PrintStream(
            object : OutputStream() {
                private val buf = StringBuilder()

                override fun write(b: Int) {
                    val c = b.toChar()
                    if (c == '\n') {
                        logger.debug("[Konsist ksp dynamic compiler] $buf")
                        buf.clear()
                    } else {
                        buf.append(c)
                    }
                }
            },
        )
    val compilerMessageCollector =
        PrintingMessageCollector(
            myPrintStream,
            MessageRenderer.GRADLE_STYLE,
            true,
        )

    fun compile(files: Collection<File>) {
        logger.debug("compilation using classpath $cp")
        logger.debug("compilation to $rule_classes_path")

        // Create an instance of K2JVMCompilerArguments
        val compilerArgs =
            K2JVMCompilerArguments().apply {
                // Specify the source files to compile
                freeArgs = files.map { it.absolutePath }
                // Set the classpath (if needed)
                classpath = cp
                // Specify the output directory for compiled classes
                destination = rule_classes_path
                // Additional compiler options can be set here
                noStdlib = true
                noReflect = true
                repl = false
            }

        // Create an instance of the compiler
        val compiler = K2JVMCompiler()

        val exec: Method =
            CLICompiler::class.java.declaredMethods
                .first {
                    it.name == "exec" &&
                        it.parameterCount == 3 &&
                        it.parameterTypes[0].name == MessageCollector::class.java.name &&
                        it.parameterTypes[1].name == Services::class.java.name
                }.apply {
                    isAccessible = true
                }

        // Compile the source files
        val exitCode =
            exec.invoke(
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
