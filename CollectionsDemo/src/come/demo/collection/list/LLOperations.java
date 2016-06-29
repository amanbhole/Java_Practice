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

//		linkedList.head.next.next.next = linkedList.head.next;

		if(linkedList.detectLoop())
			System.out.println("Found loop");
		else {
			System.out.println("Linear loop");

			linkedList.traverse();

			System.out.println("Mid-element using counter: " + linkedList.getMidElementUsingCounter());
			System.out.println("Mid-element using slow/fast pointers: " + linkedList.getMidElementUsingPointer());

			System.out.println("5th node from end: " + linkedList.getNthNodeFromEnd(5));

			linkedList.swapPairwise();
			System.out.print("Swapped pair wise list: "); linkedList.traverse();
			
			linkedList.swapLLNode(1, 5);
			linkedList.traverse();
			
			linkedList.head = linkedList.reverseChunk(linkedList.head, 3);
			linkedList.traverse();
			
//			linkedList.deleteAlt();
//			linkedList.traverse();
		}
	}
}
