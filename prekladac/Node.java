package prekladac;
import java.util.ArrayList;
/**
 * Rozhrani pro prekladac, verze 20181023
 *	Syntakticka a semanticka analyza by mela vytvorit strom z techto
 *      nodu a naplnit ho typem vyrazu a subvyrazy nebo terminaly, tento
 *	strom by mel prevzit prekladac do i386 a podle stromu vytvorit
 *	instrukce.
 */
public class Node extends ArrayList<Node>{
	/* Typ vyrazu, dle nasi gramatiky */
	public enum Type{
		/* mozne terminalni a neterminalni symboly */
		PROGRAM,DECLARE,BLOCK,STATEMENT,CONDITION,EXPRESSION,
		TERM,FACTOR,IDENT(true),NUMBER(true),KEYWORD(true);
		/* jedna-li se o terminalni symbol */
		private boolean terminal = false;
		private Type(){
			/* defaultni se jedna o neterminal */
			this(false);
		}
		private Type(boolean terminal){
			this.terminal = terminal;
		}
		public boolean isTerminal(){
			return terminal;
		}
	}
	/* nadrazeny vyraz, null pokud se jedna o koren */
	public final Node parent;
	/* typ tohoto vyrazu */
	public final Type type;
	/* neterminalni symbol pro identifikator nebo klicove slovo */
	private String terminal_string = null;
	/* neterminalni symbol pro cislo */
	private Integer terminal_int = null;
	/* deep zanoreni */
	public final int deep;
	/* vytvoreni korenu */
	public Node(){
		this(Type.PROGRAM,null,0);
	}
	/* vytvoreni ostatnich potomku */
	public Node(Type type,Node parent,int deep){
		this.parent = parent;
		this.type = type;
		this.deep = deep;
	}
	public boolean isTerminal(){
		return size()==0;
	}
	public Node setTerminal(String terminal){
		this.terminal_string = terminal;
		return this;
	}
	public Node setTerminal(int terminal){
		this.terminal_int = terminal;
		return this;
	}
	public int getTerminalInt(){
		return terminal_int;
	}
	public String getTerminalString(){
		return terminal_string;
	}
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		stringify(0,sb);
		return sb.toString();
	}
	public void stringify(int lvl,StringBuffer buff){
		for(int a=0; a<lvl; a++){
			buff.append(" ");
		}
		buff.append(type);
		if(terminal_string!=null){
			buff.append(" (\"").append(terminal_string).append("\")");
		}
		if(terminal_int!=null){
			buff.append(" (").append(terminal_int).append(")");
		}
		buff.append("\n");
		for(int a=0; a<size(); a++){
			get(a).stringify(lvl+1,buff);
		}	
	}
}
