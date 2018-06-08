# Final Revision

-   complexity hierarchies

## Big O notation

-   differences between types of asymptotic notations
-   ~~**estimation of runtime complexity - see the lecture on Moodle**~~
-   ~~**algebraic manipulation of O notation**~~

## Graph Theory

### Graph Searching Algorithms

-   Dijkstra's
-   ~~breadth-first search~~
-   ~~depth-first search~~
-   ~~**Floyd-Warshall's**~~

### Encoding Graphs

-   object
-   set
-   ~~adjacency list~~

Adjacency Matrix
:   The adjacency matrix of a graph is a matrix whose rows and columns
    are both indexed by vertices of the graph, with a one in the cell
    for row i and column j when vertices i and j are adjacent, and a
    zero otherwise

### Minimium Spanning Tree Algorithms

-   ~~**Kruskal's**~~
-   (probably won't be in the exam) ~~Prim's~~

## Algorithms

### Sorting Algorithms

-   ~~**insertion sort**~~
-   ~~**merge sort**~~
-   ~~**quicksort**~~
-   ~~bubble sort~~

### Search Algorithms

-   **binary search**

### Compression

-   run length encoding

## Data Structures

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

### Hash Table

A hash table is a **lookup table** that uses an array in which the
location of each item is found by using a [hash
function](#hash-functions).

#### Hash Functions

-   Hash function converts data into an integer
-   a good hash function is quick to calculate and creates an even
    spread of values

For example sha512:

    34ddb532d73c71e147782d5b3 ...  data-structures.md
    da439b3a0b2178463fda1d45b ...  floyd-warshall.md
    e0af9ab8b0ba319a1e0a2376d ...  runtime-complexity-estimation.md

``` java
public class HTable {
        private int size;
        private Node table[];
        public HTable(int size) {
            this.size = size;
            table = new Node[size];
            for (int n = 0; n < size; n++) table[n] = null;
        }
        private int hash(String string) {
            return Math.abs(string.hashCode()) % size;
        }
        public void add(String name, String number) {
            int pos = hash(name);
            table[pos] = new Node(name, number, table[pos]);
        }
        public String getNumber(String name) {
            int pos = hash(name);
            for (Node n = table[pos]; n != null; n = n.next)
                if (name.equals(n.name))
                    return n.number;
            return null;
        }
```

A complete table would also need methods to:

-   delete entries
-   return the number of entries

etc.

#### Hash table efficiency

If the table size is the same as, or larger than, the number of entries
then average lookup takes constant time (i.e. O(1)).

Search time is the same however many records are stored.

Generally, there is a trade-off between table size and speed. Hash
tables are the usual way to implement a lookup table where speed is
important.

### Binary Search Tree

A binary search tree is a linked data structure where each node has
links to two other nodes (left and right).

The entry node is called the **root node** and left leads to a subtree
of values that come before the root node. Similarly, right refers to a
subtree of nodes that come after the root node.

Between each pair of nodes is a null representing an empty subtree
provides a place to add new nodes

``` java
class Tree {

        // key could be anything that can be ordered
        private int key;

        private Tree left;
        private Tree right;

        // any other private fields

        // member functions and class

        // functions for manipulating trees
};
```

#### Searching a BST

``` java
// Return the node with the matching key,
// or null if not found.
static Tree search(Tree root, int key) {
    Tree node = root;
    while (node != null) {
        if (node.key == key)
            return node;
        else if (key < node.key)
            node = node.left;
        else
            node = node.right;
    }
    return null;
}
```

``` java
// Return the node with the matching key,
// null if not found.
static Tree search(Tree root, int key) {
    if (root == null)
        return null;
    if (root.key == key)
        return root;
    else if (key < root.key)
        return search(root.left, key);
    else
        return search(root.right, key);
}
```

**Why use a static method?**

We want a method that will work on any tree, including a tree with no
nodes. This will not work if we use an ordinary non-static method.

... unless we make the Tree class abstract, have an `EmptyTree`{.java} class
too, and use that instead of null.

The recursive search is simpler and safe for well-constructed trees
since the recursive depth is proportional to depth of the tree.

#### Inserting Nodes to BST

``` java
// Insert a new node in the tree and return
// the new tree
public static Tree insert(Tree tree, int key) {
    if (tree == null)
        return new Tree(key);
    if (key <= tree.key)
        tree.left = insert(tree.left, key);
    else
        tree.right = insert(tree.right, key);
    return tree;
}
// Tree node constructor
public Tree(int key) {
    this.key = key;
}
```

#### Process (e.g. print) BST

``` java
// print the keys of the tree, one per line,
// in increasing order
public static void print(Tree tree) {
    if (tree != null) {
        print(tree.left);
        System.out.println(tree.key);
        print(tree.right);
    }
}
```

#### BSTs vs Hash Tables

-   Binary search trees and hash tables provide alternative ways to
    implement a lookup-table abstract data type.
-   In the Java API, `TreeMap`{.java} and `HashMap`{.java} both implement the Map
    interface.
-   Hash tables are potentially faster. BSTs are more flexible and often
    easier can search for more than just an exact match can create of
    the top of your head if you understand the principles.

#### How efficient are binary search trees?

-   The algorithms to search and insert in a tree move one step down the
    tree at each iteration (or each recursive method call).
-   Means that the time taken is limited by the height of the tree.
-   A tree's height is the maximum distance from the root to a leaf node
    (that is, a node with no subtrees). The amount of data stored in a
    tree (its size) is the number of nodes
-   A tree in which each left subtree is null will effectively be a
    linked list and its height will be equal to its size

**NOTE**:

A tree of height (h) can contain up to $1 + 2 + 4 + \dots + 2\^{(h - 1)}$ nodes. This is a geometric progression and is equal to $2\^{(h - 1)}$.

### Priority Queues

Many algorithms need a data structure to store a collection of values
and allow fast access to the largest value stored.

-   read largest value
-   remove largest
-   rapidly determine the new largest
-   add a new value which may or may not be the new largest

An example is Dikstra's shortest-path algorithm but there are plenty of
others. Different implementations give different performance
characteristics.

#### Naive Implementations

**Unordered List**

-   Insert -- add to end (constant time)
-   Remove -- scan list for largest and swap this with end element
    (linear time)
-   Construct -- put into array (linear time)

**Ordered List**

-   Remove -- return end element (constant)
-   Insert -- scan list to find place and move elements to preserve
    order (linear)
-   Construct -- sort (worse than linear)

#### Heaps

Binary tree such that each parent element is larger than its two
children. Thus largest element at root. (Max-Heap)

## Glossary

Stack frame

:

Travelling Salesman Problem

:

Trail
:   a walk in which all the edges are distinct. A closed trail has been
    called a tour or circuit, but these are not universal, and the
    latter is often reserved for a regular subgraph of degree two.

Walk

:   a sequence of alternating vertices and edges such as
    $v\_0, e\_1, v\_1, e\_2, \dots, e\_k, v\_k$ where each edge
    $e\_i = v\_{(i − 1)}, v\_i)$. The Length of this walk is (k).

    A **walk is a trail if any edge is traversed at most once**.

    The difference between a walk and a path is that paths cannot repeat
    vertices (or, it follows, edges).

Path

:   sequence of edges which connect a sequence of vertices.

    In a directed graph, a directed path is again a sequence of edges
    which connect a sequence of vertices, but with the added restriction
    that the edges all be directed in the same direction.

    See [path](#walk) for differences between walk and path

Eulerian Path
:   a walk that uses every edge of a graph exactly once.

Eulerian Circuit
:   (also called an Eulerian cycle or an Euler tour) is a closed walk
    that uses every edge exactly once.

Regular
:   A graph is (d)-regular when all of its vertices have degree (d). A
    regular graph is a graph that is (d)-regular for some (d).

Planar
:   A planar graph is a graph that has an embedding onto the Euclidean
    plane. A plane graph is a planar graph for which a particular
    embedding has already been fixed. A (k)-planar graph is one that can
    be drawn in the plane with at most (k) crossings per edge.

Eulerian Graph

:   a graph that has an Eulerian circuit. For an undirected graph, this
    means that the graph is connected and every vertex has even degree.

    For a directed graph, this means that the graph is strongly
    connected and every vertex has in-degree equal to the out-degree. In
    some cases, the connectivity requirement is loosened, and a graph
    meeting only the degree requirements is called Eulerian.

Strongly Connected Component

:   A **directed graph** is called strongly connected if there is a
    **path in each direction between each pair of vertices** of the
    graph.

    In a directed graph (G) that may not itself be strongly connected, a
    pair of vertices (u) and (v) are said to be strongly connected to
    each other if there is a path in each direction between them.

    A graph is said to be strongly connected or diconnected if every
    vertex is reachable from every other vertex

    The strongly connected components or diconnected components of an
    arbitrary directed graph form a partition into subgraphs that are
    themselves strongly connected.

Eulerian graph

:

Simple Graph

:   A simple graph, is an unweighted, undirected graph containing no
    loops or multiple edges.

    A simple graph may be either connected or disconnected.

Tree
:   an undirected graph in which any two vertices are connected by
    exactly one path. In other words, any **acyclic connected graph is a
    tree**. A forest is a disjoint union of trees.

Forest
:   A forest is an undirected graph, all of whose connected components
    are trees; in other words, the graph consists of a disjoint union of
    trees. Equivalently, a forest is an undirected acyclic graph. As
    special cases, an empty graph, a single tree, and the discrete graph
    on a set of vertices (that is, the graph with these vertices that
    has no edges), are examples of forests. Since for every tree
    ($V - E = 1$), we can easily count the number of trees that are
    within a forest by subtracting the difference between total vertices
    and total edges. ($TV - TE = number of trees in a forest$).

Degree
:   The degree of a vertex in a graph is its number of incident edges.

Graph
:   set of objects in which some pairs of the objects are in some sense
    "related". The objects correspond to mathematical abstractions
    called vertices (also called nodes points) and each of the related
    pairs of vertices is called an edge.

Planar Graph

:   In graph theory, a planar graph is a graph that can be embedded in
    the plane, i.e., it can be drawn on the plane in such a way that its
    edges intersect only at their endpoints.

    In other words, it **can be drawn in such a way that no edges cross
    each other**.

Connected

:   A connected graph is one in which each pair of vertices forms the
    endpoints of a path. Higher forms of connectivity include strong
    connectivity in directed graphs (for each two vertices there are
    paths from one to the other in both directions),
    (k)-vertex-connected graphs (removing fewer than (k) vertices cannot
    disconnect the graph), and (k)-edge-connected graphs (removing fewer
    than (k) edges cannot disconnect the graph).

    -   A graph with just one vertex is connected.
    -   An edgeless graph with two or more vertices is disconnected.

Cycle
:   A cycle may either refer to a closed walk (also called a tour) or
    more specifically to a closed walk without repeated vertices or
    edges (a simple cycle). In either case, the choice of starting
    vertex is usually considered unimportant: cyclic permutations of the
    walk produce the same cycle. Important special cases of cycles
    include Hamiltonian cycles, induced cycles, peripheral cycles, and
    the shortest cycle, which defines the girth of a graph. A (k)-cycle
    is a cycle of length (k); for instance a (2)-cycle is a digon and a
    (3)-cycle is a triangle. A cycle graph is a graph that is itself a
    simple cycle; a cycle graph with (n) vertices is commonly denoted
    (C\_n). The cycle space is a vector space generated by simple cycles
    in a graph.
