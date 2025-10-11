package fr.tokazio.konsistksp.kotlin

import fr.tokazio.konsistksp.api.Logger
import java.io.File
import java.io.FileInputStream
import java.nio.file.Paths
import java.util.jar.JarFile
import kotlin.io.path.absolutePathString

class FileClassLoader(
    private val logger: Logger,
    private val parentClassLoader: ClassLoader,
) : ClassLoader(parentClassLoader) {
    private val loaded = mutableMapOf<String, Class<*>>()

    public override fun findClass(name: String): Class<*>? =
        if (name.contains(".jar://")) {
            val split = name.split("://")
            val jarFile = split[0]
            val className = split[1]
            val classJarPath = "${className.replace(".", "/")}.class"
            if (loaded.containsKey(className)) {
                loaded[className]
            } else {
                JarFile(jarFile).use { jar ->
                    val classFileEntry =
                        jar.entries().asSequence().firstOrNull {
                            it.name == classJarPath
                        }
                    if (classFileEntry != null) {
                        loadClass(className, jar.getInputStream(classFileEntry).readAllBytes())
                    } else {
                        null
                    }
                }
            }
        } else if (name.startsWith("/")) {
            val fromMainFolder =
                if (name.indexOf("classes/") > 0) {
                    name.substring(name.lastIndexOf("classes/") + 8)
                } else {
                    name.substring(name.lastIndexOf("main/") + 5)
                }
            val className =
                fromMainFolder
                    .replace(".class", "")
                    .replace("${File.separatorChar}", ".")
            if (loaded.containsKey(className)) {
                loaded[className]
            } else {
                val resolvedPath =
                    Paths
                        .get(name)
                        .normalize()
                logger.debug("FileClassLoader loading $className from ${resolvedPath.absolutePathString()}")
                val b: ByteArray = FileInputStream(resolvedPath.absolutePathString()).readAllBytes()
                loadClass(className, b)
            }
        } else {
            null
        }

    private fun loadClass(
        className: String,
        b: ByteArray,
    ): Class<*> {
        val loadedClass = defineClass(className, b, 0, b.size)
        loaded[className] = loadedClass
        return loadedClass
    }
}
