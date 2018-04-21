# UML

## Dynamic Diagrams

Emphasise behaviors, represent the system in action ie running.

### State Diagrams

**Features of state diagrams**:

-   enhanced state diagrams
-   good at showing transitions between states of some object
-   states but also pseudostates (initial and final)
-   similar notation to FSM
-   each state belongs to a single class
-   states can be nested (there are sub- and super-states)
-   transitions need to be unambiguous
-   conditional transitions
-   we can have concurrent substrates

### Activity Diagrams

Informally activity diagrams are enhanced UML flow charts.

**Features of activity diagrams**:

-   mutually exclusive conditions
-   good at representing concurrency
-   synchronisation bar
    -   forks
    -   joins
-   similarly to state diagrams there are initial and final pseudo-states
-   represent flow of execution
-   choices (conditions labeled with diamonds)
-   states are denoted by boxes with rounded corners
-   events trigger transitions
-   it's possible that when some condition is not met an even doesn't result in
    change of state (this is unlike in activity diagrams)
-   swim lanes (partitions) can be used to group related activities
-   signal sending
-   signal receiving
-   time signals (eg wait x hours)

### Sequence Diagrams

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

## Static Diagrams

Emphasise structure.

### Class Diagrams
