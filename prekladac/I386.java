package prekladac;
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
class Symbol{
	public final Token ident;
	public final int SP;
	public final byte deep;
	public Symbol(Token ident,int SP,byte deep){
		this.ident = ident;
		this.SP = SP;
		this.deep = deep;
	}
}
public class I386 extends pl0BaseListener{
	private final Deque<Integer> jump_requests = new LinkedList<>();
	private final Deque<Integer> jump_backwards = new LinkedList<>();
	private final List<Byte> program = new ArrayList<>();
	private final List<Symbol> symbols = new ArrayList<>();
	private final ILoader loader = new KernelLoader();
	private final String path;
	private byte deep = -1;
	public static void main(String[] args) throws IOException{
		pl0Lexer lexer = new pl0Lexer(CharStreams.fromFileName(args[0]));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		new ParseTreeWalker().walk(new I386(args[1]),
			new pl0Parser(tokens).program());
	}
	public I386(String path){
		this.path = path;
	}
	public void hint(String hint){
		System.out.println(hint); //TODO remove on release
	}
	public void error(String message){
		System.err.println(message);
		System.exit(-1);
	}
	public void add_program(int... values){
		for(int val: values){
			program.add((byte)val);
		}
	}
	public void push_EAX(){
		hint("push eax");
		add_program(0x50);
	}
	public void pop_EBX(){
		hint("pop ebx");
		add_program(0x5b);
	}
	public void pop_EAX(){
		hint("pop eax");
		add_program(0x58);
	}
	public void add_program(byte... values){
		for(byte val: values){
			program.add(val);
		}
	}
	public void add_int(int value){
		add_program(
			(byte)(value&0xff),
			(byte)((value>>>8)&0xff),
			(byte)((value>>>16)&0xff),
			(byte)((value>>>24)&0xff)
		);
	}
	public void set_program(int value,int pos){
		program.set(pos  ,(byte)(value&0xff));
		program.set(pos+1,(byte)((value>>>8)&0xff));
		program.set(pos+2,(byte)((value>>>16)&0xff));
		program.set(pos+3,(byte)((value>>>24)&0xff));
	}
	public void add_word(int word){
		add_program(
			(byte)(word&0xff),
			(byte)((word>>>8)&0xff)
		);
	}
	/*public void allocate(byte elements){
		hint("sub esp, (byte)"+elements);
		add_program(0x83,0xec,elements);
	}
	public void allocate(int elements){
		hint("sub esp,"+elements);
		add_program(0x81,0xec);
		add_int(elements);
	}*/
	public void allocateSymbol(Symbol s){
		symbols.add(s);
	}
	public void load(Symbol s){
		if(s.deep == deep){
			hint("mov eax,ebp");
			add_program(0x89,0xe8);
		}else{
			hint("mov eax,[ss:ebp-"+(4*(s.deep+1))+"]");
			add_program(0x36,0x8b,0x85);
			add_int(4*(-s.deep-1));
		}
		hint("sub eax,"+(4*(s.deep+s.SP+1)));
		add_program(0x2d);
		add_int(4*(s.SP+s.deep+1));
		push_EAX();
	}
	public void store(Symbol s){
		pop_EAX();
		store_EAX(s);
	}
	public void store_EAX(Symbol s){
		hint("mov [ss:address],eax");
		add_program(0x36,0xa3);
		add_int(s.SP);
	}
	public void print(String path){
		try{
			Files.write(new File(path).toPath(),getArray());
		}catch(IOException e){
			System.err.println("Program cannot be saved!, details:");
			e.printStackTrace();
		}
	}
	public byte[] getArray(){
		byte[] arr = new byte[program.size()];
		for(int a=0; a<arr.length; a++){
			arr[a] = program.get(a);
		}
		return arr;
	}
	//GRAMMAR
	@Override public void enterEveryRule(ParserRuleContext ctx){
		hint("=>: "+ctx.getText());
	}
	@Override public void exitEveryRule(ParserRuleContext ctx){
		hint("<=: "+ctx.getText());
	}
	@Override public void enterProgram(pl0Parser.ProgramContext ctx){
		hint("... <loader> ...");
		add_program(loader.loader());
	}
	@Override public void exitProgram(pl0Parser.ProgramContext ctx){
		hint("jmp $");
		add_program(0xeb,0xfe);
		print(path);
	}
	@Override public void enterWhilestmt(pl0Parser.WhilestmtContext ctx){
		String keyword = ctx.getParent().getPayload().toString();
		jump_backwards.addFirst(program.size());
	}
	@Override public void exitWhilestmt(pl0Parser.WhilestmtContext ctx){
		String keyword = ctx.getParent().getPayload().toString();
		int pc = jump_backwards.pop();
		hint("mov ecx,<adresa>");
		add_program(0xb9);
		add_int(pc);
	}
	public void dereferenceStack_EAX(){
		pop_EBX();
		hint("mov eax,[ss:ebx]");
		add_program(0x36,0x8b,0x03);
	}
	public void write(){
		dereferenceStack_EAX();
		hint("call write");
		add_program(loader.write());
	}
	@Override public void exitWritestmt(pl0Parser.WritestmtContext ctx){
		write();
	}
	@Override public void exitQstmt(pl0Parser.QstmtContext ctx){
		//TODO
		pop_EAX();
		hint("call read");
		add_program(loader.read());
		push_EAX();
	}
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
	@Override public void exitExecstmt(pl0Parser.ExecstmtContext ctx){
		String text = ctx.HEXSTRING().getSymbol().getText();
		for(int a=2; a<text.length(); a+=2){
			add_program(
				(hex2byte(text.charAt(a))<<4)+
				hex2byte(text.charAt(a+1))
			);
		}
	}
	public Symbol findSymbol(String search){
		Symbol to_find = null;
		for(int a=symbols.size()-1; a>=0; a--){
			Symbol sym = symbols.get(a);
			if(sym.ident.getText().equals(search)){
				to_find = sym;
				break;
			}
		}
		return to_find;
	}
	@Override public void exitIdent(pl0Parser.IdentContext ctx){
		String name = ctx.STRING().getSymbol().getText();
		Symbol s = findSymbol(name);
		if(s!=null){
			if(!(ctx.getParent() instanceof pl0Parser.VarsContext) &&
			   !(ctx.getParent() instanceof pl0Parser.ProcedureContext)){
				load(s);
			}
		}else{
			error("Symbol "+name+" does not exists");
		}
	}
	@Override public void exitNumber(pl0Parser.NumberContext ctx){
		int number = Integer.valueOf(ctx.NUMBER().getSymbol().getText());
		if((byte)number==number){
			hint("push byte "+number);
			add_program(0x6a,(byte)number);
		}else{
			hint("push "+number);
			add_program(0x68);
			add_int(number);
		}
	}
	@Override public void enterBlock(pl0Parser.BlockContext ctx){
		deep++;
		List<pl0Parser.IdentContext> idc;
		if(ctx.vars()==null){
			idc = new ArrayList<pl0Parser.IdentContext>();
		}else{
			idc = ctx.vars().ident();
		}
		for(int a=0; a<idc.size(); a++){
			allocateSymbol(new Symbol(idc.get(a).STRING()
				.getSymbol(),a,deep));
		}
		int size = 4*idc.size();
		hint("enter "+size+","+deep);
		add_program(0xc8,(byte)(size&0xff),(byte)((size>>8)&0xff),deep);
		if(ctx.getParent() instanceof pl0Parser.ProgramContext){
			hint("jmp near <block>");
			add_program(0xe9);
			jump_requests.addFirst(program.size());
			add_int(0);
		}
	}
	@Override public void enterProcedure(pl0Parser.ProcedureContext ctx){
		allocateSymbol(new Symbol(ctx.ident().STRING().getSymbol(),
			program.size(),deep));
	}
	@Override public void exitBlock(pl0Parser.BlockContext ctx){
		hint("leave");
		add_program(0xc9);
	}
	@Override public void exitProcedure(pl0Parser.ProcedureContext ctx){
		hint("ret");	
		add_program(0xc3);
		clearVariables();
		deep--;
	}
	@Override public void exitCallstmt(pl0Parser.CallstmtContext ctx){
		String name = ctx.ident().STRING().getSymbol().getText();
		Symbol s = findSymbol(name);
		if(s!=null){
			hint("call [cs:"+s.SP+"]");
			add_program(0x2e,0xff,0x15);	
			add_int(s.SP);
		}else{
			System.err.println("Procedure "+ctx.ident()+" not found");
		}
	}
	@Override public void exitAssignstmt(pl0Parser.AssignstmtContext ctx){
		pop_EAX();
		pop_EBX();
		hint("mov [ss:ebx],eax");
		add_program(0x36,0x89,0x03);
	}
	public void clearVariables(){
		for(int a=0; a<symbols.size(); a++){
			Symbol s = symbols.get(a);
			if(s.deep >= deep){
				symbols.remove(a);
				hint("Removing symbol");
				a--;
			}
		}
	}
	public void switchText(String text){
		switch(text){
			case "+":
				hint("add eax,ebx");
				add_program(0x01,0xd8);
				break;
			case "-":
				hint("sub eax,ebx");
				add_program(0x29,0xd8);
				break;
			case "*":
				hint("mul ebx");
				add_program(0xf7,0xe3);
				break;
			case "/":
				hint("div ebx");
				add_program(0xf7,0xf3);
				break;
		}
	}
	@Override public void exitExpression(pl0Parser.ExpressionContext ctx){
		int children = ctx.getChildCount();
		if(children==3){
			pop_EBX();
			pop_EAX();
			switchText(((TerminalNode)ctx.getChild(1)).getSymbol().getText());
			push_EAX();
		}else if(children==2){
			if(((TerminalNode)ctx.getChild(0)).getSymbol().getText().equals("-")){
				pop_EAX();
				hint("neg eax");
				add_program(0x7f,0xd8);
				push_EAX();
			}
		}
	}
	@Override public void exitTerm(pl0Parser.TermContext ctx){
		int children = ctx.getChildCount();
		if(children==3){
			pop_EAX();
			pop_EBX();
			switchText(((TerminalNode)ctx.getChild(1)).getSymbol().getText());
			push_EAX();
		}
	}
	@Override public void exitFactor(pl0Parser.FactorContext ctx){
		if(ctx.getChild(0) instanceof pl0Parser.IdentContext){
			dereferenceStack_EAX();	
			push_EAX();
		}
	}
	@Override public void enterStatement(pl0Parser.StatementContext ctx){
		if(ctx.getParent().getParent() instanceof pl0Parser.ProgramContext){	
			int pc = jump_requests.pop();
			hint("set start address to "+program.size());
			set_program(program.size()-pc-4,pc);
		}
	}
}
