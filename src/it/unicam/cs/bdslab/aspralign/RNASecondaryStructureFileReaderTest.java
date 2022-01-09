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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Class of JUnit 5 tests for the loading of input of files containing RNA
 * secondary structures in the formats that are accepted by ASPRAlign.
 * 
 * @author Luca Tesei
 *
 */
class RNASecondaryStructureFileReaderTest {

    @Test
    void testReadStructureRunningExample1() throws IOException {
	RNASecondaryStructure s1 = RNASecondaryStructureFileReader.readStructure("test/aas/running-example-1-aas.txt",
		false);
	assertTrue(s1.getSequence().equals("AAGACCUGCACGCUAGUU"));
	List<WeakBond> wb = new ArrayList<WeakBond>();
	wb.add(new WeakBond(4, 7));
	wb.add(new WeakBond(9, 12));
	wb.add(new WeakBond(3, 13));
	wb.add(new WeakBond(2, 14));
	wb.add(new WeakBond(8, 17));
	// System.out.println(s1.getBonds());
	assertTrue(s1.getBonds().equals(wb));
	RNASecondaryStructure s2 = RNASecondaryStructureFileReader
		.readStructure("test/aas-no-sequence/running-example-1-aas.txt", false);
	assertTrue(s2.getSequence() == null);
	assertTrue(s2.getBonds().equals(wb));
	RNASecondaryStructure s3 = RNASecondaryStructureFileReader.readStructure("test/edbn/running-example-1-dbn.txt",
		false);
	assertTrue(s3.getSequence().equals("AAGACCUGCACGCUAGUU"));
	assertTrue(s3.getBonds().equals(wb));
	RNASecondaryStructure s4 = RNASecondaryStructureFileReader
		.readStructure("test/edbn-no-sequence/running-example-1-dbn.txt", false);
	assertTrue(s4.getSequence() == null);
	assertTrue(s4.getBonds().equals(wb));
    }

    @Test
    void testReadStructureRunningExample2() throws IOException {
	RNASecondaryStructure s1 = RNASecondaryStructureFileReader.readStructure("test/aas/running-example-2-aas.txt",
		false);
	assertTrue(s1.getSequence().equals("AAGACCUGCACGCUAGUAGU"));
	List<WeakBond> wb = new ArrayList<WeakBond>();
	wb.add(new WeakBond(9, 12));
	wb.add(new WeakBond(3, 13));
	wb.add(new WeakBond(2, 14));
	wb.add(new WeakBond(11, 19));
	// System.out.println(s1.getBonds());
	assertTrue(s1.getBonds().equals(wb));
	RNASecondaryStructure s2 = RNASecondaryStructureFileReader
		.readStructure("test/aas-no-sequence/running-example-2-aas.txt", false);
	assertTrue(s2.getSequence() == null);
	assertTrue(s2.getBonds().equals(wb));
	RNASecondaryStructure s3 = RNASecondaryStructureFileReader.readStructure("test/edbn/running-example-2-dbn.txt",
		false);
	assertTrue(s3.getSequence().equals("AAGACCUGCACGCUAGUAGU"));
	assertTrue(s3.getBonds().equals(wb));
	RNASecondaryStructure s4 = RNASecondaryStructureFileReader
		.readStructure("test/edbn-no-sequence/running-example-2-dbn.txt", false);
	assertTrue(s4.getSequence() == null);
	assertTrue(s4.getBonds().equals(wb));
    }

