# Architectural Design

-   Focuses on how software systems should be organized and designed.
-   The link design and requirements engineering because it identifies the main
    structural components in a system and relationships between those
    components.
-   It's output is an **architectural model** that describes the structure of
    the system and how it's components communicate.
-   Bad design may lead to refactoring of the system architecture which may be
    expensive. Therefore even in agile methods, some planning needs to be done
    in advance to minimise future loss.

## Architectural Representations

**Informal Diagrams**

-   simple informal block diagrams showing entities and relationships are the
    most frequently used methods for documenting software architecture
-   lack semantics
-   do not show the types of relationships between entities
-   do not show the properties of entities

**Box and Line Diagrams**

-   very abstract
-   do not show the nature of component relationships not the externally
    visible properties of the subsystems
-   useful for communication with stakeholders and project planning

## Architectural Abstraction

**Small**

-   Focus on the architecture of individual programs.
-   Focus on how an individual program is decomposed into components.

**Large**

-   Focus on the architecture of complex **enterprise systems** that include
    other systems, programs and program components. Such enterprise systems are
    **distributed** over different computers which may be owned and managed by
    different companies.

## Advantages of Explicit Architecture

-   Stakeholder communication
-   System analysis (analysis if the system can meet it's non-functional
    requirements)
-   Large-scale reuse (the same architecture can be used in another project)

## Design Decisions

-   Creative process
-   Differs depending on the type of system being developed
-   There are a number of common decisions that span all design processes
    -   is there an existing, generic architectural pattern that can be reused
        and act as a template for developing this system?
    -   how will the system be distributed?
    -   what will be the approach used to structure the system?
    -   how will the components be decomposed into subcomponents?
    -   how will the architecture be documented?
    -   what architectural patterns or styles might be useful in creation of
        the components?

## Architecture Reuse

-   Systems in the same domain often have a similar architectures that reflect
    domain concepts
-   The architecture of a system may be designed around one of more
    architectural patterns or 'styles'.

## System Characteristics

-   Security
    -   use a layered architecture with critical assets in the inner layers
-   Performance
    -   locate critical operations and minimise communications
    -   use large rather than fine-grained components
-   Availability
-   Safety
    -   locate safety-critical features in a small number of subsystems
-   Maintainability
    -   use find-grain, replaceable components

Forms acronym: `SPASM`.

## Architectural Views

Each architectural model only shows one view (perspective) of the system.

E.g. it might show how the system is decomposed into modules, how the run-time
processes interact.

Some people argue that the Unified Modelling Language (UML) is an appropriate
notation for describing and documenting system architectures.

### 4 + 1 View of Software Architecture

-   Logical View
    -   shows key abstractions as objects or object classes
-   Physical View
    -   shows the system hardware
    -   how software components are distributed across processors in the system
-   Development View
    -   shows how the system is decomposed for development
-   Process View
    -   how the system is composed of running processes.

## Architectural Patterns

-   meant for representing, sharing and reusing knowledge
-   stylized description of good design practice -- tried and tested in
    different environments
-   patterns should include description of when they are and aren't useful
-   they might be represented using a tabular or graphical form

### Pipe and Filter Architecture

Similar to the idea of UNIX shell pipelines. E.g.:

``` {.sh}
cat ./table.csv | grep 21 | sort | head -n 15 | sed -E 's/.*/'&'/g' | vim -
```

Process inputs by supplying the result of *f~1~* to *f~2~* and the result of
*f~2~* to *f~3~* etc. Data flows through a series of functions to finally
produce the desired outcome.

**Advantages**:

-   simple
-   variants of this approach are widely used, e.g. in Java:

``` {.java}
data.stream()
    .filter(this::isOk)
    .map(Object::toString)
    .filter(x -> x.length() >= MIN_LEN)
    .map(String::toUpperCase)
    .sum();
```

**Disadvantages**:

-   not suitable for interactive systems

### Layered Architecture

-   used to model the interfacing of subsystems
-   organises a system into a set of layers
-   each layer provides some core functionality (e.g. UI, data storage) --
    components in each layer are related
