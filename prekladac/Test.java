package prekladac;
import static prekladac.Node.Type.*;
public class Test{
	public static void main(String[] args){
		Node n = new Node(PROGRAM,null);
		Node b = new Node(BLOCK,n);
		n.add(b);
		n.add(new Node(KEYWORD,n).setTerminal("."));	
		Node d = new Node(DECLARE,b);
		b.add(d);
		d.add(new Node(KEYWORD,d).setTerminal("var"));
		d.add(new Node(IDENT,d).setTerminal("test"));
		d.add(new Node(KEYWORD,d).setTerminal(";"));
		b.add(new Node(KEYWORD,d).setTerminal("procedure"));
		b.add(new Node(IDENT,d).setTerminal("func"));
		b.add(new Node(KEYWORD,d).setTerminal(";"));
		Node blck = new Node(BLOCK,b);
		b.add(blck);
		b.add(new Node(KEYWORD,d).setTerminal(";"));
		Node stmnt = new Node(STATEMENT,d);
		b.add(stmnt);
		stmnt.add(new Node(KEYWORD,b).setTerminal("!"));
		Node expr = new Node(EXPRESSION,stmnt);
		stmnt.add(expr);
		Node term = new Node(TERM,expr);
		expr.add(term);
		Node factor = new Node(FACTOR,term);
		term.add(factor);
		factor.add(new Node(IDENT,factor).setTerminal("test"));
		System.out.println(n);
		I386 compiler = new I386(n);
		compiler.compile();
		compiler.print("test.bin");
	}
}
