# Data Modelling

As of 2012 seven main databases dominate the commercial marketplace:

-   DB2
-   Informix
-   MySQL
-   Oracle
-   Postgres
-   SQL Server
-   Sybase

Other RDBMS systems tend either to be legacy databases or used within academia
such as universities or further education colleges.

Part of the process of database development. It takes place after initial
problem analysis has been conducted. We need to know:

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

-   first step in data modelling, typically this is a first-cut model, with
    insufficient detail to build an actual database, describes the structure of
    the whole database for a group of users
-   high level description of data and their relationships
-   independent of DBMS types and all physical considerations (a conceptual
    data model could be done for: a relational database, object-oriented
    database, graph database etc.)
-   examining properties of a system to determine
    -   which data objects are relevant
    -   how are they related to each other
    -   constraints that must be satisfied
-   uses non-technical names, so that executives and managers at all levels can
    understand the data basis of Architectural Description
-   May not be normalized
-   it describes the things of significance to an organization
    -   entity classes, about which it is inclined to collect information
    -   characteristics of (attributes) entities
    -   associations between pairs of those entities of significance
        (relationships)
-   it may exist on various levels of abstraction
-   the model does allow for what is called inheritance in object oriented
    terms (not the case in logical model for relational databases)
    -   super-type/sub-type relationships may be exclusive or not

### ER Modelling

Entity Relational (ER) modelling is a process of identifying:

-   data (**entities** and their **attributes**)
-   **relationships** between them
-   **constraints**

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

Conceptual model identifies candidate keys and selects one of them as the
primary key.

### Abstraction

Represents a very high approach to data modelling.

Types of abstractions:

-   classification (grouping concepts)
    -   find a common property in a set of objects and group them into an
        entity
-   aggregation (composing)
    -   define a new entity from a set of other entities
    -   each component is associated with the aggregation by the
        "**is-part-of**" or "**has-a**" relationship
-   generalisation and specialisation (hierarchical relationships)
    -   group or classify objects with common properties to a class or entity
    -   individual objects are associated with its class by the
        "**is-instance-of**" relationship

## Logical Database Design

Logical data models should be based on the structures identified in a preceding
conceptual data model, since this describes the semantics of the information
context, which the logical model should also reflect.

-   Process of refining an mapping the conceptual data model to a logical data
    model.
-   Local database design should produce a description of database which takes
    into account the type of DBMS.
-   Independent of a *particular* type of DBMS (e.g. PostgreSQL or MySQL) and
    other physical considerations.
-   Is normalized to fourth normal form (4NF)
-   Uses business names for entities & attributes
-   Includes entities (tables), attributes (columns/fields) and relationships (keys)

## Physical Database Design

The physical design of a database involves deep use of particular database
management technology. For example, a table/column design could be implemented
on a collection of computers, located in different parts of the world. That is
the domain of the physical model.

-   typically derives from a logical data model
-   description of the database implementation in terms of **storage
    structures** and **access methods**
-   more low-level, concerned with implementation details such as:
    -   indexes
    -   security measures
    -   base relations
    -   integrity constraints
    -   file organisations
-   specific to a DBMS
-   includes:
    -   tables
    -   columns
    -   keys
    -   data types
    -   validation rules
    -   database triggers
    -   stored procedures
    -   domains
    -   access constraints
-   uses more defined and less generic specific names for tables and columns, such as abbreviated column names, limited by DBMS and any company defined standards
-   includes:
    -   primary keys
    -   indices (for fast data access)
-   may be de-normalized to meet performance requirements based on the nature of the database

For example: SQL Server runs only on Microsoft Windows operating-systems, while
Oracle and MySQL can run on Solaris, Linux and other UNIX-based
operating-systems as well as on Windows.

This means that the disk requirements, security requirements and many other
aspects of a physical data model will be influenced by the RDBMS that a
database administrator (or an organization) chooses to use.

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

-   one-to-one
-   one-to-many
-   many-to-many
    -   to handle these extract an association entity so that many to many
        (\*..\*) becomes one to many to one (1..1 1..\* 1..\* 1..1)

### Strong vs Weak Entities

**Strong Entity**:

-   does not depend on the existence of another entity
-   uniquely identifiable by attribute i.e. primary key

**Weak Entity**:

-   depends (partially or wholly) on the existence of another entity
-   e.g. via participation constraint
-   e.g. when not uniquely identifiable by own attributes (primary key depends
    on other entity)

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
-   A new department can only be added when there exists an employee for that
    department.

> An Insert Anomaly occurs when certain attributes cannot be inserted into the
> database without the presence of other attributes. For example this is the
> converse of delete anomaly - we can't add a new course unless we have at
> least one student enrolled on the course.
>
> -- <https://www.sqa.org.uk/e-learning/MDBS01CD/page_24.htm>

