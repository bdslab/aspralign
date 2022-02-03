// Generated from RNASecondaryStructure.g4 by ANTLR 4.9.3

package it.unicam.cs.bdslab.aspralign;

import java.util.List;

import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

@SuppressWarnings({ "all", "warnings", "unchecked", "unused", "cast" })
public class RNASecondaryStructureParser extends Parser {
    static {
	RuntimeMetaData.checkVersion("4.9.3", RuntimeMetaData.VERSION);
    }

    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();
    public static final int T__0 = 1, T__1 = 2, T__2 = 3, T__3 = 4,
	    LINE1BPSEQCT = 5, LINE2BPSEQCT = 6, LINE3BPSEQCT = 7,
	    LINE4BPSEQCT = 8, LINE5CT = 9, INDEX = 10, ZERO = 11,
	    IUPAC_CODE = 12, NUCLEOTIDES = 13, EDBN = 14, LINE_COMMENT = 15,
	    WS = 16;
    public static final int RULE_rna = 0, RULE_sequence = 1,
	    RULE_structure = 2, RULE_edbns = 3, RULE_bonds = 4, RULE_bond = 5,
	    RULE_bpseq = 6, RULE_bpseqinfo = 7, RULE_bpseqline = 8,
	    RULE_ct = 9, RULE_ctinfo = 10, RULE_ctline = 11;

    private static String[] makeRuleNames() {
	return new String[] { "rna", "sequence", "structure", "edbns",
		"bonds", "bond", "bpseq", "bpseqinfo", "bpseqline", "ct",
		"ctinfo", "ctline" };
    }

    public static final String[] ruleNames = makeRuleNames();

    private static String[] makeLiteralNames() {
	return new String[] { null, "';'", "'('", "','", "')'", null, null,
		null, null, null, null, "'0'" };
    }

    private static final String[] _LITERAL_NAMES = makeLiteralNames();

    private static String[] makeSymbolicNames() {
	return new String[] { null, null, null, null, null, "LINE1BPSEQCT",
		"LINE2BPSEQCT", "LINE3BPSEQCT", "LINE4BPSEQCT", "LINE5CT",
		"INDEX", "ZERO", "IUPAC_CODE", "NUCLEOTIDES", "EDBN",
		"LINE_COMMENT", "WS" };
    }

    private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
    public static final Vocabulary VOCABULARY = new VocabularyImpl(
	    _LITERAL_NAMES, _SYMBOLIC_NAMES);

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
    public String getGrammarFileName() {
	return "RNASecondaryStructure.g4";
    }

    @Override
    public String[] getRuleNames() {
	return ruleNames;
    }

    @Override
    public String getSerializedATN() {
	return _serializedATN;
    }

    @Override
    public ATN getATN() {
	return _ATN;
    }

    public RNASecondaryStructureParser(TokenStream input) {
	super(input);
	_interp = new ParserATNSimulator(this, _ATN, _decisionToDFA,
		_sharedContextCache);
    }

    public static class RnaContext extends ParserRuleContext {
	public RnaContext(ParserRuleContext parent, int invokingState) {
	    super(parent, invokingState);
	}

	@Override
	public int getRuleIndex() {
	    return RULE_rna;
	}

	public RnaContext() {
	}

	public void copyFrom(RnaContext ctx) {
	    super.copyFrom(ctx);
	}
    }

    public static class EdbnOrAasFormatContext extends RnaContext {
	public StructureContext structure() {
	    return getRuleContext(StructureContext.class, 0);
	}

	public SequenceContext sequence() {
	    return getRuleContext(SequenceContext.class, 0);
	}

	public EdbnOrAasFormatContext(RnaContext ctx) {
	    copyFrom(ctx);
	}

