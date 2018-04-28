# TODO

## Current

-   Amazon
    -   laptop case
-   talk to grandparents
    -   probably write a letter + thank for the money
-   wait for Simon's reply
-   `Writer`, `Reader` and `State` monads
-   possible uses of `MonadPlus` and `Alternative`

## Revision

### Algorithms, Correctness and Efficiency

-   ~~stack frames~~

#### Big O notation

-   differences between types of asymptotic notations
-   ~~**estimation of runtime complexity - see the lecture on Moodle**~~
-   ~~**algebraic manipulation of O notation**~~

#### Graph Theory

-   Definitions
    -   trail
    -   walk
    -   Travelling Salesman Problem
    -   path
    -   strongly connected component
    -   Eulerian graph
    -   simple graph
    -   ~~tree~~
    -   ~~graph~~
    -   ~~planar graph~~
-   Algorithms
    -   Dijkstra's
    -   ~~breadth-first search~~
    -   ~~depth-first search~~
    -   ~~**Floyd-Warshall's**~~
-   **encoding**
    -   object
    -   set
    -   ~~matrix~~
    -   ~~adjacency list~~
-   ~~Minimum Spanning Tree algorithms~~
    -   ~~**Kruskal's**~~
    -   ~~Prim's~~

##### Search Algorithms

-   **binary search**

#### Data Structures

-   b-tree
-   priority queues
    -   heap-based
    -   array-based
-   ~~**union-find**~~
-   ~~hashes~~
    -   ~~dictionary~~
    -   ~~**hashtable**~~
-   ~~**linked lists**~~
-   ~~stacks~~
-   ~~queues~~

#### Sorting Algorithms

-   ~~**insertion sort**~~
-   ~~bubble sort~~
-   ~~**merge sort**~~
-   ~~**quicksort**~~

#### Other

-   run length encoding
-   complexity hierarchies

### Database Systems

#### Relational Model

-   concepts and definitions
    -   constraints
    -   ~~attribute~~
    -   ~~entity integrity~~
    -   ~~referential integrity~~
    -   ~~relation~~
    -   ~~NULL~~
    -   ~~domain~~
    -   ~~primary, composite, candidate, foreign keys~~
    -   ~~relational vs sql vocabulary~~
    -   ~~tuple~~

#### Modelling

-   physical
-   issues
    -   fan-in
    -   fan-out
-   ~~relations~~
    -   ~~total~~
    -   ~~partial~~
-   ~~conceptual~~
-   ~~logical~~

#### Relational Algebra

-   translate from SQL to relational algebra
-   translate from relational algebra to SQL
-   set operations
    -   difference
    -   union
    -   cartesian product
-   selection
-   rename
-   projection
-   joins
    -   equijoin
    -   semijoin
    -   ~~natural join~~
-   ~~vocabulary (eg relation vs table, tuple vs row)~~

#### SQL

-   ~~casting~~
-   nested queries
-   aggregate queries
    -   ~~`GROUP BY`~~
    -   ~~`HAVING`~~
-   creating a table
    -   adding constraints
        -   ~~foreign key~~
        -   ~~(composite) primary key~~

#### Normalisation

#### ACID

-   ~~atomicity~~
-   ~~consistency~~
-   ~~isolation~~
-   ~~durability~~

#### Concurrency

-   pessimistic approaches
    -   timestamps (advantages and disadvantages)
    -   locking (advantages and disadvantages)
-   optimistic approaches
-   what is and how to deal with:
    -   dirty read
    -   dirty write
    -   livelock
    -   ~~deadlock~~
-   ~~transactions~~
-   ~~concepts~~
    -   ~~serial schedule (advantages and disadvantages)~~
    -   ~~serialisable~~

#### Performance on the physical level

-   indexing
-   hashing
-   storage
-   B+-Trees
-   file structure
    -   heap
    -   sequential
    -   hash
    -   clustered

#### Normalisation

### Theory of Computing

#### Automata and Regular Expressions

-   ~~**translate NFA to DFA**~~
-   ~~**the pumping lemma for regular languages**~~
-   ~~regex~~
-   ~~translate regex to automata~~
-   ~~translate automata to regex~~

#### Parsing and Context-Free Grammars

-   operations
    -   set operations
        -   union
    -   concatenation
    -   substitution
        -   intersection with a regular language
    -   Kleene star
-   the pumping lemma for context-free languages
-   CYK parsing algorithm
-   ~~recursive descent parser~~
    -   ~~first sets~~
    -   ~~checks for nullability~~
    -   ~~follow sets~~
    -   ~~lookahead sets~~
-   ~~Chomsky Normal Form~~

#### Turing Machines

-   ~~configurations~~

#### Lambda Calculus

-   ~~alpha equivalence and alpha conversion~~
-   ~~beta reduction~~
-   encoding
    -   natural numbers
    -   boolean functions

#### Logic

-   modelling systems
-   "model implies specification"
-   definitions
    -   ~~validity~~
    -   ~~satisfiability~~
    -   ~~SAT solver~~
    -   ~~SAT problem~~
    -   ~~undecidability (of predicate logic)~~
    -   ~~tautology~~
    -   ~~contradiciton~~
    -   ~~contradiciton~~
-   properties of boolean algebra
    -   ~~duality of existential and universal quantifiers~~
    -   ~~idempotency~~
    -   ~~annihilation~~
    -   ~~identity~~
    -   ~~absorption~~
    -   ~~deMorgan's laws~~
    -   ~~principle of explosion~~
