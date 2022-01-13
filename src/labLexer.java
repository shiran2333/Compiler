// Generated from .\lab.g4 by ANTLR 4.9
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class labLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, DecimalConst=11, OctalConst=12, HexadecimalConst=13, HexadecimalPrefix=14, 
		Digit=15, NonzeroDigit=16, OctalDigit=17, HexadecimalDigit=18, Whitespace=19, 
		Newline=20, BlockComment=21, LineComment=22;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "DecimalConst", "DC", "OctalConst", "OC", "HexadecimalConst", 
			"HC", "HexadecimalPrefix", "Digit", "NonzeroDigit", "OctalDigit", "HexadecimalDigit", 
			"Whitespace", "Newline", "BlockComment", "LineComment"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'int'", "'main'", "'{'", "'}'", "'return'", "';'", 
			"'+'", "'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "DecimalConst", 
			"OctalConst", "HexadecimalConst", "HexadecimalPrefix", "Digit", "NonzeroDigit", 
			"OctalDigit", "HexadecimalDigit", "Whitespace", "Newline", "BlockComment", 
			"LineComment"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	public labLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "lab.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\30\u00a3\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3"+
		"\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3"+
		"\f\3\f\3\f\3\r\3\r\3\r\5\rZ\n\r\3\16\3\16\3\16\3\17\3\17\3\17\5\17b\n"+
		"\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\5\21k\n\21\3\22\3\22\3\22\3\22"+
		"\5\22q\n\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\6\27|\n\27\r"+
		"\27\16\27}\3\27\3\27\3\30\3\30\5\30\u0084\n\30\3\30\5\30\u0087\n\30\3"+
		"\30\3\30\3\31\3\31\3\31\3\31\7\31\u008f\n\31\f\31\16\31\u0092\13\31\3"+
		"\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\7\32\u009d\n\32\f\32\16\32"+
		"\u00a0\13\32\3\32\3\32\3\u0090\2\33\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n"+
		"\23\13\25\f\27\r\31\2\33\16\35\2\37\17!\2#\20%\21\'\22)\23+\24-\25/\26"+
		"\61\27\63\30\3\2\5\5\2\62;CHch\4\2\13\13\"\"\4\2\f\f\17\17\2\u00a8\2\3"+
		"\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2"+
		"\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\33"+
		"\3\2\2\2\2\37\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+"+
		"\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\3\65\3\2\2\2"+
		"\5\67\3\2\2\2\79\3\2\2\2\t=\3\2\2\2\13B\3\2\2\2\rD\3\2\2\2\17F\3\2\2\2"+
		"\21M\3\2\2\2\23O\3\2\2\2\25Q\3\2\2\2\27S\3\2\2\2\31Y\3\2\2\2\33[\3\2\2"+
		"\2\35a\3\2\2\2\37c\3\2\2\2!j\3\2\2\2#p\3\2\2\2%r\3\2\2\2\'t\3\2\2\2)v"+
		"\3\2\2\2+x\3\2\2\2-{\3\2\2\2/\u0086\3\2\2\2\61\u008a\3\2\2\2\63\u0098"+
		"\3\2\2\2\65\66\7*\2\2\66\4\3\2\2\2\678\7+\2\28\6\3\2\2\29:\7k\2\2:;\7"+
		"p\2\2;<\7v\2\2<\b\3\2\2\2=>\7o\2\2>?\7c\2\2?@\7k\2\2@A\7p\2\2A\n\3\2\2"+
		"\2BC\7}\2\2C\f\3\2\2\2DE\7\177\2\2E\16\3\2\2\2FG\7t\2\2GH\7g\2\2HI\7v"+
		"\2\2IJ\7w\2\2JK\7t\2\2KL\7p\2\2L\20\3\2\2\2MN\7=\2\2N\22\3\2\2\2OP\7-"+
		"\2\2P\24\3\2\2\2QR\7/\2\2R\26\3\2\2\2ST\5\'\24\2TU\5\31\r\2U\30\3\2\2"+
		"\2VW\5%\23\2WX\5\31\r\2XZ\3\2\2\2YV\3\2\2\2YZ\3\2\2\2Z\32\3\2\2\2[\\\7"+
		"\62\2\2\\]\5\35\17\2]\34\3\2\2\2^_\5)\25\2_`\5\35\17\2`b\3\2\2\2a^\3\2"+
		"\2\2ab\3\2\2\2b\36\3\2\2\2cd\5#\22\2de\5+\26\2ef\5!\21\2f \3\2\2\2gh\5"+
		"+\26\2hi\5!\21\2ik\3\2\2\2jg\3\2\2\2jk\3\2\2\2k\"\3\2\2\2lm\7\62\2\2m"+
		"q\7z\2\2no\7\62\2\2oq\7Z\2\2pl\3\2\2\2pn\3\2\2\2q$\3\2\2\2rs\4\62;\2s"+
		"&\3\2\2\2tu\4\63;\2u(\3\2\2\2vw\4\629\2w*\3\2\2\2xy\t\2\2\2y,\3\2\2\2"+
		"z|\t\3\2\2{z\3\2\2\2|}\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\177\3\2\2\2\177\u0080"+
		"\b\27\2\2\u0080.\3\2\2\2\u0081\u0083\7\17\2\2\u0082\u0084\7\f\2\2\u0083"+
		"\u0082\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0087\3\2\2\2\u0085\u0087\7\f"+
		"\2\2\u0086\u0081\3\2\2\2\u0086\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088"+
		"\u0089\b\30\2\2\u0089\60\3\2\2\2\u008a\u008b\7\61\2\2\u008b\u008c\7,\2"+
		"\2\u008c\u0090\3\2\2\2\u008d\u008f\13\2\2\2\u008e\u008d\3\2\2\2\u008f"+
		"\u0092\3\2\2\2\u0090\u0091\3\2\2\2\u0090\u008e\3\2\2\2\u0091\u0093\3\2"+
		"\2\2\u0092\u0090\3\2\2\2\u0093\u0094\7,\2\2\u0094\u0095\7\61\2\2\u0095"+
		"\u0096\3\2\2\2\u0096\u0097\b\31\2\2\u0097\62\3\2\2\2\u0098\u0099\7\61"+
		"\2\2\u0099\u009a\7\61\2\2\u009a\u009e\3\2\2\2\u009b\u009d\n\4\2\2\u009c"+
		"\u009b\3\2\2\2\u009d\u00a0\3\2\2\2\u009e\u009c\3\2\2\2\u009e\u009f\3\2"+
		"\2\2\u009f\u00a1\3\2\2\2\u00a0\u009e\3\2\2\2\u00a1\u00a2\b\32\2\2\u00a2"+
		"\64\3\2\2\2\f\2Yajp}\u0083\u0086\u0090\u009e\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}