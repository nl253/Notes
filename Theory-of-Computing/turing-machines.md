# Turing Machines

For a given word a Turing machine may accept the word, reject the word or loop.

## Decidability

-   A Turing machine decides a language, if it accepts every word of the
    language and rejects all other words.
-   The language is called **Turing-decidable** (or decidable or recursive)

Some languages are not Turing-decidable.

## Recognisability

-   A Turing machine recognises a language, if it accepts every word of the
    language and either rejects OR loops on other inputs
-   A language that is recognised by some Turing machine is called
    **Turing-recognisable** (or recursively enumerable).
-   By definition **every Turing-decidable language is also Turing-recognisable**.

Turing-decidable languages $\subset$ Turing-recognisable languages.

Some languages are not Turing-recognisable.

## Co-Recognisability

A language is **co-Turing-recognisable** if it is the complement of a
Turing-recognisable language.

A language is decidable iff it is Turing-recognisable and
co-Turing-recognisable.

## Computability

A function is called computable if there exists a Turing machine that computes
it.

## Variants

The Turing machine model is **robust** (variants decide/recognise the same languages).

Numerous variations:

-   left, right and stay
-   enumerator
-   multiples tapes instead of just one
-   non-determinism

All these variants prove to be equivalent, they recognise / decide the same
class of languages.

-   An **enumerator** is a Turing machine with a printer for words:
    -   If enumerator E enumerates language L, then a Turing machine M
        recognises it.
    -   If Turing machine M recognises language L, then an enumerator E
        enumerates L.
-   Every **multi-tape Turing machine** has an equivalent single-tape Turing
    machine.
-   Every **non-deterministic Turing machine** has an equivalent deterministic
    Turing machine.

**A Turing machine can simulate another Turing machine: same input and accepts/rejects same input**.

**Universal Turing machine** is an interpreter for Turing machines.
The universal Turing machine can simulate any Turing machine.

## Encodings

We could also encode as a single word:

-   the full contents of one database
-   a graph (to then run some graph algorithm)
-   the complete memory of a computer

Often don't care about details of encoding:

- `<5>`     = aaaaa
- `<(5,3)>` = aaaaa\$aaa $\dots$

We can encode any data including a Turing machine M: `<M>`.

## Infinite Sets

Two sets have the same **size** if every element of one set can be paired with a
different element of the other set such that every element of each set is
paired (a **correspondence** exists).

A set is **countable** if it is finite or it has the same size as the set of
natural numbers.

### Proof by Diagnoalisation

Cantor's Diagonalisation Method proves that a set is **uncountable**, i.e., no
**correspondence with the natural numbers** exists.

Construct an irrational number from the diagonal, such that it is different
from all numbers in the list:

    0) 3.14159 ...
    1) 2.12345 ...
    2) 5.24634 ...
    3) 3.23452 ...

## Reducibility

General Two problems A and B. If A reduces to B, then we can use a solution for
B to solve A.

-   If A reduces to B, then if B is decidable, so is A.
-   If A reduces to B, then if A is undecidable, so is B.

## The Halting Problem

It's undecidable whether a given program terminates for given input.

## Glossary

Configuration
:   a state with the current tape content and the current head

