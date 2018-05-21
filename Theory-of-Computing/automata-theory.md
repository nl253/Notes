# Automata Theory

The machine accepts $w$ if the last input of $w$ causes the machine to halt
in one of the accepting states. Otherwise, it is said that the automaton
rejects the string. The set of strings $M$ accepts is the language
recognized by $M$ and this language is denoted by $L(M)$.

## Formal Definition

A deterministic finite automaton $M$ is a 5-tuple, $(Q, \Sigma, \delta, q_0, F)$, consisting of:

- a finite set of states ($Q = \{q_0,\ q_1,\ \dots,\ q_n\}$)
- a finite set of input symbols called the alphabet (e.g. $\Sigma = \{0,\ 1\}$)
- a transition function ($\delta : Q \ \Sigma \rightarrow Q$) -- function that
  accepts a state from $Q$ and an input symbol from $\Sigma$ and returns
  another state in $Q$.
- an initial or start state ($q_0 \in Q$)
- a set of accept states ($F \subset Q$)

## The Pumping Lemma for Regular Languages

- Describes an essential property of all regular languages.
- Informally, it says that all sufficiently long words in a regular language may
  be pumped -- that is, have a middle section of the word repeated an arbitrary
  number of times -- to produce a new word that also lies within the same
  language.
- This can be used to prove that a language is not regular if we manage to show
  that it does not have this property.

Let $M = (Q, \Sigma, \delta, q_1 , F)$ be a DFA that recognizes $A$.

1.  We assign the pumping length $p$ to be the number of states of $M$ .

2.  We show that any string $s$ in $A$ of length at least $p$ may be broken
    into the three sub-strings, $s = xyz$, where the middle portion $y$ must
    not be empty, such that the words $xz$, $xyz$, $xyyz$, $xyyyz$, $\dots$
    constructed by repeating $y$ zero or more times are still in $A$.
    This process of repetition is known as "*pumping*".

    - If $s$ in $A$ has length at least $p$, consider the sequence of states that $M$
    goes through when computing with input $s$. It starts with $q_{0}$ the start
    state, then goes to, say, $q_{1}$ , then, say, $q_{2}$ , then $q_{3}$ , and so
    on, until it reaches the end of $s$ in state $q_{2}$ .
    - With $s$ in $A$, we know that $M$ accepts $s$, so $q_{2}$ is an accept state.
    - If we let $n$ be the length of $s$, the sequence of states $q_{0}$ , $q_{1}$ , $q_{2}$ , $q_{3}$ ,$\dots$ , $q_{2}$
    has length $n + 1$.
    - Because $n$ is at least $p$, we know that $n + 1$ is greater than $p$,
    the number of states of $M$. Therefore, the sequence must contain a repeated state.

This result is an example of the pigeon-hole principle, a fancy name for
the rather obvious fact that if $p$ pigeons are placed into fewer than $p$
holes, some hole has to have more than one pigeon in it.

*What if no strings in $A$ are of length at least $p$*?

Then our task is even easier because the theorem becomes vacuously true:
Obviously the three conditions hold for all strings of length at least $p$
if there aren't any such strings.

## Non-deterministic finite automaton (NFA)

Unlike a DFA, it is non-deterministic, i.e., for some state and input
symbol, the next state may be nothing or one or two or more possible
states.

## DFA vs NFA

-   Every state of a DFA always has exactly one exiting transition arrow
    for each symbol in the alphabet.
-   Every DFA is an NFA.

## Non-deterministic finite automaton with $\varepsilon$-moves (NFA-$\varepsilon$)

NFA-$\varepsilon$ is a further generalization to NFA. This automaton replaces the
transition function with the one that allows the empty string $\varepsilon$ as a
possible input. The transitions without consuming an input symbol are
called $\varepsilon$-transitions.

 $\varepsilon$-transitions provide a convenient way of modelling the systems whose
current states are not precisely known: i.e., if we are modelling a
system and it is not clear whether the current state (after processing
some input string) should be $q$ or $q'$, then we can add an $\varepsilon$-transition
between these two states, thus putting the automaton in both states
simultaneously.

