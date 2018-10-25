# CCS (Calculus of Communicating Systems)

-   developed by Robin Milner (1980)
-   simple, reduces complexity
-   main elements
    -   names (channels) and communication
    -   scopes
        -   allow for private communication
        -   use parenthesis
        -   "new" $v$ symbol which will make those items visible only in
            the parenthesis
    -   parallel composition (using "pipe" `|`)
    -   choice

## Formal Model

-   allows to reason about models and their properties
-   e.g.: you can analyse if an undesirable trace can ever be produced
    by a FSM
-   e.g.: you can analyse if a word is accepted by a FSM
-   the point is to create an abstract representation of a system (
    i.e. one that omits unnecessary details) and then analyse and verify
    the system by analysing the formal model (**verification**)

## CCS

-   has a set of transition rules
-   transitions produce an execution trace e.g.: `coin, tea, coin`

### Example

-   chatty tea drinker
-   ports:
    -   coin
    -   tea
    -   call
-   output actions:
    -   coin
    -   call
-   `D = def = coin . tea . D`

## Parallel composing of processes

Example: `D | P = coin . tea . D | coin . tea . P`

-   two processes can proceed independently
-   if they share actions i.e. they are complementary they can
    "handshake" (NOTE: handshake is not externally visible, it's also
    synchronised)

## Finite State Machines

See <file:///home/norbert/Documents/Notes/CS/automata-theory.md>

## Glossary

syntax
:   rules about how to form constructs

semantics
:   describes the behaviour of well-formed models

traces
:   all the possible executions of an automaton

process

:   an instance of a program that runs concurrently

    It can be described by:

    -   interface
    -   behaviour

    E.g.: the interface of the Tea machine:

    -   ports: coin, tea
    -   input actions: coin
    -   output actions: tea

## References

-   <https://www.wikiwand.com/en/Calculus_of_communicating_systems>
