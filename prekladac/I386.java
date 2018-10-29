package prekladac;
import static prekladac.Node.Type.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Deque;
import java.util.Collections;
@FunctionalInterface
interface Functor{
	void apply(I386 i386,Node node,Integer integer);
}
class Symbol{
	public final Node ident;
	public final int SP;
	public Symbol(Node ident,int SP){
		this.ident = ident;
		this.SP = SP;
	}
}
public class I386{
	private final Deque<Integer> jump_requests = new LinkedList<>();
	private final Deque<Integer> jump_backwards = new LinkedList<>();
	private final Node node;
	private final List<Byte> program = new ArrayList<>();
	private final List<Symbol> symbols = new ArrayList<>();
	private int SP = 0xffffff;
	private final Functor[] functs = {
		I386::program,
		I386::declare,
		I386::block,
		I386::statement,
		I386::condition,
		I386::expression,
		I386::term,
		I386::factor,
		I386::ident,
		I386::number,
		I386::keyword
	};
	public I386(Node node){
		this.node = node;
	}
	public void add_program(int... values){
		for(int val: values){
			program.add((byte)val);
		}
	}
	public void add_program(byte... values){
		for(byte val: values){
			program.add(val);
		}
	}
	public void compile(){
		program.clear();
		symbols.clear();
		compile(node);
	}
	private void compile(Node node){
		Functor functor = functs[node.type.ordinal()];
		int a=0;
		functor.apply(this,node,a);
		if(!node.isTerminal()){
			for(Node subnode:node){
				a++;
				compile(subnode);
				functor.apply(this,node,a);
			}
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
		//sub eps,elements (byte)
		add_program(0x66,0x83,0xec,elements);
	}
	public void allocate(int elements){
		//sub esp,elements
		add_program(0x66,0x81,0xec);
		add_int(elements);
	}
	public void load(Symbol s){
		//mov eax,[ss:address]
		add_program(0x36,0x66,0xa1);
		add_word(s.SP);
		//push eax
		add_program(0x66,0x50);
	}
	public void store(Symbol s){
		//pop eax
		add_program(0x66,0x58);
		store_EAX(s);
	}
	public void store_EAX(Symbol s){
		//mov eax,[ss:address]
		add_program(0x36,0x66,0xa3);
		add_word(s.SP);
	}
	//GRAMMAR
	public void program(Node node,int element){
		add_program(Loader.loader);
	}
	public void statement(Node node,int element){
		//TODO everything except while & if
		String keyword = node.parent.get(0).getTerminalString();
		if(element==0){
			switch(keyword){
				case "while":
				//case "for":
					jump_backwards.addFirst(program.size());
			}
		}else if(element==node.size()){
			//jumps back (loops only)
			switch(keyword){
				case "while":
				//case "for":
					int pc = jump_backwards.pop();
					//mov ecx,<adresa>
					add_program(0x66,0xb9);
					add_int(pc);
					
			}
			//jump out if condition is not met
			switch(keyword){
				case "while":
				//case "for":
				case "if":
					int pc = jump_requests.pop();
					set_program(program.size(),pc);	
			}
		}
	}
	public void condition(Node node,int element){
		if(element==node.size()){
			//mov ecx,<adresa>
			jump_requests.addFirst(program.size()+2);
			add_program(0x66,0xb9,0,0,0,0);
			//pop eax
			add_program(0x66,0x58);
			if(node.parent.get(0).getTerminalString().equals("odd")){
				//test eax,eax
				add_program(0x66,0x85,0xc0);
				//jnp $+5
				add_program(0x7b,3);
			}else{
				//pop ebx
				add_program(0x66,0x5b);
				String op = node.parent.get(1).getTerminalString();
				if(op.contains(">") || op.contains("<")){
						//cmp eax,ebx
						add_program(0x66,0x39,0xd8);
						switch(op){
							case ">=":
								//jge $+5
								add_program(0x7d,3);
								break;
							case ">":
								//jg $+5
								add_program(0x7f,3);
								break;
							case "<=":
								//jle $+5
								add_program(0x7e,3);
								break;
							case "<":
								//jl $+5
								add_program(0x7c,3);
								break;
						}
				}
			}
			//jmp ecx
			add_program(0x66,0xff,0xe1);
		}
	}
	public void factor(Node node,int element){}
	public void declare(Node node,int element){
		if(element==node.size()){
			int declares = 0;
			for(Node sub: node){
				if(sub.type==IDENT){
					symbols.add(new Symbol(sub,SP));
					declares++;
					SP--;
				}
			}
			if(declares<0x80){
				allocate((byte)declares);
			}else{
				allocate(declares);
			}
		}
	}
	public void block(Node node,int element){
		if(element==node.size()){
			byte deallocation = 0;
			for(int a=symbols.size()-1; a>=0; a--){
				Symbol sym = symbols.get(a);
				if(sym.ident.deep > node.deep){
					symbols.remove(a);
					deallocation++;
				} 
			}
			if(deallocation<0x80){
				allocate(-(byte)deallocation);
			}else{
				allocate(-deallocation);
			}
		}
	}
	public void term(Node node,int element){
		if(element==node.size()){
			//pop eax
			add_program(0x66,0x58);
			for(int a=1; a<node.size(); a+=2){
				//pop ebx
				add_program(0x66,0x5b);
				if(node.get(a).getTerminalString()=="*"){
					//imul ebx
					add_program(0x66,0xf7,0xeb);
				}else{
					//idiv ebx
					add_program(0x66,0xf7,0xfb);
				}
			}
			//push eax
			add_program(0x66,0x50);
		}
	}
	public void expression(Node node,int element){
		if(element==node.size()){
			//pop eax
			add_program(0x66,0x58);
			int start = 1;
			//Java >=7 required for following:
			switch(node.parent.getTerminalString()){
				case "-":
					//neg eax
					add_program(0x66,0xf7,0xd8);
				case "+":
					start++;
			}
			for(int a=start; a<node.size(); a+=2){
				//pop ebx
				add_program(0x66,0x5b);
				if(node.get(a).getTerminalString()=="+"){
					//add eax,ebx
					add_program(0x66,0x01,0xd8);
				}else{
					//sub eax,ebx
					add_program(0x66,0x29,0xfb);
				}
			}
			//push eax
			add_program(0x66,0x50);
		}
	}
	public void ident(Node node,int element){
		if(element==node.size()){
			Symbol to_find = null;
			for(int a=symbols.size()-1; a>=0; a--){
				Symbol sym = symbols.get(a);
				if(sym.ident.getTerminalString().equals(
					node.getTerminalString())){
					to_find = sym;
					break;
				}
			}
			if(node.parent.type==FACTOR){
				load(to_find);
				return;
			}
			if(node.parent.get(0).getTerminalString()=="?"){
				//call [0x0:0x7d00]
				add_program(0x9a,0,0x7d,0,0);
				store_EAX(to_find);
				return;
			}
			//TODO function
			//TODO block
		}
	}
	public void number(Node node,int element){
		if(element==node.size()){
			//mov eax,
			add_program(0x66,0xb8);
			//immediate
			add_int(node.getTerminalInt());
			//push eax
			add_program(0x66,0x50);
		}
	}
	public void keyword(Node node,int element){
		if(element==node.size()){
			switch(node.getTerminalString()){
				case "true": number(
					new Node(null,null,0).setTerminal(1)
					,0); break;
				case "false": number(
					new Node(null,null,0).setTerminal(0)
					,0); break;
			}
		}
	}
	public byte[] getArray(){
		byte[] arr = new byte[program.size()];
		for(int a=0; a<arr.length; a++){
			arr[a] = program.get(a);
		}
		return arr;
	}
	public void print(String path){
		try{
			Files.write(new File(path).toPath(),getArray());
		}catch(IOException e){
			System.err.println("Program nelze ulozit!, detaily:");
			e.printStackTrace();
		}
	}
}
