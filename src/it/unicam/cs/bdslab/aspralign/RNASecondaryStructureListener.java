// Generated from RNASecondaryStructure.g4 by ANTLR 4.9.3

package it.unicam.cs.bdslab.aspralign;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RNASecondaryStructureParser}.
 */
public interface RNASecondaryStructureListener extends ParseTreeListener {
    /**
     * Enter a parse tree produced by the {@code edbnOrAasFormat} labeled
     * alternative in {@link RNASecondaryStructureParser#rna}.
     * 
     * @param ctx the parse tree
     */
    void enterEdbnOrAasFormat(
	    RNASecondaryStructureParser.EdbnOrAasFormatContext ctx);

    /**
     * Exit a parse tree produced by the {@code edbnOrAasFormat} labeled
     * alternative in {@link RNASecondaryStructureParser#rna}.
     * 
     * @param ctx the parse tree
     */
    void exitEdbnOrAasFormat(
	    RNASecondaryStructureParser.EdbnOrAasFormatContext ctx);

    /**
     * Enter a parse tree produced by the {@code bpseqFormat} labeled
     * alternative in {@link RNASecondaryStructureParser#rna}.
     * 
     * @param ctx the parse tree
     */
    void enterBpseqFormat(RNASecondaryStructureParser.BpseqFormatContext ctx);

    /**
     * Exit a parse tree produced by the {@code bpseqFormat} labeled
     * alternative in {@link RNASecondaryStructureParser#rna}.
     * 
     * @param ctx the parse tree
     */
    void exitBpseqFormat(RNASecondaryStructureParser.BpseqFormatContext ctx);

    /**
     * Enter a parse tree produced by the {@code ctFormat} labeled alternative
     * in {@link RNASecondaryStructureParser#rna}.
     * 
     * @param ctx the parse tree
     */
    void enterCtFormat(RNASecondaryStructureParser.CtFormatContext ctx);

    /**
     * Exit a parse tree produced by the {@code ctFormat} labeled alternative
     * in {@link RNASecondaryStructureParser#rna}.
     * 
     * @param ctx the parse tree
     */
    void exitCtFormat(RNASecondaryStructureParser.CtFormatContext ctx);

    /**
     * Enter a parse tree produced by the {@code sequenceContinue} labeled
     * alternative in {@link RNASecondaryStructureParser#sequence}.
     * 
     * @param ctx the parse tree
     */
    void enterSequenceContinue(
	    RNASecondaryStructureParser.SequenceContinueContext ctx);

    /**
     * Exit a parse tree produced by the {@code sequenceContinue} labeled
     * alternative in {@link RNASecondaryStructureParser#sequence}.
     * 
     * @param ctx the parse tree
     */
    void exitSequenceContinue(
	    RNASecondaryStructureParser.SequenceContinueContext ctx);

    /**
     * Enter a parse tree produced by the {@code sequenceEnd} labeled
     * alternative in {@link RNASecondaryStructureParser#sequence}.
     * 
     * @param ctx the parse tree
     */
    void enterSequenceEnd(RNASecondaryStructureParser.SequenceEndContext ctx);

    /**
     * Exit a parse tree produced by the {@code sequenceEnd} labeled
     * alternative in {@link RNASecondaryStructureParser#sequence}.
     * 
     * @param ctx the parse tree
     */
    void exitSequenceEnd(RNASecondaryStructureParser.SequenceEndContext ctx);

    /**
     * Enter a parse tree produced by the {@code rnaEdbn} labeled alternative
     * in {@link RNASecondaryStructureParser#structure}.
     * 
     * @param ctx the parse tree
     */
    void enterRnaEdbn(RNASecondaryStructureParser.RnaEdbnContext ctx);

    /**
     * Exit a parse tree produced by the {@code rnaEdbn} labeled alternative
     * in {@link RNASecondaryStructureParser#structure}.
     * 
     * @param ctx the parse tree
     */
    void exitRnaEdbn(RNASecondaryStructureParser.RnaEdbnContext ctx);

    /**
     * Enter a parse tree produced by the {@code rnaAas} labeled alternative
     * in {@link RNASecondaryStructureParser#structure}.
     * 
     * @param ctx the parse tree
     */
    void enterRnaAas(RNASecondaryStructureParser.RnaAasContext ctx);

    /**
     * Exit a parse tree produced by the {@code rnaAas} labeled alternative in
     * {@link RNASecondaryStructureParser#structure}.
     * 
     * @param ctx the parse tree
     */
    void exitRnaAas(RNASecondaryStructureParser.RnaAasContext ctx);

