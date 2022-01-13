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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, DecimalConst=9, 
		OctalConst=10, HexadecimalConst=11, HexadecimalPrefix=12, Digit=13, NonzeroDigit=14, 
		OctalDigit=15, HexadecimalDigit=16, ADD=17, SUB=18, MUL=19, DIV=20, MOD=21, 
		Whitespace=22, Newline=23, BlockComment=24, LineComment=25;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "DecimalConst", 
			"DC", "OctalConst", "OC", "HexadecimalConst", "HC", "HexadecimalPrefix", 
			"Digit", "NonzeroDigit", "OctalDigit", "HexadecimalDigit", "ADD", "SUB", 
			"MUL", "DIV", "MOD", "Whitespace", "Newline", "BlockComment", "LineComment"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'int'", "'main'", "'{'", "'}'", "'return'", "';'", 
			null, null, null, null, null, null, null, null, "'+'", "'-'", "'*'", 
			"'/'", "'%'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "DecimalConst", 
			"OctalConst", "HexadecimalConst", "HexadecimalPrefix", "Digit", "NonzeroDigit", 
			"OctalDigit", "HexadecimalDigit", "ADD", "SUB", "MUL", "DIV", "MOD", 
			"Whitespace", "Newline", "BlockComment", "LineComment"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\33\u00af\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2\3\2\3\3\3\3\3\4\3\4"+
		"\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\5\13\\\n\13\3\f\3\f\3\f\3\r\3\r"+
		"\3\r\5\rd\n\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\5\17m\n\17\3\20\3\20"+
		"\3\20\3\20\5\20s\n\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25"+
		"\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\6\32\u0088\n\32\r\32\16"+
		"\32\u0089\3\32\3\32\3\33\3\33\5\33\u0090\n\33\3\33\5\33\u0093\n\33\3\33"+
		"\3\33\3\34\3\34\3\34\3\34\7\34\u009b\n\34\f\34\16\34\u009e\13\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\7\35\u00a9\n\35\f\35\16\35\u00ac"+
		"\13\35\3\35\3\35\3\u009c\2\36\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13"+
		"\25\2\27\f\31\2\33\r\35\2\37\16!\17#\20%\21\'\22)\23+\24-\25/\26\61\27"+
		"\63\30\65\31\67\329\33\3\2\5\5\2\62;CHch\4\2\13\13\"\"\4\2\f\f\17\17\2"+
		"\u00b4\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2"+
		"\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\27\3\2\2\2\2\33\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3"+
		"\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65"+
		"\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\3;\3\2\2\2\5=\3\2\2\2\7?\3\2\2\2\tC\3"+
		"\2\2\2\13H\3\2\2\2\rJ\3\2\2\2\17L\3\2\2\2\21S\3\2\2\2\23U\3\2\2\2\25["+
		"\3\2\2\2\27]\3\2\2\2\31c\3\2\2\2\33e\3\2\2\2\35l\3\2\2\2\37r\3\2\2\2!"+
		"t\3\2\2\2#v\3\2\2\2%x\3\2\2\2\'z\3\2\2\2)|\3\2\2\2+~\3\2\2\2-\u0080\3"+
		"\2\2\2/\u0082\3\2\2\2\61\u0084\3\2\2\2\63\u0087\3\2\2\2\65\u0092\3\2\2"+
		"\2\67\u0096\3\2\2\29\u00a4\3\2\2\2;<\7*\2\2<\4\3\2\2\2=>\7+\2\2>\6\3\2"+
		"\2\2?@\7k\2\2@A\7p\2\2AB\7v\2\2B\b\3\2\2\2CD\7o\2\2DE\7c\2\2EF\7k\2\2"+
		"FG\7p\2\2G\n\3\2\2\2HI\7}\2\2I\f\3\2\2\2JK\7\177\2\2K\16\3\2\2\2LM\7t"+
		"\2\2MN\7g\2\2NO\7v\2\2OP\7w\2\2PQ\7t\2\2QR\7p\2\2R\20\3\2\2\2ST\7=\2\2"+
		"T\22\3\2\2\2UV\5#\22\2VW\5\25\13\2W\24\3\2\2\2XY\5!\21\2YZ\5\25\13\2Z"+
		"\\\3\2\2\2[X\3\2\2\2[\\\3\2\2\2\\\26\3\2\2\2]^\7\62\2\2^_\5\31\r\2_\30"+
		"\3\2\2\2`a\5%\23\2ab\5\31\r\2bd\3\2\2\2c`\3\2\2\2cd\3\2\2\2d\32\3\2\2"+
		"\2ef\5\37\20\2fg\5\'\24\2gh\5\35\17\2h\34\3\2\2\2ij\5\'\24\2jk\5\35\17"+
		"\2km\3\2\2\2li\3\2\2\2lm\3\2\2\2m\36\3\2\2\2no\7\62\2\2os\7z\2\2pq\7\62"+
		"\2\2qs\7Z\2\2rn\3\2\2\2rp\3\2\2\2s \3\2\2\2tu\4\62;\2u\"\3\2\2\2vw\4\63"+
		";\2w$\3\2\2\2xy\4\629\2y&\3\2\2\2z{\t\2\2\2{(\3\2\2\2|}\7-\2\2}*\3\2\2"+
		"\2~\177\7/\2\2\177,\3\2\2\2\u0080\u0081\7,\2\2\u0081.\3\2\2\2\u0082\u0083"+
		"\7\61\2\2\u0083\60\3\2\2\2\u0084\u0085\7\'\2\2\u0085\62\3\2\2\2\u0086"+
		"\u0088\t\3\2\2\u0087\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u0087\3\2"+
		"\2\2\u0089\u008a\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008c\b\32\2\2\u008c"+
		"\64\3\2\2\2\u008d\u008f\7\17\2\2\u008e\u0090\7\f\2\2\u008f\u008e\3\2\2"+
		"\2\u008f\u0090\3\2\2\2\u0090\u0093\3\2\2\2\u0091\u0093\7\f\2\2\u0092\u008d"+
		"\3\2\2\2\u0092\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0095\b\33\2\2"+
		"\u0095\66\3\2\2\2\u0096\u0097\7\61\2\2\u0097\u0098\7,\2\2\u0098\u009c"+
		"\3\2\2\2\u0099\u009b\13\2\2\2\u009a\u0099\3\2\2\2\u009b\u009e\3\2\2\2"+
		"\u009c\u009d\3\2\2\2\u009c\u009a\3\2\2\2\u009d\u009f\3\2\2\2\u009e\u009c"+
		"\3\2\2\2\u009f\u00a0\7,\2\2\u00a0\u00a1\7\61\2\2\u00a1\u00a2\3\2\2\2\u00a2"+
		"\u00a3\b\34\2\2\u00a38\3\2\2\2\u00a4\u00a5\7\61\2\2\u00a5\u00a6\7\61\2"+
		"\2\u00a6\u00aa\3\2\2\2\u00a7\u00a9\n\4\2\2\u00a8\u00a7\3\2\2\2\u00a9\u00ac"+
		"\3\2\2\2\u00aa\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ad\3\2\2\2\u00ac"+
		"\u00aa\3\2\2\2\u00ad\u00ae\b\35\2\2\u00ae:\3\2\2\2\f\2[clr\u0089\u008f"+
		"\u0092\u009c\u00aa\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}