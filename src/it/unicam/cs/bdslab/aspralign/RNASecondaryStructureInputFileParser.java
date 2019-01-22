/**
 * ASPRAlign - Algebraic Structural Pseudoknot RNA Alignment
 * 
 * Copyright (C) 2018 Luca Tesei, Michela Quadrini, Emanuela Merelli - 
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;
import java.util.Set;
import java.util.Stack;

/**
 * Functions for parsing dot bracket notation and arc annotated sequence text
 * input files. They are both transformed into an ArcAnnotatedSequence object.
 * 
 * @author Luca Tesei
 *
 */
public class RNASecondaryStructureInputFileParser {
	// IUPAC nucleotide codes
	private static String standardNucleotidesCodes = "A|C|G|U|a|c|g|u|T|t|R|r|Y|y|s|S|W|w|K|k|M|m|B|b|D|d|H|h|V|v|N|n|\\.|\\-";
	private static String nonStandardNucleotidesCodes = "\\\"|\\?|\\]|\\~|\\[|\\_|\\+|\\=|\\/|\\4|\\7";
	private static String nucleotidesRegexp = "(" + standardNucleotidesCodes + "|" + nonStandardNucleotidesCodes + ")+";
	private static String dotNotationRegexp = "(\\(|\\)|\\.|\\[|\\]|\\{|\\}|\\<|\\>|[a-zA-Z])+";

	public static ArcAnnotatedSequence readAasFile(String inputFile, boolean basePairsCheck)
			throws RNASecondaryStructureInputFileException {
		File f = new File(inputFile);
		StringBuffer nucleotides = new StringBuffer();
		StringBuffer bounds = new StringBuffer();
		String line = null;
		try (BufferedReader b = new BufferedReader(new FileReader(f))) {
			// The file has been opened, try to read first the sequence of nucleotides

			// skip initial comment or empty lines
			boolean stop = false;
			line = b.readLine();
			while (line != null && !stop)
				// check if comment or empty line
				if (line.trim().startsWith("#") || line.trim().isEmpty()) {
					// skip comment or empty line
					line = b.readLine();
				} else
					stop = true;
			if (line == null)
				throw new RNASecondaryStructureInputFileException(
						"ERROR: Input Arc Annotated Sequence file " + inputFile + " does not contain the string " + "of nucleotides.");

			// check if current line is a nucleotide string
			if (line.trim().matches(nucleotidesRegexp)) {
				// add to nucleotides
				nucleotides.append(line.trim());
			} else
				throw new RNASecondaryStructureInputFileException(
						"ERROR: Input Arc Annotated Sequence file " + inputFile + " does not contain the string " + "of nucleotides.");

			// check if there are other lines with nucleotides and add them
			stop = false;
			line = b.readLine();
			while (line != null && !stop)
				// check if nucleotide string
				if (line.trim().matches(nucleotidesRegexp)) {
					// add to nucleotides
					nucleotides.append(line.trim());
					line = b.readLine();
				} else
					stop = true;
			if (line == null)
				throw new RNASecondaryStructureInputFileException(
						"ERROR: Input Arc Annotated Sequence file " + inputFile + " does not contain the list of weak bonds");

			// skip further comment or empty lines
			stop = false;
			while (line != null && !stop)
				// check if comment or empty line
				if (line.trim().startsWith("#") || line.trim().isEmpty()) {
					// skip comment or empty line
					line = b.readLine();
				} else
					stop = true;
			if (line == null)
				throw new RNASecondaryStructureInputFileException(
						"ERROR: Input Arc Annotated Sequence file " + inputFile + " does not contain the list of weak bonds");

			// current line is not a comment, is not empty and does not contain nucleotides,
			// so
			// it should contain a (part of) the list of weak bounds

			bounds.append(line.trim());

			// check if there are other lines with bounds and add them
			stop = false;
			line = b.readLine();
			while (line != null && !stop)
				// check if not empty or comment
				if (!(line.trim().startsWith("#") || line.trim().isEmpty())) {
					// add to bounds
					bounds.append(line.trim());
					line = b.readLine();
				} else
					stop = true;

			// ignore the rest of the file
		} catch (FileNotFoundException e) {
			throw new RNASecondaryStructureInputFileException("ERROR: Input Arc Annotated Sequence file " + inputFile + " not found.");
		} catch (IOException e) {
			throw new RNASecondaryStructureInputFileException(
					"ERROR: Input Arc Annotated Sequence file " + inputFile + " cannot be read.");
		}

		// Nucleotides and Weak bounds have been read, try to construct the arc
		// annotated sequence
		ArcAnnotatedSequence aas = null;
		try {
			aas = new ArcAnnotatedSequence(nucleotides.toString(), bounds.toString(), basePairsCheck);
		} catch (IllegalArgumentException e) {
			throw new RNASecondaryStructureInputFileException(
					"ERROR: Input Arc Annotated Sequence file " + inputFile + ": " + e.getMessage());
		}
		return aas;
	}

