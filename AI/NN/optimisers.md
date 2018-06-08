# Optimisers

Optimisation algorithms help us to minimise an objective function (aka
the error function) $E$.

Optimization Algorithm falls in 2 major categories.

## First-Order

Minimise or maximise a loss function $E$ using it's gradient values with
respect to the parameters. Most widely used algorithm of this kind is
Gradient Descent.

### Gradient

**Gradient** is a vector (sequence of number e.g. $[\ 3,\ 4,\ 1,\ 4\ ]$)
which a multi-variable generalisation of derivative $\frac{dy}{dx}$.

Derivative is often described as:

-   instantaneous rate of change
-   measure of sensitivity of the outputs $y$ i.e. $f(x)$ to the inputs
    $x$

Gradient replaces derivative when we enter the world of multi-variable
function. E.g. $f(w,\ x,\ y,\ z)$. Gradients are calculated using
**partial derivatives**.

Gradient produces a **vector field**.

Gradient is represented by a **Jacobian Matrix** (matrix consisting of
first order partial derivatives).

## Second-Order

Second order methods use the second order derivative which is also
called **Hessian** to minimise or maximise the **loss function**. The
**Hessian Matrix** is a matrix of second-order partial derivatives.

-   It's costly to compute and hence not used much.
-   It tells if the firs derivative is increasing or decreasing which
    hints at the function curvature
-   They provide us with a quadratic surface which touches the curvature
    of the error surface.

## 1^st^ vs 2^nd^ Order

  First Order                        Second Order
  ---------------------------------- -------------------------------------
  Easy to compute                    Costly to compute (time and memory)
  Converge quick on large datasets   --

## Adam

## Gradient Descent

-   most important
-   foundational technique
-   $parameter := parameter - \alpha \cdot \nabla E$
-   guaranteed to converge to the global minimum for convex error
    surfaces and to a local minimum for non-convex surfaces.
-   The traditional Batch Gradient Descent will calculate the gradient
    of the whole Data set but will perform only one update , hence it
    can be very slow and hard to control for datasets which are very
    very large and don't fit in the Memory

## Stochastic Gradient Descent (SGD)

Performs a parameter update for each training example .It is usually
much faster technique (It performs one update at a time)

Now due to these frequent updates, parameters updates have high variance
and causes the loss function to fluctuate to different intensities. This
is actually a good thing because it helps us discover new and possibly
better local minima, whereas Standard Gradient Descent will only
converge to the minimum of the basin as mentioned above.

But the problem with SGD is that due to the frequent updates and
fluctuations it ultimately complicates the convergence to the exact
minimum and will keep overshooting due to the frequent fluctuations .

## Mini Batch Gradient Descent

An improvement to avoid all the problems and demerits of SGD and
standard Gradient Descent would be to use **Mini Batch Gradient
Descent** as it takes the best of both techniques and performs an update
for every batch with $n$ training examples in each batch.

The advantages of using Mini Batch Gradient Descent are :

-   It Reduces the variance in the parameter updates , which can
    ultimately lead us to a much better and stable convergence.
-   Can make use of highly optimized matrix optimizations common to
    state-of-the-art deep learning libraries that make computing the
    gradient w.r.t. a mini-batch very efficient.
-   Commonly Mini-batch sizes Range from 50 to 256, but can vary as per
    the application and problem being solved.
-   **Mini-batch gradient descent is typically the algorithm of choice
    when training a neural network nowadays**.

P. S. Actually the term SGD is used also when mini-batch gradient
descent is used.
