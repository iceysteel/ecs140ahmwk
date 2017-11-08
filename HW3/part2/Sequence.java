//Create an abstraction called Sequence which is a dynamic array of Element objects

class Sequence{

    public Element root;
    int numElements;
    int pos;

    public Sequence()
    {
    	root = new Element();
    	pos = 0;
    }
    
    void Print()
    {
	System.out.printf("[");
	//print out the element
	System.out.printf("]");
    }
    
    Element first()
    {
	//return first element of the sequence
    }
    
    Sequence rest()
    {
	//return the rest of the elements of the sequence
    }
    
    int length()
    {
	return numElements;
    }
    
    void add(Element elm, int pos)
    {
	//add element at position and push all elements to the right
    }
    
    void delete(int pos)
    {
	//delete element at position and push all elements to the left
    }

}