	public static ArcAnnotatedSequence readDotBracketNotationFile(String inputFile, boolean basePairsCheck)
			throws RNASecondaryStructureInputFileException {
		File f = new File(inputFile);
		StringBuffer nucleotides = new StringBuffer();
		StringBuffer dotBracketNotation = new StringBuffer();
		String line = null;
		try (BufferedReader b = new BufferedReader(new FileReader(f))) {
			// The file has been opened, try to read first the sequence of nucleotides

			// skip initial comment or empty lines
			boolean stop = false;
			line = b.readLine();
			while (line != null && !stop)
				// check if comment or empty line
				if (line.trim().startsWith("#") || line.trim().isEmpty()) {
					// skip comment or empty line
					line = b.readLine();
				} else
					stop = true;
			if (line == null)
				throw new RNASecondaryStructureInputFileException("ERROR: Input Extended Dot-Bracket Notation file " + inputFile
						+ " does not contain the string " + "of nucleotides.");

			// check if current line is a nucleotide string
			if (line.trim().matches(nucleotidesRegexp)) {
				// add to nucleotides
				nucleotides.append(line.trim());
			} else
				throw new RNASecondaryStructureInputFileException("ERROR: Input Extended Dot-Bracket Notation file " + inputFile
						+ " does not contain the string " + "of nucleotides.");

			// check if there are other lines with nucleotides and add them
			stop = false;
			line = b.readLine();
			while (line != null && !stop)
				// check if nucleotide string
				if (line.trim().matches(nucleotidesRegexp)) {
					// add to nucleotides
					nucleotides.append(line.trim());
					line = b.readLine();
				} else
					stop = true;
			if (line == null)
				throw new RNASecondaryStructureInputFileException("ERROR: Input Extended Dot-Bracket Notation file " + inputFile
						+ " does not contain the dot bracket notation");

			// skip further comment or empty lines
			stop = false;
			while (line != null && !stop)
				// check if comment or empty line
				if (line.trim().startsWith("#") || line.trim().isEmpty()) {
					// skip comment or empty line
					line = b.readLine();
				} else
					stop = true;
			if (line == null)
				throw new RNASecondaryStructureInputFileException("ERROR: Input Extended Dot-Bracket Notation file " + inputFile
						+ " does not contain the dot bracket notation");

			// current line is not a comment, is not empty and does not contain nucleotides,
			// so
			// it should contain a (part of) the dot bracket notation
			if (line.trim().matches(dotNotationRegexp))
				dotBracketNotation.append(line.trim());
			else
				throw new RNASecondaryStructureInputFileException("ERROR: Input Extended Dot-Bracket Notation file " + inputFile
						+ " does not contain the dot bracket notation");

			// check if there are other lines with dot bracket notation and add them
			stop = false;
			line = b.readLine();
			while (line != null && !stop)
				// check if dot bracket notation
				if (line.trim().matches(dotNotationRegexp)) {
					// add to dotBracketNotation
					dotBracketNotation.append(line.trim());
					line = b.readLine();
				} else
					stop = true;

			// ignore the rest of the file
		} catch (FileNotFoundException e) {
			throw new RNASecondaryStructureInputFileException(
					"ERROR: Input Extended Dot-Bracket Notation file " + inputFile + " not found.");
		} catch (IOException e) {
			throw new RNASecondaryStructureInputFileException(
					"ERROR: Input Extended Dot-Bracket Notation file " + inputFile + " cannot be read.");
		}

		// Nucleotides and Dot Bracket Notation have been read
		// Transform Dot Bracket Notation into a list of weak bounds
		StringBuffer bounds = new StringBuffer();
		// Start parsing dbn
		String dbn = dotBracketNotation.toString();
		HashMap<Character, Stack<Integer>> stacks = new HashMap<>();
		for (int i = 0; i < dbn.length(); i++) {
			char c = dbn.charAt(i);
			Character oc = new Character(c);
			if (isOpeningChar(c)) {
				if (!stacks.containsKey(oc))
					stacks.put(oc, new Stack<Integer>());
				stacks.get(oc).push(new Integer(i));
			}
			if (isClosingChar(c)) {
				Character opening = new Character(getCorrespondingOpening(c));
				if (stacks.get(opening) == null || stacks.get(opening).isEmpty()) {
					throw new RNASecondaryStructureInputFileException("ERROR: Input Extended Dot-Bracket Notation file "
							+ inputFile + " - dot bracket closing character at position " + (i + 1)
							+ " does not have a corresponding opening character");
				}
				int leftPosition = stacks.get(opening).pop().intValue();
				// add weak bound pair to bounds
				bounds.append("(" + (leftPosition + 1) + "," + (i + 1) + ");");
			}
			// skip .
		}

		// check mismatched closing symbols
		Set<Character> ks = stacks.keySet();
		for (Character c : ks)
			if (!stacks.get(c).isEmpty()) {
				String msg = "ERROR: Input Extended Dot Bracket Notation file " + inputFile + " - mismatch: "
						+ stacks.get(c).size() + " missing closing occurrences of " + c
						+ " symbol - left opening symbols at positions: ";
				for (Integer i : stacks.get(c))
					msg = msg + (i.intValue() + 1) + " ";
				throw new RNASecondaryStructureInputFileException(msg);
			}
		// remove trailing ";" from bounds
		bounds.deleteCharAt(bounds.length() - 1);

		// Construct Arc Annotated Sequence
		ArcAnnotatedSequence aas = null;
		try {
			aas = new ArcAnnotatedSequence(nucleotides.toString(), bounds.toString(), basePairsCheck);
		} catch (IllegalArgumentException e) {
			throw new RNASecondaryStructureInputFileException(
					"ERROR: Input Extended Dot-Bracket Notation file " + inputFile + ": " + e.getMessage());
		}
		return aas;
	}

	private static boolean isOpeningChar(char c) {
		String cs = "" + c;
		return c == '(' || c == '[' || c == '{' || c == '<' || cs.matches("[A-Z]");
	}

	private static boolean isClosingChar(char c) {
		String cs = "" + c;
		return c == ')' || c == ']' || c == '}' || c == '>' || cs.matches("[a-z]");
	}

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

}
