
/**
 * Sriman Abhishek Yengaldas(G01010450)
 * INFS 519
 * Fall 2016
 */

/**
 * A Program to implement Merge Sort algorithm.
 * This algorithm implements the divide and conquer technique.
 * The array of elements are split into halves at each stage,then sorted and then finally clubbed back.
 */
public class MergeSort
{
       /**
         * Sorts the items ascending, from smallest to largest(ascending order).
         * @param items (modified),the array to be sorted.
         * @throws NullPointerException if items is null
         */

	public static void sort( Comparable[] items )
	{
		int n = items.length;
        	Comparable[] aux = new Comparable[n];
        	for (int len = 1; len < n; len *= 2)
		{
			for (int lo = 0; lo < n-len; lo += len+len)
			{
				int mid  = lo+len-1;
				int hi = Math.min(lo+len+len-1, n-1);
                		merge(items, aux, lo, mid, hi);
            		}
        	}
        	assert SortUtil.isSorted( items, 0, items.length-1 );
	}
	
	public static void mergeSort( Comparable[] items, Comparable[] aux, int lo, int hi )
	{
		if( lo < hi )
		{
			int mid = (lo + hi) / 2;
			mergeSort(items, aux, lo, mid);
			mergeSort(items, aux, mid + 1, hi);
			merge(items, aux, lo, mid + 1, hi);
		}
	}
	
	/**
	 *merge items[lo..mid] with items[mid+1..hi] using aux[lo..hi]
	 */
	
	public static void merge( Comparable[] items, Comparable[] aux, int lo, int mid, int hi )
	{
		// Preconditions using java assert, can also use junit tests
		
		for (int k = lo; k <= hi; k++)
		{
			aux[k] = items[k]; 
        	}		
		assert SortUtil.isSorted( items, lo, mid );
        	assert SortUtil.isSorted( items, mid+1, hi );

        	// merge back to items[]
        	int i = lo, j = mid+1;
        	for (int k = lo; k <= hi; k++)
		{
            		if(i > mid)
				items[k] = aux[j++];  // this copying is unneccessary
            		else if(j > hi)
				items[k] = aux[i++];
            		else if(less(aux[j], aux[i]))
				items[k] = aux[j++];
            		else
                        	items[k] = aux[i++];
		}
        	assert SortUtil.isSorted( items, lo, hi );
	}

    //--------------------- DO NOT MODIFY BELOW THIS -----------------------//

    /** USE IS OPTIONAL, NOT NECESSARY
     * Utility method that compares a and b.  Conceptually return a &lt b.
     * @param a
     * @param b
     * @return true if a strictly less than b, false otherwise
     */
    public static boolean less( Comparable a, Comparable b )
    {
        return a.compareTo(b) < 0;
    }

    /**
     * Unit tests MergeSort.
     * @param args 
     */
    public static void main( String[] args )
    {
        if( args.length != 1 )
        {
            String u = "Usage: MergeSort < n | inputFilename >";
            Stdio.println(u);
            return;
        }
        
        String argument = args[0];
        Comparable[] items;
        if( Stdio.isInteger( argument ) )
        {
            int n = Integer.parseInt(argument);
            int bounds = 100; // keep generated number between [0,bounds)
            items = Stdio.generate( n , bounds);
        }
        else
        {
            Stdio.open( args[0] );
            // Create list, (could also use DynamicArray here)
            Comparable[] list = new Comparable[4];
            int size = 0;
            while(Stdio.hasNext())
            {
                //String item = Stdio.readString();
                Integer item = Stdio.readInt();
                // expand capacity if necessary
                if( size == list.length )
                {
                    Comparable[] temp = new Comparable[list.length*2];
                    System.arraycopy(list, 0, temp, 0, list.length);
                    list = temp;
                }
                list[size++] = item;
            }
            Stdio.close();
            // Trim off null positions after size
            items = new Comparable[size];
            System.arraycopy(list, 0, items, 0, size);
        }
        
        Clock time = new Clock();
        MergeSort.sort(items);

        Stdio.println( "Is sorted? "+SortUtil.isSorted(items, 0, items.length-1) );
        Stdio.println( "Time=" + time );
    }
    
}
