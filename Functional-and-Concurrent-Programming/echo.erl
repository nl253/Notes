-module(echo).
-export([start_link/0, count/0, echo/1, reset/1, stop/0]).

% Example interaction
% > c(echo).
% > echo:echo(x).
% x
% > echo:echo(y).
% y
% > echo:echo(z).
% z
% > echo:count().
% 3
% > echo:reset(3).
% ok
% > echo:count().
% 3
% > echo:echo(z).
% z
% > echo:count().
% 4

start_link() -> 
    Pid = spawn_link(fun init/0),
    register(echo, Pid).

count() ->
    ?MODULE ! {count, self()},
    receive
	Reply -> Reply
    end.

echo(X) ->
    ?MODULE ! {X, self()},
    receive
	Reply -> Reply
    end.

reset(N) ->
    ?MODULE ! {{reset,N}, self()},
    ok.

stop() ->
    ?MODULE ! {stop, self()},
    ok.

init() ->
    State = 0,
    loop(State).

loop(N) ->
    receive
	{count, From} ->
	    From ! N,
	    loop(N);
	{{reset,X}, _From} ->
	    loop(X);
	{stop, _From} ->
	    ok;
	{Msg, From} ->
	    From ! Msg,
	    loop(N+1)
    end.    
