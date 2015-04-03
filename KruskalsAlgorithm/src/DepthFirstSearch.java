import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;


public class DepthFirstSearch {
	
	/**
	 * The resulting stack of vertices from the DepthFirstSearch
	 */
	private static Deque<Vertex> dfsStack = new ArrayDeque<Vertex>();
	
	/**
	 * the count keeps track of how many connected components there are in the Graph
	 */
	private static Integer count = 0;
	
	/**
	 * private constructor
	 */
	private DepthFirstSearch() {
		
	}
	
	
	/**
	 * Runs depth first search on the passed in SimpleGraph G.
	 * 
	 * @param G		The SimpleGraph that DFS will be run on
	 * @param V		The starting vertex for DFS
	 * @return		A Deque<Vertex> of Vertices in order of being visited from a DFS
	 */
	public static Deque<Vertex> depthFirstSearch(SimpleGraph G, Vertex V) {
		
		//get the vertices in G
		Iterator verts = G.vertices();
		
		//for each vertex in G
		while(verts.hasNext()) {
			Vertex v = (Vertex) verts.next();
			
			//if v has not been visited yet
			if(v.getData() == null) {
				//if we return from dfs and there are more vertices in G to be visited
				//then there is at least one more component to graph G
				count++;
				dfs(G, v);
				
			}			
		}

		return dfsStack;
	}
	
	/**
	 * A recursive helper method for {@link #depthFirstSearch(SimpleGraph, Vertex) depthFirstSearch} that
	 * marks the vertex v if it isn't already marked.  Then for each vertex w, that is adjacent to v 
	 * if it isn't yet marked calls {@link #dfs(SimpleGraph, Vertex) dfs} on it.
	 * 
	 * @param G		- the graph that DepthFirstSearch is being run on
	 * @param v		- the current vertex being examined
	 */
	private static void dfs(SimpleGraph G, Vertex v) {
		
		//if vertex v has not been visited mark it as true
		//and add it to the stack
		if(v.getData() == null) {
			v.setData(count);
			dfsStack.addFirst(v);
		}
		
		Iterator<Object> adjVerts;
		
		//for each vertex w adjacent to v if it hasnt been visited call dfs on it
		for(adjVerts = G.incidentEdges(v); adjVerts.hasNext();) {
			Edge e = (Edge) adjVerts.next();
			Vertex w = G.opposite(v, e);
			//if vertex w has not been visited yet call dfs on it
			if(w.getData() == null) {
				dfs(G, w);
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		SimpleGraph G = new SimpleGraph();
		GraphInput.LoadSimpleGraph(G);
		Vertex v = G.aVertex();
		Deque<Vertex> dfs = DepthFirstSearch.depthFirstSearch(G, v);
		
		while(!dfs.isEmpty()) {
			Vertex w = dfs.removeFirst();
			System.out.print("vertex: " + (String) w.getName() + ", Component: ");
			System.out.println((Integer) w.getData());
		}

	}

}
