public class Parser {

    
    //Initialize symbol table
    SymbolTable st = new SymbolTable();
    // tok is global to all these parsing methods;
    // scan just calls the scanner's scan method and saves the result in tok.
    private Token tok; // the current token
    private void scan() {
	tok = scanner.scan();
    }

    private Scan scanner;
    Parser(Scan scanner) {
	this.scanner = scanner;
	scan();
	program();
	if( tok.kind != TK.EOF )
	    parse_error("junk after logical end of program");
    }

    private void program() {
	System.out.println("#include <stdio.h>");
	System.out.println("int main() ");
	System.out.println("{");
	block();
	System.out.println("}");
    }

    private void block(){
	st.push_Block();// each variable declaration adds to the list for the current block
	declaration_list();
	statement_list();
	st.pop_Block();// when a block is exited, the list for that block is popped from the stack
    }

    private void declaration_list() {
	// below checks whether tok is in first set of declaration.
	// here, that's easy since there's only one token kind in the set.
	// in other places, though, there might be more.
	// so, you might want to write a general function to handle that.
	while( is(TK.DECLARE) ) {
	    declaration();
	}
    }

    private void declaration() {
	
	mustbe(TK.DECLARE);
	System.out.print("int ");
	if(is(TK.ID))
	{
	    //we want to search block to see if the token string
	    //exists and if it doesn't then we add it, otherwise 
	    //it gives a redeclaration error
	    if(st.block_Search(tok.string) == null)
	    {
		st.add_var(tok.string);
	    }
	    else
	    {
		redeclareError();
	    }
	}
	
	mustbe(TK.ID);
	
	while( is(TK.COMMA) ) {
	    scan();
	    System.out.print(", ");
	    //Same thing here as described above, if we search and
	    //it's the same then it's a redeclaration
	    if(is(TK.ID))
	    {
		if(st.block_Search(tok.string) == null)
		{
		    st.add_var(tok.string);
		}
		else
		{
		    redeclareError();
	        }
	    }
	    
	    mustbe(TK.ID);
	}
	System.out.println(";");
    }

    private void statement_list() {
	
	while(is(TK.ID) || is(TK.TILDE) || is(TK.ASSIGN) || is(TK.PRINT) || is(TK.DO) || is(TK.IF))
	{
		statement();
	}
    }

    private void statement() {
	//We want to check the first symbols in assignment which can either be tilde or id
	if( is(TK.ID) || is(TK.TILDE))
	{
		assignment();
	}
	else if( is(TK.PRINT))
	{
		printFunc();
	}
	else if( is(TK.DO))
	{
		doFunc();
	}
	else if( is(TK.IF))
	{
		ifFunc();
	}
	else
	{
		mustbe(TK.ERROR); //if none of the options then error
	}
    }

    private void assignment() {
	ref_id(); //ref_id
	mustbe(TK.ASSIGN); //=i
	System.out.print(" = ");
	expr(); //expr
	System.out.println(";");
    }

    private void printFunc() {
	mustbe(TK.PRINT); // !
	System.out.print("printf(\"\", ");
	expr(); //expr
	System.out.println(");");
    }

    private void doFunc() {
	mustbe(TK.DO); // <
	System.out.print("while( 0 >= (");
	guarded_command(); //guarded_commmand
	mustbe(TK.ENDDO); // >
    }

    private void ifFunc() {
	mustbe(TK.IF); // [
	System.out.print("if( 0 >= (");
	guarded_command(); //guarded_command
	
	while(is(TK.ELSEIF))
	{
		mustbe(TK.ELSEIF); // |
		System.out.print("else if( 0 >= (");
		guarded_command(); //guarded_command
	}

	if(is(TK.ELSE))
	{
		mustbe(TK.ELSE); // %
		System.out.print("else {");
		block(); //block
	}

	mustbe(TK.ENDIF); // ]
    }

