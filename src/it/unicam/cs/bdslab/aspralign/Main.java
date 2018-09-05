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

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.cli.*;

import fr.orsay.lri.varna.models.treealign.*;

/**
 * Main class interacting with the user through command line options.
 * 
 * @author Luca Tesei
 *
 */
public class Main {

	public static void main(String[] args) {
		// Use Apache Commons CLI 1.4
		// create Options object for Command Line Definition
		Options options = new Options();

		// define command line options
		Option o1 = Option.builder("g").desc("Produce the algebraic RNA tree corresponding to the given structure")
				.longOpt("alg").hasArg().argName("input-file").build();
		options.addOption(o1);
		Option o2 = Option.builder("s").desc("Produce the structural RNA tree corresponding to the given structure")
				.longOpt("struct").hasArg().argName("input-file").build();
		options.addOption(o2);
		Option o3 = Option.builder("a").desc("Align two given structures producing alignment tree and distance")
				.longOpt("align").hasArgs().numberOfArgs(2).argName("input-file1 input-file2").build();
		options.addOption(o3);
		Option o4 = Option.builder("o").desc("Output result on the given file instead of standard output")
				.longOpt("out").hasArg().argName("output-file").build();
		options.addOption(o4);
		Option o5 = Option.builder("l").desc("Output in LaTeX format instead of linearised tree").longOpt("latexout")
				.build();
		options.addOption(o5);
		Option o6 = Option.builder("i").desc("Show license and other info").longOpt("info").build();
		options.addOption(o6);
		Option o7 = Option.builder("h").desc("Show usage information").longOpt("help").build();
		options.addOption(o7);
		Option o8 = Option.builder("r")
				.desc("Input Arc Annotated Sequence file(s) instead of Extended Dot-Bracket Notation file(s)")
				.longOpt("aasinput").build();
		options.addOption(o8);
		Option o9 = Option.builder("c")
				.desc("Check the presence of only standard Watson-Crick and wobble base pairing (disabled by default)")
				.longOpt("chkpair").build();
		options.addOption(o9);
		Option o10 = Option.builder("d").desc("Output only distance, no alignment tree (works only with option -a)")
				.longOpt("outdist").build();
		options.addOption(o10);
		Option o11 = Option.builder("e").desc("Show current values of edit scores used for alignment")
				.longOpt("showscores").build();
		options.addOption(o11);

		String launchCommand = "java -jar ASPRAlign.jar";
		String header = "\n\nASPRAling version 0.9 - Build Algebraic RNA Trees and Structural"
				+ " RNA Trees or align Structural RNA Trees of RNA secondary structures with or without any kind of pseudoknot. "
				+ "Default input file format is Extended Dot-Bracket Notation, "
				+ "see https://www.tbi.univie.ac.at/RNA/ViennaRNA/doc/html/rna_structure_notations.html "
				+ "Use option -r to use, instead, Arc Annotated Sequence format, similar to "
				+ "the the Extended Dot-Bracket Notation format in which the weak bonds are expressed as a "
				+ "list (i_1,j_1);(i_2,j_2); ... ;(i_m,j_m) where "
				+ "each index i_k, j_k belongs to the interval [1,n] (n is the length of the primary sequence) "
				+ "and i_k < j_k + " + "1 for all k. Default output is a linearised tree of the form "
				+ "(\"node-label\", [list-of-children]), use option -l to change to LaTeX output. "
				+ "The LaTeX code can be processed with LaTeX to produce a graphical representation "
				+ "of the tree in a pdf file. " + "Option -o for specifying output file is "
				+ "optional, if not specified the result is printed on the standard output.\n\n";
		String usageExamples = "Usage examples:\n\n>" + launchCommand + " -r -g aas1.txt -l -o "
				+ "aas1.tex\n\nProduce file aas1.tex containing the LaTeX code to draw the "
				+ "algebraic RNA tree corresponding to the RNA secondary structure given in the "
				+ "Arc Annotated Sequence file aas1.txt\n\n" + ">" + launchCommand
				+ " -a rna1.dbn.txt rna2.dbn.txt\n\nPrint on the standard "
				+ "output the linearised alignment tree of the two structural RNA trees corresponding to "
				+ "the two RNA secondary structures given in the Extended Dot-Bracket Notation files rna1.dbn.txt and rna2.dbn.txt\n\n";
		String copyRight = "ASPRAling Copyright (C) 2018 Michela Quadrini, Luca Tesei, "
				+ "Emanuela Merelli - BioShape and Data Science Lab at the University of Camerino,"
				+ " Italy - http://www.emanuelamerelli.eu/bigdata/\n\n";
		String shortNotice = "This program comes with ABSOLUTELY NO WARRANTY; for details use "
				+ "option '-i'. This is free software, and you are welcome to redistribute it "
				+ "under certain conditions; use option '-i' for more details.\n\n";
		String longNotice = "This program is free software: you can redistribute it and/or modify "
				+ "it under the terms of the GNU General Public License as published by "
				+ "the Free Software Foundation, either version 3 of the License, or " + "any later version.\n\n"
				+ "This program is distributed in the hope that it will be useful, "
				+ "but WITHOUT ANY WARRANTY; without even the implied warranty of "
				+ "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the "
				+ "GNU General Public License for more details.\n\n"
				+ "You should have received a copy of the GNU General Public License "
				+ "along with this program.  If not, see <http://www.gnu.org/licenses/>.\n\n";
		String report = "Please report any issue to luca.tesei@unicam.it or to Luca Tesei, "
				+ "Polo Informatico, via Madonna delle Carceri 9, 62032 Camerino (MC) Italy.";
		HelpFormatter formatter = new HelpFormatter();
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;
		try {
			cmd = parser.parse(options, args);
		} catch (ParseException e) {
			// oops, something went wrong
			System.out.println("ERROR: Command Line parsing failed.  Reason: " + e.getMessage() + "\n");
			formatter.printHelp(launchCommand, header, options, usageExamples + copyRight + shortNotice + report, true);
			System.exit(1);
		}

		// Manage option c
		boolean basePairsCheck = false;
		if (cmd.hasOption("c"))
			basePairsCheck = true;

		// Manage options g and s
		if (cmd.hasOption("g") || cmd.hasOption("s")) {
			boolean algebraic = cmd.hasOption("g");
			// read input file
			String inputFile;
			if (algebraic) {
				inputFile = cmd.getOptionValue("g");
			} else
				inputFile = cmd.getOptionValue("s");

			// Read the arc annotated sequence from the input file
			ArcAnnotatedSequence aas = null;
			try {
				// Manage option r
				if (cmd.hasOption("r"))
					aas = RNASecondaryStructureInputFileParser.readAasFile(inputFile, basePairsCheck);
				else
					aas = RNASecondaryStructureInputFileParser.readDotBracketNotationFile(inputFile, basePairsCheck);
			} catch (RNASecondaryStructureInputFileException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}

			// Construct the result tree
			Tree<String> t;

			// Construct Algebraic RNA tree
			AlgebraicRNATreeBuilder a = new AlgebraicRNATreeBuilder(aas);

			// Check if algebraic or structural
			if (algebraic)
				// get the algebraic RNA tree
				t = a.getAlgebraicRNATree();
			else {
				// get the structural RNA tree
				t = a.getStructuralRNATree();
			}

			// Produce Output
			String output;
			if (cmd.hasOption("l"))
				// produce LaTeX
				output = AlgebraicRNATreeOutputter.toLatex(t);
			else
				// produce linearised tree
				output = AlgebraicRNATreeOutputter.treeToString(t);

			// Write Output on proper file or on standard output
			if (cmd.hasOption("o")) {
				String outputFile = cmd.getOptionValue("o");
				try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, false))) {
					writer.write(output);
				} catch (FileNotFoundException e) {
					System.out.println("ERROR: Output file " + outputFile + " cannot be created.");
					System.exit(1);
				} catch (IOException e) {
					System.out.println("ERROR: Output file" + outputFile + " cannot be written.");
					System.exit(1);
				}
			} else
				System.out.println(output);
			return;
		}

		// Manage Option a
		if (cmd.hasOption("a")) {
			String[] inputFiles = cmd.getOptionValues("a");

			// variables for structural RNA trees
			Tree<String> t1 = null;
			Tree<String> t2 = null;

			// Read the arc annotated sequence from the input file 1
			ArcAnnotatedSequence aas = null;
			try {
				// Manage option r
				if (cmd.hasOption("r"))
					aas = RNASecondaryStructureInputFileParser.readAasFile(inputFiles[0], basePairsCheck);
				else
					aas = RNASecondaryStructureInputFileParser.readDotBracketNotationFile(inputFiles[0],
							basePairsCheck);
			} catch (RNASecondaryStructureInputFileException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}

			// Construct structural RNA tree 1
			AlgebraicRNATreeBuilder a1 = new AlgebraicRNATreeBuilder(aas);
			t1 = a1.getStructuralRNATree();

			// Read the arc annotated sequence from the input file 2
			try {
				// Manage option r
				if (cmd.hasOption("r"))
					aas = RNASecondaryStructureInputFileParser.readAasFile(inputFiles[1], basePairsCheck);
				else
					aas = RNASecondaryStructureInputFileParser.readDotBracketNotationFile(inputFiles[1],
							basePairsCheck);
			} catch (RNASecondaryStructureInputFileException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}

			// Construct structural RNA tree
			AlgebraicRNATreeBuilder a2 = new AlgebraicRNATreeBuilder(aas);
			t2 = a2.getStructuralRNATree();

			// t1 and t2 contain the two structural RNA trees to align
			StructuralRNATreeAlignResult r = null;
			try {
				r = new StructuralRNATreeAlignResult(t1, t2);
			} catch (TreeAlignException e) {
				System.out.println("ERROR: Alignment Exception: " + e.getMessage());
				System.exit(3);
			}

			// Produce Output
			if (!cmd.hasOption("d")) {
				Tree<AlignedNode<String, String>> t = r.getAlignedTree();
				String output;
				if (cmd.hasOption("l"))
					// produce LaTeX
					output = AlgebraicRNATreeOutputter.toLatexAligned(t);
				else
					// produce linearised tree
					output = AlgebraicRNATreeOutputter.treeToStringAligned(t);

				// Write Output on proper file or on standard output
				if (cmd.hasOption("o")) {
					String outputFile = cmd.getOptionValue("o");
					try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, false))) {
						writer.write(output);
					} catch (FileNotFoundException e) {
						System.out.println("ERROR: Output file " + outputFile + " cannot be created.");
						System.exit(1);
					} catch (IOException e) {
						System.out.println("ERROR: Output file" + outputFile + " cannot be written.");
						System.exit(1);
					}
				} else
					System.out.println(output + "\n");
			}
			// Output distance
			System.out.println("Distance = " + r.getDistance());
			return;
		}

		// Manage Option e
		if (cmd.hasOption("e")) {
			String scores = "ASPRAlign Current Scores:\n\nScore for Operator Insertion or Deletion = "
					+ EditScores.OPERATOR_INSERTION_OR_DELETION + "\nScore for Operator Replacement with Operator = "
					+ EditScores.OPERATOR_REPLACEMENT + "\nScore for Hairpin Insertion or Deletion = "
					+ EditScores.HAIRPIN_INSERTION_OR_DELETION + "\nScore for Hairpin Replacement with Hairpin = "
					+ EditScores.HAIRPIN_REPLACEMENT_WITH_HAIRPIN + "\nScore for Hairpin Replacement with Operator = "
					+ EditScores.HAIRPIN_REPLACEMENT_WITH_OPERATOR
					+ "\nScore for One Crossing Mismatch (Local Score) = " + EditScores.CROSSING_MISMATCH_SCORE;
			System.out.println(scores);
			return;
		}

		// Manage Option h
		if (cmd.hasOption("h")) {
			formatter.printHelp(launchCommand, header, options, usageExamples + copyRight + shortNotice + report, true);
			return;
		}

		// Manage Option i
		if (cmd.hasOption("i")) {
			Options optionsEmpty = new Options();
			formatter.printHelp(launchCommand, "", optionsEmpty,
					copyRight + longNotice + report + "\n\nUse option -h for full usage information", true);
			return;
		}

		// If no option is given, output usage
		formatter.printHelp(launchCommand, header, options, usageExamples + copyRight + shortNotice + report, true);
	}
}
