# CO518 Excercises, Floyd-Warshall

Download the file Graph.java. It allows to generate an (empty) directed
graph with a designated number of vertices, to which you can then add
either specific edges, or an amount of random edges. It also implements
the Floyd-Warshall algorithm the code looks slightly different from the
lectures, because it specifically considers and exploits INFINITY -
which will update the edge matrix accordingly, computing the costs of
the shortest paths.

What the code does not at the moment is to actually compute the shortest
paths themselves. Have a look at how this website does it. It maintains
2 such 2-dimensional arrays, one for the shortest distances, and one
that represents the paths.

Modify the implementation accordingly and implement the method path,
which currently has a dummy implementation. Given two nodes, say 3 and
1, path(3,1) should return something like "3 6 12 1" if that describes a
path from 3 to 1 in that graph.

## Challenge exercise

Try to write an alternative implementation of the path method in which
only the shortest distance matrix is used to compute the path.
