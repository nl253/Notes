# Object pool

Avoid waiting for instantiation of objects by having a pool that stores a
bunch of objects readlily available. If the pool's resources decrease, it
replenishes itself. Pools can have a cap on the number of objects alive / created.

## Classification

*   creational

## Problem it solves

*   You don't wait for object instantiation of expensive objects (eg thread)
*   You need to create many objects in short bursts

## Implementation

1.  Create a `Pool` class that when asked, immediately produces an `Object`
    and replenishes it's supply of `Object`s after every request.
2.  Implement a cap (think counting semaphore) on the number of objects.
3.  If you spawned so many `Object`s that the cap has been reached but you are receiving a request for another one, block the thread.

## Example

## Notes

1.  Good when you expect to ask for _expensive_ objects _in bursts_. Eg you have
    a `Queue` of `Task`s, and you wish to spawn 5 `Thread`s ~immediately~ rather
    than after 5 seconds.
2.  The `Pool` might wish to keep a reference to _released_ `Object`s to
    see whether they are ~dead~ to see if it's ## Ok to add another one to the `Pool` to replace it.
3.  Another way of dealing wiht that may be returning the objects to the
    `Pool` once you are done with it. Think _acquire_ and _release_.
4.  It makes sense to make `Pool`s _singletons_.
5.  You must be able to clean up unused objects periodically.
