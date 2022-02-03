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

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * Container class for the static method to read an RNA secondary structure
 * from a file using ANTLR 4.
 * 
 * @author Luca Tesei
 *
 */
public class RNASecondaryStructureFileReader {

    /**
     * Use ANTLR 4 and the grammar defined for RNA Secondary Structures to
     * read a secondary structure from a file.
     * 
     * @param fileName       the name of the file to read
     * @param basePairsCheck flag to indicate if the base pairs should be
     *                       checked
     * @throws IOException                 if the specified file cannot be
     *                                     read
     * @throws RNAInputFileParserException if the file contains lexical or
     *                                     syntax errors in the definition of
     *                                     the secondary structure
     */
    public static RNASecondaryStructure readStructure(String fileName,
	    boolean basePairsCheck) throws IOException {
	// create a CharStream that reads from the input file
	CharStream input = CharStreams.fromFileName(fileName);
	// create a lexer that feeds off of input CharStream
	RNASecondaryStructureLexer lexer = new RNASecondaryStructureLexer(
		input);
	// create a buffer of tokens pulled from the lexer
	CommonTokenStream tokens = new CommonTokenStream(lexer);
	// create a parser that feeds off the tokens buffer
	RNASecondaryStructureParser structureParser = new RNASecondaryStructureParser(
		tokens);
	// remove default error listeners
	structureParser.removeErrorListeners();
	// add an error listener that throws an RNAInputFileParserException
	// upon syntax errors
	structureParser.addErrorListener(
		new RNASecondaryStructureFileReaderErrorListener());
	// begin parsing at rna rule
	ParseTree tree = structureParser.rna();
	// Create a generic parse tree walker that can trigger callbacks
	ParseTreeWalker walker = new ParseTreeWalker();
	// Create the specialised listener for the RNA secondary structure
	RNASecondaryStructureConstructor constructor = new RNASecondaryStructureConstructor();
	// Walk the tree created during the parse, trigger callbacks
	walker.walk(constructor, tree);
	// Get the parsed secondary structure
	RNASecondaryStructure secondaryStructure = constructor.getS();
	// check base pairs if needed
	if (basePairsCheck)
	    secondaryStructure.checkBasePairs();
	// Return the structure
	return secondaryStructure;
    }
}
