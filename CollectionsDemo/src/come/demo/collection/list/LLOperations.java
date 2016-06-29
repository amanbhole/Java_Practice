package come.demo.collection.list;

public class LLOperations {

	public static void main(String[] args) {
		MyLinkedList<Integer> linkedList = new MyLinkedList<>();
		linkedList.insert(1);
		linkedList.insert(2);
		linkedList.insert(3);
		linkedList.insert(4);
		linkedList.insert(5);
		linkedList.insert(6);
		linkedList.insert(7);
		
		linkedList.traverse();
		
		System.out.println("MidElement using counter : " + linkedList.getMidElementUsingCounter());
		System.out.println("MidElement using slow/fast pointers : " + linkedList.getMidElementUsingPointer());
		
		System.out.println("Nth Node From End : " + linkedList.getNthNodeFromEnd(6));
		
		linkedList.swapLLNode(1, 5);
		
		linkedList.traverse();
	}
	
}
