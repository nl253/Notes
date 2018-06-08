# CO518 class, O-Notation

This time we are doing some exercises with determining O-notation
experimentally.. For this, you need to download and unzip the BlueJ
project SortTester.

## Testing Harness

This project contains a fair number of sorting algorithms, as well as a
general testing harness for them. In particular, the testing harness has
methods for testing the run time on random inputs.

Make yourself familiar with the testing harness: create a Tester object,
a QuickSort object, and register the latter with the former using the
registerProgram method of the Tester. By running the testA method you
can see the run times of various input sizes, in milliseconds. testB
does a similar thing, in nanoseconds (but on different input sizes);
testC in microseconds.

Note that during the first test you will get a slight anomaly due to
Java's JIT compiler. You can register more than one program at a time,
the Tester will then put the run times for both/all programs on one
line, for any particular input. Verify the expected time complexity for
BubbleSortA, by analysing the output of testA using a spreadsheet -- as
I did in a recent lecture.

## Columns

-   the input size (column A)
-   the time it takes for that input size (column B)
-   the function you expect the program to behave (column C), this is a
    function on the input value in column D: the time (from B) divided
    by the expected time (from C) optionally a column E where you scale
    column D by a constant factor, moving the numbers into a sensible
    range.

## For example

If you expect that (want to test if) a sorting algorithm is `O(n2)` and
your first data is in row 2 then you put in C2: `=A2*A2`. If you
expected that it's $O(n * log(n))$ then it's `=A2\*log(A2)`.

In D2 you put `=B2/C2`. These cells you then copy across their columns.
If you are not sure what O-notation function to expect have one column
for each likely candidate.

## Recall how to interpret these data

If the values in column D converge to 0, we have an overestimate; if
they diverge, an underestimate; if they go up and down but stay in the
same ballpark we probably have found a tight bound.

There are a few mystery sorts in the project. Pick one and try to figure
out what O-notation it has, using the same method as in question 2.

## If you want a challenge

MysterySort3 is the trickiest of the lot.

(Note: it should suffice to update the data in column B, as input sizes
and formulae stay unchanged.)

We can use the same technique (as well as algebraic manipulations) to
"simplify" growth functions.

Simplify the following the growth functions as much as possible and
validate your simplification using the spreadsheet.

$$
    O(4n * 2 + 6n) \\
    O(log_{2}{(5n + 3n * 4 + 2n})) \\
    O(2n * 2n + 1) \\
    O(2n + n * log_2{n} * 2) 
$$
