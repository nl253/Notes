# Graph Searching

## Uninformed Search

### Prerequisite Code

``` {.haskell}
data Node a = Node
  { getNodeVal  :: a
  , getAdjNodes :: [Node a]
  }

-- mock object
graph :: Node Int
graph = (Node 0 [Node 1 [], Node 3 []])
```

### Depth-First Search

- not optimal

``` {.haskell}
import Data.Maybe (isJust)

depthFirst :: Eq a => Node a -> a -> Maybe a
depthFirst root@(Node rootVal adjNodes) dest =
  if rootVal == dest
    then Just rootVal
    else case filter isJust ((\x -> depthFirst x dest) <$> adjNodes) of
           -- get the first one (it doesn't matter which since
           -- all valid solutions will be equivalent)
           match:matches -> match
           _             -> Nothing -- failed to find
```

### Depth-First (Limited) Search

Same as depth-first search but only go up to a certain level.

**When to use?**

- infinite or very large graphs
-   we use iterative deepening

``` {.haskell}
depthFirstLimited :: (Eq a, Integral n) => Node a -> a -> n -> Maybe a
depthFirstLimited (Node rootVal adjNodes) dest depth
  | rootVal == dest = Just rootVal -- success
  | depth   == 0    = Nothing      -- failed to find at this depth
  | otherwise =
    case filter isJust ((\x -> depthFirstLimited x dest (depth - 1)) <$> adjNodes) of
      -- get the first one (it doesn't matter which since
      -- all valid solutions will be equivalent)
      match:_ -> match
      _       -> Nothing -- failed to find
```

### Iterative Deepening

- the basic version is not complete but it can be made complete if we force it
  to stop when $k \gt count(nodes)$
- it does produce an optimal solution
- conceptually simple but it does require limited depth first search

It visits the nodes in the search tree in the same order as depth-first search,
but the cumulative order in which nodes are first visited is effectively
breadth-first

**Asymptotic Analysis**

The time complexity of IDDFS in a (well-balanced) tree works out to be the same
as breadth-first search, i.e. $O(b^{d}$),
where $b$ is the branching factor and $d$ is the depth of the goal.

Since iterative deepening visits states multiple times, it may *seem* wasteful,
but it turns out to be not so costly, since in a tree most of the nodes are in
the bottom level, so it does not matter much if the upper levels are visited
multiple times.

**When to use?**

- when the problem doesn't require a more sophisticated algorithm
- the graph is unweighted or all paths are of the same cost

**Advantages over other algorithms**

- simple
-   responsiveness: because early iterations use small values for \$d\$, they
    execute extremely quickly -- this allows the algorithm to supply early
    indications of the result almost immediately.

**The Algorithm**:

1.  Iterate all paths of length \(k\), try to match all paths to destination.
    If any paths were generated, return the first match (all will have the same
    distance so the choice can be made arbitrarily).
2.  Repeat the algorithm with length of \(k + 1\). Stop repeating the
    algorithm when \(k = numberOfNodes\) (at this point we know we won't find
    it -- this will work only for finite graphs).

``` {.haskell}
iterativeDeepening :: Eq a => Node a -> a -> Maybe a
iterativeDeepening root dest = iterativeDeepening' root dest 0
  where
    maxDepth :: Integral n => n
    maxDepth = getDepth root
    iterativeDeepening' :: (Eq a, Integral n) => Node a -> a -> n -> Maybe a
    iterativeDeepening' root@(Node rootVal adjNodes) dest depthLim
      | depthLim <= maxDepth =
        case depthFirstLimited root dest maxDepth of
          res@(Just _) -> res
          _ -> iterativeDeepening' root dest (depthLim + 1)
      | otherwise = Nothing
```

### Uniform Cost Search

-   repeatedly expands the paths with smallest cost
-   relies only on distance to calculate the costs
-   uses a priority queue to retrieve the path with smallest cost
-   ends when:
    -   either destination is found
    -   or all paths expanded
-   complete
-   produces an optimal solution

