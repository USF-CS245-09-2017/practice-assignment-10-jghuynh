import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GraphImplementation {


    private int[][] adjMatrix;
    private int size;

    /**
     * Constructs an object of type GraphImplementation
     * @param n the number of vertices
     */
    public GraphImplementation(int n){
        // vertices from 0---n-1
         this.adjMatrix = new int[n][n];
         this.size = n;
    }

    /**
     * Adds a directed (uni-directional) edge from beginning point to ending point
     * @param begin the initial point
     * @param end the end point
     */
    public void addEdge (int begin, int end) {
        this.adjMatrix[begin][end] = 1;
    }

    /**
     * Creates an array of neighbors, ie. where 1 = a vertex's neighbor and 0 = not a neighbor
     * @param vertex the vertex to use
     * @return the array of neighbors
     */
    public int[] neighbors (int vertex) {
        int[] neighbors = new int[adjMatrix.length];
        for (int index = 0; index < adjMatrix.length; index ++) {
            if (adjMatrix[vertex][index] > 0) {
                neighbors[index] = 1;
            }

        }
        return neighbors;
    }

    /**
     * Prints an ordering of the vertices
     * @return the ordering; notify if impossible to order (because of cycles, for instance)
     */
    public List<Integer> topologicalSort () {
        List<Integer> order = new ArrayList<Integer>();
        boolean[] visited = new boolean[adjMatrix.length];
        DFS(0, visited);


        // my way
        for (int row = 0; row < adjMatrix.length; row ++) {
            for (int col = 0; col < adjMatrix.length; col ++) {
                if (adjMatrix[row][col] > 0) {
                    order.add(row);
                    order.add(col);
                    row = col; // advance to next node, basically
                    col = 0; // reset column
                }
                else if (col == adjMatrix.length - 1) {
                    // if you managed to make it to the end and everything is 0 = no link!

                }
            }
        }
        System.out.println("Ordering: ");
        for (int value: order) {
            System.out.print(value + " ");

        }
        return order;

    }

    private void DFS(int vertex, boolean[] visited) {
        visited[vertex] = true; // visited!
        Iterator<Integer> neigh = neighbors(vertex).iterator();
        while (neigh.hasNext()) {
            int n = neigh.next(); //advance to next neighbor
            if (!visited[n]) { // if n-next neighbor hasn't been visited yet
                visited[n] = true;
                DFS(n, visited);
            }
        }
    }


}
