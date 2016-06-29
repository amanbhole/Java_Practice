package come.demo.collection.list;


public class MyLinkedList<E> {

	private Node<E> head;

	public Node<E> insert(E data) {
		Node<E> node = new Node<E>(data);

		if(null == head) {
			head = node;
		} else {
			Node<E> temp = head;
			while(temp.next != null) {
				temp = temp.next;
			}
			temp.next = node;
		}
		return node;
	}

	public void traverse() {
		if(null == head) {
			System.out.println("Empty List");
			return;
		}

		Node<E> temp = head;
		while(temp!=null) {
			System.out.print(temp.data + " ");
			temp = temp.next;
		}
		System.out.println();
	}

	public void swapLLNode(E x, E y) {
		// Nothing to do if x and y are same
		if (x.equals(y)) return;
				
		// Search for x (keep track of prevX and CurrX)
		Node<E> prevX = null; Node<E> currX = head;
		while(currX!=null && currX.data!=null && !currX.data.equals(x)) {
			prevX = currX;
			currX = currX.next;
		}
		
		// Search for y (keep track of prevY and currY)
		Node<E> prevY = null; Node<E> currY = head;
		while(currY!=null && currY.data!=null && !currY.data.equals(y)) {
			prevY = currY;
			currY = currY.next;
		}
		
		// If either x or y is not present, nothing to do
		if(currX==null || currY==null) {
			return;
		}
		
		// If x is not head of linked list
		if(prevX != null)
			prevX.next = currY;
		else
			// make x the new head
			head = currY;
		
		// If y is not head of linked list
		if(prevY != null)
			prevY.next = currX;
		else
			// make x the new head
			head = currX;
		
		// Swap next pointers
		Node<E> temp = currX.next;
		currX.next = currY.next;
		currY.next = temp;
	}
	
	//Increment mid->mid.next when counter is odd.
	public E getMidElementUsingCounter() {
		if(null == head)
			return null;
		
		Node<E> current = head;
		Node<E> mid = head;
		int counter = 0;
		while(current != null) {
			if ((counter++ & 1) != 0) {
				mid = mid.next;
			}
			current = current.next;
		}
		return mid.data;
	}
	
	public E getMidElementUsingPointer() {
		if(null == head)
			return null;
		
		Node<E> slow = head;
		Node<E> fast = head;
		while(fast!=null && fast.next!=null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow.data;
	}

	public E getNthNodeFromEnd(int indexFromEnd) {
		if(indexFromEnd < 0)
			return null;
		
		Node<E> reference = head;
		Node<E> main = head;
		
		while (reference != null) {
			reference = reference.next;
			indexFromEnd --;
			if(indexFromEnd < 0)
				main = main.next;
		}
		
		return main.data;
	}
	
	private static class Node<E> {
		private E data;
		private Node<E> next;

		public Node(E data) {
			super();
			this.data = data;
			this.next = null;
		}
	}
}
