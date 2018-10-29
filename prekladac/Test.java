package prekladac;
import static prekladac.Node.Type.*;
public class Test{
	public static void main(String[] args){
		Node n = new Node(PROGRAM,null,0);
		Node b = new Node(BLOCK,n,1);
		n.add(b);
		n.add(new Node(KEYWORD,n,1).setTerminal("."));	
		Node d = new Node(DECLARE,b,2);
		b.add(d);
		d.add(new Node(KEYWORD,d,3).setTerminal("var"));
		d.add(new Node(IDENT,d,3).setTerminal("test"));
		d.add(new Node(KEYWORD,d,3).setTerminal(";"));
		System.out.println(n);
		I386 compiler = new I386(n);
		compiler.compile();
		compiler.print("test.bin");
	}
}
