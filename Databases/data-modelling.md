# Data Modelling

As of 2012 seven main databases dominate the commercial marketplace:

-   DB2
-   Informix
-   MySQL
-   Oracle
-   Postgres
-   SQL Server
-   Sybase

Other RDBMS systems tend either to be legacy databases or used within
academia such as universities or further education colleges.

Part of the process of database development. It takes place after
initial problem analysis has been conducted. We need to know:

-   the problem domain i.e. business rules and facts about the company
    -   what kind of objects are relevant to the user
    -   relationships between them
-   requirements
-   constraints

**Data Modelling** is a process of mapping the "real-world" facts into a
generalised conceptual data model.

**Phases of Database Design**:

  Conceptual                 Logical                     Physical
  -------------------------- --------------------------- ------------------------
  Independent of specific    Independent of specific     Dependent on specific
  DBMS independent of type   DBMS dependent on type of   DBMS dependent on type
  of DBMS.                   DBMS.                       of DBMS.

## Conceptual Data Modelling

Conceptual data model describes the semantics of an organization without
reference to technology.

-   first step in data modelling, typically this is a first-cut model,
    with insufficient detail to build an actual database, describes the
    structure of the whole database for a group of users
-   high level description of data and their relationships
-   independent of DBMS types and all physical considerations (a
    conceptual data model could be done for: a relational database,
    object-oriented database, graph database etc.)
-   examining properties of a system to determine
    -   which data objects are relevant
    -   how are they related to each other
    -   constraints that must be satisfied
-   uses non-technical names, so that executives and managers at all
    levels can understand the data basis of Architectural Description
-   May not be normalized
-   it describes the things of significance to an organization
    -   entity classes, about which it is inclined to collect
        information
    -   characteristics of (attributes) entities
    -   associations between pairs of those entities of significance
        (relationships)
-   it may exist on various levels of abstraction
-   the model does allow for what is called inheritance in object
    oriented terms (not the case in logical model for relational
    databases)
    -   super-type/sub-type relationships may be exclusive or not

### ER Modelling

Entity Relational (ER) modelling is a process of identifying:

-   data (**entities** and their **attributes**)
-   **relationships** between them
-   **constraints**
-   conceptual
    -   at the conceptual level we aren't concerned with primary keys
        *unless* we are given data which includes them e.g. `studentID`,
        then it becomes part of the conceptual data model
        -   mark primary keys IF PRESENT like so: `studentID {PK}`
    -   multiplicities
    -   attributes
    -   entities
    -   relationships to other entities (but not foreign keys since this
        is a relational database concept that)
        -   relationships *might* have attributes
-   logical
    -   at the logical level we aren't concerned with data types
    -   remove generalisation and specialisation
    -   resolve relationships with attributes into association entities

**NOTE**: various notations are used for ER modelling.

**Entity Relational (ER) Diagrams**:

-   entities (E)
-   relationships (R)
-   attributes

**Instance Diagrams**:

Might be useful to figure out properties of relationships.

**Steps to produce a conceptual data model**:

1.  Identify major entities and relationships between them
2.  Identify attributes and select primary keys
3.  Create an association entity for any relationships with attributes
4.  Determine they domain for each attribute

Conceptual model identifies candidate keys and selects one of them as
the primary key.

### Abstraction

Represents a very high approach to data modelling.

Types of abstractions:

-   **classification** (grouping concepts)
    -   find a common property in a set of objects and group them into
        an entity
    -   "**is-instance-of**" relationship
-   **aggregation** (composing)
    -   define a new entity from a set of other entities
    -   each component is associated with the aggregation by the
        "**is-part-of**" or "**has-a**" relationship
-   **generalisation** and **specialisation** (hierarchical,
    "inheritance-like" relationships)
    -   group or classify objects with common properties to a class or
        entity
    -   individual objects are associated with its class by the
        "**is-a**" relationship
    -   superclass can be related to exactly one subclass (OR --
        exclusive constraint) or it might be related to more than one
        (AND -- overlapping constraint)
    -   when the superclass must be related to some subclass then we
        have a **mandatory participation**, if not then we have an
        **optional participation**
    -   cannot be modelled directly in relational databases (but can in
        Object-Relational Database Management Systems)

## Logical Database Design

Logical data models should be based on the structures identified in a
preceding conceptual data model, since this describes the semantics of
the information context, which the logical model should also reflect.

-   Process of refining an mapping the conceptual data model to a
    logical data model.
-   Local database design should produce a description of database which
    takes into account the type of DBMS.
-   Independent of a *particular* type of DBMS (e.g. PostgreSQL or
    MySQL) and other physical considerations.
