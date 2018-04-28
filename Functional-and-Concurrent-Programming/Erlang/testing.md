# Testing - Lecture 6 

## EUnit - Unit Testing Framework 

**EUnit** is equivalent to JUnit in Java.

Include a library:

```erlang
-include_lib("eunit/include/eunit.hrl"). % import the testing framework

take_3_test() -> ?assertEqual(take(1, [3, 4, 5]), []).
```

Unit testing is weak. You can only run tests on a few inputs.

> 'Program testing can be used to show the presence of bugs, but never to show their absence!' - E. Dijkstra

## Test Functions

```erlang
test1() -> test1.
test2() -> test2.
```

## Property-Based Testing

<a href="https://github.com/manopapad/proper">PropEr</a> is a clone of
**QuickCheck** with an open source licence.  QuickCheck was originally
written for Haskell. There are clones for all sorts of languages.

You define what property should hold for that piece of program.

```erlang
-include_lib("proper/include/proper.hrl").  % import

take(...) -> ...;

take_prop2() ->
  ?FORALL(N, nat()),
	?FORALL(Xs, list(nat()), length(take(N, Xs)) == N).
```

QuickCheck will run the tests on many (pseudo)random inputs.

If a counterexample is found, ie if a property does not hold, QuickCheck
will reduce the counterexample to the simplest case.
