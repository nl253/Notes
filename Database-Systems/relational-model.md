# Relational Model

## History

Codd in 1970 proposed the priniciples of the relational model.

-   logical model (independent of physical representation)
-   formal model (uses set theory and predicate logic)
-   data model

## Basics of the Relational Model

**Concerns of the relational model**:

-   data structure
-   data [integrity](#integrity-constraints)
-   data manipulation

**Components of the relational model**:

-   data structures -- entities and their relations.

    For example:

    -   Person
    -   knows
    -   marriedTo

-   data constraints
-   [relational operators](#relational-operators)

**Objectives of the relational model**:

-   To obtain a high degree of [data independence](#data-independence)
-   To control [redundancy](#redundancy) and maintain
    [consistency](#consistency)
-   To enable set-oriented manipulation languages to be used (e.g. [relational
    algebra](#relational-algebra))

**NOTE** by convention [primary keys](#primary-keys) are underlined.

## Relation

-   a relation between sets A and B leads to a set of [ordered pairs](#tuple)
-   mathematical concept based on set theory and predicate logic
-   is a logical representation of data
-   physically representable as a table

For example:

$$
    S_1\ R\ S_2 = \{('Olivia', 'Harry'), ('Olivia', 'Olivia'), ('Harry', 'Lily')\ ... \}
    \\ \\ \\
    where
    \\
    S_1 = domain \\
    S_2 = codomain \\
    R = Knows \\
$$

A relation is a table with rows and columns. It represents an entity or a class
(eg. Student).

Because relations are sets of tuples, all set related properties apply:

-   order doesn't matter
-   each instance of this entity needs to be uniquely identified by one or a
    collection of attributes (think set theory -- sets don't include duplicate
    elements) -- [primary key](#primary-key) in SQL

## Domain

a set of possible values that an [attribute](#attribute) can take

For example:

-   integer
-   date
-   float
-   string (varchar or text)
-   money
-   decimal

Like in programming languages it makes it possible to put restrictions on the
types of operations we can carry out.

The 'type sytem' prevents us from adding for example: a string to an int or
comparing money to date.

Attributes in a [relation](#relation) may have the same domain. We could have a
`Person` relation with age (int) and height (cm -- int).

  age   height
  ----- --------
  32    189
  22    201

Domains can be:

-   potentially infinite

    For example: integers \\( \\{ - \\infty ... \\infty\\} \\)

-   [contrained](#integrity-constraints)

    For example: gender \\( \\{M,\\ F\\}\\)

## Database Constraints

-   at any time a [database](#database) is a 'snaphshot of reality'
-   the defintion of a database encompasses [integrity
    contrains](#entity-integrity)

We might have [**integrity constraints**](#integrity-constraints):

-   [entity integrity](#entity-integrity)
-   [referential integrity](#referential-integrity)
-   application/database-specific -- equivaltnt to business rules

## Terminology

  Relational Model     Database
  -------------------- ----------------
  Relation or Entity   Table or File
  Tuple                Row or Record
  Attribute            Colum or Field

## Glossary

Database
:   collection of related relations

Function

:   -   a is a mapping between sets A and B takes each item a from A to b in B.
    -   functions are 1-1
    -   they take the [domain](#domain) and map it to the codomain
    -   \\( f : X \\rightarrow Y \\)

Relationship

:   association between [entities](#relation)

NULL

:   -   represents missing data when for example: value is unknown or will be
        filled in later
    -   not the same as `""` (the empty string) or 0
    -   introduced reluctantly by Codd
    -   without it the underlying relational theory would be based on (boolean)
        predicate logic that deals with T and F. This turns it into 3-valued
        logic.

Tuple

:   -   instance (ie occurrence) of an [entity](#relation)
    -   corresponds to a row in SQL [databases](#database).

    For example:

      pid   pname
      ----- ------------
      1     toothpaste
      2     brush

    tuples are: \\( (1,\\ toothpaste),\\ (2,\\ brush)\\)

Entity Integrity

:   -   no part of primary key may have a null value
    -   primary key must be unique

Referential Integrity

:   -   constraint on [foreign keys](#foreign-key)
    -   value of a foreign key must either match a [candidate
        key](#candidate-key) in the [entity](#entity) it refers to or be
        [NULL](#null)

Attribute

:   -   descriptions of an [entity](#relation)
    -   attributes have distinct names
    -   values need to be atomic -- drawn from the [domain](#domain) of the
        attribute

    For example:

      name    gender   age
      ------- -------- -----
      Tom     M        19
      Alice   F        33

    `name`, `gender` and `age` are attributes.

Candidate Key

:   minimal set of [attributes](#attribute) needed to uniquely identify each
    [tuple](#tuple)

Integrity Constraints

:   rules ensuring the database does not have invalid values and configurations

Primary Key

:   [candidate key](#candidate-key) chosen to uniquely identify each tuple

Alternate key

:   If there are more than one [candidate keys](#candidate-key) in a
    [relation](#relation) and one of these is chosen as the [primary
    key](#primary-key), the remaining candidate keys are referred to as
    alternate keys

Composity Key

:   a key consisting of more than one [attributes](#attribute)

Relational Operators

:   -   restrict
    -   project
    -   cartesian product
    -   union
    -   difference

    ...

Foreign Key

:   -   attribute (or a set of them) that matches a [candidate
        key](#candidate-key) of the associated [relation](#relation)
    -   used to represent [relationships](#relationship) between relations
