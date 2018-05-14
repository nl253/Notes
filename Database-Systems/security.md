# Security

<!--TODO types and prevention of security threats-->

See <https://www.wikiwand.com/en/Database_security>.

## Access Control in PostgreSQL

**Issues**:

-   excessive privileges
-   downloading data locally

**Actions**:

-   `UPDATE`
-   `INSERT`
-   `DELETE`
-   `EXECUTE`
-   `SELECT`
-   `ALL`

To give access to do a type of action (in this case update):

``` {.sql}
GRANT  <action> ON ( <table_name> | ALL TABLES ) [IN SCHEMA <schema_name>] TO <user>;
REVOKE <action> ON ( <table_name> | ALL TABLES ) [IN SCHEMA <schema_name>] TO <user>;
```

## Good practices

-   regular updates
-   track users' activity
-   configure DBMS properly
-   correct protocols
-   train (Database Administrators) DBAs
    -   awareness of security issues

## Sensitive Data
    -   encryption
    -   storing passwords
        -   hashing -- e.g.: MD5, SHA2, SHA3
        -   salting
            <!--(TODO look into 'salting' and how it relates to hashing)-->
        -   prevent brute force attacks by limiting repeated queries

## Roles

PostgreSQL manages database access permissions using the concept of roles. A
role can be thought of as either a database user, or a group of database users,
depending on how the role is set up. Roles can own database objects (for
example, tables and functions) and can assign privileges on those objects to
other roles to control who has access to which objects. Furthermore, it is
possible to grant membership in a role to another role, thus allowing the
member role to use privileges assigned to another role.

The concept of roles subsumes the concepts of "users" and "groups". In
PostgreSQL versions before 8.1, users and groups were distinct kinds of
entities, but now there are only roles. Any role can act as a user, a group, or
both.
