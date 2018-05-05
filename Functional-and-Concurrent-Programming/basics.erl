-module(basics).
-compile(export_all).

% Run me by loading up 'erl' and doing:
% c(preLecture11).
% preLecture11:exampleCom().

% The order that the 'Receive' messages are output by
% the server shows us something important about how
% Erlang mailboxes work, which we will cover in Lectures 11 and 12.

% 'server' is a function which we use to define a concurrent
% process which repeated receives either an atom 'y' or 'z': 
server() ->
    receive
	% Look in the "mailbox" and see if there is an 'x' message
	x -> io:fwrite("Received x~n");
	% or see if there is a 'y' message in the mailbox
	y -> io:fwrite("Received y~n")
	     % Uncomment ne to get a different behaviour
	     % z -> io:fwrite("Received z~n")
    end,
    server().

% 'client' is a function used to define a concurrent client
% process which sends several message to the process 'S'
client(S) ->
    S ! z,  % send atom 'z' to 'S'
    S ! y,  % send atom 'y' to 'S'
    S ! x,  % send atom 'x' to 'S'
    done. % return the symbol 'done'.

% 'exampleCom' is the setup function which starts both the client
% and server processes
exampleCom() ->
    % Spawn (create) the server function as a process whose "id" is bound
    % to the variable 'Server'
    Server = spawn(fun server/0),
    % Start the client process, passing it the 'Server' process id.
    client(Server).
