# Checklist

## Models of Software Development 

-   what is a "software process"?

> A software development process, also known as a software development lifecycle,
> is a structure imposed on the development of a software product. A software
> process is represented as a set of work phases that is applied to design and
> build a software product.
>
> There are no right or wrong software processes. All have advantages and
> disadvantages under different circumstances.
> 
> In practice in the industry companies use a combination of Agile and
> Plan-Driven software development practices.

-   Outline the main phases that all software processes share:

> All software processes at the minimum include:
>
> -   Specification (defining what functionality the system should provide)
> -   Design and implementation (defining in detail to structure, organisation
>     and behaviour of the system and implementing it)
> -   Validation (checking that the system does what the client expects it to)
> -   Evolution (changing the product in response to changing customer needs)
>
> Forms acronym: `SDVE`.

-  What does "integration" mean?

> Assembling all the components into a working system.

-  Outline the phases of the Waterfall Software Deployment Process Model.

> 1. Requirements analysing.
> 2. System and Software Design.
> 3. Implementation and unit testing.
> 4. Integration and system testing.
> 5. Operation and maintenance.

-  Explain the idea behind "Incremental Software Deployment".

> -   Unlike in the Waterfall Software Development Process there is no notion of
>     clear sequence of actions that software developers should follow.
> -   Instead, phases such as requirements analysis and definition, design,
>     implementation, testing and validation are interleaved i.e. they are
>     carried out concurrently.
> -   During that increasing better versions of the final product are created
>     until all the requirements are met and the client is satisfied.

-   Give 3 advantages and 3 disadvantages associated with adopting the
    incremental approach to software development.

> **Positives**:
> 
> - The major advantage of incremental software development is that feedback is
>   quick -- we can get part of the implementation running and show it to the
>   client for validation. As a result we quickly gain feedback and can make
>   adjustments if necessary.
> - Customers can get the product quicker since: unlike in the Waterfall Software
>   Development Process Model the emphasis on documentation is smaller and also
>   clients can use the snapshots of the product that software developers
>   produce
>
> **Negatives**: 
>
> - The software process is not as visible -- it's difficult to measure
>   progress
> - 

-  How do the Waterfall and the Incremental Software Development Processes differ?

-   plan-driven
    -   reuse-oriented
    -   spiral
    -   agile
    -   ~~waterfall~~
-   project planning and documentation
-   Agile and Scrum
    -   team (describe roles)
        -   scrum master
        -   product owner
    -   ~~documentation~~
        -   ~~product backlog~~
        -   ~~sprint backlog~~
    -   ~~sprints~~

## Architectural Patterns

- What are "box-and-line" Diagrams?
- What's an architectural view?
- What is the "4 + 1 View"?
- Outline, give advantages and disadvantages:
    - MVC
    - Pipe and Filter
    - Layered
    - Client-Server
    - Repository

## Design Patterns

-   Outline, say when they are useful:
    -   composite
    -   decorator
    -   factory method
    -   visitor
    -   MVC
    -   builder
    -   command
    -   facade
    -   observer
    -   singleton
    -   strategy
-   What are "anti-patterns"?
-   What is "code smell"?

## Project Management

- What are the stages of project preparation?
- What documentation is useful in project management?
- What is "requirements engineering"?
- What is the formula for calculating risk exposure?
- Compare and contrast system and user requirements.
- What are the strategies one could adopt in management of risks.

## Agile and Scrum

-   Outline the principles behind Agile software development.

> Agile software development focuses on:
>
> - ensuring high customer satisfaction 
> - working closely with the "product owner"
> - people rather than rigid engineering practices
> - responding to change rather than following a plan
> - delivering the product on time even at the expense of extensive
>   documentation
>
> Agile is arguable more suitable since it takes a more realist approach to
> software development -- it accepts the possibility of change and is open to
> adjustment. Agile is generally speaking a response to the failures in the
> industry caused by following the traditional in engineering "Waterfall
> Model" which has been found to be inadequate when making software.

-   Give 3 strengths and 3 weaknesses of Agile software development.

> Strengths:
>
> -   allows to respond to change (more realistic)
> -   more suitable when we want QA -- this is because of close collaboration
>     with the clients who are able to provide feedback e.g. say if this is
>     what they expected
> -   following Agile software development practices can arguably make a team
>     more productive as argued by the creator of Scrum -- teams are
>     self-organising, (when using a framework) there is a clear structure so
>     there is no ambiguity about who is doing what -- this *might* save some
>     time
>     
> Weaknesses:
>
> - some have argued that it's not suitable for large-scale projects since
>   Agile software development teams are typically small and self-organising
> - maintaining of Agile documents such as Product Backlog, Sprint Backlog and
>   Burndown Chart means less time is spent on coding i.e. doing the actual
>   work
> - Scrum, for instance, can be argued to require excessive ceremonies on the
>   part of software developers e.g. *daily* stand-up in which everyone is
>   likely to say the same thing everyday

