-module(states).
-compile(export_all).

% The following function, when spawned as a process, acts like
% a reference to a shared memory cell
ref(State) -> 
    receive 
	% Receive a request to retrieve the currenst ate
	{get, P}        -> P ! State, ref(State);
	% Recive a request to update the sate
	{put, NewState} -> ref(NewState)
    end.
% The stateful behaviour is implemented via recursion: the
% parameter of the recursive function is the currently "stored"
% value.

% Here are two clients which interact with the memory cell reference
% They have some random sleeping in order to show problems with
% non-determinism in concurrency.
client1(Ref, Parent) ->
    % Sleep randomly then get the value of the cell
    timer:sleep(round(rand:uniform() * 1000)),
    Ref ! {get, self()},
    receive X ->
		% Sleep randomly then update the memory cell, with X + 1
		timer:sleep(round(rand:uniform() * 1000)),
		Ref ! {put, X + 1}
    end,
    % Tell the parent process that you are done
    Parent ! done1.

client2(Ref, Parent) ->
    % Sleep randomly then get the value of the cell
    timer:sleep(round(rand:uniform()*1000)),
    Ref ! {get, self()},
    receive X ->
		% Sleep randomly then update the memory cell, with X + 2
		timer:sleep(round(rand:uniform() * 1000)),
		Ref ! {put, X + 2}
    end,
    % Tell the parent process that you are done
    Parent ! done2.

% Run preWeek19:example() a few times and see that we get different
% values, i.e., this is non deterministic
example() ->
    % Start a memory cell process, initialised at 0
    Ref = spawn(?MODULE, ref, [0]),
    % Start the two clients
    spawn(?MODULE, client1, [Ref, self()]),
    spawn(?MODULE, client2, [Ref, self()]),
    % Wait to receive notification that they are done
    receive done1 -> done1 end,
    receive done2 -> done2 end,
    % Get the current value of the memory cell and print it
    Ref ! {get, self()},
    receive X -> io:fwrite("~s~n", [integer_to_list(X)]) end.
