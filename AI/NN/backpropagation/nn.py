#!/usr/bin/python3
import random
from math import e, log, exp
from typing import MutableSequence, Sequence, SupportsFloat, Union, Tuple, \
    Iterable
from enum import Enum, auto

Float = SupportsFloat
Vector = MutableSequence[Float]


def ident(n: Float) -> Float: return n


def relu(n: Float) -> Float: return max(0, n)


def softplus(n: Float) -> Float: return log(1 + exp(n))


def ident(n: Float) -> Float: return n


def logs(n: Float) -> Float: return 1 / (1 + e ** (-n))


class Activation(Enum):
    RELU = relu
    SOFTPLUS = softplus
    LOGS = logs
    IDENT = ident


def loss(expected: Float, actual: Float) -> Float:
    return 0.5 * (expected - actual) ** 2


def mean_loss(expected: Vector, actual: Vector) -> Float:
    assert len(expected) == len(
            actual), f"vectors {str(expected)} and {str(actual)} differ in len"
    assert len(expected) > 0, "empty vector"
    return sum(map(lambda pair: loss(*pair), zip(expected, actual))) / len(
            expected)


def dot(a: Vector, b: Vector) -> Float:
    """ Compute the dot product of vectors a and b.

    E.g.:

    >>> dot([1, 2, 3], [2, 3, 2])
    14

    :param a:
    :param b:
    :return:
    """
    assert len(a) == len(
            b), f"vectors {str(a)} and {str(b)} aren't of equal len"
    assert len(a) > 0, "empty vectors"
    result: Float = 0
    for i in range(len(a)):
        result += (a[i] * b[i])
    return result


class Neuron:

    def __init__(self, no_inputs: int = 2,
                 activation: Activation = Activation.LOGS):
        self._weights: Vector = [random.random() for i in range(no_inputs)]
        self._bias: Float = random.random()
        self._activatation = activation
        self._previous_activation: Float = None
        self._previous_input: Vector = None

    def feed(self, vector: Vector) -> Float:
        self._previous_input = vector
        f: function = ident
        x: Float = dot(self.weights, vector) + self.bias

        if self.activation == Activation.LOGS:
            f = logs
        elif self.activation == Activation.RELU:
            f = relu
        elif self.activation == Activation.SOFTPLUS:
            f = softplus

        y: Float = f(x)
        self._previous_activation = y
        return y

    @property
    def activation(self) -> Activation:
        return self._activatation

    @activation.setter
    def activation(self, new_activation: Activation) -> None:
        self._activation = new_activation

    @property
    def bias(self) -> Float:
        return self._bias

    @bias.setter
    def bias(self, new_bias: Float) -> None:
        self._bias = new_bias

    def __repr__(self) -> str:
        weights: str = ', '.join('{:.2f}'.format(w) for w in self.weights)
        bias: str = '{:.2f}'.format(self.bias)
        return f"(w: {weights}, b: {bias})"

    @property
    def weights(self) -> Vector:
        return self._weights

    @weights.setter
    def weights(self, new_weights: Vector) -> None:
        self._weights = new_weights

    @property
    def no_inputs(self) -> int:
        return len(self.weights)


Layer = Sequence[Neuron]


class NN:
    def __init__(self, n_inputs: int = 4, n_outputs: int = 1,
                 learning_rate: Float = 0.1):
        self._input_layer = [Neuron(1) for i in range(n_inputs)]
        self._hidden_layer = [Neuron(n_inputs) for i in range(n_inputs)]
        self._output_layer = [Neuron(n_inputs) for i in range(n_outputs)]
        self._learning_rate: Float = learning_rate

    def feed(self, inputs: Vector) -> Vector:
        assert len(inputs) == len(
                self.input_layer), "inputs and the input layer differ in len"
        result: Vector = []
        for i in range(len(inputs)):
            result.append(self.input_layer[i].feed([inputs[i]]))

        result2: Vector = []
        for i in range(len(self.hidden_layer)): result2.append(
                self.hidden_layer[i].feed(result))

        result3: Vector = []
        for i in range(len(self.output_layer)):
            result3.append(self.output_layer[i].feed(result2))

        # if the output layer only consits of 1 neuron, just the value in the last vector
        # otherwise return the vector
        return result3[0] if self.no_outputs == 1 else result3

    def train(self, data: Iterable[Tuple[Vector, Union[Float, Vector]]]):
        for vec, t in data:
            y: Union[Float, Vector] = self.feed(vec)
            delta: Float = t - y
            # TODO hidden layer
            # TODO input layer
            for neuron in self.output_layer:
                ls_result = logs(
                        neuron.bias + dot(neuron.weights,
                                          neuron._previous_input))
                neuron.bias -= delta * ((1 - ls_result) * ls_result)
                for i in range(len(neuron.weights)):
                    neuron.weights[i] -= delta * ((1 - ls_result) * ls_result)

        def __repr__(self) -> str:
            input_l: str = str(self.input_layer[
                                   0] if self.no_inputs == 1 else self._input_layer)
            hidden_l: str = str(self.hidden_layer[0] if len(
                self.hidden_layer) == 1 else self.hidden_layer)
            output_l: str = str(self.output_layer[
                                    0] if self.no_outputs == 1 else self.output_layer)
            return f"in -> {input_l} -> {hidden_l} -> {output_l} -> out"

        @property
        def no_inputs(self):
            return len(self.input_layer)

        @property
        def no_outputs(self):
            return len(self.output_layer)

        @property
        def input_layer(self) -> Layer:
            return self._input_layer

        @property
        def learning_rate(self) -> Float:
            return self._learning_rate

        @learning_rate.setter
        def learning_rate(self, new_learning_rate: Float) -> None:
            self._learning_rate = new_learning_rate

        @input_layer.setter
        def input_layer(self, new_layer: Layer):
            self._input_layer = new_layer

        @property
        def output_layer(self) -> Layer:
            return self._output_layer

        @output_layer.setter
        def output_layer(self, new_layer: Layer):
            self._output_layer = new_layer

        @property
        def hidden_layer(self) -> Layer:
            return self._hidden_layer

        @hidden_layer.setter
        def hidden_layer(self, new_layer: Layer):
            self._hidden_layer = new_layer

    def main() -> None:
        nn = NN(n_inputs=4, n_outputs=1)
        print(nn)
        print(nn.feed([1, 2, 3, 4]))

    if __name__ == '__main__':
        import sys

        if len(sys.argv) > 1:
            cmd: str = sys.argv[1]
            if cmd == "test" or cmd == "doctest":
                print(f"running doc tests")
                import doctest
                doctest.testmod()
            else:
                print(f"unrecognised command `{cmd}`")
        else:
            main()
