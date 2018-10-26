package prekladac;
import static prekladac.Node.Type.*;
import java.util.function.BiConsumer;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
interface Functor extends BiConsumer<Node,Integer>{};
class Symbol{
	public final Node ident;
	public final long SP;
	public Symbol(Node ident,long SP){
		this.ident = ident;
		this.SP = SP;
	}
}
public class I386{
	private final Node node;
	private static final List<Byte> program = new ArrayList<>();
	private static final List<Symbol> symbols = new ArrayList<>();
	private static long SP = 0xffffff;
	private static long BP = 0xffffff;
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
	public void compile(){
		program.clear();
		symbols.clear();
		compile(node);
	}
	private void compile(Node node){
		Functor functor = functs[node.getType().ordinal()];
		int a=0;
		functor.accept(node,a);
		if(!node.isTerminal()){
			for(Node subnode:node){
				a++;
				compile(subnode);
				functor.accept(node,a);
			}
		}
	}
	public static void allocate(byte elements){
		//sub sp,elements
		Collections.addAll(program,(byte)0x83,(byte)0xec,elements);
	}
	public static void deallocate(byte elements){
		//add sp,elements
		Collections.addAll(program,(byte)0x83,(byte)0xc4,elements);
	}
	public static void program(Node node,int element){}
	public static void declare(Node node,int element){
		byte declares = 0;
		for(Node sub: node){
			if(sub.getType()==IDENT){
				symbols.add(new Symbol(sub,SP));
				declares++;
				SP--;
			}
		}
		allocate(declares);
	}
	public static void block(Node node,int element){
		if(element==node.size()){
			byte deallocation = 0;
			for(int a=symbols.size()-1; a>=0; a--){
				Symbol sym = symbols.get(a);
				if(sym.ident.getDeep() > node.getDeep()){
					symbols.remove(a);
					deallocation++;
				} 
			}
			deallocate(deallocation);
		}
	}
	public static void statement(Node node,int element){}
	public static void condition(Node node,int element){}
	public static void expression(Node node,int element){}
	public static void term(Node node,int element){
		if(element==node.size()){
			//pop eax
			Collections.addAll(program,(byte)0x66,(byte)0x58);
			for(int a=1; a<node.size(); a+=2){
				//pop ebx
				Collections.addAll(program,(byte)0x66,(byte)0x5b);
				if(node.get(a).getTerminalString()=="*"){
					//imul ebx
					Collections.addAll(program,(byte)0x66,(byte)0xf7,(byte)0xeb);
				}else{
					//idiv ebx
					Collections.addAll(program,(byte)0x66,(byte)0xf7,(byte)0xfb);
				}
			}
			//push eax
			Collections.addAll(program,(byte)0x66,(byte)0x50);
		}
	}
	public static void factor(Node node,int element){}
	public static void ident(Node node,int element){
		
	}
	public static void number(Node node,int element){
	
	}
	public static void keyword(Node node,int element){}
}
