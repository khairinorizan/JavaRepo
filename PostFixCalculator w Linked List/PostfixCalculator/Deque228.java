package Assignment3;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Assignment2.AmusingLinkedList;
import Assignment2.AmusingLinkedList.Node;
import Assignment2.AmusingPreciseNumber;

/**
 * 
 * @author Muhammad Khairi Norizan
 *
 * @param <E>
 */
public class Deque228<E> implements Deque<E> {
	private AmusingLinkedList<E> deque;
	
	/**
	 * Creating a deck using AmusingLinkedList class
	 */
	public Deque228() {
		deque = new AmusingLinkedList<E>();
	}

	@Override
	public boolean addAll(Collection arg0) {
		// TODO Auto-generated method stub
		deque.addAll(arg0);
		return true;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		deque.clear();
		
	}


	@Override
	public boolean containsAll(Collection arg0) {
		// TODO Auto-generated method stub
		deque.containsAll(arg0);
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return deque.size() == 0;
	}

	@Override
	public boolean removeAll(Collection arg0) {
		// TODO Auto-generated method stub
		deque.removeAll(arg0);
		return true;
	}

	@Override
	public boolean retainAll(Collection arg0) {
		// TODO Auto-generated method stub
		deque.retainAll(arg0);
		return false;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return deque.toArray();
	}

	@Override
	public Object[] toArray(Object[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(E arg0) {
		// TODO Auto-generated method stub
		deque.add(arg0);
		
		return true;
	}

	@Override
	public void addFirst(E arg0) {
		// TODO Auto-generated method stub
		deque.add(0, arg0);
	}

	@Override
	public void addLast(E arg0) {
		// TODO Auto-generated method stub
		deque.add(deque.size()-1, arg0);
		
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		deque.contains(arg0);
		
		return true;
	}

	@Override
	public Iterator descendingIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E element() {
		// TODO Auto-generated method stub
		
		return getFirst();
	}

	@Override
	public E getFirst() {
		// TODO Auto-generated method stub
		E retVal = null;
		
		retVal = deque.get(0);
		
		return retVal;
	}

	@Override
	public E getLast() {
		// TODO Auto-generated method stub
		E retVal = null;
		
		retVal = deque.get(deque.size()-1);
		
		return retVal;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean offer(E arg0) {
		// TODO Auto-generated method stub
		add(arg0);
		return true;
	}

	@Override
	public boolean offerFirst(E arg0) {
		// TODO Auto-generated method stub
		addFirst(arg0);
		return true;
	}

	@Override
	public boolean offerLast(E arg0) {
		// TODO Auto-generated method stub
		addLast(arg0);
		return true;
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			return null;
		}
		
		return getFirst();
	}

	@Override
	public E peekFirst() {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			return null;
		}
		
		return peek();
	}

	@Override
	public E peekLast() {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			return null;
		}
		
		return getLast();
	}

	@Override
	public E poll() {
		// TODO Auto-generated method stub
		if(deque.size() == 0) {
			return null;
		}
		
		return deque.remove(0);
	}

	@Override
	public E pollFirst() {
		// TODO Auto-generated method stub
		return poll();
	}

	@Override
	public E pollLast() {
		// TODO Auto-generated method stub
		if(deque.size() == 0) {
			return null;
		}
		
		return deque.remove(deque.size() - 1);
	}
	
	@Override
	public E pop() {
		// TODO Auto-generated method stub
		if(deque.size() == 0) {
			throw new NoSuchElementException();
		}
		
		return deque.remove(0);
	}

	@Override
	public void push(E arg0) {
		// TODO Auto-generated method stub
		addFirst(arg0);
	}

	@Override
	public E remove() {
		// TODO Auto-generated method stub
		return pop();
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		if(!deque.contains(arg0)) {
			return false;
		}else {
			deque.remove(arg0);
			return true;
		}
		
	}

	@Override
	public E removeFirst() {
		// TODO Auto-generated method stub
		return pop();
	}

	@Override
	public boolean removeFirstOccurrence(Object arg0) {
		// TODO Auto-generated method stub
		if(!remove(arg0)) {
			return false;
		}else {
			return true;
		}
		
	}

	@Override
	public E removeLast() {
		// TODO Auto-generated method stub
		return pollLast();
	}

	@Override
	public boolean removeLastOccurrence(Object arg0) {
		// TODO Auto-generated method stub
		int index = deque.lastIndexOf(arg0);
		
		deque.remove(index);
		
		return true;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return deque.size();
	}

}
