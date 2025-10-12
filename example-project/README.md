# How to configure your gradle project to use konsist-ksp for validation

## Show ksp logs

Provide a `gradle.properties` at your project root containing

```
org.gradle.logging.level=info
#or
org.gradle.logging.level=debug
```

## Enable ksp processing

In your `build.gradle` file

```
plugins{
    id "org.jetbrains.kotlin.jvm" version "2.2.0"
    id "com.google.devtools.ksp" version "2.0.2"
}
```

## Enable @ArchitectureRule validation

In your `build.gradle` file

```
dependencies {
    ksp "fr.tokazio.konsistksp:konsistksp-processor:{latest}"
}
```

You must configure the ksp processor so that it will be able to validate your project

In your `build.gradle` file

```
ksp {
    arg("konsistKspProjectBase", projectDir.absolutePath)
    arg("konsistKspClasspath", configurations.testCompileClasspath.asPath)
}
```

Has we provide a `konsistKspClasspath` the processor will be used as a validation one.

You can optionally the the debug mode

```
ksp{
    arg("konsistKspDebug", "true")
}
```

## Enable src/rules to provide your custom rules

In your `build.gradle` file

```
dependencies {
    testImplementation "fr.tokazio.konsistksp:konsistksp-annotations:{latest}"
}
```

We target test sourceSet voluntarily

## Enable src/rules IDE compilation

In your `build.gradle` file

```
sourceSets.test.java.srcDirs += ['src/rules']
```

## Use the provided standard-rules

In your `build.gradle` file

```
dependencies {
    testImplementation "fr.tokazio.konsistksp:konsistksp-standard-rules:{latest}"
}
```

# How to configure your gradle project to use konsist-ksp for rule generation

Your project can only serve as a rule provider that will be used in another projects.

## Show ksp logs

Provide a `gradle.properties` at your project root containing

```
org.gradle.logging.level=info
#or
org.gradle.logging.level=debug
```

## Enable ksp processing

In your `build.gradle` file

```
plugins{
    id "org.jetbrains.kotlin.jvm" version "2.2.0"
    id "com.google.devtools.ksp" version "2.0.2"
}
```

## Enable rule metadata generation

This will generate a META-INF/architecture-rules file in your project.
This file will contains the description of the @ArchitectureRule functions found in your project
to be used later by the validation processor.

In your `build.gradle` file

```
dependencies{
    ksp "fr.tokazio.konsistksp:konsistksp-processor"
}

ksp {
    arg("konsistKspProjectBase", projectDir.absolutePath)
}
```

Has we're NOT providing the `konsistKspClasspath` the processor will be used as a generator one.

You can optionally the the debug mode

```
ksp{
    arg("konsistKspDebug", "true")
}
```

## Providing a rule

A rule is a class's function marked @ArchitectureRule with two parameters:

* koScopeCreator: KonsistKspScopeCreator
* packageName: String

Apart of this specificity you could write your assertion like a regular Konsist assertion

for example:

```
    import com.lemonappdev.konsist.api.verify.assertTrue

    ...

    @ArchitectureRule
    fun doNotUseALoggerFromAnotherClass(
        koScopeCreator: KonsistKspScopeCreator,
        packageName: String,
    ) {
        koScopeCreator
            .scopeFromPackage("$packageName..")
            .files
            .filter { it.sourceSetName == "main" }
            .assertTrue {
                if (it.imports.isNotEmpty()) {
                    !it.hasImport { import ->
                        import.hasNameContaining("Companion.logger")
                    }
                } else {
                    true
                }
            }
    }
```

/!\ the assertion must come from `fr.tokazio.konsistksp`not from `com.lemonappdev.konsist`