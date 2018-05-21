import java.security.SecureRandom;
import java.text.MessageFormat;
import java.util.Random;

public class Graph {

    private static final int INFINITY = 0;
    // add a certain amount of random edges to the graph with a weight between 1 and 1000
    private static final Random RANDOM = new SecureRandom();
    // instance variables - replace the example below with your own
    private final int order;
    private final int[][] matrix;

    /**
     * Constructor for objects of class Graph
     */
    public Graph(final int order) {
        // initialise instance variables
        this.order = order;
        //possibly more initialisation
        matrix = new int[order][order];
    }

    public void addDirectedEdge(final int from, final int to, final int weight) {
        if ((matrix[from][to] == INFINITY) || (matrix[from][to] > weight))
            matrix[from][to] = weight;
    }

    public void addRandomEdges(int number) {
        while (number > 0) {
            final int from = RANDOM.nextInt(order);
            final int to = RANDOM.nextInt(order);

            if ((from != to) && (matrix[from][to] == INFINITY)) {
                final int weight = RANDOM.nextInt(1000) + 1;
                matrix[from][to] = weight;
                number--;
            }
        }
    }

    public Integer edgeWeight(final int from, final int to) {
        return (matrix[from][to] == INFINITY) ? null : matrix[from][to];
    }

    public void FloydWarshall() {
        for (int k = 0; k < order; k++)
            for (int i = 0; i < order; i++)
                if (matrix[i][k] != INFINITY) for (int j = 0; j < order; j++)
                    if (matrix[k][j] != INFINITY) {
                        final int sum = matrix[i][k] + matrix[k][j];

                        if ((matrix[i][j] == INFINITY) || (matrix[i][j] > sum))
                            matrix[i][j] = sum;
                    }
    }

    public void printShortestPath(final int from, final int to) {

        if (matrix[from][to] == INFINITY) {
            if (from == to)
                System.out.println(MessageFormat.format("empty path from {0} to itself, and no other way.", from));
            else
                System.out.println(MessageFormat.format("no connection from {0} to {1}.", from, to));
            return;
        }

        if (from == to)
            System.out.print(MessageFormat.format("Beside the empty path the cheapest way to get from {0} back to itself with cost {1} is: ", from, matrix[to][to]));
        else
            System.out.print(MessageFormat.format("We can get from {0} to {1} with cost {2} as follows: ", from, to, matrix[from][to]));

        System.out.println(path(from, to));

    }

    public String path(final int from, final int to) {
        return "this string should be replaced with the sequence of vertices that lead from 'from' to 'to'.";
    }

    public void printMatrix() {
        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++)
                System.out.print((matrix[i][j] == INFINITY) ? "_\t" : (matrix[i][j] + "\t"));
            System.out.println();
        }
    }
}
