# Quality Assurance

QA defines a set of practices that developers should follow to ensure high
quality of software. It encompasses the whole software development process:

-   requirements
-   design
-   coding
-   testing
-   reviews
-   release

etc.

The techniques used to ensure QA may vary but most often they include testing.

**Characteristics of SQA**:

-   reliability
-   functionality
-   usability
-   portability
-   maintainability
-   efficiency

## Verification & Validation

The goal of V & V is the establish confidence that the system is fit for
purpose. The level of confidence required for the release depends on what the
system is indented to do. If it is about managing people's lives, for instance,
then it critical that the software is thoroughly tested in advance using all
possible techniques. This would not be as important when the product is
supposed to be a prototype.

**Validation** is intended to ensure a product, service, or system results in a
product, service, or system that meets the operational needs of the user.

**Verification** is intended to check that a product, service, or system meets
a set of design specifications.

In the development phase, verification procedures involve performing special
tests to model or simulate a portion, or the entirety, of a product, service or
system, then performing a review or analysis of the modeling results.

In the post-development phase, verification procedures involve regularly
repeating tests devised specifically to ensure that the product, service, or
system continues to meet the initial design requirements, specifications, and
regulations as time progresses.

It is a process that is used to evaluate whether a product, service, or system
complies with regulations, specifications, or conditions imposed at the start
of a development phase. Verification can be in development, scale-up, or
production.

This is often an internal process.

Apart from testing V & V may include:

-   inspections
-   code reviews
-   static code analysis such as cyclomatic complexity or dependency analysis

### Testing

Testing helps us the verify software systems by demonstrating to the
stakeholders that the system behaves (or not) in the expected way (conforms to
the specification).

Testing is a part of the broader V & V process.

**Goals of Testing**

1.  Validation Testing

Demonstrate to stakeholders that the product conforms to the specification.

2.  Defect Testing

