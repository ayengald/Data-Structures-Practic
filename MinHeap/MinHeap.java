
import java.util.Iterator;

/**
 * Sriman Abhishek Yengaldas(G01010450)
 * INFS 519
 * Fall 2015
 */

/**
 * ADD DOCUMENTATION
 */
public class MinHeap implements MinPQ
{
	public static final int DEFAULT_CAPACITY = 8;
	private int size;            // Number of elements in heap
   	private int[] heap;     // The heap array
	
	public MinHeap( )
    	{
		size = 0;
      		heap = (int[]) new Comparable[DEFAULT_CAPACITY];
    	}
    	
	public MinHeap( int initialCapacity )
    	{
 		size = array.length;
      		heap = (int[]) new Comparable[array.length+1];
		System.arraycopy(array, 0, heap, 1, array.length); //we do not use 0 index
		swim();
    	}

	public void insert( Comparable item )
    	{
		if(size == heap.length - 1)
			checkGrow();
	      	 //Insert a new item to the end of the array
      		int pos = ++size;
	      	 //swim up
      		for(; pos > 1 && item.compareTo(heap[pos/2]) < 0; pos = pos/2 )
         		heap[pos] = heap[pos/2];
	        heap[pos] = item;
    	}

	private void swim( int k )
    	{
		for (int i = size/2; i > 0; i--)
      		{
         		sink(i);
      		}
	}

	public Comparable min()
    	{
		int min = heap[1];
		heap[1]=heap[pos-1];
		heap[pos-1]=0;
		pos--;		
		sink(1);
		return min;
    	}

    	public Comparable delMin()
    	{
		if (size == 0) throw new RuntimeException();
      		int min = heap[1];
      		heap[1] = heap[size--];
      		sink(1);
      		return min;
    	}
	
	private void sink( int k )
    	{
		int tmp = heap[k];
      		int child;
      		for(; 2*k <= size; k = child)
      		{
        		child = 2*k;
         		if(child != size &&heap[child].compareTo(heap[child + 1]) > 0)
				child++;
			if(tmp.compareTo(heap[child]) > 0)
				heap[k] = heap[child];
         		else
                		break;
      		}
      		heap[k] = tmp;
	}    

	private boolean greater( int x, int y )
    	{
		if (pos >=  (size / 2)  &&  pos <= size)
        	{ 
            		return true;
        	}
        	return false;
    	}

	private void swap( int x, int y )
    	{
		size = array.length;
      		heap = (int[]) new Comparable[size+1];
      		System.arraycopy(array, 0, heap, 1, size);
      		swim();
	        for (int i = size; i > 0; i--)
      		{
         		int tmp = heap[i]; //move top item to the end of the heap array
         		heap[i] = heap[1];
         		heap[1] = tmp;
         		size--;
         		sink(1);
      		}
      		for(int k = 0; k < heap.length-1; k++)
         		array[k] = heap[heap.length - 1 - k];
    	}
	
	public int size()
    	{
		return size;
	}

	public boolean isEmpty()
    	{
		return size==0;
    	}

	private void checkGrow()
    	{
		int [] old = heap;
      		heap = (int []) new Comparable[heap.length * 2];
      		System.arraycopy(old, 1, heap, 1, size);
    	}
    
    /**
     * Returns Iterator that returns items in level order.  Does not support
     * remove.
     * @return iterator
     */
    public Iterator<Comparable> iterator()
    {
        return null; // MODIFY
    }

    //--------------------- DO NOT MODIFY BELOW THIS -----------------------//

    @Override
    public String toString()
    {
        StringBuilder buf = new StringBuilder();
        boolean first = true;
        for (Comparable item : this)
        {
            if( first ) first = false;
            else buf.append(", ");
            
            buf.append( item.toString() );
        }
        return buf.toString();
    }
    
    /**
     * Unit tests the BinaryHeap data type.
     * @param args 
     */
    public static void main(String[] args)
    {
        Stdio.open( args[0] );
        MinPQ pq = new MinHeap();
        while( Stdio.hasNext() )
        {
            String method = Stdio.readString();
            if( method.equalsIgnoreCase("insert") )
            {
                int value = Stdio.readInt();
                pq.insert( value );
                Stdio.println( "insert="+pq.toString() );
            }
            else if( method.equalsIgnoreCase("delMin") )
            {
                Stdio.println( "delMin="+pq.delMin() );
            }
            else if( method.equalsIgnoreCase("size") )
            {
                Stdio.println( "size="+pq.size() );
            }
            else if( method.equalsIgnoreCase("min") )
            {
                Stdio.println( "min="+pq.min() );
            }
            else if( method.equalsIgnoreCase("empty") )
            {
                while( pq.isEmpty() == false )
                {
                    Stdio.println( "delMin="+pq.delMin() );
                }
            }
        }
        Stdio.println( "Final priority queue=" +pq.toString() );
        Stdio.close();
    }
}
