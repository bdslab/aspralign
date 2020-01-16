// Generated from RNASecondaryStructure.g4 by ANTLR 4.7.2

	package it.unicam.cs.bdslab.aspralign;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RNASecondaryStructureParser}.
 */
public interface RNASecondaryStructureListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RNASecondaryStructureParser#rna}.
	 * @param ctx the parse tree
	 */
	void enterRna(RNASecondaryStructureParser.RnaContext ctx);
	/**
	 * Exit a parse tree produced by {@link RNASecondaryStructureParser#rna}.
	 * @param ctx the parse tree
	 */
	void exitRna(RNASecondaryStructureParser.RnaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sequenceContinue}
	 * labeled alternative in {@link RNASecondaryStructureParser#sequence}.
	 * @param ctx the parse tree
	 */
	void enterSequenceContinue(RNASecondaryStructureParser.SequenceContinueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sequenceContinue}
	 * labeled alternative in {@link RNASecondaryStructureParser#sequence}.
	 * @param ctx the parse tree
	 */
	void exitSequenceContinue(RNASecondaryStructureParser.SequenceContinueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sequenceEnd}
	 * labeled alternative in {@link RNASecondaryStructureParser#sequence}.
	 * @param ctx the parse tree
	 */
	void enterSequenceEnd(RNASecondaryStructureParser.SequenceEndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sequenceEnd}
	 * labeled alternative in {@link RNASecondaryStructureParser#sequence}.
	 * @param ctx the parse tree
	 */
	void exitSequenceEnd(RNASecondaryStructureParser.SequenceEndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rnaEdbn}
	 * labeled alternative in {@link RNASecondaryStructureParser#structure}.
	 * @param ctx the parse tree
	 */
	void enterRnaEdbn(RNASecondaryStructureParser.RnaEdbnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rnaEdbn}
	 * labeled alternative in {@link RNASecondaryStructureParser#structure}.
	 * @param ctx the parse tree
	 */
	void exitRnaEdbn(RNASecondaryStructureParser.RnaEdbnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rnaAas}
	 * labeled alternative in {@link RNASecondaryStructureParser#structure}.
	 * @param ctx the parse tree
	 */
	void enterRnaAas(RNASecondaryStructureParser.RnaAasContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rnaAas}
	 * labeled alternative in {@link RNASecondaryStructureParser#structure}.
	 * @param ctx the parse tree
	 */
	void exitRnaAas(RNASecondaryStructureParser.RnaAasContext ctx);
	/**
	 * Enter a parse tree produced by the {@code edbnsContinue}
	 * labeled alternative in {@link RNASecondaryStructureParser#edbns}.
	 * @param ctx the parse tree
	 */
	void enterEdbnsContinue(RNASecondaryStructureParser.EdbnsContinueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code edbnsContinue}
	 * labeled alternative in {@link RNASecondaryStructureParser#edbns}.
	 * @param ctx the parse tree
	 */
	void exitEdbnsContinue(RNASecondaryStructureParser.EdbnsContinueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code edbnsEnd}
	 * labeled alternative in {@link RNASecondaryStructureParser#edbns}.
	 * @param ctx the parse tree
	 */
	void enterEdbnsEnd(RNASecondaryStructureParser.EdbnsEndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code edbnsEnd}
	 * labeled alternative in {@link RNASecondaryStructureParser#edbns}.
	 * @param ctx the parse tree
	 */
	void exitEdbnsEnd(RNASecondaryStructureParser.EdbnsEndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bondsContinue}
	 * labeled alternative in {@link RNASecondaryStructureParser#bonds}.
	 * @param ctx the parse tree
	 */
	void enterBondsContinue(RNASecondaryStructureParser.BondsContinueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bondsContinue}
	 * labeled alternative in {@link RNASecondaryStructureParser#bonds}.
	 * @param ctx the parse tree
	 */
	void exitBondsContinue(RNASecondaryStructureParser.BondsContinueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bondsEnd}
	 * labeled alternative in {@link RNASecondaryStructureParser#bonds}.
	 * @param ctx the parse tree
	 */
	void enterBondsEnd(RNASecondaryStructureParser.BondsEndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bondsEnd}
	 * labeled alternative in {@link RNASecondaryStructureParser#bonds}.
	 * @param ctx the parse tree
	 */
	void exitBondsEnd(RNASecondaryStructureParser.BondsEndContext ctx);
	/**
	 * Enter a parse tree produced by {@link RNASecondaryStructureParser#bond}.
	 * @param ctx the parse tree
	 */
	void enterBond(RNASecondaryStructureParser.BondContext ctx);
	/**
	 * Exit a parse tree produced by {@link RNASecondaryStructureParser#bond}.
	 * @param ctx the parse tree
	 */
	void exitBond(RNASecondaryStructureParser.BondContext ctx);
}