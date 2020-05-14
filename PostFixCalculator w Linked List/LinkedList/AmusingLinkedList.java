package Assignment2;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
/**
 * 
 * @author Muhammad Khairi Norizan
 *
 * @param <E>
 */
public class AmusingLinkedList<E> implements List<E> {
	private Node head;
	private int size;
	
	/**
	 * Create LinkedList with no dummy node and head is null
	 */
	public AmusingLinkedList() {
		head = null;
		size = 0;
	}
	
	@Override
	public boolean add(E e) {
		// TODO Auto-generated method stub
		Node newNode = new Node(e, null, null);
		
		if(head == null) {
			head = newNode;
			head.next = head;
			size++;

		}else {
			Node last = getNodeAtIndex(size - 1);
			last.next = newNode;
			newNode.next = head;
			size++;
		}
		
		
		rewireList();
		
		return true;
	}

	@Override
	public void add(int index, E element) {
		// TODO Auto-generated method stub
		if((index >= size || index < 0) && index != 0) {
			throw new IndexOutOfBoundsException();
		}
		
		Node newNode = new Node(element, null ,null);
		
		if(index ==  0) {
			if(head == null) {
				add(element);
			}else {
				Node tempNode = getNodeAtIndex(index);
				Node lastNode = getNodeAtIndex(size-1);
				
				head = newNode;
				
				head.next = tempNode;
				lastNode.next = head;
			
				size++;
			}
			
			
			
		}else {
			
			Node tempNode = getNodeAtIndex(index);
			
			Node currNode = getNodeAtIndex(index-1);
			
			currNode.next = newNode;
			
			newNode.next = tempNode;
			
			size++;
			
		}
		
		
		rewireList();
	
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		if(c.size() == 0) {
			return false;
		}
		
		Iterator<?> cIter = c.iterator();
		int i = 0;
		
		while(cIter.hasNext() && i < c.size()) {
			E element = (E) cIter.next();
			
			add(element);
			i++;
		}
		

		rewireList();
		
		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		// TODO Auto-generated method stub
		if(c.size() == 0) {
			return false;
		}
		
		int i = 0;
		
		Object[] cToArray = c.toArray();
		
		for(int j = c.size()-1; j >= 0; j--) {
			add(index, (E)cToArray[j]);
		}
		
		rewireList();
		
		return true;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		head.next = null;
		head.prev = null;
		head = null;

		size = 0;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		
		if(head == null) {
			return false;
		}
			
		if(head.data == o) {
			return true;
		}else {
			Node curr = head.next;
					
			while(!curr.equals(head)) {
				if(curr.data == o) {
					return true;
				}
				
				curr = curr.next;
			}
			return false;
				
		}
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		if(c.size() == 0) {
			return false;
		}
		
		Iterator<?> cIter = c.iterator();
		
		int i = 0;
		
		Node currNode = head;
		
		while(cIter.hasNext() && i < c.size()) {
			E cCurrElement = (E)cIter.next();
			
			if(!contains(cCurrElement)) {
				return false;
			}
			
			i++;

		}

		return true;
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		Node getNode = getNodeAtIndex(index);
		
		return getNode.data;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		if(!contains(o) && o != null) {
			return -1;
		}

		Node curr = head;
		int index = 0;
		
		while(!(curr.data == o)) {
			curr = curr.next;
			index++;
		}
		
		return index;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		Iterator<E> iterator = new AmusingListIterator();
		
		return iterator;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		if(!contains(o)) {
			return -1;
		}else {
			
			int lastIndex = 0;
			
			if(head.data == o) {
				lastIndex = 0;
			}
			
			Node currNode = head.next;
			int i = 1;
			
			while(currNode != head && i < size) {
				
				if(currNode.data == o) {
					lastIndex = i;
				}
				currNode = currNode.next;
				i++;
				
			}
			
			
			return lastIndex;
		}
		
		
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		ListIterator<E> li= new AmusingListIterator();
		
		return li;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		if(index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		
		ListIterator<E> li= new AmusingListIterator();	
		
		for(int i=0; i < index; i++) {
			li.next();
		}
		
		return li;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		if(!contains(o)) {
			return false;
		}else {
			
			while(contains(o)) {
				if(head.data.equals(o)) {
					if(size == 1) {
						clear();
					}else {
						
						Node lastNode = getNodeAtIndex(size-1);
						
						lastNode.next = head.next;
						
						head = getNodeAtIndex(1);
						
						size--;
						
					}
					
				}else {
					Node curr = head;
					
					while(curr.next.data != o) {
						curr = curr.next;
					}
					
					if(curr.next.data == o) {
						curr.next = curr.next.next;
						size--;
					}
					
				}
				
				rewireList();
				
			}
			
			return true;
		}
		

	}

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		
		Node retVal = getNodeAtIndex(index);
		
		if(index == 0 && size == 1) {
			clear();
			
		}else if(index == 0) {
			
			Node lastNode = getNodeAtIndex(size-1);
			
			head = head.next;
			lastNode.next = head;
			size--;
			
		}else {
			Node prevNode = getNodeAtIndex(index-1);
			Node targetedNode = getNodeAtIndex(index);
			
			prevNode.next = targetedNode.next;

			size--;
			
		}
		
		rewireList();
		
		return retVal.data;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		if(c.size() == 0) {
			throw new NoSuchElementException();
		}
		
		Iterator<?> cIter = c.iterator();
		
		while(cIter.hasNext()) {
			E currItem = (E)cIter.next();
			
			if(contains(currItem)) {
				remove(currItem);
			}
		}
		
		rewireList();
		
		return true;
			
		
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		if(c.size() == 0) {
			throw new NoSuchElementException();
		}
		
		Iterator<?> cIter = c.iterator();
		Node currNode = head;
		int i = 0;
		
		while(i < size) {
			if(!c.contains(currNode.data)) {
				remove(currNode.data);
				
				currNode = head;
				i = 0;
				
				System.out.println(this.toString());
				//size--;
			}else {
				currNode = currNode.next;
				i++;
			}
			
			
			
			
		}
		
		rewireList();
		return true;

	}

	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		int i = 0;
		
