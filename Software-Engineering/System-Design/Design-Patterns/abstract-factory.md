# Abstract factory

Instead of hard-coding concrete implementations, use a Factory that will
choose the implementation depending on the environment.

## Classification

-   creational
-   object oriented

## Problem it solves

-   You don't need to use the `new` keyword.
-   *You don't need to specify concrete classes*.
-   You create *less dependencies*.

## Implementation

Within the `InterfaceName`Factory class you make a (possibly static)
method that will return instances of objects that implement
`InterfaceName`. Particular implementation (conrete type) will be chosen
in the body of that function.

## Example

-   A `Factory` can choose the appropriate implementation of, say a
    `UserInterface` object, based on the operating system.

-   The user of this API would only need to interact with the
    `UserInterfaceFactory` regardless of what operating system they use.
    Ideally, *only* the `Factory` will make use of the `new` keyword.

## Notes

1.  Good for cross-platform applications.

2.  Sometimes creational patterns are competitors.

There are cases when either `Prototype` or `Abstract Factory` could be
used profitably. At other times they are *complementary*
`Abstract Factory` might store a set of `Prototypes` from which to clone
and return product objects.

Builder can use one of the other patterns to implement which components
get built. `Abstract Factory`, `Builder`, and `Prototype` can use
`Singleton` in their implementation.
