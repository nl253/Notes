# Quality Assurance

<!--TODO deriving tests from specification-->
<!--TODO integration: big-bang vs bottom-up vs top-down vs sandwich-->
<!--TODO MC/DC-->

Quality Assurance (QA) defines a set of practices that developers should follow
to ensure high quality of software. It encompasses the whole software
development process:

-   requirements
-   design
-   coding
-   testing
-   reviews
-   integration
-   release

The techniques used to ensure QA may vary but most often they include testing.

**Characteristics of Software Quality Assurance (SQA)**:

-   reliability
-   functionality
-   usability
-   portability
-   maintainability
-   efficiency

## Verification & Validation

The goal of Verification & Validation (V & V) is the establish confidence that
the system is fit for purpose. The level of confidence required for the release
depends on what the system is indented to do. If it is about managing people's
lives, for instance, then it critical that the software is thoroughly tested in
advance using all possible techniques. This would not be as important when the
product is supposed to be a prototype.

Apart from testing V & V may include:

-   inspections
-   code reviews
-   static code analysis such as cyclomatic complexity or dependency analysis

### Validation

Is intended to ensure a product, service, or system results in a product,
service, or system that meets the operational needs of the user or client.

This may be done using client acceptance testing.

### Verification

It is a process that is used to evaluate whether a product, service, or system
complies with regulations, specifications, or conditions imposed at the start
of a development phase.

In the post-development phase, verification procedures involve regularly
repeating tests devised specifically to ensure that the product, service, or
system continues to meet the initial design requirements, specifications, and
regulations as time progresses.

This is often an internal process.

### Functional vs Non-functional requirements

#### Functional requirements

are about what the system is supposed to do. E.g. the system shall enable
it's users to access their accounts.

#### Non-functional requirements

are about what the system is supposed to be like.

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

### Static vs Dynamic

**Dynamic techniques**

- are V & V methods that require to run the program.
- Dynamic and static techniques go hand in hand.

**Static techniques**

- V & V methods that don't require to run the program.
- If it's not necessary you should avoid running the system -- static techniques are *safer*.

### Testing

Testing helps us the verify software systems by demonstrating to the
stakeholders that the system behaves (or not) in the expected way (conforms to
the specification).

Testing is a part of the broader V & V process.

#### Goals of Testing

1.  Validation Testing

Demonstrate to stakeholders that the product conforms to the specification.

2.  Defect Testing

