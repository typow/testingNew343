import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class DuplicateURL {

	public static void main(String[] args) {
		HashMap<String, String> table = new HashMap<String, String>();
		table.put("a.com", "aa");
		table.put("b.com", "b");
		table.put("c.com", "c");
		table.put("e.com", "a");
		table.put("d.com", "a");
		HashMap<String, Set<String>> result = dupesMap(table);
		System.out.print(result.toString());

	}


	public static HashMap<String, Set<String>> dupesMap(Map<String, String> table) {
		
		HashMap<String, String> keyMap = new HashMap<String, String>();
		HashMap<String, Set<String>> resultsMap = new HashMap<String, Set<String>>();
		
		for(Entry<String, String> entry : table.entrySet()) {
			
			String oldKey = entry.getKey();
			String oldVal = entry.getValue();
			String resultKey = keyMap.get(oldVal);
			
			if(resultKey == null){
				keyMap.put(oldVal, oldKey);
				resultsMap.put(oldKey, new HashSet<String>());
			}
			else{
				Set<String> dupes = resultsMap.get(resultKey);
				dupes.add(oldKey);
			}
		}
		
		return resultsMap;
	}
	
	
}