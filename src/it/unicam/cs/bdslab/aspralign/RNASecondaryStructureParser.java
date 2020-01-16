// Generated from RNASecondaryStructure.g4 by ANTLR 4.7.2

	package it.unicam.cs.bdslab.aspralign;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RNASecondaryStructureParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, INDEX=5, NUCLEOTIDES=6, EDBN=7, IUPAC_CODE=8, 
		NON_STANDARD_CODE=9, EDBN_CODE=10, LINE_COMMENT=11, WS=12;
	public static final int
		RULE_rna = 0, RULE_sequence = 1, RULE_structure = 2, RULE_edbns = 3, RULE_bonds = 4, 
		RULE_bond = 5;
	private static String[] makeRuleNames() {
		return new String[] {
			"rna", "sequence", "structure", "edbns", "bonds", "bond"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'('", "','", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, "INDEX", "NUCLEOTIDES", "EDBN", "IUPAC_CODE", 
			"NON_STANDARD_CODE", "EDBN_CODE", "LINE_COMMENT", "WS"
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

	@Override
	public String getGrammarFileName() { return "RNASecondaryStructure.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RNASecondaryStructureParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class RnaContext extends ParserRuleContext {
		public StructureContext structure() {
			return getRuleContext(StructureContext.class,0);
		}
		public SequenceContext sequence() {
			return getRuleContext(SequenceContext.class,0);
		}
		public RnaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rna; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterRna(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitRna(this);
		}
	}

	public final RnaContext rna() throws RecognitionException {
		RnaContext _localctx = new RnaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_rna);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(13);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUCLEOTIDES) {
				{
				setState(12);
				sequence();
				}
			}

			setState(15);
			structure();
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

	public static class SequenceContext extends ParserRuleContext {
		public SequenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sequence; }
	 
		public SequenceContext() { }
		public void copyFrom(SequenceContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SequenceContinueContext extends SequenceContext {
		public TerminalNode NUCLEOTIDES() { return getToken(RNASecondaryStructureParser.NUCLEOTIDES, 0); }
		public SequenceContext sequence() {
			return getRuleContext(SequenceContext.class,0);
		}
		public SequenceContinueContext(SequenceContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterSequenceContinue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitSequenceContinue(this);
		}
	}
	public static class SequenceEndContext extends SequenceContext {
		public TerminalNode NUCLEOTIDES() { return getToken(RNASecondaryStructureParser.NUCLEOTIDES, 0); }
		public SequenceEndContext(SequenceContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterSequenceEnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitSequenceEnd(this);
		}
	}

	public final SequenceContext sequence() throws RecognitionException {
		SequenceContext _localctx = new SequenceContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_sequence);
		try {
			setState(20);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				_localctx = new SequenceContinueContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(17);
				match(NUCLEOTIDES);
				setState(18);
				sequence();
				}
				break;
			case 2:
				_localctx = new SequenceEndContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(19);
				match(NUCLEOTIDES);
				}
				break;
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

	public static class StructureContext extends ParserRuleContext {
		public StructureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structure; }
	 
		public StructureContext() { }
		public void copyFrom(StructureContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class RnaEdbnContext extends StructureContext {
		public EdbnsContext edbns() {
			return getRuleContext(EdbnsContext.class,0);
		}
		public RnaEdbnContext(StructureContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterRnaEdbn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitRnaEdbn(this);
		}
	}
	public static class RnaAasContext extends StructureContext {
		public BondsContext bonds() {
			return getRuleContext(BondsContext.class,0);
		}
		public RnaAasContext(StructureContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterRnaAas(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitRnaAas(this);
		}
	}

	public final StructureContext structure() throws RecognitionException {
		StructureContext _localctx = new StructureContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_structure);
		try {
			setState(24);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EDBN:
				_localctx = new RnaEdbnContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(22);
				edbns();
				}
				break;
			case T__1:
				_localctx = new RnaAasContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(23);
				bonds();
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

	public static class EdbnsContext extends ParserRuleContext {
		public EdbnsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_edbns; }
	 
		public EdbnsContext() { }
		public void copyFrom(EdbnsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class EdbnsContinueContext extends EdbnsContext {
		public TerminalNode EDBN() { return getToken(RNASecondaryStructureParser.EDBN, 0); }
		public EdbnsContext edbns() {
			return getRuleContext(EdbnsContext.class,0);
		}
		public EdbnsContinueContext(EdbnsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterEdbnsContinue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitEdbnsContinue(this);
		}
	}
	public static class EdbnsEndContext extends EdbnsContext {
		public TerminalNode EDBN() { return getToken(RNASecondaryStructureParser.EDBN, 0); }
		public EdbnsEndContext(EdbnsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterEdbnsEnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitEdbnsEnd(this);
		}
	}

	public final EdbnsContext edbns() throws RecognitionException {
		EdbnsContext _localctx = new EdbnsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_edbns);
		try {
			setState(29);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				_localctx = new EdbnsContinueContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(26);
				match(EDBN);
				setState(27);
				edbns();
				}
				break;
			case 2:
				_localctx = new EdbnsEndContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(28);
				match(EDBN);
				}
				break;
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

	public static class BondsContext extends ParserRuleContext {
		public BondsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bonds; }
	 
		public BondsContext() { }
		public void copyFrom(BondsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BondsEndContext extends BondsContext {
		public BondContext bond() {
			return getRuleContext(BondContext.class,0);
		}
		public BondsEndContext(BondsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterBondsEnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitBondsEnd(this);
		}
	}
	public static class BondsContinueContext extends BondsContext {
		public BondContext bond() {
			return getRuleContext(BondContext.class,0);
		}
		public BondsContext bonds() {
			return getRuleContext(BondsContext.class,0);
		}
		public BondsContinueContext(BondsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterBondsContinue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitBondsContinue(this);
		}
	}

	public final BondsContext bonds() throws RecognitionException {
		BondsContext _localctx = new BondsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_bonds);
		try {
			setState(36);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				_localctx = new BondsContinueContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(31);
				bond();
				setState(32);
				match(T__0);
				setState(33);
				bonds();
				}
				break;
			case 2:
				_localctx = new BondsEndContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(35);
				bond();
				}
				break;
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

	public static class BondContext extends ParserRuleContext {
		public List<TerminalNode> INDEX() { return getTokens(RNASecondaryStructureParser.INDEX); }
		public TerminalNode INDEX(int i) {
			return getToken(RNASecondaryStructureParser.INDEX, i);
		}
		public BondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).enterBond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RNASecondaryStructureListener ) ((RNASecondaryStructureListener)listener).exitBond(this);
		}
	}

	public final BondContext bond() throws RecognitionException {
		BondContext _localctx = new BondContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_bond);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			match(T__1);
			setState(39);
			match(INDEX);
			setState(40);
			match(T__2);
			setState(41);
			match(INDEX);
			setState(42);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\16/\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\5\2\20\n\2\3\2\3\2\3\3\3\3\3\3"+
		"\5\3\27\n\3\3\4\3\4\5\4\33\n\4\3\5\3\5\3\5\5\5 \n\5\3\6\3\6\3\6\3\6\3"+
		"\6\5\6\'\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\2\2\b\2\4\6\b\n\f\2\2\2-\2\17"+
		"\3\2\2\2\4\26\3\2\2\2\6\32\3\2\2\2\b\37\3\2\2\2\n&\3\2\2\2\f(\3\2\2\2"+
		"\16\20\5\4\3\2\17\16\3\2\2\2\17\20\3\2\2\2\20\21\3\2\2\2\21\22\5\6\4\2"+
		"\22\3\3\2\2\2\23\24\7\b\2\2\24\27\5\4\3\2\25\27\7\b\2\2\26\23\3\2\2\2"+
		"\26\25\3\2\2\2\27\5\3\2\2\2\30\33\5\b\5\2\31\33\5\n\6\2\32\30\3\2\2\2"+
		"\32\31\3\2\2\2\33\7\3\2\2\2\34\35\7\t\2\2\35 \5\b\5\2\36 \7\t\2\2\37\34"+
		"\3\2\2\2\37\36\3\2\2\2 \t\3\2\2\2!\"\5\f\7\2\"#\7\3\2\2#$\5\n\6\2$\'\3"+
		"\2\2\2%\'\5\f\7\2&!\3\2\2\2&%\3\2\2\2\'\13\3\2\2\2()\7\4\2\2)*\7\7\2\2"+
		"*+\7\5\2\2+,\7\7\2\2,-\7\6\2\2-\r\3\2\2\2\7\17\26\32\37&";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}