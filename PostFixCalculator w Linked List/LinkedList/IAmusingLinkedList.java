package Assignment2;

public interface IAmusingLinkedList<E> {
	//Appends the specified element to the end of this list
	public boolean add(E e);
	//Insert the specified element at the specified position in this list
	public void add(int index, E element);
	//Returns true if this list contains the specified element
	public boolean contains(Object o);
	//Returns the element at the specified position in this list
	public E get(int index);
	//Returns true if this list contains no elements
	public boolean isEmpty();
	//Removes the element at the specified position in this list
	public E remove(Object o);
	//Returns the number of elements in this list
	public int size();
	
}
