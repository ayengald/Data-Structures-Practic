/**
 * Sriman Abhishek Yengaldas
 * INFS 519
 * Fall 2016
 */
/**
 * The DFS class represents a data type for determining the no. of vertices.
 */
public class DFS
{
	private Graph graph;
	private boolean[] marked;    
    	private int count;
	private int[] source; 
	private static final int INFINITY = Integer.MAX_VALUE;      
    
    /**
     * Creates a new DFS and performs search on the specified Graph.
     * @param graph
     * @param source
     */
    	
	public DFS( Graph graph, int source )
    	{
        	marked = new boolean[graph.V()];
        	validateVertex(source);
        	dfs(graph, source);
		search(graph,source);
    	}
	private void dfs(Graph graph, int v) 
	{
        	count++;
        	marked[v] = true;
        	for (int w : graph.vertices[v]) 
		{
            		if (!marked[w]) 
			{
                		dfs(graph, w);
            		}
        	}
    	}
	private void validateVertex(int v) 
	{
        	int V = marked.length;
        	if (v < 0 || v >= V)
            		throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    	}

    
	/**
     	 * Iterative approach to DFS.  Uses Stack that may grow to E.  Keeps
     	 * track of the marked and paths for later queries.
     	 */
    
	private void search(Graph graph,int source)
    	{
		Stack<Integer> mystack = new Stack<Integer>();
        	marked[source] = true;
        	mystack.push(source);
	       	while (!mystack.isEmpty()) 
		{
            		int v = mystack.pop();
            		for (int w : graph.vertices[v]) 
			{
                		if (!marked[w]) 
				{
                    			marked[w] = true;
                    			mystack.push(w);
                		}
            		}
    		}
    	}	
    
	/**
     	 * Returns whether or not a path exists from the source to v.
     	 * @param v
     	 * @return true if a path exists from the source to v, false otherwise
     	 */
    
	public boolean hasPathFromSource( int v )
    	{
        	validateVertex(v);
		return marked[v];
    	}
	
    
    /**
     * Returns path from the source to the given vertex v, in that order.
     * @param v
     * @return path from the source to v, starts with source, ends with v
     *         returns a null if no path exists
     */
    
	public Iterable<Integer> pathFromSource( int v )
    	{
        	validateVertex(v);
		if (!hasPathFromSource(v)) 
			return null;
        	Stack<Integer> path = new Stack<Integer>();
        	while(v!=source[v])
			path.push(v);
		path.push(v);
		return path;
    	}
    


    //------- DO NOT MODIFY BELOW THIS LINE -------//
    
    /**
     * Unit test main for the DFS class.
     * @param args 
     * @throws java.io.FileNotFoundException 
     */
    public static void main( String[] args ) throws java.io.FileNotFoundException
    {
        if( args.length != 2 )
        {
            String u = "Usage: DFS <filename> <source>";
            Stdio.println(u);
            return;
        }
        
        String fileName = args[0];
        int source      = Integer.parseInt(args[1]);
        Graph graph = GraphFactory.make( fileName );
        
        DFS dfs = new DFS( graph, source );
        Stdio.println( "Paths to source: "+source );
        for (int vertexId = 0; vertexId < graph.V(); vertexId++)
        {
            Stdio.print( "  path for "+vertexId+" : " );
            if( dfs.hasPathFromSource(vertexId) )
            {
                for( int pathVertex : dfs.pathFromSource(vertexId) )
                {
                    Stdio.print( "->" + pathVertex );
                }
            }
            Stdio.println( "" );
        }
    }
}
