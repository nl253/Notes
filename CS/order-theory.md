# Order Theory

## Glossary

Semilattice

:   A semilattice can be either *lower* or *upper* depending on whether
    it is constructed form a *meet* or *join*.

    Both *meet* ($\wedge$) and *join* ($\vee$) can be used to partially
    order a set.

Lattice

:   A *lattice* consists of two semilattices one of which is an upper
    semilattice and the other being a lower semilattice.

Meet

:   aka GLB (Greatest Lower Bound).

    Given any two elements $\alpha$ and $\beta$ the *meet* operation
    ($\wedge$) produces a value such that the value *precedes* both
    $\alpha$ and $\beta$.

    E.g.: a *meet* of $\alpha$ and $\beta$ denoted $\alpha \wedge \beta$
    is a term such that:

    $$\alpha \wedge \beta \le \alpha$$ $$\wedge$$
    $$\alpha \wedge \beta \le \beta$$

    In other words if there is a $\gamma$ such that $\gamma$ *precedes*
    $\alpha$ and $\beta$ then it is their *meet*:

    $$\frac{\gamma \le \alpha,\ \gamma \le \beta}{\gamma \iff \alpha \wedge \beta}$$

    The *meet* of $n$ elements from set
    $S = \{\ s_0,\ s_1,\ \dots,\ s_n\ \}$ can be obtained by repeated
    application of the *meet* operation to every pair
    $(s_i,\ s_{i + 1})$ until $s_n$.

    $$\bigwedge^n_{i = 0} s_i$$

Join

:   \"\"

Preorder

:   In order theory, a preorder or quasiorder is a binary relation that
    is reflexive and transitive.

    Preorders are more general than equivalence relations and
    (non-strict) partial orders, both of which are special cases of a
    preorder.

    An antisymmetric preorder is a partial order, and a symmetric
    preorder is an equivalence relation.
