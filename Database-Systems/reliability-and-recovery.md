# Reliability And Recovery

See <https://www.tutorialspoint.com/dbms/dbms_data_recovery.htm>.

## Intro

**Examples of failures**:

-   transaction
-   system (DBMS software or OS)
-   communication (over network)
-   media failure (hardware such as HDD)

**Reliability is achieved by**:

a)  minimising:
    - side effects
    - failures
b)  maximising ability to recover i.e. recovery mechanisms

## Primary Reliablity (design and implementation)

-   avoid single point of failure
-   hardware replication (e.g. RAID)

## Secondary Reliablity (operating procedures)

-   rigorously implemented operating procedures
-   access controls
    - restrict access if not needed -- principle of least privilege
-   reliable (i.e. tested) backup and recovery mechanisms (logging-based recovery, backups)

## Database Recovery

-   should restore the database to a correct state
-   should ensure that transactions are either completely and successfully
    executed or not at all (**Atomicity**)
-   should ensure that updates committed in the past aren't lost
    (**Durability**)

DBMS should provide facilities to aid recovery:

-   Backup
    -   periodical backup of the database
-   Logging
    -   log transactions
-   Checkpoints
    -   store database updates on a secondary storage
-   Recovery Manager
    -   restore the database to a consistent state if a failure occurs

## Storage

For performance reasons a typical DBMS installation will require 3 disc units:

-   1 for logs
-   1 for the OS and DBMS software
-   1 for live data

## DBMS Log File

Contains information about current states of transactions and database updates.

-   transaction records
    -   transaction ID
    -   type of log records
        -   start
        -   commit
        -   abort
    -   pages affected by insert update
        -   data page ID
        -   before image (data page before)
        -   after image (data page after)
-   checkpoint records
    -   where all changes made to the database are on the disc

### Log-Based Recovery

**NOTE**: Here logs aren't the human-readable logs used by programmers for debugging
purposes, although DBMS also provide that.

**NOTE**: there are actually different **types of logs** that DBMS keeps track
of. E.g.:

-   commit log (records decisions to commit a transaction)
-   update log (records changes to database)
-   abort log (records decisions to abort and hence roll back a transaction)

**NOTE**: "Log" is also referred to as: transaction log, transaction journal
and database log.

**Types of log recovery**:

-   Normal recovery (after normal shutdown)
    -   start from last checkpoint (written at shutdown)
-   Warm recovery (after system failure)
    -   revert to last checkpoint in logs
    -   remove effects of uncommitted transactions
-   Cold recovery (after media failure)
    -   restore from backup or dump
    -   apply log records to reach latest consistent state

## Checkpoints

Checkpoint declares a point before which the DBMS was in consistent state, and
all the transactions were committed.

Checkpoint is a mechanism where all the previous logs are removed from the
system and stored permanently in a storage disk.
