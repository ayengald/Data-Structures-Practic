/**
 * Sriman Abhishek Yengaldas
 * INFS 519
 * Fall 2016
 */
import java.util.Iterator;

/**
 * The Graph class represents an undirected graph pf vertices.
 * It supports the following operations : add an edge to the graph;
 * Iterate over all the vertices adjacent to a vertex.
 * Also returns the number of edges.
 */
public class Graph
{
	public Bag<Integer>[] vertices;
	private int numEdges;
	private final int numVertices;

    	/**
     	 * Creates a new graph.  Vertices are fixed.  Edges can be added 
     	 * after creation but not removed.
     	 * @param numVertices
     	 */
    
	public Graph( int numVertices )
    	{
        	if (numVertices < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        	this.numVertices = numVertices;
        	this.numEdges = 0;
        	vertices = (Bag<Integer>[]) new Bag[numVertices];
        	for (int i = 0; i < numVertices; i++) 
		{
           		vertices[i] = new Bag<Integer>();
        	}
    	}
    
    	/**
     	 * Gets the number of vertices in the graph.
     	 * @return V
         */
    
	public int V()
    	{
        	return numVertices;
    	}
    
    	/**
     	 * Gets the number of edges in the graph.
     	 * @return E
     	 */
    
	public int E()
    	{
        	return numEdges;
    	}
    
    	/**
     	 * Gets iterator that enumerates the vertexId of the neighbors of given
     	 * vertexId.
     	 * @param vertexId
     	 * @return neighbor of vertexId
     	 * @throws IndexOutOfBoundsException if vertexId is invalid
     	 *         (less than 0, more than or equal to V)
     	 */
    
	public Iterable<Integer> neighbors( final int vertexId )
    	{
        	validateVertex(vertexId);
        	return vertices[vertexId];
    	}
	private void validateVertex(int v) 
	{
        	if (v < 0 || v >= numVertices)
            	throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (numVertices-1));
    	}
    	
	/**
     	 * Adds an edge between v and w.
     	 * @param v
     	 * @param w 
      	 * @throws IndexOutOfBoundsException if v or w are invalid
     	 *         (less than 0, more than or equal to V)
     	 */
   	
	public void addEdge( int v, int w )
    	{
        	validateVertex(v);
        	validateVertex(w);
        	numEdges++;
        	vertices[v].add(w);
        	vertices[w].add(v);
    	}
    


    //------- DO NOT MODIFY BELOW THIS LINE -------//

    /**
     * Gets String facsimile of this graph.
     * @return 
     */
    @Override
    public String toString()
    {
        StringBuilder buf = new StringBuilder();
        String title = "V=" + this.V() + " E=" + this.E();
        buf.append( title );
        for (int vertexId = 0; vertexId < this.vertices.length; vertexId++)
        {
            Bag<Integer> neighbors = this.vertices[vertexId];
            String prefix = "\n["+vertexId+"] neighbors="+neighbors.size()+": ";
            buf.append( prefix );
            boolean first = true;
            for( int neighborId : neighbors )
            {
                if( first ) first = false;
                else buf.append( ", " );
                buf.append( neighborId );
            }
        }
        return buf.toString();
    }
    
    
    /**
     * Unit test main for the Graph class.  Reads a file and prints out.
     * @param args 
     * @throws java.io.FileNotFoundException 
     */
    public static void main( String[] args ) throws java.io.FileNotFoundException
    {
        String fileName = args[0];
        Graph graph = GraphFactory.make( fileName );
        Stdio.println( "Graph: "+graph );
    }
}
