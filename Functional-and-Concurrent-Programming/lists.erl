-module(lists).
-compile(export_all).

-spec take(integer(), [T]) -> [T].

my_take(N, Xs) ->
    case length(Xs) < N of
	true ->
	    Xs;
	false ->
	    {Ys, _} = lists:split(N, Xs),
	    Ys
    end.

% takes a number N and a list Xs
% returns the first N elements of Xs.
take(0, Xs) -> [];
take(N,[]) -> [];
take(N, [X|Xs]) -> [X|take(N - 1, Xs)].

% drop the first N elements from the list Xs
drop(0, Xs) -> Xs;
drop(N, []) -> [];
drop(N, [_|Xs]) -> drop(N - 1, Xs).

sum([]) -> 0;
sum([N|Ns]) -> N + sum(Ns).

% join a list of lists
concat([]) -> [];
concat([L|Ls]) ->
    L ++ concat(Ls).

% sort a list of numbers
sort([]) -> [];
sort([X|Xs]) -> insert(X, sort(Xs)).

stars(1) -> io:format('*~n');

stars(N) when N > 1 ->
    io:format('*'),
    stars(N - 1).

build(I, I) -> [I];
build(I, J) when I < J -> [I|build(I + 1, J)].

maximum([X|[]]) -> X;
maximum([X|[Y|Ys]]) when X =< Y -> maximum([Y|Ys]);
maximum([X|[Y|Ys]]) -> maximum([X|Ys]).

member(N, []) -> false;
member(N, [X|Xs]) when X == N -> true;
member(N, [X|Xs]) -> member(N, Xs).

greater(N, []) -> [];
greater(N, [X|Xs]) when X >= N -> [X|greater(N, Xs)];
greater(N, [X|Xs]) when X =< N -> greater(N, Xs).

% eg for [3, 2, 1, 5, 4]
% head: 3
% tail: [2, 1, 5, 4] -> [1, 2, 4, 5]
% we want: [1, 2, 3, 4, 5]
% we need to insert an element at the right place in the sorted list

insert(X, []) -> [X];
insert(X, [Y|Ys]) when X =< Y -> [X|[Y|Ys]];
insert(X, [Y|Ys]) -> [Y|insert(X, Ys)].
