import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SymbolTable implementation that holds key/value pairs in a singly linked list.  
 * @param <Key>
 * @param <Value>
 */
 
 /**
 * Sriman Abhishek Yengaldas
 * INFS 519
 * Fall 2016
 */
 
public class EntryList <Key, Value> implements BasicSymbolTable <Key, Value>
{
	private int n;           
    	private Entry first;
	
	private class Entry
    	{
		private Key key;
        	private Value val;
        	private Entry next;
    	}
	public EntryList(Key key, Value val, Entry next)
    	{
		this.key=key;
		this.val=val;
		this.next=next;
    	}

    
    	/**
     	  * Gets the number of elements currently in this symbol table
      	  * @return size
     	  */
    
	public int size()
    	{
        	return n;
    	}

        /**
      	  * Determines if there are not elements in this symbol table.
          * @return true if no elements, false otherwise
     	  */
    	
	public boolean isEmpty()
    	{
        	return size() == 0;
    	}

  	/**
          * Inserts the value into this symbol table using specified key.  Overwrites
          * any previous value with specified value.
     	  * @param key
     	  * @param value
     	  * @throws NullPointerException if the key or value is null
      	  */
    
	public void put( Key key, Value value )
    	{
     	   	if (value == null) 
		{
            		delete(key);
            		return;
        	}
	        for (Entry x = first; x != null; x = x.next) 
		{
            		if (key.equals(x.key)) 
			{
                		x.value = value;
                		return;
            		}
        	}
        	first = new Entry(key, value, first);
        	n++;
    	}

    	/**
     	  * Finds Value for the given Key.
     	  * @param key
     	  * @return value that key maps to or null if not found
     	  * @throws NullPointerException if the key is null
     	  */
    
	public Value get( Key key )
    	{
		for (Entry x = first; x != null; x = x.next) 
		{
            		if (key.equals(x.key))
			return x.val;
        	}
        		return null;
    	}
	
	/**
     	 * Removes the Value for the given Key from this symbol table.
     	 * @param key
     	 * @return value that was removed or null if not found
     	 * @throws NullPointerException if the key is null
     	 */
	
	public Value delete( Key key )
    	{
        	first = delete(first, key);
    	}
	
	private Entry delete(Entry x, Key key) 
	{
        	if (x == null)
	 		return null;
        	if (key.equals(x.key)) 
		{
            		n--;
            		return x.next;
        	}
        	x.next = delete(x.next, key);
        	return x;
    	}

    /**
     * Iterable that enumerates each key in this symbol table.
     * @return iter
     */
    public Iterable<Key> keys()
    {
        Iterable<Key> iterable = new Iterable<Key>();
        for (Entry x = first; x != null; x = x.next)
        	iterable.enqueue(x.key);
        return iterable;         
    	}

    //------------------ DO NOT MODIFY BELOW THIS LINE -------------------//
    
    @Override
    public String toString()
    {
        // Uses the iterator to build String
        StringBuilder buf = new StringBuilder();
        boolean first = true;
        buf.append("[");
        for (Key key : this.keys())
        {
            Value item = this.get(key);
            if( first ) first = false;
            else buf.append( ", " );
            
            buf.append( key );
            
            buf.append( "->" );
            buf.append( item.toString() );
        }
        buf.append("]");
        return buf.toString();
    }
    
}


