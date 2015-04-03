


import java.util.Iterator;

/**
 * Creates a SimpleGraph of edges and vertices from a file specified by the user then
 * performs Kruskal's algorithm on the SimpleGraph printing out the edges and their 
 * costs that make up the MST as well as the total weight cost.
 * 
 * @author David Kim
 * @author Tyler Powers
 * @author Mahir Ahmed
 */
public class Kruskals {
	
	/** Empty constuctor */
	private Kruskals() {
		
	}
	
	
	/**
	 * Runs Kruskal's algorithm on graph G and prints out the edges and their weights
	 * that make up the MST as well as the MST total weight cost. 
	 * 
	 * @param G		the SimpleGraph that Kruskal's algorithm will be run on
	 */
	public static void kruskal(SimpleGraph G) {
		
		//the number of edges accepted into the MST
		int edgesAccepted = 0;
		
		//the total weight of the MST
		double totalCost = 0;
		
		//An array of EdgeHeapNodes the size of the number of edges in the graph
		Comparable[] nodes = new EdgeHeapNode[G.numEdges()];
		int numVertices = G.numVertices();
		
		Edge e;
		Vertex vert;
		int counter = 0;
		Iterator k;
		
		//Assign each vertex a number between 0 and |V|-1 to be used with the disjoint set
		for (k = G.vertices(); k.hasNext();) {
			vert = (Vertex) k.next();
			vert.setData(counter); //Use the data field of a Vertex object
			counter++;
		}
		
		counter = 0;
		
		//create a EdgeHeapNode for each edge and add it to the array of edges
		for (k = G.edges(); k.hasNext(); ) {
			
			e = (Edge) k.next();  
			nodes[counter] = new EdgeHeapNode((double) e.getData(), e.getFirstEndpoint(), 
		 			e.getSecondEndpoint());
		  	counter++;

		}   
		  
		//Build a min BinaryHeap out of the edges
	    BinaryHeap heap = BinaryHeap.buildHeap(nodes);
		
	    //Create a disjoint set for the vertices in the graph
		DisjointSet s = new DisjointSet(numVertices); 
		
		//while the graph doesn't include all the vertices check if each edge creates
		//a cycle if it does ignore it if it doesn't add it to the MST.
		while (edgesAccepted < numVertices - 1) {
			
			//Set edge to the edge with the smallest edge weight
			EdgeHeapNode edge = null;
			try {
				edge = (EdgeHeapNode) heap.deleteMin(); 
			} catch (EmptyHeapException e1) {
				System.out.println("Error deleting from heap");
			}
			
			//get the vertices data to use in the disjoint sets find operation
			int u = (Integer) edge.getVertexOne().getData();
			int v = (Integer) edge.getVertexTwo().getData();
			
			//get the roots of vertices u and v to check for a cycle
			int uset = s.find(u);
			int vset = s.find(v); 
			
			//Check if the union of vertices u and v create a cycle
			if (uset != vset) {
				edgesAccepted++;
				//***************************FUTURE CHANGE***************************************
				//add the edges to a collection and return it next time then the data can be used however 
				//the programmer wants (they can print it and sum it themselves.)
				//*******************************************************************************
				System.out.print("(" + (String) edge.getVertexOne().getName() + ", " 
									+ (String) edge.getVertexTwo().getName() + ") ");
				System.out.println(" weight = " + edge.getWeight());
				totalCost += edge.getWeight();
				s.union(uset, vset);    			
			} 
		}
		System.out.println();
		System.out.println("Total cost of the MST: " + totalCost);
	}
 
	
	/**
	 * Create a graph load vertices and edges from a file and run Kruskal's algorithm on it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {   
		SimpleGraph G = new SimpleGraph(); 
		GraphInput.LoadSimpleGraph(G);
	    
	    /*
	    System.out.println();
	    System.out.println("Number of vertices = " + NUM_VERTICES);
	    System.out.println("Number of edges = " + G.numEdges());
	    System.out.println();
	    */
		
	    System.out.println("Edges accepted into MST:");
	    kruskal(G);  
	} 
}