    @Test
    void testReadStructureLargerExample1() throws IOException {
	RNASecondaryStructure s1 = RNASecondaryStructureFileReader.readStructure("test/aas/larger-example-1-aas.txt",
		false);
	assertTrue(s1.getSequence().equals("AAGAGCUAUUUCCCUUAAGGGGGCACUAUUGAACUCCAUGAAACCGGAU" + "UUGGCCCCGCGG"));
	List<WeakBond> wb = new ArrayList<WeakBond>();
	wb.add(new WeakBond(4, 7));
	wb.add(new WeakBond(3, 13));
	wb.add(new WeakBond(2, 15));
	wb.add(new WeakBond(10, 18));
	wb.add(new WeakBond(22, 24));
	wb.add(new WeakBond(21, 26));
	wb.add(new WeakBond(29, 33));
	wb.add(new WeakBond(31, 34));
	wb.add(new WeakBond(28, 35));
	wb.add(new WeakBond(20, 44));
	wb.add(new WeakBond(36, 47));
	wb.add(new WeakBond(41, 50));
	wb.add(new WeakBond(53, 59));
	wb.add(new WeakBond(55, 61));
	// System.out.println(s1.getBonds());
	assertTrue(s1.getBonds().equals(wb));
	RNASecondaryStructure s2 = RNASecondaryStructureFileReader
		.readStructure("test/aas-no-sequence/larger-example-1-aas.txt", false);
	assertTrue(s2.getSequence() == null);
	assertTrue(s2.getBonds().equals(wb));
	RNASecondaryStructure s3 = RNASecondaryStructureFileReader.readStructure("test/edbn/larger-example-1-dbn.txt",
		false);
	assertTrue(s3.getSequence().equals("AAGAGCUAUUUCCCUUAAGGGGGCACUAUUGAACUCCAUGAAACCGGAU" + "UUGGCCCCGCGG"));
	assertTrue(s3.getBonds().equals(wb));
	RNASecondaryStructure s4 = RNASecondaryStructureFileReader
		.readStructure("test/edbn-no-sequence/larger-example-1-dbn.txt", false);
	assertTrue(s4.getSequence() == null);
	assertTrue(s4.getBonds().equals(wb));
    }

    @Test
    void testReadStructureLargerExample2() throws IOException {
	RNASecondaryStructure s1 = RNASecondaryStructureFileReader.readStructure("test/aas/larger-example-2-aas.txt",
		false);
	assertTrue(s1.getSequence().equals("GCACGAUCGCCAAUGGAUUGUCAUUUCUGGGAAUUUGAUGGACCUUGGAA" + "AAUGCAUU"));
	List<WeakBond> wb = new ArrayList<WeakBond>();
	wb.add(new WeakBond(5, 11));
	wb.add(new WeakBond(8, 16));
	wb.add(new WeakBond(3, 18));
	wb.add(new WeakBond(13, 21));
	wb.add(new WeakBond(27, 29));
	wb.add(new WeakBond(26, 33));
	wb.add(new WeakBond(30, 35));
	wb.add(new WeakBond(39, 42));
	wb.add(new WeakBond(40, 44));
	wb.add(new WeakBond(38, 45));
	wb.add(new WeakBond(37, 46));
	wb.add(new WeakBond(24, 49));
	wb.add(new WeakBond(47, 55));
	wb.add(new WeakBond(51, 58));
	// System.out.println(s1.getBonds());
	assertTrue(s1.getBonds().equals(wb));
	RNASecondaryStructure s2 = RNASecondaryStructureFileReader
		.readStructure("test/aas-no-sequence/larger-example-2-aas.txt", false);
	assertTrue(s2.getSequence() == null);
	assertTrue(s2.getBonds().equals(wb));
	RNASecondaryStructure s3 = RNASecondaryStructureFileReader.readStructure("test/edbn/larger-example-2-dbn.txt",
		false);
	assertTrue(s3.getSequence().equals("GCACGAUCGCCAAUGGAUUGUCAUUUCUGGGAAUUUGAUGGACCUUGGAA" + "AAUGCAUU"));
	assertTrue(s3.getBonds().equals(wb));
	RNASecondaryStructure s4 = RNASecondaryStructureFileReader
		.readStructure("test/edbn-no-sequence/larger-example-2-dbn.txt", false);
	assertTrue(s4.getSequence() == null);
	assertTrue(s4.getBonds().equals(wb));
    }