-   Is normalized to fourth normal form (4NF)
-   Uses business names for entities & attributes
-   Includes entities (tables), attributes (columns/fields) and
    relationships (keys)

### Resolving Issues

<!--TODO fan-in and fan-out-->
<!--TODO finish off data-modelling PowerPoint 3-->
<!--TODO dealing with associations with attributes-->
<!--TODO resolving many-to-many associations-->
-   fan-in
-   fan-out
-   many to many relationships
    -   association entities
    -   1 table (add field `isTableA BOOLEAN`, make other fields
        nullable)
    -   2 tables

## Physical Database Design

The physical design of a database involves deep use of particular
database management technology i.e. it's specific to a DBMS. It's more
low-level description of the database implementation typically derived
from a logical data model.

It's concerned with **implementation details** such as:

-   access constraints
-   access methods
-   base relations
-   columns
-   data types
-   database triggers
-   domains
-   file organisations
-   indices (for fast data access)
-   integrity constraints
-   keys
-   primary keys
-   security measures
-   storage structures
-   stored procedures
-   tables
-   validation rules

**Physical database design involves**:

-   analysis of transactions

It's what the database will be like:

-   uses more defined and less generic specific names for tables and
    columns, such as abbreviated column names, limited by DBMS and any
    company defined standards

To correctly design a database system information is needed:

-   frequency of access
-   volume
-   type of access

Any additional information such as: 'what queries are most commonly run'
allows database designers to make optimisations.

In addition, it *may be* de-normalized to meet performance requirements
based on the nature of the database.

For example: SQL Server runs only on Microsoft Windows
operating-systems, while Oracle and MySQL can run on Solaris, Linux and
other UNIX-based operating-systems as well as on Windows.

This means that the disk requirements, security requirements and many
other aspects of a physical data model will be influenced by the RDBMS
that a database administrator (or an organization) chooses to use.

**Different DBMS may have different**:

-   data types
-   syntax for non-standard SQL (e.g. window clause, indexes)
-   etc.

### Hardware for a Database System

  Component   Description
  ----------- -------------------------------------------------------------------------------------------------
  Memory      Large enough to minimise the need for swapping
  CPU         Fast enough to handle with many simultaneous queries, ideally multi core
  Disk IO     Major bottleneck
  Storage     OS, main database files, index files and log files should be
  distributed across multiple disks
  Network     If network traffic is excessive, collisions can occur and affect database performance

### Aims of Physical Database Design

1.  Transactions should be as fast as possible.
2.  Response time should be "acceptably fast".
3.  Disk storage should be minimised.
4.  Database must be able to store large volumes of data.
5.  Retrieval must be efficient.
6.  Storage must be reliable.

**NOTE**: Physical database design *is not* static, it must respond to
performance monitoring and special requirements.

### Storage

Primarily disks and tapes are used for long-term storage of database
records. With tapes being used for backup and disks for current database
records.

**Main memory** i.e. RAM has fast access time but:

-   it's expensive relative to how much data needs to be stored
-   it's volatile i.e. it loses data when powered off

**Disks**:

-   cheap
-   slower than RAM
-   faster than tapes

**Tapes**:

-   very cheap
-   not suitable for current records
-   slow, allow for sequential access (**not random access** so $O(n)$)
-   most commonly used for backup

### Indices

A database index is a data structure that improves the speed of data
retrieval operations on a database table at the cost of additional
writes (reorganising when inserting or deleting) and storage space to
maintain the index data structure.

Indexes can be created using one or more columns of a database table,
providing the basis for both rapid random lookups and efficient access
of ordered records.

An index is **a copy of selected columns of data from a table that can
be searched very efficiently** that also includes a **low-level disk
block address or direct link to the complete row of data** it was copied
from.

Most relational databases structure indices as B-trees.

The worst case height of a B-tree is $O(log\\ n)$, and since a
search is dependent on height, B-tree lookups run in something like (on
average) $O(log\\ n)$

To get $O(1)$, primary index itself would need to be hash-based
(which is typically not the default choice)

If there is no index, then it is $O(n)$. But linear search is
inefficient for large databases.

-   **Indexes are often created automatically on primary keys.**
-   `FOREIGN KEY`{.sql} and `UNIQUE`{.sql} columns are often indexed to speed up constraint checking.
-   Indices speed up the join operation (given a foreign key, it is much
    easier to find the corresponding primary key).
-   Consider indexes whenever you have a column that is often in a
    `WHERE`{.sql} clause
    -   This will speed up the access of the required rows

