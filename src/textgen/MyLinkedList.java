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
		head= new LLNode<E>(null);
		tail=new LLNode<E>(null);
		head.next=tail;
		tail.prev=head;
		size=0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		if(element==null){
			throw new NullPointerException();
		}
		LLNode<E> n = new LLNode<E>(element);

		if(head == null) {

			head.next = n;

			n.prev = head;

			n.next = tail;

			tail.prev = n;

		}else {

			tail.prev.next = n;

			n.prev = tail.prev;

			n.next = tail;

			tail.prev = n;

		}

		size++;

		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		if(index<0||index>=size){
			throw new IndexOutOfBoundsException();
		}
			LLNode current=head;
			for(int i=0;i<index;i++){
				current=current.next;
			}
			return (E) current.data;
		// TODO: Implement this method.
	}

	/**
	 * Add an element to the list at the specified index
	 * @param //The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if(element==null){
			throw  new NullPointerException();
		}
		if(index==0){
			LLNode<E> newNode = new LLNode<>(element);
			newNode.next=head;
			head=newNode;
			size++;
			if(tail==null) tail=head;
		}
		else if(index>=size){
			LLNode <E> newNode= new LLNode<>(element);
			if(tail==null){
				head=tail=newNode;
			}
			else{
				tail.next=newNode;
				tail=newNode;
			}
			size++;
		}
		else {
			LLNode<E> current=head;
			for (int i=1;i<index;i++){
				current=current.next;
			}
			LLNode<E> temp=current.next;
			current.next=new LLNode<>(element);
			(current.next).next=temp;
			size++;
		}
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
		if (index < 0 || index >= size) {
		throw new IndexOutOfBoundsException();
		}
		else if(index==0){
			if(size==0) return null; //can be wrong
			else {
				E temp=head.data;
				head=head.next;
				size--;
				if(head==null){
					tail=null;
				}
				return temp;
			}
		}
		else if(index==size-1){
			if(size==0){
				return null;
			}
			else if(size==1){
				E temp = head.data;
				head=tail=null;
				size=0;
				return temp;
			}
			else{
				LLNode<E> current=head;
				for(int i=0;i<size-2;i++){
					current=current.next;
				}
				E temp= tail.data;
				tail=current;
				tail.next=null;
				size--;
				return temp;
			}
		}
		else{
			LLNode<E> previous =head;
			for(int i=1;i<index;i++){
				previous=previous.next;
			}
			LLNode<E> current=previous.next;
			previous.next=current.next;
			size--;
			return current.data;
		}
		// TODO: Implement this method
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
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if(element==null){
			throw new NullPointerException();
		}

		LLNode<E> positionTemp=new LLNode<>(element);
		LLNode<E> positionCurrent= head;
		for(int i=0;i<index;i++){
			positionCurrent=positionCurrent.next;
		}
		positionCurrent.next=positionTemp;
		return positionCurrent.data;
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

}
