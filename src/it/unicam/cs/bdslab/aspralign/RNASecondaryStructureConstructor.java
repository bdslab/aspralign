/**
 * ASPRAlign - Algebraic Structural Pseudoknot RNA Alignment
 * 
 * Copyright (C) 2020 Luca Tesei, Michela Quadrini, Emanuela Merelli - 
 * BioShape and Data Science Lab at the University of Camerino, Italy - 
 * http://www.emanuelamerelli.eu/bigdata/
 *  
 * This file is part of ASPRAlign.
 * 
 * ASPRAlign is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * ASPRAlign is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with ASPRAlign. If not, see <http://www.gnu.org/licenses/>.
 */
package it.unicam.cs.bdslab.aspralign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import it.unicam.cs.bdslab.aspralign.RNASecondaryStructureParser.*;

/**
 * Construct an RNASecondaryStructure object while visiting an
 * RNASecondaryStructure Parse Tree generated with ANTLR 4.
 * 
 * @author Luca Tesei
 *
 */
public class RNASecondaryStructureConstructor extends RNASecondaryStructureBaseListener {
	private RNASecondaryStructure s;
	private StringBuffer sequenceBuffer;
	private StringBuffer edbnsBuffer;
	private StringBuffer descriptionBuffer;

	public RNASecondaryStructureConstructor() {
		this.s = new RNASecondaryStructure();
		this.sequenceBuffer = new StringBuffer();
		this.edbnsBuffer = new StringBuffer();
		this.descriptionBuffer = new StringBuffer();
	}

	/**
	 * @return the secondary structure
	 */
	public RNASecondaryStructure getS() {
		return s;
	}

	@Override
	public void enterSequenceContinue(SequenceContinueContext ctx) {
		// add this line of nucleotides to the already existing sequence because of the
		// right recursion of the parse tree
		this.sequenceBuffer.append(ctx.NUCLEOTIDES().getText());
	}

	@Override
	public void enterSequenceEnd(SequenceEndContext ctx) {
		// add this line of nucleotides to the already existing sequence because of the
		// right recursion of the parse tree and the call to the exit method
		this.sequenceBuffer.append(ctx.NUCLEOTIDES().getText());
		// assign the whole sequence to the RNASecondaryStructure
		this.s.sequence = this.sequenceBuffer.toString();
		// set the size of the structure to the length of the sequence
		this.s.size = this.s.sequence.length();
	}

	@Override
	public void enterEdbnsContinue(EdbnsContinueContext ctx) {
		String edbn = ctx.EDBN().getText();
		// Control if this part of string has been classified wrongly as EDBN while
		// originally it was a nucleotide part with non-recognised codes; in this case
		// throw an exception
		if (edbn.indexOf(".") == -1) {
			// there are no dots, check if there is at least one bracket
			if (edbn.indexOf("(") == -1 && edbn.indexOf(")") == -1 && edbn.indexOf("[") == -1 && edbn.indexOf("]") == -1
					&& edbn.indexOf("{") == -1 && edbn.indexOf("}") == -1) {
				// there are no brackets, check if the string is very short
				if (edbn.length() >= 5) {
					// ok, it is not considered edbn, the exception is thrown
					String m = "Line " + ctx.start.getLine() + " Character " + (ctx.start.getCharPositionInLine() + 1)
							+ ": " + "unrecognised nucleotide code in " + edbn;
					throw new RNAInputFileParserException(m);
				}
			}
		}
		// add this line of edbn to the already existing ones because of the
		// right recursion of the parse tree
		this.edbnsBuffer.append(edbn);
	}

