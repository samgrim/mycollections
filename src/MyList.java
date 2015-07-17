import java.util.Iterator;
import java.util.ListIterator;


public class MyList<E> implements Iterable<E>{
	
	private Node<E> header;
	private int size;
	
	public MyList(){
		header = new Node<E>();
		header.next = header.prev = header;
		size = 0;
	}
	
	private class Node <N> {
		Node <N> prev;
		Node<N> next;
		N value;
	}
	
	/**Add to tail*/
	public void add(E e) {
		addBefore(header, e);
	}
	/**Add to head*/
	public void addFirst(E e) {
		addBefore(header.next, e);
	}
	
	private void addBefore(Node<E> n, E e) {
		Node<E> temp = new Node<E>();
		temp.value = e;
		temp.next = n;
		temp.prev = n.prev;
		temp.prev.next = temp;
		n.prev = temp;
		size ++;
	}
	
	public int length() {
		return size;
	}
	
	private Node<E> getNodeAt(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Invalid element index " + index);
		
		Node<E> n = header;
		if(index > size/2) {
			for(int i = 0; i< size - index; i++)
				n = n.prev;
		} else {
			for(int i = 0; i<= index; i++)
				n = n.next;
		}
		return n;
	}
	

	public E getElementAt(int index) {					
		return getNodeAt(index).value;	
	}
	
	
	public void add(int index, E e){
		if(index == size) {
			add(e);
		} else {
			Node<E> n = getNodeAt(index);
			addBefore(n, e);
		}
	}
	
	@Override
	public String toString() {
		Node<E> n = header;
		StringBuilder builder = new StringBuilder("[");
		while(n.next!=header) {
			n = n.next;
			builder.append(n.value.toString());
			if(n.next != header) {
				builder.append(", ");
			}
		}
		builder.append("]");
		return builder.toString();
	}
	
	/**Index of first occurrence*/
	public int indexOf(E e) {
		int res = -1;
		Node<E> n = header;
		for (int i = 0; i< size; i++){
			n = n.next;
			if(n.value.equals(e)) {
				res = i;
				break;
			}
		}
		return res;
	}
	
	private Node<E> getNodeOf(E e) {
		Node<E> n = header;
		Node<E> res = null;
		for (int i = 0; i< size; i++) {
			n = n.next;
			if(n.value.equals(e)){
				res = n;
				break;
			}
		}
		return res;
	}
	
	/**Removes first occurrence*/
	public boolean remove(E e){
		Node<E> n = getNodeOf(e);
		if (n == null) return false;
		remove(n);
		return true;
	}
	
	/**Removes last*/
	public void remove() {
		if(size < 1)
			throw new IndexOutOfBoundsException("Unable to remove element: list is empty");
		remove(header.prev);
	}
	
	public void removeFirst() {
		if(size < 1)
			throw new IndexOutOfBoundsException("Unable to remove element: list is empty");
		remove(header.next);
	}
	
	private void remove(Node<E> n) {
		n.prev.next = n.next;
		n.next.prev = n.prev;
		size --;
	}
	
	public void removeElementAt(int index) {
		Node <E> n = getNodeAt(index);
		remove(n);
	}
	
	/**/
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>(){
			
			private Node <E> current = header;

			@Override
			public boolean hasNext() {
				return current.next != header;
			}

			@Override
			public E next() {
				current = current.next;
				return current.value;
			}

			@Override
			public void remove() {
				MyList.this.remove(current);
			}
			
		};
	}
	
	public ListIterator<E> listIterator() {
		return new ListIterator<E>(){
			
			private Node <E> next = header.next;
			private Node <E> lastReturned = null;
			private int nextIndex;

			@Override
			public boolean hasNext() {
				return next !=header;
			}

			@Override
			public E next() {
				lastReturned = next;
				next = next.next;
				nextIndex++;
				return lastReturned.value;
			}

			@Override
			public boolean hasPrevious() {
				return lastReturned.prev != header;
			}

			@Override
			public E previous() {
				/*если эл-т перед next не удалялся*/
				if (lastReturned == next.prev) {
					next = next.prev;
					nextIndex--;
				} 
				lastReturned = next.prev;
				return lastReturned.value;
			}

			@Override
			public int nextIndex() {
				return nextIndex;
			}

			@Override
			public int previousIndex() {
				return nextIndex-1;
			}

			@Override
			public void remove() {
				MyList.this.remove(lastReturned);
				nextIndex--;
			}

			@Override
			public void set(E e) {
				lastReturned.value = e;				
			}

			@Override
			public void add(E e) {
				MyList.this.addBefore(next, e);
			}
			
		};
	}
	
	
}
