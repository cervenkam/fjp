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
class Symbol{
	public final Token ident;
	public final int SP;
	public final byte deep;
	public final byte type;
	public Symbol(Token ident,int SP,byte deep,byte type){
		this.ident = ident;
		this.SP = SP;
		this.deep = deep;
		this.type = type;
	}
}
public class I386 extends pl0BaseListener{
	private static final byte TYPE_VARIABLE=0;
	private static final byte TYPE_PROCEDURE=1;
	private static final byte TYPE_CONSTANT=2;
	private static final byte TYPE_LABEL=3;
	private static final byte TYPE_GOTO=4;
	private static final boolean[][] map = {
		{false,false,true ,false,false},
		{true ,false,false,false,false},
		{false,true ,false,false,false},
		{true ,false,false,false,false},
		{false,true ,false,false,false},
		{true ,false,true ,false,false},
		{true ,false,false,false,false},
		{true ,false,true ,false,false}
	};
	private final Deque<Integer> jump_requests = new LinkedList<>();
	private final Deque<Integer> jump_backwards = new LinkedList<>();
	private final List<Byte> program = new ArrayList<>();
	private final List<Symbol> symbols = new ArrayList<>();
	private final List<Symbol> goto_symbols = new ArrayList<>();
	private final List<Symbol> labels = new ArrayList<>();
	private final ILoader loader = new KernelLoader();
	private final String path;
	private byte deep = 0;
	private int push_pop_ratio = 0;
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
		/*StackTraceElement[] l = Thread.currentThread().getStackTrace();
		System.out.println(l[2].getFileName()+":"+l[2].getLineNumber());
		System.out.println("\t"+hint);*/
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
		add_program(0x66,0x67,0x50);
		push_pop_ratio+=4;
	}
	public void pop_EBX(){
		hint("pop ebx");
		add_program(0x66,0x67,0x5b);
		push_pop_ratio-=4;
	}
	public void pop_EAX(){
		hint("pop eax");
		add_program(0x66,0x67,0x58);
		push_pop_ratio-=4;
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
	public void set_program_word(int value,int pos){
		program.set(pos  ,(byte)(value&0xff));
		program.set(pos+1,(byte)((value>>>8)&0xff));
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
	public void load(Symbol s){
		if(s.deep == deep){
			hint("mov eax,ebp");
			add_program(0x66,0x89,0xe8);
		}else{
			hint("mov eax,[ss:ebp-"+(4*s.deep)+"]");
			add_program(0x66,0x67,0x36,0x8b,0x85);
			add_int(-4*s.deep);
		}
		hint("sub eax,"+(4*(s.deep+1+s.SP)));
		add_program(0x66,0x2d);
		add_int(4*(s.deep+1+s.SP));
		push_EAX();
	}
	public void store(Symbol s){
		pop_EAX();
		store_EAX(s);
	}
	public void store_EAX(Symbol s){
		hint("mov dword [ss:"+s.SP+"],eax");
		add_program(0x36,0x66,0xa3); //TODO word addr
		add_word(s.SP); //TODO word addr
	}
	public void print(String path){
		try{
			Files.write(new File(path).toPath(),getArray());
		}catch(IOException e){
			e.printStackTrace();
			error("Program cannot be saved!, details:");
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
		while((program.size()&0x1ff)!=0){
			add_program(0x90);
		}
		set_program_word(511+(program.size()>>9),0x2d);
		print(path);
		hint("PUSH/POP: "+push_pop_ratio);
		if(push_pop_ratio!=0){
			error("Push/pop ratio is "+push_pop_ratio+", should be zero");
		}
	}
	@Override public void enterWhilestmt(pl0Parser.WhilestmtContext ctx){
		jump_backwards.addFirst(program.size());
	}
	@Override public void exitWhilestmt(pl0Parser.WhilestmtContext ctx){
		int pc = jump_backwards.pop();
		hint("jmp "+(pc-program.size()-4));
		add_program(0x66,0xe9);
		add_int(pc-program.size()-4);
		pc = jump_requests.pop();
		hint("WHILE: set start address to "+(program.size()-pc-4));
		set_program(program.size()-pc-4,pc);
	}
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
	@Override public void exitElsebranch(pl0Parser.ElsebranchContext ctx){
		if(ctx.ELSE()!=null){
			int pc = jump_requests.pop();
			hint("ELSE: set start address to "+(program.size()-pc-4));
			set_program(program.size()-pc-4,pc);
		}
	}
	public void dereferenceStack_EAX(){
		pop_EBX();
		hint("mov eax,[ss:ebx]");
		add_program(0x66,0x67,0x36,0x8b,0x03);
	}
	@Override public void exitWritestmt(pl0Parser.WritestmtContext ctx){
		String name = ctx.ident().STRING().getSymbol().getText();
		Symbol s = findSymbol(symbols,name);
		if(s!=null){
			if(s.type==TYPE_CONSTANT){
				pop_EAX();
			}else{
				dereferenceStack_EAX();
			}
		}else{
			error("Symbol \""+name+"\" does not exist");
		}
		hint("call write");
		add_program(loader.write());
	}
	@Override public void exitQstmt(pl0Parser.QstmtContext ctx){
		hint("call read");
		add_program(loader.read());
		pop_EBX();
		hint("mov [ss:ebx],eax");
		add_program(0x66,0x67,0x36,0x89,0x03);
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
	public Symbol findSymbol(List<Symbol> syms,String search){
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
	public boolean isADeclarationContext(Object o){
		return o instanceof pl0Parser.VarsContext ||
			   o instanceof pl0Parser.ConstsContext ||
			   o instanceof pl0Parser.ProcedureContext ||
			   o instanceof pl0Parser.CallstmtContext;
	}
	public boolean isValid(ParserRuleContext idx,int type){
		int rule = 0;
		//TODO dependent
		switch(idx.getRuleIndex()){
			case  2: rule=0; break;
			case  3: rule=1; break;
			case  4: rule=2; break;
			case  8: rule=3; break;
			case  9: rule=4; break;
			case 10: rule=5; break;
			case 11: rule=6; break;
			case 20: rule=7; break;
		}
		return map[rule][type];
		
	}
	@Override public void exitIdent(pl0Parser.IdentContext ctx){
		String name = ctx.STRING().getSymbol().getText();
		Symbol s = findSymbol(symbols,name);
		if(s!=null){
			if(isValid(ctx.getParent(),s.type)){
				if(!isADeclarationContext(ctx.getParent())){
					if(s.type==TYPE_CONSTANT){
						push_int(s.SP);	
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
	@Override public void exitNumber(pl0Parser.NumberContext ctx){
		if(!(ctx.getParent() instanceof pl0Parser.ConstsContext)){
			int number = parseInt(ctx.NUMBER().getSymbol().getText());
			push_int(number);
		}
	}
	public void push_int(int num){
		hint("push "+num);
		add_program(0x66,0x67,0x68);
		push_pop_ratio+=4;
		add_int(num);
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
			symbols.add(new Symbol(idc.get(a).STRING()
				.getSymbol(),a,deep,TYPE_VARIABLE));
		}
		if(ctx.consts()!=null){
			List<pl0Parser.IdentContext> ident = ctx.consts().ident();
			List<pl0Parser.NumberContext> numcon = ctx.consts().number();
			for(int a=0; a<ident.size(); a++){
				symbols.add(new Symbol(ident.get(a).STRING().getSymbol(),
					parseInt(numcon.get(a).NUMBER().getText()),
					deep,TYPE_CONSTANT));
			}
		}
		int size = 4*idc.size();
		//add_program(0x66,0x89,0xe0,0x9a,0x80,0x01,0xc0,0x07); //TODO remove
		hint("enter "+size+","+deep);
		add_program(0x66,0x67,0xc8,
			(byte)(size&0xff),(byte)((size>>8)&0xff),deep);
		//add_program(0x66,0x89,0xe0,0x9a,0x80,0x01,0xc0,0x07); //TODO remove
		hint("jmp near <block>");
		add_program(0x66,0xe9);
		jump_requests.addFirst(program.size());
		add_int(0);
	}
	@Override public void enterProcedure(pl0Parser.ProcedureContext ctx){
		symbols.add(new Symbol(ctx.ident().STRING().getSymbol(),
			program.size(),deep,TYPE_PROCEDURE));
	}
	@Override public void enterLabel(pl0Parser.LabelContext ctx){
		labels.add(new Symbol(ctx.STRING().getSymbol(),
			program.size(),deep,TYPE_LABEL));
	}
	@Override public void enterGotostmt(pl0Parser.GotostmtContext ctx){
		hint("jmp <???>");
		add_program(0x66,0xe9);
		goto_symbols.add(new Symbol(ctx.STRING().getSymbol(),
			program.size(),deep,TYPE_GOTO));
		add_int(0);
	}
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
					set_program(lab.SP-gt.SP-4,gt.SP);
					continue outer;
				}	
			}
			error("Label \""+gt.ident.getText()+"\" not found");
		}
		clearVariables(symbols);
		clearVariables(goto_symbols);
		clearVariables(labels);
		deep--;
	}
	@Override public void exitProcedure(pl0Parser.ProcedureContext ctx){
		hint("ret");	
		add_program(0x66,0xc3);
	}
	@Override public void exitCallstmt(pl0Parser.CallstmtContext ctx){
		String name = ctx.ident().STRING().getSymbol().getText();
		Symbol s = findSymbol(symbols,name);
		if(s!=null && s.type==TYPE_PROCEDURE){
			hint("call "+s.SP);
			add_program(0x66,0xe8);	
			add_int(s.SP-program.size()-4);
		}else{
			error("Procedure "+ctx.ident().STRING()+" not found");
		}
	}
	@Override public void exitAssignstmt(pl0Parser.AssignstmtContext ctx){
		pop_EAX();
		pop_EBX();
		hint("mov [ss:ebx],eax");
		add_program(0x66,0x67,0x36,0x89,0x03);
	}
	public void clearVariables(List<Symbol> syms){
		for(int a=0; a<syms.size(); a++){
			Symbol s = syms.get(a);
			if(s.deep >= deep){
				syms.remove(a);
				hint("Removing symbol");
				a--;
			}
		}
	}
	public void switchText(String text){
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
				add_program(0x66,0x85,0xd8);
				hint("jnz <rel_addr>");
				add_program(0x66,0x0f,0x85);
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
	@Override public void exitCondition(pl0Parser.ConditionContext ctx){
		if(ctx.ODD()!=null){
			pop_EAX();
			switchText("ODD");
		}else{
			pop_EBX();
			pop_EAX();
			switchText(((TerminalNode)ctx.getChild(1)).getSymbol().getText());
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
				add_program(0x66,0xf7,0xd8);
				push_EAX();
			}
		}
	}
	@Override public void exitTerm(pl0Parser.TermContext ctx){
		int children = ctx.getChildCount();
		if(children==3){
			pop_EBX();
			pop_EAX();
			switchText(((TerminalNode)ctx.getChild(1)).getSymbol().getText());
			push_EAX();
		}
	}
	@Override public void exitFactor(pl0Parser.FactorContext ctx){
		if(ctx.getChild(0) instanceof pl0Parser.IdentContext){
			pl0Parser.IdentContext idx=(pl0Parser.IdentContext)ctx.getChild(0);
			String name = idx.STRING().getSymbol().getText();
			Symbol s = findSymbol(symbols,name);
			if(s!=null && s.type==TYPE_VARIABLE){
				dereferenceStack_EAX();	
				push_EAX();
			}
		}
	}
	@Override public void enterStatement(pl0Parser.StatementContext ctx){
		if(ctx.getParent() instanceof pl0Parser.BlockContext){	
			int pc = jump_requests.pop();
			hint("MAIN: set start address to "+(program.size()-pc-4));
			set_program(program.size()-pc-4,pc);
		}
	}
	public int parseInt(String val){
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
}