-   Compare and contrast Agile and Waterfall software development.

  Agile                            Waterfall
  -------------------------------- -----------------------------------------------------------
  Responds to change, iterative    Plan-driven, distinct phases to which you don't go back
  Focus on people                  Focus on software engineering practices e.g. documentation 
  Small self-organising groups     No requirements about the size of development groups
  Close collaboration with clients No such requirement

-   What is the relationship between Agile software development and Scrum?

> Agile is a particular approach to building software. It tells you what the
> Agile values are but not how to build software or how to enforce those
> values. Scrum is an implementation of Agile -- it's an Agile software
> development framework with more detailed description of exactly what one
> should do.

-   Discuss main elements i.e. "ceremonies" of Agile software development.

> 1. "Daily stand-up"
> 2. "Sprint Review"
> 3. Spring planning meeting
> 4. Regular progress review meetings

-   Explain what is a "daily stand-up" and what's it's purpose in Agile
    software development.

> Keeps other team members up-to-date with what has been done and if you are
> facing any issues.

-   Describe what is a "sprint" in the context of Agile software development.

> Sprint in an iteration in Scrum (in Agile software development). Each sprint
> lasts (usually) 2 -- 4 weeks. It consists of: intial planning, daily
> meetings, coding, testing etc.

-   Discuss the main documents maintained in Agile software development.
-   How is progress tracked in Agile software development?
-   What are the team roles in Agile software development and what are their
    responsibilities?

## UML

- what's a "model"?
- why use models?
- Types of system perspectives.
- Context vs Interaction vs Behavioural vs Structural models.
- What is "model-driven engineering"?
- What is "data-driven modelling"?

### Use Case Diagrams

- system boundaries
- include
- extend

### Class Diagrams

- what are:
    - attributes
    - features
    - operations
    - stereotypes
    - multiplicities
    - visibility
    - instantiation
    - associations
        - dependencies
            - call
            - instantiate
            - use
            - create
    - interfaces
    - abstract classes
- aggregation vs composition

### Sequence Diagrams

- difference between synchronous and asynchronous messages, what's the syntax
- what's: a lifeline, an activation
- what's `opt`, `alt`, `loop`?

### Activity Diagrams

- what is:
    - fork
    - join
    - merge node
    - decision node
    - swim-lane
- syntax for conditions
- syntax for concurrency
- difference between state, sequence and activity diagrams

### State Diagrams

- what are state diagrams used for
- what is:
    - pseudo-state
    - sub-state

## Software Testing

-   What is "QA"? How does it relate to software testing?
-   Levels of testing, differences and relationships between them (unit, component, integration, system)
-   Discuss the choice of Inputs for unit tests (random, equivalence partition testing, boundary value analysis).
-   Explain the difference between an error, a fault and a failure.
-   Give 6 main desirable characteristics of a software system.
-   What are the goals of testing.
-   Compare and contrast "defect" and "validation testing".
-   Compare and contrast "static" and "dynamic verification".
-   What are the difference between formal methods of testing and other methods
    of testing. Give strengths and weaknesses of both.
-   Discuss deriving tests from specification. How do use cases fit with this?
-   Code coverage (line, function, statement, decision, path, branch -- explanation and difference between them)
-   What is "cyclomatic complexity"?
-   What are "Test doubles" (e.g. mocks, stubs and drivers)?
-   What is "dependency injection"?
-   Discuss the different methods of integration testing (top-down, bottom-up, sandwich).
-   Compare and contrast "white-box" and "black-box testing".
-   Compare and contrast "functional" and "non-functional testing".
-   What is "V & V"? How does it fit with QA?
-   What is "acceptance testing"?
-   What is "regression testing"?
-   What is "TDD"?
-   What is "MC/DC"?

## Professional Issues

### UK Law

- difference between common and criminal law
- barrister vs lawyer vs solicitor

### Organisations

- what professional organisations are relevant to Software Engineering
- BCS
- IET
- IEEE

### Data Protection Act

-   sensitive data such as beliefs, gender, race etc.
-   protects the data of the people who the data is about
-   mostly about personal data i.e. about an individual
-   relates to processing i.e.

### Computer Misuse Act

-   unauthorised access
-   unauthorised access with an intent of committing further offences or
    facilitating committing offences
-   unauthorised modification of computer material

### Patents 

- how do they work

Needs to be non-obvious, inventive, applicable to the industry you are
targeting. Some things cannot be patentable: scientific, mathematical or
software.

- How long do patents last?

Patents last 20 years. But after 18 the details of the contract become publicly
available.

It's more difficult to get patents rights in the UK compared to the US where
it's more liberal. Patents in the UK are more technical.

### Copyright

- What is Copyright?

It means you have the right to express your idea (e.g. music or film) without
anyone unauthorised selling or reproducing it. In the UK you get it
automatically.
