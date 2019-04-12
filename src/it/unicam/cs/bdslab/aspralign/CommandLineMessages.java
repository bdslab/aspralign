package it.unicam.cs.bdslab.aspralign;

public interface CommandLineMessages {
	public static String VERSION = "0.91";

	public static String LAUNCH_COMMAND = "java -jar ASPRAlign.jar";
	public static String HEADER = "\n\nASPRAling Comparator version " + VERSION
			+ " - Build Algebraic RNA Trees and Structural RNA Trees or "
			+ "calculate ASPRA Distance by alignign Structural RNA Trees of "
			+ "RNA secondary structures with arbitrary pseudoknots. Default "
			+ "input file format is Extended Dot-Bracket Notation, see "
			+ "https://www.tbi.univie.ac.at/RNA/ViennaRNA/doc/html/rna_structure_notations.html "
			+ "With option -r Arc Annotated Sequence format is used instead, "
			+ "similar to the Extended Dot-Bracket Notation format in which the"
			+ " weak bonds are expressed as a list (i_1,j_1);(i_2,j_2); ... ;"
			+ "(i_m,j_m) where each index i_k, j_k belongs to the interval "
			+ "[1,n] (where n is the length of the primary sequence) and "
			+ "i_k < j_k + 1 for all k. Default output is a linearised tree of "
			+ "the form (\"node-label\", [list-of-children]), use option -l to "
			+ "change to LaTeX output. The LaTeX code can be processed with "
			+ "LaTeX to produce a graphical representation of the tree in a "
			+ "pdf file. Option -o for specifying output file is optional, if "
			+ "not specified the result is printed on the standard output.\n\n";
	public static String USAGE_EXAMPLES = "Usage examples:\n\n>" + LAUNCH_COMMAND
			+ " -r -g aas1.txt -l -o aas1.tex\n\nProduce file aas1.tex "
			+ "containing the LaTeX code to draw the algebraic RNA tree "
			+ "corresponding to the RNA secondary structure given in the " + "Arc Annotated Sequence file aas1.txt\n\n>"
			+ LAUNCH_COMMAND + " -a rna1.dbn.txt rna2.dbn.txt\n\nPrint on the standard "
			+ "output the linearised alignment tree of the two structural "
			+ "RNA trees corresponding to the two RNA secondary structures "
			+ "given in the Extended Dot-Bracket Notation files rna1.dbn.txt " + "and rna2.dbn.txt\n\n";

	public static String LAUNCH_COMMAND_WB = "java -jar ASPRAlignWorkbench.jar";
	// TODO modificare stringhe _WB
	public static String HEADER_WB = "\n\nASPRAling Workbench Comparator version " + VERSION
			+ " - Compare all the RNA secondary structures files in a given "
			+ "input folder by computing the ASPRA Distance between all possible "
			+ "pairs. All the files are expected by default to be in Extended "
			+ "Dot-Bracket Notation. Use option -r to input Arc Annotated "
			+ "Sequences instead. The output is given as two "
			+ "comma-separated values files describing the processed "
			+ "structures and containing the ASPRA Distance calculated "
			+ "for each pair of processed structures. By default the "
			+ "output files are put in the input folder. Use option -o"
			+ " file-1 file-2 to specifiy different output files.\n";
	public static String USAGE_EXAMPLES_WB = "Usage examples:\n\n>" + LAUNCH_COMMAND_WB
			+ " -f TestWorkBench1\n\nProcesses all the files in folder "
			+ "\"TestWorkBench1\". Each file is read as an RNA secondary "
			+ "structure with arbitrary pseudoknots in Extended Dot-Bracket "
			+ "Notation. Comma-separated values files \"ASPRAlignProcessedStructures.csv\" "
			+ "and \"ASPRAlignComparisonResults.csv\" are created in the folder "
			+ "\"TestWorkBench1\". The former contains the description of all "
			+ "the structures that were found and correctly processed. The "
			+ "latter contains, for each pair of processed structures, the "
			+ "ASPRA Distance between the two structures and execution time " + "information.\n\n" + LAUNCH_COMMAND_WB
			+ " -f TestWorkBench1 -o " + "stucts.csv cmpr.csv -n my-config.txt\n\nProcesses all the files "
			+ "in folder \"TestWorkBench1\" as above but produce the description"
			+ " of processed structures in file \"structs.csv\" and comparison "
			+ "results in file \"cmpr.csv\". Instead of using \"ASPRAling-config.txt\" "
			+ "default configuration file, use \"my-config.txt\" as configuration file.\n\n";

	public static String COPYRIGHT = "ASPRAling Copyright (C) 2018 Michela Quadrini, Luca Tesei, "
			+ "Emanuela Merelli - BioShape and Data Science Lab at the University of Camerino,"
			+ " Italy - http://www.emanuelamerelli.eu/bigdata/\n\n";
	public static String SHORT_NOTICE = "This program comes with ABSOLUTELY NO WARRANTY; for details use "
			+ "option '-i'. This is free software, and you are welcome to redistribute it "
			+ "under certain conditions; use option '-i' for more details.\n\n";
	public static String LONG_NOTICE = "This program is free software: you can redistribute it and/or modify "
			+ "it under the terms of the GNU General Public License as published by "
			+ "the Free Software Foundation, either version 3 of the License, or " + "any later version.\n\n"
			+ "This program is distributed in the hope that it will be useful, "
			+ "but WITHOUT ANY WARRANTY; without even the implied warranty of "
			+ "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the "
			+ "GNU General Public License for more details.\n\n"
			+ "You should have received a copy of the GNU General Public License "
			+ "along with this program.  If not, see <http://www.gnu.org/licenses/>.\n\n";
	public static String REPORT_TO = "Please report any issue to luca.tesei@unicam.it or to Luca Tesei, "
			+ "Polo Informatico, via Madonna delle Carceri 9, 62032 Camerino (MC) Italy.";

}
