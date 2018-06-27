/**
 * ProductID uniquely identifies a product.  Should be IMMUTABLE!!!
 */
 
 /**
 * Sriman Abhishek Yengaldas
 * INFS 519
 * Fall 2016
 */
 
public class ProductID
{
	private final String epc;
	private final int serialNumber;
    	public ProductID(String epc, int serialNumber)
    	{
        	this.epc = epc;
        	this.serialNumber = serialNumber;
    	}
   
	 @Override
    	public int hashCode()
    	{
        	int hash = 17; // pick prime constants
		hash = 31 * hash + epc.hashCode();
		hash = 31 * hash + ((Integer)serialNumber).hashCode();
		return hash;
    	}

    	@Override
    	public boolean equals(Object obj)
    	{
		//performance trick, typical to check super.equals also		
        	if(obj == this) 
			return true;
		// type check, handles null
		if(!(obj instanceof ProductID)) 
			return false;
		// safe cast so can check each attribute
		ProductID that = (ProductID)obj;
		// check each attribute for equality, should handle nulls
		return (this.epc.equals(that.epc)) &&
		this.serialNumber == that.serialNumber;
    	}
    
    @Override
    public String toString()
    {
        return epc + ","+ serialNumber;
    }
}



