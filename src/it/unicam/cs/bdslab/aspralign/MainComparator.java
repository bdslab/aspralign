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

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import fr.orsay.lri.varna.models.treealign.AlignedNode;
import fr.orsay.lri.varna.models.treealign.Tree;
import fr.orsay.lri.varna.models.treealign.TreeAlignException;

/**
 * MainComparator class interacting with the user through command line
 * options.
 * 
 * @author Luca Tesei
 *
 */
public class MainComparator {

    public static void main(String[] args) {
	// Use Apache Commons CLI 1.4
	// create Options object for Command Line Definition
	Options options = new Options();

	defineCommandLineOptions(options);

	// Parse command line
	HelpFormatter formatter = new HelpFormatter();
	CommandLineParser commandLineParser = new DefaultParser();
	CommandLine cmd = null;
	try {
	    cmd = commandLineParser.parse(options, args);
	} catch (ParseException e) {
	    // oops, something went wrong
	    System.err.println("ERROR: Command Line parsing failed.  Reason: "
		    + e.getMessage() + "\n");
	    formatter.printHelp(CommandLineMessages.LAUNCH_COMMAND,
		    CommandLineMessages.HEADER, options,
		    CommandLineMessages.USAGE_EXAMPLES
			    + CommandLineMessages.COPYRIGHT
			    + CommandLineMessages.SHORT_NOTICE
			    + CommandLineMessages.REPORT_TO,
		    true);
	    System.exit(1);
	}

	// Manage Option h
	if (cmd.hasOption("h")) {
	    formatter.printHelp(CommandLineMessages.LAUNCH_COMMAND,
		    CommandLineMessages.HEADER, options,
		    CommandLineMessages.USAGE_EXAMPLES
			    + CommandLineMessages.COPYRIGHT
			    + CommandLineMessages.SHORT_NOTICE
			    + CommandLineMessages.REPORT_TO,
		    true);
	    return;
	}

	// Manage Option i
	if (cmd.hasOption("i")) {
	    Options optionsEmpty = new Options();
	    formatter.printHelp(CommandLineMessages.LAUNCH_COMMAND, "",
		    optionsEmpty,
		    CommandLineMessages.COPYRIGHT
			    + CommandLineMessages.LONG_NOTICE
			    + CommandLineMessages.REPORT_TO
			    + "\n\nUse option -h for full usage information",
		    true);
	    return;
	}

	// Manage Option n
	String configurationFileName = ScoringFunction.DEFAULT_PROPERTY_FILE;
	if (cmd.hasOption("n")) {
	    configurationFileName = cmd.getOptionValue("n");
	}

	// Manage Option e
	if (cmd.hasOption("e")) {
	    ScoringFunction f = new ScoringFunction(configurationFileName);
	    String scores = "ASPRAlign current costs:\n\n"
		    + "Cost for Operator Insertion = "
		    + f.getInsertOperatorCost()
		    + "\nCost for Operator Deletion = "
		    + f.getDeleteOperatorCost()
		    + "\nCost for Operator Replacement with Operator = "
		    + f.getReplaceOperatorCost()
		    + "\nCost for Hairpin Insertion = "
		    + f.getInsertHairpinCost()
		    + "\nCost for Hairpin Deletion = "
		    + f.getDeleteHairpinCost()
		    + "\nCost for One Crossing Mismatch (Local Cost) = "
		    + f.getCrossingMismatchCost();
	    System.out.println(scores);
	    return;
	}

	// Manage option c
	boolean basePairsCheck = false;
	if (cmd.hasOption("c"))
	    basePairsCheck = true;

	// Manage options g and s
	if (cmd.hasOption("g") || cmd.hasOption("s")) {
	    boolean algebraic = cmd.hasOption("g");
	    // Determine input file
	    String inputFile;
	    if (algebraic) {
		inputFile = cmd.getOptionValue("g");
	    } else
		inputFile = cmd.getOptionValue("s");
	    // Parse the input file for the secondary structure
	    RNASecondaryStructure secondaryStructure = null;
	    try {
		secondaryStructure = RNASecondaryStructureFileReader
			.readStructure(inputFile, basePairsCheck);
	    } catch (IOException e) {
		System.err.println("Input File " + inputFile + " ERROR:"
			+ e.getMessage());
		System.exit(3);
	    } catch (RNAInputFileParserException e) {
		System.err.println("Input File " + inputFile + " ERROR: "
			+ e.getMessage());
		System.exit(2);
	    }
	    // Construct the ASPRATtree
	    Tree<String> t;
	    ASPRATree a = new ASPRATree(secondaryStructure);
	    // Check if algebraic or structural
	    if (algebraic) {
		// Check if the sequence was given in the input file
		if (secondaryStructure.sequence == null) {
		    System.err.println("Input File " + inputFile
			    + " ERROR: to create the algebraic RNA tree the "
			    + "sequence of nucleotides is needed. The input "
			    + "file did not contain the primary sequence.");
		    System.exit(2);
		}
		// get the algebraic RNA tree
		t = a.getAlgebraicRNATree();
	    } else {
		// get the structural RNA tree
		t = a.getStructuralRNATree();
	    }
	    // Produce Output
	    String output;
	    if (cmd.hasOption("l"))
		// produce LaTeX
		output = ASPRATreeOutputter.toLatex(t);
	    else
		// produce linearised tree
		output = ASPRATreeOutputter.treeToString(t);

	    // Write Output on proper file or on standard output
	    if (cmd.hasOption("o")) {
		String outputFile = cmd.getOptionValue("o");
		try (BufferedWriter writer = new BufferedWriter(
			new FileWriter(outputFile, false))) {
		    writer.write(output);
		} catch (FileNotFoundException e) {
		    System.err.println("ERROR: Output file " + outputFile
			    + " cannot be created.");
		    System.exit(3);
		} catch (IOException e) {
		    System.err.println("ERROR: Output file" + outputFile
			    + " cannot be written.");
		    System.exit(3);
		}
	    } else
		System.out.println(output);
	    return;
	}

	// Manage Option a
	if (cmd.hasOption("a")) {
	    // Get input file names
	    String[] inputFiles = cmd.getOptionValues("a");
	    // variables for structural RNA trees
	    Tree<String> t1 = null;
	    Tree<String> t2 = null;

	    // Parse the first input file for the secondary structure
	    RNASecondaryStructure secondaryStructure1 = null;
	    try {
		secondaryStructure1 = RNASecondaryStructureFileReader
			.readStructure(inputFiles[0], basePairsCheck);
	    } catch (IOException e) {
		System.err.println("Input File " + inputFiles[0] + " ERROR:"
			+ e.getMessage());
		System.exit(3);
	    } catch (RNAInputFileParserException e) {
		System.err.println("Input File " + inputFiles[0] + " ERROR: "
			+ e.getMessage());
		System.exit(2);
	    }
	    // Construct structural RNA tree 1
	    ASPRATree a1 = new ASPRATree(secondaryStructure1);
	    t1 = a1.getStructuralRNATree();

	    // Parse the second input file for the secondary structure
	    RNASecondaryStructure secondaryStructure2 = null;
	    try {
		secondaryStructure2 = RNASecondaryStructureFileReader
			.readStructure(inputFiles[1], basePairsCheck);
	    } catch (IOException e) {
		System.err.println("Input File " + inputFiles[1] + " ERROR:"
			+ e.getMessage());
		System.exit(3);
	    } catch (RNAInputFileParserException e) {
		System.err.println("Input File " + inputFiles[1] + " ERROR: "
			+ e.getMessage());
		System.exit(2);
	    }
	    // Construct structural RNA tree 2
	    ASPRATree a2 = new ASPRATree(secondaryStructure2);
	    t2 = a2.getStructuralRNATree();

	    // Align t1 and t2, which contain two structural RNA trees
	    ASPRAlignResult r = null;
	    ScoringFunction f = new ScoringFunction(configurationFileName);
	    try {
		r = new ASPRAlignResult(t1, t2, f);
	    } catch (TreeAlignException e) {
		System.err.println(
			"ERROR: Alignment Exception: " + e.getMessage());
		System.exit(4);
	    }

	    // Produce Output
	    if (!cmd.hasOption("d")) {
		Tree<AlignedNode<String, String>> t = r.getAlignedTree();
		String output;
		if (cmd.hasOption("l"))
		    // produce LaTeX
		    output = ASPRATreeOutputter.toLatexAligned(t);
		else
		    // produce linearised tree
		    output = ASPRATreeOutputter.treeToStringAligned(t);
		// Write Output on proper file or on standard output
		if (cmd.hasOption("o")) {
		    String outputFile = cmd.getOptionValue("o");
		    try (BufferedWriter writer = new BufferedWriter(
			    new FileWriter(outputFile, false))) {
			writer.write(output);
		    } catch (FileNotFoundException e) {
			System.err.println("ERROR: Output file " + outputFile
				+ " cannot be created.");
			System.exit(3);
		    } catch (IOException e) {
			System.err.println("ERROR: Output file" + outputFile
				+ " cannot be written.");
			System.exit(3);
		    }
		} else
		    System.out.println(output + "\n");
	    }
	    // Output distance
	    System.out.println("Distance = " + r.getDistance());
	    return;
	}

	// If no option is given, output usage
	System.err.println("No operation specified...");
	formatter.printHelp(CommandLineMessages.LAUNCH_COMMAND,
		CommandLineMessages.HEADER, options,
		CommandLineMessages.USAGE_EXAMPLES
			+ CommandLineMessages.COPYRIGHT
			+ CommandLineMessages.SHORT_NOTICE
			+ CommandLineMessages.REPORT_TO,
		true);
    }