#### Deletion Anomaly

(See the table above)

If the last employee is deleted form, say, the IT department, the department
vanishes from the database.

  Name       Department   Manager
  ---------- ------------ -----------
  John       HR           Bernie
  Kate       HR           Bernie
  ~~Alan~~   ~~IT~~       ~~Annie~~

> A Delete Anomaly exists when certain attributes are lost because of the
> deletion of other attributes.
>
> -- <https://www.sqa.org.uk/e-learning/MDBS01CD/page_23.htm>

### Modification Anomaly

(See the table above)

If the HR manager is replaced by Adam, all the records for the employees in HR
must be updated.

  Name   Department   Manager
  ------ ------------ ---------------------
  John   **HR**       **Bernie -\> Adam**
  Kate   **HR**       **Bernie -\> Adam**
  Alan   IT           Annie

> An Update Anomaly exists when one or more instances of duplicated data is
> updated, but not all.
>
> -- <https://www.sqa.org.uk/e-learning/MDBS01CD/page_22.htm>

## Normalisation

<!--TODO finish off normalisation-->
Normalization is a systematic way of ensuring that a database structure is
suitable for general-purpose querying and free of certain undesirable
characteristics:

-   insertion anomaly
-   update anomaly
-   deletion anomaly

that could lead to loss of data integrity.

Normalization, restructures a relational database in accordance with a series
of so-called 'normal forms' in order to reduce data redundancy and improve data
integrity.

A standard piece of database design guidance is that the designer should create
a fully normalized design.

### First Normal Form (1NF)

A relation is in 1NF if:

Values in table row need are atomic i.e. they need to be primitive data types.

In other words, the domain of each attribute (column) contains atomic values
and the value of each attribute contains only 1 value from that set.

### Second Normal Form (2NF)

A relation is in 2NF if it is in 1NF and:

all non-prime attributes depend (see [functional
dependencies](#functional-dependency)) on the whole of all candidate keys.

A functional dependency on part of any candidate key is a violation of 2NF.

### Third Normal Form (3NF)

A relation is in 3NF if it is in 2NF and:

all attributes are determined only by the candidate keys of that relation and
not by any non-prime attributes.

**NOTE** tables can be normalised beyond 3NF but most 3NF tables are free of
update, deletion and insertion anomalies so it might not be necessary.

### Functional Dependencies

In a relation (i.e. a table), some attributes may be logically dependent on
other attributes (e.g. `Employee.name` may depend on primary key
`Employee.empNo`).

**Functional dependency** describes a relationship between attributes in a
relation (table). It's a property of the meaning (semantics) of attributes in a
relation.

Functional dependency is an important concept connected to normalisation.

If A and B are attributes in a relation R, then `A -> B` indicates that B is
functionally dependent on A. I.e. each value of A in R is associated with
exactly one value of B, but a value in B may be associated with different
values of A.

The **determinant** of a functional dependency refers to the attribute on the
left-hand side of the arrow.

### Normal Forms

#### 1NF

TODO 1NF

#### 2NF

TODO 2NF

#### 3NF

TODO 3NF

## Glossary

DBMS
:   acronym for "Database Management System".

Attributes

:   Refer to properties of an entity or a relationship.

    E.g.: `Student (stdID, name, gender, address)`.

    Attribute domain is a set of allowed values for an attribute.

    E.g.:

    -   age is drawn from the set of integers
    -   name is drawn from the set of strings

    Attributes may be:

    -   single-valued (one value for each instance of an entity e.g. age)
    -   multi-valued (more than one value for an instance of an entity
        e.g. hobby)
    -   derived (they might be derived from other attributes)

Entity

:   Represents a group of objects with the same characteristic of interest.

    E.g.: Student, Staff, Course.

    An entity *may* be **concrete** (e.g.: Film and Car) or **abstract** (e.g.:
    Booking, Registration).

    Every instance of entity is uniquely identifiable by a **primary key**. A
    primary key *may* consist of more than one attribute (which makes it a
    **composite key**)

Relationship

:   Is an association between entities.

    Entities may participate to varying degrees in relationships.

    E.g.: `Staff 1..2 --- 0..* Course`

    -   A course is taught by EITHER one OR two staff members
    -   a staff member MAY teach 0 OR MORE courses

    There may be more than one association between entities.

    E.g.: `Person -- owns > -- Car` and `Person -- drives > -- Car`

Non-Prime Attribute
:   an attribute that is not a part of any candidate key of the relation.

Prime Attribute
:   a key or parts of a key

Physical schema
:   how data is to be represented and stored (files, indices, et al.) in
    secondary storage using a particular DBMS