-   ~~**DPLL**~~
    -   ~~tautology elimination~~
    -   ~~unit propagation~~
    -   ~~pure literal elimination~~
    -   ~~splitting~~

### Introduction to Intelligent Systems

-   graph searching algorithms
    -   uniform cost search
    -   heuristic search and A\* algorithm
    -   ~~breadth first search~~
    -   ~~limited depth first search~~
    -   ~~depth first search~~
    -   ~~iterative deepening~~
-   neural networks
    -   perceptron
    -   backpropagation
-   puzzle solving
-   minimax and alpha-beta pruning
-   program synthesis

### Software Engineering

-   models (approaches)
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

#### Testing

-   levels (unit, component, integration, system)
-   inputs (random, equivalence partition testing, boundary value analysis)
-   deriving tests from specification
-   code coverage (line, function, statement)
-   Test doubles (e.g. mocks, stubs and drivers)
-   Dependency injection
-   methods of integration testing (top-down, bottom-up, sandwich)
-   ~~white-box vs black-box~~
-   ~~functional vs non-functional~~
-   ~~V & V~~
-   ~~acceptance testing~~
-   ~~TDD~~

#### Design Patterns

-   anti patterns
-   code smell
-   composite
-   decorator
-   factory method
-   visitor
-   ~~MVC~~
-   ~~builder~~
-   ~~command~~
-   ~~facade~~
-   ~~observer~~
-   ~~singleton~~
-   ~~strategy~~

#### UML

-   ~~class diagrams~~
-   ~~sequence diagrams~~
-   ~~activity diagrams~~
-   ~~state diagrams~~
-   ~~use case diagrams~~
-   OCL

#### Professional Issues

-   organisations
    -   certifications
    -   IET
    -   BSC
    -   BSC Code of Conduct
-   law in the UK (common vs civil)
    -   Computer Misuse Act
    -   Data Protection Act
    -   types of lawyers
    -   ~~patents~~

### Functional and Concurrent Programming

#### Erlang

##### Concurrency

-   OTP
-   servers
-   consumer vs producer
-   sequential access
-   topologies
    -   ring
    -   ~~star~~
-   ~~pipelines~~
-   ~~linking processes~~
-   ~~spawning processes~~

##### Functional Programming

-   ~~pattern matching~~
-   ~~variables~~
-   ~~functions~~
-   ~~recursion~~

#### Haskell

-   ~~monads~~
    -   ~~maybe~~
    -   ~~either~~
-   ~~data declarations~~
    -   ~~binary trees~~
-   ~~currying~~
-   ~~function signatures~~
-   ~~pattern matching~~
-   ~~if statment~~
-   ~~functions~~
-   ~~recursion~~
-   ~~let clause~~
-   ~~where clause~~

### Web Development

-   HTTP
    -   explain how HTTP requests are sent and processed
    -   request (explain every line)
        -   `GET /people/staff/iau/x.txt HTTP/1.1`
        -   `Host: www.cs.kent.ac.uk`
        -   `User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536 ...`
        -   `Accept: text/html;q=0.9,text/plain;q=0.8`
        -   `Accept-Charset: ISO-8859-1,UTF-8;q=0.7,*;q=0.7`
        -   `Accept-Language: fr,en;q=0.7,en-us;q=0.3`
        -   `Referer: http://cs.kent.ac.uk/people/staff/iau/index.html`
    -   response
        -   `Status` -- including HTTP status codes
        -   `Date`
        -   `Server`
        -   `Last-Modified`
        -   `Content-Length`
        -   `Content-Type`
-   Web Servers
-   serving dynamic content
    -   CGI scripts
-   Apache
    -   workers
    -   multiple hosts
    -   configuration
    -   command line
    -   virtual host
    -   access control

#### CodeIgniter

-   MVC (explain and evaluate)
    -   Models
    -   Views
    -   Controllers
    -   CRUD
-   Sessions
-   Caching
-   Autoloading
-   Profiling
-   Helpers
    -   URL
    -   Form
-   Controllers
    -   file name, naming convention
    -   ~~why and where call parent constructor?~~
    -   ~~non-servable (private) methods~~
    -   ~~how is routing implemented in CI?~~
-   Models
    -   file name, naming convention
    -   how to connect to the database
    -   running SQL queries (including parametrised)
    -   retrieving data from SQL responses
-   Views
    -   file name, naming convention
    -   passing data into views
    -   how to load a view
-   Configuration
    -   `base_url`
    -   `db`
-   URL segments
-   Errors
    -   Parse
    -   Fatal

#### Android

-   Android GUIs
-   Activity
-   `ViewGroup` and View
-   `Bundle` and `InstanceSave`
    -   role
    -   callbacks
        -   `void onSaveInstanceState(Bundle)`
        -   `void onRestoreInstanceState(Bundle)`
        -   `onCreate()`
-   Widgets
    -   `Button`
    -   `RadioButton`
    -   `ImageButton`
    -   `CheckBox`
    -   `TextView` (single or multi-line)
-   Layouts (description and configuration)
    -   `LinearLayouts`
    -   `TableLayout`
    -   `RelativeLayout`
-   XML
    -   `@color`
    -   `@string`
    -   `@+id`
-   Directories (resources)
-   Mobile Web