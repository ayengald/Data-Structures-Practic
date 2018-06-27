
/**
 
  * SRIMAN ABHISHEK YENGALDAS(G01010450)


      * INFS 519

 
     * Fall 2016
 */



/**
   * Program to implement dynamic array for a group of students*/




import java.util.Arrays;
public class DynamicArray <Type> implements List<Type>
{
    	public static final int MIN_CAPACITY = 4;// initial capacity
	private Type[] items;
    	private int size;
    	int capacity=4;//declaring capacity at first
	public DynamicArray()
    	{
        	this.items = (Type[])new Object[MIN_CAPACITY];
        	this.size = 0;//initial size
    	}
    	/**
     	* Gets the item at index i
     	* @param i
     	* @return item at i
     	* @throws IndexOutOfBoundsException if i is not in range
     	*/
    	public Type get(int i)
    {
        if(i >= 0 || i < capacity)
	{
            return items[i];//getting the item at the particular index
        }
        else

        throw new IndexOutOfBoundsException("Index: " +
            i+ ", Size: " + size); //throws because i is not in range
    	}
    	/**
     	* Overwrites the item at position i with specified item
     	* @param i
     	* @param item
     	* @throws IndexOutOfBoundsException if i is not in range
     	* @throws NullPointerException is item is null
     	*/
	public void set(int i, Type item)
    	{
        	if(i >= 0 || i < capacity)
		{
        		if (item != null)
			{
        			items[i] = item;//setting an item at the specified index
        		}
			else 
        		{
            throw new NullPointerException("item: " + item);// throws when item is equal to null
        		}
        	} 
        	else
		{
            throw new IndexOutOfBoundsException("Index:" +
                i+",Size : " + size);// throws when index is not in range 
        	} 
    	}
    	/**
     	* Appends item to the end of the list.
     	* @param item
     	* @throws NullPointerException if item is null
     	*/
    	public void add(Type item)
    	{
        	if ( capacity==size)
      		{
        		capacity = (capacity  * 2);
         		items = Arrays.copyOf(items, capacity );//capacity increament before adding the item
      		}
      		items[size++]=item;//item is appended in the last place
	}
    	/**
     	* Removes item at index from the list.
     	* @param i
     	* @return item at i
     	* @throws IndexOutOfBoundsException if i is not in range
     	*/
	public Type remove(int i)
    	{
        	Type temp = items[i];//declaring temp variable as it stores the item
        	for(int j=i+1;j< size;j++)
		{
            		items[j-1] = items[j];// item is derived from the array
        	}
        	size--;// size is decreased 
		if(size==capacity/4 && MIN_CAPACITY < capacity/2)// checks capacity with size and min_capacity
        	{
			capacity= capacity/2;
            		items = Arrays.copyOf(items, capacity );//array after size shrinks
		}
		return temp; 
    	}
    	/**
     	* Inserts item at index into the list at index i.
     	* @param i
     	* @param item
     	* @throws IndexOutOfBoundsException if i is not in range
     	* @throws NullPointerException is item is null
     	*/
	public void insert(int i, Type item)
    	{
        	if(item!=null)
        	{
            		if(capacity==size)//comparing size and capacity 
            		{
                		capacity = (capacity * 2);// doubling the capacity 
         			items = Arrays.copyOf(items, capacity);
            		}
            		for(int j=size;j>=i;j--)
            		{
                		items[j+1]=items[j];//moving items to the right 
            		}
            		items[i]=item;// inserting item in the array
            		size++;// size increament 
        	}
    	} 
    	/**
     	* Gets the size of the list (number of items).
     	* @return size
     	*/ 
    	public int size()
    	{
        	return size; // size of the array
    	}
    	/**
    	*Gets the capacity of the list.
    	*@return capacity
    	*/
    	public int capacity()
    	{
        	return capacity; // capacity of the array
    	}
		
		// Place any utility methods here and make them private
	//--------------------- DO NOT MODIFY BELOW THIS -----------------------//
    @Override
    public String toString()
    {
        StringBuilder buf = new StringBuilder();
        //buf.append("cap="+this.items.length+"[");
        buf.append("[");
        for( int i = 0; i < this.size(); i++ )
        {
            Type item = this.get(i);
            if( i != 0 ) buf.append( ", " );
            buf.append( item.toString() );
        }
        buf.append("]");
        return buf.toString();
    }


    public static void main(String[] args)
    {
        if( args.length != 1 )
        {
            String u = "Usage: java DynamicArray <filename> \n"+
                       "  e.g: java DynamicArray operations.txt";
            Stdio.println(u); 
            return;
        }

        DynamicArray<String> list = new DynamicArray<String>();
        
        Stdio.open( args[0] );
        while( Stdio.hasNext() )
        {
            String method = Stdio.readString();
            if( method.equals("add") )
            {
                String param1 = Stdio.readString();
                Stdio.println( "adding "+ param1 );
                list.add( param1 );
            }
            else if( method.equals("get") )
            {
                int index = Stdio.readInt();
                Stdio.println( "get(" + index+")="+list.get(index) );
            }
            else if( method.equals("size") )
            {
                Stdio.println( "size="+list.size() );
            }
            else if( method.equals("capacity") )
            {
                Stdio.println( "capacity="+list.capacity() );
            }
            else if( method.equals("remove") )
            {
                int index = Stdio.readInt();
                Stdio.println( "remove "+  list.remove(index) );
            }
            else if( method.equals("set") )
            {
                int index     = Stdio.readInt();
                String item   = Stdio.readString();
                list.set(index, item);
                Stdio.println( "set "+ index + " to " + list.get(index) );
            }
            else
            {
                Stdio.println( "Unknown method: "+ method );
            }
        }
        Stdio.println( "" );
        Stdio.println( "Final list=" +list.toString() );
        Stdio.close();
    }

}
