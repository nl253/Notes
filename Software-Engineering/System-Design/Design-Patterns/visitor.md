# Visitor

Instead of polluting the class, represent operations on elements of a
heterogeneous structure as visitor objects.

## Classification

-   behavioural

## Problem It Solves

-   You want to carry out different operations on the same object
-   You don't want to pollute the class with operations such as:
    -   print
    -   getLenght
    -   addUp
    -   mult

## Implementation

1.  Create an *abstract* Visitor that defines the `visit(visitable)` method.
2.  Ensure implementors have the `accept(visitor)` method. They are the ones
    calling it by passing a reference to itself into the function.

## Example

``` {.java}
interface NodeVisitor<E> {
    E visit(HeadingNode<E> node);
    E visit(ParaNode<E> node);
    E visit(OtherNode<E> node);
}

interface VisitableNode<E> {
    E accept(NodeVisitor<E> visitor);
}

class HeadingNode<E> implements VisitableNode<E> {
    public String heading;

    public E accept(NodeVisitor<E> visitor) {
        return visitor.visit(this);
    }
}

class ParaNode<E> implements VisitableNode<E> {
    public String para;

    public E accept(NodeVisitor<E> visitor) {
        return visitor.visit(this);
    }
}

class OtherNode<E> implements VisitableNode<E> {
    public String other;

    public E accept(NodeVisitor<E> visitor) {
        return visitor.visit(this);
    }
}

class FmtVisitor extends StrReformatter implements NodeVisitor {

    public String visit(HeadingNode<String> node) {
        return reformat(node.heading);
    }

    public String visit(ParaNode<String> node) {
        return reformat(node.para);
    }

    public String visit(OtherNode<String> node) {
        return reformat(node.other);
    }
}
```

## Notes

-   Visitor encourages lightweight (possibly field-only) classes with
    functionality stored separately i.e. it decouples behaviour from state
-   good for:
    -   AST
    -   file system nodes
    -   recursive data structures
-   can apply an operation over a composite
-   more powerful than command
    -   it can carry out the right operation depending on the type of objects
    -   e.g. printing a file would be different to printing a directory --
        unlike command, visitor could make that distinction and choose the
        right method