-   supports incremental development of sub-systems in different layers
-   when a layer interface changes only the adjacent layers are affected
-   it's often artificial to structure systems in this way
-   lowest layer stores services that are used throughout the system

<!-- -->
    ------------------------------------------------------
    |                           UI                       |
    ------------------------------------------------------
    |          authentication and authorisation          |
    ------------------------------------------------------
    |   core business logic, application functionality   |
    ------------------------------------------------------
    |            system support (OS, database)           |
    ------------------------------------------------------

**Advantages**:

-   entire layers can be replaced i.e. they are pluggable so long as the
    interface is maintained

**Disadvantages**:

-   complete separation between layers is in practice difficult to achieve
-   performance can be a problem when the number of layers lead to a lot of
    indirection when accessing e.g. the database (bottom layer) from the UI
    (top layer) (the request flows through all the intermediate layers which
    means a lot of inter-component communication)

**When to use**:

-   adding additional functionality to an existing system
-   separate groups can begin working on different layers

### Client Server Architecture

-   promotes separation of concerns i.e. high cohesion and low coupling
-   servers can be distributed across a network
-   clients call on those services
-   on receiving a request servers provide some specific functionality to
    clients e.g. HTTP servers serve web pages
-   each server is susceptible to denial of service (DoS) attack, if this
    server provides some crucial functionality it could lead to a system
    failure

### MVC

**Description**

-   separates presentation and interaction from the system data
-   the system is structured into three logical components that interact with
    each other
-   models manage system data and operations on that data (business logic,
    database)
-   view defines and manages how the data is presented to users
-   controller manages user interaction (e.g. key presses, JavaScript events)
    -- it activates the view and controller based on it

**Example**

-   web application

**When Used**

-   when there are many ways to interact with the data
-   when it's unclear how interacting with the data is going to look in the
    future

**Advantages**

-   supports presentation of data in different ways
-   the same models can be used in an iOS app and Android app and a web app

**Disadvantages**

-   may force additional code and code complexity when the interactions and
    data are simple

### Repository Architecture

-   sub-systems must exchange data
-   this may be done by:
    a)  storing shared data in a central repository which all sub-system have
        access to
    b)  each sub-system has it's own database and passes data explicitly to
        other sub-systems
-   when large amount of data needs to be shared, the repository model of
    sharing is most commonly used because it's efficient
-   sub-systems do not interact directly but through the repository

**Advantages**:

-   components can be independent (low coupling, they don't need to know about
    the other components)
-   all data kept in one place
    -   easy to back-up
    -   easy to access -- you don't search through multiple sub-systems
-   promotes separation of concerns (one component stores data while the rest
    performs their specific functions)

**Disadvantages**:

-   the repository is a single point of failure
    -   all sub-systems depend on the repository
    -   problems with the repository affect all sub-systems


## Types of Applications

- Data processing (process data in batches without explicit user intervention)
- Transaction processing (process user request and update information in database)
    - e.g. e-commerce systems, reservation systems
- Event processing (applications where system actions are dependent on the events in the system's environment)
- Language processing (applications where user intentions are specified in a formal language and are interpreted by the system)
    - e.g. compilers, interpreters
    - tokeniser
    - lexer
    - symbol table
    - parse tree
    - abstract syntax tree (AST)

### Distrubuted Systems

A distributed system is a model in which system components air located on
networked computers which communicate by passing messages. Components do that
to achieve a common goal.

**Characteristics of distributed systems**:

-   concurrency of components
-   lack of global lock
-   independent failure of components

**Remote Procedure Call (RPC)** is when a program causes a function to execute
in a different address space (commonly on another computer on a shared
network). This is coded as if it were a normal (local) function call, without
having to explicitly code the details for the remote interaction.

## Glossary

Software Architecture
:   description of how a software system is organised

Architectural Patterns
:   - means of reusing knowledge about generic system architectures
    - describe the architecture
    - explain why it might be used
    - describe advantages and disadvantages

Architectural Design Decisions
:   decisions on:

    - the type of application
    - the distribution of the system and architectural styles used
