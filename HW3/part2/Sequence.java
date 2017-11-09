//Create an abstraction called Sequence which is a dynamic array of Element objects

class Sequence extends Element{

    int numElements;
    
    public Element data;
    public Sequence next;
    
    
    public Sequence()
    {
	data = null;
	next = null;
	
    }
    
    public void Print()
    {
    	System.out.printf("[");

	Sequence current = next;
        int count;
        
	System.out.printf(" ");
	data.Print();
        for(count = 0; count < length() - 2; count++)
	{
	    current = current.next;
	    System.out.printf(" ");
            current.data.Print();
        }
	
	System.out.printf(" ");
    	System.out.printf("]");
    }
    
    public Element first()
    {
        return data;
    }
    
    public Sequence rest()
    {
	return next;
    }
    
    public int length()
    {
	numElements = 0;
	
	Sequence temp = next; //set len to the sequence
	
	//iterate through temp and increase counter
	while(temp != null)
	{
	    temp = temp.next;
	    numElements++;
	}
	
	return numElements;
    }
    
    public void add(Element elm, int pos)
    {
	//add element at position and push all elements to the right

	int count;
	Sequence current = this;
	Sequence swap2 = new Sequence();
	
        //first check if the index is even in the right range
        if(pos < 0 || pos > length())
	{
	    System.err.println("Error: position out of bounds.");
	    System.exit(1);
	}    
	
	if (pos == 0)
	{
	    Sequence swap1 = new Sequence();
	    swap1.data = data;
	    swap1.next = next;
	    data = elm;
	    next = swap1;
	    
	}
	else
	{
	    for(count = 0; count < pos - 1; count++)
	    {
	        current = current.next;
	    }
	    
	    swap2.data = current.data;
	    swap2.next = current.next;
	    current.data = elm;
	    current.next = swap2;
	}
        
    }
    
    public void delete(int pos)
    {
	int count;
	Sequence current = this;	

	//delete element at position and push all elements to the left
        
        //first check if the index is even in the right range
        if(pos < 0 || pos > length())
	{
	    System.err.println("Error: deleting out of bounds.");
	    System.exit(2);
	}
	
	if(pos == 0)
	{
	    data = next.data;
	    next = next.next;
	}
	else
	{
	    for(count = 0; count < pos - 1; count++)
	    {
		current = current.next;
	    }
	    
	    current.data = current.next.data;
	    current.next = current.next.next;
	}
    }

}