See [A*](#A) for the algorithm.

**When to use?**

- when you cannot come up with an "admissible" heuristic

**Advantages over other algorithms**

- heuristic is not required

## Heuristic (Informed) Search

### Best-First Search

-   extends uninformed (non-heuristic) search
-   adds to the cost the estimated heuristic value for that node
-   $cost(node) = heuristic(node) + distance(node)$
    - $heuristic$ is computed by looking at how *far away* $n$ is from the destination
    - $distance$ is computed by looking at the length of path from $start$ to $node$

#### A*

- efficient
- complete
- produces an optimal solution
- requires a heuristic function to make a valid estimation
    - the heuristic must be admissible i.e. it cannot overestimate

**When to use?**

- whenever we can come up with an admissible heuristic
- large graph -- high performance requirements

**Advantages over other algorithms**

- because of the heuristic it finds a solution faster

At each iteration of its main loop, `A*` needs to determine which of its
partial paths to expand into one or more longer paths. It does so based on an
estimate of the cost (total weight) still to go to the goal node. Specifically,
`A*` selects the path that minimizes

$$f(n) = g(n) + h(n)$$

where:

- $n$ is the last node on the path
- $g(n)$ is the cost of the path from the start node to $n$
- $h(n)$ is a heuristic that estimates the cost of the cheapest path from $n$ to the goal.

The heuristic is problem-specific. The heuristic function must be
**admissible**, meaning that it never overestimates the actual cost to get to
the nearest goal node.

**NOTE**: without the heuristic function i.e. without adding $h(n)$ to $cost$
the algorithm is essentially the same as uniform-cost search (sometimes referred
to as Dijkstra's Algorithm).


```java
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

interface Graph<E extends Comparable<E>> {
    Map<Double, Graph<E>> getAdj();
    E getVal();
}

interface Path<E extends Comparable<E>> extends Comparable<Path<E>> {

    Graph<E> getLast();

    Graph<E> getFirst();

    List<Graph<E>> getNodes();

    List<E> getVals();

    double getCost();

    void append(final Graph<E> node, final double cost);
}

final class ConcrPath<E extends Comparable<E>> implements Path<E> {

    private final List<Graph<E>> nodes = new ArrayList<>(20);
    private double cost;

    public ConcrPath(final Graph<E>... nodes) {
        Collections.addAll(this.nodes, nodes);
    }

    public ConcrPath(final Collection<Graph<E>> nodes) {
        this.nodes.addAll(nodes);
    }

    public ConcrPath(final Graph<E> start) {
        this.nodes.add(start);
    }

    @Override
    public void append(final Graph<E> node, final double cost) {
        nodes.add(node);
        this.cost += cost;
    }

    @Override
    public Graph<E> getLast() {
        return nodes.get(nodes.size() - 1);
    }

    @Override
    public Graph<E> getFirst() {
        return nodes.get(0);
    }

    @Override
    public List<Graph<E>> getNodes() {
        return Collections.unmodifiableList(nodes);
    }

    @Override
    public List<E> getVals() {
        return nodes.stream()
                    .map(Graph::getVal)
                    .collect(Collectors.toList());
    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return String.format("ConcrPath { getCost = %f, %s }",
            cost,
            nodes.stream()
                 .map(Object::toString)
                 .collect(Collectors.joining(", ")));
    }

    @Override
    public int compareTo(final Path<E> o) {
        return Double.compare(cost, o.getCost());
    }
}

public class AStar<E extends Comparable<E>> {

    private final Function<Path<E>, Double> heuristic;
    private final Predicate<Graph<E>> checkDest;
    private final Graph<E> root;

    public AStar(final Graph<E> root,
                 final Predicate<Graph<E>> isDest,
                 final Function<Path<E>, Double> heuristic) {

        assert root != null;
        assert heuristic != null;
        assert isDest != null;
        this.checkDest = isDest;
        this.root = root;
        this.heuristic = heuristic;
    }

    @SuppressWarnings("ObjectAllocationInLoop")
    public Optional<Path<E>> run() {
        final PriorityQueue<Path<E>> queue = new PriorityQueue<>(100);
        queue.add(new ConcrPath<>(root));
        while (!queue.isEmpty()) {
            final Path<E> path = queue.poll();
            if (checkDest.test(path.getLast())) return Optional.of(path);
            else path.getLast()
                .getAdj()
                .entrySet()
                .stream()
                .map((Entry<Double, Graph<E>> entry) -> {
                    final ConcrPath<E> p = new ConcrPath<>(path.getNodes());
                    p.append(entry.getValue(), path.getCost() + entry.getKey() + heuristic.apply(p));
                    return p;
                }).forEach(queue::add);
        }
        return Optional.empty();
    }
}
```

## Puzzle Solving

-   the problem of solving a puzzle can be reformulated as a problem of
    searching through a graph in which nodes correspond to possible configurations
    -   identify the states (nodes)
    -   decide how the represent the states (nodes) e.g. in the missionary game
        `Left: MMCCCMM <Boat: MMC> Right: CCCCC`
    -   identify the final state (destination(s) i.e. the leaves)
    -   identify the initial state ($q_0$)
    -   create a `Collection<State<E>> nextConfigs(State<E> state)`{.java} function
        -   needs to generate adjacent nodes to `state`
    -   create a `boolean isValidState(State<E> state)`{.java} predicate that will help to
        filter out invalid configurations
        -   e.g. in the boat-missionary-cannibal game we might have a rule that
            on the left side of the river there must be more missionaries than
            cannibals (this would remove the invalid configuration)
        -   `nextConfigs(focusState).stream().filter(isValidState)`{.java} will
            give a subset of all possible configurations such that the rules of
            the puzzle aren't broken
        -   this and the filter function are dependent on the puzzle

**NOTE**: be careful with puzzles that have a high **branching factor**. I.e.
high number of "child-states" generated for every state when fed into
`nextConfigs(state)`. This can quickly start taking up a lot of resources.

### River Crossing Puzzle

-   $n$ **missionaries** and $n$ **cannibals** on one side of river
-   on the same side of the river there is a **boat** that holds $c$ people
-   find *the most efficient way* of moving everyone to the other side *without*
    leaving the missionaries on either side outnumbered by cannibals
-   it turns out that it's sufficient to only represent 1 side
    -   if on the left side we have the boat, then it follows that it's not on the right side
    -   if on the left side we have $k$ missionaries, then it follows that on the right side there is $n - k$ missionaries
    -   if on the left side we have $j$ cannibals, then it follows that on the right side there is $n - j$ cannibals
-   States
    -   Representation of States `<K, J, B>` where:
        -   $K$ is the number of missionaries on the left side
        -   $J$ is the number of cannibals on the left side
        -    $B$ is $true$ if the boat is on the left side of the river otherwise it's $false$
    -   Start State ($q_0$ = `<N, N, true>`)
    -   Final State `<0, 0, false>`

## Games

### Minimax Theorem

Minimax Theorem. The fundamental theorem of game theory which states that every
finite, zero-sum, two-person game has optimal mixed strategies.

**NOTE**: In chess there's 32 pieces in total (16 on both sides).

### Alpha-Beta Prunning

- reduces the search space by excluding it's parts
    -  important observations:
        - when another child is known \(\alpha\)-values in Max never decrease
        - when another child is known \(\beta\)-values in Min never decrease
- sometimes descending to the bottom of the tree is not possible
    - you descend up to, say, the 4^th^ ply
    - calculate heuristic of, say, 2 and propagate up the tree

## Glossary

Ply

:   (in game theory) the number of levels at which branching occurs in a tree
    of possible outcomes, typically corresponding to the number of moves ahead
    (in chess strictly half-moves ahead) considered by a computer program.

:   a half-move (i.e. one player's move) in computer chess.

Completeness

:   In the context of algorithms, completeness is a quality of algorithms when
    they either produce a result or say that there is none. In other words,
    they always terminate i.e. they will not loop forever if there is no answer
    to the problem.

    An algorithm that does not have this property is said to be **incomplete**.

Optimal

:   An algorithm is said to be optimal if it always finds the most efficient
    solution. In the context of graph-searching algorithms, an optimal
    algorithm is one that always finds the shortest path.

    An example of such an algorithm is iterative deepening which always
    explores paths of length \(k\) before exploring paths of length \( k + 1
    \). This guarantees that whenever we find a path from A to B it is always
    the shortest one (because we couldn't find it in the set of paths of length
    \(k - 1\) ).

    An algorithm that does not have this property is said to be
    **sub-optimal**.

Heuristic

:   The objective of a heuristic is to produce a solution in a reasonable time frame that is good enough for solving the problem at hand. This solution may not be the best of all the solutions to this problem, or it may simply approximate the exact solution. But it is still valuable because finding it does not require a prohibitively long time.

    a heuristic function is said to be **admissible** if it never overestimates the cost of reaching the goal, i.e. the cost it estimates to reach the goal is not higher than the lowest possible cost from the current point in the path.

    may be used in situations where there are no known algorithms

    Examples of admissible heuristics:

    - straight-line distance (using polar radius a for r)
    - Manhattan block distance