### $\varepsilon$-closure

$\varepsilon$-closure of state $q$ is the set of states reachable from $q$ using
$\varepsilon$-transitions.

\[
closure(q) = \{\ q_i\ |\ q\ \rightarrow^\varepsilon\ q_i\ \}
\]

$\varepsilon$-closure is also defined for a set of states. The $\varepsilon$-closure of a set of
states, $P$, of an NFA is defined as the set of states reachable from any
state in $P$ following $\varepsilon$-transitions.

\[
closure(\{q_1,\ q_2,\ \dots,\ q_n\}) = \bigcup_{i=1}^{n} closure(q_i)
\]

## Deterministic Finite automaton (DFA)

In automata theory, a finite state machine is called a deterministic
finite automaton (DFA), if:

1.  each of its transitions is uniquely determined by its source state
    and input symbol (unambiguous)
2.  reading an input symbol is required for each state transition i.e. no
    $\varepsilon$ transitions

## NFA Closure properties

NFAs are said to be closed under a (binary/unary) operator if NFAs recognize
the languages that are obtained by applying the operation on the NFA
recognizable languages (`bop :: Semigroup a => a -> a -> a`{.haskell}). The NFAs are closed
under the following operations.

1. Union (binary) -- Result of $M_1 \cup M_2$ is $M_3$ that recognises $L(M_1)$ and $L(M_2)$
2. Intersection (binary) -- Result of $M_1 \cap M_2$ is $M_3$ that recognises strings that are **both** in $L(M_1)$ and $L(M_2)$
3. Concatenation (binary)
4. Negation (unary)
5. Kleene star (unary)
    - let $FSM = M$
    - let $L(M) = \{a,\ b\}$
    - $M^* = \{a,\ b,\ aa,\ ab,\ aaa,\ abbb,\ \dots\}$

## Equivalence of DFA and NFA

- The establishment of such equivalence is important and useful.
- It is useful because constructing an NFA to recognize a given language is sometimes much
  easier than constructing a DFA for that language.
- It is important because NFAs can be used to reduce the complexity of the mathematical
  work required to establish many important properties in the theory of computation.

For example, it is much easier to prove closure properties of regular languages using NFAs than DFAs.

### Converting NFA to DFA - Powerset Construction Method

For every NFA a DFA can be found that accepts the same language. Therefore, it
is possible to convert an existing NFA into a DFA for the purpose of
implementing a (perhaps) simpler machine. This can be performed using the
powerset construction, which *may* lead to an exponential rise in the number of
necessary states.

If the NFA has $n$ states, the resulting DFA may have up to $2^n$ states,
an exponentially larger number, which sometimes makes the construction
impractical for large NFAs.

The powerset construction applies most directly to an NFA that does not
allow state transformations without consuming input symbols (aka:
"$\varepsilon$-moves").

So you need to remove $\varepsilon$-transitions before you convert NFA to an
equivalent DFA. What will aid you in the process is calculating the
$\varepsilon$-closure for each state. I.e. $closure(q)$ is the set of
states reachable from $q$ by following $\varepsilon$ transitions.
When you remove the $\varepsilon$-transitions you'll need to connect
every state $q_i$ with the states in the set produced in the process
of finding $\varepsilon$-closure.

## Weaknesses of FSM

FSM are of strictly limited power in the
languages they can recognize; many simple languages, including any problem that
requires more than constant space to solve, cannot be recognized by a DFA.

-   The classic example of a simply described language that no DFA can
    recognize is bracket language, i.e., the language that consists of
    properly paired brackets such as word "(()())".
    -   Intuitively, no DFA can recognize the language because DFAs are
        not capable of counting: a DFA-like automaton needs to have a state to
        represent any possible number of "currently open" parentheses, meaning
        it would need an unbounded number of states.

Another simpler example is the language consisting of strings of the form $a^n b^n$ for some finite but arbitrary number of $a$'s, followed by an equal number of $b$'s.

## Glossary

Regular language
:   The set of all strings accepted by an NFA is the language the NFA
    accepts. This language is a regular language. Equivalently, it is a set of
    strings that is describable by some regular expression.
