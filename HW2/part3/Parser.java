public class Parser {


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
	block();
    }

    private void block(){
	declaration_list();
	//System.out.printf("fucc\n");
	statement_list();
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
	mustbe(TK.ID);
	//System.out.printf("before declaration scan\n");
	while( is(TK.COMMA) ) {
	    scan();
	    mustbe(TK.ID);
	}
    }

//We are editing statement_list right now
    private void statement_list() {
	//System.out.printf("pu$$y\n");
	//if (is(TK.ASSIGN))
	//{
	//	System.out.printf("this is an assignment\n");
	//}
	while(is(TK.ID) || is(TK.TILDE) || is(TK.ASSIGN) || is(TK.PRINT) || is(TK.DO) || is(TK.IF))
	{
		//System.out.printf("shiettt\n");
		statement();
	}
    }

    private void statement() {
	//System.out.println("Inside statement");
	//scan();
	if( is(TK.ID) || is(TK.TILDE))
	{
		//System.out.printf("Will assign\n");
		assignment();
	}
	else if( is(TK.PRINT))
	{
		//System.out.println("Will print");
		printFunc();
	}
	else if( is(TK.DO))
	{
		//System.out.println("Will do");
		doFunc();
	}
	else if( is(TK.IF))
	{
		//System.out.println("Will if");
		ifFunc();
	}
	else
	{
		//System.out.println("why the fuck are we here");
		mustbe(TK.ERROR); //could be extra
	}
    }

    private void assignment() {
	//System.out.println("we gonna do ref_id??");
	ref_id(); //ref_id
	//System.out.println("we done with that shit");
	mustbe(TK.ASSIGN); //=
	//System.out.println("trying go into expr");
	expr(); //expr
    }

    private void printFunc() {
	//System.out.println("printing biatch");
	mustbe(TK.PRINT); // !
	//System.out.printf("Does this print?");
	//System.out.println("expr inside printtt");
	expr(); //expr
    }

    private void doFunc() {
	mustbe(TK.DO); // <
	guarded_command(); //guarded_commmand
	mustbe(TK.ENDDO); // >
    }

    private void ifFunc() {
	mustbe(TK.IF); // [
	guarded_command(); //guarded_command
	
	while(is(TK.ELSEIF))
	{
		mustbe(TK.ELSEIF); // |
		//scan();
		guarded_command(); //guarded_command
	}

	if(is(TK.ELSE))
	{
		mustbe(TK.ELSE); // %
		//scan();
		block(); //block
	}

	mustbe(TK.ENDIF); // ]
    }

    private void ref_id() {
	//System.out.println("we here in ref_id niqqua");
	//scan();
	if(is(TK.TILDE))
	{
		//System.out.println("Is this tilde or nah");
		mustbe(TK.TILDE); // ~
		if(is(TK.NUM))
		{
		//	System.out.println("ayy where that num be tho");
			mustbe(TK.NUM); //number
		}
	}
	//System.out.println("We doing that id shit again");
	mustbe(TK.ID); //id
    }

    private void guarded_command() {
	expr(); //expr
	mustbe(TK.THEN); // :
	block(); //block
    }

    private void expr() {
	//System.out.println("first term");
	term(); //term
	//System.out.printf("fucc");
	while(is(TK.PLUS) || is(TK.MINUS))
	{
	    //System.out.println("add or subtract op");
	    addop(); //addop
	    //System.out.println("each additional term");
	    term(); //term
	}
    }

    private void term() {
	//System.out.println("we doing factor");
	factor(); //factor
	while(is(TK.TIMES) || is(TK.DIVIDE))
	{
	   multop(); //multop
	   factor(); //factor
	}
    }

    private void factor() {
	//System.out.println("We're in factor doing some shiettt");
	//Place holder else if
	if(is(TK.LPAREN))
	{
	    mustbe(TK.LPAREN); // (
	    //scan();
	    expr(); //expr
	    mustbe(TK.RPAREN); // )
	}
	else if(is(TK.ID) || is(TK.TILDE))
	{
	    ref_id(); //ref_id
	}
	else if(is(TK.NUM))
	{
	    //scan();
	    mustbe(TK.NUM); //number
	}
    }

//Add,Subtr,Mult,Div operations
    private void addop() {
	if(is(TK.PLUS) || is(TK.MINUS))
	{
	    scan();
	}	
    }

    private void multop() {
	if(is(TK.TIMES) || is(TK.DIVIDE))
	{
	    scan();
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