    private void ref_id() {
	
	int scopeNum = -1;
	
	if(is(TK.TILDE))
	{
		mustbe(TK.TILDE); // ~
		
		//we want to see what scope we're in
		if(is(TK.NUM))
		{
			scopeNum = Integer.parseInt(tok.string);
			
			mustbe(TK.NUM); //number
			
			if(scopeNum == 0)
			{
				scopeNum = -1;
			}
			
		}
		else
		{
			scopeNum = 0;
		}
	}
	if(is(TK.ID))
	{
		scope(scopeNum);
	}
	mustbe(TK.ID); //id
    }

    private void guarded_command() {
	expr(); //expr
	System.out.println(")){");
	mustbe(TK.THEN); // :
	block(); //block
    }

    private void expr() {
	term(); //term
	while(is(TK.PLUS) || is(TK.MINUS))
	{
	    addop(); //addop
	    term(); //term
	}
    }

    private void term() {
	factor(); //factor
	while(is(TK.TIMES) || is(TK.DIVIDE))
	{
	   multop(); //multop
	   factor(); //factor
	}
    }

    private void factor() {
	
	if(is(TK.LPAREN))
	{
	    mustbe(TK.LPAREN); // (
	    System.out.print("(");
	    expr(); //expr
	    mustbe(TK.RPAREN); // )
	    System.out.print(")");
	}
	else if(is(TK.ID) || is(TK.TILDE))
	{
	    ref_id(); //ref_id
	}
	else if(is(TK.NUM))
	{
	    System.out.print(tok.string);
	    mustbe(TK.NUM); //number
	}
    }

//Error and exit statements up in here
    private void redeclareError()
    {
	System.err.println("redeclaration of variable " + tok.string);
    }

    private void undeclareError()
    {
	System.err.println(tok.string + " is an undeclared variable on line " + tok.lineNumber);
	System.exit(1);
    }

    private void noVarError()
    {
	System.err.println("no such variable ~" + tok.string + " on line " + tok.lineNumber);
    }
    
    private void noVarError2(int scopeNum)
    {
	System.err.println("no such variable ~" + scopeNum + tok.string + " on line " + tok.lineNumber);
    }

//Scope functions
    private void scope(int scopeNum)
    {	
	if(scopeNum == -1) //there is no scope
	{
		//go through every element in every list to
		//see if variable is declared
		if(st.search(tok.string) == null)
		{
			undeclareError(); //undeclare error
		}
	}
	else if(scopeNum != -1) //there is a scope get correct variable but throw error if no variable is found
	{
		if(st.scope_Search(scopeNum, tok.string) == null)
		{
		    if(scopeNum == 0)
		    {
			noVarError(); //no such variable error global variable
		    }
		    else
		    {
		        noVarError2(scopeNum); //no such variable error with scope
		    }
		}
	}
    }

//Add,Subtr,Mult,Div operations
    private void addop() {
	if(is(TK.PLUS) || is(TK.MINUS))
	{
	    scan();
	    if(is(TK.PLUS))
	    {
	        System.out.print(" + ");
	    }
	    else
	    {
	        System.out.print(" - ");
	    }
	}	
    }

    private void multop() {
	if(is(TK.TIMES) || is(TK.DIVIDE))
	{
	    scan();
	    if(is(TK.TIMES))
	    {
	        System.out.print(" * ");
	    }
	    else
	    {
	        System.out.print(" / ");
	    }
	}
    }

//Up to this point

    // is current token what we want?
    private boolean is(TK tk) {
        return tk == tok.kind;
    }

    // ensure current token is tk and skip over it.
    private void mustbe(TK tk) {
	if( tok.kind != tk ) {
	    System.err.println( "mustbe: want " + tk + ", got " +
				    tok);
	    parse_error( "missing token (mustbe)" );
	}
	scan();
    }


    private void parse_error(String msg) {
	System.err.println( "can't parse: line "
			    + tok.lineNumber + " " + msg );
	System.exit(1);
    }
}