		Node curr = head;
		
		while(i != index) {
			curr = curr.next;
			i++;
		}
		
		E retVal = curr.data;
		
		curr.data = element;
		
		
		return retVal;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		if(fromIndex > toIndex || fromIndex > size || toIndex > size) {
			throw new IndexOutOfBoundsException();
		}
		
		//Object[] toArray = toArray();
		AmusingLinkedList<E> subList = new AmusingLinkedList<E>();
		Node currNode = head;
		Iterator<?> subListIter = subList.iterator();
		int i = 0;
		
		while(i <= fromIndex) {
			currNode = currNode.next;
			i++;
		}		
		
		i = fromIndex;
		
		while(i < toIndex+1) {
			Node curr = getNodeAtIndex(i);
			subList.add(curr.data);
			i++;
		}
		
		subList.rewireList();
		
		return subList;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		Object[] linkedListToArray = new Object[size];
		
		if(head == null) {	
			return null;
		}
		
		Node curr = head;
		
		for(int i = 0; i < size; i++) {
			linkedListToArray[i] = curr.data;
			curr = curr.next;
		}
		
		return linkedListToArray;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		T[] t= (T[])new Object[3];
		
		
		// TODO Auto-generated method stub
	
		
		return null;
	}
	
	@Override
	public String toString() {
		String retVal = "";
		String val = "";
		
		if(head == null) {
			retVal = null;
		}else {

			Node currNode = head;
			int i = 0;
			
			while(i < size ) {
				if(currNode.prev != null) {
					if(currNode.data == null) {
						val = i + " " + Integer.toString(indexOf(currNode.prev.data)) + " " + Integer.toString(indexOf(currNode.next.data)) + " " + null + "\n";
					}else {
						val = i + " " + Integer.toString(indexOf(currNode.prev.data)) + " " + Integer.toString(indexOf(currNode.next.data)) + " " + currNode.data.toString() + "\n";
					}
					
				}else {
					if(currNode.data == null) {
						val = i + " " + "-1" + " " + Integer.toString(indexOf(currNode.next.data)) + " " + "null" + "\r\n";
					}else {
						val = i + " " + "-1" + " " + Integer.toString(indexOf(currNode.next.data)) + " " + currNode.data.toString() + "\r\n";
					}
					
				}
				
				i++;
				currNode = currNode.next;	
				retVal += val;
			}
			
			
		}
			
		return retVal;
	}
		
		
	/**
	 * Get node at the specific index
	 * 
	 * @param index
	 * @return
	 * Node at the specific index
	 */
	public Node getNodeAtIndex(int index) {
		
		if(index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		int i = 0;
		Node cur = head;
		
		while(i != index) {
			cur = cur.next;
			i++;
		}
		
		return cur;
	}
	
	/**
	 * A helper method to link all of the nodes properly
	 */
	private void rewireList(){
		
		if(head == null) {
			head = null;
			
		}else if(size == 1) { //only head existed
			head.next = head;
			head.prev = head;
		}else {
			
			Node currNode = head.next;
			
			if(size % 2 != 0) { //last Node is even number
				head.prev = getNodeAtIndex(size-1);
			}else {
				head.prev = getNodeAtIndex(size-2);
			}
				
			int i = 1;
				
			while(i < size && currNode != head) {
					
				if(i % 2 != 0) {
					currNode.prev = null;
				}else {
					currNode.prev = getNodeAtIndex(i-2);
				}

				currNode = currNode.next;
				i++;
			}
				
		}
	}
	
	/**
	 * Inner node class
	 * 
	 * @author Muhammad Khairi Norizan
	 *
	 */
	public class Node{
		private E data;
		private Node next;
		private Node prev;
		
		public Node(E d, Node n, Node p) {
			data = d;
			next = n;
			prev = p;
		}
		
		public E getData() {
			if(data == null) {
				return null;
			}else {
				return data;
			}
			
		}
		
		public E getNext() {
			if(next == null) {
				return null;
			}else {
				return next.data;
			}
			
		}
		
		public E getPrev() {
			if(prev == null) {
				return null;
			}else {
				return prev.data;
			}
			
		}
	}
	
	/**
	 * Amusing Linked List Iterator
	 * 
	 * @author Muhammad Khairi Norizan
	 *
	 */
	private class AmusingListIterator implements ListIterator<E>{
		private Node iterPos;
		private int indexToRemove;
		private int position;
		private boolean prevIsCalled;
		
		private AmusingListIterator() {
			iterPos = head;
			position = -1;
			//position = -0.5;
			indexToRemove = -1;
			prevIsCalled = false;
		}

		@Override
		public void add(E arg0) { // add the new item before the cursor
			// TODO Auto-generated method stub
			
			if(iterPos == null) {
				AmusingLinkedList.this.add(arg0);
				iterPos = head;
				position++;
				
			}else if(position == size-1) {
				AmusingLinkedList.this.add(arg0);
				iterPos = iterPos.next;
				position++;
				
				
			}else {
				
				AmusingLinkedList.this.add(position+1, arg0);
				position++;
				iterPos = getNodeAtIndex(position);

			}
			
			indexToRemove = -1;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if(position+1 != size){
				return true;
			}else {
				return false;
			}
	
		}

		@Override
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			if(position == -1) {
				return false;
			}else if(position-1 >= 0){
				return true;
			}else {
				return false;
			}
			
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
			
			if(hasNext()) {
				if(size == 0) {
					
					throw new RuntimeException();
				}else if(position == size-1) {
					
					iterPos = iterPos.next;
					E retVal = iterPos.data;
					
					position = 0;
					indexToRemove = position;
					prevIsCalled = false;
					
					return retVal;
				}else if(position == -1) {
					
					E retVal = iterPos.data;
					position++;
					indexToRemove = position;
					prevIsCalled = false;
					
					return retVal;
				}else{
								
					iterPos = iterPos.next;
					E retVal = iterPos.data;				
					position++;
					indexToRemove = position;
					prevIsCalled = false;
					
					return retVal;
				}
			}else {
				position++;
				throw new NoSuchElementException();
			}

		}

		@Override
		public int nextIndex() {
			// TODO Auto-generated method stub
			if(position == size-1) {
				return 0;
			}else {
				return position+1;
			}
		}

		@Override
		public E previous() {
			// TODO Auto-generated method stub
			if(size == 0) {
				throw new RuntimeException();
			}
			
			if(hasPrevious()) {
				if(iterPos == head) {
					iterPos = iterPos.prev;
					
					if(size % 2 == 0) { //last element is odd
						position = size - 2;
					}else { //last element is even
						position = size - 1;
					}
					
					indexToRemove = position;
					
				}else {
					if(position >= size) {
						position--;
					}else {
						position--;
						iterPos = getNodeAtIndex(position);
					}
					
				}
				
				prevIsCalled = true;
				return iterPos.data;
			}else {
				position--;
				throw new NoSuchElementException();
			}
			
		}

		@Override
		public int previousIndex() {
			// TODO Auto-generated method stub
			if(position == 0) {
				if(size % 2 == 0) {//last index is odd
					return size-2;
				}else {//last index is even
					return size-1;
				}
			}else if(position == -1){
				return -1;
			}else {
				return position-1;
			}

		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			if(size == 0) {
				throw new UnsupportedOperationException();
			}
						
			if(indexToRemove == -1) {
				throw new IllegalStateException();
			}
			//1234
			if(prevIsCalled == true) {
				iterPos = getNodeAtIndex(indexToRemove-1);
				AmusingLinkedList.this.remove(indexToRemove);
				position--;
			}else {
				AmusingLinkedList.this.remove(indexToRemove);
				position--;	
			}
			
			indexToRemove = -1;
		}

		@Override
		public void set(E arg0) {
			// TODO Auto-generated method stub
			if(indexToRemove == -1) { // next or previous has not been called
				throw new IllegalStateException();
			}
			
			iterPos.data = arg0;
			
		}
		
	}


}
