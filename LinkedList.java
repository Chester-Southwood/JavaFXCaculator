
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedList<E> extends Object implements List<E>, Serializable, Cloneable 
{

	private class Node<E>
	{
		private E data;
		private Node<E> next;
		
		public Node(Node<E> next, E data)
		{
			this.next = next;
			this.data = data;
		}
		
		public Node(E data)
		{
			this.next = null;
			this.data = data;
		}
	}
	
	public class LinkedListIterator implements Iterator<E>
	{
		private Node<E> curr;
		public LinkedListIterator(Node<E> startingNode)
		{
			this.curr = head.next;
		}
		public boolean hasNext()
		{
			return this.curr!=null;
		}
		public E next()
		{
			if(curr == null)
			{
				throw new NoSuchElementException();
			}
			E temp = (E) curr.data;
			curr = curr.next;
			return temp;
		}
		
	}
	
	private Node<E> head;
	private int size;
	
	public LinkedList()
	{
		this.head = new Node<E>(null);
		this.size = 0;
	}
	
	public LinkedList(Collection<E> c)
	{
		LinkedList<E> collectionLinkedList = new LinkedList<E>();
      
		for(E object : c)
		{
			collectionLinkedList.add(object);
		}
      
      this.head = collectionLinkedList.head;
	}
	
	@Override
	public boolean add(E data) 
   {
	  int lastIndex = size()-1;
	  Node<E> newNode = new Node<E>(data);
     
	  if(data == null)
	  {
	     throw new NullPointerException();
	  }
     else if(isEmpty())
     {
        head.next = newNode;
     }
	  else
	  {
	     Node<E> counterNode = findNode(lastIndex);
	     counterNode.next = newNode;
	  }
	  size++;
	  return true;
	} 

	@Override
	public void add(int index, E data) 
	{
      if(index < 0 || index > size())
      {
         throw new IndexOutOfBoundsException("Index must be > -1 or Index must be <= size");
      }
      else
      {
         Node<E> newNode = new Node<E>(data);
   		if(index == size())
   		{
   			add(data);
   		}
         else if(index == 0)
         {
            newNode.next = head.next;
            head.next = newNode;
         }
   		else
   		{
   			Node<E> prev = findNode(index - 1);
   			newNode.next = prev.next;
   			prev.next = newNode;
   			size++;
   		}
      }
	}
	

	@Override
	public boolean addAll(Collection<? extends E> c) 
	{
      int originalSize = size();
      
		for(E element : c)
		{
			if(element == null)
			{
				throw new NullPointerException();
			}
		}
      
		for(E element : c)
		{
			this.add(element);
		}
      
      if(originalSize != size())
      {
		   return false;
      }
      
      return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) 
	{
      int originalSize = size();
      
      if(index < 0 || index > size())
      {
         throw new IndexOutOfBoundsException("Index " + index + " is out of range, index must be > -1 or <= size.");
      }
      
		for(E element : c)
		{
			if(element == null)
			{
				throw new NullPointerException("Collection cannot add a null value to the LinkedList.");
			}
		}
      
		for(E element : c)
		{
			this.add(index, element);
			index++;
		}
      
      if(originalSize != size())
      {
		   return false;
		}
      
      return true;
	}
	
	public void addFirst(E data)
	{
	     if(data == null)
	     {
	        throw new NullPointerException("Element added cannot be null.");
	     }
	     else
	     {
		   Node<E> newNode = new Node<E>(data);
	   	   newNode.next = head.next;
	   	   head.next = newNode;
	   	   size++;
	     }
	}

	@Override
	public void clear() 
	{
		head.next = null;
		size = 0;		
	}
	
	@Override
	public Object clone()
	{
		LinkedList<E> copy = new LinkedList<E>();
      for(Object data : this)
      {
         copy.add((E) data);
      }
		return copy;
		
	}

	@Override
	public boolean contains(Object itemFound) 
	{
      E itemFoundCheck = (E) itemFound;
		for(E data : this)
		{
			if(itemFound==null ? data==null : data.equals(itemFound))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) 
	{
		for(Object object : c)
		{
			E data = (E) object;
			boolean doesExist = false;
			for(E element : this)
			{
				if(element == null)
				{
					throw new NullPointerException("Collection cannot have a null element");
				}
				if(data.equals(element))
				{
					doesExist = true;
				}
			}
			if(!doesExist)
			{
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean equals(Object other)
	{
		if(other == null)
		{
			return false;
		}
      
		if(!(other instanceof LinkedList))
		{
			return false;
		}
      
		LinkedList<E> that = (LinkedList<E>) other;
      
		if(this.size() == that.size())
		{
			if(this == that)
			{
				return true;
			}
			
			Node<E> thisCurr;
			Node<E> thatCurr;
			
			for(thisCurr = this.head.next, thatCurr = that.head.next; thisCurr!=null; thisCurr = thisCurr.next, thatCurr = thatCurr.next)
			{
				if(!(thisCurr.data.equals(thatCurr.data)))
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public Node<E> findNode(int index)
	{
		if(index > -1 && index < size() || isEmpty())
		{
			Node<E> newNode = head.next;
			for(int x = 0; x<index; x++)
		    {
		       newNode = newNode.next;
		    }
		    return newNode;
		}
		else
		{
			throw new IndexOutOfBoundsException("Index of " + index + " is out of bounds of a LinkedList size of " + size() + ".");
		}
	}

	@Override
	public E get(int index) 
	{
		if(index < 0 || index >= size())
		{
			throw new IndexOutOfBoundsException("Index of " + index + " is out of bounds of a LinkedList size of " + size() + ".");
		}
		Node<E> curr;
		int counter = 0;
		E getData = null;
		
		for(curr = head.next; curr != null; curr = curr.next)
		{
			if(counter == index)
			{
				getData = (E) curr.data;
			}
			counter++;
		}
		return getData;
	}
	
	@Override
	public int hashCode()
	{
		int result = 0;
		for(Node<E> curr = this.head.next; curr != null; curr = curr.next)
		{
			result += curr.data.hashCode();
		}
		return result;
	}

	@Override
	public int indexOf(Object objectLook) 
	{
		int counter = 0;
		for(E object : this)
		{
			if(objectLook==null ? get(counter)==null : objectLook.equals(get(counter)))
			{
				return counter;
			}
			counter++;
		}
		return -1;
	}

	@Override
	public boolean isEmpty() 
	{
		return this.size == 0;
	}

	@Override
	public Iterator<E> iterator() 
	{
		return new LinkedListIterator(this.head.next);
	}

	@Override
	public int lastIndexOf(Object objectLook) 
	{
		int counter = 0;
		int index = 0;
		boolean objectFound = false;
		for(E element : this)
		{
			if(objectLook==null ? get(counter)==null : objectLook.equals(get(counter)))
			{
				index = counter;
				objectFound = true;
			}	
			counter++;
		}
		if(objectFound)
		{
			return index;
		}
		return -1;
	}

	@Override
	public ListIterator<E> listIterator() 
	{
		throw new UnsupportedOperationException();
	}
	

	@Override
	public ListIterator<E> listIterator(int arg0) 
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object removeObject) 
	{
		int index = 0;
		while(index < size())
		{
			if(removeObject==null ? get(index)==null : removeObject.equals(get(index)))
			{
				if(index == 0)
				{
					head.next = head.next.next;
					size--;
				}
				else
				{
					Node<E> prev = findNode(index-1);
					prev.next = findNode(index).next;
					size--;
				}
				return true;
			}
         index++;
		}
		return false;
		
	}

	@Override
	public E remove(int index) 
	{
		E dataRemoved = null;
		if(index < 0 || index >= size())
		{
			throw new IndexOutOfBoundsException("Index: " + index + " is out of range of LinkedList size " + size() + " (index >= 0 || index < size()");
		}
		else
		{
			if(index == 0)
			{
				dataRemoved = head.next.data;
				head.next = head.next.next;
				size--;
				return dataRemoved;
			}
			else
			{
				Node<E> prev = findNode(index - 1);
				Node<E> curr = findNode(index);
				dataRemoved = curr.data;
				prev.next = curr.next;
				size--;
				return dataRemoved;
			}
		}

	}

	@Override
	public boolean removeAll(Collection<?> c) 
	{
      int originalSize = size();
		for(Object object : c)
		{
			if(object == null)
			{
				throw new NullPointerException("Collection cannot have any null values");
			}
		}
		for(Object object : c)
		{
			for(E element : this)
			{
				if(object.equals(element))
				{
					this.remove(object);
				}
			}
		}
      if(size() == originalSize)
      {
         return false;
      }
		return true;
	}

	@Override
	public boolean retainAll(Collection<?> c) 
	{
		LinkedList<E> tempList = new LinkedList<E>();
      int originalSize = size();
		for(Object o : c)
		{
			if(o == null)
			{
				throw new NullPointerException("Collection must have no null values as elements.");
			}
			for(E element : this)
			{
				if(o.equals(element))
				{
					tempList.add(element);
				}
			}
		}
		this.size = tempList.size();
		this.head = tempList.head;
      if(size() == originalSize)
      {
         return false;
      }
		return true;
	}

	@Override
	public E set(int index, E replaceData) 
	{
		   E dataFound = null;
         if(index < 0 || index >= size())
         {
            throw new IndexOutOfBoundsException("Index " + index + " does not within the range of the LinkedList, must be (index < 0 || index >= size())");
         }
         else
         {
			Node<E> foundIndex = findNode(index);
			dataFound = foundIndex.data;
		    foundIndex.data = replaceData;
	   	    return dataFound;
         }
	}

	@Override
	public int size() 
	{
		return this.size;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) 
	{
		if(fromIndex > toIndex || fromIndex < 0 || toIndex > size())
		{
			throw new IndexOutOfBoundsException("The two indexes must be inclusively between 0 and exclusively to size, and the first index smaller than the second index");
		}
		LinkedList<E> sub = new LinkedList<E>();
		int index = fromIndex;
		while(index < toIndex)
		{
			sub.add(findNode(index).data);
			index++;
		}
		return sub; //returns shallow copy
	}

	@Override
	public Object[] toArray() 
	{
		Object[] newArray = new Object[size()];
		int index = 0;
		for(E elements : this)
		{
			newArray[index] = elements;
			index++;
		}
		return newArray;
	}

	@Override
	public <T> T[] toArray(T[] arg0) 
	{
		throw new UnsupportedOperationException();
	}
	
	@Override
	public String toString()
	{
		String LinkedListString = "";
      for(Node<E> curr = head.next; curr != null; curr = curr.next)
      {
         LinkedListString += curr.data + "\n";
      }
      return LinkedListString;
	}
}