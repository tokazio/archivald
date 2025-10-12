package fr.tokazio.konsistksp.internal.collector

import fr.tokazio.konsistksp.internal.logger.Logger
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor
import java.util.function.Consumer

class CollectorEngine(
    private val logger: Logger,
    cores: Int =
        Runtime
            .getRuntime()
            .availableProcessors() / 2,
) {
    private val executorService: ThreadPoolExecutor = Executors.newFixedThreadPool(cores) as ThreadPoolExecutor
    private val fileFilters: MutableList<(name: String) -> Boolean> = mutableListOf()
    private val dirFilters: MutableList<(name: String) -> Boolean> = mutableListOf()
    private var files: MutableList<File>? = null

    fun collect(pathName: String): List<File> {
        files = CopyOnWriteArrayList()
        async(Paths.get(pathName), 0, (Consumer { file: File -> files!!.add(file) }))
        while (executorService.getActiveCount() > 0) {
            try {
                Thread.sleep(250)
            } catch (e: InterruptedException) {
                logger.exception(e)
            }
        }
        executorService.shutdown()
        return files!!
    }

    fun forEach(
        pathName: String?,
        consumer: Consumer<File?>,
    ) {
        async(Paths.get(pathName), 0, consumer)
        while (executorService.getActiveCount() > 0) {
            try {
                Thread.sleep(250)
            } catch (e: InterruptedException) {
                logger.exception(e)
            }
        }
        executorService.shutdown()
    }

    private fun async(
        path: Path,
        level: Int,
        consumer: Consumer<File?>,
    ) {
        executorService.submit {
            try {
                this@CollectorEngine.walk(path, level, consumer)
            } catch (e: IOException) {
                logger.exception(e)
            }
        }
    }

    private fun walk(
        path: Path,
        level: Int,
        consumer: Consumer<File?>,
    ) {
        // if at least one directory filter isn't true, then this directory is skipped (and all its children)
        var c = 0
        for (d in dirFilters) {
            if (!d(path.toString())) {
                logger.debug("Skipped path (and sub paths) of '" + path + "' (by directory filter #" + c + ")")
                return
            }
            c++
        }
        Files
            .newDirectoryStream(path)
            .use { stream ->
                for (entry in stream) {
                    if (Files.isDirectory(entry)) {
                        async(entry, level + 1, consumer)
                    } else {
                        // if at least one file filter isn't true, then this entry is skipped
                        val name = entry.toString()
                        var shouldHandle = true
                        for (f in fileFilters) {
                            shouldHandle = shouldHandle && f(name)
                        }
                        if (shouldHandle) {
                            consumer.accept(entry.toFile())
                        } else {
                            logger.debug("Skipped file '" + name + "'")
                        }
                    }
                }
            }
    }

    /**
     * CollectorFilter returning false will skip the file
     */
    fun fileFilter(filter: (name: String) -> Boolean): CollectorEngine {
        fileFilters.add(filter)
        return this
    }

    /**
     * CollectorFilter returning false will prevent directory scanning
     */
    fun dirFilter(filter: (name: String) -> Boolean): CollectorEngine {
        dirFilters.add(filter)
        return this
    }

    fun dirFilters(dirFilters: List<(name: String) -> Boolean>): CollectorEngine {
        for (filter in dirFilters) {
            dirFilter(filter)
        }
        return this
    }
}
