import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class GraphImplementation implements Graph {


    private int[][] adjMatrix;
    private int[] sum;
    private int size;
    private int neighborsSize;

    /**
     * Constructs an object of type GraphImplementation
     * @param n the number of vertices
     */
    public GraphImplementation(int n){
        // vertices from 0---n-1
         this.adjMatrix = new int[n][n];
         this.size = n;
         this.sum = new int[n];
    }

    /**
     * Adds a directed (uni-directional) edge from beginning point to ending point
     * @param begin the initial point
     * @param end the end point
     */
    public void addEdge (int begin, int end) {
        // maybe add an inputValidation so users won't add too many points
        this.adjMatrix[begin][end] = 1;
        sum[end] ++;
        neighborsSize ++;

    }

    /**
     * Creates an array of neighbors, ie. where 1 = a vertex's neighbor and 0 = not a neighbor
     * @param vertex the vertex to use
     * @return the array of neighbors
     */
    public int[] neighbors (int vertex) {
        // question: why should
        int[] neighbors = new int[neighborsSize];
        int neighIndex = 0;
        for (int index = 0; index < adjMatrix.length; index ++) {
            if (adjMatrix[vertex][index] > 0) {
                neighbors[neighIndex] = index;
                neighIndex ++;
                // implementation is correct...now make sure neighbors is just size 2, not 3...
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
        /*
        //
        System.out.println("Before Sorting: Sum:");
        for(int value: sum) {
            System.out.print(value + " ");
        }
        System.out.println();
        //
        */
        for (int index = 0; index < adjMatrix.length; index ++) {
                order.add(zeroCount(sum));
        }
        if (order.contains(-1)) {
            System.out.println("Graph has cycle!");
            System.out.println("Partial order: ");
            for (int value: order) {
                if (value != -1)
                {
                    System.out.print(value + " ");
                }
            }
            return order;
        }
        System.out.println("Order: ");
        for (int value: order) {
            System.out.print(value + " ");
        }
        System.out.println();
        //DFS(0, visited);


        // my way
        //updateSum();
        /*
        System.out.println("Sum array:");
        for (int value: sum) {
            System.out.print(value + " ");
        }
        System.out.println();
        */
        return order;
        /*
        for (int row = 0; row < this.adjMatrix.length; row ++) {
            for (int col = 0; col < this.adjMatrix.length; col ++) {
                if (adjMatrix[row][col] > 0 && !order.contains(col)) {
                    order.add(row);
                    order.add(col);
                    row = col; // advance to next node, basically
                    col = 0; // reset column
                }
//                if (col == adjMatrix.length - 1) {
//                    // if you managed to make it to the end and everything is 0 = no link!
//                    // stop. You're dead
//                    row = this.adjMatrix.length;
//                    col = this.adjMatrix.length;
//                    System.out.println();
//                }
                // if found cycle
                if (hasCycle()) {
                    System.out.println("Graph has cycle!");
                    System.out.println("Partial order: ");
                    for (int value: order) {
                        System.out.println(value + " ");
                    }
                    return order;
                }
            }
        }

        System.out.println("Ordering: ");
        for (int value: order) {
            System.out.print(value + " ");

        }
        return order;
        */

    }

    /**
     * Gets the sum array summarizing which vertex connects to which vertex
     * @return the sum array
     */
    private void updateSum() {
        for (int col = 0; col < adjMatrix.length; col ++) {
            for (int row = 0; row < adjMatrix.length; row++) {
                // if found an edge
                if (adjMatrix[row][col] == 1) {
                    sum[col]++;
                }

                sum[col] = 0;
            }
        }

    }

    /**
     * Gets the first link in topological sort by finding the first index that has 0
     * @param incident the given array displaying the sums
     * @return the first index that has count 0
     */
    private int zeroCount(int[] incident) {
        for (int index = 0; index < incident.length; index ++) {
            if (incident[index] == 0) {
                incident[index] = -1;
                for (int col = 0; col < adjMatrix.length; col ++) {
                    if (adjMatrix[index][col] > 0) {
                        adjMatrix[index][col] = 0;
                        sum[col]--;
                    }

                }

                return index;
            }
        }
        return -1;
    }


    /**
     * Determines if the sum array/graph has a cycle
     * @return true if graph has a cycle; false otherwise
     */
    private boolean hasCycle() {
        int num1s = 0;
        for (int value: sum) {
            if (value == 1) {
                num1s++;
                // if more than possible start link
            }

        }
        if (num1s == sum.length) {
            return true;
        }
        return false;
    }


    private void DFS(int vertex, boolean[] visited) {
        visited[vertex] = true; // visited!
        int[] neighbor = neighbors(vertex);
        List neighbors = Arrays.asList(neighbor);
        Iterator<Integer> neigh = neighbors.iterator();
        while (neigh.hasNext()) {
            int n = neigh.next(); //advance to next neighbor
            if (!visited[n]) { // if n-next neighbor hasn't been visited yet
                visited[n] = true;
                DFS(n, visited);
            }
        }
    }

    public static void main(String[] args) {
        GraphImplementation myG = new GraphImplementation(5);
        myG.addEdge(0, 1);
        myG.addEdge(0, 2);
        myG.addEdge(2, 1);
        myG.addEdge(1, 3);
        myG.addEdge(3, 4);
        myG.addEdge(4, 2);
        myG.topologicalSort();
    }



}
