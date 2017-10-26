import java.util.*;

public class SymbolTable{

	//private int var_scope;
	//private Stack<ArrayList<Variable>> list_stack;
	// this is the main stack used throughout the class
	private Stack<ArrayList<String>> symTable;
	private int table_scope;

	public SymbolTable()
	{
	   symTable = new Stack<ArrayList<String>>();
	   table_scope = -1;
	}

	public void push_Block()
	{
	   ArrayList<String> block = new ArrayList<String>(); //new block
	   symTable.push(block); //push block onto symbol table
	   table_scope++;
	}

	public void pop_Block()
	{
	   symTable.pop(); //pop block off symbol table
	   table_scope--;
	}

	public int scope(){
		 return table_scope;
	}

	public String scope_Search(int scopeToFind, String varName){
		int scopeLevel;
		if(table_scope < scopeToFind){
			return null;
		}
		else if (scopeToFind == 0) {
			scopeLevel = 0;
		}
		else{
			//subtract the outside scope
			scopeLevel = table_scope - scopeToFind;
		}
		//get the block at the right scope level out of the stack
		// for ever variable in the block
		for(String var: symTable.elementAt(scopeLevel)){
				if(var.equals(varName)){
					return var;
				}
		}
		// we shouldn't reach this statement
		return null;
	}

	//simple search through every element in every list
	public String search(String varName){
		for(ArrayList<String> varlist : symTable){
			for(String var : varlist){
				if(var.equals(varName)){
					return var;
				}
			}
		}
		return null;
	}

	public String block_Search(String tokName)
	{
	   ArrayList<String> top = symTable.peek();
	   for(String s : top)
	   {
		if(s.equals(tokName))
		{
		   return s;
		}
	   }
	   return null;
	}

	public boolean add_var(String varName){
		if(block_Search(varName) != null){
			// the varible was found in the stack so it already exists; print the error
			System.err.println("redeclaration of variable " + varName );
			return false;
		}
		else if (block_Search(varName) == null) {
					//if we're in this part it means the variable wasn't in the stack so make it and add to top of the stack
					//use .add since its a list on the top of the stack
					symTable.peek().add(varName);
					return true;
		}
		//if you reached this part only god can help you now
		return false;
	}





}