**NOTE**: ISO SQL Standard does not cover physical aspects so some DBMSs
*might* have syntax or give options to create, say, indices using data
structures that other DBMSs won't provide. That said, most DMBSs provide
`CREATE INDEX` syntax.

``` {.sql}
CREATE INDEX <index_name> ON <table_name> (<column_name>)
[ USING ( HASH | RTREE | BTREE (default)) ];

-- to delete index
DROP INDEX <index_name> ON <table_name> (<column_name>);
```

#### B^+^-Trees

-   variant of *balanced tree*
-   dynamic (expands and shrinks as needed)
-   multi-level
-   **full index**
    -   every record is addressed by the index
    -   data file does not need to be ordered
-   $O(log\ n)$lookup time complexity
-   maintaining the data structure has some cost associated with it --
    deletion and addition entail some internal reorganisation

**Degree** or order of a tree refers to the number of child nodes
allowed per parent node.

##### Structure of B^+^-Trees

-   Each node contains **2 values and 3 references** to further nodes.
-   Each leaf contains a reference ko
-   Depth of a tree refers to the number of levels.

<!-- -->
    +----------------------------------+
    | ref1 [ val1 ] ref2 [ val2 ] ref3 |
    +----------------------------------+

##### Lookup in B^+^-Trees

1.  Compare the value you are looking for with `val1` and `val2`.
2.  If any matches, then follow the `ref` on the left of the match. This
    will point you to the record on disk. Finish.
3.  Else If the value you are looking for is between `val1` and `val2`
    follow the middle `ref2`
4.  Else If the value you are looking for is smaller than `val1` follow
    the left `ref1`
5.  Else If the value you are looking for is greater than `val2` follow
    the last `ref3`
6.  If by following any ref (step 3 -- step 5) you reach a leaf then the
    item is not there. Finish.
7.  Repeat the algorithm.

##### Advantages and Disadvantages of B^+^-Trees

**Disadvantages**:

-   extra storage space needed
-   extra insertion and deletion overhead

**Advantages**:

-   faster access than without an index
-   dynamic (changes size as needed)
-   sequential as well as random access
    -   not really random because it's not $O(1)$it's logarithmic
        and sequential at the leaves
-   unlike binary search trees, B^+^-trees have very high fanout (number
    of pointers to child nodes in a node, typically on the order of 100
    or more), which reduces the number of I/O operations required to
    find an element in the tree

**NOTE**: Advantages of B^+^-trees typically outweigh the disadvantages,
they are used extensively.

<!--TODO file structure: heap, sequential (ordered), hash, clustered-->
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
    -   between $n$ entities
    -   e.g. staff meets student at location

### Multiplicities

-   main structural constraint on a relationship
-   the number (or range) of possible occurrences of an entity that may
    be related to a single occurrence of an associated entity in a
    relationship
-   dependent on the policies of the user or organisation and is
    referred to as a **business rule**
-   made up of two constraints -- participation and cardinality `(P..C)`
-   cardinality: maximum number of instances of entity in a relationship
-   participation: minimum number of instances of an entity in a
    relationship
    -   can be made **optional** if set to 0, otherwise it's
        **mandatory**

Based on cardinalities there are 3 types of binary relationships:

-   "one-to-one"
-   "one-to-many"
-   "many-to-many"
    -   to handle these extract an association entity so that many to
        many (`*..*`) becomes one to many to one (`1..1` `1..*` `1..*`
        `1..1`)

### Strong vs Weak Entities

**Strong Entity**:

-   does not depend on the existence of another entity
-   uniquely identifiable by attribute i.e. primary key

**Weak Entity**:

Depends (partially or wholly) on the existence of another entity:

-   e.g. via participation constraint (if optional)
-   e.g. when not uniquely identifiable by own attributes (primary key
    depends on other entity)

## Problems

### Anomalies and Data Duplication

Data duplication (i.e. data redundancy) causes animalises in updates.

  Name   Department   Manager
  ------ ------------ ------------
  John   **HR**       **Bernie**
  Kate   **HR**       **Bernie**
  Alan   IT           Annie

#### Insertion Anomaly

(See the table above)

-   If a new employee needs to be added the manager has to be re-input.
-   A new department can only be added when there exists an employee for
    that department.

> An Insert Anomaly occurs when certain attributes cannot be inserted
> into the database without the presence of other attributes. For
> example this is the converse of delete anomaly - we can't add a new
> course unless we have at least one student enrolled on the course.
>
> -- <https://www.sqa.org.uk/e-learning/MDBS01CD/page_24.htm>

#### Deletion Anomaly

(See the table above)

