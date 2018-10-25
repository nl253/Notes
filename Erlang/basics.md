# Concurrency - Basics

## Theory

Sequential programs are all programs written by us so far.
In sequential programming, instructions are executed by the CPU one at a
time, one after another.

There was a time in processor design where CPU designers realised that the
only way to make CPUs faster, is to have multiple CPU cores on a single chip.

Concurrency is when two things are independent of each other. They might
be executed in parallel ie at the same time.

## Inter-Process Commuication

In programming languages like Erlang (Go, Haskell etc), processes send
messages to each other. Another approach to dealing with concurrency is to 
use **threads**.

Threads compete for CPU time on a single core. Eg all tabs on Google Chrome
are separate threads, selecting a tab wakes them up.

Erlang was written specifically to be concurrent so it's much easier than
in other languages.

In Erlang processes might be forced to wait for a message with the `receive`
built-in.

## Spawning Processes

When you spawn a process with Erlang, the runtime assigns it a PID
(Process ID). This can be stored in a variable and used later to send
messages to that process.

### Syntax

Send the atom `z`, `y` and `x` to process `S`:

```erlang
client(S) ->
  S!z,
  S!y,
  S!x,
  done.
```

Receive the messages:

```erlang
server() ->
  receive
	x -> io:fwrite("received x ~n");
	y -> io:fwrite("received y ~n")
  end.
```

Each time you "receive" something, it is received from the mailbox.
The atom `z` was not "received". That means that it sits in the mailbox
(queue).

When you call `receive` but the process cannot find a matching pattern, the 
process will wait forever waiting for a matching pattern.

### Controllers

Controllers spawn everything:

```erlang
program() -> 

  % store the PID in C
  C = spawn(?MODULE, consumer, []), % spawn the consumer, don't pass any args

  % pass C (PID of the consumer porcess just spawned) 
  % as an argument to producer
  spawn(?MODULE, producer, [C]). 
  spawn(?MODULE, producer2, [C]). 
```

### Consumer

You first create consumers because they don't require parameters.

Use the `spawn` built-in (overloaded to take 1 or 3 params).

The `?MODULE` macro expands to the name of the current module.

```erlang
spawn(module(), fun(), [any()]) -> pid()
spawn(fun()) -> pid() 
```

Consumers don't take parameters so they are spawned first.

```erlang
consumer() -> 
  receive
	{msg, X} -> io:format("received ~s~n", [X]),
	  % call itself again waiting for another message
	  Consumer(); 
	stop -> stop
  end.
```

### Producers

A function that takes another `PID` and sends messages to it.
`stop` acts as a signal.

```erlang
producer(Consumer) ->  
  Consumer!{msg, "Hi"},
  Consumer!{msg, "Hello"},
  Consumer!stop.
```

When you have more than one producer sending messages to the same consumer:

```erlang
producer2(Consumer) ->  
  Consumer!{msg, "Hi"},
  Consumer!{msg, "Hello"},
  Consumer!stop.
```

Both send `stop`. This means that the actions of `producer` may cause the
consumer to stop working before all messages from `producer2`.

## Erlang Processes

In the shell you can run these:

get the current process `self(). `

get all process running currently, whenever you spawn a new process it
will show up in that list

	`processes().`

create a new process

	`spawn(fun () -> io:write("sldjf laskdj flkasdj f") end).`

spawn immediately returns PID.

You don't need to create an anonymous function every time, 

eg `fun server/2` 

is a reference to the server function that accepts 2 parameters

block the current execution for n number of seconds:

	`timer:sleep(10).`

`spawn(ModuleName, function, parameterListToTheFunction).`

```erlang
-spec spawn(?MODULE, fun(), [any()]) -> pid().
```

--------------------------------------------------------

Unlike in other languages, processes in Erlang are very lightweight so
it's OK to launch many of them.

A long running-repetitive process should generally be implemented using
tail-recursion.

```erlang
example2() -> spawn(notes, fun io:format/2, ["Hello", "world!"]).
```

Each process has a single 'mailbox' (which is a queue) which stores messages that have been
sent to that process.
