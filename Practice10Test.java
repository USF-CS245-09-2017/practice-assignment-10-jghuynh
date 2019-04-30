import java.util.List;


public class Practice10Test {
	
	
	public Graph getGraph(int size) {
		return new GraphImplementation(size);
	}

	
	public boolean createGraphTest() {
		// Just create a graph and expect it not to blow up...
		try {
			Graph g = getGraph(3);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	
	public boolean addEdgeTest() {
		Graph g = getGraph(3);
		
		g.addEdge(0,  1);
		g.addEdge(0, 2);
		
		// Check the neighbours...
		int[] neighbours = g.neighbors(0);
		boolean contains1 = false;
		boolean contains2 = false;
		int items = 0;
		for (int i = 0; i < neighbours.length; i++) {
			++items;
			if (neighbours[i] == 1) {
                contains1 = true;
            }

			if (neighbours[i] == 2) {
                contains2 = true;
            }

		}
		return contains1 && contains2 && items == 2;
	}
	
	
	public boolean topologicalSortTest() {
		Graph g = getGraph(3);
		int [] expected = {0, 2, 1};
		
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(2, 1);
		List<Integer> schedule = g.topologicalSort();
		if (schedule.size() != 3)
		{
		    return false;
        }
        for (int i = 0; i < expected.length; i++)
        {
            if (schedule.get(i) != expected[i]) {
                return false;
            }

        }
		return true;
	}


	public void runTest () {
		int grade = 0;
		
		if (createGraphTest()) {
			grade += 10;
			System.out.println("[+10%] Passed create graph test");
		} else {
			System.out.println("[    ] Failed create graph test");
		}
		
		if (addEdgeTest()) {
			grade += 30;
			System.out.println("[+30%] Passed adding edge / check neighbours test");
		} else {
			System.out.println("[    ] Failed adding edge / check neighbours test");
		}
		
		// Topological sort will be checked by hand, because ...
		if (topologicalSortTest()) {
			grade += 60;
			System.out.println("[+60%] Passed topological sort test");
		} else {
			System.out.println("[    ] Failed topological sort test");
		}
		
		System.out.println("Starting point for this assignment: " + grade + "%");
	}

	public static void main(String[] args) {
		Practice10Test test = new Practice10Test();
		test.runTest();
	}

}
