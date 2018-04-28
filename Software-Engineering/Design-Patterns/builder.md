# Builder

Split the construction of a complex object into multiple function calls.
At the end run `build()` to create it.

## Classification

-   creational
-   object oriented

## Problem It Solves

Some objects require more components to 'build' them. We want to avoid
constructors with >10 arguments.

## Example

-   `StringBuilder` -- use `append()` and later run `toString()`
-   `StreamBuilder`
