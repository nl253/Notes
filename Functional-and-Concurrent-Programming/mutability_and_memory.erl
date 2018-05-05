-module(mutability_and_memory).
-compile(export_all).

% This provides a process that acts like a mutable memory cell
% - i.e., a mutable variable in an imperative language
% But processes must get exclusive access to it via a "mutual exclusion lock".
% This avoids the race condition happening in preWeek19.erl and gives
% a deterministic execution.

% A safe memory cell that ensures  *mutual exclusion* of the the  *critical sections*
refSafe(State) ->
    receive
	% Receive a lock from Client, then Client has exclusive access
	{lock, Client} -> 
	    NewState = refSafeLocked(Client, State),
	    refSafe(NewState)
    end.

% Exclusive access of get/put for Client until unlock is received
refSafeLocked(Client, State) ->
    receive
	{put, Client, NewState} -> refSafeLocked(Client, NewState);
	{get, Client}           -> Client ! State, 
				   refSafeLocked(Client, State);
	{unlock, Client}        -> State
    end.

client1Safe(Ref, Parent) ->
    % Sleep randomly then lock the cell
    timer:sleep(round(rand:uniform() * 1000)),
    Ref ! {lock, self()},
    % Sleep randomly then get the value of the cell
    timer:sleep(round(rand:uniform() * 1000)),
    Ref ! {get, self()},
    % Sleep randomly then update the value of the cell to X + 1
    timer:sleep(round(rand:uniform() * 1000)),
    receive X -> Ref ! {put, self(), X + 1} end,
    % unlock and quit
    Ref ! {unlock, self()},
    Parent ! done1.

client2Safe(Ref, Parent) ->
    % Sleep randomly then lock the cell
    timer:sleep(round(rand:uniform() * 1000)),
    Ref ! {lock, self()},
    % Sleep randomly then get the value of the cell
    timer:sleep(round(rand:uniform() * 1000)),
    Ref ! {get, self()},
    % Sleep randomly then update the value of the cell to X + 2
    timer:sleep(round(rand:uniform() * 1000)),
    receive X -> Ref ! {put, self(), X + 2} end,
    % unlock and quit
    Ref ! {unlock, self()},
    Parent ! done2.

memory() ->
    Ref = spawn(?MODULE, refSafe, [0]),
    spawn(?MODULE, client1Safe, [Ref, self()]),
    spawn(?MODULE, client2Safe, [Ref, self()]),
    receive done1 -> done1 end,
    receive done2 -> done2 end,
    % When both clients are done, get the value ourselves
    Ref ! {lock, self()},
    Ref ! {get, self()},
    Ref ! {unlock, self()}, 
    receive 
	X -> io:fwrite("~s", [integer_to_list(X)])
    end.
