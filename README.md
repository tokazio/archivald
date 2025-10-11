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

# Logging

Define these 'gradle.properties' to enable logging:

```
org.gradle.logging.level=info
```

# Konsist ksp

Run Konsist rules via KSP annotation processor to validate architecture at compile time.

There is 2 processors:

1. The first is active if no 'konsistKspClasspath' is given and is responsible to generate a META-INF/architecture-rules in which we list where we could find @ArchitectureRule 
2. The second is active if 'konsistKspClasspath' is given and is responsible to validate rules found in the META-INF/architecture-rules of the different jars in the given 'konsistKspClasspath' 

## Rule definition

A rule is a function annotated with @ArchitectureRule

This function should take a KonsistKspKoScopeCreator as the first parameter

Create a class to hold your rules.

## How it's work

Because the rules need to be compiled for the konsist ksp processor be able to run it
they can't simply live in the same project.

We introduce the src/rules to separate them from the project code.
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

## TODO

* handle rules from external jar / project