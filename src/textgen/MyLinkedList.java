package textgen;

import java.util.AbstractList;

/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */

public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		head = new LLNode<>(null);
		tail = new LLNode<>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method

//		if (element == null) {
//			throw new NullPointerException();
//		}
//		LLNode<E> newNode = new LLNode<>(element);
//		tail.prev.next = newNode;
//		newNode.prev = tail.prev;
//		newNode.next = tail;
//		tail.prev = newNode;
//		size++;

		add(size, element);
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> temp = head.next;
		// TODO: Implement this method.
		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}
		return temp.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if (element == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> temp = head.next;
		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}
		LLNode<E> newNode = new LLNode<>(element);
		temp.prev.next = newNode;
		newNode.prev = temp.prev;
		newNode.next = temp;
		temp.prev = newNode;
		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> temp = head.next;
		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}

		temp.prev.next = temp.next;
		temp.next.prev = temp.prev;

		temp.prev = null;
		temp.next = null;
		size --;
		return temp.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if (element == null) {
			throw new NullPointerException();
		}

		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> temp = head.next;
		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}
		E ele = temp.data;
		temp.data = element;
		return ele;
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		LLNode<E> temp = head.next;
		for (int i = 0; i < size; i++) {
			output.append(temp).append(" ");
			temp = temp.next;
		}
		return "MyLinkedList size = " + size + " { " + output.toString() + '}';
	}
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

	@Override
	public String toString() {
		return "[" + data + ']';
	}
}
