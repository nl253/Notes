# Concurrency

-   measure the change of runtime as the number of threads approaches
    infinity
-   independent variable would be the `#threads`
-   unlikelty to be linear i.e. the derivative is unlikely to be a
    constant
    -   at some point (when `#threads == #CPUs` ) you will start loosing
        the benfits of concurrency (i.e. it's logarithmic)
    -   $f(t) = -(t-4)^2+9$ for a 4-core CPU where $f(t)$ is performance
    -   discrete
    -   peaks at `#threads = #CPUs` from `#CPUs + 1` performance starts
        to decrease dramatically
