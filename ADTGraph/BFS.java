/**
 * Sriman Abhishek Yengaldas
 * INFS 519
 * Fall 2016
 */
/**
 * The BFS class represents a data type for finding shortest paths from a source vertex
 */
public class BFS
{
	private Graph graph;
	private static final int INFINITY = Integer.MAX_VALUE;
    	private int[] source;
    	private boolean[] marked;
    	private int[] paths;
    
    	/**
     	 * Creates a new BFS and performs search on the specified Graph.
     	 * @param graph
     	 * @param source
     	 */
	
	public BFS( Graph graph, int sourc )
    	{
        	marked = new boolean[graph.V()];
        	source = new int[graph.V()];
        	paths = new int[graph.V()];
        	validateVertex(sourc);
        	search(graph, sourc);
    	}
    
    	/**
    	 * Iterative approach to BFS.  Uses Queue that may grow to E.  Keeps
    	 * track of the marked and paths for later queries.
    	 */
    	private void search(Graph graph,int sourc)
    	{
		Queue<Integer> q = new Queue<Integer>();
	        for (int i = 0; i < graph.V(); i++)
        		    source[i] = INFINITY;
        	source[sourc] = 0;
        	marked[sourc] = true;
        	q.enqueue(sourc);
	       	while (!q.isEmpty()) 
		{
            		int v = q.dequeue();
            		for (int w : graph.vertices[v])
			{
                		if (!marked[w]) 
				{
                    			source[w] = v;
                    			paths[w] = paths[v] + 1;
                    			marked[w] = true;
                    			q.enqueue(w);
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
	
	private void validateVertex(int v) 
	{
        	int V = marked.length;
        	if (v < 0 || v >= V)
            	throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
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
		int x;
        	for (x = v; source[x] != 0; x = paths[x])
            		path.push(x);
        	path.push(x);
      		return path;
    	}
    
    
    //------- DO NOT MODIFY BELOW THIS LINE -------//

    /**
     * Unit test main for the BFS class.
     * @param args 
     * @throws java.io.FileNotFoundException 
     */
    public static void main( String[] args ) throws java.io.FileNotFoundException
    {
        if( args.length != 2 )
        {
            String u = "Usage: BFS <filename> <source>";
            Stdio.println(u);
            return;
        }
        
        String fileName = args[0];
        int source      = Integer.parseInt(args[1]);
        Graph graph = GraphFactory.make( fileName );
        
        BFS bfs = new BFS( graph, source );
        Stdio.println( "Paths to source: "+source );
        for (int vertexId = 0; vertexId < graph.V(); vertexId++)
        {
            Stdio.print( "  path for "+vertexId+" : " );
            if( bfs.hasPathFromSource(vertexId) )
            {
                for( int pathVertex : bfs.pathFromSource(vertexId) )
                {
                    Stdio.print( "->" + pathVertex );
                }
            }
            Stdio.println( "" );
        }
    }
}
