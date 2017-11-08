//Create an abstraction called Sequence which is a dynamic array of Element objects

class Sequence extends Element{

    public Element root;
    int numElements;
    //int pos;

    public Sequence()
    {
    	root = new Element();
    	numElements = 0;
    }

    public Sequence(Element newRoot, int oldcount){
        numElements = oldcount -1 ;
        root = newRoot;
    }
    
    public void Print()
    {
    	System.out.printf("[");
    	//print out the element

        //change this
        Element last = new Element();
        Element current = root;
        int count;
        

            count = 0;
            while(count < numElements){
                current = current.next;
                count++;
                current.Print();
            }
        

    	System.out.printf("]");
    }
    
    public Element first()
    {
	//return first element of the sequence
        return root;
    }
    
    public Sequence rest()
    {
    //return the rest of the elements of the sequence
        return Sequence(this.root.next, this.numElements);
    }
    
    public int length()
    {
	return numElements;
    }
    
    public void add(Element elm, int pos)
    {
	//add element at position and push all elements to the right
        Element last = new Element();
        Element current = root;
        int count;
        
        //first check if the index is even in the right range
        /*if( pos > numElements){
            return false;
        }
        else if (pos < 1) {
            return false;
        }
        else{ */
            count = 0;
            numElements = numElements + 1;
            while(count < pos -1 ){
                current = current.next;
                count++;
            }
            
            last.next = current.next;
            current.next = last;
            
            //return true;
        //}

    }
    
    public void delete(int pos)
    {
	//delete element at position and push all elements to the left
        Element last = new Element();
        Element current = root;
        int count;
        
        //first check if the index is even in the right range
        /*if( pos > numElements){
            return false;
        }
        else if (pos < 1) {
            return false;
        }
        else{ */
            count = 0;
            numElements = numElements + 1;
            while(count < pos -1 ){
                current = current.next;
                count++;
            }
            
            current.next = current.next.next;
            numElements--;
            
            //return true;
        //}
    }

}
