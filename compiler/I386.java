package compiler;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Deque;
import java.util.Collections;
/**
 * Wrapping class containing information about one symbol
 * - for instance variable, constant, procedure, label etc.
 * @author Martin Cervenka
 * @version 20190101
 */
class Symbol{
	/** Token containing ANTLR informations about given symbol */
	public final Token ident;
	/** Pointer (stack/code), where given symbol exists */
	public final int pointer;
	/** Degree of deepness, where is the symbol declared */
	public final byte deep;
	/** Type of the symbol */
	public final byte type;
	/**
	 * C'tor of the symbol
	 * @param ident Token containing ANTLR informations about given symbol
	 * @param pointer Pointer (stack/code), where given symbol exists
	 * @param deep Degree of deepness, where is the symbol declared
	 * @param type Type of the symbol
	 */
	public Symbol(Token ident,int pointer,byte deep,byte type){
		this.ident = ident;
		this.pointer = pointer;
		this.deep = deep;
		this.type = type;
	}
}
/**
 * Compilator class
 * @author Martin Cervenka
 * @version 20190101
 */
public class I386 extends pl0BaseListener{
	/** Type of the symbol - variable */
	private static final byte TYPE_VARIABLE=0;
	/** Type of the symbol - procedure */
	private static final byte TYPE_PROCEDURE=1;
	/** Type of the symbol - constant */
	private static final byte TYPE_CONSTANT=2;
	/** Type of the symbol - label */
	private static final byte TYPE_LABEL=3;
	/** Type of the symbol - goto statement */
	private static final byte TYPE_GOTO=4;
	/** Stack of jump request (jumps forward) - program pointers,
		where address will be filled in the future */
	private final Deque<Integer> jump_requests = new LinkedList<>();
	/** Stack of backwards jumps - contains pointers to locations,
		where the program will be jumping in the future */
	private final Deque<Integer> jump_backwards = new LinkedList<>();
	/** Opcodes of the compiled program */
	private final List<Byte> program = new ArrayList<>();
	/** List of all symbols in the current and higher scopes */
	private final List<Symbol> symbols = new ArrayList<>();
	/** List of all goto symbols - places with goto statement */
	private final List<Symbol> goto_symbols = new ArrayList<>();
	/** List of all label symbols - places where program may
		jump in the future */
	private final List<Symbol> labels = new ArrayList<>();
	/** Program loader - wraps current program to be able to
		communicate with HW */
	private final ILoader loader = new KernelLoader();
	/** Path to the program output file */
	private final String path;
	/** ANTLR which parser source code */
	private final pl0Parser parser;
	/** Current program deepness */
	private byte deep = 0;
	/** Ratio between stack push and pop, protects stack */
	private int push_pop_ratio = 0;
	/**
	 * Main directly executable method
	 * @param args 1. argument - source code file name (default - hilbert.pl0),
		2. argument - output file name (default - out.bin)
	 */
	public static void main(String[] args){
		try{
			String in = "hilbert.pl0";
			if(args.length>0){
				in=args[0];
			}else{
				System.err.println("First argument (input source file) is not"+
					"defined, using "+in);
			}
			String out = "out.bin";
			if(args.length>1){
				out=args[1];
			}else{
				System.err.println("Second argument (output binary file) is "+
					"not defined, using "+out);
			}
			pl0Lexer lexer = new pl0Lexer(CharStreams.fromFileName(in));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			pl0Parser parser = new pl0Parser(tokens);
			new ParseTreeWalker().walk(new I386(out,parser),parser.program());
		}catch(Exception e){
			e.printStackTrace();
			error("Compilation has been terminated");
		}
	}
	/**
	 * Prints hint for every opcode insertion
	 * @param hint Hint for given opcode
	 */
	public static void hint(String hint){
		//System.out.println(hint); //uncomment in case of debugging
	}
	/**
	 * Prints error message and quits compilation
	 * @param message Error message
	 */
	public static void error(String message){
		System.err.println(message);
		System.exit(-1);
	}
	/**
	 * Compiler c'tor 
	 * @param path Path to the output file (binary)
	 * @param parser ANTLR parser which parses input
	 */
	public I386(String path,pl0Parser parser){
		this.path = path;
		this.parser = parser;
	}
	/**
	 * Adds given values (bytes) to the program
	 * @param values Bytes to append to the program
	 */
	public void add_program(int... values){
		for(int val: values){
			program.add((byte)val);
		}
	}
	/**
	 * Adds given values (bytes) to the program
	 * @param values Bytes to append to the program
	 */
	public void add_program(byte... values){
		for(byte val: values){
			program.add(val);
		}
	}
	/**
	 * Pushes content of the EAX register onto the stack
	 */
	public void push_EAX(){
		hint("push eax");
		add_program(0x66,0x67,0x50);
		push_pop_ratio+=4;
	}
	/**
	 * Pops top of the stack into EBX register
	 */
	public void pop_EBX(){
		hint("pop ebx");
		add_program(0x66,0x67,0x5b);
		push_pop_ratio-=4;
	}
	/**
	 * Pops top of the stack into EAX register
	 */
	public void pop_EAX(){
		hint("pop eax");
		add_program(0x66,0x67,0x58);
		push_pop_ratio-=4;
	}
	/**
	 * Adds one integer value (4B) at the end of the program
	 * @param value Value, which will be apended to the program
	 */
	public void add_int(int value){
		//little endian
		add_program(
			(byte)(value&0xff),
			(byte)((value>>>8)&0xff),
			(byte)((value>>>16)&0xff),
			(byte)((value>>>24)&0xff)
		);
	}
	/**
	 * Sets one integer value (4B) at the specified position in the program
	 * @param value Value, which will be set in the program
	 * @param pos Program position, where it will be set
	 */
	public void set_program(int value,int pos){
		//little endian
		program.set(pos  ,(byte)(value&0xff));
		program.set(pos+1,(byte)((value>>>8)&0xff));
		program.set(pos+2,(byte)((value>>>16)&0xff));
		program.set(pos+3,(byte)((value>>>24)&0xff));
	}
	/**
	 * Loads given symbol from its position and pushes its value onto the stack
	 * @param s Symbol to load
	 */
	public void load(Symbol s){
		if(s.deep == deep){
			hint("mov eax,ebp");
			add_program(0x66,0x89,0xe8);
		}else{
			hint("mov eax,[ss:ebp-"+(4*s.deep)+"]");
			add_program(0x66,0x67,0x36,0x8b,0x85);
			add_int(-4*s.deep);
		}
		hint("sub eax,"+(4*(s.deep+1+s.pointer)));
		add_program(0x66,0x2d);
		add_int(4*(s.deep+1+s.pointer));
		push_EAX();
	}
	/**
	 * Prints program into the given file
	 * @param path Output file
	 */
	public void print(String path){
		try{
			Files.write(new File(path).toPath(),get_array());
		}catch(IOException e){
			e.printStackTrace();
			error("Program cannot be saved!");
		}
	}
	/**
	 * Method returns array of program bytes
	 * @return Program bytes
	 */
	public byte[] get_array(){
		byte[] arr = new byte[program.size()];
		for(int a=0; a<arr.length; a++){
			arr[a] = program.get(a);
		}
		return arr;
	}
	/**
	 * Dereference top of the stack and dereferenced value moves
	 *	into EAX register
	 */
	public void dereference_stack_EAX(){
		pop_EBX();
		hint("mov eax,[ss:ebx]");
		add_program(0x66,0x67,0x36,0x8b,0x03);
	}
	/**
	 * Converts one hexadecimal ASCII character [0-9a-fA-F] to byte value
	 * @param c Character in ASCII code
	 * @return Its value
	 */
	private byte hex2byte(char c){
		if(c<='9' && c>='0'){
			return (byte)(c-'0');
		}else if(c<='F' && c>='A'){
			return (byte)(c-'A'+10);
		}else if(c<='f' && c>='a'){
			return (byte)(c-'a'+10);
		}else{
			return (byte)0;
		}
	}
	/**
	 * Finds given symbol in the specified list
	 * @param syms Searched list of symbols
	 * @param search Symbol name to find
	 * @return Symbol with given name / null
	 */
	public Symbol find_symbol(List<Symbol> syms,String search){
		Symbol to_find = null;
		for(int a=syms.size()-1; a>=0; a--){
			Symbol sym = syms.get(a);
			if(sym.ident.getText().equals(search)){
				to_find = sym;
				break;
			}
		}
		return to_find;
	}
	/**
	 * Returns whether given object (non-terminal symbol) is a
	 *	declaration or not, if yes it can be loaded
	 * @param o Testing object
	 * @return Is given non-terminal symbol a declaration?
	 */
	public boolean is_a_declaration_context(Object o){
		return o instanceof pl0Parser.VarsContext ||
			   o instanceof pl0Parser.ConstsContext ||
			   o instanceof pl0Parser.ProcedureContext ||
			   o instanceof pl0Parser.CallstmtContext;
	}
	/**
	 * Returns whether given non-terminal symbol can be used in given context
	 * For instance it is not possible to assign into a constant, but it is
	 * possible to assing into a variable
	 * @param idx Context of the symbol (call/read/write etc.)
	 * @param type Type of the identifier (variable/procedure etc.)
	 * @return Is the operation allowed?
	 */
	public boolean is_valid(ParserRuleContext idx,int type){
		/* Map, where given symbol can be used, row index means
			type of the operation, column index means type of the symbol */
		final boolean[][] map = {
			{false,false,true ,false,false},
			{true ,false,false,false,false},
			{false,true ,false,false,false},
			{true ,false,false,false,false},
			{false,true ,false,false,false},
			{true ,false,true ,false,false},
			{true ,false,false,false,false},
			{true ,false,true ,false,false}
		};
		int rule= 0;
		String[] names = parser.getRuleNames();
		switch(names[idx.getRuleIndex()]){
			case "consts":     rule=0; break;
			case "vars":       rule=1; break;
			case "procedure":  rule=2; break;
			case "assignstmt": rule=3; break;
			case "callstmt":   rule=4; break;
			case "writestmt":  rule=5; break;
			case "qstmt":      rule=6; break;
			case "factor":     rule=7; break;
			default:           return false;
		}
		return map[rule][type];
	}
	/**
	 * Pushes immediate value onto the stack
	 * @param num Number pushed onto the top of the stack
	 */
	public void push_int(int num){
		hint("push "+num);
		add_program(0x66,0x67,0x68);
		push_pop_ratio+=4;
		add_int(num);
	}
	/**
	 * Clears all current scope variables when program exits this scope
	 * @param syms List of symbols
	 */
	public void clear_variables(List<Symbol> syms){
		for(int a=0; a<syms.size(); a++){
			Symbol s = syms.get(a);
			if(s.deep >= deep){
				syms.remove(a);
				hint("Removing symbol");
				a--;
			}
		}
	}
	/**
	 * Makes an operation defined by the parameter
	 * @param text Operation (for example =,#,+,- etc.)
	 */
	public void switch_text(String text){
		text=text.toUpperCase();
		switch(text){
			case "=":
			case "#":
			case ">=":
			case "<=":
			case "<":
			case ">":
				hint("cmp eax,ebx");
				add_program(0x66,0x39,0xd8);
		}
		switch(text){
			case "+":
				hint("add eax,ebx");
				add_program(0x66,0x01,0xd8);
				break;
			case "-":
				hint("sub eax,ebx");
				add_program(0x66,0x29,0xd8);
				break;
			case "*":
				hint("mul ebx");
				add_program(0x66,0xf7,0xe3);
				break;
			case "/":
				hint("xor edx,edx");
				add_program(0x66,0x31,0xd2);
				hint("div ebx");
				add_program(0x66,0xf7,0xf3);
				break;
			case "NOT":
				hint("neg eax");
				add_program(0x66,0xf7,0xd0);
				break;
			case "LEFT":
				hint("mov cl,bl");
				add_program(0x88,0xd9);
				hint("shl eax,cl");
				add_program(0x66,0xd3,0xe0);
				break;
			case "RIGHT":
				hint("mov cl,bl");
				add_program(0x88,0xd9);
				hint("shr eax,cl");
				add_program(0x66,0xd3,0xe8);
				break;
			case "AND":
				hint("and eax,ebx");
				add_program(0x66,0x21,0xd8);
				break;
			case "XOR":
				hint("or eax,ebx");
				add_program(0x66,0x31,0xd8);
				break;
			case "OR":
				hint("or eax,ebx");
				add_program(0x66,0x09,0xd8);
				break;
			case "=":
				hint("jne <rel_addr>");
				add_program(0x66,0x0f,0x85);
				break;
			case "#":
				hint("je <rel_addr>");
				add_program(0x66,0x0f,0x84);
				break;
			case ">=":
				hint("jl <rel_addr>");
				add_program(0x66,0x0f,0x8c);
				break;
			case "<=":
				hint("jg <rel_addr>");
				add_program(0x66,0x0f,0x8f);
				break;
			case ">":
				hint("jle <rel_addr>");
				add_program(0x66,0x0f,0x8e);
				break;
			case "<":
				hint("jge <rel_addr>");
				add_program(0x66,0x0f,0x8d);
				break;
			case "ODD":
				hint("test eax,0x1");
				add_program(0x66,0xa9);
				add_int(1);
				hint("jz <rel_addr>");
				add_program(0x66,0x0f,0x84);
				break;
		}
		switch(text){
			case "=":
			case "#":
			case "<":
			case ">":
			case "<=":
			case ">=":
			case "ODD":
				jump_requests.addFirst(program.size());
				add_int(0);
		}
	}
	/**
	 * Parses integer value, determines its base by its prefix.
	 * Allowed bases are: hexadecimal (prefix 0x), binary (prefix 0b),
	 * octal (prefix 0) and decimal (without prefix)
	 * @param val String representation of the number
	 */
	public int parse_int(String val){
		if(val.startsWith("0x")){
			return Integer.parseInt(val.substring(2),16);
		}else if(val.startsWith("0b")){
			return Integer.parseInt(val.substring(2),2);
		}else if(val.equals("0")){
			return 0;
		}else if(val.charAt(0)=='0'){
			return Integer.parseInt(val.substring(1),8);
		}else{
			return Integer.parseInt(val);
		}
	}
	/**
	 * Executes when parser enters every rule	
	 * @param ctx Current context
	 */
	@Override public void enterEveryRule(ParserRuleContext ctx){
		hint("=>: "+ctx.getText());
	}
	/**
	 * Executes when parser exits every	rule
	 * @param ctx Current context
	 */
	@Override public void exitEveryRule(ParserRuleContext ctx){
		hint("<=: "+ctx.getText());
	}
	/**
	 * Executes when parser enters program	
	 * @param ctx Current context
	 */
	@Override public void enterProgram(pl0Parser.ProgramContext ctx){
		hint("... <loader> ...");
		add_program(loader.loader());
	}
	/**
	 * Executes when parser exits program	
	 * @param ctx Current context
	 */
	@Override public void exitProgram(pl0Parser.ProgramContext ctx){
		loader.finish(program);
		print(path);
		hint("PUSH/POP: "+push_pop_ratio);
		if(push_pop_ratio!=0){
			error("Push/pop ratio is "+push_pop_ratio+", should be zero");
		}
	}
	/**
	 * Executes when parser enters while statement	
	 * @param ctx Current context
	 */
	@Override public void enterWhilestmt(pl0Parser.WhilestmtContext ctx){
		jump_backwards.addFirst(program.size());
	}
	/**
	 * Executes when parser exits while statement	
	 * @param ctx Current context
	 */
	@Override public void exitWhilestmt(pl0Parser.WhilestmtContext ctx){
		int pc = jump_backwards.pop();
		hint("jmp "+(pc-program.size()-4));
		add_program(0x66,0xe9);
		add_int(pc-program.size()-4);
		pc = jump_requests.pop();
		hint("WHILE: set start address to "+(program.size()-pc-4));
		set_program(program.size()-pc-4,pc);
	}
	/**
	 * Executes when parser enters else branch
	 * @param ctx Current context
	 */
	@Override public void enterElsebranch(pl0Parser.ElsebranchContext ctx){
		int pc = jump_requests.pop();
		if(ctx.ELSE()!=null){
			hint("jmp near <block>");
			add_program(0x66,0xe9);
			jump_requests.addFirst(program.size());
			add_int(0);
		}
		hint("IF: set start address to "+(program.size()-pc-4));
		set_program(program.size()-pc-4,pc);
	}
	/**
	 * Executes when parser exits else branch	
	 * @param ctx Current context
	 */
	@Override public void exitElsebranch(pl0Parser.ElsebranchContext ctx){
		if(ctx.ELSE()!=null){
			int pc = jump_requests.pop();
			hint("ELSE: set start address to "+(program.size()-pc-4));
			set_program(program.size()-pc-4,pc);
		}
	}
	/**
	 * Executes when parser exits write statement	
	 * @param ctx Current context
	 */
	@Override public void exitWritestmt(pl0Parser.WritestmtContext ctx){
		String name = ctx.ident().STRING().getSymbol().getText();
		Symbol s = find_symbol(symbols,name);
		if(s!=null){
			if(s.type==TYPE_CONSTANT){
				pop_EAX();
			}else{
				dereference_stack_EAX();
			}
		}else{
			error("Symbol \""+name+"\" does not exist");
		}
		hint("call write");
		add_program(loader.write());
	}
	/**
	 * Executes when parser exits read statement	
	 * @param ctx Current context
	 */
	@Override public void exitQstmt(pl0Parser.QstmtContext ctx){
		hint("call read");
		add_program(loader.read());
		pop_EBX();
		hint("mov [ss:ebx],eax");
		add_program(0x66,0x67,0x36,0x89,0x03);
	}
	/**
	 * Executes when parser exits execute statement	
	 * @param ctx Current context
	 */
	@Override public void exitExecstmt(pl0Parser.ExecstmtContext ctx){
		String text = ctx.NUMBER().getSymbol().getText();
		if(!text.startsWith("0x")){
			error("Use execute with hexadecimal value, starting with '0x'");
		}
		for(int a=2; a<text.length(); a+=2){
			add_program(
				(hex2byte(text.charAt(a))<<4)+
				hex2byte(text.charAt(a+1))
			);
		}
	}
	/**
	 * Executes when parser exits identifier	
	 * @param ctx Current context
	 */
	@Override public void exitIdent(pl0Parser.IdentContext ctx){
		String name = ctx.STRING().getSymbol().getText();
		Symbol s = find_symbol(symbols,name);
		if(s!=null){
			if(is_valid(ctx.getParent(),s.type)){
				if(!is_a_declaration_context(ctx.getParent())){
					if(s.type==TYPE_CONSTANT){
						push_int(s.pointer);	
					}else{
						load(s);
					}
				}
			}else{
				error("Symbol \""+name+"\" cannot be used in this context");
			}
		}else{
			error("Symbol \""+name+"\" does not exist");
		}
	}
	/**
	 * Executes when parser exits number	
	 * @param ctx Current context
	 */
	@Override public void exitNumber(pl0Parser.NumberContext ctx){
		if(!(ctx.getParent() instanceof pl0Parser.ConstsContext)){
			int number = parse_int(ctx.NUMBER().getSymbol().getText());
			push_int(number);
		}
	}
	/**
	 * Executes when parser enters block	
	 * @param ctx Current context
	 */
	@Override public void enterBlock(pl0Parser.BlockContext ctx){
		deep++;
		List<pl0Parser.IdentContext> idc;
		if(ctx.vars()==null){
			idc = new ArrayList<pl0Parser.IdentContext>();
		}else{
			idc = ctx.vars().ident();
		}
		for(int a=0; a<idc.size(); a++){
			symbols.add(new Symbol(idc.get(a).STRING()
				.getSymbol(),a,deep,TYPE_VARIABLE));
		}
		if(ctx.consts()!=null){
			List<pl0Parser.IdentContext> ident = ctx.consts().ident();
			List<pl0Parser.NumberContext> numcon = ctx.consts().number();
			for(int a=0; a<ident.size(); a++){
				symbols.add(new Symbol(ident.get(a).STRING().getSymbol(),
					parse_int(numcon.get(a).NUMBER().getText()),
					deep,TYPE_CONSTANT));
			}
		}
		int size = 4*idc.size();
		hint("enter "+size+","+deep);
		add_program(0x66,0x67,0xc8,
			(byte)(size&0xff),(byte)((size>>8)&0xff),deep);
		hint("jmp near <block>");
		add_program(0x66,0xe9);
		jump_requests.addFirst(program.size());
		add_int(0);
	}
	/**
	 * Executes when parser enters procedure	
	 * @param ctx Current context
	 */
	@Override public void enterProcedure(pl0Parser.ProcedureContext ctx){
		symbols.add(new Symbol(ctx.ident().STRING().getSymbol(),
			program.size(),deep,TYPE_PROCEDURE));
	}
	/**
	 * Executes when parser enters label	
	 * @param ctx Current context
	 */
	@Override public void enterLabel(pl0Parser.LabelContext ctx){
		labels.add(new Symbol(ctx.STRING().getSymbol(),
			program.size(),deep,TYPE_LABEL));
	}
	/**
	 * Executes when parser enters goto statement	
	 * @param ctx Current context
	 */
	@Override public void enterGotostmt(pl0Parser.GotostmtContext ctx){
		hint("jmp <???>");
		add_program(0x66,0xe9);
		goto_symbols.add(new Symbol(ctx.STRING().getSymbol(),
			program.size(),deep,TYPE_GOTO));
		add_int(0);
	}
	/**
	 * Executes when parser exits block	
	 * @param ctx Current context
	 */
	@Override public void exitBlock(pl0Parser.BlockContext ctx){
		hint("leave");
		add_program(0x66,0x67,0xc9);
		outer:
		for(Symbol gt:goto_symbols){
			for(Symbol lab:labels){
				if(gt.ident.getText().equals(lab.ident.getText())){
					if(gt.deep!=lab.deep){
						error("ERR: goto "+gt.ident.getText()+
							" - jump in different scope");
					}
					set_program(lab.pointer-gt.pointer-4,gt.pointer);
					continue outer;
				}	
			}
			error("Label \""+gt.ident.getText()+"\" not found");
		}
		clear_variables(symbols);
		clear_variables(goto_symbols);
		clear_variables(labels);
		deep--;
	}
	/**
	 * Executes when parser exits procedure	
	 * @param ctx Current context
	 */
	@Override public void exitProcedure(pl0Parser.ProcedureContext ctx){
		hint("ret");	
		add_program(0x66,0xc3);
	}
	/**
	 * Executes when parser exits call statement	
	 * @param ctx Current context
	 */
	@Override public void exitCallstmt(pl0Parser.CallstmtContext ctx){
		String name = ctx.ident().STRING().getSymbol().getText();
		Symbol s = find_symbol(symbols,name);
		if(s!=null && s.type==TYPE_PROCEDURE){
			hint("call "+s.pointer);
			add_program(0x66,0xe8);	
			add_int(s.pointer-program.size()-4);
		}else{
			error("Procedure "+ctx.ident().STRING()+" not found");
		}
	}
	/**
	 * Executes when parser exits assignment	
	 * @param ctx Current context
	 */
	@Override public void exitAssignstmt(pl0Parser.AssignstmtContext ctx){
		pop_EAX();
		pop_EBX();
		hint("mov [ss:ebx],eax");
		add_program(0x66,0x67,0x36,0x89,0x03);
	}
	/**
	 * Executes when parser exits condition	
	 * @param ctx Current context
	 */
	@Override public void exitCondition(pl0Parser.ConditionContext ctx){
		if(ctx.ODD()!=null){
			pop_EAX();
			switch_text("ODD");
		}else{
			pop_EBX();
			pop_EAX();
			switch_text(((TerminalNode)ctx.getChild(1)).getSymbol().getText());
		}	
	}
	/**
	 * Executes when parser exits logical expression
	 * @param ctx Current context
	 */
	@Override public void exitLogical(pl0Parser.LogicalContext ctx){
		int children = ctx.getChildCount();
		if(children==3){
			pop_EBX();
			pop_EAX();
			switch_text(((TerminalNode)ctx.getChild(1)).getSymbol().getText());
			push_EAX();
		}else if(children==2){
			if(((TerminalNode)ctx.getChild(0))
					.getSymbol().getText().equalsIgnoreCase("NOT")){
				pop_EAX();
				switch_text("NOT");
				push_EAX();
			}
		}
	}
	/**
	 * Executes when parser exits expression	
	 * @param ctx Current context
	 */
	@Override public void exitExpression(pl0Parser.ExpressionContext ctx){
		int children = ctx.getChildCount();
		if(children==3){
			pop_EBX();
			pop_EAX();
			switch_text(((TerminalNode)ctx.getChild(1)).getSymbol().getText());
			push_EAX();
		}else if(children==2){
			if(((TerminalNode)ctx.getChild(0))
					.getSymbol().getText().equals("-")){
				pop_EAX();
				hint("neg eax");
				add_program(0x66,0xf7,0xd8);
				push_EAX();
			}
		}
	}
	/**
	 * Executes when parser exits term	
	 * @param ctx Current context
	 */
	@Override public void exitTerm(pl0Parser.TermContext ctx){
		int children = ctx.getChildCount();
		if(children==3){
			pop_EBX();
			pop_EAX();
			switch_text(((TerminalNode)ctx.getChild(1)).getSymbol().getText());
			push_EAX();
		}
	}
	/**
	 * Executes when parser exits factor	
	 * @param ctx Current context
	 */
	@Override public void exitFactor(pl0Parser.FactorContext ctx){
		if(ctx.getChild(0) instanceof pl0Parser.IdentContext){
			pl0Parser.IdentContext idx=(pl0Parser.IdentContext)ctx.getChild(0);
			String name = idx.STRING().getSymbol().getText();
			Symbol s = find_symbol(symbols,name);
			if(s!=null && s.type==TYPE_VARIABLE){
				dereference_stack_EAX();	
				push_EAX();
			}
		}
	}
	/**
	 * Executes when parser enters statement	
	 * @param ctx Current context
	 */
	@Override public void enterStatement(pl0Parser.StatementContext ctx){
		if(ctx.getParent() instanceof pl0Parser.BlockContext){	
			int pc = jump_requests.pop();
			hint("MAIN: set start address to "+(program.size()-pc-4));
			set_program(program.size()-pc-4,pc);
		}
	}
}
