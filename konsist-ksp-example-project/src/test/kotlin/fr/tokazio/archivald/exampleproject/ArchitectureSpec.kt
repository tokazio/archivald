package fr.tokazio.archivald.exampleproject

import fr.tokazio.archivald.KonsistKspKoAssertionFailedException
import fr.tokazio.archivald.OriginalKonsistKspScopeCreator
import fr.tokazio.archivald.rules.NoJavaIoDependencies
import io.kotest.core.spec.style.StringSpec

class ArchitectureSpec : StringSpec() {
    init {

        "should not use java.io" {
            try {
                NoJavaIoDependencies().noJavaIoDependencies(OriginalKonsistKspScopeCreator)
            } catch (ex: KonsistKspKoAssertionFailedException) {
                ex.asKoAssertionFailedException()
            }
        }
    }
}