	@Override
	public void enterEdbnsEnd(EdbnsEndContext ctx) {
		String edbn = ctx.EDBN().getText();
		// Control if this part of string has been classified wrongly as EDBN while
		// originally it was a nucleotide part with non-recognised codes; in this case
		// throw an exception
		if (edbn.indexOf(".") == -1) {
			// there are no dots, check if there is at least one bracket
			if (edbn.indexOf("(") == -1 && edbn.indexOf(")") == -1 && edbn.indexOf("[") == -1 && edbn.indexOf("]") == -1
					&& edbn.indexOf("{") == -1 && edbn.indexOf("}") == -1) {
				// there are no brackets, check if the string is very short
				if (edbn.length() >= 5) {
					// ok, it is not considered edbn, the exception is thrown
					String m = "Line " + ctx.start.getLine() + " Character " + (ctx.start.getCharPositionInLine() + 1)
							+ ": " + "unrecognised nucleotide code in " + edbn;
					throw new RNAInputFileParserException(m);
				}
			}
		}
		// add this line of edbn to the already existing ones because of the
		// right recursion of the parse tree
		this.edbnsBuffer.append(edbn);
		// check length of edbns wrt the size of the structure
		if (this.s.sequence == null)
			// set the size of the structure using the length of the edbns
			this.s.size = this.edbnsBuffer.length();
		else // the structure has a sequence, check if the length are the same
		if (this.edbnsBuffer.length() != this.s.size)
			throw new RNAInputFileParserException("Extended Dot-Bracket Notation Structure is of length "
					+ this.edbnsBuffer.length() + " while the sequence of nucleotides is of length " + this.s.size);
		// parse edbn and create weak bonds in the structure
		List<WeakBond> bonds = parseEDBN(this.edbnsBuffer.toString());
		// add all the bonds to the structure
		for (WeakBond wb : bonds)
			this.s.addBond(wb);
	}

	/*
	 * Parse an Extended Dot-Bracket Notation string and transform it into a list of
	 * weak bonds.
	 * 
	 * @param extendedDotBracketNotation the string of extended dot-bracket notation
	 * to convert
	 * 
	 * @return a list of the bonds in the given extended dot-bracket notation
	 * 
	 * @throws RNAInputFileParserException if the extended dot-bracket notation
	 * contains errors
	 */
	private static List<WeakBond> parseEDBN(String extendedDotBracketNotation) {
		List<WeakBond> bonds = new ArrayList<WeakBond>();
		// Parse the edbn string using stacks to push opening symbols and match them
		// with closing ones
		HashMap<Character, Stack<Integer>> stacks = new HashMap<>();
		for (int i = 0; i < extendedDotBracketNotation.length(); i++) {
			char c = extendedDotBracketNotation.charAt(i);
			Character oc = new Character(c);
			if (isOpeningChar(c)) {
				if (!stacks.containsKey(oc))
					stacks.put(oc, new Stack<Integer>());
				stacks.get(oc).push(new Integer(i));
			}
			if (isClosingChar(c)) {
				Character opening = new Character(getCorrespondingOpening(c));
				if (stacks.get(opening) == null || stacks.get(opening).isEmpty()) {
					throw new RNAInputFileParserException(
							"Extended dot-bracket notation parsing: closing character at position " + (i + 1)
									+ " does not have a corresponding opening character");
				}
				int leftPosition = stacks.get(opening).pop().intValue();
				// add this weak bond to bonds
				bonds.add(new WeakBond(leftPosition + 1, i + 1));
			}
			// skip the "."
		}
		// check mismatched closing symbols
		Set<Character> ks = stacks.keySet();
		for (Character c : ks)
			if (!stacks.get(c).isEmpty()) {
				String msg = "Extended dot-bracket notation parsing: " + stacks.get(c).size()
						+ " missing closing occurrence(s) of " + c + " symbol, left opening symbol(s) at position(s) ";
				for (Integer i : stacks.get(c))
					msg = msg + (i.intValue() + 1) + " ";
				throw new RNAInputFileParserException(msg);
			}
		// return
		return bonds;
	}

	/*
	 * Determine if the given character is a correct opening character of an
	 * extended dot-bracket notation string.
	 */
	private static boolean isOpeningChar(char c) {
		return c == '(' || c == '[' || c == '{' || c == '<' || Character.isUpperCase(c);
	}

	/*
	 * Determine if the given character is a correct closing character of an
	 * extended dot-bracket notation string.
	 */
	private static boolean isClosingChar(char c) {
		return c == ')' || c == ']' || c == '}' || c == '>' || Character.isLowerCase(c);
	}

	/*
	 * Given a closing character of an extended dot-bracket notation string, returns
	 * the corresponding opening character.
	 */
	private static char getCorrespondingOpening(char c) {
		switch (c) {
		case ')':
			return '(';
		case ']':
			return '[';
		case '}':
			return '{';
		case '>':
			return '<';
		default:
			return Character.toUpperCase(c);
		}
	}

