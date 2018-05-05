% module must match file name - no dashes `-`.
-module(pipelines).
-compile(export_all).

%% @doc Emmits signals of value V or F() every M miliseconds N times.
%%
%% @param V value to emit
%% @param M milisecond delay
%% @param N number of cycles
%% @param P process

emitter(_V, _M, 0, P) when is_pid(P) -> 
    P ! stop, 
    io:fwrite("emitter ~p done~n", [self()]),
    done;

% run a function instead of using a fixed value
emitter(F, M, N, P) when is_function(F) and is_number(M) and is_number(N) and is_pid(P) -> 
    Result = F(),
    io:fwrite("emitting ~p to ~p~n", [Result, P]),
    P ! Result, 
    timer:sleep(M), 
    emitter(F, M, N - 1, P);

emitter(V, M, N, P) when is_number(M) and is_number(N) and is_pid(P) -> 
    io:fwrite("emitting ~p to ~p~n", [V, P]),
    P ! V, 
    timer:sleep(M), 
    emitter(V, M, N - 1, P).

%% @doc Wait for signals and duplicate them to all PS.
%%
%% @param PS processes

splitter(PS) when is_list(PS) -> 
    receive 
	stop -> 
	    splitterHelper(stop, PS), 
	    io:fwrite("splitter ~p done~n", [self()]), 
	    done;
	X -> splitterHelper(X, PS), 
	     splitter(PS)
    end.

splitterHelper(_X, []) -> done;
splitterHelper(X, [P|PS]) when is_pid(P) and is_list(PS) -> 
    P ! X, splitterHelper(X, PS).

%% @doc 
%% Combines value X with current value S using function F.
%% After receiving {get, PID()} signal, send result.
%% @end
%%
%% @param F function that takes S and X and combines them
%% @param X initial value
%% @param P next process PID

combiner(F, S) ->
    receive
	stop -> S;
	{get, N} -> N ! S, combiner(F, S);
	X -> combiner(F, F(S, X))
    end.

%% @doc Applies F to received items and sends to P.
%%
%% @param P process
%% @param F function

applicator(F, P) when is_function(F) and is_pid(P) ->
    receive
        stop -> io:fwrite("applicator ~p done~n", [self()]), done;
        X when is_pid(P) and is_function(F) -> 
	    Result = F(X),
	    {_, FunName} = lists:nth(2, erlang:fun_info(F)),
	    io:fwrite("~p ~p(~p) = ~p~n", [self(), FunName, X, Result]),
	    P ! Result, applicator(F, P)
    end.

sumApplicator(P) when is_pid(P) -> applicator(fun(XS) -> lists:foldl(fun(X, Y) -> X + Y end, 0, XS) end, P).
maxApplicator(P) when is_pid(P) -> applicator(fun(XS) -> lists:foldl(fun erlang:max/2, 0, XS) end, P).
prodApplicator(P) when is_pid(P) -> applicator(fun(XS) -> lists:foldl(fun(X, Y) -> X * Y end, 1, XS) end, P).

%% @doc Consumer is a sink ie a function that accepts messages but does not send.
%%
%% @param F function to execute on messages

consumer(F) when is_function(F) -> 
    receive 
	stop -> io:fwrite("consumer ~p done~n", [self()]), done; 
	X -> F(X), consumer(F) 
    end.

%% @doc 
%% Groups input into chunks (lists) of size N.
%% After collecting enough elements, sends chunk to process P and goes to initial state.
%% @end 
%%
%% @param N size of chunk
%% @param P process

chunker(P, N) when is_pid(P) and is_number(N) -> 
    chunkerHelper(P, N, N, []).

chunkerHelper(P, N, 0, XS) when is_pid(P) and is_number(N) and is_list(XS) -> 
    io:fwrite("grouped input into ~p~n", [XS]),
    P ! XS, 
    chunkerHelper(P, N, N, []);

chunkerHelper(P, N, K, XS) when is_pid(P) and is_number(N) and is_number(K) and is_list(XS) -> 
    receive 
	stop -> 
	    P ! stop, 
	    io:fwrite("chunker ~p done~n", [self()]), 
	    done; 
	X -> chunkerHelper(P, N, K - 1, [X|XS]) 
    end.

%% @doc Entry point into the program.

main() ->
    Printer = spawn(?MODULE, consumer, [fun(X) -> io:fwrite("received result: ~p at ~p:~p:~p~n", [X|tuple_to_list(time())]) end]),
    SumApplicator = spawn(?MODULE, sumApplicator, [Printer]),
    ProdApplicator = spawn(?MODULE, prodApplicator, [Printer]),
    MaxApplicator = spawn(?MODULE, maxApplicator, [Printer]),
    Splitter = spawn(?MODULE, splitter, [[ProdApplicator, SumApplicator, MaxApplicator]]),
    Chunker = spawn(?MODULE, chunker, [Splitter, 10]),
    Emitter = spawn(?MODULE, emitter, [fun () -> random:uniform(10) end, 100, 100, Chunker]).
