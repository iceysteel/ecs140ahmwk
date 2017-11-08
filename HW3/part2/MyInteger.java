
class MyInteger extends Element {

    int val;

    public MyInteger()
    {
	val = 0;
    }
    
    public int Get()
    {
	return val;
    }

    public void Set(int val)
    {
	this.val = val;
    }

    public int Print(int val)
    {
	System.out.printf("%d", val);
    }


}
