# Automata Theory

The machine accepts w if the last input of w causes the machine to halt
in one of the accepting states. Otherwise, it is said that the automaton
rejects the string. The set of strings M accepts is the language
recognized by M and this language is denoted by L(M).

## The Pumping Lemma for Regular Languages

Let M = (Q, Σ, δ, q 1 , F ) be a DFA that recognizes A.

1.  We assign the pumping length p to be the number of states of M .

2.  We show that any string s in A of length at least p may be broken
    into the three pieces xyz, satisfying our three conditions.

What if no strings in A are of length at least p?

Then our task is even easier because the theorem becomes vacuously true:
Obviously the three conditions hold for all strings of length at least p
if there aren't any such strings.

If s in A has length at least p, consider the sequence of states that M
goes through when computing with input s. It starts with q~1~ the start
state, then goes to, say, q~3~ , then, say, q~20~ , then q~9~ , and so
on, until it reaches the end of s in state q~13~ . With s in A, we know
that M accepts s, so q~13~ is an accept state. If we let n be the length
of s, the sequence of states q~1~ , q~3~ , q~20~ , q~9~ , . . . , q~13~
has length n + 1.

Because n is at least p, we know that n + 1 is greater than p, the
number of states of M . Therefore, the sequence must contain a repeated
state.

This result is an example of the pigeon-hole principle, a fancy name for
the rather obvious fact that if p pigeons are placed into fewer than p
holes, some hole has to have more than one pigeon in it.

## Non-deterministic finite automaton (NFA)

Unlike a DFA, it is non-deterministic, i.e., for some state and input
symbol, the next state may be nothing or one or two or more possible
states.

## DFA vs NFA

-   Every state of a DFA always has exactly one exiting transition arrow
    for each symbol in the alphabet.
-   Every DFA is an NFA.

## Non-deterministic finite automaton with ε-moves (NFA-ε) {#non-deterministic-finite-automaton-with--moves-nfa-}

NFA-ε is a further generalization to NFA. This automaton replaces the
transition function with the one that allows the empty string ε as a
possible input. The transitions without consuming an input symbol are
called ε-transitions.

In the state diagrams, they are usually labelled with the Greek letter
ε. ε-transitions provide a convenient way of modelling the systems whose
current states are not precisely known: i.e., if we are modelling a
system and it is not clear whether the current state (after processing
some input string) should be q or q', then we can add an ε-transition
between these two states, thus putting the automaton in both states
simultaneously.

### ε-closure {\#-closure} {#-closure--closure}

ε-closure of state q is the set of states reachable from q using
ε-transitions.

ε-closure is also defined for a set of states. The ε-closure of a set of
states, P, of an NFA is defined as the set of states reachable from any
state in P following ε-transitions.

## Deterministic Finite automaton (DFA)

In automata theory, a finite state machine is called a deterministic
finite automaton (DFA), if:

1.  each of its transitions is uniquely determined by its source state
    and input symbol
2.  reading an input symbol is required for each state transition.

## NFA Closure properties

NFAs are said to be closed under a (binary/unary) operator if NFAs
recognize the languages that are obtained by applying the operation on
the NFA recognizable languages. The NFAs are closed under the following
operations.

-   Union (cf. picture)
-   Intersection
-   Concatenation
-   Negation
-   Kleene closure

## Converting NFA to DFA - Powerset Construction Method

The establishment of such equivalence is important and useful. It is
useful because constructing an NFA to recognize a given language is
sometimes much easier than constructing a DFA for that language. It is
important because NFAs can be used to reduce the complexity of the
mathematical work required to establish many important properties in the
theory of computation. For example, it is much easier to prove closure
properties of regular languages using NFAs than DFAs.

For every NFA a deterministic finite automaton (DFA) can be found that
accepts the same language. Therefore, it is possible to convert an
existing NFA into a DFA for the purpose of implementing a (perhaps)
simpler machine. This can be performed using the powerset construction,
which may lead to an exponential rise in the number of necessary states.

If the NFA has n states, the resulting DFA may have up to 2^n^ states,
an exponentially larger number, which sometimes makes the construction
impractical for large NFAs.

The powerset construction applies most directly to an NFA that does not
allow state transformations without consuming input symbols (aka:
"ε-moves").

So you need to remove ε-transitions before you convert NFA to an
equivalent DFA.

## Glossary

Regular language
:   The set of all strings accepted by an NFA is the language the NFA
    accepts. This language is a regular language.
