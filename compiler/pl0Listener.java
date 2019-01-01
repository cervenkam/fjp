// Generated from compiler/pl0.g4 by ANTLR 4.7.1
package compiler;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link pl0Parser}.
 */
public interface pl0Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link pl0Parser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(pl0Parser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(pl0Parser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(pl0Parser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(pl0Parser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#consts}.
	 * @param ctx the parse tree
	 */
	void enterConsts(pl0Parser.ConstsContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#consts}.
	 * @param ctx the parse tree
	 */
	void exitConsts(pl0Parser.ConstsContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#vars}.
	 * @param ctx the parse tree
	 */
	void enterVars(pl0Parser.VarsContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#vars}.
	 * @param ctx the parse tree
	 */
	void exitVars(pl0Parser.VarsContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#procedure}.
	 * @param ctx the parse tree
	 */
	void enterProcedure(pl0Parser.ProcedureContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#procedure}.
	 * @param ctx the parse tree
	 */
	void exitProcedure(pl0Parser.ProcedureContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(pl0Parser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(pl0Parser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#gotostmt}.
	 * @param ctx the parse tree
	 */
	void enterGotostmt(pl0Parser.GotostmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#gotostmt}.
	 * @param ctx the parse tree
	 */
	void exitGotostmt(pl0Parser.GotostmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(pl0Parser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(pl0Parser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#assignstmt}.
	 * @param ctx the parse tree
	 */
	void enterAssignstmt(pl0Parser.AssignstmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#assignstmt}.
	 * @param ctx the parse tree
	 */
	void exitAssignstmt(pl0Parser.AssignstmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#callstmt}.
	 * @param ctx the parse tree
	 */
	void enterCallstmt(pl0Parser.CallstmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#callstmt}.
	 * @param ctx the parse tree
	 */
	void exitCallstmt(pl0Parser.CallstmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#writestmt}.
	 * @param ctx the parse tree
	 */
	void enterWritestmt(pl0Parser.WritestmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#writestmt}.
	 * @param ctx the parse tree
	 */
	void exitWritestmt(pl0Parser.WritestmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#qstmt}.
	 * @param ctx the parse tree
	 */
	void enterQstmt(pl0Parser.QstmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#qstmt}.
	 * @param ctx the parse tree
	 */
	void exitQstmt(pl0Parser.QstmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#execstmt}.
	 * @param ctx the parse tree
	 */
	void enterExecstmt(pl0Parser.ExecstmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#execstmt}.
	 * @param ctx the parse tree
	 */
	void exitExecstmt(pl0Parser.ExecstmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#beginstmt}.
	 * @param ctx the parse tree
	 */
	void enterBeginstmt(pl0Parser.BeginstmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#beginstmt}.
	 * @param ctx the parse tree
	 */
	void exitBeginstmt(pl0Parser.BeginstmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#ifstmt}.
	 * @param ctx the parse tree
	 */
	void enterIfstmt(pl0Parser.IfstmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#ifstmt}.
	 * @param ctx the parse tree
	 */
	void exitIfstmt(pl0Parser.IfstmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#elsebranch}.
	 * @param ctx the parse tree
	 */
	void enterElsebranch(pl0Parser.ElsebranchContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#elsebranch}.
	 * @param ctx the parse tree
	 */
	void exitElsebranch(pl0Parser.ElsebranchContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#whilestmt}.
	 * @param ctx the parse tree
	 */
	void enterWhilestmt(pl0Parser.WhilestmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#whilestmt}.
	 * @param ctx the parse tree
	 */
	void exitWhilestmt(pl0Parser.WhilestmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(pl0Parser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(pl0Parser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(pl0Parser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(pl0Parser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(pl0Parser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(pl0Parser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(pl0Parser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(pl0Parser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#ident}.
	 * @param ctx the parse tree
	 */
	void enterIdent(pl0Parser.IdentContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#ident}.
	 * @param ctx the parse tree
	 */
	void exitIdent(pl0Parser.IdentContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(pl0Parser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(pl0Parser.NumberContext ctx);
}