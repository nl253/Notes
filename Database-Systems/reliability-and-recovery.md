# Reliability And Recovery

<!--TODO types of failures-->
<!--TODO database recovery-->
<!--TODO rolling back and concurrency-->
<!--TODO checkpoints in log recovery, relation to concurrency-->
<!--buffer manager, role, explanation-->
<!--TODO undo/redo algorithm-->

See <https://www.tutorialspoint.com/dbms/dbms_data_recovery.htm>.

## Intro

**Examples of failures**:

-   transaction
-   system (DBMS software or OS)
-   communication (over network)
-   media failure (hardware such as HDD)

**Reliability is achieved by**:

a)  minimising:
    -   side effects
    -   failures
b)  maximising ability to recover i.e. recovery mechanisms

## Primary Reliablity (design and implementation)

-   avoid single point of failure
-   hardware replication (e.g. RAID)

## Secondary Reliablity (operating procedures)

-   rigorously implemented operating procedures
-   access controls
    -   restrict access if not needed -- principle of least privilege
-   reliable (i.e. tested) backup and recovery mechanisms (logging-based
    recovery, backups)

## Database Recovery

-   should restore the database to a correct state
-   should ensure that transactions are either completely and successfully
    executed or not at all (**Atomicity**)
-   should ensure that updates committed in the past aren't lost
    (**Durability**)

**DBMS should provide facilities to aid recovery**:

a)  Backup
    -   periodical backup of the database
    -   There are three fundamentally different approaches to backing up
        PostgreSQL data:
        -   SQL dump
        -   File system level backup
        -   Continuous archiving
b)  Logging
    -   log transactions
c)  Checkpoints
    -   store database updates on a secondary storage
d)  Recovery Manager <!--TODO what's recovery manager-->
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
        -   etc.
    -   pages affected by insert update
        -   data page ID
        -   before image (data page before)
        -   after image (data page after)
-   checkpoint records
    -   where all changes made to the database are on the disc

### Log-Based Recovery

**NOTE**: Here logs aren't the human-readable logs used by programmers for
debugging purposes, although DBMS also provide that.

**NOTE**: there are actually different **types of logs** that DBMS keeps track
of. E.g.:

-   commit log (records decisions to commit a transaction)
-   update log (records changes to database)
-   abort log (records decisions to abort and hence roll back a transaction)

**NOTE**: "Log" is also referred to as: transaction log, transaction journal
and database log.

**Types of log recovery**:

<!--TODO normal vs cold vs warm recovery.-->

-   Normal recovery (after normal shutdown)
    -   start from last checkpoint (written at shutdown)
-   Warm recovery (after system failure)
    -   revert to last checkpoint in logs
    -   remove effects of uncommitted transactions
-   Cold recovery (after media failure)
    -   restore from backup or dump
    -   apply log records to reach latest consistent state

## Checkpoints

<!--TODO checkpoints and concurrency-->

Checkpoint declares a point before which the DBMS was in consistent state, and
all the transactions were committed.

Checkpoint is a mechanism where all the previous logs are removed from the
system and stored permanently in a storage disk.

## Maintenance

PostgreSQL, like any database software, requires that certain tasks be
performed regularly to achieve optimum performance. They are required, but they
are repetitive in nature and can easily be automated using standard tools such
as cron scripts or Windows' Task Scheduler. It is the database administrator's
responsibility to set up appropriate scripts, and to check that they execute
successfully.

One obvious maintenance task is the **creation of backup copies** of the data on a
regular schedule. Without a recent backup, you have no chance of recovery after
a catastrophe (disk failure, fire, mistakenly dropping a critical table, etc.).

### Reindexing 

In some situations it is worthwhile to rebuild indexes periodically with the
`REINDEX` command or a series of individual rebuilding steps.

B-tree index pages that have become completely empty are reclaimed for re-use.
However, there is still a possibility of inefficient use of space: if all but a
few index keys on a page have been deleted, the page remains allocated.
Therefore, a usage pattern in which most, but not all, keys in each range are
eventually deleted will see poor use of space. For such usage patterns,
periodic re-indexing is recommended.

The potential for bloat in non-B-tree indexes has not been well researched. It
is a good idea to periodically monitor the index's physical size when using any
non-B-tree index type.

Also, for B-tree indexes, a freshly-constructed index is slightly faster to
access than one that has been updated many times because logically adjacent
pages are usually also physically adjacent in a newly built index. (This
consideration does not apply to non-B-tree indexes.) It might be worthwhile to
re-index periodically just to improve access speed.

`REINDEX` can be used safely and easily in all cases. But since the command
requires an exclusive table lock, it is often preferable to execute an index
rebuild with a sequence of creation and replacement steps. Index types that
support CREATE INDEX with the CONCURRENTLY option can instead be recreated that
way. If that is successful and the resulting index is valid, the original index
can then be replaced by the newly built one using a combination of ALTER INDEX
and DROP INDEX. When an index is used to enforce uniqueness or other
constraints, ALTER TABLE might be necessary to swap the existing constraint
with one enforced by the new index. Review this alternate multi-step rebuild
approach carefully before using it as there are limitations on which indexes
can be re-indexed this way, and errors must be handled.

### Logs

It is a good idea to save the database server's log output somewhere, rather
than just discarding it via `/dev/null`. The log output is invaluable when
diagnosing problems. However, the log output tends to be voluminous (especially
at higher debug levels) so you won't want to save it indefinitely. You need to
rotate the log files so that new log files are started and old ones removed
after a reasonable period of time.

If you simply direct the stderr of postgres into a file, you will have log
output, but the only way to truncate the log file is to stop and restart the
server. This might be acceptable if you are using PostgreSQL in a development
environment, but few production servers would find this behaviour acceptable.

A better approach is to send the server's stderr output to some type of log
rotation program. There is a built-in log rotation facility, which you can use
by setting the configuration parameter logging\_collector to true in
postgresql.conf. The control parameters for this program are described in
Section 18.8.1. You can also use this approach to capture the log data in
machine readable CSV (comma-separated values) format.

Alternatively, you might prefer to use an external log rotation program if you
have one that you are already using with other server software. For example,
the rotatelogs tool included in the Apache distribution can be used with
PostgreSQL. To do this, just pipe the server's stderr output to the desired
program. If you start the server with pg\_ctl, then stderr is already
redirected to stdout, so you just need a pipe command, for example:

pg\_ctl start \| rotatelogs /var/log/pgsql\_log 86400 Another production-grade
approach to managing log output is to send it to syslog and let syslog deal
with file rotation. To do this, set the configuration parameter
log\_destination to syslog (to log to syslog only) in postgresql.conf. Then you
can send a SIGHUP signal to the syslog daemon whenever you want to force it to
start writing a new log file. If you want to automate log rotation, the
logrotate program can be configured to work with log files from syslog.

On many systems, however, syslog is not very reliable, particularly with large
log messages; it might truncate or drop messages just when you need them the
most. Also, on Linux, syslog will flush each message to disk, yielding poor
performance. (You can use a "-" at the start of the file name in the syslog
configuration file to disable syncing.)

Note that all the solutions described above take care of starting new log files
at configurable intervals, but they do not handle deletion of old,
no-longer-useful log files. You will probably want to set up a batch job to
periodically delete old log files. Another possibility is to configure the
rotation program so that old log files are overwritten cyclically.
