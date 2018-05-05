# SQL

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

**NOTE**: in PostgreSQL `AUTO_INCREMENT` is achieved using the `SERIAL` type.

## Indexes

-   primary keys are indexed by default

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

Apply a function to all rows in a table.

**Common aggregate functions**:

-   `COUNT`
-   `SUM`
-   `AVG`
-   `MIN`
-   `MAX`

**All these functions**:

- Take a column as argument
    - `COUNT` may also take a row
- Ignore `NULL` values
- Return a single value

**The result may contain only**:

- the grouping attributes
- constants
- and aggregate function results

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

### With Clause

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
  >          Greater than
  <          Less than
  >=         Greater than or equal to
  <=         Less than or equal to
  <>         Not equal to
  \+         Add
  \-         Subtract
  \*         Multiply
  /          Divide
  %          Modulo
