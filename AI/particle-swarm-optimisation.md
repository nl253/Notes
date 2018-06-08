# Particle Swarm Optimisation

``` python
from copy import deepcopy
from typing import Optional
import numpy as np

def pso(
    # the no particles should be low (20 to 40)
    nparticles=30,
    # no values that each particles has (e.g.: in 2d space it's 2 for the x and y coordinate)
    ndim=3,
    # lower bound of the search space
    b_low=(-100.0),
    # upper bound of the search space
    b_high=100.0,
    # how long to look for  a solution
    max_iters=20,
    # takes ndim-dimensional ndarray and returns a number
    cost_f=np.sum,
    # to initialise particle positions
    init='uniform',
    # rate of change of velocity
    gamma=2.0,
    # rate of change of velocity
    phi_p=2.0,
    # rate of change of velocity
    phi_g=2.0,
) -> Optional[float]:

    def rand_bit() -> float:
        return float(np.random.randint(0, 2))

    # middle of search space
    middle: float = ((b_high - b_low) / 2) + b_low

    # single *best* config
    # start in the middle of every dimension
    g_best = np.full(shape=ndim, fill_value=middle, dtype='uint8')

    # init veclocity to 0 for all dimensions
    velocity = np.zeros(shape=(nparticles, ndim), dtype='float32')

    # distribute particles evenly across the search space
    if init.startswith('uni'):
        particles: np.ndarray = np.random.uniform(
            b_low,
            b_high,
            size=(nparticles, ndim),
        )
    elif init.startswith('norm') or init.startswith('gauss'):
        particles: np.ndarray = np.random.normal(
            loci=middle, size=(nparticles, ndim)
        )
    else:
        raise Exception(f'unrecognised init function {init}')

    p_best = deepcopy(particles)

    for pos in p_best:
        if cost_f(pos) < cost_f(g_best):
            g_best = pos

    # count iterations to ensure it halts
    iters = 0

    while iters != max_iters:
        for p in range(nparticles):
            for d in range(ndim):
                # the further away you are from the swarm pos then faster you move
                velocity[p][d] = gamma * velocity[p][d] + \
                    phi_p * rand_bit() * (p_best[p][d] - particles[p][d]) + \
                    phi_g * rand_bit() * (g_best[d] - particles[p][d])

            particles[p] += velocity[p]

            if cost_f(particles[p]) < cost_f(p_best[p]):
                p_best[p][d] = particles[p][d]

        iters += 1

    return g_best
```

## References

- <https://www.wikiwand.com/en/Particle_swarm_optimization>
