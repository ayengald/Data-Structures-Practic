
/**
 * BasicSymbolTable implementation using hash table with separate chaining.
 * @param <Key>
 * @param <Value>
 */
 
/**
 * Sriman Abhishek Yengaldas
 * INFS 519
 * Fall 2016
 */
 
public class ChainingSymbolTable <Key, Value> implements BasicSymbolTable<Key, Value>
{
    	public static final int REHASH_MIN_THRESHOLD = 2;
    	public static final int REHASH_MAX_THRESHOLD = 8;
    	public static final int INITIAL_M = 4;
    	
    	private EntryList<Key,Value>[] items;
	int initialM;
    	private int size;
	private int n;
	public ChainingSymbolTable()
    	{
        	this(INITIAL_M);
    	}
    	public ChainingSymbolTable(int initialM)
    	{
		this.initialM = initialM;
        	items = (EntryList<Key, Value>[]) new EntryList[initialM];
        	for (int i = 0; i < initialM; i++)
            	items[i] = new EntryList<Key, Value>();
    	}
    
	/**
     	  * Gets the number of elements currently in the queue
     	  * @return size
     	*/
    
	public int size()
    	{
        	return this.size;
    	}

    	/**
     	  * Determines if there are not elements in the queue.
     	  * @return true is no elements, false otherwise
     	*/
    	public boolean isEmpty()
    	{
        	return this.size == 0;
    	}

    	/**
     	  * Inserts the value into the table using specified key.  Overwrites
          * any previous value with specified value.
          * @param key
          * @param value
          * @throws NullPointerException if the key or value is null
     	 */
    	
	public void put( Key key, Value value )
    	{
        	if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        		if (value == null) 
			{
            			delete(key);
            			return;
    			}
			if (n >= REHASH_MIN_THRESHOLD*items.length)
				rehash(2*items.length);
		        int hashIndex = hashFunction(key);
        		if (!items[hashIndex].contains(key))
		 		n++;
         		items[hashIndex].put(key, value);
	}
    
	/**
     	  * Finds Value for the given Key.
     	  * @param key
          * @return value that key maps to or null if not found
     	  * @throws NullPointerException if the key is null
     	*/
    
	public Value get( Key key )
    	{
		if (key == null) throw new IllegalArgumentException("argument to get() is null");
        	int hashIndex = hashFunction(key);
        	return items[hashIndex].get(key);
    	}

    	/**
     	  * Removes the Value for the given Key from the table.
     	  * @param key
     	  * @return value that was removed or null if not found
          * @throws NullPointerException if the key is null
     	*/
    
	public Value delete( Key key )
    	{
		if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        	int hashIndex = hashFunction(key);
        	if (items[hashIndex].contains(key))
			n--;
        	items[hashIndex].delete(key);
		if (size >INITIAL_M  && n <= 2*size) 
			rehash(size/2);  // halve table size if average length of list <= 2
    	}

    	/**
     	  * Iterable that enumerates each key in the table.
     	  * @return iterable over the keys
     	 */
	
	public Iterable<Key> keys()
    	{
		Queue<Key> keys = new Queue<Key>();
        	for (EntryList<Key, Value> list : this.items)
        	{
            		for( Key key : list.keys() )
        	    	{
                		keys.enqueue(key);
            		}
        	}
        	return keys;
    	}
	
	public boolean contains(Key key) 
	{
        	if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        	return get(key) != null;
    	} 
	
    
    	/**
     	  * Creates a new table (and thus new hash function), inserts previous
          * items using new hash function into the new table, sets new table.
     	  * @param newM 
     	*/
	
	private void rehash( int newM )
    	{
        	EntryList<Key, Value> temp = new EntryList<Key, Value>(newM);
        	for (int i = 0; i < size; i++) 
		{
            		for (Key key : items[i].keys()) 
			{
                		temp.put(key, items[i].get(key));
            		}
        	}
        	this.size  = temp.size;
        	this.n  = temp.n;
        	this.items = temp.items;
    	}
    
    	/**
     	  * Determines an index in [0,M-1] using specified key to generate hash code.
     	  * @param key with properly implemented hash code
     	  * @param M
     	  * @return index in [0,M-1]
     	*/
    
