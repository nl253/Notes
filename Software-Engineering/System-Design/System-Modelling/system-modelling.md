# System Modelling

## Introduction

- complexity of the problem domain and software
- other engineering disciplines rely heavily on models
- a large software product is a large investment -- we cannot afford to mess it up
- it's difficult to explain and exchange ideas about design between team members

See [system modelling](#system-modelling).

**Why use system modes?**

- they help to understand the structure and behaviour of the system
    - structure: understanding the system in terms of components (i.e. sub-systems)
    - behaviour: understanding the system in terms of interactions between
      components
- communicate with stakeholders (mostly customers but also others)

Models of the **existing system** should be produced during
**requirements engineering**. They can be helpful in:

- clarifying what the existing system does
- identifying it's strengths and weaknesses
- facilitating discussion about what the new system might be like

Models of the **new system** are created by the system architects to explain
proposed requirements to other stakeholders.

Useful for:

- discussing design proposals
- documentation of the systems

## Types of System Perspectives

- external (system and it's context)
    - models the context or environment of the system
    - show what lies outside of the system boundaries
- interaction
    - models the interaction: between a system and it's environment or between
      components within the system
    - useful in analysing user interaction -- it helps to identify user
      requirements
    - can be used to discover and highlight communication problems in
      system-system interaction
    - helps to understand if the proposed system is likely to deliver the
      required performance and dependability
    - e.g.: UML use case and UML sequence diagrams can be used for interaction
      modelling
- structural
    - models the organisation of the system and how it responds to events
    - display the organisation in terms of:
        - components that make up the system
        - their relationships
    - structural models may be:
        - static (show the structure of the system design)
        - dynamic (show the organisation of the system when it's executing)
- behavioural
    - models the dynamic (i.e. runtime) behaviour of the system and how it
      responds to events
    - shows what happens or what is supposed to happen when a system responds to
      a stimulus (e.g.: data or event i.e. a trigger) from it's environment

### Context Models

Show how a system is positioned in an environment with other systems and processes.

### Interaction Models

Represent interactions between users and systems and between system components.

### Structural Models

Show the organisation and architecture of a system.

### Behavioural Models

Describe the dynamic behaviour of an executing system.

From the perspective of:

- data processed by the system
- events stimulating responses from the system

## Model-Driven Engineering

See [model driven engineering](#model-driven-engineering)

Model-Driven Engineering is still in early stages of development.
It's unclear if it will have a significant effect on software engineering practice.

**Advantages**:

- allows problems to be considered at higher levels of abstraction i.e.
  implementation details are not the focus of software engineers' attention and
  they can focus on finding a solution to the actual problem
- automatic generation of code can be cost-effective

**Disadvantages**:

- models for abstraction are not necessarily right for implementation
- even if you save money by generating the code from models, you might have to
  spend it on developing model-to-code translators for new platforms

The precursor of Model-Driven Engineering was **Model-Driven Architecture**.

- use a subset of UML to describe a system
- models at different levels of abstraction are created

## Data-Driven Modelling

Data-processing systems that are primary driven by data:

- controlled by the data input to the system with relatively little external
  even processing e.g.: business systems

**Data-Driven Models** represent:

- sequence of actions involved in processing input data
- generating an associated output
- useful during analysing requirements as they can be used to show an end-to-end
  processing in a system model-driven architecture

## Event-Driven Modelling

Shows:

- how a system responds to external and internal events
- based on the assumption that a system has a finite number of states (stimuli
  may cause a transition from a state to another)

E.g.: **State Machine Models** (such as UML State Diagrams)

- transitions are the stimuli that cause transition from one state (node) to another
- nodes are the states

## System Boundaries

- What constitutes the system is affected by social and organisational concerns.
- Defining a system boundary is a difficult decision. It might put pressure on
  some parts of the organisation.

E.g.: more functionality in the system means more work for software developers.

## UML

-   UML specification defines two major kinds of UML diagram: structure
    diagrams and behavior diagrams.
-   A UML diagram is a partial graphical representation (**view**) of a model
    of a system under design, implementation, or already in existence.
-   UML diagram contains graphical elements (symbols)
    -   UML nodes connected with edges (also known as paths or flows)
    -   that represent elements in the UML model of the designed system.
    -   The UML model of the system might also contain other documentation such
        as use cases written as templated texts.

### Structure diagrams

Structure diagrams show static structure of the system and its parts on
different abstraction and implementation levels and how those parts are related
to each other. The elements in a structure diagram represent the meaningful
concepts of a system, and may include abstract, real world and implementation
concepts.

Structure diagrams are not utilizing time related concepts, do not show the
details of dynamic behavior. However, they may show relationships to the
behaviors of the classifiers exhibited in the structure diagrams.

#### Class Diagrams

Shows structure of the designed system, subsystem or component as related
classes and interfaces, with their features, constraints and relationships -
associations, generalizations, dependencies, etc.

- describe types of objects in the system and the **static relationships** that
  exist between them
- show **structural features** of a class
- show **properties** and **operations** (i.e. **features**) of a class
- show the **constraints** that apply to the way objects are connected
- properties correspond to fields in a class

##### Attributes

Formula:

`[visibility] <name>: [type] [\[multiplicity\]] [= default] [{property, ... }]`

E.g.: `title: String [1] = "Untitled" {readonly}`

##### Associations

- Alternative way to denote a property.
- The same information (e.g.: field `title` in a `Book` class) can be shown as
  an association to another class
- Attributes tend to be used for primitive type such as: `Boolean` and `String`.
- Associations are added to significant classes such as: `Database` and `Client`.
- Associations are denoted by a solid arrow from the source (containing) class
  to the target.
- Multiplicity can be written on both arrow ends
- In addition to unidirectional associations there are also bidirectional
  associations. I.e. the arrow may point from `A` to `B` and from `B` to `A`.
    - may be useful when a `Person` has a `Car vehicle` but the `Car` also has an
      `Person owner`
    - both of them can send messages to each other
    - if the association is unidirectional then only the source sends messages

###### Role Names vs Association Names

It's OK to label role names on both ends of the association arrow.

E.g.: if `Company` is associated to `Person`, we could label `Person` at the
other end with "manager" or "customer" if that is appropriate.

The other way of doing it is labelling associations: the label can apply to the
arrow itself and would be written in the middle. E.g.: "managed by" (just like
in relational databases in ER diagrams)

###### Aggregation and Composition

- models "part of whole" relationships e.g.: `Club` has `Facility`
- aggregation can be seen as an instance of association
    - composition can be seen as a special case of aggregation
        - there is no major difference between association and aggregation
        - composition is the same with the difference that it  "consists of",
        target cannot exists without the source
        - "owned by value"
        - in composition when the container is destroyed so is the value inside of it
        - in aggregation when the container is destroyed the value persists

###### Dependencies

-   a weaker form of bond that indicates that one class depends on another
    because it uses it at some point in time
    -   a dependency exists between the source and target when a change in
        definition of source affect the target
    -   one class depends on another if:
        -   the independent class (target) is a parameter variable of the
            dependent class (source)
        -   the independent class is local variable of a method of the
            dependent class
-   dependency is between classes, not objects of those classes
    -   different from an association, where an attribute of the dependent
        class is an instance of the independent class
-   built-in dependencies:
    -   `<< call >>` -- source calls operations on the target
    -   `<< create >>` -- source creates an instance of target
    -   `<< instantiate >>` -- source is an instance of target
    -   `<< use >>` -- source requires target for implementation

##### Generalisation

- corresponds to inheritance
- extend and reuse functionality of a class
- used to model "is-a" relationships
- subclass inherits all attributes and operations
- subclass may override them
- a subclass may always replace the superclass but not the other way
- denoted by a hollow arrow pointing to the superclass

##### Mulitplicities

E.g.:

- `[1]` -- this is implicit
- `[10]` -- exactly 10
- `[*]` -- any number
- `[1..10]` -- ranges are OK

Multiplicities can be specified in fields and at both end of associations.

##### Visibility

- public denoted by `+`
- private denoted by `-`
- protected denoted by `#`

Visibility can be specified in fields and at both end of associations.

###### Instance vs Class Scope

**instance**

- Instance members are scoped to a specific instance.
- Attribute values may vary between instances
- Instance scope is assumed by default.
- Method invocation may affect the instance's state (i.e. change instance's attributes)

**classifier**:

- Represented by underlined names.
- Correspond to "static" in Java.
- The scope is the class itself.
- Attribute values are equal for all instances Method invocation does not affect the instance's state.

##### Instantiation

We can make instances of classes using the notation:
`[instance name] : <class name ie type>`.

##### Operations

- Services that can be requested from an **object** (instance of a class).
- Correspond to methods.
- Have signatures which describes the parameters and possible return values.
- Syntax: `[visibility] ([param: Type, ...]) : [return type] [{property, ...}]`.
- E.g.: `setName(name: String): void`.
- In UML show only the most important operations that define how the class
  interact.
- Avoid things such as getters and setters and helper methods.

##### Advanced Features of Class Diagrams

###### Stereotypes

- allow to extend UML (extra classification to model items)
- e.g.: `<< interface >>`, `<< persistent >>`, `<< table >>`, `<< enumeration >>`
  on a class, `<< use >>` on a dependency
- part of **profiles**
- help to tailor the diagram to fit a particular domain

###### Interfaces

- similar to classes but don't list fields
- only list operations
- no state, just behaviour

### Behaviour Diagrams

Behaviour diagrams show the dynamic behavior of the objects in a system, which
can be described as a series of changes to the system over time.

#### Use Case Diagrams

Describes a set of actions (**use cases**) that some **system** or systems
(subject) should or can **perform in collaboration** with one or more
**external users** of the system (actors) to provide some observable and
valuable results to the actors or other stakeholders of the system(s).

#### State Diagrams

-   Used for modeling discrete behavior through finite state transitions.
-   In addition to expressing the behavior of a part of the system, state
    machines can also be used to express the usage protocol of part of a
    system.

**Features of state diagrams**:

-   enhanced state diagrams
-   good at showing transitions between states of some object
-   states but also pseudo-states (initial and final)
-   similar notation to FSM
-   each state belongs to a single class
-   states can be nested (there are sub- and super-states)
-   transitions need to be unambiguous
-   conditional transitions
-   we can have concurrent substrates

#### Activity Diagrams

-   UML behavior diagram
-   shows flow of control or object flow with emphasis on the sequence and
    conditions of the flow
-   The actions coordinated by activity models can be initiated because other
    actions finish executing, because objects and data become available, or
    because some events external to the flow occur.

- Shows sequence and conditions for coordinating lower-level behaviors, rather than which classifiers own those behaviors.
- Informally activity diagrams are enhanced UML flow charts.

**Features of activity diagrams**:

-   mutually exclusive conditions
-   good at representing concurrency
-   synchronisation bar
    -   forks
    -   joins
-   similarly to state diagrams there are initial and final pseudo-states
-   represent flow of execution
-   choices (conditions labelled with diamonds)
-   states are denoted by boxes with rounded corners
-   events trigger transitions
-   it's possible that when some condition is not met an even doesn't result in
    change of state (this is unlike in activity diagrams)
-   swim lanes (partitions) can be used to group related activities
-   signal sending
-   signal receiving
-   time signals (e.g. wait x hours)

#### Sequence Diagrams

Most common kind of **interaction diagrams** which focuses on the message
interchange between lifelines (objects).

**Features of sequence diagrams**:

-   self-call
-   purpose
    -   show steps (high level)
    -   trace method calls (low level)
    -   show flow of execution
-   callbacks
-   deriving from class diagrams
-   deriving from use case diagrams
-   combined fragments
    -   opt
    -   alt
    -   loop
-   lifeline
-   activation
-   repetition
-   synchronous calls
-   asynchronous calls
-   deletion of an object
-   self-deletion

## Glossary

Model-driven engineering
:   An approach to software development where a system is represented as a set of
    models that can be automatically transformed to executable code. It
    aims to generate a complete or partial system implementation from the
    system model.

System Boundaries
:   define what is outside of the system and what forms part of the system

System Model
:   abstract representation of a system that ignores details
