// Generated from RNASecondaryStructure.g4 by ANTLR 4.4

	package it.unicam.cs.bdslab.aspralign;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RNASecondaryStructureLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__3=1, T__2=2, T__1=3, T__0=4, LINE1BPSEQCT=5, LINE2BPSEQCT=6, LINE3BPSEQCT=7, 
		LINE4BPSEQCT=8, LINE5CT=9, INDEX=10, ZERO=11, IUPAC_CODE=12, NUCLEOTIDES=13, 
		EDBN=14, LINE_COMMENT=15, WS=16;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'"
	};
	public static final String[] ruleNames = {
		"T__3", "T__2", "T__1", "T__0", "LINE1BPSEQCT", "LINE2BPSEQCT", "LINE3BPSEQCT", 
		"LINE4BPSEQCT", "LINE5CT", "NONEWLINE", "INDEX", "ZERO", "IUPAC_CODE", 
		"NON_STANDARD_CODE", "NUCLEOTIDES", "EDBN_CODE", "EDBN", "LINE_COMMENT", 
		"WS"
	};


	public RNASecondaryStructureLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "RNASecondaryStructure.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\22\u00d5\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6<\n\6\f\6\16\6?\13\6\3\6\5\6B\n\6\3\6"+
		"\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7P\n\7\f\7\16\7S\13\7\3"+
		"\7\5\7V\n\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\be\n"+
		"\b\f\b\16\bh\13\b\3\b\5\bk\n\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\7\ty\n\t\f\t\16\t|\13\t\3\t\5\t\177\n\t\3\t\3\t\3\n\7\n\u0084"+
		"\n\n\f\n\16\n\u0087\13\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\5\n\u0097\n\n\3\n\7\n\u009a\n\n\f\n\16\n\u009d\13\n\3\n\5\n"+
		"\u00a0\n\n\3\n\3\n\3\13\3\13\3\f\3\f\7\f\u00a8\n\f\f\f\16\f\u00ab\13\f"+
		"\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\6\20\u00b5\n\20\r\20\16\20\u00b6"+
		"\3\21\5\21\u00ba\n\21\3\22\6\22\u00bd\n\22\r\22\16\22\u00be\3\23\3\23"+
		"\7\23\u00c3\n\23\f\23\16\23\u00c6\13\23\3\23\5\23\u00c9\n\23\3\23\3\23"+
		"\3\23\3\23\3\24\6\24\u00d0\n\24\r\24\16\24\u00d1\3\24\3\24\t=Qfz\u0085"+
		"\u009b\u00c4\2\25\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\2\27\f\31"+
		"\r\33\16\35\2\37\17!\2#\20%\21\'\22\3\2\t\4\2\f\f\17\17\3\2\63;\3\2\62"+
		";\17\2//CFIJMMOPTY[[cfijmmopty{{\17\2$$--\61\61\66\6699??AAKKQR]]__aa"+
		"\u0080\u0080\n\2*+\60\60>>@@C]__c}\177\177\5\2\13\f\17\17\"\"\u00e5\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2"+
		"\33\3\2\2\2\2\37\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\3)\3\2\2\2"+
		"\5+\3\2\2\2\7-\3\2\2\2\t/\3\2\2\2\13\61\3\2\2\2\rE\3\2\2\2\17Y\3\2\2\2"+
		"\21n\3\2\2\2\23\u0085\3\2\2\2\25\u00a3\3\2\2\2\27\u00a5\3\2\2\2\31\u00ac"+
		"\3\2\2\2\33\u00ae\3\2\2\2\35\u00b0\3\2\2\2\37\u00b4\3\2\2\2!\u00b9\3\2"+
		"\2\2#\u00bc\3\2\2\2%\u00c0\3\2\2\2\'\u00cf\3\2\2\2)*\7*\2\2*\4\3\2\2\2"+
		"+,\7+\2\2,\6\3\2\2\2-.\7=\2\2.\b\3\2\2\2/\60\7.\2\2\60\n\3\2\2\2\61\62"+
		"\7H\2\2\62\63\7k\2\2\63\64\7n\2\2\64\65\7g\2\2\65\66\7p\2\2\66\67\7c\2"+
		"\2\678\7o\2\289\7g\2\29=\3\2\2\2:<\13\2\2\2;:\3\2\2\2<?\3\2\2\2=>\3\2"+
		"\2\2=;\3\2\2\2>A\3\2\2\2?=\3\2\2\2@B\7\17\2\2A@\3\2\2\2AB\3\2\2\2BC\3"+
		"\2\2\2CD\7\f\2\2D\f\3\2\2\2EF\7Q\2\2FG\7t\2\2GH\7i\2\2HI\7c\2\2IJ\7p\2"+
		"\2JK\7k\2\2KL\7u\2\2LM\7o\2\2MQ\3\2\2\2NP\13\2\2\2ON\3\2\2\2PS\3\2\2\2"+
		"QR\3\2\2\2QO\3\2\2\2RU\3\2\2\2SQ\3\2\2\2TV\7\17\2\2UT\3\2\2\2UV\3\2\2"+
		"\2VW\3\2\2\2WX\7\f\2\2X\16\3\2\2\2YZ\7C\2\2Z[\7e\2\2[\\\7e\2\2\\]\7g\2"+
		"\2]^\7u\2\2^_\7u\2\2_`\7k\2\2`a\7q\2\2ab\7p\2\2bf\3\2\2\2ce\13\2\2\2d"+
		"c\3\2\2\2eh\3\2\2\2fg\3\2\2\2fd\3\2\2\2gj\3\2\2\2hf\3\2\2\2ik\7\17\2\2"+
		"ji\3\2\2\2jk\3\2\2\2kl\3\2\2\2lm\7\f\2\2m\20\3\2\2\2no\7E\2\2op\7k\2\2"+
		"pq\7v\2\2qr\7c\2\2rs\7v\2\2st\7k\2\2tu\7q\2\2uv\7p\2\2vz\3\2\2\2wy\13"+
		"\2\2\2xw\3\2\2\2y|\3\2\2\2z{\3\2\2\2zx\3\2\2\2{~\3\2\2\2|z\3\2\2\2}\177"+
		"\7\17\2\2~}\3\2\2\2~\177\3\2\2\2\177\u0080\3\2\2\2\u0080\u0081\7\f\2\2"+
		"\u0081\22\3\2\2\2\u0082\u0084\5\25\13\2\u0083\u0082\3\2\2\2\u0084\u0087"+
		"\3\2\2\2\u0085\u0086\3\2\2\2\u0085\u0083\3\2\2\2\u0086\u0096\3\2\2\2\u0087"+
		"\u0085\3\2\2\2\u0088\u0089\7G\2\2\u0089\u008a\7P\2\2\u008a\u008b\7G\2"+
		"\2\u008b\u008c\7T\2\2\u008c\u008d\7I\2\2\u008d\u0097\7[\2\2\u008e\u008f"+
		"\7G\2\2\u008f\u0090\7p\2\2\u0090\u0091\7g\2\2\u0091\u0092\7t\2\2\u0092"+
		"\u0093\7i\2\2\u0093\u0097\7{\2\2\u0094\u0095\7f\2\2\u0095\u0097\7I\2\2"+
		"\u0096\u0088\3\2\2\2\u0096\u008e\3\2\2\2\u0096\u0094\3\2\2\2\u0097\u009b"+
		"\3\2\2\2\u0098\u009a\13\2\2\2\u0099\u0098\3\2\2\2\u009a\u009d\3\2\2\2"+
		"\u009b\u009c\3\2\2\2\u009b\u0099\3\2\2\2\u009c\u009f\3\2\2\2\u009d\u009b"+
		"\3\2\2\2\u009e\u00a0\7\17\2\2\u009f\u009e\3\2\2\2\u009f\u00a0\3\2\2\2"+
		"\u00a0\u00a1\3\2\2\2\u00a1\u00a2\7\f\2\2\u00a2\24\3\2\2\2\u00a3\u00a4"+
		"\n\2\2\2\u00a4\26\3\2\2\2\u00a5\u00a9\t\3\2\2\u00a6\u00a8\t\4\2\2\u00a7"+
		"\u00a6\3\2\2\2\u00a8\u00ab\3\2\2\2\u00a9\u00a7\3\2\2\2\u00a9\u00aa\3\2"+
		"\2\2\u00aa\30\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ac\u00ad\7\62\2\2\u00ad\32"+
		"\3\2\2\2\u00ae\u00af\t\5\2\2\u00af\34\3\2\2\2\u00b0\u00b1\t\6\2\2\u00b1"+
		"\36\3\2\2\2\u00b2\u00b5\5\33\16\2\u00b3\u00b5\5\35\17\2\u00b4\u00b2\3"+
		"\2\2\2\u00b4\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6"+
		"\u00b7\3\2\2\2\u00b7 \3\2\2\2\u00b8\u00ba\t\7\2\2\u00b9\u00b8\3\2\2\2"+
		"\u00ba\"\3\2\2\2\u00bb\u00bd\5!\21\2\u00bc\u00bb\3\2\2\2\u00bd\u00be\3"+
		"\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf$\3\2\2\2\u00c0\u00c4"+
		"\7%\2\2\u00c1\u00c3\13\2\2\2\u00c2\u00c1\3\2\2\2\u00c3\u00c6\3\2\2\2\u00c4"+
		"\u00c5\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c5\u00c8\3\2\2\2\u00c6\u00c4\3\2"+
		"\2\2\u00c7\u00c9\7\17\2\2\u00c8\u00c7\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9"+
		"\u00ca\3\2\2\2\u00ca\u00cb\7\f\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00cd\b\23"+
		"\2\2\u00cd&\3\2\2\2\u00ce\u00d0\t\b\2\2\u00cf\u00ce\3\2\2\2\u00d0\u00d1"+
		"\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3"+
		"\u00d4\b\24\2\2\u00d4(\3\2\2\2\27\2=AQUfjz~\u0085\u0096\u009b\u009f\u00a9"+
		"\u00b4\u00b6\u00b9\u00be\u00c4\u00c8\u00d1\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}