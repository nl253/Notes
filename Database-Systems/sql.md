# SQL

<!--TODO SQL functions-->
<!--TODO window clause in SQL-->
Most relational databases use the **SQL data definition and query language**.

## Creating Tables

``` {.sql}
CREATE [TEMP] TABLE <table_name> (
    <attr1> <type> [<CONTRAINT>, ...],
    [<attr1> <type> [<CONTRAINT>, ...], ...]

    [PRIMARY KEY (<attr>),]
    [FOREIGN KEY (<attr>)]
);
```

Where:

    CONSTRAINT
        ::= DEFAULT <default_val>
         |  UNIQUE
         |  CHECK <predicate>
         |  NOT NULL
         |  PRIMARY KEY
         |  REFERENCES <table_name> (<column_name>) [ON ( UPDATE | DELETE ) ( CASCADE | SET NULL | SET DEFAULT ) ]

**NOTE**: in PostgreSQL `AUTO_INCREMENT` is achieved using the `SERIAL` type.

**NOTE**: the `ON UPDATE` and `ON DELETE` clauses enforce referential
integrity. Without this deletion of a record in the 'parent' table branch is
not allowed if there exists any referenced records in the 'child' table
employee.

## Indexes

-   primary keys are indexed by default in Postgres

``` {.sql}
CREATE INDEX <index_name> 
ON <table_name> (<col1> [, <col2>, ...]);
```

## Selecting

``` {.sql}
SELECT ( <attr1> [, <attr2>, ...] | * ) 
FROM <table_name>
[WHERE <condition>]
[ORDER BY <attr>];
```

### Aggregate Queries

Apply a function to groups of rows in a table returning a single value.

**Common aggregate functions**:

-   `COUNT`
-   `SUM`
-   `AVG`
-   `MIN`
-   `MAX`

**All these functions**:

-   Take a column as argument
    -   `COUNT` may also take a row
-   Ignore `NULL` values
-   Return a single value

**The result may contain only**:

-   the grouping attributes
-   constants
-   and aggregate function results

``` {.sql}
SELECT <attr1> [<attr2>, ...]
FROM <table_name>
[WHERE <condition>]
GROUP BY <attr1> [, <attr2>, ...]
[HAVING <condition>]
[ORDER BY <attr>];
```

**NOTE**: `HAVING` is a `WHERE` for aggregates.

### Nested Queries

```sql
SELECT * FROM ( 
    SELECT bid AS branch, (SELECT COUNT(id) FROM employee WHERE branch = bid) AS num
    FROM branch
) AS tmp;
```

Completely unmanageable.

#### With Clause

Nested queries can be simplified using the `WITH` clause. It's function is to
temporarily assign an "alias name" to a query.

```sql
WITH branch_avg AS (
    SELECT branch, AVG(AGE(dob)) AS avgAge
    FROM employee
    GROUP BY branch
);

SELECT name, AGE(dob), a.branch, avgAge
FROM employee a, branch_avg b
WHERE a.branch = b.branch;
```

## Inserting

``` {.sql}
INSERT INTO <table_name> [(<attr1>[, <attr2>, ...])] 
VALUES (<val1> [, <val2>, ...])
[WHERE <condition>];
```

## Updating

``` {.sql}
UPDATE <table_name>
SET <attr1> = <val1> [, <attr2> = <val2>, ...]
[WHERE <condition>];
```

## Operators

  Operator   Description
  ---------- --------------------------------------------------------------
  ALL        TRUE if all of the sub-query values meet the condition
  AND        TRUE if all the conditions separated by AND is TRUE
  ANY        TRUE if any of the sub-query values meet the condition
  BETWEEN    TRUE if the operand is within the range of comparisons
  EXISTS     TRUE if the sub-query returns one or more records
  IN         TRUE if the operand is equal to one of a list of expressions
  LIKE       TRUE if the operand matches a pattern
  NOT        Displays a record if the condition(s) is NOT TRUE
  OR         TRUE if any of the conditions separated by OR is TRUE
  SOME       TRUE if any of the sub-query values meet the condition
  =          Equal to
  \>         Greater than
  \\\<       Less than
  \>=        Greater than or equal to
  \\\<=      Less than or equal to
  \\\<\>     Not equal to
  \+         Add
  \-         Subtract
  \*         Multiply
  /          Divide
  \\%        Modulo

## Functions

``` {.sql}
CREATE FUNCTION active_subscribers() RETURNS BIGINT AS
$$
    -- variable for the following BEGIN ... END block
    DECLARE subscribers INTEGER;

    BEGIN

        -- SELECT must always be used with INTO
        SELECT COUNT(user_id) INTO subscribers FROM users WHERE subscribed;

        -- function result
        RETURN subscribers;

        -- return NULL if table "users" does not exist

        EXCEPTION

            WHEN undefined_table THEN RETURN NULL;

    END;

$$
LANGUAGE plpgsql;

```

## Window Clauses

<!--TODO Window Clauses-->

## Triggers

A trigger is a specification that the database should automatically execute a
particular function whenever a certain type of operation is performed. Triggers
can be attached to both tables and views.

On tables, triggers can be defined to execute either before or after any:

-   `INSERT`
-   `UPDATE`
-   `DELETE`

operation, either **once per modified row, or once per SQL statement**.

Triggers are also classified according to whether they fire:

-   `BEFORE`
-   `AFTER`
-   `INSTEAD OF`

the operation.

Once a suitable trigger function has been created, the trigger is established
with `CREATE TRIGGER`. A trigger definition can specify a Boolean `WHEN`
condition, which will be tested to see whether the trigger should be fired.

Trigger functions can be written in most of the available procedural languages,
including (for PostgreSQL): PL/pgSQL, PL/Tcl, PL/Perl and PL/Python.

### Cascading Triggers

If a trigger function executes SQL commands then these commands might fire
triggers again. This is known as cascading triggers. It is possible for
cascades to cause a recursive invocation of the same trigger; for example, an
INSERT trigger might execute a command that inserts an additional row into the
same table, causing the INSERT trigger to be fired again. It is the trigger
programmer's responsibility to avoid infinite recursion in such scenarios.

### Statement-Level vs Row-Level Trigger

PostgreSQL offers both per-row triggers and per-statement triggers.

With a **per-row trigger (row-level)**, the trigger function is invoked once for each row
that is affected by the statement that fired the trigger.

In contrast, a **per-statement (statement-level) trigger** is invoked only once when an
appropriate statement is executed, regardless of the number of rows affected by
that statement.

Typically, row-level `BEFORE` triggers are used for checking or modifying the
data that will be inserted or updated.

For example, a BEFORE trigger might be used to insert the current time into a
timestamp column, or to check that two elements of the row are consistent.

Row-level `AFTER` triggers are most sensibly used to propagate the updates to
other tables, or make consistency checks against other tables.

<!--
vim:foldmarker=```\ ,```:foldmethod=marker:
-->
