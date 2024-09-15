// Generated from c:/Users/User/OneDrive/Desktop/CETVRTA_GODINA/InformatickiProjekat/Informaticki_Projekat/MuLexer.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class MuLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ID=1, NUMBER=2, STRING=3, BOOLEAN=4, NIL=5, ASSIGN=6, PLUS=7, MINUS=8, 
		MUL=9, DIV=10, EQ=11, GT=12, LT=13, GE=14, LE=15, NE=16, LPAREN=17, RPAREN=18, 
		LBRACE=19, RBRACE=20, SEMICOLON=21, COMMA=22, IF=23, ELSE=24, ELSEIF=25, 
		WHILE=26, LOG=27, WS=28;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"ID", "NUMBER", "STRING", "BOOLEAN", "NIL", "ASSIGN", "PLUS", "MINUS", 
			"MUL", "DIV", "EQ", "GT", "LT", "GE", "LE", "NE", "LPAREN", "RPAREN", 
			"LBRACE", "RBRACE", "SEMICOLON", "COMMA", "IF", "ELSE", "ELSEIF", "WHILE", 
			"LOG", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, "'nil'", "'='", "'+'", "'-'", "'*'", "'/'", 
			"'=='", "'>'", "'<'", "'>='", "'<='", "'!='", "'('", "')'", "'{'", "'}'", 
			"';'", "','", "'if'", "'else'", "'else if'", "'while'", "'log'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ID", "NUMBER", "STRING", "BOOLEAN", "NIL", "ASSIGN", "PLUS", "MINUS", 
			"MUL", "DIV", "EQ", "GT", "LT", "GE", "LE", "NE", "LPAREN", "RPAREN", 
			"LBRACE", "RBRACE", "SEMICOLON", "COMMA", "IF", "ELSE", "ELSEIF", "WHILE", 
			"LOG", "WS"
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


	public MuLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MuLexer.g4"; }

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
		"\u0004\u0000\u001c\u00ae\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a"+
		"\u0002\u001b\u0007\u001b\u0001\u0000\u0001\u0000\u0005\u0000<\b\u0000"+
		"\n\u0000\f\u0000?\t\u0000\u0001\u0001\u0004\u0001B\b\u0001\u000b\u0001"+
		"\f\u0001C\u0001\u0001\u0001\u0001\u0004\u0001H\b\u0001\u000b\u0001\f\u0001"+
		"I\u0003\u0001L\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0005\u0002R\b\u0002\n\u0002\f\u0002U\t\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003b\b\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001"+
		"\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0012"+
		"\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0015"+
		"\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0004\u001b\u00a9\b\u001b"+
		"\u000b\u001b\f\u001b\u00aa\u0001\u001b\u0001\u001b\u0000\u0000\u001c\u0001"+
		"\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007"+
		"\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d"+
		"\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017/"+
		"\u00181\u00193\u001a5\u001b7\u001c\u0001\u0000\u0005\u0003\u0000AZ__a"+
		"z\u0004\u000009AZ__az\u0001\u000009\u0002\u0000\"\"\\\\\u0003\u0000\t"+
		"\n\r\r  \u00b5\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000"+
		"\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000"+
		"\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000"+
		"\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000"+
		"\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000"+
		"\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000"+
		"\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000"+
		"\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000"+
		"\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%"+
		"\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001"+
		"\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000"+
		"\u0000\u0000/\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u0000"+
		"3\u0001\u0000\u0000\u0000\u00005\u0001\u0000\u0000\u0000\u00007\u0001"+
		"\u0000\u0000\u0000\u00019\u0001\u0000\u0000\u0000\u0003A\u0001\u0000\u0000"+
		"\u0000\u0005M\u0001\u0000\u0000\u0000\u0007a\u0001\u0000\u0000\u0000\t"+
		"c\u0001\u0000\u0000\u0000\u000bg\u0001\u0000\u0000\u0000\ri\u0001\u0000"+
		"\u0000\u0000\u000fk\u0001\u0000\u0000\u0000\u0011m\u0001\u0000\u0000\u0000"+
		"\u0013o\u0001\u0000\u0000\u0000\u0015q\u0001\u0000\u0000\u0000\u0017t"+
		"\u0001\u0000\u0000\u0000\u0019v\u0001\u0000\u0000\u0000\u001bx\u0001\u0000"+
		"\u0000\u0000\u001d{\u0001\u0000\u0000\u0000\u001f~\u0001\u0000\u0000\u0000"+
		"!\u0081\u0001\u0000\u0000\u0000#\u0083\u0001\u0000\u0000\u0000%\u0085"+
		"\u0001\u0000\u0000\u0000\'\u0087\u0001\u0000\u0000\u0000)\u0089\u0001"+
		"\u0000\u0000\u0000+\u008b\u0001\u0000\u0000\u0000-\u008d\u0001\u0000\u0000"+
		"\u0000/\u0090\u0001\u0000\u0000\u00001\u0095\u0001\u0000\u0000\u00003"+
		"\u009d\u0001\u0000\u0000\u00005\u00a3\u0001\u0000\u0000\u00007\u00a8\u0001"+
		"\u0000\u0000\u00009=\u0007\u0000\u0000\u0000:<\u0007\u0001\u0000\u0000"+
		";:\u0001\u0000\u0000\u0000<?\u0001\u0000\u0000\u0000=;\u0001\u0000\u0000"+
		"\u0000=>\u0001\u0000\u0000\u0000>\u0002\u0001\u0000\u0000\u0000?=\u0001"+
		"\u0000\u0000\u0000@B\u0007\u0002\u0000\u0000A@\u0001\u0000\u0000\u0000"+
		"BC\u0001\u0000\u0000\u0000CA\u0001\u0000\u0000\u0000CD\u0001\u0000\u0000"+
		"\u0000DK\u0001\u0000\u0000\u0000EG\u0005.\u0000\u0000FH\u0007\u0002\u0000"+
		"\u0000GF\u0001\u0000\u0000\u0000HI\u0001\u0000\u0000\u0000IG\u0001\u0000"+
		"\u0000\u0000IJ\u0001\u0000\u0000\u0000JL\u0001\u0000\u0000\u0000KE\u0001"+
		"\u0000\u0000\u0000KL\u0001\u0000\u0000\u0000L\u0004\u0001\u0000\u0000"+
		"\u0000MS\u0005\"\u0000\u0000NO\u0005\\\u0000\u0000OR\u0005\"\u0000\u0000"+
		"PR\b\u0003\u0000\u0000QN\u0001\u0000\u0000\u0000QP\u0001\u0000\u0000\u0000"+
		"RU\u0001\u0000\u0000\u0000SQ\u0001\u0000\u0000\u0000ST\u0001\u0000\u0000"+
		"\u0000TV\u0001\u0000\u0000\u0000US\u0001\u0000\u0000\u0000VW\u0005\"\u0000"+
		"\u0000W\u0006\u0001\u0000\u0000\u0000XY\u0005t\u0000\u0000YZ\u0005r\u0000"+
		"\u0000Z[\u0005u\u0000\u0000[b\u0005e\u0000\u0000\\]\u0005f\u0000\u0000"+
		"]^\u0005a\u0000\u0000^_\u0005l\u0000\u0000_`\u0005s\u0000\u0000`b\u0005"+
		"e\u0000\u0000aX\u0001\u0000\u0000\u0000a\\\u0001\u0000\u0000\u0000b\b"+
		"\u0001\u0000\u0000\u0000cd\u0005n\u0000\u0000de\u0005i\u0000\u0000ef\u0005"+
		"l\u0000\u0000f\n\u0001\u0000\u0000\u0000gh\u0005=\u0000\u0000h\f\u0001"+
		"\u0000\u0000\u0000ij\u0005+\u0000\u0000j\u000e\u0001\u0000\u0000\u0000"+
		"kl\u0005-\u0000\u0000l\u0010\u0001\u0000\u0000\u0000mn\u0005*\u0000\u0000"+
		"n\u0012\u0001\u0000\u0000\u0000op\u0005/\u0000\u0000p\u0014\u0001\u0000"+
		"\u0000\u0000qr\u0005=\u0000\u0000rs\u0005=\u0000\u0000s\u0016\u0001\u0000"+
		"\u0000\u0000tu\u0005>\u0000\u0000u\u0018\u0001\u0000\u0000\u0000vw\u0005"+
		"<\u0000\u0000w\u001a\u0001\u0000\u0000\u0000xy\u0005>\u0000\u0000yz\u0005"+
		"=\u0000\u0000z\u001c\u0001\u0000\u0000\u0000{|\u0005<\u0000\u0000|}\u0005"+
		"=\u0000\u0000}\u001e\u0001\u0000\u0000\u0000~\u007f\u0005!\u0000\u0000"+
		"\u007f\u0080\u0005=\u0000\u0000\u0080 \u0001\u0000\u0000\u0000\u0081\u0082"+
		"\u0005(\u0000\u0000\u0082\"\u0001\u0000\u0000\u0000\u0083\u0084\u0005"+
		")\u0000\u0000\u0084$\u0001\u0000\u0000\u0000\u0085\u0086\u0005{\u0000"+
		"\u0000\u0086&\u0001\u0000\u0000\u0000\u0087\u0088\u0005}\u0000\u0000\u0088"+
		"(\u0001\u0000\u0000\u0000\u0089\u008a\u0005;\u0000\u0000\u008a*\u0001"+
		"\u0000\u0000\u0000\u008b\u008c\u0005,\u0000\u0000\u008c,\u0001\u0000\u0000"+
		"\u0000\u008d\u008e\u0005i\u0000\u0000\u008e\u008f\u0005f\u0000\u0000\u008f"+
		".\u0001\u0000\u0000\u0000\u0090\u0091\u0005e\u0000\u0000\u0091\u0092\u0005"+
		"l\u0000\u0000\u0092\u0093\u0005s\u0000\u0000\u0093\u0094\u0005e\u0000"+
		"\u0000\u00940\u0001\u0000\u0000\u0000\u0095\u0096\u0005e\u0000\u0000\u0096"+
		"\u0097\u0005l\u0000\u0000\u0097\u0098\u0005s\u0000\u0000\u0098\u0099\u0005"+
		"e\u0000\u0000\u0099\u009a\u0005 \u0000\u0000\u009a\u009b\u0005i\u0000"+
		"\u0000\u009b\u009c\u0005f\u0000\u0000\u009c2\u0001\u0000\u0000\u0000\u009d"+
		"\u009e\u0005w\u0000\u0000\u009e\u009f\u0005h\u0000\u0000\u009f\u00a0\u0005"+
		"i\u0000\u0000\u00a0\u00a1\u0005l\u0000\u0000\u00a1\u00a2\u0005e\u0000"+
		"\u0000\u00a24\u0001\u0000\u0000\u0000\u00a3\u00a4\u0005l\u0000\u0000\u00a4"+
		"\u00a5\u0005o\u0000\u0000\u00a5\u00a6\u0005g\u0000\u0000\u00a66\u0001"+
		"\u0000\u0000\u0000\u00a7\u00a9\u0007\u0004\u0000\u0000\u00a8\u00a7\u0001"+
		"\u0000\u0000\u0000\u00a9\u00aa\u0001\u0000\u0000\u0000\u00aa\u00a8\u0001"+
		"\u0000\u0000\u0000\u00aa\u00ab\u0001\u0000\u0000\u0000\u00ab\u00ac\u0001"+
		"\u0000\u0000\u0000\u00ac\u00ad\u0006\u001b\u0000\u0000\u00ad8\u0001\u0000"+
		"\u0000\u0000\t\u0000=CIKQSa\u00aa\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}