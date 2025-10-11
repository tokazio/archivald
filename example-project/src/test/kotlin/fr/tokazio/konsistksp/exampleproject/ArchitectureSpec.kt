package fr.tokazio.konsistksp.exampleproject

import fr.tokazio.konsistksp.KonsistKspKoAssertionFailedException
import fr.tokazio.konsistksp.OriginalKonsistKspScopeCreator
import fr.tokazio.konsistksp.rules.NoJavaIoDependencies
import io.kotest.core.spec.style.StringSpec

class ArchitectureSpec : StringSpec() {
    init {

        "should not use java.io" {
            try {
                NoJavaIoDependencies().noJavaIoDependencies(OriginalKonsistKspScopeCreator())
            } catch (ex: KonsistKspKoAssertionFailedException) {
                ex.asKoAssertionFailedException()
            }
        }
    }
}
