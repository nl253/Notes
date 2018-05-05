# Proxy

Create a wrapper object that you use access/control the actual object.

## Classification

*   structural
*   object oriented

## Problem it solves

1.  When you need to create expensive objects (eg. Database), you might want
    to postpone initialisation (eg. connecting to the database, wating for the
    response) until the client actually requests that explicitly.

2.  You want some indirection to, for instance, vertify id/pass (logic) before
    forwarding the request to the real object.

## Implementation

*   You need to wrap the actual object with the `Proxy` class. This allows
    you to postpone expensive operations until the appropriate method is called.
*   All requests passed to the `Proxy` are forwarded to the object it wraps.
*   You define an interface that will make the proxy and the original object interchangeable.

## Example

*   `ConnectionProxy`
*   `DatabaseProxy`

## Notes
