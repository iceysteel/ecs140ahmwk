//Create an abstraction called Element
//MyChar, MyInteger, and Sequence have Elements

abstract class Element{

	public Element next = null;
	char val;

    Element()
    {
    	//val = null;
    }

    //meant to be overloaded
    /*public void Get()
    {
		
    }

    public void Set()
    {
	
    }*/

    abstract public void Print();
    
}