Discover bugs before the software is put to use. Software is exposed to a set
of test cases (i.e. a [test suite](#test-suite)) which need to reflect the way
it's supposed to be used.

#### Weaknesses of Testing

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

#### White vs Black-Box Testing

Tests are categorised as either Black Box or White Box depending on the
amount of access to the internal workings of what is being tested

**Black box (functional) testing**

No access to the internal structure of the code (nothing is known about how the
program is written). All you can see is the functionality i.e. the output
(behaviour).

Black box testing applies at all granularity levels:

-   Unit (from module interface specification)
-   Integration (from API or subsystem specification)
-   System (from system requirements specification)
-   Regression (from system requirements and bug history)

**White box (structural) testing**

Testing with access the internal structure of the code being tested.
Structural testing is basically concern both the results and also the process.

White box testing applies to relatively small parts of a system:

-   Unit
-   Integration

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

###### Unit Testing Frameworks

It's almost always a good idea to use a framework for unit testing (e.g. JUnit)

-   good and informative error messages
    -   how it failed
    -   what went wrong
    -   what was expected
-   reduce code duplication
    -   if you aren't using a framework you're writing one yourself
-   easy to write `setUp` and `tearDown` methods
-   can group tests using tags (see JUnit5)
-   manual testing is error prone
-   generate report
-   assemble a bunch of test cases into a

###### Weaknesses of Unit Testing

1.  Does not catch issues on the level of integration. It will not tell us how
    components interact when put together in practice.
2.  Cannot test performance.
3.  UIs are not testable with unit testing.
4.  Non-functional requirements are difficult to test.
5.  Testing a unit in isolation may not be possible.

See [weaknesses of TDD](#weaknesses-of-tdd).

Testing a unit in isolation may not be easy... E.g. a unit may rely on methods
provided by another unit (dependent-on component -- DOC).

One solution is to replace the DOC with a piece of code that simulates the
activity of that DOC.

-   It doesn't need to behave exactly as the DOC
-   Just provides the same API

###### Test Double

-   additional code that "stands in" for the missing code with which the SUT is
    interacting.
-   not part of the final code, they are just useful for testing
-   developing them has a cost, but it is often compensated by the benefits of
    unit testing
-   use when:
    -   untested requirement that cannot be verified
    -   want to isolate the unit i.e. test it without interaction with other
        units which might make it unclear what is cause what behaviour
    -   want to be able to run our tests more quickly and more often

Dummy
:   an empty object passed in a method call, but never used. Typically just to
    satisfy a compiler when a method argument is required

Fake
:   an object with a working implementation, but usually in a simplified form,
    i.e. not suitable for production.

Spy
:   a stub that also records information based on how they were called e.g. an
    in-memory database

Stub

:   -   an object with hard-coded behaviour suitable for a given test. Provides
        "canned answers", not responding to other calls
        -   No dynamically chosen random values as tests should be consistent
        -   It must be very simple, e.g. returning hard-coded values
    -   A stub replaces a DOC and provides the test a control point for the
        indirect inputs of the SUT
    -   It can be used to force the SUT down paths it might not otherwise
        execute
    -   In the choice of inputs, you can use partitioning, boundary analysis,
        etc.

Mock
:   has a set of expectations about calls that are made. If these expectations
    are not met, the test is fail.

###### Testing Inputs

**Random (uniform) choice** -- pick inputs randomly in the input domain.

-   Avoid designer bias -- test designer can make the same logical assumptions as
    the program designer (especially if they are the same person)
-   But treats all inputs as equally viable ... is this a good assumption ?
-   The faults in the code are needles in a haystack
-   To sample the number of needles in a hay we would sample randomly
-   sampling requires unbiased statistics but it is not our goal!
-   To find the needles in the hay we need to look systematically (not
    uniformly, unless there are really many needles)
-   **Systematic approach** -- try to select inputs that are especially valuable
-   Functional testing is systematic testing

Some general guidelines:

-   force invalid outputs to be generated generate all possible error messages
-   force computation results to be too large or too small
-   sequences
    -   test sequences of zero length
    -   test sequences with only a single value
    -   use sequences of different sizes in different tests
    -   derive tests that access the first, central, last element

###### Equivalence Partitions Testing

-   It is a software testing technique that systematically divides the input
    domain of a piece of software into **classes** forming a partition all
    elements in a class should "fail in the same way"
-   the aim is to uncover classes of errors and minimise the number of inputs
    to try (hence tests) **at least two classes -- legal and unexpected inputs**

###### Boundary Analysis

No guarantees, but experience suggests that faults are more likely to be at the
boundary than at the centre.

Boundary analysis-- refines the input choice made by Equivalence Class
Partitioning by picking the "boundary" values for each class.

By Boundary Value Analysis, which input values should we consider for integer x
with domain {0..10} ?

1.  divide the domain in partitions -- \\( x \\lt 0, 0 \\ge x \\le 10, x \\gt 10 \\)
2.  chose boundary values for each partition: -1, 0, 10, 11

Repeat the exercise with the input x being a string of 3 characters.

###### MC/DC

###### Deriving Unit Tests from Specification

Use case diagrams provide useful information for testing (Jacobson et al. 92):

-   the basic courses (expected flow of events)
-   the odd courses (alternative flows)
-   each line-item in the requirements traceable to a use case
-   each feature in user documentation traceable to a use case

However use cases lack important information for testing:

-   no domain definitions for inputs and output variables (no type information)
-   no conditions that determine basic or alternative flow
-   no testable specification of I/O relationships among variables
-   no dependencies among use cases
-   no information on the frequency of each use cases

###### Unit Tesing Glossary

Units
:   are the smallest testable parts of the system. In object oriented
    programming units can be classes, interfaces or methods in functional
    programming they can be modules or functions.

Test suite
:   is a collection of test cases intended to show that some piece of software
    conforms to the specification by behaving in accordance with the test
    cases.

Test case
:   is a specification of a single test which means a description of the
    procedure, inputs, environment and expected output.

Fixture

:   -   is a fixed state of a set of objects used as a baseline for running
        tests.
    -   The purpose of a test fixture is to ensure that there is a well known
        and fixed environment in which tests are run so that results are
        repeatable. (a bit like seeding random number generator)

##### Component Level Testing

- test several interacting objects
- you access the functionality of these objects through the defined **component interface**
- testing composite components should therefore focus on showing that the component interface behaves according to its specification
- you can assume that unit tests on the individual objects within the component have been completed

##### Integration Level Testing

- putting components together
- focus on interactions between components
- checks emergent behaviour
- lower level than system testing

##### System Level Testing

- putting it all together
- higher level than integration
- checks emergent behaviour
- focus on interactions between components

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

Mock objects

:   sometimes called stubs act as dummy objects that stand in place of the
    actual units. They often satisfy an interface without doing the work a real
    unit would require.

    For example we can have a mock database that instead of
    fetching the data from a remote repository simply returns some fake,
    hard-coded data to the client.