Discover bugs before the software is put to use. Software is exposed to a set
of test cases (i.e. a [test suite](#test-suite)) which need to reflect the way
it's supposed to be used.

**Weaknesses of Testing**

> Program testing can be used to show the presence of bugs, but never to show
> their absence! ...
>
> Therefore, proof of program correctness should depend only upon the program
> text. <br/>
>
> -- Dijkstra in "Structured Programming" (1969)

1.  Software testing cannot tell us that the software is bug-free. What
    software is good at is discovering issues and ensuring that the system
    behaves in a certain way under certain conditions.
2.  Because there are possibly infinite combinations of environmental
    conditions and inputs for even the simplest program we can never test for
    all possible scenario's. At best we can aim for high coverage and for the
    system to behave in accordance with the specification.

#### Inspections

Inspections are a static verification technique for analysing code. They are
fairly effective in improving the code quality (e.g. IDE inspections might
encourage 'best' practices such as favouring immutability `final String s` vs
`String s`) but also discovering possible bugs (e.g. `'n'` instead of
`System.lineSeparator()`).

**Benefits of Inspections**

There are some advantages to inspections that make them better than other
techniques.

-   they are easy to carry out -- unlike automated testing they don't require
    to plan and write a test suite to discover issues
-   they are cheap to carry out compared to techniques such as alpha testing
    which might take hours for which the tester will have to be paid
-   they might be safer since the code is not executed

#### Testing Levels

There are four main recognised levels of testing:

1.  Unit
2.  Component
3.  Integration
4.  System

##### Unit Level Testing

Unit level testing occurs at the lowest level of granularity, it concerns
itself with verifying that units behave in accordance with the specification.

Ideally, when doing unit testing we want units to be separate. If units are
coupled testing becomes really difficult. It is inevitable that units will need
to interact in some way with each other and to test a unit we will have to
provide a mock object that will allow us to test the unit in isolation.

Unit tests should ideally not go outside of their own class boundary. They
should not cross process or network boundaries. Crossing such boundaries turns
a unit test into an integrations test. If something fails then we cannot be
certain what caused the failure. In other words, by testing units in isolation
it becomes easier to narrow down the cause of bugs.

Unit testing forms the basis for component testing.

**Weaknesses of Unit Testing**

1.  Does not catch issues on the level of integration. It will not tell us how
    components interact when put together in practice.
2.  Cannot test performance.
3.  UIs are not testable with unit testing.
4.  Non-functional requirements are difficult to test.

See [weaknesses of TDD](#weaknesses-of-tdd).

##### Component Level Testing

##### Integration Level Testing

##### System Level Testing

#### Test Driven Development

TDD is about interleaving of coding and writing tests. This is an incremental
approach in which programmers identify the requirements and from those
requirements they extract tests. Programmers don't move to the next stage of
development until the code they have written in this particular stage passes
their tests.

**Origin**

TDD was originally very popular in agile development (particularly XP --
Extreme Programming) but has now become a widely accepted practice in agile as
well plan-driven methods-driven development methods.

**Benefits of TDD**

1.  Passing the tests which are based on system requirements means that the
    system implements the required functionality and meets the specification.
    Therefore it helps to verify the system and improves confidence in it's
    implementation of requirements.
2.  It forces the programmer to write code that is directly relevant to the
    required functionality. It also forces the programmer to be conscious of
    the specification since it's necessary to engage in TDD. This in turn is
    likely to result in a product that meets the client's expectations (higher
    validity).
3.  Tests are automated and can be re-run at any point during development. This
    allows programmers to ensure that the old code has not been broken with the
    addition of new features and that even in stage 4 the system still
    correctly implements functionality rom stage 1, 2 and 3. It promotes
    confidence in the correctness of the way the system has been implemented.
    This is referred to as **regression testing**.
4.  Defects in code are quickly (i.e. in early stages) and easily (after a
    failed test) discovered.
5.  A test suite that TDD will help to produce might act as documentation. We
    would expect it to list in a detailed manner all the required functionality
    and expected behaviour of the system. Tools such as `javadoc` can be used
    to generate human-readable documentation from it.

**Weaknesses of TDD**

1.  Typically an external libera is required (e.g. JUnit). This means there is
    an additional dependency in the project.
2.  Requires understanding of specification. The programmer must know in
    advance what features to write tests for.
3.  TDD goes hand in hand with automated unit testing. It might be difficult to
    ensure that the system acts in the expected way as a whole using just TDD.
    Unit testing cannot guarantee the all the components interact in the
    expected way. It only ensures units behave correctly. In might be necessary
    to do some manual alpha testing to see if all the higher level components
    such as UI and database work together correctly.
4.  Anything that requires changes in code (for instance changes to
    requirements) will result in more work. On top of changing the actual code
    the programmer must also make alterations to the tests. This means they
    pose an extra burden as they contribute to a larger code base.

#### Regression Testing

Type of software testing where after changes have been made to the codebase
tests are rerun to ensure that those changes haven't broken the old code and
that the 'old' part of the system still behaves in the expected way.

Regression testing becomes extremely expensive when done manually which is why
in most cases this is done using previously written automated tests.

### Terminology

Cohesion
:   is another dimension which can be considered in measuring unit
    independence. A high level of cohesion means that a unit is focused on a
    particular task such as database only storing data. Such a database would
    begin to lose cohesion if it started to deal with authorisation or data
    validation.

Coupled units
:   are units with high level of dependency on other units. Coupling entails
    low level of independence.

Errors
:   happen when faults cause parts of the system to deviate from the expected
    state.

Failures
:   happen when the entire system goes down because of some error.

Faults
:   are flaws (defects) in the system such as bugs.

Functional requirements
:   are about what the system is supposed to do. E.g. the system shall enable
    it's users to access their accounts.

Non-functional requirements

:   are about what the system is supposed to be like.

    E.g. the system will be user-friendly.

    Describe aspects of the system that do not specifically provide user
    functionality but are required to support delivery of the functions.

    -   Their implementation may require additional functional requirements to
        be made.
    -   Examples include product requirements, such as:

        -   Response Times
        -   Storage capacity
        -   Security

    Other non-functional constraints that may affect a system might also
    include:

    -   External considerations such as legal, ethical or regulatory
        requirements.
    -   How the users and developers organise/ons work, e.g. in an open plan
        office (confidentiality), using prescribed development tools,
        languages, frameworks, etc.
    -   Non-functional requirements often affect the overall system
        architecture.

Mock objects
:   sometimes called stubs act as dummy objects that stand in place of the
    actual units. They often satisfy an interface without doing the work a real
    unit would require. For example we can have a mock database that instead of
    fetching the data from a remote repository simply returns some fake,
    hard-coded data to the client.

Units
:   are the smallest testable parts of the system. In object oriented
    programming units can be classes, interfaces or methods in functional
    programming they can be modules or functions.

Static techniques
:   are V & V methods that don't require to run the program. If it's not
:   necessary you should avoid running the system -- static techniques are
    *safer*.

Dynamic techniques
:   are V & V methods that require to run the program. Dynamic and static
    techniques go hand in hand.

Test suite
:   is a collection of test cases intended to show that some piece of software
    conforms to the specification by behaving in accordance with the test
    cases.

Test case
:   is a specification of a single test which means a description of the
    procedure, inputs, environment and expected output.
