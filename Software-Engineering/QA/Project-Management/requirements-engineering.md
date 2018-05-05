# Requirements Engineering

The process of establishing the services that a customer requires from a system
and the constraints under which it operates and is developed.

System requirements are descriptions of the system services and constraints
that are generated during the requirements engineering process.

## Requirements

May range from a high-level abstract statement of a service or of a system
constraint to a detailed mathematical functional specification.

They may serve a dual function:

-   may be the basis for a bid for a contract -- therefore open to
    interpretation
-   may be the basis for the contract itself -- therefore defined in details

A company that wishes to let a contract for a large development contract, it
should define their requirements is a sufficiently abstract way that a solution
is not pre-defined. This allows several contractors to bid offering different
solutions to the problem that still satisfy the customers requirements.

A more detailed requirement specification -- system definition should be
produced by the contractor for the client after the contract has been awarded.
This allows the client to understand and validate what the system will do.

### Types of Requirements

User and system requirements are generated for two types of audiences. They
differ in the level of detail and the type of language used (technical vs
non-technical).

**User Requirements**:

-   statements in natural language
-   diagrams of the services the system provides
-   written for customers
-   for: client managers, contractor managers, client engineers etc.

**System Requirements**:

-   detailed description of system functions, services and operational
    constraints
-   defines what should be implemented so may be a part of the contract
-   for: software developers, system architects, client engineers, end users
    etc.

## Agile and Requirements Engineering

Agile argues that producing a detailed specification of the system is a wast of
time as requirements change so quickly. As a result the requirements document
is always out-of-date.

> "Working software over comprehensive documentation" ... -- Agile Manifesto

Agile uses incremental requirements engineering and may express requirements as
'user stories'. This is practical for business systems but problematic for
systems which need pre-delivery analysis (e.g. critical systems).

## Functional and non-functional requirements

### Requirements Imprecision

Issues arise when requirements are not precisely stated. Requirements
(especially stated in natural language) may be interpreted in different ways by
software developers and end-users.

Ideally requirements should be **complete** (include descriptions of all
facilities) and **consistent** (i.e. non-self-contradictory and
non-self-conflicting).

In practice this is not possible.

### Functional Requirements

-   statements of services the system should provide
-   how the system should react to particular inputs
-   how the system should behave in particular situations
-   may state what the system should not do
-   describe functionality of system services
-   depend on the type of software, expected users and the type of system where
    the software is used
-   **functional user requirements** may be high-level statements of what the
    system should do
-   **functional system requirements** should describe the system services in
    detail

E.g.:

-   A user shall be able to search the appointments lit for all clinics.
-   The system shall generate a list of patients who are expected to have an
    appointment for each day.

### Non-Functional Requirements

Non-functional requirements may be more critical than functional requirements.
If these are not met, the system may be useless.

-   properties of the system
-   constraints on the services or functions such as:
    -   timing constraints
    -   constraints on the development process
    -   standards
    -   often apply to the system as a whole (rather than individual features
        or services)

Non-functional requirements include:

-   product requirements e.g. dependability, efficiency, usability, space,
    performance.
-   organisational requirements e.g. development, operational.
-   external requirements e.g. ethical, legislative, safety, accounting.

Non-functional requirements may affect the overall architecture of a system
rather than individual components.

E.g. to ensure performance you might wish to minimise communication between
components.

A single non-functional requirement might generate a series of related
functional requirements that define what systems and services are required.

Non-functional requirements may be difficult to state precisely and they might
be hard to verify. E.g. ease of use and usability are subjective -- they cannot be objectively
tested.

#### Non-Functional Classifications

-   product requirements
    -   requirements which specify that the product must behave in some way:
        e.g. it must be high-performance, it must be secure, it must be
        maintainable.
-   organisational requirements
    -   requirements which are a consequence of organisational policies or
        procedures
-   external requirements
    -   requirements that arise from factors external to the system and the
        development process e.g. legislative requirements

## Requirements engineering processes

The processes used for requirements engineering vary widely depending on the
application domain, the people involved and the organisation developing the
requirements.

However, there are a number of generic activities common to all processes.

In practice requirements engineering is an iterative activity in which these
processes are interleaved.

### Requirements Elicitation (Discovery)

Technical staff working with customers to find out about the application
domain, the services that the system should provide and system's operational
constraints.  Involves stakeholders.

Problems:

- stakeholders don't know what they really want
- stakeholders express requirements in their own terms
- different stakeholders have conflicting requirements
- organisational and political factors affect system requirements
- requirements change during the analysis process as new stakeholders emerge and
  business environment changes

### Requirements Specification

### Requirements Validation

### Requirements Change

## Glossary

Stakeholder

:   Any person who is affected by the system in some way and so who has a
    legitimate interest

    Types of stakeholders:

    -   end-users
    -   system managers
    -   system owners
    -   external stakeholders
