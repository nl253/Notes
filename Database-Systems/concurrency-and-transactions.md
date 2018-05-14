# Concurrency And Transactions

<!--TODO pessimistic and optimistic  approaches: advantages, disadvantages, examples-->
<!--TODO what is and how to deal with dirty read, write, live- and dead-lock -->
<!--TODO what is serial schedule and serializability (advantages and disadvantages)-->
See <https://www.wikiwand.com/en/Concurrency_control>.

A DBMS provides:

-   [transaction](#transaction) support
-   [concurrency control](#consistency-control) services
-   recovery services

To ensure that the database:

-   is reliable
-   remains in a consistent state

## Transactions

In the context of databases, a sequence of database operations that satisfies
the ACID properties, and thus can be perceived as a single logical operation on
the data, is called a transaction.

-   it bundles multiple steps into a single, all-or-nothing operation
    (Atomicity)
-   the intermediate states between the steps are not visible to other
    concurrent transactions (Isolation)
-   if some failure occurs that prevents the transaction from completing, then
    none of the steps affect the database at all.
-   Once a transaction is completed and acknowledged by the database system, it
    has indeed been permanently recorded and won't be lost even if a crash
    ensues shortly thereafter. (Durability)

**How ?**

> A transactional database guarantees that all the updates made by a
> transaction are logged in permanent storage (i.e., on disk) before the
> transaction is reported complete.
>
> -- postgres documentation

### Savepoints

It's possible to control the statements in a transaction in a more granular
fashion through the use of savepoints. Savepoints allow you to selectively
discard parts of the transaction, while committing the rest.

-   define a savepoint with `SAVEPOINT`
-   roll back to the savepoint with `ROLLBACK TO`

`ROLLBACK TO` is the only way to regain control of a transaction block that was
put in aborted state by the system due to an error, short of rolling it back
completely and starting again.

## ACID

-   [atomicity](#atomicity)
-   [consistency](#consistency)
-   [isolation](#isolation)
-   [durability](#durability)

ACID properties are enforced by different components of the DBMS.

  Property      Responsibility
  ------------- -----------------------------------------------------------------------
  atomicity     Recovery Mechanisms
  consistency   DBMS and the transaction implemented to enforce integrity constraints
  isolation     concurrency control mechanisms
  durability    recovery mechanisms

### Atomicity

A [transaction](#transaction) has one of the two outcomes:

1.  **success** -- a transaction is committed and database reaches a new
    consistent state
2.  **failure** -- transaction is aborted / rolled back and the database must
    be restored to it's consistent state

-   committed transactions cannot be aborted
-   aborted transactions that are rolled back can be restarted later

### Consistency

Any data written to the database must be valid according to all defined rules,
including constraints, cascades, triggers, and any combination thereof.

## Concurrency Control

Shared databases must allow concurrent access to data items which means
interleaving of different transactions.

Operations may conflict only if:

a)  they are performed by different transactions on the same granule or data
    item
b)  of the them is a write

This means that **two read operations are always safe**!

**Concurrency control** aims to schedule transactions in such a way as to
prevent conflict and interference between them.

In a serialisable schedule, the order of Reads/Writes is important if one
transaction writes a data item and another reads/writes the same data item.

The order is not important if two transactions only read a data item, or two
transactions either read or write separate data items.

### Optimistic Methods

**Assumptions**:

-   conflict is rare and only check at commit
-   they don't check during execution

Transactions are applied to local copies of data items and are checked for
serializability violation *before* commit.

1.  Read -- updates applied to local values
2.  Validation -- check for serializability
3.  Write -- if 2 succeeded, updates are applied to the database, otherwise
    transaction is restarted

### Pessimistic Methods

Pessimistic approaches delay transactions to check for conflict with other
transactions.

#### Locking

-   Most widely used approach to ensure serializability
-   locks are used to grant a single database transaction access to some part
    of it's content whilst blocking all other transactions from accessing the
    same data until the lock is released
-   in order to access a data item a transaction must first acquire the lock --
    when it's finished the lock is released

**Types of locks**:

-   Read -- if A has a read lock B cannot be given a write block (but can be
    given a read lock)
-   Write -- exclusive (i.e. if A has a write lock then nobody else can be
    given read or write locks for that granule)

**Lock manager** maintains a list of locks.

The Lock Manager is responsible for deciding the appropriate lock type (shared,
exclusive, update, and so on) and the appropriate granularity of locks (row,
page, table, and so on) according to the type of operation being performed and
the amount of data being affected.

A transaction can proceed only after the request is granted.

**Two-phase locking** (2PL) is widely used as serializability is guaranteed,
but at a cost of limiting the level of concurrency that can be achieved.

**Growing phase**

-   Locks are acquired incrementally as each data item is accessed
-   Cannot release any locks

**Shrinking phase**

-   All locks are released on commit or rollback
-   Cannot acquire any new locks

**Issues with 2PL**:

-   **Deadlock** -- A circular waiting condition, caused by two or more
    transactions each waiting for another to release its lock
-   **Livelock**
    -   Transactions waits indefinitely for a lock despite being rolled back
        repeatedly.
    -   Two or more transactions are changing the data locked, but never get to
        a state where 27 one can complete.

**Solutions**:

-   **Deadlock prevention** (expensive)
-   **Deadlock detection** followed by abort/recovery (manageable)
-   **Timeout**: abort uncompleted transactions after a timeout (easy)


#### Timestamping

Operations are run in chronological order of the timestamps.

Advantages:

-   no deadlocks
-   concurrency -- no transaction has to wait i.e. the can run in parallel / be
    interleaved

Good if:

-   the database is mostly read-only
-   transactions on the very same data items are unlikely

Bad if:

-   conflicts occur frequently (because transactions are often rolled back)

## Issues with Concurrent Transactions

**Main transaction issues**:

1.  system failures
    -   hardware (for example: power cut)
    -   software
    -   network
2.  concurrent transaction problems

A DBMS must allow multiple users to access the database at the same time.

If we allow transactions to run concurrently we get better performance -- we
utilise multi-core CPUs better

Problems arise when that isn't properly controlled.

For example:

-   lost update (**dirty read**)
-   reading uncommitted data (**dirty read**)
-   non-repeatable read (**inconsistent analysis**)

### Non-repeatable read (inconsistent analysis)

It can occur if one transaction is performing an aggregate operation while another
is updating the data.

### Dirty Write

An apparently successful transaction is lost because more than one clients are
operating on the same data item.

**Solution to dirty write**: force B to wait until A is finished.

### Dirty Read

Transaction is aborted but it's intermediate results are visible to other
transactions.

**Solution to dirty read**: isolate transactions -- make sure that A doesn't
see the changes to the table until B has committed.

## Glossary

Transaction

:   an [action](#action) or series of actions carried out by a user or
    application which accesses or updates content of a database

Action

:   Examples of actions:

    -   read
    -   write
    -   delete

    Actions can be carried out on different levels of granularity:

    -   table
    -   row
    -   data item

Durability

:   once a transaction is committed it's effects must be permanent

Atomicity

:   a transaction is either completed or aborted (all or nothing)

Serial Schedule
:   A schedule where operations are executed sequentially i.e. each transaction
    commits before the next one is allowed to run.

    Advantages:

    -   no concurrent issues

    Disadvantages:

    -   poor throughput and response time

Schedule
:   sequence of operations by a set of concurrent transactions

Serialisable
:   guaranteed to produce the same effect as running them one at a time in some
    order

Isolation

:   -   transactions must be independent of each other
    -   partial effects or incomplete transactions should not be visible to
        other transactions

Consistency

:   database must be in a consistent state before and after a transaction even
    if consistency was violated during the transaction
