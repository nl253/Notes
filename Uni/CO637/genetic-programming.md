# Genetic Programming (GP)

-   a technique whereby computer programs are encoded as a set of genes
-   the genes are modified (evolved) using an evolutionary algorithm
    (often a genetic algorithm, "GA")
-   GP starts from an ooze of random computer programs, and
    progressively refines them through processes of mutation and sexual
    recombination, until solutions emerge.
-   Genetic programming starts from a high-level statement of "what
    needs to be done" and automatically creates a computer program to
    solve the problem.

## Encoding

-   Genetic representation is a way of representing
    solutions/individuals in evolutionary computation methods.
-   Genetic representation can encode appearance, behavior, physical
    qualities of individuals.
-   Designing a good genetic representation that is expressive and
    evolvable is a hard problem in evolutionary computation.

**Common genetic representations**:

-   binary array
-   binary tree
-   natural language
-   parse tree
-   directed graph

## Selection

-   Certain individuals are selected from the current generation that
    would serve as parents for the next generation.
-   Better performing individuals have a higher chance of getting
    selected.
-   The most commonly used selection method in GP is **Tournament
    selection**, although other methods such as **Fitness proportionate
    selection**, **Lexicase Selection**, and many others have been
    demonstrated to perform better for many GP problems.

### Tournament Selection

-   Tournament selection is a method of selecting an individual from a
    population of individuals in a genetic algorithm.
-   Tournament selection involves running several "tournaments" among a
    few individuals (or "chromosomes") chosen at random from the
    population.
-   The winner of each tournament (the one with the best fitness) is
    selected for **crossover**.

### Crossover

Crossover, also called recombination, is a genetic operator used to
combine the genetic information of two parents to generate new
offspring.

## Applications

-   timetabling
-   scheduling

## Example

``` python
#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# Standard Library
import logging
from logging import Logger
from math import log
from typing import Callable

# 3rd Party
import numpy as np

np.set_printoptions(precision=2, linewidth=90)

# initialise logging with sane configuration
logging.basicConfig(level=logging.DEBUG, format="[%(levelname)s] %(message)s")

l: Logger = logging.getLogger()


def genetic_algorithm(
    gene_pool: np.ndarray,
    compete: Callable[[np.ndarray, np.ndarray], np.ndarray],
    n=(2**10)
) -> np.ndarray:
    assert len(gene_pool) > 0
    assert log(n, 2) == round(log(n, 2))
    while gene_pool.shape[0] > 1:
        l.info('[NEW ROUND] %12d candidates' % gene_pool.shape[0])
        i = 1
        k = 0
        n = gene_pool.shape[0]
        while i < n:
            gene_pool[k] = compete(gene_pool[i - 1], gene_pool[i])
            i += 2
            k += 1
        gene_pool = gene_pool[:k]
    return gene_pool[0]


if __name__ == "__main__":
    from numpy.random import triangular

    no_candidates = 1 << 22
    no_dim = 3
    candidates = triangular(
        left=0,
        mode=100,
        right=1000,
        size=(no_candidates, no_dim)
    )

    def compete(x, y): return x if (np.sum(x) < np.sum(y)) else y

    print('winner: ' + str(genetic_algorithm(gene_pool=candidates, compete=compete)))
```

## Glossary

## References
