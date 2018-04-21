# Observer

Create an dependency such that a change in one objects triggers a reaction
in others.

## Classification

*   behavioural

## Problem it solves

*   decouples `View`s from `Controller`s

## Implementation

1.  have a single `Subject` that is being watched by a collection of `Observers`
2.  the `Subject` may `register()` additional `Observer`s to watch her state
3.  the `Subject` notifies the `Observer`s whenever changes are made to it
4.  the `Observer`s may query `Subject`'s status eg `getStatus()`

## Example

Stock market - changes in the value of shares are reflected automatically
on the website and the mobile apps. Website and the app are the `Observer`s
that respond to change in the `Engine` or `Core`.

## Notes

*   This is the "View" part of _## Mvc_.
*   In Java use the `Observer` interface and the `Observable` class
