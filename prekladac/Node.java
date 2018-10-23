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
	private final Node parent;
	/* typ tohoto vyrazu */
	private final Type type;
	/* neterminalni symbol pro identifikator nebo klicove slovo */
	private String terminal_string = null;
	/* neterminalni symbol pro cislo */
	private Integer terminal_int = null;
	public Node(Type type,Node parent){
		this.parent = parent;
		this.type = type;
	}
	public boolean isTerminal(){
		return size()==0;
	}
	public Type getType(){
		return type;
	}
	public void setTerminal(String terminal){
		this.terminal_string = terminal;
	}
	public void setTerminal(int terminal){
		this.terminal_int = terminal;
	}
	public int getTerminalInt(){
		return terminal_int;
	}
	public String getTerminalString(){
		return terminal_string;
	}
}
