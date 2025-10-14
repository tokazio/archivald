package fr.tokazio.konsistksp.rules

import com.lemonappdev.konsist.api.ext.list.functions
import fr.tokazio.konsistksp.ArchitectureRule
import fr.tokazio.konsistksp.KonsistKspScopeCreator
import fr.tokazio.konsistksp.assertTrue

class ControllerRules {
    // TODO maybe marked @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    // TODO @xxMapping fun should avec validation annotations on its parameters
    // : @NormalizesValidObjectId or @ValidUUID on ...Id
    // : @Valid on complex objects
    // TODO @TookMsOutput on listXXX functions

    @ArchitectureRule("A controller should have @RestController")
    fun controllerFileShouldHaveRestControllerAnnotation(konsistScope: KonsistKspScopeCreator) {
        konsistScope
            .scopeFromPackage(BASE_PACKAGE)
            .classes()
            .filter { classDeclaration ->
                classDeclaration.name.endsWith("Controller")
            }.forEach { classDeclaration ->
                classDeclaration.assertTrue { classDeclaration ->
                    classDeclaration.annotations.map { it.fullyQualifiedName }.containsAll(
                        listOf(
                            "org.springframework.web.bind.annotation.RestController",
                        ),
                    )
                }
            }
    }

    @ArchitectureRule("@RestController should have @Validated")
    fun restControllerShouldHaveValidatedAnnotation(konsistScope: KonsistKspScopeCreator) {
        konsistScope
            .scopeFromPackage(BASE_PACKAGE)
            .classes()
            .filter { classDeclaration ->
                classDeclaration.annotations.map { it.fullyQualifiedName }.contains(
                    "org.springframework.web.bind.annotation.RestController",
                )
            }.forEach { classDeclaration ->
                classDeclaration.assertTrue { classDeclaration ->
                    classDeclaration.annotations.map { it.fullyQualifiedName }.containsAll(
                        listOf(
                            "org.springframework.validation.annotation.Validated",
                        ),
                    )
                }
            }
    }

    @ArchitectureRule("Functions of a controller should have validation annotation")
    fun controllerFunctionsArgsShouldHaveValidationAnnotations(konsistScope: KonsistKspScopeCreator) {
        konsistScope
            .scopeFromPackage(BASE_PACKAGE)
            .classes()
            .filter { classDeclaration ->
                classDeclaration.annotations
                    .map { it.fullyQualifiedName }
                    .contains(
                        "org.springframework.web.bind.annotation.RestController",
                    )
            }.functions()
            .forEach { functionDeclaration ->
                functionDeclaration.parameters.assertTrue { parameterDeclaration ->
                    parameterDeclaration.annotations
                        .map { it.fullyQualifiedName }
                        .any {
                            setOf(
                                "javax.validation.Valid",
                                "javax.validation.NotEmpty",
                                "javax.validation.Size",
                                "io.biznet.commons.validator.NormalizedValidObjectId",
                                "io.biznet.commons.validator.ValidUUID",
                            ).contains(it)
                        }
                }
            }
    }
}
