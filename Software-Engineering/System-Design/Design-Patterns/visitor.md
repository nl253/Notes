# Visitor

Instead of polluting the class, represent operations on elements of a
heterogeneous structure as visitor objects.

## Classification

-   behavioural

## Problem It Solves

-   You want to carry out different operations on the same object
-   You don't want to pollute the class with operations such as:
    -   `print()`
    -   `getLenght()`
    -   `addUp()`
    -   `mult()`

## Implementation

1.  Create an *interface* `IVisitor<E>` that defines the `E visit(IVisitable visitable)` method.
2.  Ensure implementors have the `E accept(IVisitor<E> visitor)` method. They are the ones
    calling it by passing a reference to itself into the function.

## Example

``` {.java}
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@FunctionalInterface
interface IVisitable<E> {
    default E accept(IVisitor<E> visitor) {
        return visitor.visit(this);
    }
}


@FunctionalInterface
interface IVisitor<E, V extends IVisitable<E>> {
    E visit(V visitable);
}

class BinaryTreeAdder implements IVisitor<Integer> {
    @Override
    public final Integer visit(final BinaryTree<Integer> visitable) {
        if (visitable == null) return 0;
        int result = 0;
        final BinaryTree<Integer> t = (BinaryTree<Integer>) visitable;
        if (t.data != null) result += t.data;
        if (t.left != null) result += t.left.accept(this);
        if (t.right != null) result += t.right.accept(this);
        return result;
    }
}

class BinaryTree<E extends Comparable<E>> implements IVisitable<E> {

    public BinaryTree<E> left, right;
    public E data;

    public BinaryTree(final E... vals) {
        this(Arrays.asList(vals));
    }

    public BinaryTree(final List<E> vals) {
        insertAll(vals);
    }

    private void insertAll(final Collection<E> vals) {
        for (final E val : vals) insert(val);
    }

    private void insert(final E val) {
        if (data == null) data = val;
        else if (val.compareTo(data) < 0) {
            if (left == null) left = new BinaryTree<>();
            left.insert(val);
        } else {
            if (right == null) right = new BinaryTree<>();
            right.insert(val);
        }
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