    @Test
    void testReadStructureLargerExample3() throws IOException {
	RNASecondaryStructure s1 = RNASecondaryStructureFileReader.readStructure("test/aas/larger-example-3-aas.txt",
		false);
	assertTrue(s1.getSequence().equals("AAGAGCUAUUUCCCUUAAGGGGGCACUAUUGAACUCCAUGAAACCGGAU" + "UUGGCCCCGCGG"));
	List<WeakBond> wb = new ArrayList<WeakBond>();
	wb.add(new WeakBond(4, 7));
	wb.add(new WeakBond(3, 13));
	wb.add(new WeakBond(1, 15));
	wb.add(new WeakBond(10, 18));
	wb.add(new WeakBond(22, 24));
	wb.add(new WeakBond(21, 26));
	wb.add(new WeakBond(29, 33));
	wb.add(new WeakBond(31, 34));
	wb.add(new WeakBond(28, 35));
	wb.add(new WeakBond(20, 44));
	wb.add(new WeakBond(36, 47));
	wb.add(new WeakBond(41, 50));
	wb.add(new WeakBond(53, 59));
	wb.add(new WeakBond(55, 61));
	// System.out.println(s1.getBonds());
	assertTrue(s1.getBonds().equals(wb));
	RNASecondaryStructure s2 = RNASecondaryStructureFileReader
		.readStructure("test/aas-no-sequence/larger-example-3-aas.txt", false);
	assertTrue(s2.getSequence() == null);
	assertTrue(s2.getBonds().equals(wb));
	RNASecondaryStructure s3 = RNASecondaryStructureFileReader.readStructure("test/edbn/larger-example-3-dbn.txt",
		false);
	assertTrue(s3.getSequence().equals("AAGAGCUAUUUCCCUUAAGGGGGCACUAUUGAACUCCAUGAAACCGGAU" + "UUGGCCCCGCGG"));
	assertTrue(s3.getBonds().equals(wb));
	RNASecondaryStructure s4 = RNASecondaryStructureFileReader
		.readStructure("test/edbn-no-sequence/larger-example-3-dbn.txt", false);
	assertTrue(s4.getSequence() == null);
	assertTrue(s4.getBonds().equals(wb));
    }

    @Test
    void testReadStructureRunningMismatchExample1() throws IOException {
	RNASecondaryStructure s1 = RNASecondaryStructureFileReader
		.readStructure("test/aas/running-example-mismatch-1-aas.txt", false);
	assertTrue(s1.getSequence().equals("UGGACGUGCACGUUAGUGG"));
	List<WeakBond> wb = new ArrayList<WeakBond>();
	wb.add(new WeakBond(2, 9));
	wb.add(new WeakBond(4, 13));
	wb.add(new WeakBond(11, 18));
	// System.out.println(s1.getBonds());
	assertTrue(s1.getBonds().equals(wb));
	RNASecondaryStructure s2 = RNASecondaryStructureFileReader
		.readStructure("test/aas-no-sequence/running-example-mismatch-1-aas.txt", false);
	assertTrue(s2.getSequence() == null);
	assertTrue(s2.getBonds().equals(wb));
	RNASecondaryStructure s3 = RNASecondaryStructureFileReader
		.readStructure("test/edbn/running-example-mismatch-1-dbn.txt", false);
	assertTrue(s3.getSequence().equals("UGGACGUGCACGUUAGUGG"));
	assertTrue(s3.getBonds().equals(wb));
	RNASecondaryStructure s4 = RNASecondaryStructureFileReader
		.readStructure("test/edbn-no-sequence/running-example-mismatch-1-dbn.txt", false);
	assertTrue(s4.getSequence() == null);
	assertTrue(s4.getBonds().equals(wb));
    }

    @Test
    void testReadStructureRunningMismatchExample2() throws IOException {
	RNASecondaryStructure s1 = RNASecondaryStructureFileReader
		.readStructure("test/aas/running-example-mismatch-2-aas.txt", false);
	assertTrue(s1.getSequence().equals("UGGACGUGCACGUUAGUGG"));
	List<WeakBond> wb = new ArrayList<WeakBond>();
	wb.add(new WeakBond(2, 9));
	wb.add(new WeakBond(4, 13));
	wb.add(new WeakBond(7, 16));
	// System.out.println(s1.getBonds());
	assertTrue(s1.getBonds().equals(wb));
	RNASecondaryStructure s2 = RNASecondaryStructureFileReader
		.readStructure("test/aas-no-sequence/running-example-mismatch-2-aas.txt", false);
	assertTrue(s2.getSequence() == null);
	assertTrue(s2.getBonds().equals(wb));
	RNASecondaryStructure s3 = RNASecondaryStructureFileReader
		.readStructure("test/edbn/running-example-mismatch-2-dbn.txt", false);
	assertTrue(s3.getSequence().equals("UGGACGUGCACGUUAGUGG"));
	assertTrue(s3.getBonds().equals(wb));
	RNASecondaryStructure s4 = RNASecondaryStructureFileReader
		.readStructure("test/edbn-no-sequence/running-example-mismatch-2-dbn.txt", false);
	assertTrue(s4.getSequence() == null);
	assertTrue(s4.getBonds().equals(wb));
    }

