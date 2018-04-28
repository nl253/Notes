# Data Modelling

**Phases of Database Design**:

  -------------------------------------------------------------------------------
  Conceptual                  Logical                     Physical
  --------------------------- --------------------------- -----------------------
  indenpendent of specific    indenpendent of specific    dependenet on specific
  DBMS indepdentend of type   DBMS dependent on type of   DBMS dependent on type
  of DBMS                     DBMS                        of DBMS
  -------------------------------------------------------------------------------

## Conceptual Data Modelling

-   examining properties of a system to determine
    -   which data objects are relevant
    -   how are they related to each other
    -   constraints that must be satisfied

**Entity Relational (ER) Diagrams**:

-   entities (E)
-   relationships (R)
-   attributes

**Instance Diagrams**:

Might be useful to figure out properties of the system.

**Steps to produce a conceptual data model**:

1.  Identify major entities and relationships between them
2.  Identify attributes and select primary keys
3.  Create an association entity for any relationships with attributes
4.  Determine they domain for each attribute

## Entity Relationships

**Degrees**:

-   unary
    -   relationship to itself
    -   aka recursive
    -   e.g. student is a friend of student
-   binary
    -   between two entities
    -   most common
-   tenary - between three entities
    -   e.g. staff meets student at location
-   n-ary
    -   between \\(n\\) entities
    -   e.g. staff meets student at location

### Muliplicities

-   main structural constraint on a relationship
-   the number (or range) of possible occurrences of an entity that may be
    related to a single occurrence of an associated entity in a relationship
-   dependent on the policies of the user or organisation and is referred to as
    a **business rule**
-   made up of two constraints -- participation and cardinality (P..C)
-   cardinality: maximum number of instances of entity in a relationship
-   participation: minimum number of instances of an entity in a relationship
    -   can be made **optional** if set to 0, otherwise it's **mandatory**

Based on cardinalities there are 3 types of binary relationships:

-   one to one
-   one to many
-   many to many
    -   to handle these extract an association entity so that many to many
        (*..*) becomes one to many to one (1..1 1..\* 1..\* 1..1)

### Strong vs Weak Entities

**Strong Entity**:

-   does not depend on the existence of another entity
-   uniquely identifiable by attribute i.e. primary key

**Weak Entity**:

-   depends (partially or wholly) on the existence of another entity
-   e.g. via participation constraint
-   e.g. when not uniquely identifiable by own attributes (primary key depends
    on other entity)
