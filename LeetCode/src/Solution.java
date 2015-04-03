import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Solution {
	
	public static void main(String[] args) {
		char next = 48;
		int num = next - 48;
		System.out.println(num);
	}

	//Problem 1 Two Sum Mine running time O(NlogN) because of sorting space O(N)
	public int[] myTwoSum(int[] numbers, int target) {
        int[] numbersCopy = Arrays.copyOf(numbers, numbers.length);
        Arrays.sort(numbersCopy);
        int index1 = 0; //index of first sum number
        int index2 = numbersCopy.length - 1; //index of second sum number
        int[] result = new int[2];  //result is returned here
        
        //while the indexes havent crossed
        while(index1 < index2)  {

            //if we have found the two indexes that add to target
            int j = 0;
            if(numbersCopy[index1] + numbersCopy[index2] == target) {
                
                //get the original index of the first(smallest) number
                for(j = 0; j < numbers.length; j++) {
                    if(numbersCopy[index1] == numbers[j]) {
                        break;
                    }
                }
                
                //get the original index of the second(larger) number
                int i = 0;
                for(i = 0; i < numbers.length; i++) {
                    if(numbersCopy[index2] == numbers[i] && i != j) {
                        break;
                    }
                }
                result[0] = Math.min(i, j) + 1;
                result[1] = Math.max(i, j) + 1;
                break;
            }
            //if the sum is less than the target increase the left pointer by 1
            else if(numbersCopy[index1] + numbersCopy[index2] < target) {
                index1++;
            }
            //if the sum is greater than the target decrease right pointer by 1
            else if(numbersCopy[index1] + numbersCopy[index2] > target) {
                index2--;
            }
        }
        return result;
        
    }
	
	//Problem 1 Two Sum using HashMap running time O(N) space O(N)
	public int[] twoSum(int[] numbers, int target) {
        Character hello = new Character('c');
        HashMap<Integer, Integer> numbersMap = new HashMap<Integer, Integer>();
        
        //put all numbers into a hash map with key being the contents of numbers[] and value being index
        for(int i = 0; i < numbers.length; i++) {
            
            //check first if target - numbers[i] exists if it does we have found a solution.
            // a solution being current index i and the index of target-numbers[i].
            //(this check before adding into map allows for the key to be numbers[i] even when there are doubles)
            Integer check = (Integer)(target - numbers[i]);
            if(numbersMap.containsKey(check)) {
            	int result[] = {(numbersMap.get(check.intValue()) + 1), (i + 1)};
                return result;
            }
            
            //if it doesn't exist put current value and index into map.
            numbersMap.put(i, numbers[i]);
        }
        
        //no match found
        return new int[] {-1, -1};
        
    }
	
	//Problem 4
	//find median of two uneven sorted arrays
	//requires A is smaller than B
	public double findMedianSortedArrays(int A[], int B[]) {
        
        double median = -1.0;
        int m = A.length;//size of A is m
        int n = B.length;// size of B is n

        //if A is larger than B call this method with the arrays swapped
        if(m > n) {
            return findMedianSortedArrays(B, A);
        }
        
        int midA = (m - 1) / 2;
        int midB = (n - 1) / 2;
        int lowA = 0;
        int lowB = 0;
        int highA = m - 1;
        int highB = n - 1;
        
        //at this point we know the smaller array is A with size m so check if
        //m is 0 then just return the median of B
        if(m == 0) {
            
            //if B is of even size return the average of the two center elements
            if(n % 2 == 0) {
                return (B[midB] + B[midB + 1]) / 2.0;
                
            //if B is off return median    
            } else {
                return B[midB] / 1.0;
            }
        }
        
        //until A has one or two elements in it
        //NOTE: for the median of an array of even numbers you average the 2 centers
        //      instead the mid pointer will point to the lower so the real median will be
        //      (mid + (mid + 1)) / 2
        while(m > 2) {
            
            //median of A is less than median of B cut off lower half of A
            //and a chunk half the size of A from upper half of B
            if(A[midA] <= B[midB]) {
                
                n = n - ((m - 1) / 2); //make the size of B n - the new size of A
                highB = highB - ((m - 1) / 2); //subtract half the size of A from B's high pointer
                midB = ((highB - lowB) / 2) + lowB;
                

                m = m / 2 + 1;//make the size of A half the size it was
                lowA = midA; //take upper half of A
                midA = ((highA - lowA) / 2) + lowA;
                
            
            //median of A is higher than median of B cut off upper half of A
            //and a chunk half the size of A from lower half of B
            } else {
                
                n = n - ((m - 1) / 2); // make the size of B n - half the size of A
                lowB = lowB + ((m - 1) / 2); // take the upper portion of B by subtracting half the size of A from it
                midB = ((highB - lowB) / 2) + lowB;
                
                
                highA = highA - ((m - 1) / 2); // take the lower half of A by changing the high pointer to the midA
                m = m / 2 + 1;//make the size of A half the size it was                
                midA = ((highA - lowA) / 2) + lowA;
                 
                
            }//end if/else case
            
        }//end while loop
        
        //the size of A aka m is now either 2 or 1 there are special cases
        //Case 1: m=n=1
        //Case 2: m=n=2
        //Case 3: m=1 n=odd
        //Case 4: m=1 n=even
        //Case 5: m=2 n=odd
        //Case 6: m=2 n=even
        
        int max = 0;
        int min = 0;
        
        //Case1 m=n=1
        if(m == 1 && n == 1) {
            median = (A[midA] + B[midB]) / 2.0;
        
        //Case2: m=n=2
        } else if(m == 2 && n == 2) {
            median = A[midA] + A[midA + 1] + B[midB] + B[midB + 1];
            max = Math.max((Math.max((Math.max(A[midA + 1], B[midB + 1])), A[midA])), B[midB]);
            min = Math.min((Math.min((Math.min(A[midA], B[midB])), A[midA + 1])), B[midB + 1]);
            median = median - max - min;
            median = median / 2.0;
            
        //Case3: m=1 n=odd
        } else if(m == 1 && n % 2 == 1) {
            
            median = A[midA] + B[midB] + B[midB - 1] + B[midB + 1];
            max = Math.max(A[midA], B[midB + 1]);
            min = Math.min(A[midA], B[midB - 1]);
            median = median - max - min;
            median = median / 2.0;
            
        //Case4: m=1 n=even    
        } else if(m == 1 && n % 2 == 0) {
            
            median = A[midA] + B[midB] + B[midB + 1]; 
            max = Math.max(A[midA], B[midB + 1]);
            min = Math.min(A[midA], B[midB]);
            median = (median - max - min) / 1.0;
            
        //Case5: m=2 n=odd
        } else if(m == 2 && n % 2 == 1) {
            int maxone = Math.max(A[midA], B[midB - 1]);
            int minone = Math.min(A[midA + 1], B[midB + 1]);
            median = B[midB] + maxone + minone;
            max = Math.max((Math.max(B[midB], minone)), maxone);
            
            //min must be two smallest numbers added together
            min = Math.min((Math.min(B[midB], maxone)), minone);
            median = (median - max - min) / 1.0;
            
        //Case6: m=2 n=even    
        } else if(m == 2 && n % 2 == 0) {
            int maxone = Math.max(A[midA], B[midB - 1]); //maximum of the lower mid A and one left of mid B
            int minone = Math.min(A[midA + 1], B[midB + 2]);//minimum of the higher mid A and the element to the right of the 2 mid B's
            median = maxone + minone + B[midB] + B[midB + 1];
            max = Math.max((Math.max((Math.max(minone, B[midB + 1])), maxone)), B[midB]);
            min = Math.min((Math.min((Math.min(maxone, B[midB])), minone)), B[midB + 1]);
            median = median - max - min;
            median = median / 2.0;
            
        }//end if cases
            
        return median;
    }//end method
	
	
	//Problem 3
	//find the longest substring without repeating characters.
	public int lengthOfLongestSubstring(String s) {
        
        int maxLength = 0;//the maximum substring so far
        int currLength = 0;//the current substring since a duplicate
        HashMap<Character, Integer> chars = new HashMap<Character, Integer>();
        
        for(int i = 0; i < s.length(); i++) {
            
            //if the map has the current char, make the currLength be the minimum between
            //the currLength and the length between the duplicate char
            if(chars.containsKey(s.charAt(i))) {
                currLength = Math.min(currLength + 1, i - chars.get(s.charAt(i)));
                
            //else increase currLength by 1
            } else {
                currLength++;
                
            }
            
            //check if its bigger than the maxLength
            if(currLength > maxLength) {
                    maxLength = currLength;
            }
                
            chars.put(s.charAt(i), i);
            
        }//end for
        
        return maxLength;
        
    }//end lengthOfLongestSubstring
	
	/**
	//Problem 2
	//add two numbers stored as a linked list representing two non negative numbers
	//each node represents a single digit
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        ListNode node = new ListNode(0);
        
        //if both l1 and l2 are null then we have reached the end 
        if(l1 == null && l2 == null) {
            return null;
        
        //if just l1 is null sum of l1 and l2 is just l2
        } else if(l1 == null) {
            node.val = l2.val;
            node.next = addTwoNumbers(l1, l2.next);
            
        //if just l2 is null sum of l1 and l2 is just l1    
        } else if(l2 == null) {
            //if the l1.val was 9 and there was a carry
            if(l1.val == 10) {
                node.val = 0;
                
                //if l1.next isnt null add carry to it
                if(l1.next != null) {
                    l1.next.val += 1;
                //if l1.next is null create new one with val of 1 for carry
                } else {
                    l1.next = new ListNode(1);
                }
                
            //if l1.val is less than 10
            } else {
                node.val = l1.val;
            }
            
            node.next = addTwoNumbers(l1.next, l2);
        } else {
            
            int sum = l1.val + l2.val;
            
            //if the sum is over 9 there is a carry to worry about
            if(sum > 9) {
                node.val = sum % 10;
                
                //if l1.next is null create new node with 1 as a value
                if(l1.next == null) {
                    l1.next = new ListNode(1);
                    
                //if l1.next exists add one to its value
                } else {
                   l1.next.val += 1;//add the carry to l1.val 
                }
                
                node.next = addTwoNumbers(l1.next, l2.next);
                
            //the sum is a single digit
            } else {
                node.val = sum;
                node.next = addTwoNumbers(l1.next, l2.next);
            }
        }
        
        return node;
    } **/
	
	//Problem 5
	//Return the longest palindrome in the string S
	public String longestPalindrome(String s) {
        
        int n = s.length();//size of s
        
        int maxPal = 1;//length of the longest palindrome
        int maxI = 0; //first index of the max palindrome
        int maxJ = 0;//ending index of the max palindrome
        
        //let p[i][j] represent whether the substring of s from i to j is 
        //a palindrome. True if it is, false if it is not
        boolean[][] p = new boolean[n][n];
        
        //all substrings of size one are palindromes
        for(int i = 0; i < n; i++) {
            p[i][i] = true;
        }//end for
        
        //all substrings of size two
        for(int i = 0; i < n - 1; i++) {
            
            //if the two chars are the same then they are a palindrome
            //so max length can be changed to 2
            if(s.charAt(i) == s.charAt(i + 1)) {
               p[i][i + 1] = true;
               maxPal = 2;
               maxI = i;
               maxJ = i + 1;
            } else {
                p[i][i + 1] = false;
            }
            
        }//end for
        
        //fills in the rest of the table top down
        for(int j = 2; j < n; j++) {
            int i = 0;
            
            while(i <= j - 2) {
                
                //if the first(s.charAt(i)) and last(s.charAt(j)) char of the current substring are the same AND
                //the same substring without those first and last chars is also a palindrome
                //then substring from i to j is also a palindrome so set p[i][j] = true
                if(s.charAt(i) == s.charAt(j) && p[i + 1][j - 1] == true) {
                    p[i][j] = true;
                    
                    //if the current palindrome substring is longer than
                    //the max so far update it.
                    if(maxPal < (j - i + 1)) {
                        maxPal = j - i + 1;
                        maxI = i;
                        maxJ = j;
                    }
                    
                    
                } else {
                    p[i][j] = false;
                }
                
                i++;//increment i
                
            }//end while
            
        }//end for
        
        return s.substring(maxI, maxJ + 1);
        
    }//end longestPalindrome
	
	
	//Problem 7
	//reverse int; check for overflow of int and return 0 if it does
	public int reverse(int x) {
		/**
		 //simpler version of reverse
		long temp;
        if(x < 0) {
            
            //check if
            if(x == Integer.MIN_VALUE){
                return 0;
            }
           temp = x * -1; 
        }else {
           temp = x; 
        }

        long reverse=0;
        while(temp!=0){
            reverse = 10*reverse+temp%10;
            temp = temp/10;
        }

        //if original x was negative make result negative
        if(x < 0) {
            reverse = reverse * -1;
        }
        
        //Check for out of bounds
        if(reverse > Integer.MAX_VALUE || reverse < Integer.MIN_VALUE) {
            return 0;
        }
        
        return (int)reverse;
		 */
        long div = 1;
        long result = 0;
        long digits = 1;
        int newX = 0;
        if(x < 0) {
            
            //check if
            if(x == Integer.MIN_VALUE){
                return 0;
            }
           newX = x * -1; 
        }else {
           newX = x; 
        }
        
        int temp = 0;
        
        if(newX < 10) {
            return x;
        }
        
        //to determine what div should be
        while(newX / div > 0) {
            div = div * 10;
        }
        
        //div is * 10 too high
        div = div / 10;
        
        while(div > 0) {
            temp = (newX / (int)div);
            result = result + (temp * digits);
            digits = digits * 10;
            newX = newX - (temp * (int)div);
            div = div / 10;
        }
        
        //if original x was negative make result negative
        if(x < 0) {
            result = result * -1;
        }
        
        //Check for out of bounds
        if(result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }
        
        return (int)result;
    }
	
	//Problem 9 isPalindrome number
	//is the number a palindrome (negatives don't count)
	public boolean isPalindrome(int x) {
        long high = 1;
        long low = 10;
        long highInt = 0;
        long lowInt = 0;
        long longX = x;
        if(longX < 0) {
            return false;
        } 
        long highX = longX;
        
        while(longX / high > 0) {
            high = high * 10;
        }
        high = high / 10;
        
        while(high >= low) {
            highInt = highX / high;
            highX = highX - (highInt * high);
            high = high / 10;
            lowInt = longX % low;
            low = low * 10;
            if(lowInt != highInt) {
                return false;
            }
        }
        return true;
        /**
		 
		    //this is simpler version of palindrome number
		    long reverse = 0;
	        long temp = x;
	        
	        //if its negative return false
	        if(x < 0) {
	            return false;
	        }
	        
	        
	        while(temp != 0) {
	            reverse = reverse * 10 + temp % 10;
	            temp = temp / 10;
	        }
	        return reverse == x;
		 */
    }
	
	
	//Problem 12
	//return roman numeral from integer
	public String intToRoman(int num) {
        int newNum = num;
        String roman = "";
        String temp = "";
        
        //get the thousands roman numeral set
        while(newNum / 1000 > 0) {
            newNum = newNum - 1000;
            temp = temp + "M";
        }
        
        roman = temp;
        temp = "";
        
        //get the hunderths roman numeral set
        while(newNum / 100 > 0) {
            if(newNum >= 500) {
                temp = "D";
                newNum = newNum - 500;
            } else {
                temp = temp + "C";
                newNum = newNum - 100;
            }
            
        }
        
        //check for 400 or 900 and adjust
        if(temp.equals("DCCCC")) {
            temp = "CM";
        } else if(temp.equals("CCCC")) {
            temp = "CD";
        }
        
        roman = roman + temp;
        temp = "";
        
        //determine the tenths roman numeral
        while(newNum / 10 > 0) {
            if(newNum >= 50) {
                temp = "L";
                newNum = newNum - 50;
            } else {
                temp = temp + "X";
                newNum = newNum - 10;
            }
            
        }
        
        //check for 40 or 90 and adjust
        if(temp.equals("LXXXX")) {
            temp = "XC";
        } else if(temp.equals("XXXX")) {
            temp = "XL";
        }
        
        roman = roman + temp;
        temp = "";
        
        //determine the ones roman numeral
        while(newNum > 0) {
            if(newNum >= 5) {
                temp = "V";
                newNum = newNum - 5;
            } else {
                temp = temp + "I";
                newNum = newNum - 1;
            }
        }
        
        //check for 4 and 9 and adjust
        if(temp.equals("VIIII")) {
            temp = "IX";
        } else if(temp.equals("IIII")) {
            temp = "IV";
        }
        
       return roman + temp;
       
       
       /**
        //simpler version
        String result = "";
        int newNum = num;
        String[] mNums = {"", "M", "MM", "MMM"};
        String[] cNums = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] xNums = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] iNums = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        
        int m = newNum / 1000;
        newNum = newNum % 1000;
        int c = newNum / 100;
        newNum = newNum % 100;
        int x = newNum / 10;
        newNum = newNum % 10;
        int i = newNum / 1;
        
        return mNums[m] + cNums[c] + xNums[x] + iNums[i];
        */
    }
	
	//Problem 13
	//Roman numeral to integer
	public int romanToInt(String s) {
        int result = 0;
        char chars[] = s.toCharArray();
        int size = s.length();
        char temp = ' ';
        
        for(int i = 0; i < size; i++) {
            temp = s.charAt(i);
            if(temp == 'M') {                           //check 1000
                result += 1000;
            } else if(temp == 'D') {                    //check 500
                result += 500;
            } else if(temp == 'C') {                    //check 100
                if(i + 1 < size && s.charAt(i + 1) == 'M') {
                    result += 900;                      //check 900
                    i++;
                } else if(i + 1 < size && s.charAt(i + 1) == 'D') {
                    result += 400;                      //check 400
                    i++;
                } else {
                    result += 100;
                }
            } else if(temp == 'L') {                    //check 50
                result += 50;
            } else if(temp == 'X') {                    //check 10
                if(i + 1 < size && s.charAt(i + 1) == 'C') {
                    result += 90;                       //check 90
                    i++;
                } else if(i + 1 < size && s.charAt(i + 1) == 'L') {
                    result += 40;                       //check 40
                    i++;
                } else {
                    result += 10;
                }
            } else if(temp == 'V') {                    //check 5
                result += 5;
            } else if(temp == 'I') {                    //check 1
                if(i + 1 < size && s.charAt(i + 1) == 'X') {
                    result += 9;                        //check 9
                    i++;
                } else if(i + 1 < size && s.charAt(i + 1) == 'V') {
                    result += 4;                        //check 4
                    i++;
                } else {
                    result++;
                }
            }
        }
        
        return result;
        
        /**
        //version of roman numeral to integer that is scalable using a mapping
        //keep a mapping of the roman numeral to integer conversions
        HashMap<Character,  Integer> romans = new HashMap<Character, Integer>();
        romans.put('I', 1);
        romans.put('V', 5);
        romans.put('X', 10);
        romans.put('L', 50);
        romans.put('C', 100);
        romans.put('D', 500);
        romans.put('M', 1000);
        
        char prev = s.charAt(0);
        int result = romans.get(prev);
        char current = ' ';
        
        for(int i = 1; i < s.length(); i++) {
            current = s.charAt(i);
            result += romans.get(current);
            if(romans.get(current) > romans.get(prev)) {
                result -= 2 * romans.get(prev);
            }
            prev = current;
        }
        
        return result;
         */
    }
	
	//Problem 14
	//longest common prefix in an array of strings
	public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) {
            return "";
        } else if(strs.length == 1) {
            return strs[0];
        }
        String prefix = strs[0];//longest common prefix in strs
        int prefixLen = prefix.length();//length of prefix
        
        //set prefix to the shortest string in strs
        for(int i = 1; i < strs.length; i++) {
            if(strs[i].length() != 0 && prefixLen > strs[i].length()) {
                prefix = strs[i];
                prefixLen = prefix.length();
            }
        }
        
        prefixLen--;//make it zero index to address indexes in strings
        
        for(String s: strs) {
            int index = prefixLen;
            //while the last char of prefix isnt the same as the current string at that same char
            //then the prefix doesnt match reduce its length by one
            while(index >= 0) {
                if(s.length() == 0) {
                    return "";
                }
                if(prefix.charAt(index) == s.charAt(index)) {
                    index--;
                } else {
                    prefix = prefix.substring(0, index);
                    index--;
                    prefixLen = index;
                }
                
            }
             
            if(prefixLen == -1) {
                return "";
            }
            
        }
        return prefix;
    }
	
	
	//Problem 15
	//find all unique sets of 3 numbers that sum up to zero in passed in array
	public List<List<Integer>> threeSum(int[] num) {
        
        int[] numCopy = Arrays.copyOf(num, num.length);//make a copy to leave num as it was
        Arrays.sort(numCopy);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        if(numCopy.length < 3) {
            return result;
        }
        
        //examine each element in the array to check for all possiblites of two other elements
        //(low and high) to add up to zero.  Also assumes element at i is the first element in
        //a unique list to prevent duplicates.
        for(int i = 0; i < numCopy.length - 2; i++) {
            
            //while the current first element being looked at is the same as the previous
            //go to the next element to prevent duplicate list because all possible
            //list that begin with that element were already added last interation of i.
            while(i > 0 && i < numCopy.length - 2 && numCopy[i] == numCopy[i - 1]) {
                i++;    
            }
            
            //set the target sum that low and high must sum up to be
            //Assumes element at i is the lowest element scan for everything above i
            int target = 0 - numCopy[i];
            int low = i + 1;
            int high = numCopy.length - 1;
            
            while(low < high) {
                
                //if the sum of the numbers at high and low equal the target we have found a unique set
                if((numCopy[low] + numCopy[high]) == target) {
                    List<Integer> set = new ArrayList<Integer>();
                    set.add(numCopy[i]); set.add(numCopy[low]); set.add(numCopy[high]);
                    result.add(set);
                    
                    //now we need to make sure the next low and high aren't duplicates
                    while(low < high && numCopy[low] == numCopy[low + 1]) {
                        low++;
                    }
                    low++;
                    
                    while(low < high && numCopy[high] == numCopy[high - 1]) {
                        high--;
                    }
                    high--;
                    //now continue to look for unique sets that start with element at i
                    
                //else if the sum of elements at high and low is higher than the target decrease high by one
                } else if((numCopy[low] + numCopy[high]) > target) {
                    high--;
                //else the sum of the elements at high and low are lower than the target so we need to increase low
                } else {
                    low++;
                }
                
            }
        }
        
        return result;
        
    }
	
}
