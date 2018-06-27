import java.util.NoSuchElementException;
import java.lang.NullPointerException;
/**
/**
 * Sriman Abhishek Yengaldas(G01010450)
 * INFS 519
 * Fall 2016
 */

/**
 * Balanced BST symbol table.
 * @param <Key>
 * @param <Value>
 */

/*Program to implement AVLTree Symbol Table*/

public class AVLTreeST<Key extends Comparable, Value>
    implements OrderedSymbolTable<Key, Value>
{
	/*cases 1,2,3 and 4 in put*/
	private Node put( Node node, Key key, Value value )
    	{
        
		if( node == null )
        	{
            		node = new Node(key, value);
            		this.size++;
            		return node;
        	}
        
		else if( key.compareTo( node.key ) < 0  )
        	{
            		node.left   = put( node.left,  key, value );
			if(this.height(node.left)-this.height(node.right) >= 2 )
                 		if( key.compareTo( node.left.key ) < 0)
                     			node = rotateWithLeftChild( node );
                 		else
                     			node = doubleRotateLeft( node );
        	}
        
		else if( key.compareTo( node.key ) > 0 )
        	{
            		node.right = put( node.right, key, value );
			if(this.height(node.right)-this.height(node.left) >= 2 )
                 		if(key.compareTo( node.right.key ) > 0)
                     			node = rotateWithRightChild( node );
                 		else
                     			node = doubleRotateRight( node );
        	}
        
		else
		{
			node.value = value;
		}
        
	   	node.height=max(this.height( node.left ), this.height( node.right ) ) + 1;
		return node;
    
        }
		
	/*left child rotate*/
	private Node rotateWithLeftChild( Node k2 )
    	{
		Node k1 = k2.left;
        	k2.left = k1.right;
            	k1.right = k2;
            	k2.height = max( height( k2.left ), height( k2.right ) ) + 1;
            	k1.height = max( height( k1.left ), k2.height ) + 1;
            	return k1;
    	}
	
	/*right child rotate*/
    	private Node rotateWithRightChild( Node k1 )
    	{
        	Node k2 = k1.right;
            	k1.right = k2.left;
            	k2.left = k1;
            	k1.height = max( height( k1.left ), height( k1.right ) ) + 1;
            	k2.height = max( height( k2.right ), k1.height ) + 1;
            	return k2;
    	}
	
	/*Double rotate left child*/
    	private Node doubleRotateLeft( Node k3 )
    	{
        	k3.left = rotateWithRightChild( k3.left );
            	return rotateWithLeftChild( k3 );
   	}
	
	/*Double rotate right child*/
    	private Node doubleRotateRight( Node k1 )
    	{
        	k1.right = rotateWithLeftChild( k1.right );
        	return rotateWithRightChild( k1 );
    	}



    //--------------------- DO NOT MODIFY BELOW THIS -----------------------//

    private class Node
    {
        private Key key;
        private Value value;
        
        private Node left;
        private Node right;
        
        private int height;
        
        public Node( Key key, Value value )
        {
            this.key   = key;
            this.value = value;
	    
        }
    }
    
    private Node root;
    private int size;
    
    /**
     * Creates a new search tree with default parameters.
     */
    public AVLTreeST()
    {
        this.size = 0;
    }


    private int max( int h1, int h2 )
    {
        return Math.max(h1, h2);
    }
    
    private int height( Node x )
    {
        return ( x == null ) ? -1 : x.height;
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
        return size == 0;
    }
    
    /**
     * Inserts the value into the table using specified key.  Overwrites
     * any previous value with specified value.
     * @param key
     * @param value
     * @throws NullPointerException if key or value is null
     */
    public void put( Key key, Value value )
    {
        this.root = this.put(this.root, key, value);
    }
    
    /**
     * Finds Value for the given Key.
     * @param key
     * @return value that key maps to or null if not found
     */
    public Value get( Key key )
    {
        Node node = this.find(key, this.root);
        return (node!=null)?node.value:null;
    }
    
    /**
     * Recursive function that finds the Node with specified Key or null.
     * @param key
     * @param node
     * @return node for key or null if not found
     */
    private Node find( Key key, Node node )
    {
        if(      node == null )         return null;
        else
        {
            // Do we go left or right?
            int cmp = key.compareTo(node.key);
            if(      cmp < 0 ) return find( key, node.left  );
            else if( cmp > 0 ) return find( key, node.right );
            else     return node;
        }
    }
    
    
    
    public Iterable<Key> keys()
    {
        List<Key> list = new DynamicArray<Key>();
        this.keys(root, list);
        return list;
    }
    
    private void keys( Node x, List list )
    {
        if( x == null ) return;
        this.keys(x.left, list);
        list.add(x.key);
        this.keys(x.right, list);
    }
    
    private void checkEmpty()
    {
        if( this.size == 0 )
        {
            throw new NoSuchElementException("SymbolTable is empty");
        }
    }
    
    public Key min() throws NoSuchElementException
    {
        checkEmpty();
        return this.min(this.root).key;
    }
    
    private Node min( Node t )
    {
        if( t != null )
        {
            while( t.left != null ) t = t.left;
        }
        return t;
    }

    public Key max() throws NoSuchElementException
    {
        checkEmpty();
        return this.max(this.root).key;
    }
    
    private Node max( Node t )
    {
        if( t != null )
        {
            while( t.right != null ) t = t.right;
        }
        return t;
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
            
            buf.append( key );
            
            buf.append( "->" );
            buf.append( item.toString() );
        }
        buf.append("]");
        return buf.toString();
    }
    
    public boolean balanced( )
    {
        if( root == null ) throw new IllegalStateException("Expect root to be non-null");
        return this.balanced(root);
    }
    
    private boolean balanced( Node x )
    {
        if( x.left == null && x.right == null )
        {
            // leaf, height should be zero
            if( x.height != 0 ) return false;
        }
        
        // Check to see if our left and right subtrees are balanced
        boolean lb = (x.left != null) ? balanced(x.left)  : true;
        boolean rb = (x.right!= null) ? balanced(x.right) : true;
        
        if( lb == false || rb == false ) return false;
        
        // If subtrees are balanced, is our height correct
        // Non-leaf, should be one more than left/right height
        int lh = (x.left  != null) ? x.left.height  : -1;
        int rh = (x.right != null) ? x.right.height : -1;
            
        int expectedHeight = max(lh, rh) + 1;
            
        if( x.height != expectedHeight ) return false;
        
        // Everything up to here makes sure the height is correct
        // This final check makes sure balanced condition is met
        int diff = lh - rh;
        if( Math.abs(diff) >= 2 ) return false;
        
        return true;
    }
    
    public static boolean isInteger( String s )
    {
        try
        {
            Integer.parseInt(s);
        }
        catch(NumberFormatException e)
        {
            return false;
        }
        return true;
    }
    
    /**
     * Unit tests the ST data type.
     * @param args 
     */
    public static void main(String[] args)
    {
        AVLTreeST<Integer,String> st = new AVLTreeST<Integer,String>();
        
        if( isInteger(args[0]) )
        {
            int n = Integer.parseInt( args[0] );
            java.util.Random rand = new java.util.Random();
            Clock time = new Clock();
            for( int i = 0; i < n; i++ )
            {
                // Ways to generate test data
                // 1. Generate sorted            (worse case  )
                // 2. Generate uniformily random (average case)
                int key      = i;                // Case 1
                //int key      = rand.nextInt(); // Case 2                

                String value = "value"+key;
                st.put( key, value );

                if( st.get(key) == null ) throw new IllegalStateException("Put failed: "+key);
            }
            Stdio.println( "Put "+n+ " items took " +time );
            Stdio.println( "Balanced? " +st.balanced() );
        }
        else
        {
            Stdio.open( args[0] );
            while( Stdio.hasNext() )
            {
                String method = Stdio.readString();
                if( method.equalsIgnoreCase("put") )
                {
                    int key    = Stdio.readInt();
                    String val = Stdio.readString();
                    st.put( key, val );
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
            Stdio.println( "Balanced? " +st.balanced() );
            Stdio.close();
        }
    }
}

