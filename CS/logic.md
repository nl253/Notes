# Logic

## Glossary

Soundness

:   Soundness is a property of a logical system. Informally, a soundness
    theorem for a deductive system expresses that all provable sentences
    are true. Completeness states that all true sentences are provable.

Validity

:   A formula of a formal language is a valid formula if and only if it
    is true under every possible interpretation of the language. In
    propositional logic, they are tautologies. (e.g. ($T \vee F$))

:   A valid logical argument is one in which the conclusion is entailed
    by the premises, because the conclusion is the consequence of the
    premises.

Syntactic Consequence

:   there is a derivation in the proof system

<!-- Completeness -->
<!-- : -->

Decidability

:   -   an undecidable problem is a decision problem for which it is
        known to be impossible to construct a single algorithm that
        always leads to a correct yes-or-no answer.
    -   The halting problem is an example: there is no algorithm that
        correctly determines whether arbitrary programs eventually halt
        when run.

<!-- Cogency  -->
<!-- :  -->

Tautology

:   -   a formula or assertion that is true under all interpretations.
    -   e.g. $(P(x) \iff \bot) \vee (P(x) \iff \top)$
    -   **all tautologies are equivalent**

Contradiction
:   a formula that is false under all interpretations.

Satisfiability

:   A formula is satisfiable if it is true under at least one
    interpretation, and thus a tautology is a formula whose negation is
    unsatisfiable.

:   Unsatisfiable statements, both through negation and affirmation, are
    known formally as contradictions. A formula that is neither a
    tautology nor a contradiction is said to be logically contingent.
    Such a formula can be made either true or false based on the values
    assigned to its propositional variables. The double turnstile
    notation is used to indicate a tautology.

Conjunctive Normal Form (CNF)

:   A formula is in conjunctive normal form (CNF) if it is a conjunction
    of clauses (or a single clause).

    For example, $x_{1}$ is a positive literal, $\neg x_{2}$ is a
    negative literal, $x_{1} \vee \neg x_{2}$ is a clause, and
    $(x\_{1} \vee \neg x_{2}) \wedge (\neg x_{1} \vee x_{2} \vee x_{3}) \wedge \neg x_{1}$
    is a formula in conjunctive normal form,

    Using the laws of Boolean algebra, every propositional logic formula
    can be transformed into an equivalent conjunctive normal form, which
    may, however, be exponentially longer.

Principle of Explosion

:   -   From contradiction, anything follows.
    -   Formal definition: ($\models (P \rightarrow Q) \wedge \neg P$)

The Boolean satisfiability problem (SAT)

:   A propositional logic formula, also called Boolean expression, is
    built from variables, operators AND (conjunction, also denoted by
    ($\wedge$)), OR (disjunction, ($\vee$))), NOT (negation, ($\neg$)),
    and parentheses. A formula is said to be satisfiable if it can be
    made true by assigning appropriate logical values
    (i.e. (\top, \bot)) to its variables. The Boolean satisfiability
    problem (SAT) is, given a formula, to check whether it is
    satisfiable

Well-formed formula

:   finite sequence of symbols from a given alphabet that is part of a
    formal language.

    A "well formed formula" is a formula that adheres to the syntactic
    rules of some formal language. In propositional logic it would a
    formula that makes use of the alphabet of propositional logic i.e.
    $\{\vee, \wedge, <=>, \rightarrow, \dots\}$

Syntax

:   the relationship between symbols in a message

Semantics

:   -   the relationship between the symbols or terms, and their
        associated meanings.
    -   Just as we can change a message by changing its syntax, we can
        change it by changing the semantics of its terms.

Law of Excluded Middle

:   -   Formal definition: $\vDash P \vee \neg P$
    -   relevant in 2-valued logic
    -   based on the assumption that something must be either True OR
        False
    -   in 3 valued logics this does not hold true
        -   in intuitionistic (constructivist) logic, things might be
            true or false or 'other'

Intuitionist's Logic

:   -   aka constructive logic
    -   rejects [law of excluded middle](#law-of-excluded-middle)
    -   rejects double negation elimination
    -   we could say it's a restriction of classical logic where the
        laws above are removed
    -   in intuitionistic logic variables aren't assigned a definite
        truth value and are only considered true when we have direct
        evidence -- hence proof
