/* 
 * Written by Donald Chinn September 19, 2003
 * Modified by Tyler Powers November 26, 2014 
 * 
 */



/**
 * An EdgeHeapNode has a weight of the edge and the two vertices
 * that make up the edge.
 * 
 * @author Donald Chinn
 * @version September 19, 2003
 */
public class EdgeHeapNode implements Comparable {
   
	/** the edge weight */
	private double weight;            
    
    /** the first endpoint of the edge */
    private Vertex v1;
    
    /** the second endpoint of the edge */
    private Vertex v2;
    
    /**
     * Constructor.
     * @param weight   the weight of the edge
     * @param v1    the first vertex that makes up the edge
     * @param v2	the second vertex that makes up the edge
     *                          
     */
    public EdgeHeapNode (double key, Vertex v1, Vertex v2) {
        this.weight = key;
        this.v1 = v1;
        this.v2 = v2;
    }
    
    
    // Accessor methods
    public double getWeight() {
        return weight;
    }
    
    
    public Vertex getVertexOne() {
        return v1;
    }
    
    
    public Vertex getVertexTwo() {
        return v2;
    }
    
    
    /**
     * Implements the compareTo method.
     * @param rhs the other EdgeHeapNode object.
     * @return 0 if two objects are equal;
     *     less than zero if this object is smaller;
     *     greater than zero if this object is larger.
     * @exception ClassCastException if rhs is not a EdgeHeapNode.
     */
    public int compareTo (Object rhs) {
        if (this.weight < ((EdgeHeapNode) rhs).weight) {
            return -1;
        } else if (this.weight == ((EdgeHeapNode) rhs).weight) {
            return 0;
        } else {
            return 1;
        }
    }
}