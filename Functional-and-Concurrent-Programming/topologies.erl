-module(topologies).
-compile(export_all).

% Example of blocked process

waiting() -> receive hello -> io:fwrite("Received hello") end, io:fwrite("I'm done").

exampleW() -> spawn(?MODULE, waiting, []) ! goodbye, ok.

% Example of simple worker
example0() ->
    spawn(?MODULE, worker0, [self()]),
    % maybe do some other work in parallel with the 'worker'
    % then receive the return value
    receive X -> X * X * X end.

worker0(Target) -> Target ! 42.

% Recall the usual map function
map(_, []) -> [];
map(F, [X|XS]) -> [F(X)|map(F, XS)].

% Attempt 1 at a parallel version of map
worker1(F, X) -> F(X).

parmap1(_, [])     -> [];
parmap1(F, [X|XS]) -> 
    % Ultimately the flaw is here, spawn of node does not
    % the result of node, and we don't do anything with it
    spawn(?MODULE, worker1, [F, X]),
    parmap1(F, XS).

% Take a list as parameter and for each element X compute X^3
go1(XS) -> parmap1(fun comp/1, XS).

% Attempt 2
comp(X) -> timer:sleep(round(rand:uniform() * 1000)), X * X * X.

workerC(F, X, Pid) -> Pid!(F(X)).

parmap2(_, []) -> [];
parmap2(F, [X|XS]) -> 
    spawn(?MODULE, workerC, [F, X, self()]),
    receive Y -> [Y|parmap2(F, XS)] end.

% Take a list as parameter and for each element X compute X^3
go2(XS) -> parmap2(fun comp/1, XS).

% Attempt 3

parmap3(_, []) -> [];
parmap3(F, [X|XS]) -> 
    spawn(?MODULE, workerC, [F, X, self()]),
    receive Y -> [Y|parmap3(F, XS)] end.

go3(X) -> parmap3(fun comp/1, X).

% Attemp 4 - Correct (Star topology)

workerD(F, X, Pid, Tag) -> Pid!{Tag, F(X)}.

parmap4Main(_, [], _) -> [];
parmap4Main(F, [X|XS], N) -> 
    spawn(?MODULE, workerD, [F, X, self(), N]),
    receive {N, Y} -> [Y|parmap4Main(F, XS, N + 1)] end.

parmap4(F, XS) -> parmap4Main(F, XS, 0).

% Take a list as parameter and for each element X compute X^3
go4(XS) -> parmap4(fun comp/1, XS).

% Attemp 4 - Correct (Star topology) - separated

starter(_, [], _) -> done;
starter(F, [X|XS], N) -> 
    spawn(?MODULE, workerD, [F, X, self(), N]),
    starter(F, XS, N + 1).

collector(N, N) -> [];
collector(N, M) -> receive {N, X} -> [X|collector(N + 1, M)] end.

parmap4Alt(F, XS) -> 
    starter(F, XS, 0),
    % Collect from tags 0 to length(XS)
    collector(0, length(XS)).

% Take a list as parameter and for each element X compute X^3
go4Alt(XS) -> parmap4Alt(fun comp/1, XS).

% Attempt 4 - (Ring topology)

parmapInner(_, [],     Last) -> Last;
parmapInner(F, [X|XS], Left) -> parmapInner(F, XS, spawn(fun () -> receive YS -> Left ! [F(X)|YS] end end)).

parmapRing(F, XS) -> parmapInner(F, XS, self()) ! [], receive YS -> YS end.

% Take a list as parameter and for each element X compute X^3
goRing(XS) -> parmapRing(fun comp/1, XS).
