import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
/**
 * Sriman Abhishek Yengaldas
 * INFS 519
 * Fall 2016
 */
 
/**
 * ADD DOCUMENTATION
 */
public class Huffman
{
    /**
     * Construct an array of letter frequencies for a text.
     */
	public static int[] determineFrequencies( String text )
    	{
       		int[] counts = new int[RADIX]; // 256 ASCII characters
    		for (int i = 0; i < text.length(); i++)
		counts[(int)text.charAt(i)]++;         // Count the character in text
    		return counts;
    	}

    /*
	* Reads a sequence of 8-bit bytes from standard input; compresses them
     	* using Huffman codes with an 8-bit alphabet; and writes the results
     	* to standard output.
     */
	public static void compress(char [] text)
    	{
		BitStreamOutput out;
		BitStreamInput in;
		String s;
        	char[] input = s.toCharArray();
        	int[] freq = new int[RADIX];
        	for (int i = 0; i < input.length; i++)
            		freq[input[i]]++;
        	Node root = makeTrie(freq);
        	String[] st = new String[RADIX];
        	makeCodingTable(st, root, "");
        	/*writeTrie(root);*/
		out.writeBit(true);
        	for (int i = 0; i < input.length; i++)
		{
            		String code = st[input[i]];
            		for (int j = 0; j < code.length(); j++)
			{
                		if (code.charAt(j) == '0') 
				{
                    			out.writeBit(false);
                		}
                		else if (code.charAt(j) == '1') 
				{
                    			out.writeBit(true);
                		}
                		else 
					throw new IllegalStateException("Illegal state");
            		}
        	}
    	}

    /**
     * make a lookup table from symbols and their encodings
     */
	public static void makeCodingTable( String[] table, Node x, String code )
	{
		if (!x.isLeaf()) 
		{
            		makeCodingTable(table, x.left,  code + '0');
            		makeCodingTable(table, x.right, code + '1');
        	}
        	else 
		{
			table[x.symbol] = code;
        	}
    	}

    /**
     * build the Huffman trie given frequencies
     */
	public static Node makeTrie( int[] freq )
    	{
	/*	MinPQ<Node> pq = new MinPQ<Node>();
        	for (char i = 0; i < RADIX; i++)
            		if (freq[i] > 0)
                		pq.insert(new Node(i, freq[i], null, null));
				        // special case in case there is only one character with a nonzero frequency
        		if (pq.size() == 1) 
			{
            			if (freq['\0'] == 0) 
					pq.insert(new Node('\0', 0, null, null));
            			else
			                pq.insert(new Node('\1', 0, null, null));
        		}
		        // merge two smallest trees
        		while (pq.size() > 1) 
			{
            			Node left  = pq.delMin();
            			Node right = pq.delMin();
            			Node parent = new Node('\0', left.freq + right.freq, left, right);
            			pq.insert(parent);
        		}
        	return pq.delMin();
	*/}

    /**
     * ADD DOCUMENTATION
     */
	public static char[] decompress(BitStreamInput in)
    	{
		/*Node root = readTrie(); 
        		// number of bytes to write
		int length = BinaryStdIn.readInt();
        		// decode using the Huffman trie
		for (int i = 0; i < length; i++) 
		{
            		Node x = root;
            		while (!x.isLeaf()) 
			{
                		boolean bit = BinaryStdIn.readBoolean();
                		if (bit) 
					x = x.right;
                		else     
					x = x.left;
            	}
            	BinaryStdOut.write(x.ch, 8);
        }
        BinaryStdOut.close();
        return length;
	*/}



    //----- DO NOT MODIFY ANYTHING BELOW THIS LINE -----//

    public static final int RADIX = 256; // number of symbols for extended ascii

    private static class Node implements Comparable<Node>
    {
        private Node left;
        private Node right;
        private char symbol;
        private int freq;    

        public Node( char c, int freq )
        {
            this(c, null, null, freq);
        }

        public Node( char symbol, Node left, Node right, int freq )
        {
            this.symbol = symbol;
            this.left   = left;
            this.right  = right;
            this.freq   = freq;
        }

        public Node getLeft() { return this.left; }
        public Node getRight() { return this.right; }
        public char getSymbol() { return this.symbol; }
        public int getFreq() { return this.freq; }
        public boolean isLeaf() { return this.left == null && this.right == null; }

        public int compareTo( Node other )
        {
            return this.freq - other.freq;
        }
        
        @Override
        public String toString()
        {
            return "("+this.symbol+" " + freq+")";
        }
    }
    

    public static void writeTrie(Node x, BitStreamOutput out)
    {
        // Use preorder traversal to encode the trie
        if (x.isLeaf())
        {
            out.writeBit(true);
            out.writeBits(x.symbol, 8);
            return;
        }
        out.writeBit(false);
        writeTrie(x.left,  out);
        writeTrie(x.right, out);
    }

    public static Node readTrie( BitStreamInput in )
    {
        boolean bit = in.readBit();
        if( bit )
        {
            char symbol = (char)in.readBits(8);
            return new Node(symbol, 0);
        }
        Node internalNode = new Node('\0', 0);
        internalNode.left  = readTrie( in );
        internalNode.right = readTrie( in );

        return internalNode;
    }
    
    public static void printTable(String[] table)
    {
        for( int i = 0; i < table.length; i++ )
        {
            String code = table[i];
            if(code != null) Stdio.println(""+((char)i) + " = " +code );
        }
    }
    
    
    /**
     * Unit tests the Huffman compression/decompression algorithm.
     * @param args 
     * @throws java.io.IOException 
     */
    public static void main( String[] args ) throws IOException
    {
        if( args.length != 2 )
        {
            String u = "Usage: Huffman <+|-> <filename>";
            Stdio.println(u);
            return;
        }
        
        String option   = args[0];
        String filename = args[1];
        if( option.equals("-") )
        {
            BufferedReader fileIn = new BufferedReader( new FileReader(filename) );
            StringBuilder buf = new StringBuilder();
            int nextChar = fileIn.read();
            while( nextChar != -1 )
            {
                buf.append((char)nextChar);
                nextChar = fileIn.read();
            }
            fileIn.close();
            
            char[] text = buf.toString().toCharArray();
            
            byte[] compressedText = compress();
            
            FileOutputStream fos = new FileOutputStream( filename+".zip" );
            BufferedOutputStream file = new BufferedOutputStream(fos);
            file.write(compressedText);
            file.close();
        }
        else if( option.equals("+") )
        {
            // READ IN THE FILE
            FileInputStream fis = new FileInputStream( filename );
            BufferedInputStream file = new BufferedInputStream(fis);
            
            byte[] compressedText = new byte[16];
            int size = 0;
            int byteRead = file.read();
            while( byteRead != -1 )
            {
                if( size == compressedText.length )
                {
                    byte[] newCompressedText = new byte[compressedText.length*2];
                    System.arraycopy(compressedText, 0, newCompressedText, 0, compressedText.length);
                    compressedText = newCompressedText;
                }
                
                compressedText[size++] = (byte)byteRead;
                byteRead = file.read();
            }            
            file.close();
            
            BitStreamInput in = new BitStreamInput(compressedText);
            char[] decompressedText = decompress( in );
            
            String text = new String(decompressedText);
            Stdio.println("Decompressed: "+text);
        }
        else
        {
            Stdio.println("Invalid option: "+option);
        }
        
    }
}
