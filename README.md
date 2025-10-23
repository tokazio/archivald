# Archivald

Run Konsist/ArchUnit rules via KSP/java annotation processor to validate architecture at compile time.

There are 2 processors:

1. The first is active if no 'konsistKspClasspath' is given and is responsible to generate a META-INF/architecture-rules in which we list where we could find @ArchitectureRule 
2. The second is active if 'konsistKspClasspath' is given and is responsible to validate rules found in the META-INF/architecture-rules of the different jars in the given 'konsistKspClasspath' 

## Rule definition

A rule is a function annotated with @ArchitectureRule

This function should take a KonsistKspKoScopeCreator as the first parameter

Create a class to hold your rules.

## How it's work

Because the rules need to be compiled for the konsist ksp processor be able to run it
they can't simply live in the same project.

We introduce the `src/rules` to separate them from the project code.
This folder will be compiled dynamically by the konsist ksp processor.

Then you must provide him a classpath like, with gradle:

```
ksp {
    arg("konsistKspClasspath", configurations.testCompileClasspath.asPath)
}
```

For convenience, this following gradle configuration allow to compile the rules in the test source set.
The compilation result is not used but it allow you to check your code.
If your rules are already ok you doesn't need to configure this.

```
dependencies{
  testCompileOnly(project(":konsist-ksp")) //To compile src/rules
}

sourceSets.test.java.srcDirs += ['src/rules'] //To compile src/rules
```

## Configuration

Create a 'rules' folder in kotlin/src and put it your rules.

### Gradle

```
dependencies{
    ksp(project(":konsist-ksp"))
}

ksp {
    arg("konsistKspProjectBase", projectDir.absolutePath) //mandatory
    arg("konsistKspClasspath", configurations.testCompileClasspath.asPath)
    arg("konsistKspDebug", "true") //If needed
}
```

## Disclaimers

Konsist API is not fully implemented by this KSP processor.

This is using a lot of 'hacks' mainly based on the gradle architecture

### Dynamic compilation (src/rules)

Use a custom `ClassLoader` and `K2JVMCompiler` to dynamically compile and load the rules from src/rules to be used
during the 'main' compilation process.

The compilation is done using the private `exec` function of `K2JVMCompiler` reflectively.

For now it's not possible to find the classpath automatically that's why it must be provided by the processor option
`KONSIST_KSP_CLASSPATH_OPTION`

`src/rules` should be declared as a `test` src to be compiled by the ide even if this compilation will no be used

/!\ The whole project is based on this fragile dynamic compilation.

### Konsist hacking

We provide our own `KoScopeCreator` implementation to access the compiling files
We also provide our own `KoBaseProvider` `assertTrue` and `assertFalse` to handle assertions from ksp
duplicating a lot of private code from the originals ones in `com.lemonappdev.konsist.api.verify`
The main function with our custom implementation is the `KonsistKspAssertions.getResult` that wrap the failed validation
to a `KonsistKspKoAssertionFailedException`

/!\ beware to not use the original ones from `com.lemonappdev.konsist.api.verify` but the ones from
`fr.tokazio.konsistksp`

A `OriginalKonsistKspScopeCreator` is also provided to test the rules as usual from a test context, this one is a
wrapping the original `Konsist` object under the konsist ksp API.

# Modules descriptions

## annotations

Produce the `konsistksp-annotations` jar providing:

* `@ArchitectureRule`
* Scope creators (the konsist-ksp one and a wrapper on the original `Konsist` one)
* Assertions
* Exceptions

## example-project

Is a standalone gradle example project using:

* src/rules to provide its custom rules
* konsistksp-standard-rules jar providing already defined standard rules

## processor

Produce the `konsistksp-processor` jar providing the 2 processor described earlier

## standard-rules

Produce the `konsistksp-standard-rules` jar providing some rules about:

* Logging
* Style

# Debug

https://github.com/google/ksp/blob/main/DEVELOPMENT.md#debug-a-processor-andor-ksp

Kill KotlinCompileDaemon

```
./gradlew --stop; pkill -f KotlinCompileDaemon
```

Run

```
./gradlew -Dorg.gradle.debug=true -Dkotlin.daemon.jvm.options="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005" :konsist-example-project:kspKotlin
```

Attach a debugger to it using a run conf

Re run it

```
 ./gradlew -Dorg.gradle.debug=true -Dkotlin.daemon.jvm.options="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005" :konsist-example-project:kspKotlin
```

add `--rerun-tasks` to force recompilation
add `--stacktrace` to have detailed errors

# Logging

Define these 'gradle.properties' to enable logging:

```
org.gradle.logging.level=info
```

# Rule uuid

Use https://shortunique.id/ to generate short uuid to uniquely identify a rule
