# Introduction to Concurrency

Alan Kay said OOP as he envisioned it, was [more about messaging than anything else](http://userpage.fu-berlin.de/~ram/pub/pub_jf47ht81Ht/doc_kay_oop_en).
He's right. Computer scientists should have good mental models, and
good programming models, for how objects behave and how they communicate
with each other.

## Examples of Concurrency

Computing systems model the world, and the world contains **actors**
that execute indepedently of, but communicate with, each other. In
modeling the world, many (possibly) parallel executions have to be
composed and coordinated, and that's where the study of **concurrency**
comes in. Consider:

-   Railway Networks (note shared sections of track)
-   Gardening (people rake, sweep, mow, plant, etc.)
-   Machines in a factory
-   Banking systems
-   Travel reservation systems
-   Multiplayer games
-   Well coordinated teams or societies ([Awesome example](http://en.rocketnews24.com/2014/11/19/1200-japanese-workers-convert-above-ground-train-to-subway-line-in-a-matter-of-hours/))
-   Your daily routines (are you good at *multitasking*?)
-   The brain --- the mind, really --- as shown in Minsky's [Society of Mind](http://www.amazon.com/The-Society-Mind-Marvin-Minsky/dp/0671657135)

Hardware examples:

-   A single processor can have multiple execution pipelines (but only
    one set of registers)
-   A processor can have multiple cores (multicore)
-   A computer can have multiple processors
-   A network can have multiple computers (Grid computing happens on
    these)
-   An internet has multiple networks
-   All combinations of the above

Software examples:

-   Multiprogramming, or running a lot of programs concurrently (the
    OS has to multiplex their execution on available processors). For
    example:
    -   downloading a file
    -   listening to streaming audio
    -   having a clock running
    -   chatting
    -   analyzing data from some weird experiment
    -   streaming a movie
    -   printing something
    -   running a simulation
    -   typing in a text editor
    -   running a web server, sendmail, and other daemons
-   Simulation programs
-   High volume servers (multiple clients are serviced at the same time
    (possibly in parallel, possible with interleaved execution) to give
    the appearance of responsiveness)
-   Pipes -- for example

``` sh
$ sort | uniq | format | print
```

Even more examples:

-   The expression $(ab+cd^2)(g+fh)$ can be evaluated in four clocks
-   Many algorithms can be broken up into concurrent parts:
    -   Mergesort
    -   Quicksort
    -   Summing a list by summing fragments in parallel
    -   Operating on independent slices of an array

```go
// Each of the K processors or execution units can work on the
// code below for a specific value for I, so the loop can
// be executed in ceil(N/K) "steps".
for I in 1..N {
    D[I] := A[I] + B[I] * C[I]
}
```

-   Computing primes
-   Checking equality of leaf sequences in trees
-   Computing $n \choose k$

## Modeling Concurrency

High-level models for the agents include:

-   **Actors**: communicate with each other via mailboxes
-   **Goroutines**: communicate with each other via channels
-   **Coroutines**: communicate by yielding to each other

There are probably two main philosophies at play here:

-   Threads
-   Events

Read this [neutral analysis of both styles](http://berb.github.io/diploma-thesis/original/043_threadsevents.html).

Generally speaking, threading requires that you use, locks, mutexes,
countdowns, condition variables, semaphores, and similar things. But
there do exist higher-level synchronization mechanisms like monitors and
Ada-style protected objects.

## Definitions

### The Concrete

Program
:   The source code for a process or processes.

Process
:   A unit of program execution as seen by an operating system.
    Processes tend to act like they are the only thing running. A
    process has its own address space, file handles, security
    attributes, threads, etc. The operating system prevents processes
    from messing with each other.

Thread
:   A sequential flow of control within a process. A process can contain
    one or more threads. Threads have their own program counter and
    register values, but they share the memory space and other resources
    of the process.

Multiprogramming/Multiprocessing
:   Concurrent execution of several programs on one computer.

Multithreading
:   Execution of a program with multiple threads.

### The Abstract

Parallelism
:   Truly simultaneous execution or evaluation of things.

Concurrency
:   The coordination and management of independent lines of execution.
    These executions can be truly parallel or simply be managed by
    interleaving. They can communicate via shared memory or message
    passing. (Rob Pike's definition: "the composition of independently
    executing computations")

Distribution
:   Concurrency in which *all* communication is via message passing
    (useful because shared memory communication doesn't scale to
    thousands of processors).

### The Pragmatic

Concurrent Programming
:   Solving a single problem by breaking it down into concurrently
    executing processes or threads.

Shared Resource
:   An entity used by more than one thread but one that (usually) can
    only be operated on by one thread at a time.

Synchronization
:   The act of threads agreeing to coordinate their behavior in order to
    solve a problem correctly. (This usually means that thread $A$
    will block and wait until thread $B$ performs some operation.)

## Why Study Concurrency?

-   Real world systems are naturally concurrent, and computer science is
    about modeling the real world.
-   Concurrency is useful in multicore, multiprocessor and distributed
    computer systems.
    -   Increased performance from true parallelism
    -   Increased reliability (fault tolerance)
    -   Specialized processors (graphics, communication, encryption ...)
    -   Some applications, like email, are inherently distributed
-   Concurrent programming often results in superior program structure:
    write code for the different tasks and let some *separate* engine
    schedule the tasks.

    **Example**: It's nice when
    writing code to mine data, analyze telemetry, write massive files to
    disk, or produce frames for a movie, to not have to chunk up your
    code and shove in checks for the keyboard and mouse and other
    devices.

    **Example**: When writing a word processor, it's a lot simpler to
    write separate threads for
    -   reading keystrokes and collecting characters into words
    -   collecting words to fill a line
    -   hyphenating where necessary
    -   adding spacing to justify with right margin
    -   inserting page breaks where necessary
    -   checking spelling
    -   saving (periodically)

    > One difficulty in doing this sequentially is that "hyphenation
    > requires a portion of the word to be returned to the stream of words
    > to await the next line
    >
    > --- Ben-Ari, 1990.

## Is this stuff hard?

Well, yeah. Concurrent programming is harder than sequential programming
because concurrent programming is about writing a bunch of communicating
sequential programs. You end up with a bunch of problems that don't
exist in sequential programs:

-   How do you get concurrent threads to **synchronize** when necessary
    so they don't make a mess of **shared resources**?
-   How do you avoid **race conditions**, **deadlock**, and
    **starvation**?
-   How do you ensure **safety** and **liveness**?
-   How do you prove these nasty things **partially correct**? **Totally
    correct**?
-   How do you partition the code into threads, or partition the
    processes on processors so that everything runs **efficiently**?
-   How do you recover if one of the nodes in a distributed system
    **fails?** (Fault tolerance)

Single-threaded event-based systems have issues, too. You need to make
sure each chunk of code (responses to events) always run quickly and
leave everything in a consistent state.

## Concerns

The field of concurrent programming is concerned with

-   Modeling
-   Granularity
-   Scheduling
-   Communication
-   Synchronization
-   Language Integration
-   Implementation

### Modeling

We need a formal way to talk about concurrent programming so that we can
analyze requirements and design and implement correct and efficient
algorithms. One of the most useful models used in reasoning about
concurrent programs is the **non-realtime interleaved execution model**.
This is:

> The study of interleaved execution sequences of atomic instructions,
> where each of the instructions execute in a completely arbitrary but
> finite amount of time.

In this model we can make *no* assumptions at all about the relative
speeds of the individual instructions, or how malicious a scheduler
might be. Since instructions take arbitrary time, there can be many
possible interleavings.

**Example**: Suppose thread-1 has instruction sequence $A,B,C$ and
thread-2 has sequence $x,y$. Then we have to consider:

    A B C x y
    A B x C y
    A B x y C
    A x B C y
    A x B y C
    A x y B C
    x A B C y
    x A B y C
    x A y B C
    x y A B C

**Exercise**: Write out all interleavings of two threads, the first with
sequence $A,B,C$ and the second with $x,y,z$.

The number of interleavings for two threads, one with $m$ instructions
and one with $n$ instructions is $m+n \choose n$ --- which you
probably know is $\frac{(n+m)!}{n!m!}$.

**Exercise**: How many interleavings are possible with $n$ threads
where thread $i$ has $k_i$ instructions?

### Granularity

Systems can be classified as to the level of concurrency that can be
expressed or implemented.

#### Instruction Level

Most processors have several execution units and can execute several
instructions at the same time. Good compilers can reorder instructions
to maximize instruction throughput. Often the processor itself can do
this.

**Example**: On the old Pentium microprocessor, the instruction sequence

    inc ebx
    inc ecx
    inc edx
    mov esi,[x]
    mov eax,[ebx]

would be executed as follows:

  Step  U-pipe  V-pipe
  ----- ------- ------------
  0     inc ebx inc ecx
  1     inc edx mov esi, [x]
  2     mov eax [ebx]

Modern processors even parallelize execution of micro-steps of
instructions within the same pipe.

#### Statement Level

Many programming languages have syntactic forms to express that
statements should execute in sequence or in parallel. Common notations:

##### Pascal-style          

```pascal
begin      
    A;      
    cobegin 
        B;   
        C;   
    coend   
    D;      
    cobegin 
        E;   
        F;   
        G;   
    coend       
end                       
```

##### Occam-style           

```
 SEQ    
    a   
    PAR 
       b
       c
    d   
    PAR 
       e
       f
       g
```

##### Algebraic

```        
 a ; b || c ; d ;
                 
  e || f || g    
```

#### Procedure Level

Many languages have a thread type (or class) and a means to run a
function on a thread. These things might also be called **tasks**.
Alternatively there might be a library call to spawn a thread and
execute a function on the thread.

#### Program Level

Usually the responsibility of the operating system, which runs processes
concurrently.

Many programming languages give you the ability to spawn processes from
your program.

In Java:

``` java
Runtime.getRuntime().exec(commandline);
```

In C, using the Win32 API:

```cpp
CreateProcess(security_attributes, stack_size, &function, param, activation, &thread_id);
```

**Exercise**: Show how to spawn a new process using the Unix system calls `execve`{.cpp} with `fork`{.cpp}.)

### Scheduling

Generally you'll have $M$ processors and $N$ threads.  If $M < N$ you need a 
**scheduler** to interleave execution.

### Communication

The threads of control must communicate with each other. The two main
ways to communicate:

-   **Shared Memory** (Indirect)
-   **Message Passing** (Direct)

### Synchronization

Threads sometimes have to coordinate their activities. Here is the
overused classic example: two threads $A$ and $B$ are trying to make
a $100 deposit. They both execute the sequence:

1.  Move the current value of the account into a register
2.  Add 100 to the register
3.  Write the value of the register back into memory

If A executes its first statement and then $B$ executes its first
statement before $A$ finishes its third statement, one of the deposits
gets lost. There are dozens of programming paradigms to make sure
threads can synchronize properly to avoid these problems.

The code to define this "critical region" might use explicit acquisition
and release of locks:

```go
lock := acquire_a_lock()
register := read_balance()
register += 100
write_balance(register)
release_lock()
```

or the synchronization may be more implicit:

```go
funct critical_section {
  register := read_balance()
  register += 100
  write_balance(register)
}
```

### Temporal Constraints

If a system has temporal constraints, such as "this operation must
complete in 5 ms" it is called a **real-time system**. (Real-time
constraints are common in embedded systems.)

### Support for Concurrency

Many have argued whether a language should have direct support for
concurrency and distribution or whether such support should come from a
library.

**Library or OS-based advantages**

- Because different languages have different models of concurrency, language interfacing (multi-lingual development) may be easier.
- A specific language model may not fit well with a particular OS
- OS standards (e.g. POSIX) exist anyway, so perhaps portability might be likely.

**Language-Intrinsic advantages**

- Code likely to be more reliable and maintainable since constructs are high level.
- Lots of different operating systems exist, so code may be more portable.
- An embedded computer might not even have an operating system.

### Programming Paradigms and Patterns

What kind of patterns can you adopt that will help you to produce
reliable concurrent code? There are too many to mention here, but here
are some things to think about:

-   When writing async code, consider futures and promises instead of
    callbacks.
-   Look at reactive programming.
-   Look at some of the old work, like Hewitt's Actors.
-   Learn a process algebra, like Hoare's CSP or Milner's CCS, to gain
    a deeper understanding.
-   Look at what [Rich Hickey is doing with time in Clojure](http://www.infoq.com/presentations/Are-We-There-Yet-Rich-Hickey).

#### Reactive Programming

For example, in an imperative programming setting, `a := b + c`{.go} would mean
that a is being assigned the result of `b + c`{.go} in the instant the expression
is evaluated, and later, the values of b and c can be changed with no
effect on the value of a.

On the other hand, in reactive programming, the value of a is automatically
updated whenever the values of `b` or `c` change, without the program having
to re-execute the statement `a := b + c`{.go} to determine the presently assigned
value of `a`.

A popular Java library for reactive programming: <https://github.com/ReactiveX/RxJava>.

### Fault-Tolerance, Scale, Reliability

This is a big topic. You might like to read about [Erlang](http://www.erlang.org/).

### Correctness

Concurrent programs have to be correct *for all possible interleavings*.
Natrually this makes testing more complicated, but it can be done.

## Further Reading

### Topics

-   [Actors](http://en.wikipedia.org/wiki/Actor_model) [Software Transactional Memory](http://en.wikipedia.org/wiki/Software_transactional_memory)
-   [Atomic Variables (in Java)](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/atomic/package-summary.html) [Locks](http://en.wikipedia.org/wiki/Lock_%28computer_science%29)
-   [Milner' CCS](http://en.wikipedia.org/wiki/Calculus_of_communicating_systems)
-   [Hoare's CSP](http://en.wikipedia.org/wiki/Communicating_sequential_processes)
-   [Non-blocking](http://en.wikipedia.org/wiki/Non-blocking_algorithm)
-   [Thread-Local Storage](http://en.wikipedia.org/wiki/Thread-local_storage)

### Language Support

-   [Java](http://docs.oracle.com/javase/tutorial/essential/concurrency/)
-   Scala: [Actors (Akka)](), [Futures and Promises](http://docs.scala-lang.org/overviews/core/futures.html),
    [Parallel Collections](http://docs.scala-lang.org/overviews/parallel-collections/overview.html)
-   [Clojure](http://clojure.org/concurrent_programming)
-   [Julia](http://julia.readthedocs.org/en/latest/manual/parallel-computing/)
-   [Elm](http://elm-lang.org/learn/What-is-FRP.elm)
-   [Rust](https://doc.rust-lang.org/book/concurrency.html)
-   [Go](http://www.golang-book.com/books/intro/10)
-   [Erlang](http://www.erlang.org/)

**Exercise**: Rust claims to eliminate data races. How do they do that?
