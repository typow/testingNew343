/**
 * 
 */

/**
 * Implements equivalence relations as an array where the item in the array at an index indicates
 * which equivalence relation it belongs to.  Negative number represents it being the root and 
 * negative numbers indicate size.
 * 
 * 
 * @author Tyler Powers
 * @author David Kim
 * @author Mahir Ahmed
 * 
 * @version November 28, 2014
 *
 */
public class DisjointSet {
	
	/** the array storing the uptree */
	private int[] uptree;
	
	/** 
	 * Constructor for DisjointSet class initializing all uptree's 
	 * to be in their own set.
	 * 
	 * @param size 		the size the array needs to be
	 */
	public DisjointSet(int size) {
		uptree = new int[size];
		for(int i = 0; i < size; i++) {
			uptree[i] = -1;
		}
	}
	
	/**
	 * Make the bigger uptree of v1 and v2 the root of the other and update the size.  If they are 
	 * the same size make v1 the root of v2.  If the uptrees of v1 and v2 are in 
	 * the same equivalence relation no union is done. NOTE numbers stored in the roots of the uptree array
	 * indicate the size as a negative value.  This means the smaller the number the larger the uptree
	 * (EX: -5 indicates its the root of a tree of size 5 and -2 indicates a root of a tree of size 2 therefore
	 * -5 is a bigger tree than -2.)
	 * 
	 * @param v1	the first uptree being unioned
	 * @param v2	the second uptree being unioned
	 */
	public void union(int v1, int v2) {
		v1 = find(v1);
		v2 = find(v2);
		
		//they are part of the same uptree do nothing
		if(v1 == v2) {
		
		} 
		//size of uptree at v1 is bigger than or equal to the size of the uptree at v2
		else if(uptree[v1] <= uptree[v2]) {
			uptree[v1] = uptree[v1] + uptree[v2]; //add the size of v2 to the size of v1
			uptree[v2] = v1; //set the root of uptree at v2 to be v1
		}
		//uptree at v2 is a bigger tree than uptree at v1
		else if(uptree[v1] > uptree[v2]) {
			uptree[v2] = uptree[v2] + uptree[v1]; //add the size of v1 to the size of v2
			uptree[v1] = v2; //set the root of uptree at v1 to be v2
		}
		
	}
	
	/**
	 * Finds the root of the uptree that the passed in node belongs to.
	 * 
	 * @param v1 	the node whose root needs to be found for the uptree it belong to.
	 * @return the 	root of the uptree for the node being passed in
	 */
	public int find(int v1) {
		if (uptree[v1] < 0) {
			return v1;
		} else {
			return find(uptree[v1]);
		}
	}
	
	/**
	 * Used to test DisjointSet class.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int size = 5;
		DisjointSet disJoint = new DisjointSet(size);
		
		System.out.println("After initiating the disjoint set");
		for(int i = 0; i < size; i++) {
			System.out.println("root of " + i + ": " + disJoint.find(i));
		}
		System.out.println();
		
		disJoint.union(0, 1);
		
		System.out.println("After union(0,1)");
		for(int i = 0; i < size; i++) {
			System.out.println("root of " + i + ": " + disJoint.find(i));
		}
		System.out.println();
		
		disJoint.union(2, 3);
		disJoint.union(2, 4);
		
		System.out.println("After union(2,3) and union(2,4)");
		for(int i = 0; i < size; i++) {
			System.out.println("root of " + i + ": " + disJoint.find(i));
		}
		System.out.println();
		
		System.out.println("check the data in the uptree before second union(2,3)");
		System.out.println("uptree[2]: " + disJoint.uptree[2]);
		System.out.println("uptree[3]: " + disJoint.uptree[3]);
		System.out.println();
		disJoint.union(2, 3);
		
		System.out.println("After second union(2,3)");
		for(int i = 0; i < size; i++) {
			System.out.println("root of " + i + ": " + disJoint.find(i));
		}
		System.out.println();
		
		System.out.println("Check the data in the uptree after second union(2,3)");
		System.out.println("uptree[2]: " + disJoint.uptree[2]);
		System.out.println("uptree[3]: " + disJoint.uptree[3]);
		System.out.println();
		
		disJoint.union(0, 2);
		
		System.out.println("After union(0,2)");
		for(int i = 0; i < size; i++) {
			System.out.println("root of " + i + ": " + disJoint.find(i));
		}
		System.out.println();
		
		System.out.println("Check data in uptree");
		System.out.println("uptree[0]: " + disJoint.uptree[0]);
		System.out.println("uptree[1]: " + disJoint.uptree[1]);
		System.out.println("uptree[2]: " + disJoint.uptree[2]);
		System.out.println("uptree[3]: " + disJoint.uptree[3]);
		System.out.println("uptree[4]: " + disJoint.uptree[4]);
		System.out.println();
		System.out.println("expect find of 1 to be 2 since 1 points to 0 which points to 2");
		System.out.println("find(1): " + disJoint.find(1));

	}

}