	@Override
	public void enterBondsContinue(BondsContinueContext ctx) {
		// take the bond and add it to the structure
		int left = Integer.parseInt(ctx.bond().INDEX(0).getText());
		int right = Integer.parseInt(ctx.bond().INDEX(1).getText());
		this.s.addBond(new WeakBond(left, right));
	}

	@Override
	public void enterBondsEnd(BondsEndContext ctx) {
		// take the bond and add it to the structure
		int left = Integer.parseInt(ctx.bond().INDEX(0).getText());
		int right = Integer.parseInt(ctx.bond().INDEX(1).getText());
		this.s.addBond(new WeakBond(left, right));
	}

	@Override
	public void exitEdbnOrAasFormat(EdbnOrAasFormatContext ctx) {
		// everything has been added to the structure, finalise it
		this.s.finalise();
	}

	@Override
	public void enterBpseq(BpseqContext ctx) {
		// save the four lines of description as EDBN file format comments
		this.descriptionBuffer.append("# " + ctx.LINE1BPSEQCT().getText().trim() + "\n");
		this.descriptionBuffer.append("# " + ctx.LINE2BPSEQCT().getText().trim() + "\n");
		this.descriptionBuffer.append("# " + ctx.LINE3BPSEQCT().getText().trim() + "\n");
		this.descriptionBuffer.append("# " + ctx.LINE4BPSEQCT().getText().trim());
	}

	@Override
	public void enterBpseqLineUnpaired(BpseqLineUnpairedContext ctx) {
		// add the current nucleotide to the sequence
		this.sequenceBuffer.append(ctx.IUPAC_CODE().getText().trim());
	}

	@Override
	public void enterBpseqLineBond(BpseqLineBondContext ctx) {
		// add the current nucleotide to the sequence
		this.sequenceBuffer.append(ctx.IUPAC_CODE().getText().trim());
		// determines the indexes of this bond
		int left = Integer.parseInt(ctx.INDEX(0).getText());
		int right = Integer.parseInt(ctx.INDEX(1).getText());
		if (left < right) {
			// only add the bond once, when it is first introduced
			this.s.addBond(new WeakBond(left, right));
		}
	}

	@Override
	public void exitBpseqFormat(BpseqFormatContext ctx) {
		// assign the whole sequence description to the RNASecondaryStructure
		this.s.description = this.descriptionBuffer.toString();
		// assign the whole sequence to the RNASecondaryStructure
		this.s.sequence = this.sequenceBuffer.toString();
		// set the size of the structure to the length of the sequence
		this.s.size = this.s.sequence.length();
		// everything has been added to the structure, finalise it
		this.s.finalise();
	}

	@Override
	public void enterCt(CtContext ctx) {
		// save the five lines of description as EDBN file format comments
		this.descriptionBuffer.append("# " + ctx.LINE1BPSEQCT().getText().trim() + "\n");
		this.descriptionBuffer.append("# " + ctx.LINE2BPSEQCT().getText().trim() + "\n");
		this.descriptionBuffer.append("# " + ctx.LINE3BPSEQCT().getText().trim() + "\n");
		this.descriptionBuffer.append("# " + ctx.LINE4BPSEQCT().getText().trim() + "\n");
		this.descriptionBuffer.append("# " + ctx.LINE5CT().getText().trim());
	}

	@Override
	public void enterCtLineUnpaired(CtLineUnpairedContext ctx) {
		// add the current nucleotide to the sequence
		this.sequenceBuffer.append(ctx.IUPAC_CODE().getText().trim());
	}

	@Override
	public void enterCtLineBond(CtLineBondContext ctx) {
		// add the current nucleotide to the sequence
		this.sequenceBuffer.append(ctx.IUPAC_CODE().getText().trim());
		// determines the indexes of this bond
		int left = Integer.parseInt(ctx.INDEX(0).getText());
		int right = Integer.parseInt(ctx.getChild(4).getText());
		if (left < right) {
			// only add the bond once, when it is first introduced
			this.s.addBond(new WeakBond(left, right));
		}
	}

	@Override
	public void exitCtFormat(CtFormatContext ctx) {
		// assign the whole sequence description to the RNASecondaryStructure
		this.s.description = this.descriptionBuffer.toString();
		// assign the whole sequence to the RNASecondaryStructure
		this.s.sequence = this.sequenceBuffer.toString();
		// set the size of the structure to the length of the sequence
		this.s.size = this.s.sequence.length();
		// everything has been added to the structure, finalise it
		this.s.finalise();
	}

}
