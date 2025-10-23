package fr.tokazio.archivald.konsist.bridge.provider

import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.provider.KoImportAliasProvider
import com.lemonappdev.konsist.api.provider.KoImportProvider
import fr.tokazio.archivald.internal.logger.Logger
import fr.tokazio.archivald.internal.model.File
import fr.tokazio.archivald.internal.model.ImportDeclaration
import fr.tokazio.archivald.internal.model.Location
import fr.tokazio.archivald.konsist.bridge.KonsistKspKoImportDeclaration

interface KonsistKspKoImportProvider :
    KoImportProvider,
    KoImportAliasProvider {
    val logger: Logger

    override fun hasImport(predicate: (KoImportDeclaration) -> Boolean): Boolean =
        imports.firstOrNull { koImportDeclaration: KoImportDeclaration ->
            predicate(koImportDeclaration)
        } != null

    override fun hasImportAlias(predicate: (KoImportAliasDeclaration) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasImportAliasWithName(
        name: String,
        vararg names: String,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasImportAliasWithName(names: Collection<String>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasImportAliases(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasImportAliasesWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasImportAliasesWithAllNames(names: Collection<String>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasImportWithName(
        name: String,
        vararg names: String,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasImportWithName(names: Collection<String>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasImports(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasImportsWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasImportsWithAllNames(names: Collection<String>): Boolean {
        TODO("Not yet implemented")
    }

    fun getImportsFromSourceFile(file: File): List<KoImportDeclaration> {
        val imports = mutableListOf<KoImportDeclaration>()

        // Split content into lines
        val lines =
            java.io
                .File(file.filePath)
                .readText()
                .lines()

        var inMultiLineComment = false

        if (lines.isNotEmpty()) {
            for (lineNum in 0..<lines.size) {
                val line = lines[lineNum]
                val trimmedLine = line.trim()

                // Skip empty lines
                if (trimmedLine.isEmpty()) continue

                // Handle multi-line comments
                if (trimmedLine.startsWith("/*")) {
                    inMultiLineComment = true
                    if (trimmedLine.contains("*/")) {
                        inMultiLineComment = false
                    }
                    continue
                }

                if (inMultiLineComment) {
                    if (trimmedLine.contains("*/")) {
                        inMultiLineComment = false
                    }
                    continue
                }

                // Skip single-line comments
                if (trimmedLine.startsWith("//")) continue

                // Check for import statements
                if (trimmedLine.startsWith("import ")) {
                    val importStatement = extractImportStatement(trimmedLine)
                    if (importStatement.isNotEmpty()) {
                        imports.add(
                            KonsistKspKoImportDeclaration(
                                logger = logger,
                                konsistKspImport =
                                    object : ImportDeclaration {
                                        override val location: Location
                                            get() =
                                                object : Location {
                                                    override val fullFilename: String
                                                        get() = file.filePath
                                                    override val lineNumber: Int
                                                        get() = lineNum + 1
                                                }
                                    },
                                importString = importStatement,
                            ),
                        )
                    }
                }

                // Stop parsing imports when we hit the first non-import, non-comment, non-package declaration
                if (!trimmedLine.startsWith("package ") &&
                    !trimmedLine.startsWith("import ") &&
                    !trimmedLine.startsWith("@file:") &&
                    !trimmedLine.startsWith("/*") &&
                    !trimmedLine.startsWith("//") &&
                    !trimmedLine.startsWith("*/")
                ) {
                    break
                }
            }
        }

        return imports
    }

    private fun extractImportStatement(line: String): String =
        try {
            // Remove "import " prefix
            var importPath =
                line
                    .substring(6)
                    .trim()

            // Handle import aliases (import com.example.LongName as Short)
            val asIndex = importPath.indexOf(" as ")
            if (asIndex != -1) {
                importPath =
                    importPath
                        .substring(0, asIndex)
                        .trim()
            }

            // Remove semicolon if present (shouldn't be in Kotlin, but just in case)
            if (importPath.endsWith(";")) {
                importPath = importPath.substring(0, importPath.length - 1)
            }

            importPath
        } catch (_: Exception) {
            logger.warn("Failed to parse import statement: $line")
            ""
        }
}

// private fun File.asKSFile(): KSFile = (this as KonsistKspFile).inner
