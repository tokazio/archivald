package fr.tokazio.konsistksp.konsist.provider

import com.google.devtools.ksp.symbol.FileLocation
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.Origin
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.provider.KoImportAliasProvider
import com.lemonappdev.konsist.api.provider.KoImportProvider
import fr.tokazio.konsistksp.internal.logger.Logger
import fr.tokazio.konsistksp.internal.model.File
import fr.tokazio.konsistksp.konsist.KonsistKspKoImportDeclaration
import fr.tokazio.konsistksp.ksp.bridge.model.KonsistKspFile
import fr.tokazio.konsistksp.ksp.bridge.model.KonsistKspImport

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
                                    KonsistKspImport(
                                        location =
                                            FileLocation(
                                                filePath = file.filePath,
                                                lineNumber = lineNum + 1,
                                            ),
                                        origin = Origin.KOTLIN,
                                        parent = file.asKSFile(),
                                    ),
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

private fun File.asKSFile(): KSFile = (this as KonsistKspFile).inner
