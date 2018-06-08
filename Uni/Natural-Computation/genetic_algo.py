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

# initalise logging with sane configuration
logging.basicConfig(
    level=logging.DEBUG, format="[%(levelname)s] %(message)s"
)

l: Logger = logging.getLogger()


def genetic_algorithm(
    gene_pool: np.ndarray,
    compete: Callable[[np.ndarray, np.ndarray], np.ndarray],
    reproduce: Callable[[np.ndarray, np.ndarray], np.ndarray],
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

    def compete(x: np.ndarray, y: np.ndarray) -> np.ndarray:
        return x if (np.sum(x) < np.sum(y)) else y

    def reproduce(x: np.ndarray, y: np.ndarray) -> np.ndarray:
        from copy import deepcopy
        def mangle(a: np.ndarray) -> np.ndarray:
            a = deepcopy(a)
            idx = np.random.randint(0, a.shape[0], dtype='uint8')
            a[idx] += 1
            a[idx] *= idx
            return a
        def mangle_many(a: np.ndarray, n=4) -> np.ndarray:
            stack: np.ndarray = a
            for _ in range(1, n):
                stack = np.hstack((stack, mangle(a)))
            return stack
        return mangle_many(0.5 * x + 0.5 * y)

    print('winner: ' + str(genetic_algorithm(gene_pool=candidates, compete=compete, reproduce=reproduce)))