	private int hashFunction( Key key )
    	{
        	return (key.hashCode() & 0x7fffffff) % size;
    	}

    //------------------ DO NOT MODIFY BELOW THIS LINE -------------------//
    
    /**
     * The expected length of the linked lists, i.e. the load factor.
     * @return N / M
     */
    public double loadFactor()
    {
        return this.getN() / (double)this.getM();
    }
    
    // Utility method
    public int getN()
    {
        return this.size;
    }
    
    // Utility method
    public int getM()
    {
        return this.items.length;
    }
    
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
            
            buf.append( "\n" );
            
            buf.append( key );
            
            buf.append( "->" );
            buf.append( item.toString() );
        }
        buf.append("]");
        return buf.toString();
    }
    
    public String toTableString( boolean verbose )
    {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < this.items.length; i++)
        {
            String prefix = "\n["+i+"] size="+this.items[i].size()+":";
            buf.append( prefix );
            if(verbose)
            {
                boolean first = true;
                for( Key key : this.items[i].keys() )
                {
                    if( first ) first = false;
                    else buf.append( ", " );

                    buf.append( "(" );
                    buf.append( key );
                    buf.append( ")" );
                }
            }
        }
        return buf.toString();
    }
    
    
    
    /**
     * Unit tests the ST data type.
     * @param args 
     */
    public static void main(String[] args)
    {
        ChainingSymbolTable<ProductID,Product> st =
                new ChainingSymbolTable<ProductID,Product>();
        
        if( Stdio.isInteger(args[0]) )
        {
            int n = Integer.parseInt( args[0] );
            java.util.Random rand = new java.util.Random();
            Clock time = new Clock();
            for( int i = 0; i < n; i++ )
            {
                String university = "GMU"+rand.nextInt();
                int identifer     = rand.nextInt();
                String name       = "["+university+","+identifer+"]";
                int age           = 0;
                double grade      = 0.0;

                ProductID key = new ProductID(university, identifer);
                Product value = new Product( key, name, age, grade );
                
                st.put( key, value );

                if( st.get(key) == null ) throw new IllegalStateException("Put failed: "+key);
                if( i % 100000 == 0 ) Stdio.println("Put "+i);
            }
            Stdio.println( "Put "+n+ " items took " +time );
            Stdio.println( "Final symbol table=" +st.toTableString(false) );
            Stdio.printf(  "Load factor=%.2f\n", st.loadFactor() );
        }
        else
        {
            Stdio.open( args[0] );
            while( Stdio.hasNext() )
            {
                String method = Stdio.readString();
                if( method.equalsIgnoreCase("put") )
                {
                    String epc        = Stdio.readString();
                    int serialNumber  = Stdio.readInt();
                    String description= Stdio.readString();
                    int quantity      = Stdio.readInt();
                    double cost       = Stdio.readDouble();

                    ProductID key = new ProductID(epc, serialNumber);
                    Product value = new Product( key, description, quantity, cost );

                    st.put( key, value );
                    //Stdio.println( "put="+key.toString() );
                }
                else if( method.equalsIgnoreCase("delete") )
                {
                    String epc        = Stdio.readString();
                    int serialNumber  = Stdio.readInt();
                    ProductID key = new ProductID(epc, serialNumber);

                    Product deletedValue = st.delete(key);
                    //Stdio.println( "deleted="+deletedValue );
                }
                else if( method.equalsIgnoreCase("get") )
                {
                    String epc        = Stdio.readString();
                    int serialNumber  = Stdio.readInt();
                    ProductID key = new ProductID(epc, serialNumber);

                    Product value = st.get(key);
                    Stdio.println( "get="+value );
                }
                else if( method.equalsIgnoreCase("size") )
                {
                    Stdio.println( "size="+st.size() );
                }
                else if( method.equalsIgnoreCase("isEmpty") )
                {
                    Stdio.println( "isEmpty?="+st.isEmpty() );
                }
                else if( method.equalsIgnoreCase("toString") )
                {
                    Stdio.println( "toString?="+st.toTableString(true) );
                }
            }
            Stdio.println( "Final mappings=" +st.toString() );
            Stdio.println( "Final symbol table=" +st.toTableString(true) );
            Stdio.printf(  "Load factor=%.2f\n", st.loadFactor() );
            Stdio.close();
        }
    }
}
