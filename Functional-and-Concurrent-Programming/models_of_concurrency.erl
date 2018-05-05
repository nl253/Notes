-module(models_of_concurrency).
-compile(export_all).

% Recall the usual map function
map(_, []) -> [];
map(F, [X|XS]) -> [F(X)|map(F, XS)].

% Example of blocked process

waiting() ->
    receive hello -> io:fwrite("Received hello") end,
    io:fwrite("I'm done").

exampleW() -> spawn(?MODULE, waiting, []) ! goodbye, ok.

% Example of simple worker

worker0(Target) -> Target ! 42.

example0() ->
    spawn(?MODULE, worker0, [self()]),
    % maybe do some other work in parallel with the 'worker'
    % then receive the return value
    receive X -> X * X * X end.

comp(X) -> timer:sleep(round(rand:uniform() * 1000)), X * X * X.

% Attemp 4 - Correct (Star topology)

workerD(F, X, Pid, Tag) -> Pid ! {Tag, F(X)}.

parmap4Main(_, [], _) -> [];
parmap4Main(F, [X|XS], N) -> 
    spawn(?MODULE, workerD, [F, X, self(), N]),
    YS = parmap4Main(F, XS, N + 1),
    receive {N, Y} -> [Y|YS] end.

parmap4(F, XS) -> parmap4Main(F, XS, 0).

% Take a list as parameter and for each element X compute X^3
go4(XS) -> parmap4(fun comp/1, XS).

% Attemp 4 - Correct (Star topology) - separated

starter(_, [], _) -> done;
starter(F, [X|XS], N) -> 
    spawn(?MODULE, workerD, [F, X, self(), N]),
    starter(F, XS, N+1).

collector(N, N) -> [];
collector(N, M) -> receive {N, X} -> [X|collector(N + 1, M)] end.

parmap4Alt(F, XS) -> 
    starter(F, XS, 0),
    % Collect from tags 0 to length(XS)
    collector(0, length(XS)).

% Take a list as parameter and for each element X compute X^3
go4Alt(XS) -> parmap4Alt(fun comp/1, XS).

% Attempt 4 - (Ring topology)

parmapInner(_, [],     Last)  -> Last;
parmapInner(F, [X|XS], First) -> parmapInner(F, XS, spawn(fun () -> First ! [F(X)|XS] end)).

goRing(XS) -> parmapInner(fun comp/1, XS, self()) ! [], receive YS -> YS end.