    @Test
    void testReadStructureDB1() throws IOException {
	RNASecondaryStructure s1 = RNASecondaryStructureFileReader.readStructure("test/edbn/CRW_00808.dp", false);
	assertTrue(s1.getSequence()
		.equals("AAAUAUAAUAAUAAUUAAAUUAUUAUUUUAGAGGAAUUAUAAAAUAUAGA"
			+ "GAAUAUGAUGUUAGUUCAGACUUAAUACUAUAUAGAGACAUAACACAUGC"
			+ "AAAUCAAACGAUUAUUUAAUUGAUAUAUAAUUAUAUAAUAUUAAUAUUAU"
			+ "AUAAUUCAAAUAAUAAUAAUUAAGUGGUGUAUCCGUGAGUAAUAAAUAGA"
			+ "AUUAGAACUAAUAUAUCUUUUAAAUGAGGAAUAAUAAAUAAAGAUAAUUA"
			+ "UUAAUUAAUUAUUGAUAUUAGUCAAGCUAUUAGCAGUAUAGGUAUUAUGU"
			+ "AGGAUAAUAGCCAUACAUAGCCGAUAAUCUGUAGCAAUUAAUUAACGUUA"
			+ "UAAUUGUCACGUCGAUUAUCAAAUACAAUCGAUAUCUAUAUGAUACAGCA"
			+ "GUGAGGAAUAUUGGGCUUAGAUCGAAAGAUUGACCCAGUUAUCUAUAAUA"
			+ "AUGAAAUUUCAAUUUUCUUAUUUAUUUUUUUUUAAAAUUUAAAUAUAGUA"
			+ "AAGAAAAAAUUGUUAUAAAUUAAAGUUAUAGACUAGCGAGAAUAAUGAUA"
			+ "GUAGCUAUCAUCAGAUUAUGAUGAAUAUAAAAUUUAUUUUUUUAUAUGUC"
			+ "GAUUAGUACCGAUUAAGAUAUGUGCCAGCAUUCGCGGUUAUACAUAGGGU"
			+ "GCAAGUAUUAGUUAUAUUGGUUUAAAGGAUCCGUAGAAUUUAUAUAUAAU"
			+ "AUAUAUAUAUAGAGUUAACAAUAGUAAUAAAUGAAUUAUAAUAGUAAUGA"
			+ "UGAAAUAUUAUGAUAAUUAUAGGACAGUCAAAAUUGAAAAUAUUUAUUAU"
			+ "AUGUUAACUGACAUUGAGGGAUGAAGGCUAAAUUAAUGAAAUGGAUUCGA"
			+ "UACCCAAGUAAUUUUAGCAGUAAACUAUGAGUACUGAAUAUUUAUAAUAA"
			+ "AUAAAUAUUUAAGAGAAAUCAAUAGUAUUCCACCAGGAGAGUAUAAUAAC"
			+ "UCAAUGGAAACCCAAUACAAUAGACGGUUAUAAUCAAUAGCAGUGGAGUA"
			+ "UGUUAUUUAAUUUGAUAAUCCCCAAAAAUCUUACCACAUCUUGAAUAUUA"
			+ "UCUUUAAUGAUAUUACAGGCGUUACAUAGUUGUCUUCAGUUCGUGCUGUG"
			+ "AAGUUUAAGGUUAGUUCCGUAAACGAACGCAUUCCUGUAUAAAUAUAUUA"
			+ "AUAUUUAUAAUUCUUUAUUUAAAUGCAUAAAGAGAAUAGGUUUAAGACAA"
			+ "AUCAUUAUGACCCUAAUGUUGUGGGCUAUAGACGUACUAUAAAAUAAUAU"
			+ "AUAAAAUUUAAUUAAAUAGUGAUAUUUAAUGAUAAAUACAACUUAUUAAA"
			+ "AUAUGUUAUAUAUUAAUAUAUAAUUUUAGUAUGAAUUAUAUCCUGUAAUU"
			+ "CGGAUAUAUGAAACAAGAAUUGCUAGUAAUCUGUAAUUAAGUAUAUUACG"
			+ "GUGAAUAUUAUUAAUUGUUUCGCACUAAUUACUCGUCACGCGUAAAUUAA"
			+ "AAUAAAUAACAAAAAUAAAUAUUAUAUUAUUAAUCUUUUAUUAUUUAAUA"
			+ "AUUGUUUUGAUUUUUUAAUUUUAUUUAGUUAUCUGUUUUAUAUUAUUGCG"
			+ "AAGUCGAAAUACAGUUACUGUAGGGGAACCUGCAGUGGGCUAUAACAAUA" + "CGAUUGGAUASPOM"));
	// System.out.println(s1.getBonds());
	RNASecondaryStructure s2 = RNASecondaryStructureFileReader.readStructure("test/edbn-no-sequence/CRW_00808.dp",
		false);
	assertTrue(s1.getBonds().equals(s2.getBonds()));
    }

