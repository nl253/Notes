# Database Systems

## Relational Model

-   concepts and definitions
    -   constraints
    -   ~~attribute~~
    -   ~~entity integrity~~
    -   ~~referential integrity~~
    -   ~~relation~~
    -   ~~NULL~~
    -   ~~domain~~
    -   ~~primary, composite, candidate, foreign keys~~
    -   ~~relational vs sql vocabulary~~
    -   ~~tuple~~

## Modelling

### Conceptual

-   entity-relational diagrams
-   hierarchical relationships
-   how to create a conceptual model
    -   entities (relations)
    -   attributes
    -   relationships
-   constraints
    -   multiplicities
    -   attributes domains

### Logical

-   resolving issues
    -   fan-in
    -   fan-out
    -   many to many relationships
        -   association entities
        -   1 table (add field `isTableA BOOLEAN`, make other fields nullable)
        -   2 tables
-   ~~relations~~
    -   unary (recursive)
    -   binary
        -   ~~total/partial~~
    -   tenary
    -   n-ary

### Physical

<!--TODO physical modelling-->
## Relational Algebra

-   rename
-   translate from SQL to relational algebra
-   translate from relational algebra to SQL
-   set operations
    -   difference
    -   intersection
    -   union
    -   cartesian product
-   selection
-   projection
-   joins
    -   equijoin
    -   semijoin
    -   ~~natural join~~
-   ~~vocabulary (eg relation vs table, tuple vs row)~~

## SQL

-   nested queries
-   aggregate queries
    -   ~~`GROUP BY`~~
    -   ~~`HAVING`~~
-   creating a table
    -   adding constraints
        -   ~~foreign key~~
        -   ~~(composite) primary key~~
-   ~~casting~~
-   window clause
-   SQL functions

## ACID

-   ~~atomicity~~
-   ~~consistency~~
-   ~~isolation~~
-   ~~durability~~

## Concurrency

-   pessimistic approaches
    -   timestamps (advantages and disadvantages)
    -   locking (advantages and disadvantages)
-   optimistic approaches
-   what is and how to deal with:
    -   dirty read
    -   dirty write
    -   livelock
    -   ~~deadlock~~
-   ~~transactions~~
-   ~~concepts~~
    -   ~~serial schedule (advantages and disadvantages)~~
    -   ~~serialisable~~

## Physical Aspect

### Performance on the physical level

-   indexing
-   hashing
-   storage
-   B+-Trees
    -   growing
    -   deleting
    -   pros and cons
-   file structure
    -   heap
    -   sequential (ordered)
    -   hash
    -   clustered

### Reliability and Recovery

-   types of failures
    -   transaction
    -   system
    -   communication
    -   media
-   recovery
    -   backup
    -   logs
    -   transactions
    -   checkpoints
    -   buffer manager
    -   undo/redo algorithm

### Security

-   Anderson's rule
    -   security vs usability trade-off
-   types of threats
-   privilege abuse
    -   PostgreSQL access control (see below)
    -   issues
        -   excessive privileges
        -   downloading data locally
        -   ways to minimise
-   input injection (SQL injection)
    -   prevention
        -   escape all input
        -   stored procedures
        -   parametrised queries
    -   example
-   malware
-   storage media exposure
-   misconfigured databases
-   unmanaged sensitive data
    -   encryption
    -   storing passwords
        -   hashing -- e.g.: MD5, SHA2, SHA3
        -   salting
            <!--(TODO look into 'salting' and how it relates to hashing)-->
        -   prevent brute force attacks by limiting repeated queries
-   Denial Of Service (DOS)
    -   DDOS (distributed DOS)
-   Good practices:
    -   regular updates
    -   track users' activity
    -   configure DBMS properly
    -   correct protocols
    -   train (Database Administrators) DBAs
        -   awareness of security issues

#### Access Control in PostgreSQL

Actions:

-   UPDATE
-   INSERT
-   DELETE
-   EXECUTE
-   SELECT
-   ALL

To give access to do a type of action (in this case update):

``` {.sql}
GRANT  UPDATE ON ( <table_name> | ALL TABLES ) [IN SCHEMA <schema_name>] TO <user>;
REVOKE UPDATE ON ( <table_name> | ALL TABLES ) [IN SCHEMA <schema_name>] TO <user>;
```

<!--
vim:foldmethod=indent:
-->
