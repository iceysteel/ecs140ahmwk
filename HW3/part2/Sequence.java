//Create an abstraction called Sequence which is a dynamic array of Element objects

class Sequence extends Element{

    public Sequence root;
    int numElements;

    public Element data;
    public Sequence next;
    
    //int pos;

    public Sequence()
    {
    	//root = new Element();
    	numElements = 0;
    }

    // public Sequence Seq(Sequence newRoot, int oldcount)
    // {
    //     numElements = oldcount -1 ;
    //     root = newRoot;
    //     return root;
    // }
    
    public void Print()
    {
    	System.out.printf("[");
    	//print out the element

        //change this
        //Element last = new Element();
        Sequence current = root;
        int count;
        

            count = 0;
            while(count < numElements){
                current = current.next;
                count++;
                current.Print();
            }
        

    	System.out.printf("]");
    }
    
    public Sequence first()
    {
	//return first element of the sequence
        return root;
    }
    
    public Sequence rest()
    {
        if(root == null){
            return null;
        }
        if(root.next != null){
            //return the rest of the elements of the sequence
            //Sequence rest = Seq(this.root.next, this.numElements);
            Sequence rest = new Sequence();

            Sequence current = root.next;
            int count;
            

                count = 0;
                while(count < numElements){
                    current = current.next;
                    count++;
                    rest.add(current, count);
                }
            return rest;
        }
        else{
            return null;
        }
    }
    
    public int length()
    {
	return numElements;
    }
    
    public boolean add(Element elm, int pos)
    {
	//add element at position and push all elements to the right
        //Element last = elm;
        Sequence last = new Sequence();
        last.data = elm;

        Sequence current = root;
        int count;
        
        //first check if the index is even in the right range
        if( pos > numElements){
            return false;
        }
        else if (pos < 1) {
            return false;
        }
        else{
            count = 0;
            
            while(count < pos -1){
                current = current.next;
                count++;
            }
            
            last.next = current.next;
            current.next = last;
            
            numElements = numElements + 1;
            return true;
        }

    }
    
    public boolean delete(int pos)
    {
	//delete element at position and push all elements to the left
        //Element last = new Element();
        //Sequence last = 
        Sequence current = root;
        int count;
        
        //first check if the index is even in the right range
        if( pos > numElements){
            return false;
        }
        else if (pos < 1) {
            return false;
        }
        else{ 
            count = 0;
            //numElements = numElements + 1;
            while(count < pos -1 ){
                current = current.next;
                count++;
            }
            
            current.next = current.next.next;
            numElements--;
            
            return true;
        }
    }

}