    private static void defineCommandLineOptions(Options options) {
	// define command line options
	Option o1 = Option.builder("g").desc(
		"Produce the algebraic RNA tree corresponding to the given "
			+ "structure")
		.longOpt("alg").hasArg().argName("input-file").build();
	options.addOption(o1);
	Option o2 = Option.builder("s").desc(
		"Produce the structural RNA tree corresponding to the given "
			+ "structure")
		.longOpt("struct").hasArg().argName("input-file").build();
	options.addOption(o2);
	Option o3 = Option.builder("a").desc(
		"Align two given structures producing alignment tree and distance")
		.longOpt("align").hasArgs().numberOfArgs(2)
		.argName("input-file1 input-file2").build();
	options.addOption(o3);
	Option o4 = Option.builder("o").desc(
		"Output result on the given file instead of standard output")
		.longOpt("out").hasArg().argName("output-file").build();
	options.addOption(o4);
	Option o5 = Option.builder("l")
		.desc("Output in LaTeX format instead of linearised tree")
		.longOpt("latexout").build();
	options.addOption(o5);
	Option o6 = Option.builder("i").desc("Show license and other info")
		.longOpt("info").build();
	options.addOption(o6);
	Option o7 = Option.builder("h").desc("Show usage information")
		.longOpt("help").build();
	options.addOption(o7);
	Option o9 = Option.builder("c").desc(
		"Check the presence of only standard Watson-Crick and wobble "
			+ "base pairing (disabled by default)")
		.longOpt("chkpair").build();
	options.addOption(o9);
	Option o10 = Option.builder("d").desc(
		"Output only distance, no alignment tree (works only with "
			+ "option -a)")
		.longOpt("outdist").build();
	options.addOption(o10);
	Option o11 = Option.builder("e")
		.desc("Show current values of edit scores used for alignment")
		.longOpt("showscores").build();
	options.addOption(o11);
	Option o12 = Option.builder("n").desc(
		"Use the specified configuration file instead of the default one")
		.longOpt("useconffile").hasArg().argName("conf-file").build();
	options.addOption(o12);

    }
}
