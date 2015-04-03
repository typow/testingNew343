
public class TestPeerOne {
	
	static int index = 0;
	
	public static void main(String[] args) {

		kwayMergesortRecursive(0, 9, 4);
	}
	
	public static void peerOne() {
		int k = 0;
		int n = 10;
		
		for(int i = 1; i < n + 1; i++) {
			
			for(int j = 1; j < i + 1; j++) {
				
				k = k + 2;
				if(j > 5) {
					k = k + i;
				}
				
			}
			
			System.out.println("i= " + i);
			System.out.println("k= " + k);
		}
		
		System.out.println(k);
	}
	
	public static void kwaySingleTest() {
		int low = 0;
		int high = 9;
		
		int k = 3;
		for (int i = 0; i < k; i++) {
			System.out.println("INDEX: " + i);
			System.out.println("Low: " + (low + i*(high-low+1)/k));
            System.out.println("High: " + (low + (i+1)*(high-low+1)/k - 1));
            
        }
	}
	
    public static void kwayMergesortRecursive (int low, int high, int k) { 
    	if (low < high) {
            for (int i = 0; i < k; i++) {

                kwayMergesortRecursive (
                                        low + i*(high-low+1)/k,
                                        low + (i+1)*(high-low+1)/k - 1,
                                        k);
                index++;
            	System.out.println("\nINDEX: " + index);
    			System.out.println("Low: " + (low + i*(high-low+1)/k));
                System.out.println("High: " + (low + (i+1)*(high-low+1)/k - 1));

            }
    	}
            
    }
}