    @Test
    void testReadStructureDB2() throws IOException {
	RNASecondaryStructure s1 = RNASecondaryStructureFileReader.readStructure("test/edbn/PDB_00408.txt", false);
	assertTrue(s1.getSequence()
		.equals("UGGAGAGUUUGAUCCUGGCUCAGGGUGAACGCUGGCGGCGUGCCUAAGAC"
			+ "AUGCAAGUCGUGCGGGCCGCGGGGUUUUACUCCGUGGUCAGCGGCGGACG"
			+ "GGUGAGUAACGCGUGGGUGACCUACCCGGAAGAGGGGGACAACCCGGGGA"
			+ "AACUCGGGCUAAUCCCCCAUGUGGACCCGCCCCUUGGGGUGUGUCCAAAG"
			+ "GGCUUUGCCCGCUUCCGGAUGGGCCCGCGUCCCAUCAGCUAGUUGGUGGG"
			+ "GUAAUGGCCCACCAAGGCGACGACGGGUAGCCGGUCUGAGAGGAUGGCCG"
			+ "GCCACAGGGGCACUGAGACACGGGCCCCACUCCUACGGGAGGCAGCAGUU"
			+ "AGGAAUCUUCCGCAAUGGGCGCAAGCCUGACGGAGCGACGCCGCUUGGAG"
			+ "GAAGAAGCCCUUCGGGGUGUAAACUCCUGAACCCGGGACGAAACCCCCGA"
			+ "CGAGGGGACUGACGGUACCGGGGUAAUAGCGCCGGCCAACUCCGUGCCAG"
			+ "CAGCCGCGGUAAUACGGAGGGCGCGAGCGUUACCCGGAUUCACUGGGCGU"
			+ "AAAGGGCGUGUAGGCGGCCUGGGGCGUCCCAUGUGAAAGACCACGGCUCA"
			+ "ACCGUGGGGGAGCGUGGGAUACGCUCAGGCUAGACGGUGGGAGAGGGUGG"
			+ "UGGAAUUCCCGGAGUAGCGGUGAAAUGCGCAGAUACCGGGAGGAACGCCG"
			+ "AUGGCGAAGGCAGCCACCUGGUCCACCCGUGACGCUGAGGCGCGAAAGCG"
			+ "UGGGGAGCAAACCGGAUUAGAUACCCGGGUAGUCCACGCCCUAAACGAUG"
			+ "CGCGCUAGGUCUCUGGGUCUCCUGGGGGCCGAAGCUAACGCGUUAAGCGC"
			+ "GCCGCCUGGGGAGUACGGCCGCAAGGCUGAAACUCAAAGGAAUUGACGGG"
			+ "GGCCCGCACAAGCGGUGGAGCAUGUGGUUUAAUUCGAAGCAACGCGAAGA"
			+ "ACCUUACCAGGCCUUGACAUGCUAGGGAACCCGGGUGAAAGCCUGGGGUG"
			+ "CCCCGCGAGGGGAGCCCUAGCACAGGUGCUGCAUGGCCGUCGUCAGCUCG"
			+ "UGCCGUGAGGUGUUGGGUUAAGUCCCGCAACGAGCGCAACCCCCGCCGUU"
			+ "AGUUGCCAGCGGUUCGGCCGGGCACUCUAACGGGACUGCCCGCGAAAGCG"
			+ "GGAGGAAGGAGGGGACGACGUCUGGUCAGCAUGGCCCUUACGGCCUGGGC"
			+ "GACACACGUGCUACAAUGCCCACUACAAAGCGAUGCCACCCGGCAACGGG"
			+ "GAGCUAAUCGCAAAAAGGUGGGCCCAGUUCGGAUUGGGGUCUGCAACCCG"
			+ "ACCCCAUGAAGCCGGAAUCGCUAGUAAUCGCGGAUCAGCCAUGCCGCGGU"
			+ "GAAUACGUUCCCGGGCCUUGUACACACCGCCCGUCACGCCAUGGGAGCGG"
			+ "GCUCUACCCGAAGUCGCCGGGAGCCUACGGGCAGGCGCCGAGGGUAGGGC"
			+ "CCGUGACUGGGGCGAAGUCGUAACAAGGUAGCUGUACCGGAAGGUGCGGC" + "UGGAUCAcuuUCU"));
	// System.out.println(s1.getBonds());
	RNASecondaryStructure s2 = RNASecondaryStructureFileReader.readStructure("test/edbn-no-sequence/PDB_00408.txt",
		false);
	assertTrue(s1.getBonds().equals(s2.getBonds()));
    }

