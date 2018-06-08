# Notes

## Derivation

Let $N(\boldsymbol{x})$ be the neuron net with as input takes the vector $\boldsymbol{x}$ and outputs $y$.

$$N(\boldsymbol{x}) = y$$

Where value $y$ is the result of activation function $LS$ from the output (last) layer.

$$y = LS(\boldsymbol{a'} \cdot \boldsymbol{{w''}_i} + b'')$$

Error function:

$$E = \frac{1}{2} (t - y)^2$$

Where:

- ${w''}_j$ denotes the $j$^th^ $weight$ on the output layer (3^rd^
    layer)
- $y$ is the output value
- $\boldsymbol{x}$ is the input vector
- $\alpha$ is the learning rate (typically $\frac{1}{10}$ or
    $\frac{1}{100}$)

To measure the updated bias on the last layer:

$$b'' := b'' - \alpha \frac{\partial E}{\partial {b''}}$$

$$b'' := b'' - \alpha \frac{\partial \frac{1}{2} (t - LS(\boldsymbol{a'} \cdot \boldsymbol{w''} + b''))^2}{\partial b''}$$

$$b'' := b'' - \alpha \frac{\partial\ - LS(b'')}{\partial b''}$$

$$b'' := b'' + \alpha {(1 - LS(b'')) LS (b'')}$$
