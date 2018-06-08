# CLI

There is a few CLI options that are occasionally useful in JVM
development. These flags are normally passed to Java and JavaC by your
IDE, however, sometimes things don't behave as expected or the run
configuration is misconfigured.

**NOTE**: I will ignore non-standard options (those that start with
`-X`)

## JavaC (The Java Compiler)

The `javac` command reads class and interface definitions, written in
the Java programming language, and compiles them into bytecode class
files.

There are two ways to pass source code file names to `javac`.

-   For a small number of source files, list the file names on the
    command line.

-   For a large number of source files, list the file names in a file
    that is separated by blanks or line breaks. Use the list file name
    preceded by an at sign (@) with the `javac` command.

**NOTE**: Source code file names must have `.java` suffixes, class
(bytecode) file names must have `.class` suffixes,

### Class Path

Specifies where to find user class files.

``` sh
javac -cp <path> # or -classpath <path>
```

### Destination Directory

Sets the destination directory for class files.

``` sh
javac -d <directory>
```

### Generating Code (Not Byte Code!)

Specifies the directory where to place the generated source files.

``` sh
java -s <directory>
```

### Source Path

Specifies the source code path to search for class or interface
definitions. As with the user class path, source path entries are
separated by colons (`:`).

``` sh
java -sourcepath <sourcepath>
```

### Verbosity

Uses verbose output, which includes information about each class loaded
and each source file compiled.

``` sh
javac -verbose
```

## Java (The Java Interpreter)

### Run Application

By default, the first argument that is not an option of the Java command
is the fully qualified name of the class to be called. Java starts the
application by calling that class's `main()` method.

``` java
public static void main(String[] args) { /* code */ }
```

### Run A JAR

If the `-jar` option is specified, its argument is the name of the JAR
file containing class and resource files for the application.

The start-up class must be indicated by the `Main-Class` manifest header
in its source code.

**NOTE**: Arguments after the class file name or the JAR file name are
passed to the `main()` method.

### Set System Property

Properties can be set in the code but also from the command line.

``` sh
java -Dproperty=value
```

### Disable Assertions

``` sh
java -disableassertions[:[packagename]...|:classname]
# OR java -da[:[packagename]...|:classname]
```

### Enable Assertions

``` sh
java -enableassertions[:[packagename]...|:classname]
# OR java -ea[:[packagename]...|:classname]
```

### Show Image During Java Start-Up

``` sh
java -splash:imgname
```

### Verbosity

``` sh
# Displays information about each loaded class.
java -verbose:class
```

# Displays information about each garbage collection (GC) event.
java -verbose:gc

# Displays information about the use of native methods and other Java Native Interface (JNI) activity.
java -verbose:jni
```

## References

-   man page `javac(1)`
-   man page `java(1)`
