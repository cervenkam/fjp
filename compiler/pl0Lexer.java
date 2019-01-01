// Generated from compiler/pl0.g4 by ANTLR 4.7.1
package compiler;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class pl0Lexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "WRITE", 
		"READ", "WHILE", "DO", "IF", "THEN", "ELSE", "ODD", "BEGIN", "END", "CALL", 
		"CONST", "VAR", "PROCEDURE", "EXECUTE", "GOTO", "LABEL", "A", "B", "C", 
		"D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", 
		"R", "S", "T", "U", "V", "W", "X", "Y", "Z", "STRING", "NUMBER", "WS", 
		"LINE_COMMENT"
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


	public pl0Lexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "pl0.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\'\u0164\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\7\3\7"+
		"\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16"+
		"\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23"+
		"\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26"+
		"\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31"+
		"\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\34\3\34\3\34"+
		"\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\37\3\37"+
		"\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3!\3"+
		"!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3("+
		"\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62"+
		"\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\38\38\39\39\3"+
		":\3:\3;\3;\3<\3<\3=\6=\u0135\n=\r=\16=\u0136\3>\3>\3>\3>\6>\u013d\n>\r"+
		">\16>\u013e\3>\3>\3>\3>\6>\u0145\n>\r>\16>\u0146\3>\3>\6>\u014b\n>\r>"+
		"\16>\u014c\3>\6>\u0150\n>\r>\16>\u0151\5>\u0154\n>\3?\3?\3?\3?\3@\3@\3"+
		"@\3@\7@\u015e\n@\f@\16@\u0161\13@\3@\3@\2\2A\3\3\5\4\7\5\t\6\13\7\r\b"+
		"\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26"+
		"+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E\2G\2I\2K\2M\2O"+
		"\2Q\2S\2U\2W\2Y\2[\2]\2_\2a\2c\2e\2g\2i\2k\2m\2o\2q\2s\2u\2w\2y${%}&\177"+
		"\'\3\2#\4\2CCcc\4\2DDdd\4\2EEee\4\2FFff\4\2GGgg\4\2HHhh\4\2IIii\4\2JJ"+
		"jj\4\2KKkk\4\2LLll\4\2MMmm\4\2NNnn\4\2OOoo\4\2PPpp\4\2QQqq\4\2RRrr\4\2"+
		"SSss\4\2TTtt\4\2UUuu\4\2VVvv\4\2WWww\4\2XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4"+
		"\2\\\\||\5\2C\\aac|\5\2\62;CHch\3\2\62\63\3\2\629\3\2\62;\5\2\13\f\17"+
		"\17\"\"\4\2\f\f\17\17\2\u0152\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t"+
		"\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2"+
		"\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2"+
		"\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2"+
		"+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2"+
		"\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2"+
		"C\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\3\u0081\3\2\2"+
		"\2\5\u0083\3\2\2\2\7\u0085\3\2\2\2\t\u0087\3\2\2\2\13\u0089\3\2\2\2\r"+
		"\u008c\3\2\2\2\17\u008e\3\2\2\2\21\u0090\3\2\2\2\23\u0093\3\2\2\2\25\u0095"+
		"\3\2\2\2\27\u0098\3\2\2\2\31\u009a\3\2\2\2\33\u009c\3\2\2\2\35\u009e\3"+
		"\2\2\2\37\u00a0\3\2\2\2!\u00a2\3\2\2\2#\u00a4\3\2\2\2%\u00aa\3\2\2\2\'"+
		"\u00af\3\2\2\2)\u00b5\3\2\2\2+\u00b8\3\2\2\2-\u00bb\3\2\2\2/\u00c0\3\2"+
		"\2\2\61\u00c5\3\2\2\2\63\u00c9\3\2\2\2\65\u00cf\3\2\2\2\67\u00d3\3\2\2"+
		"\29\u00d8\3\2\2\2;\u00de\3\2\2\2=\u00e2\3\2\2\2?\u00ec\3\2\2\2A\u00f4"+
		"\3\2\2\2C\u00f9\3\2\2\2E\u00ff\3\2\2\2G\u0101\3\2\2\2I\u0103\3\2\2\2K"+
		"\u0105\3\2\2\2M\u0107\3\2\2\2O\u0109\3\2\2\2Q\u010b\3\2\2\2S\u010d\3\2"+
		"\2\2U\u010f\3\2\2\2W\u0111\3\2\2\2Y\u0113\3\2\2\2[\u0115\3\2\2\2]\u0117"+
		"\3\2\2\2_\u0119\3\2\2\2a\u011b\3\2\2\2c\u011d\3\2\2\2e\u011f\3\2\2\2g"+
		"\u0121\3\2\2\2i\u0123\3\2\2\2k\u0125\3\2\2\2m\u0127\3\2\2\2o\u0129\3\2"+
		"\2\2q\u012b\3\2\2\2s\u012d\3\2\2\2u\u012f\3\2\2\2w\u0131\3\2\2\2y\u0134"+
		"\3\2\2\2{\u0153\3\2\2\2}\u0155\3\2\2\2\177\u0159\3\2\2\2\u0081\u0082\7"+
		"\60\2\2\u0082\4\3\2\2\2\u0083\u0084\7?\2\2\u0084\6\3\2\2\2\u0085\u0086"+
		"\7.\2\2\u0086\b\3\2\2\2\u0087\u0088\7=\2\2\u0088\n\3\2\2\2\u0089\u008a"+
		"\7<\2\2\u008a\u008b\7?\2\2\u008b\f\3\2\2\2\u008c\u008d\7%\2\2\u008d\16"+
		"\3\2\2\2\u008e\u008f\7>\2\2\u008f\20\3\2\2\2\u0090\u0091\7>\2\2\u0091"+
		"\u0092\7?\2\2\u0092\22\3\2\2\2\u0093\u0094\7@\2\2\u0094\24\3\2\2\2\u0095"+
		"\u0096\7@\2\2\u0096\u0097\7?\2\2\u0097\26\3\2\2\2\u0098\u0099\7-\2\2\u0099"+
		"\30\3\2\2\2\u009a\u009b\7/\2\2\u009b\32\3\2\2\2\u009c\u009d\7,\2\2\u009d"+
		"\34\3\2\2\2\u009e\u009f\7\61\2\2\u009f\36\3\2\2\2\u00a0\u00a1\7*\2\2\u00a1"+
		" \3\2\2\2\u00a2\u00a3\7+\2\2\u00a3\"\3\2\2\2\u00a4\u00a5\5q9\2\u00a5\u00a6"+
		"\5g\64\2\u00a6\u00a7\5U+\2\u00a7\u00a8\5k\66\2\u00a8\u00a9\5M\'\2\u00a9"+
		"$\3\2\2\2\u00aa\u00ab\5g\64\2\u00ab\u00ac\5M\'\2\u00ac\u00ad\5E#\2\u00ad"+
		"\u00ae\5K&\2\u00ae&\3\2\2\2\u00af\u00b0\5q9\2\u00b0\u00b1\5S*\2\u00b1"+
		"\u00b2\5U+\2\u00b2\u00b3\5[.\2\u00b3\u00b4\5M\'\2\u00b4(\3\2\2\2\u00b5"+
		"\u00b6\5K&\2\u00b6\u00b7\5a\61\2\u00b7*\3\2\2\2\u00b8\u00b9\5U+\2\u00b9"+
		"\u00ba\5O(\2\u00ba,\3\2\2\2\u00bb\u00bc\5k\66\2\u00bc\u00bd\5S*\2\u00bd"+
		"\u00be\5M\'\2\u00be\u00bf\5_\60\2\u00bf.\3\2\2\2\u00c0\u00c1\5M\'\2\u00c1"+
		"\u00c2\5[.\2\u00c2\u00c3\5i\65\2\u00c3\u00c4\5M\'\2\u00c4\60\3\2\2\2\u00c5"+
		"\u00c6\5a\61\2\u00c6\u00c7\5K&\2\u00c7\u00c8\5K&\2\u00c8\62\3\2\2\2\u00c9"+
		"\u00ca\5G$\2\u00ca\u00cb\5M\'\2\u00cb\u00cc\5Q)\2\u00cc\u00cd\5U+\2\u00cd"+
		"\u00ce\5_\60\2\u00ce\64\3\2\2\2\u00cf\u00d0\5M\'\2\u00d0\u00d1\5_\60\2"+
		"\u00d1\u00d2\5K&\2\u00d2\66\3\2\2\2\u00d3\u00d4\5I%\2\u00d4\u00d5\5E#"+
		"\2\u00d5\u00d6\5[.\2\u00d6\u00d7\5[.\2\u00d78\3\2\2\2\u00d8\u00d9\5I%"+
		"\2\u00d9\u00da\5a\61\2\u00da\u00db\5_\60\2\u00db\u00dc\5i\65\2\u00dc\u00dd"+
		"\5k\66\2\u00dd:\3\2\2\2\u00de\u00df\5o8\2\u00df\u00e0\5E#\2\u00e0\u00e1"+
		"\5g\64\2\u00e1<\3\2\2\2\u00e2\u00e3\5c\62\2\u00e3\u00e4\5g\64\2\u00e4"+
		"\u00e5\5a\61\2\u00e5\u00e6\5I%\2\u00e6\u00e7\5M\'\2\u00e7\u00e8\5K&\2"+
		"\u00e8\u00e9\5m\67\2\u00e9\u00ea\5g\64\2\u00ea\u00eb\5M\'\2\u00eb>\3\2"+
		"\2\2\u00ec\u00ed\5M\'\2\u00ed\u00ee\5s:\2\u00ee\u00ef\5M\'\2\u00ef\u00f0"+
		"\5I%\2\u00f0\u00f1\5m\67\2\u00f1\u00f2\5k\66\2\u00f2\u00f3\5M\'\2\u00f3"+
		"@\3\2\2\2\u00f4\u00f5\5Q)\2\u00f5\u00f6\5a\61\2\u00f6\u00f7\5k\66\2\u00f7"+
		"\u00f8\5a\61\2\u00f8B\3\2\2\2\u00f9\u00fa\5[.\2\u00fa\u00fb\5E#\2\u00fb"+
		"\u00fc\5G$\2\u00fc\u00fd\5M\'\2\u00fd\u00fe\5[.\2\u00feD\3\2\2\2\u00ff"+
		"\u0100\t\2\2\2\u0100F\3\2\2\2\u0101\u0102\t\3\2\2\u0102H\3\2\2\2\u0103"+
		"\u0104\t\4\2\2\u0104J\3\2\2\2\u0105\u0106\t\5\2\2\u0106L\3\2\2\2\u0107"+
		"\u0108\t\6\2\2\u0108N\3\2\2\2\u0109\u010a\t\7\2\2\u010aP\3\2\2\2\u010b"+
		"\u010c\t\b\2\2\u010cR\3\2\2\2\u010d\u010e\t\t\2\2\u010eT\3\2\2\2\u010f"+
		"\u0110\t\n\2\2\u0110V\3\2\2\2\u0111\u0112\t\13\2\2\u0112X\3\2\2\2\u0113"+
		"\u0114\t\f\2\2\u0114Z\3\2\2\2\u0115\u0116\t\r\2\2\u0116\\\3\2\2\2\u0117"+
		"\u0118\t\16\2\2\u0118^\3\2\2\2\u0119\u011a\t\17\2\2\u011a`\3\2\2\2\u011b"+
		"\u011c\t\20\2\2\u011cb\3\2\2\2\u011d\u011e\t\21\2\2\u011ed\3\2\2\2\u011f"+
		"\u0120\t\22\2\2\u0120f\3\2\2\2\u0121\u0122\t\23\2\2\u0122h\3\2\2\2\u0123"+
		"\u0124\t\24\2\2\u0124j\3\2\2\2\u0125\u0126\t\25\2\2\u0126l\3\2\2\2\u0127"+
		"\u0128\t\26\2\2\u0128n\3\2\2\2\u0129\u012a\t\27\2\2\u012ap\3\2\2\2\u012b"+
		"\u012c\t\30\2\2\u012cr\3\2\2\2\u012d\u012e\t\31\2\2\u012et\3\2\2\2\u012f"+
		"\u0130\t\32\2\2\u0130v\3\2\2\2\u0131\u0132\t\33\2\2\u0132x\3\2\2\2\u0133"+
		"\u0135\t\34\2\2\u0134\u0133\3\2\2\2\u0135\u0136\3\2\2\2\u0136\u0134\3"+
		"\2\2\2\u0136\u0137\3\2\2\2\u0137z\3\2\2\2\u0138\u0139\7\62\2\2\u0139\u013a"+
		"\7z\2\2\u013a\u013c\3\2\2\2\u013b\u013d\t\35\2\2\u013c\u013b\3\2\2\2\u013d"+
		"\u013e\3\2\2\2\u013e\u013c\3\2\2\2\u013e\u013f\3\2\2\2\u013f\u0154\3\2"+
		"\2\2\u0140\u0141\7\62\2\2\u0141\u0142\7d\2\2\u0142\u0144\3\2\2\2\u0143"+
		"\u0145\t\36\2\2\u0144\u0143\3\2\2\2\u0145\u0146\3\2\2\2\u0146\u0144\3"+
		"\2\2\2\u0146\u0147\3\2\2\2\u0147\u0154\3\2\2\2\u0148\u014a\7\62\2\2\u0149"+
		"\u014b\t\37\2\2\u014a\u0149\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u014a\3"+
		"\2\2\2\u014c\u014d\3\2\2\2\u014d\u0154\3\2\2\2\u014e\u0150\t \2\2\u014f"+
		"\u014e\3\2\2\2\u0150\u0151\3\2\2\2\u0151\u014f\3\2\2\2\u0151\u0152\3\2"+
		"\2\2\u0152\u0154\3\2\2\2\u0153\u0138\3\2\2\2\u0153\u0140\3\2\2\2\u0153"+
		"\u0148\3\2\2\2\u0153\u014f\3\2\2\2\u0154|\3\2\2\2\u0155\u0156\t!\2\2\u0156"+
		"\u0157\3\2\2\2\u0157\u0158\b?\2\2\u0158~\3\2\2\2\u0159\u015a\7\61\2\2"+
		"\u015a\u015b\7\61\2\2\u015b\u015f\3\2\2\2\u015c\u015e\n\"\2\2\u015d\u015c"+
		"\3\2\2\2\u015e\u0161\3\2\2\2\u015f\u015d\3\2\2\2\u015f\u0160\3\2\2\2\u0160"+
		"\u0162\3\2\2\2\u0161\u015f\3\2\2\2\u0162\u0163\b@\3\2\u0163\u0080\3\2"+
		"\2\2\n\2\u0136\u013e\u0146\u014c\u0151\u0153\u015f\4\2\3\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}