    @Test
    void testReadStructureError() throws IOException {
	Throwable t1 = assertThrows(RNAInputFileParserException.class, () -> RNASecondaryStructureFileReader
		.readStructure("test/error-structures/CRW_00808_Modified.dp", false));
	System.err.println(
		"ASPRAlign Test Suite, Expected Exception while loading file \"test/error-structures/CRW_00808_Modified.dp\"");
	System.err.println(">>> Reported Error: " + t1.getMessage());
	System.err.println("");

	Throwable t2 = assertThrows(RNAInputFileParserException.class, () -> RNASecondaryStructureFileReader
		.readStructure("test/error-structures/PDB_00409_Modified.dp", false));
	System.err.println(
		"ASPRAlign Test Suite, Expected Exception while loading file \"test/error-structures/PDB_00409_Modified.dp\"");
	System.err.println(">>> Reported Error: " + t2.getMessage());
	System.err.println("");

	Throwable t3 = assertThrows(RNAInputFileParserException.class, () -> RNASecondaryStructureFileReader
		.readStructure("test/error-structures/PDB_00585_Modified.dp", false));
	System.err.println(
		"ASPRAlign Test Suite, Expected Exception while loading file \"test/error-structures/PDB_00585_Modified.dp\"");
	System.err.println(">>> Reported Error: " + t3.getMessage());
	System.err.println("");

	Throwable t4 = assertThrows(RNAInputFileParserException.class, () -> RNASecondaryStructureFileReader
		.readStructure("test/error-structures/PDB_00586_Modified.dp", false));
	System.err.println(
		"ASPRAlign Test Suite, Expected Exception while loading file \"test/error-structures/PDB_00586_Modified.dp\"");
	System.err.println(">>> Reported Error: " + t4.getMessage());
	System.err.println("");

	Throwable t5 = assertThrows(RNAInputFileParserException.class, () -> RNASecondaryStructureFileReader
		.readStructure("test/error-structures/PDB_00813_Modified.dp", false));

	System.err.println(
		"ASPRAlign Test Suite, Expected Exception while loading file \"test/error-structures/PDB_00813_Modified.dp\"");
	System.err.println(">>> Reported Error: " + t5.getMessage());
	System.err.println("");

	Throwable t6 = assertThrows(RNAInputFileParserException.class, () -> RNASecondaryStructureFileReader
		.readStructure("test/error-structures/PDB_00814_Modified.dp", false));
	System.err.println(
		"ASPRAlign Test Suite, Expected Exception while loading file \"test/error-structures/PDB_00814_Modified.dp\"");
	System.err.println(">>> Reported Error: " + t6.getMessage());
	System.err.println("");

	Throwable t7 = assertThrows(RNAInputFileParserException.class, () -> RNASecondaryStructureFileReader
		.readStructure("test/error-structures/PDB_00952_Modified.dp", true));
	System.err.println(
		"ASPRAlign Test Suite, Expected Exception while loading file \"test/error-structures/PDB_00952_Modified.dp\"");
	System.err.println(">>> Reported Error: " + t7.getMessage());
	System.err.println("");

	assertThrows(NoSuchFileException.class,
		() -> RNASecondaryStructureFileReader.readStructure("test/error-structures/no-file.dp", false));
    }

