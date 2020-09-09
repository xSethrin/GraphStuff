

/**
* Karin Knapp
* Lab 3
* Implements a single linked list
*/

public class SLList<E> {
	private Node<E> head;
	private int size = 0; //the number of elements in list


	/**Constructors*/
	public SLList(E item) {
		head = new Node<E>(item);
		size++;
	}

	public SLList(){
		head = null;
		size = 0;
	}

	/** Remove all contents from the list, so it is once again
	empty*/
	public void clear(){
		head = null;
		size = 0;
	}

	/** Insert an element at the given location.
	* allows you to insert after the tail
  * @param item The element to be inserted.
	*/
    public void insert(int index, E item) {
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		if(index == 0) { //new head
			addFirst(item);//helper method
		} else{
			Node<E> node = getNode(index - 1); //using a helper
			addAfter(node, item);
		}
	}
	/**
	* Creates a new head
	* @param item the item to go in the new head
	*/
	private void addFirst(E item) {
		head = new Node<E>(item, head);
		size++;
	}

	/**
	* adds a node to the end of the list
	* @param item the item to go in the new head
	* @param node the node pointing to new head
	*/
	private void addAfter(Node<E> node, E item) {
		node.setNext(new Node<E>(item, node.getNext()));
		size++;
	}

	/**
	* helper that returns the node at a given index
	@param index the index of the node to get
	*/
	private Node<E> getNode(int index){
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		Node<E> node = head;
		for(int i = 0; i < index; i++) {
			node = node.getNext();
		}
		return node;
	}

	/** Append an element at the end of the list.
	*  @param item The element to be appended.
	*/
	public void add(E item){
		insert(size, item);
	}

	/**
	* Remove the  element at the given location.
	* @param index the index we are removing the node at
	*/
	public void remove(int index){
      if(index < 0 || index >= size){
         throw new IndexOutOfBoundsException(Integer.toString(index));
      }
      if(index ==0){
         removeFirst();
      }  else{
         	Node<E> node = getNode(index-1);
          removeAfter(node);
      }
  }

	/**
	* removes the head
	*/
  public void removeFirst(){ //remove helper
         head = head.getNext();
				 size--;
   }

	/**
	* a helper method for the remove method
	*/
	 private void removeAfter(Node<E> node){
      Node<E> temp = node.getNext();//make temp of the node after
      if(temp != null){//make sure that there is something there
        node.setNext(temp.getNext());
        size--;
      }
    }

	/**
	* Get the element in the position to one step left.
	* @return element in the node to the left of the node at the index,
	* null if at the head.
	*/
	public E prev(int index){
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		Node<E> node = getNode(index - 1);
		return node.getElement();
	}


	/** Get the element in the position one step right.
	* @return the element in the node to the right of
	* the node at the index, null if at the end.
	*/
	public E next(int index){
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		Node<E> node = getNode(index + 1);
		return node.getElement();
	}


	/** @return The number of elements in the list. */
	public int length(){
		return size;
	}


	/** Turn the contents of the Nodes to a string in order from head to end.
	* @return The String representation of the
	* elements in the list from head to end.
	* A => B => C
	*/
	public String toString(){
		Node<E> node = head;
		String result = "";
		while(node != null) {
			result = result + node.getElement().toString();
			if(node.getNext() != null) {
				result = result + " ==> ";
			}
			node = node.getNext();
		}
		return result;
	}

	/** Reverse the content of the list.
	* if list is A => B => C it becomes C => B => A
	*/
	public void reverse(){
		Node<E> node = head;
		if(node ==null || node.getNext() == null) {
			return;
		}
		Node<E> prev = node.getNext();
		Node<E> curr = prev.getNext();
		prev.setNext(node);
		node.setNext(null);

		while(curr != null) {
			Node<E> next = curr.getNext();
			curr.setNext(prev);
			prev = curr;
			curr = next;
		}
		head = prev;
	}


	/** @return The  element at given position. */
	public E getValue(int index){
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		Node<E> node = getNode(index);
		return node.getElement();
	}

	/**inserts the given list after the given index
	* @param list the list to be inserted
	* @param index the index for insertion (after the index)
	*/

	public void insertList (SLList list, int index) {
		// if(index < 0 || index > size) {
		// 	throw new IndexOutOfBoundsException(Integer.toString(index));
		// }
		if(list.getHead() != null && (index >= 0 && index < size)) {
			Node<E> head = list.getHead(); //new list head
			Node<E> tail = list.getLast(); //new list tail
			Node<E> position = getNode(index);
			Node<E> temp = position.getNext();
			position.setNext(head);
			tail.setNext(temp);
			size = list.length() + size;
		}
	}

	/**
	* @ returns the head of the list
	*/
	public Node<E> getHead(){
		return head;
	}

	/**
	* @ return the tail of the list
	*/
	public Node<E> getLast(){
		return getNode(size - 1);
	}
}
	