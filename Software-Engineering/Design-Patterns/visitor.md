# Visitor

Represent an operation on many different types as Visitor Objects.

## Classification

*   behavioural

## Problem It Solves

*   You want to carry out the same operation on objects of different types (eg list file and directory)
*   You don't want to check for types / cast
*   You have objects with different interfaces and you wish to abstract away the way in which they are traversed

## Implementation

1.  Create an _abstract_ Visitor that defines the `visit(visitable)` method.
2.  Ensure implementors have the `accept(visitor)` method. They are the ones calling it by passing a reference it itself into the function.

## Example

A `PrintVisitor` may be used to traverse (and print) `Graph`, `Tree`
and `Heap` objects if it overloads appropriately the `visit()` method:

*   `visit(Graph g);`
*   `visit(Tree t);`
*   `visit(Heap h);`

We could also have a `CountingVisitor` that counts the elements as it
traverses them. They way in which that would be done would depend on the
types of input objects. This will allow us to count nested objects.

- `visit(leaf) -> 1`
- `visit(node) -> 1 + node.getNodes.map(CountingVisitor::visit).sum()`

## Notes

*   Visitor encourages lightweight (possibly field-only) classes with
    functionality stored separately.

vim:wrap:tw=79:linebreak:fo=torcn:
