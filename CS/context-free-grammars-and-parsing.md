# Context Free Grammars And Parsing

## Normal Forms

Every context-free grammar that does not generate the empty string can be
transformed into one in which there is no $\varepsilon$-production
(that is, a rule that has the empty string as a product).

If a grammar does generate the empty string, it will be necessary to include the rule
$S \Rightarrow \epsilon$, but there need be no other $\varepsilon$-rule.

Every context-free grammar with no $\varepsilon$-production has an equivalent grammar in
Chomsky normal form, and a grammar in Greibach normal form. "Equivalent" here
means that the two grammars generate the same language.

The especially simple form of production rules in Chomsky normal form grammars
has both theoretical and practical implications. For instance, given a
context-free grammar, one can use the Chomsky normal form to construct a
polynomial-time algorithm that decides whether a given string is in the
language represented by that grammar or not (the CYK algorithm).

### Chomsky Normal Form (CNF)

Any context-free grammar may be transformed to a CNF grammar expressing the
same language.

A context-free grammar G is said to be in CNF if all of its
production rules are of the form:

- $A \Rightarrow BC$
- $A \Rightarrow a$
- $S \Rightarrow \varepsilon$

The drawback of all known transformations into CNF is that they
can lead to an undesirable bloat in grammar size.

1. **START**: If the start symbol $S$ occurs anywhere on the right side, introduce a new
   start rule $S_0 \Rightarrow S$
2. **TERM**: extract terminals into rules in rules such as: $A \Rightarrow BcD$ with $A \Rightarrow BCD$ and add rule $C \Rightarrow c$ for every extracted terminal
3. **BIN**: for every rule that has more than 2 non-terminals on the right
   side e.g. $A \Rightarrow BCD$, make an additional rule to ensure at most
   2 non-terminals are on the right e.g.: \(A \Rightarrow BCD \) becomes: \(A \Rightarrow ED\) and \(E \Rightarrow CD\)
4. **DEL**: delete $\varepsilon$ rules.
5. **UNIT**: shrink unit rules e.g.: \(A \Rightarrow B \\ B \Rightarrow CD\) becomes: \(A \Rightarrow CD\)

## Parsing

### CYK Algorithm

- The basic version requires the grammar to be in CNF. This
  simplifies the algorithm although in theory it it possible to bypass this
  requirement.
- It employs bottom-up parsing and dynamic programming.

Looks at and attempts to parse increasingly longer sub-strings of input string
$w$:

1. Look at sub-strings of length 1
2. Look at sub-strings of length 2
3. Look at sub-strings of length 3

$\dots$

To carry out the algorithm use a table. E.g. suppose input is string
"$abaade$":

<table>
    <tr>
        <td>S</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>F</td>
        <td>G</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>A</td>
        <td>B</td>
        <td>A</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>a</td>
        <td>b</td>
        <td>a</td>
        <td>a</td>
        <td>d</td>
        <td>e</td>
    </tr>
</table>

We know it successfully parsed when $S$ (the start rule) appears in the first
cell of first row.

### Recursive Descent LL(1) Parser

<!-- TODO Recursive Descent LL(1) Parser -->

## Ambiguity

- Grammars which produce more than one parse tree are called ambiguous.
- Ambiguity is typically not a feature of the language but the grammar.
- For many ambiguous grammars, an equivalent grammar can be produced that is
  unambiguous.

<!-- TODO dealing with ambiguity in CFG -->

## Closure properties

Context-free languages are closed under:

- union $K \cup L$
- concatenation $K \cdot L$
- Kleene star $L^*$
- $\dots$

They are not closed under general intersection (hence neither under complementation) and set difference.