    @Test
    void testReadStructureBpseqTest() throws IOException {
	RNASecondaryStructure s1 = RNASecondaryStructureFileReader.readStructure("test/bpseq/test-bpseq.txt", false);
	assertTrue(s1.getSequence().equals("CCUGAACAG"));
	assertTrue(s1.getDescription()
		.equals("Filename: test.bpseq\n" + "Organism: Some organism\n" + "Accession Number: XYZ123\n"
			+ "Citation and related information available at http://www.rna.ccbb.utexax.edu"));
	List<WeakBond> wb = new ArrayList<WeakBond>();
	wb.add(new WeakBond(3, 8));
	wb.add(new WeakBond(2, 9));
	// System.out.println(s1.getBonds());
	assertTrue(s1.getBonds().equals(wb));
    }

    @Test
    void testReadStructureCtTest() throws IOException {
	RNASecondaryStructure s1 = RNASecondaryStructureFileReader.readStructure("test/ct/test-ct.txt", false);
	assertTrue(s1.getSequence().equals("GCGGAUUUUAAUUCGCA"));
	assertTrue(s1.getDescription()
		.equals("Filename: test.ct\n" + "Organism: Some Energy organism\n" + "Accession Number: XYZ123\n"
			+ "Citation and related information available at http://www.acme.com\n"
			+ "17 ENERGY =     -17.50    test"));
	List<WeakBond> wb = new ArrayList<WeakBond>();
	wb.add(new WeakBond(7, 10));
	wb.add(new WeakBond(6, 11));
	wb.add(new WeakBond(5, 12));
	wb.add(new WeakBond(4, 13));
	wb.add(new WeakBond(3, 14));
	wb.add(new WeakBond(2, 15));
	wb.add(new WeakBond(1, 16));
	// System.out.println(s1.getBonds());
	assertTrue(s1.getBonds().equals(wb));
    }

    @Test
    void testReadStructureBpseqTestNoHeader() throws IOException {
	RNASecondaryStructure s1 = RNASecondaryStructureFileReader.readStructure("test/bpseq/test-bpseq-noHeader.txt",
		false);
	assertTrue(s1.getSequence().equals("CCUGAACAG"));
	assertTrue(s1.getDescription().equals(""));
	List<WeakBond> wb = new ArrayList<WeakBond>();
	wb.add(new WeakBond(3, 8));
	wb.add(new WeakBond(2, 9));
	// System.out.println(s1.getBonds());
	assertTrue(s1.getBonds().equals(wb));
    }

    @Test
    void testReadStructureCtTestNoHeader() throws IOException {
	RNASecondaryStructure s1 = RNASecondaryStructureFileReader.readStructure("test/ct/test-ct-noHeader.txt", false);
	assertTrue(s1.getSequence().equals("GCGGAUUUUAAUUCGCA"));
	assertTrue(s1.getDescription().equals("17 ENERGY =     -17.50    test"));
	List<WeakBond> wb = new ArrayList<WeakBond>();
	wb.add(new WeakBond(7, 10));
	wb.add(new WeakBond(6, 11));
	wb.add(new WeakBond(5, 12));
	wb.add(new WeakBond(4, 13));
	wb.add(new WeakBond(3, 14));
	wb.add(new WeakBond(2, 15));
	wb.add(new WeakBond(1, 16));
	// System.out.println(s1.getBonds());
	assertTrue(s1.getBonds().equals(wb));
    }

}
