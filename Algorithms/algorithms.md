## Algorithms 

## Data Structures

### Hash Table

A hash table is a **lookup table** that uses an array in which the location of
each item is found by using a [hash function](#hash-functions).

#### Hash Functions

-   Hash function converts data into an integer
-   a good hash function is quick to calculate and creates an even spread of
    values

For example sha512:

    34ddb532d73c71e147782d5b3...  data-structures.md
    da439b3a0b2178463fda1d45b...  floyd-warshall.md
    e0af9ab8b0ba319a1e0a2376d...  runtime-complexity-estimation.md

```java
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

If the table size is the same as, or larger than, the number of entries then
average lookup takes constant time (ie O(1)).

Search time is the same however many records are stored.

Generally, there is a trade-off between table size and speed. Hash tables are
the usual way to implement a lookup table where speed is very important.

### Binary Search Tree

A binary search tree is a linked data structure where each node has links to
two other other nodes (left and right).

The entry node is called the **root node** and left leads to a subtree of
values that come before the root node. Similarly, right refers to a subtree of
nodes that come after the root node.

Between each pair of nodes is a null representing an empty subtree provides a
place to add new nodes

```java
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

```java
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

```java
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

We want a method that will work on any tree, including a tree with no nodes.
This will not work if we use an ordinary non-static method.

... unless we make the Tree class abstract, have an `EmptyTree`{.java} class too, and
use that instead of null.

The recursive search is simpler and safe for well-constructed trees since the
recursive depth is proportional to depth of the tree.

#### Inserting Nodes to BST

``` {.java}
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

#### Process (e.g. print) BST

``` {.java}
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

-   Binary search trees and hash tables provide alternative ways to implement a
    lookup-table abstract data type.
-   In the Java API, `TreeMap`{.java} and `HashMap`{.java} both implement the Map
    interface.
-   Hash tables are potentially faster. BSTs are more flexible and often easier
    can search for more than just an exact match can create of the top of your
    head if you understand the principles.

#### How efficient are binary search trees?

-   The algorithms to search and insert in a tree move one step down the tree
    at each iteration (or each recursive method call).
-   Means that the time taken is limited by the height of the tree.
-   A tree's height is the maximum distance from the root to a leaf node (that
    is, a node with no subtrees). The amount of data stored in a tree (its
    size) is the number of nodes
-   A tree in which each left subtree is null will effectively be a linked list
    and its height will be equal to its size

**NOTE**:

A tree of height $h$ can contain up to $1 + 2 + 4 + \dots + 2^{(h - 1)}$ nodes.
This is a geometric progression and is equal to $ 2^{(h - 1)} $.

### Priority Queues

Many algorithms need a data structure to store a collection of values and
allow fast access to the largest value stored.

- read largest value
- remove largest
- rapidly determine the new largest
- add a new value which may or may not be the new largest

An example is Dikstra's shortest-path algorithm but there are plenty of others.
Different implementations give different performance characteristics.

#### Naive Implementations

*Unordered List*

-   Insert -- add to end (constant time)
-   Remove -- scan list for largest and swap this with end element (linear
    time)
-   Construct -- put into array (linear time)

**Ordered List**

-   Remove -- return end element (constant)
-   Insert -- scan list to find place and move elements to preserve order
    (linear)
-   Construct -- sort (worse than linear)


#### Heaps

Binary tree such that each parent element is larger than its two children. Thus
largest element at root. (Max-Heap)