If the last employee is deleted form, say, the IT department, the
department vanishes from the database.

  Name       Department   Manager
  ---------- ------------ -----------
  John       HR           Bernie
  Kate       HR           Bernie
  ~~Alan~~   ~~IT~~       ~~Annie~~

> A Delete Anomaly exists when certain attributes are lost because of
> the deletion of other attributes.
>
> -- <https://www.sqa.org.uk/e-learning/MDBS01CD/page_23.htm>

### Modification Anomaly

(See the table above)

If the HR manager is replaced by Adam, all the records for the employees
in HR must be updated.

  Name   Department   Manager
  ------ ------------ ---------------------
  John   **HR**       **Bernie -\> Adam**
  Kate   **HR**       **Bernie -\> Adam**
  Alan   IT           Annie

> An Update Anomaly exists when one or more instances of duplicated data
> is updated, but not all.
>
> -- <https://www.sqa.org.uk/e-learning/MDBS01CD/page_22.htm>

## Normalisation

<!--TODO finish off normalisation-->
Normalization is a systematic way of ensuring that a database structure
is suitable for general-purpose querying and free of certain undesirable
characteristics:

-   insertion anomaly
-   update anomaly
-   deletion anomaly

that could lead to loss of data integrity.

Normalization restructures a relational database in accordance with a
series of so-called '**normal forms**' in order to reduce data
redundancy and improve data integrity.

A standard piece of database design guidance is that the designer should
create a fully normalized design.

### First Normal Form (1NF)

A relation is in 1NF if:

Values in table row need are atomic i.e. they need to be primitive data
types.

In other words, the domain of each attribute (column) contains atomic
values and the value of each attribute contains only 1 value from that
set.

### Second Normal Form (2NF)

A relation is in 2NF if it is in 1NF and:

all non-prime (i.e. non-key) attributes depend (see [functional
dependencies](#functional-dependency)) on the whole of all candidate
keys.

**A functional dependency on part of any candidate key is a violation of
2NF.**

### Third Normal Form (3NF)

A relation is in 3NF if it is in 2NF and:

all non-key attributes are determined *only by* the whole of candidate
keys of that relation.

**NOTE** tables can be normalised beyond 3NF but most 3NF tables are
free of update, deletion and insertion anomalies so it might not be
necessary.

### Functional Dependencies

In a relation (i.e. a table), some attributes may be logically dependent
on other attributes (e.g. `Employee.name`{.sql} may depend on primary key
`Employee.empNo`{.sql}).

**Functional dependency** describes a relationship between attributes in
a relation (table). It's a property of the meaning (semantics) of
attributes in a relation.

Functional dependency is an important concept connected to
normalisation.

If A and B are attributes in a relation R, then `A -> B` indicates that
B is functionally dependent on A. I.e. each value of A in R is
associated with exactly one value of B, but a value in B may be
associated with different values of A.

The **determinant** of a functional dependency refers to the attribute
on the left-hand side of the arrow.

## Glossary

DBMS
:   acronym for "Database Management System".

Attributes

:   Refer to properties of an entity or a relationship.

    E.g.: `Student (stdID, name, gender, address)`{.sql}.

    Attribute domain is a set of allowed values for an attribute.

    E.g.:

    -   age is drawn from the set of integers
    -   name is drawn from the set of strings

    Attributes may be:

    -   single-valued (one value for each instance of an entity
        e.g. age)
    -   multi-valued (more than one value for an instance of an entity
        e.g. hobby)
    -   derived (they might be derived from other attributes)

Entity

:   Represents a group of objects with the same characteristic of
    interest.

    E.g.: Student, Staff, Course.

    An entity *may* be **concrete** (e.g.: Film and Car) or **abstract**
    (e.g.: Booking, Registration).

    Every instance of entity is uniquely identifiable by a **primary
    key**. A primary key *may* consist of more than one attribute (which
    makes it a **composite key**)

Relationship

:   Is an association between entities.

    Entities may participate to varying degrees in relationships.

    E.g.: `Staff 1..2 --------- 0..* Course`

    -   A course is taught by EITHER one OR two staff members
    -   a staff member MAY teach 0 OR MORE courses

    There may be more than one association between entities.

    E.g.: `Person ----- owns > ----- Car` and
    `Person ----- drives > ----- Car`

Non-Prime Attribute
:   an attribute that is not a part of any candidate key of the
    relation.

Prime Attribute
:   a key or parts of a key

Physical schema
:   how data is to be represented and stored (files, indices, et al.) in
    secondary storage using a particular DBMS

Database Schema

:   -   structure described in a formal language supported by the
        database management system (DBMS).
    -   The term "schema" refers to the organization of data as a
        blueprint of how the database is constructed (divided into
        database tables in the case of relational databases).
