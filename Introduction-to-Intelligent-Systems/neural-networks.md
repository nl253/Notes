# Neurac>a**hbi**lq Networks

## Introduction

-   neural networks are biologically inspired
-   86 billion neural networks in the human brain
-   regions of the human brain are somewhat specialised

### Applications

**Pattern cognition**:

-   early detection of breast cancer
-   identifying lame cattle

**Computer vision**:

-   separating signals of different origin in sky maps
-   recognizing letters and reading signs

**Anomaly detection**:

-   network management
-   malware analysis and classification

### Biological Neuron

**Main components of a biological neuron**:

-   axon
-   axon terminal
-   dendrite
-   myelin sheath
-   necleus
-   node of ranvier
-   schwann cell

### Artificial Neuron

-   takes **inputs values** \( x_1, x_2, x_3, \dots x_n \)
    -   input could be anything e.g. sensors on a robot, pixel values or vital
        signs of patient
-   feeds them into a **function** that takes a **vector** as input
-   combines then into a **single output**

E.g.:

-   every input $ x_1, x_2, x_3, \dots x_n $ is multiplied by **weight**
    $ W_{1,\ j}, W_{2,\ j}, W_{3,\ j}, \dots W_{n,\ j} $
-   we take the sum of  those products and feed it into some function

\[ F( \sum_{i=1}^{n} (x_i W_{i, j})) \]

Where $F$ could be e.g. Log-Sigmoid (LS) function.

#### Log-Sigmoid Activation Function

$$LS(n) = \frac{1}{1 + e^{-n}}$$

#### Heaviside Activation Function

Another common activation function is the Heaviside (H) step function.

```haskell
H x  = if  x  < 0 then 0 else 1
```

**NOTE** the output of H is always either 0 or 1 i.e. discrete and suitable for
yes-or-no decision problems.

#### Notation

The cumbersome $ W_{1,\ j}, W_{2,\ j}, W_{3,\ j}, \dots W_{n,\ j} $
notation can be replaced with **w** (the sequence of numbers is treated as
a vector). A dot $\cdot$ denotes dot product.

Eg. $ \boldsymbol{w} \cdot \boldsymbol{q} $ is the sum of products of every
nth elements of vector **w** and **q**.

## Perceptron

-   simplest form of neural networks
-   single-layered
-   2 inputs and optionally a bias
-   **linear classifier**
-   therefore it will never get to the state with all the input vectors
    classified correctly if the training set D is not **linearly separable**,
    -   i.e. if the positive examples cannot be separated from the negative
        examples by a hyperplane
    -   no "approximate" solution will be gradually approached under the
        standard learning algorithm -- **learning will fail**

**NOTE**: addition of vectors occurs element-wise.


### The Perceptron Learning Rule

If $H(\boldsymbol{w} \cdot \boldsymbol{p} + b) = 0$ but you expected $1$

then $\boldsymbol{w} = \boldsymbol{w} + \boldsymbol{p},\ b = b + 1$

else if $H(\boldsymbol{w} \cdot \boldsymbol{p} + b) = 1$ but you expected $0$

then $\boldsymbol{w} = \boldsymbol{w} - \boldsymbol{p},\ b = b - 1$

### Detecting Dogs

Suppose there is a linear relationship between size and domestication such
that:

``` {.haskell}
domestication size
  | size == 0 = 1
  | size == 95 = 0
```

## Backpropagation

See <https://www.wikiwand.com/en/Backpropagation>.
