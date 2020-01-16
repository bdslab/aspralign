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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

//import javax.swing.JFileChooser;
//import javax.swing.JOptionPane;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import fr.orsay.lri.varna.models.treealign.Tree;
import fr.orsay.lri.varna.models.treealign.TreeAlignException;

/**
 * This class contains a main that runs the ASPRAlign comparison algorithm among
 * all the RNA secondary structures (with arbitrary pseudoknots) in a given
 * folder.
 * 
 * Two comma-separated-values files are produced with the description of the
 * processed files and the distance among all the pairs of molecules. Additional
 * information about the size of the molecules and the execution times is output
 * as well.
 * 
 * @author Luca Tesei
 *
 */
public class WorkbenchComparator {

	public static void main(String[] args) {
		// Use Apache Commons CLI 1.4
		// create Options object for Command Line Definition
		Options options = new Options();

		// define command line options
		Option o1 = Option.builder("f").desc("Process the files in the given folder").longOpt("input").hasArg()
				.argName("input-folder").required().build();
		options.addOption(o1);
		Option o2 = Option.builder("o").desc(
				"Output structure descriptions on file-1 and comparison results on file-2 instead of generating the default ouput files")
				.longOpt("output").hasArgs().numberOfArgs(2).argName("file-1 file-2").build();
		options.addOption(o2);
		Option o3 = Option.builder("i").desc("Show license and other info").longOpt("info").build();
		options.addOption(o3);
		Option o4 = Option.builder("h").desc("Show usage information").longOpt("help").build();
		options.addOption(o4);
		Option o5 = Option.builder("r")
				.desc("Input Arc Annotated Sequence file(s) instead of Extended Dot-Bracket Notation file(s)")
				.longOpt("aasinput").build();
		options.addOption(o5);
		Option o6 = Option.builder("c")
				.desc("Check the presence of only standard Watson-Crick and wobble base pairing (disabled by default)")
				.longOpt("chkpair").build();
		options.addOption(o6);
		Option o7 = Option.builder("e").desc("Show current values of edit scores used for alignment")
				.longOpt("showscores").build();
		options.addOption(o7);
		Option o8 = Option.builder("n").desc("Use the specified configuration file instead of the default one")
				.longOpt("useconffile").hasArg().argName("conf-file").build();
		options.addOption(o8);

		// Parse command line
		HelpFormatter formatter = new HelpFormatter();
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;
		try {
			cmd = parser.parse(options, args);
		} catch (ParseException e) {
			// oops, something went wrong
			System.err.println("ERROR: Command Line parsing failed.  Reason: " + e.getMessage() + "\n");
			formatter.printHelp(CommandLineMessages.LAUNCH_COMMAND_WB, CommandLineMessages.HEADER_WB, options,
					CommandLineMessages.USAGE_EXAMPLES_WB + CommandLineMessages.COPYRIGHT
							+ CommandLineMessages.SHORT_NOTICE + CommandLineMessages.REPORT_TO,
					true);
			System.exit(1);
		}

		// Manage Option h
		if (cmd.hasOption("h")) {
			formatter.printHelp(CommandLineMessages.LAUNCH_COMMAND_WB, CommandLineMessages.HEADER_WB, options,
					CommandLineMessages.USAGE_EXAMPLES_WB + CommandLineMessages.COPYRIGHT
							+ CommandLineMessages.SHORT_NOTICE + CommandLineMessages.REPORT_TO,
					true);
			return;
		}

		// Manage Option i
		if (cmd.hasOption("i")) {
			Options optionsEmpty = new Options();
			formatter
					.printHelp(CommandLineMessages.LAUNCH_COMMAND_WB, "", optionsEmpty,
							CommandLineMessages.COPYRIGHT + CommandLineMessages.LONG_NOTICE
									+ CommandLineMessages.REPORT_TO + "\n\nUse option -h for full usage information",
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
			String scores = "ASPRAlign current costs:\n\n" + "Cost for Operator Insertion = "
					+ f.getInsertOperatorCost() + "\nCost for Operator Deletion = " + f.getDeleteOperatorCost()
					+ "\nCost for Operator Replacement with Operator = " + f.getReplaceOperatorCost()
					+ "\nCost for Hairpin Insertion = " + f.getInsertHairpinCost() + "\nCost for Hairpin Deletion = "
					+ f.getDeleteHairpinCost() + "\nCost for One Crossing Mismatch (Local Cost) = "
					+ f.getCrossingMismatchCost();
			System.out.println(scores);
			return;
		}

		// Manage option c
		boolean basePairsCheck = false;
		if (cmd.hasOption("c"))
			basePairsCheck = true;

		// Manage option f
		if (cmd.hasOption("f")) {
			// Process a folder

			// File from command line
			File inputDirectory = new File(cmd.getOptionValue("f"));
			// Variables for counting execution time
			long startTimeNano = 0;
			long elapsedTimeNano = 0;
			// Map for holding all the structures to be processed
			Map<File, ASPRATree> structures = new HashMap<File, ASPRATree>();
			// List for holding all the structures files
			List<File> structuresList = new ArrayList<File>();
			// Set of skipped files
			Set<File> skippedFiles = new HashSet<File>();
			int numStructures = 1;

			// Process input files
			if (!inputDirectory.isDirectory()) {
				System.err.println("ERROR: Input file " + cmd.getOptionValue("f") + " is not a folder");
				System.exit(1);
			}
			File[] fs = inputDirectory.listFiles();
			// Filter only files and put them in the list
			for (int i = 0; i < fs.length; i++)
				if (!fs[i].isDirectory())
					structuresList.add(fs[i]);
				else
					System.err.println("WARNING: Skipping subfolder " + fs[i].getName() + " ...");
			
			// Order files to be processed
			Collections.sort(structuresList);

			// Output files creation
			PrintStream outputStream = null;
			PrintStream structuresStream = null;
			String outputStreamName = inputDirectory.getAbsolutePath() + "/" + "ASPRAlignComparisonResults.csv";
			String structuresStreamName = inputDirectory.getAbsolutePath() + "/" + "ASPRAlignProcessedStructures.csv";

			// Manage option "o"
			if (cmd.hasOption("o")) {
				String[] names = cmd.getOptionValues("o");
				structuresStreamName = names[0];
				outputStreamName = names[1];
			}

			try {
				outputStream = new PrintStream(new File(outputStreamName));
				structuresStream = new PrintStream(new File(structuresStreamName));
			} catch (FileNotFoundException e) {
				System.err.println("ERROR: creation of output file "
						+ (outputStream == null ? outputStreamName : structuresStreamName) + " failed");
				System.exit(3);
			}

			// Write column names on the csv output files
			structuresStream.println("Num,FileName,NumberOfNucleotides,NumberOfWeakBonds,"
					+ "IsPseudoknotted,TimeToGenerateStructuralRNATree[ns]");
			outputStream.println("FileName1,FileName2,ASPRADistance,TimeToCalculateDistance[ns]");

			// Load configuration file for costs
			ScoringFunction f = new ScoringFunction(configurationFileName);

			// Main Loop
			ListIterator<File> extIt = structuresList.listIterator();
			while (extIt.hasNext()) {
				// Compare the next element with all the subsequent elements
				int currentExtIndex = extIt.nextIndex();
				// Process File 1
				File f1 = extIt.next();
				// Check if skipped
				if (skippedFiles.contains(f1))
					// skip this file
					continue;
				// Read the arc annotated sequence from the input file 1
				ArcAnnotatedSequence aas1 = null;
				try {
					// Manage option r
					if (cmd.hasOption("r"))
						aas1 = InputFileParser.readAasFile(f1.getPath(), basePairsCheck);
					else
						aas1 = InputFileParser.readDotBracketNotationFile(f1.getPath(), basePairsCheck);
				} catch (InputFileParserException e) {
					System.err.println("WARNING: Skipping file " + f1.getName() + " ... " + e.getMessage());
					// skip this structure
					skippedFiles.add(f1);
					continue;
				}

				// Retrieve the Structural RNA Tree for the structure 1
				ASPRATree art1 = null;
				Tree<String> t1 = null;
				// Check if this structure has already been processed
				if (!structures.containsKey(f1)) {
					// Create the Structural RNA Tree and put the object into the map
					art1 = new ASPRATree(aas1);
					// Build Structural RNA Tree and measure building time
					startTimeNano = System.nanoTime();
					t1 = art1.getStructuralRNATree();
					elapsedTimeNano = System.nanoTime() - startTimeNano;
					// Insert Object in map
					structures.put(f1, art1);
					// Output values in the structures output file
					structuresStream.println(numStructures + "," + "\"" + f1.getName() + "\","
							+ art1.getAas().getNucleotides().length() + "," + art1.getAas().getBonds().size() + ","
							+ (art1.getAas().isPseudoknotted() ? "Yes" : "No") + "," + elapsedTimeNano);
					numStructures++;
				} else {
					art1 = structures.get(f1);
					t1 = art1.getStructuralRNATree();
				}

				// Interior Loop - Compare structure 1 with all the subsequent ones
				ListIterator<File> intIt = structuresList.listIterator(currentExtIndex + 1);
				while (intIt.hasNext()) {
					// Process File 2
					File f2 = intIt.next();
					// Check if skipped
					if (skippedFiles.contains(f2))
						// skip this file
						continue;
					// Read the arc annotated sequence from the input file 2
					ArcAnnotatedSequence aas2 = null;
					try {
						// Manage option r
						if (cmd.hasOption("r"))
							aas2 = InputFileParser.readAasFile(f2.getPath(), basePairsCheck);
						else
							aas2 = InputFileParser.readDotBracketNotationFile(f2.getPath(), basePairsCheck);
					} catch (InputFileParserException e) {
						System.err.println("WARNING: Skipping file " + f2.getName() + " ... " + e.getMessage());
						// skip this structure
						skippedFiles.add(f2);
						continue;
					}

					// Retrieve the Structural RNA Tree for the structure 2
					ASPRATree art2 = null;
					Tree<String> t2 = null;
					// Check if this structure has already been processed
					if (!structures.containsKey(f2)) {
						// Create the Structural RNA Tree and put the object into the map
						art2 = new ASPRATree(aas2);
						// Build Structural RNA Tree and measure building time
						startTimeNano = System.nanoTime();
						t2 = art2.getStructuralRNATree();
						elapsedTimeNano = System.nanoTime() - startTimeNano;
						// Insert Object in map
						structures.put(f2, art2);
						// Output values in the structures output file
						structuresStream.println(numStructures + "," + "\"" + f2.getName() + "\","
								+ art2.getAas().getNucleotides().length() + "," + art2.getAas().getBonds().size() + ","
								+ (art2.getAas().isPseudoknotted() ? "Yes" : "No") + "," + elapsedTimeNano);
						numStructures++;
					} else {
						art2 = structures.get(f2);
						t2 = art2.getStructuralRNATree();
					}
					// Compare the two structural RNA Trees and determine the distance
					// t1 and t2 contain the two structural RNA trees to align
					ASPRAlignResult r = null;
					try {
						startTimeNano = System.nanoTime();
						r = new ASPRAlignResult(t1, t2, f);
						elapsedTimeNano = System.nanoTime() - startTimeNano;
					} catch (TreeAlignException e) {
						System.err.println("WARNING: Skipping the comparison of pair (" + f1.getName() + ","
								+ f2.getName() + ") ... " + "Alignment Exception: " + e.getMessage());
						// Skip this pair
						continue;
					}
					// Write the output file
					outputStream.println("\"" + f1.getName() + "\"," + "\"" + f2.getName() + "\"," + r.getDistance()
							+ "," + elapsedTimeNano);
					// End of Interior Loop
				}
				// End of External Loop
			}

			// Close streams
			structuresStream.close();
			outputStream.close();
			return;
		} // End Option f

		// If no option is given, output usage
		formatter.printHelp(CommandLineMessages.LAUNCH_COMMAND_WB, CommandLineMessages.HEADER_WB, options,
				CommandLineMessages.USAGE_EXAMPLES_WB + CommandLineMessages.COPYRIGHT + CommandLineMessages.SHORT_NOTICE
						+ CommandLineMessages.REPORT_TO,
				true);
	}

}