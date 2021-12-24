// Generated from RNASecondaryStructure.g4 by ANTLR 4.4

	package it.unicam.cs.bdslab.aspralign;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RNASecondaryStructureParser}.
 */
public interface RNASecondaryStructureListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code edbnOrAasFormat}
	 * labeled alternative in {@link RNASecondaryStructureParser#rna}.
	 * @param ctx the parse tree
	 */
	void enterEdbnOrAasFormat(@NotNull RNASecondaryStructureParser.EdbnOrAasFormatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code edbnOrAasFormat}
	 * labeled alternative in {@link RNASecondaryStructureParser#rna}.
	 * @param ctx the parse tree
	 */
	void exitEdbnOrAasFormat(@NotNull RNASecondaryStructureParser.EdbnOrAasFormatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bpseqSeq}
	 * labeled alternative in {@link RNASecondaryStructureParser#bpseqinfo}.
	 * @param ctx the parse tree
	 */
	void enterBpseqSeq(@NotNull RNASecondaryStructureParser.BpseqSeqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bpseqSeq}
	 * labeled alternative in {@link RNASecondaryStructureParser#bpseqinfo}.
	 * @param ctx the parse tree
	 */
	void exitBpseqSeq(@NotNull RNASecondaryStructureParser.BpseqSeqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ctLineUnpaired}
	 * labeled alternative in {@link RNASecondaryStructureParser#ctline}.
	 * @param ctx the parse tree
	 */
	void enterCtLineUnpaired(@NotNull RNASecondaryStructureParser.CtLineUnpairedContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ctLineUnpaired}
	 * labeled alternative in {@link RNASecondaryStructureParser#ctline}.
	 * @param ctx the parse tree
	 */
	void exitCtLineUnpaired(@NotNull RNASecondaryStructureParser.CtLineUnpairedContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rnaEdbn}
	 * labeled alternative in {@link RNASecondaryStructureParser#structure}.
	 * @param ctx the parse tree
	 */
	void enterRnaEdbn(@NotNull RNASecondaryStructureParser.RnaEdbnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rnaEdbn}
	 * labeled alternative in {@link RNASecondaryStructureParser#structure}.
	 * @param ctx the parse tree
	 */
	void exitRnaEdbn(@NotNull RNASecondaryStructureParser.RnaEdbnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ctFormat}
	 * labeled alternative in {@link RNASecondaryStructureParser#rna}.
	 * @param ctx the parse tree
	 */
	void enterCtFormat(@NotNull RNASecondaryStructureParser.CtFormatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ctFormat}
	 * labeled alternative in {@link RNASecondaryStructureParser#rna}.
	 * @param ctx the parse tree
	 */
	void exitCtFormat(@NotNull RNASecondaryStructureParser.CtFormatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sequenceContinue}
	 * labeled alternative in {@link RNASecondaryStructureParser#sequence}.
	 * @param ctx the parse tree
	 */
	void enterSequenceContinue(@NotNull RNASecondaryStructureParser.SequenceContinueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sequenceContinue}
	 * labeled alternative in {@link RNASecondaryStructureParser#sequence}.
	 * @param ctx the parse tree
	 */
	void exitSequenceContinue(@NotNull RNASecondaryStructureParser.SequenceContinueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bpseqLineUnpaired}
	 * labeled alternative in {@link RNASecondaryStructureParser#bpseqline}.
	 * @param ctx the parse tree
	 */
	void enterBpseqLineUnpaired(@NotNull RNASecondaryStructureParser.BpseqLineUnpairedContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bpseqLineUnpaired}
	 * labeled alternative in {@link RNASecondaryStructureParser#bpseqline}.
	 * @param ctx the parse tree
	 */
	void exitBpseqLineUnpaired(@NotNull RNASecondaryStructureParser.BpseqLineUnpairedContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ctSeq}
	 * labeled alternative in {@link RNASecondaryStructureParser#ctinfo}.
	 * @param ctx the parse tree
	 */
	void enterCtSeq(@NotNull RNASecondaryStructureParser.CtSeqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ctSeq}
	 * labeled alternative in {@link RNASecondaryStructureParser#ctinfo}.
	 * @param ctx the parse tree
	 */
	void exitCtSeq(@NotNull RNASecondaryStructureParser.CtSeqContext ctx);
	/**
	 * Enter a parse tree produced by {@link RNASecondaryStructureParser#bpseq}.
	 * @param ctx the parse tree
	 */
	void enterBpseq(@NotNull RNASecondaryStructureParser.BpseqContext ctx);
	/**
	 * Exit a parse tree produced by {@link RNASecondaryStructureParser#bpseq}.
	 * @param ctx the parse tree
	 */
	void exitBpseq(@NotNull RNASecondaryStructureParser.BpseqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rnaAas}
	 * labeled alternative in {@link RNASecondaryStructureParser#structure}.
	 * @param ctx the parse tree
	 */
	void enterRnaAas(@NotNull RNASecondaryStructureParser.RnaAasContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rnaAas}
	 * labeled alternative in {@link RNASecondaryStructureParser#structure}.
	 * @param ctx the parse tree
	 */
	void exitRnaAas(@NotNull RNASecondaryStructureParser.RnaAasContext ctx);
	/**
	 * Enter a parse tree produced by {@link RNASecondaryStructureParser#bond}.
	 * @param ctx the parse tree
	 */
	void enterBond(@NotNull RNASecondaryStructureParser.BondContext ctx);
	/**
	 * Exit a parse tree produced by {@link RNASecondaryStructureParser#bond}.
	 * @param ctx the parse tree
	 */
	void exitBond(@NotNull RNASecondaryStructureParser.BondContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bpseqFormat}
	 * labeled alternative in {@link RNASecondaryStructureParser#rna}.
	 * @param ctx the parse tree
	 */
	void enterBpseqFormat(@NotNull RNASecondaryStructureParser.BpseqFormatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bpseqFormat}
	 * labeled alternative in {@link RNASecondaryStructureParser#rna}.
	 * @param ctx the parse tree
	 */
	void exitBpseqFormat(@NotNull RNASecondaryStructureParser.BpseqFormatContext ctx);
	/**
	 * Enter a parse tree produced by {@link RNASecondaryStructureParser#ct}.
	 * @param ctx the parse tree
	 */
	void enterCt(@NotNull RNASecondaryStructureParser.CtContext ctx);
	/**
	 * Exit a parse tree produced by {@link RNASecondaryStructureParser#ct}.
	 * @param ctx the parse tree
	 */
	void exitCt(@NotNull RNASecondaryStructureParser.CtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ctLast}
	 * labeled alternative in {@link RNASecondaryStructureParser#ctinfo}.
	 * @param ctx the parse tree
	 */
	void enterCtLast(@NotNull RNASecondaryStructureParser.CtLastContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ctLast}
	 * labeled alternative in {@link RNASecondaryStructureParser#ctinfo}.
	 * @param ctx the parse tree
	 */
	void exitCtLast(@NotNull RNASecondaryStructureParser.CtLastContext ctx);
	/**
	 * Enter a parse tree produced by the {@code edbnsContinue}
	 * labeled alternative in {@link RNASecondaryStructureParser#edbns}.
	 * @param ctx the parse tree
	 */
	void enterEdbnsContinue(@NotNull RNASecondaryStructureParser.EdbnsContinueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code edbnsContinue}
	 * labeled alternative in {@link RNASecondaryStructureParser#edbns}.
	 * @param ctx the parse tree
	 */
	void exitEdbnsContinue(@NotNull RNASecondaryStructureParser.EdbnsContinueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code edbnsEnd}
	 * labeled alternative in {@link RNASecondaryStructureParser#edbns}.
	 * @param ctx the parse tree
	 */
	void enterEdbnsEnd(@NotNull RNASecondaryStructureParser.EdbnsEndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code edbnsEnd}
	 * labeled alternative in {@link RNASecondaryStructureParser#edbns}.
	 * @param ctx the parse tree
	 */
	void exitEdbnsEnd(@NotNull RNASecondaryStructureParser.EdbnsEndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bondsEnd}
	 * labeled alternative in {@link RNASecondaryStructureParser#bonds}.
	 * @param ctx the parse tree
	 */
	void enterBondsEnd(@NotNull RNASecondaryStructureParser.BondsEndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bondsEnd}
	 * labeled alternative in {@link RNASecondaryStructureParser#bonds}.
	 * @param ctx the parse tree
	 */
	void exitBondsEnd(@NotNull RNASecondaryStructureParser.BondsEndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sequenceEnd}
	 * labeled alternative in {@link RNASecondaryStructureParser#sequence}.
	 * @param ctx the parse tree
	 */
	void enterSequenceEnd(@NotNull RNASecondaryStructureParser.SequenceEndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sequenceEnd}
	 * labeled alternative in {@link RNASecondaryStructureParser#sequence}.
	 * @param ctx the parse tree
	 */
	void exitSequenceEnd(@NotNull RNASecondaryStructureParser.SequenceEndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bondsContinue}
	 * labeled alternative in {@link RNASecondaryStructureParser#bonds}.
	 * @param ctx the parse tree
	 */
	void enterBondsContinue(@NotNull RNASecondaryStructureParser.BondsContinueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bondsContinue}
	 * labeled alternative in {@link RNASecondaryStructureParser#bonds}.
	 * @param ctx the parse tree
	 */
	void exitBondsContinue(@NotNull RNASecondaryStructureParser.BondsContinueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ctLineBond}
	 * labeled alternative in {@link RNASecondaryStructureParser#ctline}.
	 * @param ctx the parse tree
	 */
	void enterCtLineBond(@NotNull RNASecondaryStructureParser.CtLineBondContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ctLineBond}
	 * labeled alternative in {@link RNASecondaryStructureParser#ctline}.
	 * @param ctx the parse tree
	 */
	void exitCtLineBond(@NotNull RNASecondaryStructureParser.CtLineBondContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bpseqLast}
	 * labeled alternative in {@link RNASecondaryStructureParser#bpseqinfo}.
	 * @param ctx the parse tree
	 */
	void enterBpseqLast(@NotNull RNASecondaryStructureParser.BpseqLastContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bpseqLast}
	 * labeled alternative in {@link RNASecondaryStructureParser#bpseqinfo}.
	 * @param ctx the parse tree
	 */
	void exitBpseqLast(@NotNull RNASecondaryStructureParser.BpseqLastContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bpseqLineBond}
	 * labeled alternative in {@link RNASecondaryStructureParser#bpseqline}.
	 * @param ctx the parse tree
	 */
	void enterBpseqLineBond(@NotNull RNASecondaryStructureParser.BpseqLineBondContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bpseqLineBond}
	 * labeled alternative in {@link RNASecondaryStructureParser#bpseqline}.
	 * @param ctx the parse tree
	 */
	void exitBpseqLineBond(@NotNull RNASecondaryStructureParser.BpseqLineBondContext ctx);
}