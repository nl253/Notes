# Intro

## Module Summary

- process
- thread
- atomic execution
- program

## Concurrency

- processes vs threads (sometimes they will be distinguished)
- parallelism, simultaneous execution i.e. doing things at the same
  time
- more generalised form of parallelism -- includes time slicing as a
  form of virtual parallelism
- a way of structuring execution into independent pieces in order to
  improve performance
- non-sequential execution, parallelism satisfies the definition of
  concurrency many algorithms can be broken down into independent
  parts (Mergesort, Quicksort)

## Challenges of Concurrency

- the downside is that you have to coordinate those pieces
- concurrent processes can interfere / interact
- concurrent execution can lead to a lot of possible interleavings
- race conditions (as a result of different interleavings there are
  different outcomes of execution)
- sometimes you might have to introduce explicit coordination

## Message Passing vs Shared Memory

- semaphores can be too low level for distributed systems
- message passing is supposed to be a solution to the issues with
  shared memory in distributed systems
- Erlang is based on message passing (more specifically it's based on
  the actor model) within message passing there is also channel based

## TODO

- [ ] take a look at the schedule & readings on the slides for first lecture
- [ ] <https://www.wikiwand.com/en/Calculus_of_communicating_systems>
- [ ] <https://www.wikiwand.com/en/%CE%A0-calculus>
