# Factory Method

![factory method UML](./factory-method.png)

Instead of hard-coding a concrete class implementation, define an interface
with a single method that instantiates a subclass (e.g. `Product`).

## Classification

-   creational
-   object-oriented

## Problem It Solves

-   you don't use the `new` operator
-   concrete subclasses of some abstract class can be chosen dynamically based
    on e.g. OS, client type (API user)

## Steps

1.  Define an interface with a single method that instantiates an entity (e.g.
    `Product`)
2.  Defer the instantiation details (which subclass of e.g. `Product` to use on
    which OS) to interface implementors
