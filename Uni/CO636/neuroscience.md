# Neuroscience

## TODO

- emergent
- biological neurons
    - structure
        - [ ] dendrite
        - [ ] synapse
        - [ ] synaptic cleft
        - [ ] extra-cellular space
    - workings
        - [ ] channels
        - [ ] equilibria (including thresholds)
            - [ ] membrane
            - [ ] channel
                - [ ] leak
                - [ ] excitatory
                - [ ] inhibitory
        - [ ] neurotransmitters
        - [ ] Ohm's law
        - [ ] current, potential
        - [ ] resistance & conductivity

## Introduction

-   neural networks will be harder to train if the inputs are similar
-   a weight of 0 means that the neuron will ignore the input
-   a negative weight means that the neuron will not only ignore the
    input but will choose those patterns that don't cause this
    activation (i.e. the neuron wants that feature to be off)
-   neurons won't fire unless they go through some threshold ($\theta$)
    -- it acts as a barrier
-   once a neuron has fired it will temporarily go below the resting
    potential -- as a result it cannot fire *right after*

The Jennefer Anniston neuron is at the end of the visual processing
pathway. This is because it requires the detection of basic shapes,
light etc. and many other, more sophisticated and fine features.

## Pre-Synaptic vs Post-Synaptic

Relative to a synapse -- a neuron can be either pre-synaptic if it comes
before or post- if it comes after.

## Ohm's Law

$$I = GV$$

<div style="text-align: center;"> or equivalently: </div>

$$I = \frac{V}{R}$$

**Where**:

-   $I$ is the flow of current
-   $G$ is conductance
-   $V$ is electrical potential

$R$ and $G$ in this relation are constants, independent of the current.

## Ions

  Ion       Denoted  Channel Equilibrium Potential
  --------- -------- -----------------------------
  Potassium $K^{+}$  $-70mV$
  Sodium    $Na^{+}$ $+55mV$
  Chloride  $Cl^{-}$ $-70mV$

## Equilibrium Channel Potential (equilibrium driving potential, equilibrium reverse potential)

An equilibrium channel potential for some channel $c$ is when there is
no current flowing through $c$. It's the electric potential required to
counteract diffusion. Remember that the diffusion force is trying to push out
and the electrical force is trying to pull in.

$$I = G(V - E)$$

**Where**:

-   $E$ is the equilibrium potential, is a constant

**Note**:

-   this equation is derived from the Ohm's Law ($V - E$ makes the
    equation also take into account diffusion -- it's the *net* voltage)
-   $V = E$ when in equilibrium (i.e. no current flowing) (amount of
    electrical potential needed to counteract diffusion)
-   when $V$ is above $E$, you get flow
-   when $E$ is above $V$, you get flow in the reverse direction
-   **each channel has it's own equilibrium potential**

For some channel $c$:

$$I_{c}(t) = g_c(t)\bar{g_c}(V_m(t) - E_c)$$

**Where**:

-   $g_c$ is the degree to which channel $c$ is open
-   $\bar{g_c}$ is the maximum conductance
-   $V_m$ is the membrane potential
-   $E_c$ is the equilibrium potential for channel $c$

**E.g.**:

When the membrane potential equals the leek equilibrium channel potential
then there's no flow over that channel -- it's resting.

**E.g.**:

There is no flow over the leak channel when $V_m = -70mV$ because then 
$V_m = E_l$ so $V - E = 0$ so: 

$$I_{l}(t) = g_c(t)\bar{g_c} * 0 = 0$$

**E.g. of excitation**:

We excite a neuron in resting state, i.e. we drive the membrane
potential up to $-55mv$ and then turn the excitation off i.e. we open
the excitatory $Na$ channel and let in $Na^{+}$ ions. Then we close it.
The leak is going to bring it down to $-70mV$ (resting equilibrium
potential) because $I_{l} = G(-55mV - (-70mV)) = G * 15mV$ (the $K$
channel will suck $K^{-}$ to the inside from outside of the neuron).

**NOTE**: a positive net current means ions flowing *into* the neuron.

## Net Current

$$I_{net} = I_{e} + I_{i} + I_{l}$$

$$I_{net} = g_e(t)\bar{g_e}(V_m - E_e) + g_i(t)\bar{g_i}(V_m - E_i) + g_l(t)\bar{g_l}(V_m - E_l)$$

**NOTE**:

-   The leak channel $l$ is **always** open, so
    $\forall t . g_l(t) = 1$.
-   $E_l = E_i = -70mV$
-   $E_e = 55mV$

## Membrane Potential

$$V_m(t + 1) = V_m(t) + dt_{vm}I_{net}$$

**Where**:

-   $dt$ is a (scalar) time constant $dt \in [0, 1]$ that governs the
    amount of change per unit
-   $I_{net}$ is *net* current

## Equilibrium Membrane Potential

-   happens when $I_{net}$ is $0mV$ i.e. there is no current (it remains in a
    **steady state**)
-   *not* the same as equilibrium channel potential
-   a classic example is the **resting potential** because for a period
    of time, the membrane potential after firing is in a stable state
-   membrane potential does not change

## Activation

-   we want the output to be bounded
-   even if the excitation grows to very large numbers, we still want an
    output between 0 and 1

## Glossary

Diffusion
:   movement of a substance from an area of high concentration of that
    substance to an area of lower concentration

Membrane potential
:   electric potential difference between the inside and outside of the
    cell

Voltage (electric potential difference, electric pressure or electric tension)
:   the difference in electric potential between two points

Action potential (spike)
:   electrical current released down axon when membrane potential
    crosses a threshold, releases neurotransmitter

Resting potential

:   -   membrane potential when no inputs
    -   steady state in absence of stimulation
    -   $-70mV$ due to $Na$/$K$ pump (electrically primed)

Leak

:   -   gets the system to go back to the baseline
    -   the leak channel is always open

Synapse
:   is the gap between neurons

Membrane
:   A thin, pliable layer of tissue covering surfaces or separating or
    connecting regions, structures, or organs of an animal or a plant.

Potassium
:   responsible for the "leak"

Extra-cellular space
:   in relative terms, effectively zero charge

Axon
:   long nerve fiber that conducts away from the cell body of the neuron

Neurotransmitter

:   activates potential via dendritic synaptic input channels.

Dendrite
:   short fiber that conducts toward the cell body of the neuron

Ion

:   -   a particle that is electrically charged (positive or negative)
    -   an atom or molecule or group that has lost or gained one or more
        electrons

Charge

-   physical property of matter that causes it to experience a force
    when placed in an electromagnetic field
-   there are two types of electric charges; positive and negative
    (commonly carried by protons and electrons respectively)
-   like charges repel and unlike attract
-   an object with an absence of net charge is referred to as neutral

Current

:   -   rate of change of charge (derivative of charge with respect to
        time)
    -   often carried by moving electrons in a wire
    -   can also be carried by ions in an electrolyte
    -   denoted with $I$

Resistance

:   -   measure of an object's opposition to the flow of electric
        current
    -   reciprocal of conductance i.e. ($G^{-1}$)
    -   a constant determined by the biological make-up of a neuron
    -   denoted with $R$

Hypo-polarised
:   very negative

Depolarised
:   moved away from the extreme

Conductance

:   -   the ease with which an electric current passes through an object
    -   a constant determined by the biological make-up of a neuron
    -   reciprocal of resistance i.e. ($R^{-1}$)

Electric Potential
:   the amount of work needed to move a unit of positive charge from a
    reference point to a specific point inside the field *without*
    producing acceleration

## References

<!-- 
vim:conceallevel=1:
-->