    /**
     * Enter a parse tree produced by the {@code edbnsContinue} labeled
     * alternative in {@link RNASecondaryStructureParser#edbns}.
     * 
     * @param ctx the parse tree
     */
    void enterEdbnsContinue(
	    RNASecondaryStructureParser.EdbnsContinueContext ctx);

    /**
     * Exit a parse tree produced by the {@code edbnsContinue} labeled
     * alternative in {@link RNASecondaryStructureParser#edbns}.
     * 
     * @param ctx the parse tree
     */
    void exitEdbnsContinue(
	    RNASecondaryStructureParser.EdbnsContinueContext ctx);

    /**
     * Enter a parse tree produced by the {@code edbnsEnd} labeled alternative
     * in {@link RNASecondaryStructureParser#edbns}.
     * 
     * @param ctx the parse tree
     */
    void enterEdbnsEnd(RNASecondaryStructureParser.EdbnsEndContext ctx);

    /**
     * Exit a parse tree produced by the {@code edbnsEnd} labeled alternative
     * in {@link RNASecondaryStructureParser#edbns}.
     * 
     * @param ctx the parse tree
     */
    void exitEdbnsEnd(RNASecondaryStructureParser.EdbnsEndContext ctx);

    /**
     * Enter a parse tree produced by the {@code bondsContinue} labeled
     * alternative in {@link RNASecondaryStructureParser#bonds}.
     * 
     * @param ctx the parse tree
     */
    void enterBondsContinue(
	    RNASecondaryStructureParser.BondsContinueContext ctx);

    /**
     * Exit a parse tree produced by the {@code bondsContinue} labeled
     * alternative in {@link RNASecondaryStructureParser#bonds}.
     * 
     * @param ctx the parse tree
     */
    void exitBondsContinue(
	    RNASecondaryStructureParser.BondsContinueContext ctx);

    /**
     * Enter a parse tree produced by the {@code bondsEnd} labeled alternative
     * in {@link RNASecondaryStructureParser#bonds}.
     * 
     * @param ctx the parse tree
     */
    void enterBondsEnd(RNASecondaryStructureParser.BondsEndContext ctx);

    /**
     * Exit a parse tree produced by the {@code bondsEnd} labeled alternative
     * in {@link RNASecondaryStructureParser#bonds}.
     * 
     * @param ctx the parse tree
     */
    void exitBondsEnd(RNASecondaryStructureParser.BondsEndContext ctx);

    /**
     * Enter a parse tree produced by
     * {@link RNASecondaryStructureParser#bond}.
     * 
     * @param ctx the parse tree
     */
    void enterBond(RNASecondaryStructureParser.BondContext ctx);

    /**
     * Exit a parse tree produced by {@link RNASecondaryStructureParser#bond}.
     * 
     * @param ctx the parse tree
     */
    void exitBond(RNASecondaryStructureParser.BondContext ctx);

    /**
     * Enter a parse tree produced by
     * {@link RNASecondaryStructureParser#bpseq}.
     * 
     * @param ctx the parse tree
     */
    void enterBpseq(RNASecondaryStructureParser.BpseqContext ctx);

    /**
     * Exit a parse tree produced by
     * {@link RNASecondaryStructureParser#bpseq}.
     * 
     * @param ctx the parse tree
     */
    void exitBpseq(RNASecondaryStructureParser.BpseqContext ctx);

    /**
     * Enter a parse tree produced by the {@code bpseqSeq} labeled alternative
     * in {@link RNASecondaryStructureParser#bpseqinfo}.
     * 
     * @param ctx the parse tree
     */
    void enterBpseqSeq(RNASecondaryStructureParser.BpseqSeqContext ctx);

    /**
     * Exit a parse tree produced by the {@code bpseqSeq} labeled alternative
     * in {@link RNASecondaryStructureParser#bpseqinfo}.
     * 
     * @param ctx the parse tree
     */
    void exitBpseqSeq(RNASecondaryStructureParser.BpseqSeqContext ctx);

    /**
     * Enter a parse tree produced by the {@code bpseqLast} labeled
     * alternative in {@link RNASecondaryStructureParser#bpseqinfo}.
     * 
     * @param ctx the parse tree
     */
    void enterBpseqLast(RNASecondaryStructureParser.BpseqLastContext ctx);

