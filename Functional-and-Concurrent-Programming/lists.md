# Notes - Lecture 5

1.  define a function that takes the first 9 elements from a list

``` {.erlang}
take(0, "hello") == [].
take(4, "hello") == "hell".
```

2.  We can specify the type of functions (input and output)

``` {.erlang}
-spec take(integer(), [T]) -> [T].
```

3.  In the standard library there is a `lists` module

``` {.erlang}
lists:split(3, "hello") == {"hel", "lo"}.
```

We can use pattern matching to extract relevant information.

``` {.erlang}
take(N, Xs) ->
    {Ys, _} = lists:split(N, Xs),
    Ys.
```

This still throws an exception when you pass a number greater than the length
of the string. We can use a case statement to solve this issue. This is an
example how you can reuse a library function to achieve your aim.

``` {.erlang}
take(N, Xs) ->
    case length(Xs) < N of
    true ->
        Xs;
    false ->
        {Ys, _} = lists:split(N, Xs),
        Ys
    end.
```

Rewriting that to tail recursive definition:

``` {.erlang}
% takes a number N and a list Xs
% returns the first N elements of Xs.
take(0, Xs) -> [];
take(N, []) -> [];
take(N, [X|Xs]) -> [X|take(N - 1, Xs)].
```

Drop:

``` {.erlang}
% drop the first N elements from the list Xs
drop(0, Xs) -> Xs;
drop(N, []) -> [];
drop(N, [X|Xs]) -> drop(N - 1, Xs).
```

Sum:

``` {.erlang}
sum([]) -> 0;
sum([N|Ns]) -> N + sum(Ns).
```

Concat:

``` {.erlang}
"abc" ++ "de" == "abcde". % using the operator
```

Join a list of lists:

``` {.erlang}
concat([]) -> [];

% join a list of lists
concat([L|Ls]) ->
    L ++ concat(Ls).
```

Sorting:

e.g. for `[3, 2, 1, 5, 4]`

head: `3`

tail: `[2, 1, 5, 4] -> [1, 2, 4, 5]`

we want: `[1, 2, 3, 4, 5]`

we need to insert an element at the right place in the sorted list

``` {.erlang}
sort([]) -> [];
sort([X|Xs]) -> insert(X, sort(Xs)).

insert(X, []) -> [X];
insert(X, [Y|Ys]) when X =< Y -> [X|[Y|Ys]];
insert(X, [Y|Ys]) -> [Y|insert(X, Ys)].
```
