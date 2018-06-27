
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Sriman Abhishek Yengaldas(G01010450)
 * INFS 519
 * Fall 2016
 */

/**
 * SymbolTable stores key/value pairs where keys map to unique values.
 * Binary search tree is a symbol table that addresses the weaknesses
 * of unordered list and ordered arrays.
 * @param <Key>
 * @param <Value>
 */
public class BinarySearchTreeST<Key extends Comparable<Key>, Value>
    implements OrderedSymbolTable<Key, Value>
{
	          
	private Node root;
	private int size;
	private class Node
	{
		private int size;	// number of nodes in subtree
        	private Key key;           // sorted by key
        	private Value value;         // associated data
        	private Node left, right;  // left and right subtrees
	        public Node(Key key, Value value, int size)
		{
            		this.key = key;
            		this.value = value;
            		this.size = size;
        	}
	}
	public BinarySearchTreeST()
    	{
    	}
    
	/**
     	* Gets the number of elements currently in the symbol table.
     	* @return size
     	*/
    	
	public int size()
    	{
        	return size(root);
    	}
		private int size(Node x)
		{
        		if (x == null)
				return 0;
        		else return x.size;
    		}
	
	/**
     	* Determines if there are no elements in the  symbol table.
     	* @return true if no elements, false otherwise
     	*/
	
	public boolean isEmpty()
    	{
        	return size() == 0;
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
		if (key == null) throw new NullPointerException("first argument to put() is null");
        	root=put(root,key,value);
    	}
		private Node put(Node x, Key key, Value value)
		{
			if (x == null)
				return new Node(key, value, 1);
        		int cmp = key.compareTo(x.key);
        		if(cmp < 0)
				x.left  = put(x.left,  key, value);
        		else if (cmp > 0)
				x.right = put(x.right, key, value);
        		else
        			x.value   = value;
        		x.size = 1 + size(x.left) + size(x.right);
        		return x;
		}
	 
	/**
     	 * Finds Value for the given Key.
    	 * @param key
      	 * @return value that key maps to or null if not found
     	 * @throws NullPointerException if the key is null
     	 */   	
	public Value get( Key key )
    	{
		return get(root, key);
    	}
		private Value get(Node x, Key key)
		{
        		if (x == null)
		 		return null;
        		int cmp = key.compareTo(x.key);
        		if(cmp < 0)
	 			return get(x.left, key);
        		else if (cmp > 0)
				return get(x.right, key);
        		else
		              	return x.value;
    		}
	
   	 /**
       	 * Iterable that enumerates (in sorted order) each key in the table.
         * @return iter
         */

    	public Iterable<Key> keys()
    	{
        	DynamicArray<Key> list = new DynamicArray<Key>();
        	//printR();
        	keys(this.root, list);
        	return list;
    	}

    		private void keys(Node x, DynamicArray<Key> list) 
		{
		  	if (x == null) return;
		        	keys(x.left, list);
        		list.add(x.key);
        		keys(x.right, list);
    		}		

	/**
     	* Finds and returns minimum key
     	* @return smallest key in the symbol table
     	* @throws NoSuchElementException if the symbol table is empty
     	*/
	public Key min() throws NoSuchElementException
    	{
		if (isEmpty())
 			throw new NoSuchElementException("called min() with empty symbol table");
        	return min(root).key;
    	}

		private Node min(Node x) 
		{ 
        		if (x.left == null)
	 			return x; 
        		else
		                return min(x.left); 
    		}  
        
	/**
     	* Finds and returns maximum key
     	* @return largest key in the symbol table
     	* @throws NoSuchElementException if the symbol table is empty
     	*/
	
	public Key max() throws NoSuchElementException
    	{
        	if (isEmpty())
	 		throw new NoSuchElementException("called max() with empty symbol table");
        	return max(root).key;
    	}
		private Node max(Node x) 
		{
        		if (x.right == null) 
				return x; 
        		else
		                return max(x.right); 
    		}

	/**
     	* Removes the minimum key from the symbol table
     	* @throws NoSuchElementException if the symbol table is empty
     	*/
	public void deleteMin( ) throws NoSuchElementException
    	{
		if (isEmpty())
			throw new NoSuchElementException("Symbol table underflow");
		root = deleteMin(root);
    	}
		private Node deleteMin(Node x) 
		{
        		if (x.left == null)
 				return x.right;
        		x.left = deleteMin(x.left);
        		x.size = size(x.left) + size(x.right) + 1;
        		return x;
    		}

 	/**
     	* Removes the maximum key from the symbol table
     	* @throws NoSuchElementException if the symbol table is empty
     	*/ 
   	public void deleteMax( ) throws NoSuchElementException
    	{
		if (isEmpty())
			throw new NoSuchElementException("Symbol table underflow");
        	root = deleteMax(root);
    	}
		 private Node deleteMax(Node x) 
		{
        		if (x.right == null) return x.left;
        		x.right = deleteMax(x.right);
        		x.size = size(x.left) + size(x.right) + 1;
        		return x;
    		}	

	

    
    
    
    //--------------------- DO NOT MODIFY BELOW THIS -----------------------//

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

    /**
     * Unit tests the ST data type.
     * @param args 
     */
    public static void main(String[] args)
    {
        Stdio.open( args[0] );
        BinarySearchTreeST<Integer,String> st = new BinarySearchTreeST<Integer,String>();
        while( Stdio.hasNext() )
        {
            String method = Stdio.readString();
            if( method.equalsIgnoreCase("insert") )
            {
                int key    = Stdio.readInt();
                String val = Stdio.readString();
                st.put( key, val );
                Stdio.println( "insert="+st.toString() );
            }
            else if( method.equalsIgnoreCase("deleteMin") )
            {
                st.deleteMin();
                Stdio.println( "deleteMin" );
            }
            else if( method.equalsIgnoreCase("deleteMax") )
            {
                st.deleteMax();
                Stdio.println( "deleteMax" );
            }
            else if( method.equalsIgnoreCase("size") )
            {
                Stdio.println( "size="+st.size() );
            }
            else if( method.equalsIgnoreCase("min") )
            {
                Stdio.println( "min="+st.min() );
            }
            else if( method.equalsIgnoreCase("max") )
            {
                Stdio.println( "max="+st.max() );
            }
            else if( method.equalsIgnoreCase("isEmpty") )
            {
                Stdio.println( "isEmpty?="+st.isEmpty() );
            }
        }
        Stdio.println( "Final symbol table=" +st.toString() );
        Stdio.close();
    }
}

