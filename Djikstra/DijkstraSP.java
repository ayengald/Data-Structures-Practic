
/**
 * Dijkstra's single-source, shortest path algorithm.  Only works when all
 * weights are non-negative.
 * Sriman Abhishek Yengaldas
 */
public class DijkstraSP
{
    	private double[] distTo;         
	private WeightedDiedge[] paths; 
    	private MinHeap<Double> pq; 
    
    	/**
     	* Creates a new DijkstraSP and performs search on the specified Graph.
     	* @param graph
     	* @param source
     	* @throws IllegalArgumentException if graph has a negative weight edge
     	*/
    	
	public DijkstraSP( WeightedDigraph graph, int source )
    	{
        	for (WeightedDiedge e : graph.edges()) 
		{
            		if (e.weight() < 0)
                	throw new IllegalArgumentException("edge " + e + " has negative weight");
        	}

        distTo = new double[graph.V()];
        paths = new WeightedDiedge[graph.V()];

        validateVertex(source);

        for (int v = 0; v < graph.V(); v++)
        	distTo[v] = Double.POSITIVE_INFINITY;
       	distTo[source] = 0.0;

        // relax vertices in order of distance from s
        
	pq = new MinHeap<Double>(graph.V());
        pq.insert(distTo[source]);
	}
        /*while (!pq.isEmpty()) 
	{
            	for (WeightedDiedge e : graph.neighbors(pq.delMin()))
                	relax(e);
        }
}
	private void relax(WeightedDiedge e) 
	{
        	int v = e.from(), w = e.to();
        	if (distTo[w] > distTo[v] + e.weight()) 
		{
            		distTo[w] = distTo[v] + e.weight();
            		paths[w] = e;
            		if (pq.contains(w)) 
				pq.decreaseKey(w, distTo[w]);
            		else                
				pq.insert(w, distTo[w]);
        	}
    	}
*/
	
	/**
	* Returns whether or not a path exists from the source to v.
     	* @param v
    	* @return true if a path exists from the source to v, false otherwise
     	*/
    
	public boolean hasPathTo( int v )
    	{
        	validateVertex(v);
        	return distTo[v] < Double.POSITIVE_INFINITY;
    	}
    
    	/**
     	* Returns distance to the specified vertex v.  If the value is
     	* Double.MAX_VALUE, the vertex is not reachable from the source.
     	* @param v
     	* @return distance to v from s
     	*/
    
	public double distTo( int v )
    	{
        	validateVertex(v);
        	return distTo[v];
    	}
    
    	/**
     	* Returns path from the source to the given vertex v, in that order.
     	* @param v
     	* @return path from the source to v, starts with source, ends with v
     	*         returns a null if no path exists
     	*/
    
	public Iterable<WeightedDiedge> pathTo( int v )
    	{
        	validateVertex(v);
        	if (!hasPathTo(v)) 
			return null;
        	Stack<WeightedDiedge> path = new Stack<WeightedDiedge>();
        	for (WeightedDiedge e = paths[v]; e != null; e = paths[e.from()]) 
		{
            		path.push(e);
        	}
        	return path;
    	}
    	private void validateVertex(int v) 
	{
        	int V = distTo.length;
        	if (v < 0 || v >= V)
            		throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    	}
    
    //---------- DO NOT MODIFY BELOW THIS LINE ----------//

    /**
     * Unit test main for the DijkstraSP class.
     * @param args 
     * @throws java.io.FileNotFoundException 
     */
    public static void main( String[] args ) throws java.io.FileNotFoundException
    {
        if( args.length != 2 )
        {
            String u = "Usage: DijkstraSP <filename> <source>";
            Stdio.println(u);
            return;
        }
        
        String fileName = args[0];
        int source      = Integer.parseInt(args[1]);
        WeightedDigraph graph = GraphFactory.makeWeightedDigraph(fileName);
        Stdio.println( "Graph: "+graph );
        
        DijkstraSP dijkstraSP = new DijkstraSP( graph, source );
        Stdio.println( "Paths to source: "+source );
        for (int vertexId = 0; vertexId < graph.V(); vertexId++)
        {
            Stdio.print( "  path for "+vertexId+" : " );
            if( dijkstraSP.hasPathTo(vertexId) )
            {
                for( WeightedDiedge path : dijkstraSP.pathTo(vertexId) )
                {
                    if( path != null ) Stdio.print( path.toString() );
                }
            }
            Stdio.println( "" );
        }
    }
}