    /**
     * Exit a parse tree produced by the {@code bpseqLast} labeled alternative
     * in {@link RNASecondaryStructureParser#bpseqinfo}.
     * 
     * @param ctx the parse tree
     */
    void exitBpseqLast(RNASecondaryStructureParser.BpseqLastContext ctx);

    /**
     * Enter a parse tree produced by the {@code bpseqLineUnpaired} labeled
     * alternative in {@link RNASecondaryStructureParser#bpseqline}.
     * 
     * @param ctx the parse tree
     */
    void enterBpseqLineUnpaired(
	    RNASecondaryStructureParser.BpseqLineUnpairedContext ctx);

    /**
     * Exit a parse tree produced by the {@code bpseqLineUnpaired} labeled
     * alternative in {@link RNASecondaryStructureParser#bpseqline}.
     * 
     * @param ctx the parse tree
     */
    void exitBpseqLineUnpaired(
	    RNASecondaryStructureParser.BpseqLineUnpairedContext ctx);

    /**
     * Enter a parse tree produced by the {@code bpseqLineBond} labeled
     * alternative in {@link RNASecondaryStructureParser#bpseqline}.
     * 
     * @param ctx the parse tree
     */
    void enterBpseqLineBond(
	    RNASecondaryStructureParser.BpseqLineBondContext ctx);

    /**
     * Exit a parse tree produced by the {@code bpseqLineBond} labeled
     * alternative in {@link RNASecondaryStructureParser#bpseqline}.
     * 
     * @param ctx the parse tree
     */
    void exitBpseqLineBond(
	    RNASecondaryStructureParser.BpseqLineBondContext ctx);

    /**
     * Enter a parse tree produced by {@link RNASecondaryStructureParser#ct}.
     * 
     * @param ctx the parse tree
     */
    void enterCt(RNASecondaryStructureParser.CtContext ctx);

    /**
     * Exit a parse tree produced by {@link RNASecondaryStructureParser#ct}.
     * 
     * @param ctx the parse tree
     */
    void exitCt(RNASecondaryStructureParser.CtContext ctx);

    /**
     * Enter a parse tree produced by the {@code ctSeq} labeled alternative in
     * {@link RNASecondaryStructureParser#ctinfo}.
     * 
     * @param ctx the parse tree
     */
    void enterCtSeq(RNASecondaryStructureParser.CtSeqContext ctx);

    /**
     * Exit a parse tree produced by the {@code ctSeq} labeled alternative in
     * {@link RNASecondaryStructureParser#ctinfo}.
     * 
     * @param ctx the parse tree
     */
    void exitCtSeq(RNASecondaryStructureParser.CtSeqContext ctx);

    /**
     * Enter a parse tree produced by the {@code ctLast} labeled alternative
     * in {@link RNASecondaryStructureParser#ctinfo}.
     * 
     * @param ctx the parse tree
     */
    void enterCtLast(RNASecondaryStructureParser.CtLastContext ctx);

    /**
     * Exit a parse tree produced by the {@code ctLast} labeled alternative in
     * {@link RNASecondaryStructureParser#ctinfo}.
     * 
     * @param ctx the parse tree
     */
    void exitCtLast(RNASecondaryStructureParser.CtLastContext ctx);

    /**
     * Enter a parse tree produced by the {@code ctLineUnpaired} labeled
     * alternative in {@link RNASecondaryStructureParser#ctline}.
     * 
     * @param ctx the parse tree
     */
    void enterCtLineUnpaired(
	    RNASecondaryStructureParser.CtLineUnpairedContext ctx);

    /**
     * Exit a parse tree produced by the {@code ctLineUnpaired} labeled
     * alternative in {@link RNASecondaryStructureParser#ctline}.
     * 
     * @param ctx the parse tree
     */
    void exitCtLineUnpaired(
	    RNASecondaryStructureParser.CtLineUnpairedContext ctx);

    /**
     * Enter a parse tree produced by the {@code ctLineBond} labeled
     * alternative in {@link RNASecondaryStructureParser#ctline}.
     * 
     * @param ctx the parse tree
     */
    void enterCtLineBond(RNASecondaryStructureParser.CtLineBondContext ctx);

    /**
     * Exit a parse tree produced by the {@code ctLineBond} labeled
     * alternative in {@link RNASecondaryStructureParser#ctline}.
     * 
     * @param ctx the parse tree
     */
    void exitCtLineBond(RNASecondaryStructureParser.CtLineBondContext ctx);
}