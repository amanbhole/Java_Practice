package come.demo.collection.list;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ReverseLL {

	public static void main(String[] args) {
		List<String> strings = new LinkedList<>();
		strings.add("String 1");
		strings.add("String 2");
		strings.add("String 3");
		strings.add("String 4");
		strings.add("String 5");
		
		System.out.println(strings);
		
		ReverseLL reverseLL = new ReverseLL();
		System.out.println(reverseLL.reverseList(strings));
	}
	
	private List<String> reverseList(List<String> originalList) {
		// Sorting Collection in reverse order
        Collections.sort(originalList, Collections.reverseOrder());
		
		return originalList;
	}
}
