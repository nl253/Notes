# Logic

## Glossary

Soundness

:   Soundness is a property of a logical system. Informally, a soundness
    theorem for a deductive system expresses that all provable sentences
    are true. Completeness states that all true sentences are provable.

Validity

:   A formula of a formal language is a valid formula if and only if it
    is true under every possible interpretation of the language. In
    propositional logic, they are tautologies. (eg \\( T \\vee F \\))

:   A valid logical argument is one in which the conclusion is entailed
    by the premises, because the conclusion is the consequence of the
    premises.

Completeness

:

Decidability

:   an undecidable problem is a decision problem for which it is known
    to be impossible to construct a single algorithm that always leads
    to a correct yes-or-no answer. The halting problem is an example:
    there is no algorithm that correctly determines whether arbitrary
    programs eventually halt when run.

Cogency
:

Tautology

:   a formula or assertion that is true under all interpretations. eg
    for predicate `P(x)` it would be when x is true or false.

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

    For example, x~1~ is a positive literal, ¬x~2~ is a negative literal, x~1~
    ∨ ¬x~2~ is a clause, and (x~1~ ∨ ¬x~2~) ∧ (¬x~1~ ∨ x~2~ ∨ x~3~) ∧ ¬x~1~ is a
    formula in conjunctive normal form,

    Using the laws of Boolean algebra, every propositional logic formula
    can be transformed into an equivalent conjunctive normal form, which
    may, however, be exponentially longer.
The Boolean satisfiability problem (SAT)

:   A propositional logic formula, also called Boolean expression, is
    built from variables, operators AND (conjunction, also denoted by
    \\( \\wedge \\)), OR (disjunction, \\( \\vee \\))), NOT (negation, \\(
    \\neg \\)),
    and parentheses. A formula is said to be satisfiable if it can be
    made TRUE by assigning appropriate logical values (i.e. TRUE, FALSE)
    to its variables. The Boolean satisfiability problem (SAT) is, given
    a formula, to check whether it is satisfiable

Well-formed formula

:   finite sequence of symbols from a given alphabet that is part of a
    formal language.

    a "well formed formula" is a formula that adheres to the syntactic
    rules of some formal language. In propositional logic it would a
    formula that makes use of the alphabet of propositional logic ie {v,
    n, \<=\>, -\>, ...}
