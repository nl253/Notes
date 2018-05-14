# Graph Searching

## Uninformed Search

### Prerequisite Code

``` {.haskell}
data Node a = Node
  { getNodeVal  :: a
  , getAdjNodes :: [Node a]
  }

graph :: Node Int
graph = (Node 0 [Node 1 [], Node 3 []])
```

### Depth-First Search

``` {.haskell}
import Data.Maybe (isJust)

depthFirst :: Eq a => Node a -> a -> Maybe a
depthFirst root@(Node rootVal adjNodes) dest =
  if rootVal == dest
    then Just rootVal
    else case filter (isJust) ((\x -> depthFirst x dest) <$> adjNodes) of
           match:matches -> match
           _             -> Nothing
```

### Depth-First (Limited) Search

Same as depth-first search but only go up to a certain level.

**This is useful when**:

-   the graph is infinite
-   we use iterative deepening

``` {.haskell}
depthFirstLimited :: (Eq a, Integral n) => Node a -> a -> n -> Maybe a
depthFirstLimited (Node rootVal adjNodes) dest depth
  | rootVal == dest = Just rootVal
  | depth == 0 = Nothing
  | otherwise =
    case filter isJust ((\x -> depthFirst x dest) <$> adjNodes) of
      match:_ -> match
      _       -> Nothing
```

### Iterative Deepening

**The Algorithm**:

1.  Iterate all paths of length \(k\), try to match all paths to destination.
    If any paths were generated, return the first match (all will have the same
    distance so the choice can be made arbitrarily).
2.  Repeat the algorithm with length of \(k + 1\). Stop repeating the
    algorithm when \(k = depthOfGraph\) (at this point we know we won't find
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

## Heuristic (Informed) Search

### Best-First Search

- extends uninformed (non-heuristic) search
- in addition

#### A\*

## Puzzle Solving

## Glossary

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
:   
    The objective of a heuristic is to produce a solution in a reasonable time frame that is good enough for solving the problem at hand. This solution may not be the best of all the solutions to this problem, or it may simply approximate the exact solution. But it is still valuable because finding it does not require a prohibitively long time.

    a heuristic function is said to be **admissible** if it never overestimates the cost of reaching the goal, i.e. the cost it estimates to reach the goal is not higher than the lowest possible cost from the current point in the path.

    may be used in situations where there are no known algorithms

    Examples of admissible heuristics:

    - straight-line distance (using polar radius a for r)
    - Manhattan block distance
