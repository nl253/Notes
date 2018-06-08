# Mathematical Philosophy

## Sets

Definition by:

- Intention (defining characteristic e.g. $\{\ n\ |\ even(n)\ \}$)
- Enumeration

Similarity is when there is a 1-1 relation between two classes.

A **Domain** is a class of terms that have a relation to something.

E.g.: $Fater$ is the domain of the relation $Fater \times Child$,
$Husbands$ is the domain of the relation $Husbands \times Wives$

The relation $Husbands \times Wives$ is the **converse** of the relation
$Wives \times Husbands$.

A **Converse** of a relation is a relation that hold between $Y$ and $Y$
if whenever the given relation holds between $X$ and $Y$.

The **Converse domain** (aka **Codomain**) of a relation is the domain
of it's converse. (here Converse domain is defined in terms of 'domain'
and 'converse relation').

Thus, the class of $Wives$ is the converse domain of the relation
$Husband \times Wives$.

**Similarity** between classes $X$ and $Y$ is when there is a 1-1
relation in which one of the classes is the domain and the other the
converse domain.

As a result:

1.  Reflexivity: $\forall \alpha . Similar(\alpha,\ \alpha)$
2.  Symmetric city:
    $\forall\ \alpha,\ \beta,\ \gamma . Similar(\alpha,\ \beta) \wedge Similar(\beta,\ \gamma) \rightarrow Similar(\alpha,\ \gamma)$
3.  Transitivity:
    $\forall\ \alpha,\ \beta . Similar(\alpha,\ \beta) \rightarrow Similar(\beta,\ \alpha)$

Two *finite* classes $X$ and $Y$ have the same number of terms if they
are $Similar$.

The act of counting elements of a finite collection $X$ means to pair
every $x$ in $X$ with a natural number in $\mathbb{N}$. The result of
the operation is *the last* natural number paired.

There is still, hoverer, a problem with **order**. The notion of
similarity does not include order.

The notion of similarity can be used to determine, if two classes belong
to the same bundle. I.e. if they have the same number of terms.

One of the bundles will have no members. This is the class $0$. We then
have a bundle of all the classes that have one member. This will be for
the number $1$. For $2$ we want a bundle consisting of all couples. For
$3$ we want all trios and so on.

## Number

- a way of bringing together collections
- collections of pairs, trios
- we end up with bundles i.e. classes which contain classes (pairs --
    class of two members, triplets -- class of 3 members)
- the problem of cardinality

We naturally think of the class of couples as somehow different than the
number $2$. The set of all couples is concrete. The number two, on the
other hand, is a metaphysical entity about which we can never be sure.

The number of a class is the class of all classes that are similar to
it.

With this definition the class of all couples is the number $2$.

A number is therefore a set of all classes that are similar to each
other and none outside of the set are similar to those inside of the
set.

By defining numbers in this way a vicious circle is avoided since the
notion of a number is not used to define itself. This definition will
suffice for finite numbers.

### Natural Numbers

#### Peano's Axioms

Suited to a more general idea of **progressions**.

3 Core Ideas:

- numbers
- the number `0 :: Nat a => a`{.haskell} (starting point)
- the successor function `Nat a => a -> a`{.haskell}

$\mathbb{N} = \{\ 0,\ succ(0),\ succ(succ(0)),\ \dots,\ succ^n(0) \}$

### Counting

- Bundling them presupposes ability to count.
- We cannot use counting to define numbers without a vicious circle

## Relations

- `1-1`
- `1-many`
- `many-1`

A Relation on $n$ sets $\{\ X_0,\ X_1,\ \dots,\ X_n\ \}$ is a subset of
the Cartesian product $X_0 \times X_1 \dots \times X_n$.

It is a set of $n$-tuples where each tuple is
$(x_0,\, x_1, \dots,\ x_n)$ and
$x_0 \subseteq X,\ x_1 \subseteq X_1, \dots, x_n \subseteq X_n$.

## Mathematical Induction

A property is said to be **hereditary**, in the natural number series,
if whenever it belongs to a number $n$, it also belongs to $n + 1$.

A class is said to be hereditary if whenever $n$ is a member of a class,
so is $n + 1$.

A property is said to be **inductive** if it is hereditary and it
belongs to $0$. $\forall p . I(p) \iff H(p) \wedge p \subseteq P(0)$

A class is inductive if it is a hereditary class of which $0$ is a
member.

The relation $predecessor$ is the converse of successor.
