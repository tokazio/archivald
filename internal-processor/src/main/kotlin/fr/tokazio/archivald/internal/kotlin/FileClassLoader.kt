package fr.tokazio.archivald.internal.kotlin

import fr.tokazio.archivald.internal.logger.Logger
import java.io.File
import java.io.FileInputStream
import java.nio.file.Paths
import java.util.jar.JarFile
import kotlin.io.path.absolutePathString

class FileClassLoader(
    private val logger: Logger,
    parentClassLoader: ClassLoader,
) : ClassLoader(parentClassLoader) {
    private val loaded = mutableMapOf<String, Class<*>>()

    public override fun findClass(name: String): Class<*>? =
        when {
            name.contains(".jar://") -> loadFromJar(name)
            name.startsWith("/") -> loadFromFilesystem(name)
            else -> null
        }

    private fun loadFromJar(name: String): Class<*> {
        val (jarFile, className) = name.split("://")
        return loaded.getOrPut(className) {
            loadClassFromJar(jarFile, className)
        }
    }

    private fun loadFromFilesystem(name: String): Class<*> {
        val className = extractClassName(name)
        return loaded.getOrPut(className) {
            loadClassFromFile(name, className)
        }
    }

    private fun loadClassFromJar(
        jarFile: String,
        className: String,
    ): Class<*> =
        JarFile(jarFile).use { jar ->
            val classJarPath = "${className.replace(".", "/")}.class"
            val classFileEntry =
                jar.entries().asSequence().firstOrNull {
                    it.name == classJarPath
                }
            if (classFileEntry != null) {
                loadClass(className, jar.getInputStream(classFileEntry).readAllBytes())
            } else {
                throw IllegalStateException("Class '$className' not found in jar file $jarFile")
            }
        }

    private fun extractClassName(name: String): String {
        val fromMainFolder =
            if (name.indexOf("classes/") > 0) {
                name.substring(name.lastIndexOf("classes/") + 8)
            } else {
                name.substring(name.lastIndexOf("main/") + 5)
            }
        return fromMainFolder
            .replace(".class", "")
            .replace("${File.separatorChar}", ".")
    }

    private fun loadClassFromFile(
        name: String,
        className: String,
    ): Class<*> {
        val resolvedPath =
            Paths
                .get(name)
                .normalize()
        logger.debug("${this::class.java.simpleName} loading $className from ${resolvedPath.absolutePathString()}")
        val b: ByteArray = FileInputStream(resolvedPath.absolutePathString()).readAllBytes()
        return loadClass(className, b)
    }

    private fun loadClass(
        className: String,
        b: ByteArray,
    ): Class<*> =
        defineClass(className, b, 0, b.size).also {
            loaded[className] = it
        }
}
