package fr.tokazio.archivald.konsist.bridge

import com.lemonappdev.konsist.api.container.KoScope
import com.lemonappdev.konsist.api.declaration.*
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceAndObjectDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndObjectDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoInterfaceAndObjectDeclaration
import fr.tokazio.archivald.internal.logger.Logger

class KonsistKspKoScope(
    private val logger: Logger,
    override val files: List<KoFileDeclaration>,
) : KoScope {
    override val packages: List<KoPackageDeclaration> =
        files
            .flatMap { koFileDeclaration ->
                koFileDeclaration.classes(includeNested = false, includeLocal = false).map { koClassDeclaration ->
                    koClassDeclaration.packagee
                }
            }.filterNotNull()

    override fun classes(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoClassDeclaration> =
        files.flatMap { koFileDeclaration ->
            koFileDeclaration.classes(includeNested, includeLocal)
        }

    override fun toString(): String {
        TODO("Not yet implemented")
    }

    // ================================================================================================================
    // ================================================================================================================
    // TODO handle
    // ================================================================================================================
    // ================================================================================================================

    override val annotations: List<KoAnnotationDeclaration>
        get() = TODO("Not yet implemented")
    override val imports: List<KoImportDeclaration>
        get() = TODO("Not yet implemented")
    override val typeAliases: List<KoTypeAliasDeclaration>
        get() = TODO("Not yet implemented")

    override fun classesAndInterfaces(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoClassAndInterfaceDeclaration> {
        TODO("Not yet implemented")
    }

    override fun classesAndInterfacesAndObjects(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoClassAndInterfaceAndObjectDeclaration> {
        TODO("Not yet implemented")
    }

    override fun classesAndObjects(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoClassAndObjectDeclaration> {
        TODO("Not yet implemented")
    }

    override fun declarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoBaseDeclaration> {
        TODO("Not yet implemented")
    }

    override fun equals(other: Any?): Boolean {
        TODO("Not yet implemented")
    }

    override fun functions(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoFunctionDeclaration> {
        TODO("Not yet implemented")
    }

    override fun hashCode(): Int {
        TODO("Not yet implemented")
    }

    override fun interfaces(includeNested: Boolean): List<KoInterfaceDeclaration> {
        TODO("Not yet implemented")
    }

    override fun interfacesAndObjects(includeNested: Boolean): List<KoInterfaceAndObjectDeclaration> {
        TODO("Not yet implemented")
    }

    override fun minus(scope: KoScope): KoScope {
        TODO("Not yet implemented")
    }

    override fun minusAssign(scope: KoScope) {
        TODO("Not yet implemented")
    }

    override fun objects(includeNested: Boolean): List<KoObjectDeclaration> {
        TODO("Not yet implemented")
    }

    override fun plus(scope: KoScope): KoScope {
        TODO("Not yet implemented")
    }

    override fun plusAssign(scope: KoScope) {
        TODO("Not yet implemented")
    }

    override fun print(
        prefix: String?,
        predicate: ((KoScope) -> String)?,
    ): KoScope {
        TODO("Not yet implemented")
    }

    override fun properties(includeNested: Boolean): List<KoPropertyDeclaration> {
        TODO("Not yet implemented")
    }

    override fun slice(predicate: (KoFileDeclaration) -> Boolean): KoScope {
        TODO("Not yet implemented")
    }
}
