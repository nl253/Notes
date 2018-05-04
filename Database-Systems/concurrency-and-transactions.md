# Concurrency And Transactions

A DBMS provides:

-   [transaction](#transaction) support
-   [concurrency control](#consistency-control) services
-   recovery services

To ensure that the database:

-   is reliable
-   remains in a consistent state

## Transactions

A [transaction](#transaction) has one of the two outcomes:

1.  **success** -- a transaction is committed and database reaches a new
    consistent state
2.  **failure** -- transaction is aborted / rolled back and the database must
    be restored to it's consistent state

-   committed transactions cannot be aborted
-   aborted transactions that are rolled back can be restarted later

## ACID

-   [atomicity](#atomicity)
-   [consistency](#consistency)
-   [isolation](#isolation)
-   [durability](#durability)

ACID properties are encored by different components of the DBMS.

  -------------------------------------------------------------------------------------
  Property      Responsibility
  ------------- -----------------------------------------------------------------------
  atomicity     Recovery Mechanisms
  consistency   DBMS and the transaction implemented to enforce integrity constraints
  isolation     concurrency control mechanisms
  durability    recovery mechanisms
  -------------------------------------------------------------------------------------

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

### Dirty Write

An apparently successful transaction is lost because more than one clients are
operating on the same data item.

**Solution to dirty write**: force B to wait until A is finished.

### Dirty Read

Transaction is aborted but it's intermediate results are visible to other
transactions.

**Solution to dirty read**: isolate transactions -- make sure that A doesn't
see the changes to the table until B has committed.

## Concurrency Control

Shared databases must allow concurrent access to data items which means
interleaving of different transactions.

Operations may conflict only if:

a)  they are performed by different transactions on the same granule or data
    item
b)  of the them is a write

This means that two read operations are always safe!

**Concurrency control** aims to schedule transactions in such a way as to
prevent conflict and interference between them.

There are the pessimistic and optimistic approaches.

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

A transaction can proceed only after the request is granted.

#### Timestamping

Operations are run in chronological order of the timestamps.

Advantages:

-   no deadlocks
-   concurrency -- no transaction has to wait i.e. the can run in parallel / be
    interleaved

Good if:

-   the database is mostly read-only i.e. there is a slim chance of conflicts
    occurring
-   transactions on the very same data items are unlikely

Bad if:

-   conflicts occur frequently (because transactions are often rolled back)

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

:   two transactions are serialisable if when their individual actions are
    interleaved, they produce the same results as a serial schedule

Isolation

:   -   transactions must be independent of each other
    -   partial effects or incomplete transactions should not be visible to
        other transactions

Consistency

:   database must be in a consistent state before and after a transaction even
    if consistency was violated during the transaction
