

class MyChar extends Element {

    char val;
	
    public MyChar()
    {
	   val = '0';
    }

    public char Get()
    {
	   return val;
    }

    public void Set(char val)
    {
	   this.val = val;
    }

    public void Print()
    {
	   System.out.printf("'%c'", val);
    }

}
