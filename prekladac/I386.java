package prekladac;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import antlr.*;
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
	public final int deep;
	public Symbol(Token ident,int SP,int deep){
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
	private int SP = 0xffffff;
	private int deep = 0;
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
		SP-=4;
	}
	public void pop_EBX(){
		hint("pop ebx");
		add_program(0x5b);
		SP+=4;
	}
	public void pop_EAX(){
		hint("pop eax");
		add_program(0x58);
		SP+=4;
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
	public void allocate(byte elements){
		hint("sub esp, (byte)"+elements);
		add_program(0x83,0xec,elements);
		SP-=elements;
	}
	public void allocate(int elements){
		hint("sub esp,"+elements);
		add_program(0x81,0xec);
		add_int(elements);
		SP-=elements;
	}
	public void allocateSymbol(Symbol s){
		symbols.add(s);
	}
	public void load(Symbol s){
		hint("push imm/32");
		add_program(0x68);
		add_int(s.SP);
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
		clearStack();
		deep--;
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
	public void write(){
		pop_EBX();
		hint("mov eax,[ss:ebx]");
		add_program(0x36,0x8b,0x03);
		hint("call write");
		add_program(loader.write());
	}
	@Override public void exitBangstmt(pl0Parser.BangstmtContext ctx){
		write();
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
			if(!(ctx.getParent() instanceof pl0Parser.VarsContext)){
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
	@Override public void enterVars(pl0Parser.VarsContext ctx){
		for(pl0Parser.IdentContext ident: ctx.ident()){
			allocateSymbol(new Symbol(ident.STRING().getSymbol(),SP,deep));
		}
		allocate(4*ctx.ident().size());
	}
	@Override public void enterProcedure(pl0Parser.ProcedureContext ctx){
		allocateSymbol(new Symbol(ctx.ident().STRING().getSymbol(),SP,deep));
	}
	@Override public void exitCallstmt(pl0Parser.CallstmtContext ctx){
		pop_EAX();
		hint("jmp [cs:eax]");
		add_program(0x2e,0xff,0x20);	
	}
	@Override public void exitAssignstmt(pl0Parser.AssignstmtContext ctx){
		pop_EAX();
		pop_EBX();
		hint("mov [ss:ebx],eax");
		add_program(0x36,0x89,0x03);
	}
	@Override public void enterBlock(pl0Parser.BlockContext ctx){
		deep++;
	}
	@Override public void exitBlock(pl0Parser.BlockContext ctx){
		clearStack();
	}
	public void clearStack(){
		int allocated = 0;
		for(int a=0; a<symbols.size(); a++){
			Symbol s = symbols.get(a);
			if(s.deep >= deep){
				symbols.remove(a);
				hint("Removing symbol");
				allocated++;
				a--;
			}
		}
		if(allocated>0){
			if(allocated<0x80){
				allocate((byte)(-4*allocated));
			}else{
				allocate(-4*allocated);
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
				add_program(0x29,0xf3);
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
		hint("xor eax,eax");
		add_program(0x31,0xc0);
		for(int a=children-1; a>=0; a--){
			ParseTree t = ctx.getChild(a);
			if(t instanceof TerminalNode){
				switchText(((TerminalNode)t).getSymbol().getText());
			}else{
				pop_EBX();
			}
		}
		ParseTree first = ctx.getChild(0);
		if(!(first instanceof TerminalNode)){
			switchText("+");
		}
		push_EAX();
	}
	@Override public void exitTerm(pl0Parser.TermContext ctx){
		int children = ctx.getChildCount();
		hint("mov eax,1");
		add_program(0xb8,0x01,0x00,0x00,0x00);
		for(int a=children-1; a>=0; a--){
			ParseTree t = ctx.getChild(a);
			if(t instanceof TerminalNode){
				switchText(((TerminalNode)t).getSymbol().getText());
			}else{
				pop_EBX();
			}
		}
		switchText("*");
		push_EAX();
	}
}
