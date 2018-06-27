
/**Program to implement a queue using single linked list.
 *  The {@code Queue} class represents a first-in-first-out (FIFO) queue of generic items.
 *  It supports Enqueue and Dequeue operations in FIFO order.
 *  The class tests if the queue is empty,
 *  peeking at the first item and iterate through in FIFO order.
 *  The implementation uses the single linked list with
 *  a non-static nested class for linked-list nodes.
 *  The time complexity of enqueue, dequeue, peek, size and isEmpty operations operations
 *  all take constant time in the worst case. 
 */

import java.util.Iterator;
import java.util.NoSuchElementException;
public class Queue <Type> implements QueueAPI<Type>
{	
	private int n;		// elements in a queue
    	private Node first;	// beginning of queue
    	private Node last;	// end of queue
    	private class Node
	{
        	private Type item;
        	private Node next;
    	}
	
	/**
	 * Empty queue initialization
	 */
	
    	public Queue()
    	{
		first = null;
        	last  = null;
        	n = 0;
        }
   	
	/**
	 * Returns number of items in the queue.
   	 * @return size
   	 */
	
	public int size()
    	{
        	return n;
    	}
	
	/**
     	 * Determines if queue is empty.
     	 * @return true if empty, false otherwise
         */
    	
	public boolean isEmpty()
    	{
        	return first == null;
    	}
	
	/**
         * Adds an item to the end of the queue.
         * @param item 
     	 */    	
	
	public void enqueue( Type item )
    	{
       		Node oldlast = last;
        	last = new Node();
        	last.item = item;
        	last.next = null;
        	if (isEmpty())
			first = last;
        	else
           		oldlast.next = last;
        	n++;
    	}
	
	/**
     	 * Returns item at front of queue without removing.
         * @return item at front
         * @throws java.util.NoSuchElementException if empty
         */
    	
	public Type peek()
    	{
		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");
		return first.item;
    	}
	
	/**
     	 * Removes item from front of queue.
      	 * @return item removed from front
     	 * @throws java.util.NoSuchElementException if empty
     	 */
    	
	public Type dequeue( )
    	{
		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");
        	Type item = first.item;
        	first = first.next;
        	n--;
        	if (isEmpty())
		last = null;
        	return item;
    	}
	
	
    	public Iterator<Type> iterator()
    	{
        	return new ListIterator();
    	}
	
	private class ListIterator implements Iterator<Type>
	{
        	private Node current = first;
		public boolean hasNext()
		{
			return current != null;
                }
	        public void remove()
	        {
			throw new UnsupportedOperationException();
	  	}
		public Type next()
		{
			if (!hasNext())
				throw new NoSuchElementException();
            		Type item = current.item;
            		current = current.next; 
            		return item;
        	}
    	}
    
    //--------------------- DO NOT MODIFY BELOW THIS -----------------------//
    
    @Override
    public String toString()
    {
        // Uses the iterator to build String
        StringBuilder buf = new StringBuilder();
        boolean first = true;
        buf.append("[");
        for (Type item : this)
        {
            if( first ) first = false;
            else buf.append( ", " );
            buf.append( item.toString() );
        }
        buf.append("]");
        return buf.toString();
    }
    
    
    /**
     * Unit tests the Queue data type.
     * @param args 
     */
    public static void main( String[] args )
    {
        if( args.length != 1 )
        {
            String u = "Usage: java Queue <filename> \n"+
                       "  e.g: java Queue operations.txt";
            Stdio.println(u); 
            return;
        }

        Queue queue = new Queue();
        
        Stdio.open( args[0] );
        while( Stdio.hasNext() )
        {
            String operation = Stdio.readString();
            if( operation.equals("enqueue") )
            {
                String item = Stdio.readString();
                Stdio.println( "enqueue "+ item );
                queue.enqueue(item);
            }
            else if( operation.equals("dequeue") )
            {
                Stdio.println( "dequeue "+ queue.dequeue() );
            }
            else if( operation.equals("peek") )
            {
                Stdio.println( "peek "+ queue.peek() );
            }
            else if( operation.equals("size") )
            {
                Stdio.println( "size "+ queue.size() );
            }
        }
        
        Stdio.println( "Queue=" +queue.toString() );
        
        Stdio.close();
    }
}
