// Generated from compiler/pl0.g4 by ANTLR 4.7.1
package compiler;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class pl0Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, WRITE=17, 
		READ=18, WHILE=19, DO=20, IF=21, THEN=22, ELSE=23, ODD=24, BEGIN=25, END=26, 
		CALL=27, CONST=28, VAR=29, PROCEDURE=30, EXECUTE=31, GOTO=32, LABEL=33, 
		STRING=34, NUMBER=35, WS=36, LINE_COMMENT=37;
	public static final int
		RULE_program = 0, RULE_block = 1, RULE_consts = 2, RULE_vars = 3, RULE_procedure = 4, 
		RULE_statement = 5, RULE_gotostmt = 6, RULE_label = 7, RULE_assignstmt = 8, 
		RULE_callstmt = 9, RULE_writestmt = 10, RULE_qstmt = 11, RULE_execstmt = 12, 
		RULE_beginstmt = 13, RULE_ifstmt = 14, RULE_elsebranch = 15, RULE_whilestmt = 16, 
		RULE_condition = 17, RULE_expression = 18, RULE_term = 19, RULE_factor = 20, 
		RULE_ident = 21, RULE_number = 22;
	public static final String[] ruleNames = {
		"program", "block", "consts", "vars", "procedure", "statement", "gotostmt", 
		"label", "assignstmt", "callstmt", "writestmt", "qstmt", "execstmt", "beginstmt", 
		"ifstmt", "elsebranch", "whilestmt", "condition", "expression", "term", 
		"factor", "ident", "number"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'.'", "'='", "','", "';'", "':='", "'#'", "'<'", "'<='", "'>'", 
		"'>='", "'+'", "'-'", "'*'", "'/'", "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, "WRITE", "READ", "WHILE", "DO", "IF", "THEN", 
		"ELSE", "ODD", "BEGIN", "END", "CALL", "CONST", "VAR", "PROCEDURE", "EXECUTE", 
		"GOTO", "LABEL", "STRING", "NUMBER", "WS", "LINE_COMMENT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "pl0.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public pl0Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			block();
			setState(47);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ConstsContext consts() {
			return getRuleContext(ConstsContext.class,0);
		}
		public VarsContext vars() {
			return getRuleContext(VarsContext.class,0);
		}
		public List<ProcedureContext> procedure() {
			return getRuleContexts(ProcedureContext.class);
		}
		public ProcedureContext procedure(int i) {
			return getRuleContext(ProcedureContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CONST) {
				{
				setState(49);
				consts();
				}
			}

			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VAR) {
				{
				setState(52);
				vars();
				}
			}

			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PROCEDURE) {
				{
				{
				setState(55);
				procedure();
				}
				}
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(61);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstsContext extends ParserRuleContext {
		public TerminalNode CONST() { return getToken(pl0Parser.CONST, 0); }
		public List<IdentContext> ident() {
			return getRuleContexts(IdentContext.class);
		}
		public IdentContext ident(int i) {
			return getRuleContext(IdentContext.class,i);
		}
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public ConstsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_consts; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).enterConsts(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).exitConsts(this);
		}
	}

	public final ConstsContext consts() throws RecognitionException {
		ConstsContext _localctx = new ConstsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_consts);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			match(CONST);
			setState(64);
			ident();
			setState(65);
			match(T__1);
			setState(66);
			number();
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(67);
				match(T__2);
				setState(68);
				ident();
				setState(69);
				match(T__1);
				setState(70);
				number();
				}
				}
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(77);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarsContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(pl0Parser.VAR, 0); }
		public List<IdentContext> ident() {
			return getRuleContexts(IdentContext.class);
		}
		public IdentContext ident(int i) {
			return getRuleContext(IdentContext.class,i);
		}
		public VarsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vars; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).enterVars(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).exitVars(this);
		}
	}

	public final VarsContext vars() throws RecognitionException {
		VarsContext _localctx = new VarsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_vars);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(VAR);
			setState(80);
			ident();
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(81);
				match(T__2);
				setState(82);
				ident();
				}
				}
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(88);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProcedureContext extends ParserRuleContext {
		public TerminalNode PROCEDURE() { return getToken(pl0Parser.PROCEDURE, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ProcedureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procedure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).enterProcedure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).exitProcedure(this);
		}
	}

	public final ProcedureContext procedure() throws RecognitionException {
		ProcedureContext _localctx = new ProcedureContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_procedure);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(PROCEDURE);
			setState(91);
			ident();
			setState(92);
			match(T__3);
			setState(93);
			block();
			setState(94);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public AssignstmtContext assignstmt() {
			return getRuleContext(AssignstmtContext.class,0);
		}
		public CallstmtContext callstmt() {
			return getRuleContext(CallstmtContext.class,0);
		}
		public WritestmtContext writestmt() {
			return getRuleContext(WritestmtContext.class,0);
		}
		public QstmtContext qstmt() {
			return getRuleContext(QstmtContext.class,0);
		}
		public ExecstmtContext execstmt() {
			return getRuleContext(ExecstmtContext.class,0);
		}
		public BeginstmtContext beginstmt() {
			return getRuleContext(BeginstmtContext.class,0);
		}
		public IfstmtContext ifstmt() {
			return getRuleContext(IfstmtContext.class,0);
		}
		public WhilestmtContext whilestmt() {
			return getRuleContext(WhilestmtContext.class,0);
		}
		public GotostmtContext gotostmt() {
			return getRuleContext(GotostmtContext.class,0);
		}
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				{
				setState(96);
				assignstmt();
				}
				break;
			case CALL:
				{
				setState(97);
				callstmt();
				}
				break;
			case WRITE:
				{
				setState(98);
				writestmt();
				}
				break;
			case READ:
				{
				setState(99);
				qstmt();
				}
				break;
			case EXECUTE:
				{
				setState(100);
				execstmt();
				}
				break;
			case BEGIN:
				{
				setState(101);
				beginstmt();
				}
				break;
			case IF:
				{
				setState(102);
				ifstmt();
				}
				break;
			case WHILE:
				{
				setState(103);
				whilestmt();
				}
				break;
			case GOTO:
				{
				setState(104);
				gotostmt();
				}
				break;
			case LABEL:
				{
				setState(105);
				label();
				}
				break;
			case T__0:
			case T__3:
			case ELSE:
			case END:
				break;
			default:
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GotostmtContext extends ParserRuleContext {
		public TerminalNode GOTO() { return getToken(pl0Parser.GOTO, 0); }
		public TerminalNode STRING() { return getToken(pl0Parser.STRING, 0); }
		public GotostmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gotostmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).enterGotostmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).exitGotostmt(this);
		}
	}

	public final GotostmtContext gotostmt() throws RecognitionException {
		GotostmtContext _localctx = new GotostmtContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_gotostmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(GOTO);
			setState(109);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LabelContext extends ParserRuleContext {
		public TerminalNode LABEL() { return getToken(pl0Parser.LABEL, 0); }
		public TerminalNode STRING() { return getToken(pl0Parser.STRING, 0); }
		public LabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_label; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).enterLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).exitLabel(this);
		}
	}

	public final LabelContext label() throws RecognitionException {
		LabelContext _localctx = new LabelContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			match(LABEL);
			setState(112);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignstmtContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignstmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignstmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).enterAssignstmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).exitAssignstmt(this);
		}
	}

	public final AssignstmtContext assignstmt() throws RecognitionException {
		AssignstmtContext _localctx = new AssignstmtContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_assignstmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			ident();
			setState(115);
			match(T__4);
			setState(116);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CallstmtContext extends ParserRuleContext {
		public TerminalNode CALL() { return getToken(pl0Parser.CALL, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public CallstmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callstmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).enterCallstmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).exitCallstmt(this);
		}
	}

	public final CallstmtContext callstmt() throws RecognitionException {
		CallstmtContext _localctx = new CallstmtContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_callstmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			match(CALL);
			setState(119);
			ident();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WritestmtContext extends ParserRuleContext {
		public TerminalNode WRITE() { return getToken(pl0Parser.WRITE, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public WritestmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_writestmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).enterWritestmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).exitWritestmt(this);
		}
	}

	public final WritestmtContext writestmt() throws RecognitionException {
		WritestmtContext _localctx = new WritestmtContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_writestmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			match(WRITE);
			setState(122);
			ident();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QstmtContext extends ParserRuleContext {
		public TerminalNode READ() { return getToken(pl0Parser.READ, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public QstmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qstmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).enterQstmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).exitQstmt(this);
		}
	}

	public final QstmtContext qstmt() throws RecognitionException {
		QstmtContext _localctx = new QstmtContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_qstmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(READ);
			setState(125);
			ident();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExecstmtContext extends ParserRuleContext {
		public TerminalNode EXECUTE() { return getToken(pl0Parser.EXECUTE, 0); }
		public TerminalNode NUMBER() { return getToken(pl0Parser.NUMBER, 0); }
		public ExecstmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_execstmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).enterExecstmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).exitExecstmt(this);
		}
	}

	public final ExecstmtContext execstmt() throws RecognitionException {
		ExecstmtContext _localctx = new ExecstmtContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_execstmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(EXECUTE);
			setState(128);
			match(NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BeginstmtContext extends ParserRuleContext {
		public TerminalNode BEGIN() { return getToken(pl0Parser.BEGIN, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode END() { return getToken(pl0Parser.END, 0); }
		public BeginstmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_beginstmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).enterBeginstmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).exitBeginstmt(this);
		}
	}

	public final BeginstmtContext beginstmt() throws RecognitionException {
		BeginstmtContext _localctx = new BeginstmtContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_beginstmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(BEGIN);
			setState(131);
			statement();
			setState(136);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(132);
				match(T__3);
				setState(133);
				statement();
				}
				}
				setState(138);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(139);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfstmtContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(pl0Parser.IF, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode THEN() { return getToken(pl0Parser.THEN, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ElsebranchContext elsebranch() {
			return getRuleContext(ElsebranchContext.class,0);
		}
		public IfstmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifstmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).enterIfstmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).exitIfstmt(this);
		}
	}

	public final IfstmtContext ifstmt() throws RecognitionException {
		IfstmtContext _localctx = new IfstmtContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_ifstmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			match(IF);
			setState(142);
			condition();
			setState(143);
			match(THEN);
			setState(144);
			statement();
			setState(145);
			elsebranch();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElsebranchContext extends ParserRuleContext {
		public TerminalNode ELSE() { return getToken(pl0Parser.ELSE, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ElsebranchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elsebranch; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).enterElsebranch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).exitElsebranch(this);
		}
	}

	public final ElsebranchContext elsebranch() throws RecognitionException {
		ElsebranchContext _localctx = new ElsebranchContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_elsebranch);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(147);
				match(ELSE);
				setState(148);
				statement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhilestmtContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(pl0Parser.WHILE, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode DO() { return getToken(pl0Parser.DO, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhilestmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whilestmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).enterWhilestmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).exitWhilestmt(this);
		}
	}

	public final WhilestmtContext whilestmt() throws RecognitionException {
		WhilestmtContext _localctx = new WhilestmtContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_whilestmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(WHILE);
			setState(152);
			condition();
			setState(153);
			match(DO);
			setState(154);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionContext extends ParserRuleContext {
		public TerminalNode ODD() { return getToken(pl0Parser.ODD, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).exitCondition(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_condition);
		int _la;
		try {
			setState(162);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ODD:
				enterOuterAlt(_localctx, 1);
				{
				setState(156);
				match(ODD);
				setState(157);
				expression(0);
				}
				break;
			case T__10:
			case T__11:
			case T__14:
			case STRING:
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(158);
				expression(0);
				setState(159);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(160);
				expression(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 36;
		enterRecursionRule(_localctx, 36, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(166);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10 || _la==T__11) {
				{
				setState(165);
				_la = _input.LA(1);
				if ( !(_la==T__10 || _la==T__11) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(168);
			term(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(175);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExpressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expression);
					setState(170);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(171);
					_la = _input.LA(1);
					if ( !(_la==T__10 || _la==T__11) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(172);
					term(0);
					}
					} 
				}
				setState(177);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).exitTerm(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		return term(0);
	}

	private TermContext term(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TermContext _localctx = new TermContext(_ctx, _parentState);
		TermContext _prevctx = _localctx;
		int _startState = 38;
		enterRecursionRule(_localctx, 38, RULE_term, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(179);
			factor();
			}
			_ctx.stop = _input.LT(-1);
			setState(186);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TermContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_term);
					setState(181);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(182);
					_la = _input.LA(1);
					if ( !(_la==T__12 || _la==T__13) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(183);
					factor();
					}
					} 
				}
				setState(188);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class FactorContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).exitFactor(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_factor);
		try {
			setState(195);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(189);
				ident();
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(190);
				number();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 3);
				{
				setState(191);
				match(T__14);
				setState(192);
				expression(0);
				setState(193);
				match(T__15);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(pl0Parser.STRING, 0); }
		public IdentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ident; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).enterIdent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).exitIdent(this);
		}
	}

	public final IdentContext ident() throws RecognitionException {
		IdentContext _localctx = new IdentContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_ident);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(pl0Parser.NUMBER, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pl0Listener ) ((pl0Listener)listener).exitNumber(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			match(NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 18:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		case 19:
			return term_sempred((TermContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean term_sempred(TermContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\'\u00cc\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2\3\2\3"+
		"\2\3\3\5\3\65\n\3\3\3\5\38\n\3\3\3\7\3;\n\3\f\3\16\3>\13\3\3\3\3\3\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4K\n\4\f\4\16\4N\13\4\3\4\3\4\3\5\3"+
		"\5\3\5\3\5\7\5V\n\5\f\5\16\5Y\13\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7m\n\7\3\b\3\b\3\b\3\t\3\t\3\t"+
		"\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16"+
		"\3\17\3\17\3\17\3\17\7\17\u0089\n\17\f\17\16\17\u008c\13\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\5\21\u0098\n\21\3\22\3\22\3\22"+
		"\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u00a5\n\23\3\24\3\24\5\24"+
		"\u00a9\n\24\3\24\3\24\3\24\3\24\3\24\7\24\u00b0\n\24\f\24\16\24\u00b3"+
		"\13\24\3\25\3\25\3\25\3\25\3\25\3\25\7\25\u00bb\n\25\f\25\16\25\u00be"+
		"\13\25\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u00c6\n\26\3\27\3\27\3\30\3"+
		"\30\3\30\2\4&(\31\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\2\5"+
		"\4\2\4\4\b\f\3\2\r\16\3\2\17\20\2\u00cb\2\60\3\2\2\2\4\64\3\2\2\2\6A\3"+
		"\2\2\2\bQ\3\2\2\2\n\\\3\2\2\2\fl\3\2\2\2\16n\3\2\2\2\20q\3\2\2\2\22t\3"+
		"\2\2\2\24x\3\2\2\2\26{\3\2\2\2\30~\3\2\2\2\32\u0081\3\2\2\2\34\u0084\3"+
		"\2\2\2\36\u008f\3\2\2\2 \u0097\3\2\2\2\"\u0099\3\2\2\2$\u00a4\3\2\2\2"+
		"&\u00a6\3\2\2\2(\u00b4\3\2\2\2*\u00c5\3\2\2\2,\u00c7\3\2\2\2.\u00c9\3"+
		"\2\2\2\60\61\5\4\3\2\61\62\7\3\2\2\62\3\3\2\2\2\63\65\5\6\4\2\64\63\3"+
		"\2\2\2\64\65\3\2\2\2\65\67\3\2\2\2\668\5\b\5\2\67\66\3\2\2\2\678\3\2\2"+
		"\28<\3\2\2\29;\5\n\6\2:9\3\2\2\2;>\3\2\2\2<:\3\2\2\2<=\3\2\2\2=?\3\2\2"+
		"\2><\3\2\2\2?@\5\f\7\2@\5\3\2\2\2AB\7\36\2\2BC\5,\27\2CD\7\4\2\2DL\5."+
		"\30\2EF\7\5\2\2FG\5,\27\2GH\7\4\2\2HI\5.\30\2IK\3\2\2\2JE\3\2\2\2KN\3"+
		"\2\2\2LJ\3\2\2\2LM\3\2\2\2MO\3\2\2\2NL\3\2\2\2OP\7\6\2\2P\7\3\2\2\2QR"+
		"\7\37\2\2RW\5,\27\2ST\7\5\2\2TV\5,\27\2US\3\2\2\2VY\3\2\2\2WU\3\2\2\2"+
		"WX\3\2\2\2XZ\3\2\2\2YW\3\2\2\2Z[\7\6\2\2[\t\3\2\2\2\\]\7 \2\2]^\5,\27"+
		"\2^_\7\6\2\2_`\5\4\3\2`a\7\6\2\2a\13\3\2\2\2bm\5\22\n\2cm\5\24\13\2dm"+
		"\5\26\f\2em\5\30\r\2fm\5\32\16\2gm\5\34\17\2hm\5\36\20\2im\5\"\22\2jm"+
		"\5\16\b\2km\5\20\t\2lb\3\2\2\2lc\3\2\2\2ld\3\2\2\2le\3\2\2\2lf\3\2\2\2"+
		"lg\3\2\2\2lh\3\2\2\2li\3\2\2\2lj\3\2\2\2lk\3\2\2\2lm\3\2\2\2m\r\3\2\2"+
		"\2no\7\"\2\2op\7$\2\2p\17\3\2\2\2qr\7#\2\2rs\7$\2\2s\21\3\2\2\2tu\5,\27"+
		"\2uv\7\7\2\2vw\5&\24\2w\23\3\2\2\2xy\7\35\2\2yz\5,\27\2z\25\3\2\2\2{|"+
		"\7\23\2\2|}\5,\27\2}\27\3\2\2\2~\177\7\24\2\2\177\u0080\5,\27\2\u0080"+
		"\31\3\2\2\2\u0081\u0082\7!\2\2\u0082\u0083\7%\2\2\u0083\33\3\2\2\2\u0084"+
		"\u0085\7\33\2\2\u0085\u008a\5\f\7\2\u0086\u0087\7\6\2\2\u0087\u0089\5"+
		"\f\7\2\u0088\u0086\3\2\2\2\u0089\u008c\3\2\2\2\u008a\u0088\3\2\2\2\u008a"+
		"\u008b\3\2\2\2\u008b\u008d\3\2\2\2\u008c\u008a\3\2\2\2\u008d\u008e\7\34"+
		"\2\2\u008e\35\3\2\2\2\u008f\u0090\7\27\2\2\u0090\u0091\5$\23\2\u0091\u0092"+
		"\7\30\2\2\u0092\u0093\5\f\7\2\u0093\u0094\5 \21\2\u0094\37\3\2\2\2\u0095"+
		"\u0096\7\31\2\2\u0096\u0098\5\f\7\2\u0097\u0095\3\2\2\2\u0097\u0098\3"+
		"\2\2\2\u0098!\3\2\2\2\u0099\u009a\7\25\2\2\u009a\u009b\5$\23\2\u009b\u009c"+
		"\7\26\2\2\u009c\u009d\5\f\7\2\u009d#\3\2\2\2\u009e\u009f\7\32\2\2\u009f"+
		"\u00a5\5&\24\2\u00a0\u00a1\5&\24\2\u00a1\u00a2\t\2\2\2\u00a2\u00a3\5&"+
		"\24\2\u00a3\u00a5\3\2\2\2\u00a4\u009e\3\2\2\2\u00a4\u00a0\3\2\2\2\u00a5"+
		"%\3\2\2\2\u00a6\u00a8\b\24\1\2\u00a7\u00a9\t\3\2\2\u00a8\u00a7\3\2\2\2"+
		"\u00a8\u00a9\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ab\5(\25\2\u00ab\u00b1"+
		"\3\2\2\2\u00ac\u00ad\f\4\2\2\u00ad\u00ae\t\3\2\2\u00ae\u00b0\5(\25\2\u00af"+
		"\u00ac\3\2\2\2\u00b0\u00b3\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b2\3\2"+
		"\2\2\u00b2\'\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4\u00b5\b\25\1\2\u00b5\u00b6"+
		"\5*\26\2\u00b6\u00bc\3\2\2\2\u00b7\u00b8\f\4\2\2\u00b8\u00b9\t\4\2\2\u00b9"+
		"\u00bb\5*\26\2\u00ba\u00b7\3\2\2\2\u00bb\u00be\3\2\2\2\u00bc\u00ba\3\2"+
		"\2\2\u00bc\u00bd\3\2\2\2\u00bd)\3\2\2\2\u00be\u00bc\3\2\2\2\u00bf\u00c6"+
		"\5,\27\2\u00c0\u00c6\5.\30\2\u00c1\u00c2\7\21\2\2\u00c2\u00c3\5&\24\2"+
		"\u00c3\u00c4\7\22\2\2\u00c4\u00c6\3\2\2\2\u00c5\u00bf\3\2\2\2\u00c5\u00c0"+
		"\3\2\2\2\u00c5\u00c1\3\2\2\2\u00c6+\3\2\2\2\u00c7\u00c8\7$\2\2\u00c8-"+
		"\3\2\2\2\u00c9\u00ca\7%\2\2\u00ca/\3\2\2\2\17\64\67<LWl\u008a\u0097\u00a4"+
		"\u00a8\u00b1\u00bc\u00c5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}