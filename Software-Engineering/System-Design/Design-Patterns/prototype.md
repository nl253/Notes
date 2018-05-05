# Prototype

Instatntiate objects using a `prototype-instance`. An instance of this
class becomes a _breeder_ for all future instances.

## Classification

*   creational
*   object oriented

## Problem it solves

You don't need to use the `new` keyword.

## Implementation

1.  Add a `clone()` method
2.  Replace all calls to `new` with the factory method

## Example

*   A `Cell` has a `split()` method, that makes a complete copy of itself.
*   An `Person` instance may have a `giveBirth` method that returns an instance of `Person`

## Notes

This is one of the many creational patterns and in some cases you are free
to choose from, for instance, `Factory` and `Prototype`. In other cases
you might prefer one over the other.