	@Override
	public void enterRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener)
			.enterEdbnOrAasFormat(this);
	}

	@Override
	public void exitRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener)
			.exitEdbnOrAasFormat(this);
	}
    }

    public static class CtFormatContext extends RnaContext {
	public CtContext ct() {
	    return getRuleContext(CtContext.class, 0);
	}

	public CtFormatContext(RnaContext ctx) {
	    copyFrom(ctx);
	}

	@Override
	public void enterRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener)
			.enterCtFormat(this);
	}

	@Override
	public void exitRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener).exitCtFormat(this);
	}
    }

    public static class BpseqFormatContext extends RnaContext {
	public BpseqContext bpseq() {
	    return getRuleContext(BpseqContext.class, 0);
	}

	public BpseqFormatContext(RnaContext ctx) {
	    copyFrom(ctx);
	}

	@Override
	public void enterRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener)
			.enterBpseqFormat(this);
	}

	@Override
	public void exitRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener)
			.exitBpseqFormat(this);
	}
    }

    public final RnaContext rna() throws RecognitionException {
	RnaContext _localctx = new RnaContext(_ctx, getState());
	enterRule(_localctx, 0, RULE_rna);
	int _la;
	try {
	    setState(30);
	    _errHandler.sync(this);
	    switch (getInterpreter().adaptivePredict(_input, 1, _ctx)) {
	    case 1:
		_localctx = new EdbnOrAasFormatContext(_localctx);
		enterOuterAlt(_localctx, 1); {
		setState(25);
		_errHandler.sync(this);
		_la = _input.LA(1);
		if (_la == NUCLEOTIDES) {
		    {
			setState(24);
			sequence();
		    }
		}

		setState(27);
		structure();
	    }
		break;
	    case 2:
		_localctx = new BpseqFormatContext(_localctx);
		enterOuterAlt(_localctx, 2); {
		setState(28);
		bpseq();
	    }
		break;
	    case 3:
		_localctx = new CtFormatContext(_localctx);
		enterOuterAlt(_localctx, 3); {
		setState(29);
		ct();
	    }
		break;
	    }
	} catch (RecognitionException re) {
	    _localctx.exception = re;
	    _errHandler.reportError(this, re);
	    _errHandler.recover(this, re);
	} finally {
	    exitRule();
	}
	return _localctx;
    }

    public static class SequenceContext extends ParserRuleContext {
	public SequenceContext(ParserRuleContext parent, int invokingState) {
	    super(parent, invokingState);
	}

	@Override
	public int getRuleIndex() {
	    return RULE_sequence;
	}

	public SequenceContext() {
	}

	public void copyFrom(SequenceContext ctx) {
	    super.copyFrom(ctx);
	}
    }

    public static class SequenceContinueContext extends SequenceContext {
	public TerminalNode NUCLEOTIDES() {
	    return getToken(RNASecondaryStructureParser.NUCLEOTIDES, 0);
	}

	public SequenceContext sequence() {
	    return getRuleContext(SequenceContext.class, 0);
	}

	public SequenceContinueContext(SequenceContext ctx) {
	    copyFrom(ctx);
	}

	@Override
	public void enterRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener)
			.enterSequenceContinue(this);
	}

	@Override
	public void exitRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener)
			.exitSequenceContinue(this);
	}
    }

    public static class SequenceEndContext extends SequenceContext {
	public TerminalNode NUCLEOTIDES() {
	    return getToken(RNASecondaryStructureParser.NUCLEOTIDES, 0);
	}

	public SequenceEndContext(SequenceContext ctx) {
	    copyFrom(ctx);
	}

	@Override
	public void enterRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener)
			.enterSequenceEnd(this);
	}

	@Override
	public void exitRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener)
			.exitSequenceEnd(this);
	}
    }

    public final SequenceContext sequence() throws RecognitionException {
	SequenceContext _localctx = new SequenceContext(_ctx, getState());
	enterRule(_localctx, 2, RULE_sequence);
	try {
	    setState(35);
	    _errHandler.sync(this);
	    switch (getInterpreter().adaptivePredict(_input, 2, _ctx)) {
	    case 1:
		_localctx = new SequenceContinueContext(_localctx);
		enterOuterAlt(_localctx, 1); {
		setState(32);
		match(NUCLEOTIDES);
		setState(33);
		sequence();
	    }
		break;
	    case 2:
		_localctx = new SequenceEndContext(_localctx);
		enterOuterAlt(_localctx, 2); {
		setState(34);
		match(NUCLEOTIDES);
	    }
		break;
	    }
	} catch (RecognitionException re) {
	    _localctx.exception = re;
	    _errHandler.reportError(this, re);
	    _errHandler.recover(this, re);
	} finally {
	    exitRule();
	}
	return _localctx;
    }

    public static class StructureContext extends ParserRuleContext {
	public StructureContext(ParserRuleContext parent, int invokingState) {
	    super(parent, invokingState);
	}

	@Override
	public int getRuleIndex() {
	    return RULE_structure;
	}

	public StructureContext() {
	}

	public void copyFrom(StructureContext ctx) {
	    super.copyFrom(ctx);
	}
    }

    public static class RnaEdbnContext extends StructureContext {
	public EdbnsContext edbns() {
	    return getRuleContext(EdbnsContext.class, 0);
	}

	public RnaEdbnContext(StructureContext ctx) {
	    copyFrom(ctx);
	}

	@Override
	public void enterRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener).enterRnaEdbn(this);
	}

	@Override
	public void exitRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener).exitRnaEdbn(this);
	}
    }

    public static class RnaAasContext extends StructureContext {
	public BondsContext bonds() {
	    return getRuleContext(BondsContext.class, 0);
	}

	public RnaAasContext(StructureContext ctx) {
	    copyFrom(ctx);
	}

	@Override
	public void enterRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener).enterRnaAas(this);
	}

	@Override
	public void exitRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener).exitRnaAas(this);
	}
    }

    public final StructureContext structure() throws RecognitionException {
	StructureContext _localctx = new StructureContext(_ctx, getState());
	enterRule(_localctx, 4, RULE_structure);
	try {
	    setState(39);
	    _errHandler.sync(this);
	    switch (_input.LA(1)) {
	    case EDBN:
		_localctx = new RnaEdbnContext(_localctx);
		enterOuterAlt(_localctx, 1); {
		setState(37);
		edbns();
	    }
		break;
	    case T__1:
		_localctx = new RnaAasContext(_localctx);
		enterOuterAlt(_localctx, 2); {
		setState(38);
		bonds();
	    }
		break;
	    default:
		throw new NoViableAltException(this);
	    }
	} catch (RecognitionException re) {
	    _localctx.exception = re;
	    _errHandler.reportError(this, re);
	    _errHandler.recover(this, re);
	} finally {
	    exitRule();
	}
	return _localctx;
    }

    public static class EdbnsContext extends ParserRuleContext {
	public EdbnsContext(ParserRuleContext parent, int invokingState) {
	    super(parent, invokingState);
	}

	@Override
	public int getRuleIndex() {
	    return RULE_edbns;
	}

	public EdbnsContext() {
	}

	public void copyFrom(EdbnsContext ctx) {
	    super.copyFrom(ctx);
	}
    }

    public static class EdbnsContinueContext extends EdbnsContext {
	public TerminalNode EDBN() {
	    return getToken(RNASecondaryStructureParser.EDBN, 0);
	}

	public EdbnsContext edbns() {
	    return getRuleContext(EdbnsContext.class, 0);
	}

	public EdbnsContinueContext(EdbnsContext ctx) {
	    copyFrom(ctx);
	}

	@Override
	public void enterRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener)
			.enterEdbnsContinue(this);
	}

	@Override
	public void exitRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener)
			.exitEdbnsContinue(this);
	}
    }

    public static class EdbnsEndContext extends EdbnsContext {
	public TerminalNode EDBN() {
	    return getToken(RNASecondaryStructureParser.EDBN, 0);
	}

	public EdbnsEndContext(EdbnsContext ctx) {
	    copyFrom(ctx);
	}

	@Override
	public void enterRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener)
			.enterEdbnsEnd(this);
	}

	@Override
	public void exitRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener).exitEdbnsEnd(this);
	}
    }

    public final EdbnsContext edbns() throws RecognitionException {
	EdbnsContext _localctx = new EdbnsContext(_ctx, getState());
	enterRule(_localctx, 6, RULE_edbns);
	try {
	    setState(44);
	    _errHandler.sync(this);
	    switch (getInterpreter().adaptivePredict(_input, 4, _ctx)) {
	    case 1:
		_localctx = new EdbnsContinueContext(_localctx);
		enterOuterAlt(_localctx, 1); {
		setState(41);
		match(EDBN);
		setState(42);
		edbns();
	    }
		break;
	    case 2:
		_localctx = new EdbnsEndContext(_localctx);
		enterOuterAlt(_localctx, 2); {
		setState(43);
		match(EDBN);
	    }
		break;
	    }
	} catch (RecognitionException re) {
	    _localctx.exception = re;
	    _errHandler.reportError(this, re);
	    _errHandler.recover(this, re);
	} finally {
	    exitRule();
	}
	return _localctx;
    }

    public static class BondsContext extends ParserRuleContext {
	public BondsContext(ParserRuleContext parent, int invokingState) {
	    super(parent, invokingState);
	}

	@Override
	public int getRuleIndex() {
	    return RULE_bonds;
	}

	public BondsContext() {
	}

	public void copyFrom(BondsContext ctx) {
	    super.copyFrom(ctx);
	}
    }

    public static class BondsEndContext extends BondsContext {
	public BondContext bond() {
	    return getRuleContext(BondContext.class, 0);
	}

	public BondsEndContext(BondsContext ctx) {
	    copyFrom(ctx);
	}

	@Override
	public void enterRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener)
			.enterBondsEnd(this);
	}

	@Override
	public void exitRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener).exitBondsEnd(this);
	}
    }

    public static class BondsContinueContext extends BondsContext {
	public BondContext bond() {
	    return getRuleContext(BondContext.class, 0);
	}

	public BondsContext bonds() {
	    return getRuleContext(BondsContext.class, 0);
	}

	public BondsContinueContext(BondsContext ctx) {
	    copyFrom(ctx);
	}

	@Override
	public void enterRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener)
			.enterBondsContinue(this);
	}

	@Override
	public void exitRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener)
			.exitBondsContinue(this);
	}
    }

    public final BondsContext bonds() throws RecognitionException {
	BondsContext _localctx = new BondsContext(_ctx, getState());
	enterRule(_localctx, 8, RULE_bonds);
	try {
	    setState(51);
	    _errHandler.sync(this);
	    switch (getInterpreter().adaptivePredict(_input, 5, _ctx)) {
	    case 1:
		_localctx = new BondsContinueContext(_localctx);
		enterOuterAlt(_localctx, 1); {
		setState(46);
		bond();
		setState(47);
		match(T__0);
		setState(48);
		bonds();
	    }
		break;
	    case 2:
		_localctx = new BondsEndContext(_localctx);
		enterOuterAlt(_localctx, 2); {
		setState(50);
		bond();
	    }
		break;
	    }
	} catch (RecognitionException re) {
	    _localctx.exception = re;
	    _errHandler.reportError(this, re);
	    _errHandler.recover(this, re);
	} finally {
	    exitRule();
	}
	return _localctx;
    }

    public static class BondContext extends ParserRuleContext {
	public List<TerminalNode> INDEX() {
	    return getTokens(RNASecondaryStructureParser.INDEX);
	}

	public TerminalNode INDEX(int i) {
	    return getToken(RNASecondaryStructureParser.INDEX, i);
	}

	public BondContext(ParserRuleContext parent, int invokingState) {
	    super(parent, invokingState);
	}

	@Override
	public int getRuleIndex() {
	    return RULE_bond;
	}

	@Override
	public void enterRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener).enterBond(this);
	}

	@Override
	public void exitRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener).exitBond(this);
	}
    }

    public final BondContext bond() throws RecognitionException {
	BondContext _localctx = new BondContext(_ctx, getState());
	enterRule(_localctx, 10, RULE_bond);
	try {
	    enterOuterAlt(_localctx, 1);
	    {
		setState(53);
		match(T__1);
		setState(54);
		match(INDEX);
		setState(55);
		match(T__2);
		setState(56);
		match(INDEX);
		setState(57);
		match(T__3);
	    }
	} catch (RecognitionException re) {
	    _localctx.exception = re;
	    _errHandler.reportError(this, re);
	    _errHandler.recover(this, re);
	} finally {
	    exitRule();
	}
	return _localctx;
    }

    public static class BpseqContext extends ParserRuleContext {
	public BpseqinfoContext bpseqinfo() {
	    return getRuleContext(BpseqinfoContext.class, 0);
	}

	public TerminalNode LINE1BPSEQCT() {
	    return getToken(RNASecondaryStructureParser.LINE1BPSEQCT, 0);
	}

	public TerminalNode LINE2BPSEQCT() {
	    return getToken(RNASecondaryStructureParser.LINE2BPSEQCT, 0);
	}

	public TerminalNode LINE3BPSEQCT() {
	    return getToken(RNASecondaryStructureParser.LINE3BPSEQCT, 0);
	}

	public TerminalNode LINE4BPSEQCT() {
	    return getToken(RNASecondaryStructureParser.LINE4BPSEQCT, 0);
	}

	public BpseqContext(ParserRuleContext parent, int invokingState) {
	    super(parent, invokingState);
	}

	@Override
	public int getRuleIndex() {
	    return RULE_bpseq;
	}

	@Override
	public void enterRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener).enterBpseq(this);
	}

	@Override
	public void exitRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener).exitBpseq(this);
	}
    }

    public final BpseqContext bpseq() throws RecognitionException {
	BpseqContext _localctx = new BpseqContext(_ctx, getState());
	enterRule(_localctx, 12, RULE_bpseq);
	int _la;
	try {
	    enterOuterAlt(_localctx, 1);
	    {
		setState(63);
		_errHandler.sync(this);
		_la = _input.LA(1);
		if (_la == LINE1BPSEQCT) {
		    {
			setState(59);
			match(LINE1BPSEQCT);
			setState(60);
			match(LINE2BPSEQCT);
			setState(61);
			match(LINE3BPSEQCT);
			setState(62);
			match(LINE4BPSEQCT);
		    }
		}

		setState(65);
		bpseqinfo();
	    }
	} catch (RecognitionException re) {
	    _localctx.exception = re;
	    _errHandler.reportError(this, re);
	    _errHandler.recover(this, re);
	} finally {
	    exitRule();
	}
	return _localctx;
    }

    public static class BpseqinfoContext extends ParserRuleContext {
	public BpseqinfoContext(ParserRuleContext parent, int invokingState) {
	    super(parent, invokingState);
	}

	@Override
	public int getRuleIndex() {
	    return RULE_bpseqinfo;
	}

	public BpseqinfoContext() {
	}

	public void copyFrom(BpseqinfoContext ctx) {
	    super.copyFrom(ctx);
	}
    }

    public static class BpseqSeqContext extends BpseqinfoContext {
	public BpseqlineContext bpseqline() {
	    return getRuleContext(BpseqlineContext.class, 0);
	}

	public BpseqinfoContext bpseqinfo() {
	    return getRuleContext(BpseqinfoContext.class, 0);
	}

	public BpseqSeqContext(BpseqinfoContext ctx) {
	    copyFrom(ctx);
	}

	@Override
	public void enterRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener)
			.enterBpseqSeq(this);
	}

	@Override
	public void exitRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener).exitBpseqSeq(this);
	}
    }

    public static class BpseqLastContext extends BpseqinfoContext {
	public BpseqlineContext bpseqline() {
	    return getRuleContext(BpseqlineContext.class, 0);
	}

	public BpseqLastContext(BpseqinfoContext ctx) {
	    copyFrom(ctx);
	}

	@Override
	public void enterRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener)
			.enterBpseqLast(this);
	}

	@Override
	public void exitRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener)
			.exitBpseqLast(this);
	}
    }

    public final BpseqinfoContext bpseqinfo() throws RecognitionException {
	BpseqinfoContext _localctx = new BpseqinfoContext(_ctx, getState());
	enterRule(_localctx, 14, RULE_bpseqinfo);
	try {
	    setState(71);
	    _errHandler.sync(this);
	    switch (getInterpreter().adaptivePredict(_input, 7, _ctx)) {
	    case 1:
		_localctx = new BpseqSeqContext(_localctx);
		enterOuterAlt(_localctx, 1); {
		setState(67);
		bpseqline();
		setState(68);
		bpseqinfo();
	    }
		break;
	    case 2:
		_localctx = new BpseqLastContext(_localctx);
		enterOuterAlt(_localctx, 2); {
		setState(70);
		bpseqline();
	    }
		break;
	    }
	} catch (RecognitionException re) {
	    _localctx.exception = re;
	    _errHandler.reportError(this, re);
	    _errHandler.recover(this, re);
	} finally {
	    exitRule();
	}
	return _localctx;
    }

    public static class BpseqlineContext extends ParserRuleContext {
	public BpseqlineContext(ParserRuleContext parent, int invokingState) {
	    super(parent, invokingState);
	}

	@Override
	public int getRuleIndex() {
	    return RULE_bpseqline;
	}

	public BpseqlineContext() {
	}

	public void copyFrom(BpseqlineContext ctx) {
	    super.copyFrom(ctx);
	}
    }

    public static class BpseqLineUnpairedContext extends BpseqlineContext {
	public TerminalNode INDEX() {
	    return getToken(RNASecondaryStructureParser.INDEX, 0);
	}

	public TerminalNode IUPAC_CODE() {
	    return getToken(RNASecondaryStructureParser.IUPAC_CODE, 0);
	}

	public TerminalNode ZERO() {
	    return getToken(RNASecondaryStructureParser.ZERO, 0);
	}

	public BpseqLineUnpairedContext(BpseqlineContext ctx) {
	    copyFrom(ctx);
	}

	@Override
	public void enterRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener)
			.enterBpseqLineUnpaired(this);
	}

	@Override
	public void exitRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener)
			.exitBpseqLineUnpaired(this);
	}
    }

    public static class BpseqLineBondContext extends BpseqlineContext {
	public List<TerminalNode> INDEX() {
	    return getTokens(RNASecondaryStructureParser.INDEX);
	}

	public TerminalNode INDEX(int i) {
	    return getToken(RNASecondaryStructureParser.INDEX, i);
	}

	public TerminalNode IUPAC_CODE() {
	    return getToken(RNASecondaryStructureParser.IUPAC_CODE, 0);
	}

	public BpseqLineBondContext(BpseqlineContext ctx) {
	    copyFrom(ctx);
	}

	@Override
	public void enterRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener)
			.enterBpseqLineBond(this);
	}

	@Override
	public void exitRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener)
			.exitBpseqLineBond(this);
	}
    }

    public final BpseqlineContext bpseqline() throws RecognitionException {
	BpseqlineContext _localctx = new BpseqlineContext(_ctx, getState());
	enterRule(_localctx, 16, RULE_bpseqline);
	try {
	    setState(79);
	    _errHandler.sync(this);
	    switch (getInterpreter().adaptivePredict(_input, 8, _ctx)) {
	    case 1:
		_localctx = new BpseqLineUnpairedContext(_localctx);
		enterOuterAlt(_localctx, 1); {
		setState(73);
		match(INDEX);
		setState(74);
		match(IUPAC_CODE);
		setState(75);
		match(ZERO);
	    }
		break;
	    case 2:
		_localctx = new BpseqLineBondContext(_localctx);
		enterOuterAlt(_localctx, 2); {
		setState(76);
		match(INDEX);
		setState(77);
		match(IUPAC_CODE);
		setState(78);
		match(INDEX);
	    }
		break;
	    }
	} catch (RecognitionException re) {
	    _localctx.exception = re;
	    _errHandler.reportError(this, re);
	    _errHandler.recover(this, re);
	} finally {
	    exitRule();
	}
	return _localctx;
    }

    public static class CtContext extends ParserRuleContext {
	public TerminalNode LINE5CT() {
	    return getToken(RNASecondaryStructureParser.LINE5CT, 0);
	}

	public CtinfoContext ctinfo() {
	    return getRuleContext(CtinfoContext.class, 0);
	}

	public TerminalNode LINE1BPSEQCT() {
	    return getToken(RNASecondaryStructureParser.LINE1BPSEQCT, 0);
	}

	public TerminalNode LINE2BPSEQCT() {
	    return getToken(RNASecondaryStructureParser.LINE2BPSEQCT, 0);
	}

	public TerminalNode LINE3BPSEQCT() {
	    return getToken(RNASecondaryStructureParser.LINE3BPSEQCT, 0);
	}

	public TerminalNode LINE4BPSEQCT() {
	    return getToken(RNASecondaryStructureParser.LINE4BPSEQCT, 0);
	}

	public CtContext(ParserRuleContext parent, int invokingState) {
	    super(parent, invokingState);
	}

	@Override
	public int getRuleIndex() {
	    return RULE_ct;
	}

	@Override
	public void enterRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener).enterCt(this);
	}

	@Override
	public void exitRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener).exitCt(this);
	}
    }

    public final CtContext ct() throws RecognitionException {
	CtContext _localctx = new CtContext(_ctx, getState());
	enterRule(_localctx, 18, RULE_ct);
	int _la;
	try {
	    enterOuterAlt(_localctx, 1);
	    {
		setState(85);
		_errHandler.sync(this);
		_la = _input.LA(1);
		if (_la == LINE1BPSEQCT) {
		    {
			setState(81);
			match(LINE1BPSEQCT);
			setState(82);
			match(LINE2BPSEQCT);
			setState(83);
			match(LINE3BPSEQCT);
			setState(84);
			match(LINE4BPSEQCT);
		    }
		}

		setState(87);
		match(LINE5CT);
		setState(88);
		ctinfo();
	    }
	} catch (RecognitionException re) {
	    _localctx.exception = re;
	    _errHandler.reportError(this, re);
	    _errHandler.recover(this, re);
	} finally {
	    exitRule();
	}
	return _localctx;
    }

    public static class CtinfoContext extends ParserRuleContext {
	public CtinfoContext(ParserRuleContext parent, int invokingState) {
	    super(parent, invokingState);
	}

	@Override
	public int getRuleIndex() {
	    return RULE_ctinfo;
	}

	public CtinfoContext() {
	}

	public void copyFrom(CtinfoContext ctx) {
	    super.copyFrom(ctx);
	}
    }

    public static class CtLastContext extends CtinfoContext {
	public CtlineContext ctline() {
	    return getRuleContext(CtlineContext.class, 0);
	}

	public CtLastContext(CtinfoContext ctx) {
	    copyFrom(ctx);
	}

	@Override
	public void enterRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener).enterCtLast(this);
	}

	@Override
	public void exitRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener).exitCtLast(this);
	}
    }

    public static class CtSeqContext extends CtinfoContext {
	public CtlineContext ctline() {
	    return getRuleContext(CtlineContext.class, 0);
	}

	public CtinfoContext ctinfo() {
	    return getRuleContext(CtinfoContext.class, 0);
	}

	public CtSeqContext(CtinfoContext ctx) {
	    copyFrom(ctx);
	}

	@Override
	public void enterRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener).enterCtSeq(this);
	}

	@Override
	public void exitRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener).exitCtSeq(this);
	}
    }

    public final CtinfoContext ctinfo() throws RecognitionException {
	CtinfoContext _localctx = new CtinfoContext(_ctx, getState());
	enterRule(_localctx, 20, RULE_ctinfo);
	try {
	    setState(94);
	    _errHandler.sync(this);
	    switch (getInterpreter().adaptivePredict(_input, 10, _ctx)) {
	    case 1:
		_localctx = new CtSeqContext(_localctx);
		enterOuterAlt(_localctx, 1); {
		setState(90);
		ctline();
		setState(91);
		ctinfo();
	    }
		break;
	    case 2:
		_localctx = new CtLastContext(_localctx);
		enterOuterAlt(_localctx, 2); {
		setState(93);
		ctline();
	    }
		break;
	    }
	} catch (RecognitionException re) {
	    _localctx.exception = re;
	    _errHandler.reportError(this, re);
	    _errHandler.recover(this, re);
	} finally {
	    exitRule();
	}
	return _localctx;
    }

    public static class CtlineContext extends ParserRuleContext {
	public CtlineContext(ParserRuleContext parent, int invokingState) {
	    super(parent, invokingState);
	}

	@Override
	public int getRuleIndex() {
	    return RULE_ctline;
	}

	public CtlineContext() {
	}

	public void copyFrom(CtlineContext ctx) {
	    super.copyFrom(ctx);
	}
    }

    public static class CtLineUnpairedContext extends CtlineContext {
	public List<TerminalNode> INDEX() {
	    return getTokens(RNASecondaryStructureParser.INDEX);
	}

	public TerminalNode INDEX(int i) {
	    return getToken(RNASecondaryStructureParser.INDEX, i);
	}

	public TerminalNode IUPAC_CODE() {
	    return getToken(RNASecondaryStructureParser.IUPAC_CODE, 0);
	}

	public List<TerminalNode> ZERO() {
	    return getTokens(RNASecondaryStructureParser.ZERO);
	}

	public TerminalNode ZERO(int i) {
	    return getToken(RNASecondaryStructureParser.ZERO, i);
	}

	public CtLineUnpairedContext(CtlineContext ctx) {
	    copyFrom(ctx);
	}

	@Override
	public void enterRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener)
			.enterCtLineUnpaired(this);
	}

	@Override
	public void exitRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener)
			.exitCtLineUnpaired(this);
	}
    }

    public static class CtLineBondContext extends CtlineContext {
	public List<TerminalNode> INDEX() {
	    return getTokens(RNASecondaryStructureParser.INDEX);
	}

	public TerminalNode INDEX(int i) {
	    return getToken(RNASecondaryStructureParser.INDEX, i);
	}

	public TerminalNode IUPAC_CODE() {
	    return getToken(RNASecondaryStructureParser.IUPAC_CODE, 0);
	}

	public List<TerminalNode> ZERO() {
	    return getTokens(RNASecondaryStructureParser.ZERO);
	}

	public TerminalNode ZERO(int i) {
	    return getToken(RNASecondaryStructureParser.ZERO, i);
	}

	public CtLineBondContext(CtlineContext ctx) {
	    copyFrom(ctx);
	}

	@Override
	public void enterRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener)
			.enterCtLineBond(this);
	}

	@Override
	public void exitRule(ParseTreeListener listener) {
	    if (listener instanceof RNASecondaryStructureListener)
		((RNASecondaryStructureListener) listener)
			.exitCtLineBond(this);
	}
    }

    public final CtlineContext ctline() throws RecognitionException {
	CtlineContext _localctx = new CtlineContext(_ctx, getState());
	enterRule(_localctx, 22, RULE_ctline);
	int _la;
	try {
	    setState(108);
	    _errHandler.sync(this);
	    switch (getInterpreter().adaptivePredict(_input, 11, _ctx)) {
	    case 1:
		_localctx = new CtLineUnpairedContext(_localctx);
		enterOuterAlt(_localctx, 1); {
		setState(96);
		match(INDEX);
		setState(97);
		match(IUPAC_CODE);
		setState(98);
		_la = _input.LA(1);
		if (!(_la == INDEX || _la == ZERO)) {
		    _errHandler.recoverInline(this);
		} else {
		    if (_input.LA(1) == Token.EOF)
			matchedEOF = true;
		    _errHandler.reportMatch(this);
		    consume();
		}
		setState(99);
		_la = _input.LA(1);
		if (!(_la == INDEX || _la == ZERO)) {
		    _errHandler.recoverInline(this);
		} else {
		    if (_input.LA(1) == Token.EOF)
			matchedEOF = true;
		    _errHandler.reportMatch(this);
		    consume();
		}
		setState(100);
		match(ZERO);
		setState(101);
		match(INDEX);
	    }
		break;
	    case 2:
		_localctx = new CtLineBondContext(_localctx);
		enterOuterAlt(_localctx, 2); {
		setState(102);
		match(INDEX);
		setState(103);
		match(IUPAC_CODE);
		setState(104);
		_la = _input.LA(1);
		if (!(_la == INDEX || _la == ZERO)) {
		    _errHandler.recoverInline(this);
		} else {
		    if (_input.LA(1) == Token.EOF)
			matchedEOF = true;
		    _errHandler.reportMatch(this);
		    consume();
		}
		setState(105);
		_la = _input.LA(1);
		if (!(_la == INDEX || _la == ZERO)) {
		    _errHandler.recoverInline(this);
		} else {
		    if (_input.LA(1) == Token.EOF)
			matchedEOF = true;
		    _errHandler.reportMatch(this);
		    consume();
		}
		setState(106);
		match(INDEX);
		setState(107);
		match(INDEX);
	    }
		break;
	    }
	} catch (RecognitionException re) {
	    _localctx.exception = re;
	    _errHandler.reportError(this, re);
	    _errHandler.recover(this, re);
	} finally {
	    exitRule();
	}
	return _localctx;
    }

    public static final String _serializedATN = "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\22q\4\2\t\2\4\3\t"
	    + "\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"
	    + "\f\t\f\4\r\t\r\3\2\5\2\34\n\2\3\2\3\2\3\2\5\2!\n\2\3\3\3\3\3\3\5\3&\n"
	    + "\3\3\4\3\4\5\4*\n\4\3\5\3\5\3\5\5\5/\n\5\3\6\3\6\3\6\3\6\3\6\5\6\66\n"
	    + "\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\5\bB\n\b\3\b\3\b\3\t\3\t\3"
	    + "\t\3\t\5\tJ\n\t\3\n\3\n\3\n\3\n\3\n\3\n\5\nR\n\n\3\13\3\13\3\13\3\13\5"
	    + "\13X\n\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\5\fa\n\f\3\r\3\r\3\r\3\r\3\r"
	    + "\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\ro\n\r\3\r\2\2\16\2\4\6\b\n\f\16\20\22"
	    + "\24\26\30\2\3\3\2\f\r\2q\2 \3\2\2\2\4%\3\2\2\2\6)\3\2\2\2\b.\3\2\2\2\n"
	    + "\65\3\2\2\2\f\67\3\2\2\2\16A\3\2\2\2\20I\3\2\2\2\22Q\3\2\2\2\24W\3\2\2"
	    + "\2\26`\3\2\2\2\30n\3\2\2\2\32\34\5\4\3\2\33\32\3\2\2\2\33\34\3\2\2\2\34"
	    + "\35\3\2\2\2\35!\5\6\4\2\36!\5\16\b\2\37!\5\24\13\2 \33\3\2\2\2 \36\3\2"
	    + "\2\2 \37\3\2\2\2!\3\3\2\2\2\"#\7\17\2\2#&\5\4\3\2$&\7\17\2\2%\"\3\2\2"
	    + "\2%$\3\2\2\2&\5\3\2\2\2\'*\5\b\5\2(*\5\n\6\2)\'\3\2\2\2)(\3\2\2\2*\7\3"
	    + "\2\2\2+,\7\20\2\2,/\5\b\5\2-/\7\20\2\2.+\3\2\2\2.-\3\2\2\2/\t\3\2\2\2"
	    + "\60\61\5\f\7\2\61\62\7\3\2\2\62\63\5\n\6\2\63\66\3\2\2\2\64\66\5\f\7\2"
	    + "\65\60\3\2\2\2\65\64\3\2\2\2\66\13\3\2\2\2\678\7\4\2\289\7\f\2\29:\7\5"
	    + "\2\2:;\7\f\2\2;<\7\6\2\2<\r\3\2\2\2=>\7\7\2\2>?\7\b\2\2?@\7\t\2\2@B\7"
	    + "\n\2\2A=\3\2\2\2AB\3\2\2\2BC\3\2\2\2CD\5\20\t\2D\17\3\2\2\2EF\5\22\n\2"
	    + "FG\5\20\t\2GJ\3\2\2\2HJ\5\22\n\2IE\3\2\2\2IH\3\2\2\2J\21\3\2\2\2KL\7\f"
	    + "\2\2LM\7\16\2\2MR\7\r\2\2NO\7\f\2\2OP\7\16\2\2PR\7\f\2\2QK\3\2\2\2QN\3"
	    + "\2\2\2R\23\3\2\2\2ST\7\7\2\2TU\7\b\2\2UV\7\t\2\2VX\7\n\2\2WS\3\2\2\2W"
	    + "X\3\2\2\2XY\3\2\2\2YZ\7\13\2\2Z[\5\26\f\2[\25\3\2\2\2\\]\5\30\r\2]^\5"
	    + "\26\f\2^a\3\2\2\2_a\5\30\r\2`\\\3\2\2\2`_\3\2\2\2a\27\3\2\2\2bc\7\f\2"
	    + "\2cd\7\16\2\2de\t\2\2\2ef\t\2\2\2fg\7\r\2\2go\7\f\2\2hi\7\f\2\2ij\7\16"
	    + "\2\2jk\t\2\2\2kl\t\2\2\2lm\7\f\2\2mo\7\f\2\2nb\3\2\2\2nh\3\2\2\2o\31\3"
	    + "\2\2\2\16\33 %).\65AIQW`n";
    public static final ATN _ATN = new ATNDeserializer()
	    .deserialize(_serializedATN.toCharArray());
    static {
	_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
	    _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
	}
    }
}