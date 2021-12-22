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

/*
 * ANTLR 4 grammar for reading files containing RNA Secondary Structures
 * in Extended Dot-Bracket Notation Format or in Arc-Annotated Sequence 
 * Format. The sequence of nucleotides is optional: it can be given 
 * only the structure. 
 * 
 * @author Luca Tesei
 * 
 */
grammar RNASecondaryStructure;

@header {
	package it.unicam.cs.bdslab.aspralign;
}

rna
:
	sequence? structure #edbnOrAas
	| bpseq  #bpseqFormat
	| ct     #ctFormat
;

sequence
:
	NUCLEOTIDES sequence # sequenceContinue
	| NUCLEOTIDES # sequenceEnd
;

structure
:
	edbns # rnaEdbn
	| bonds # rnaAas
;

edbns
:
	EDBN edbns # edbnsContinue
	| EDBN # edbnsEnd
;

bonds
:
	bond ';' bonds # bondsContinue
	| bond # bondsEnd
;

bond
:
	'(' INDEX ',' INDEX ')'
;

bpseq
:
	LINE1BPSEQCT LINE2BPSEQCT LINE3BPSEQCT LINE4BPSEQCT bpseqinfo
;

bpseqinfo
:
	bpseqline bpseqinfo  #bpseqSeq
	| bpseqline #bpseqLast
;

bpseqline
:
	INDEX IUPAC_CODE (INDEX | ZERO)
;

ct
:
	LINE1BPSEQCT LINE2BPSEQCT LINE3BPSEQCT LINE4BPSEQCT LINE5CT ctinfo 
;

ctinfo
:
	ctline ctinfo  #ctSeq
	| ctline       #ctLast
;

ctline
:
	INDEX IUPAC_CODE (INDEX | ZERO) (INDEX | ZERO) (INDEX | ZERO) INDEX
;

// Tokens

INDEX
:
	[1-9] [0-9]*
;

ZERO
:
   '0'
;

NUCLEOTIDES
:
	(
		IUPAC_CODE
		| NON_STANDARD_CODE
	)+
;

EDBN
:
	EDBN_CODE+
;

IUPAC_CODE
:
	[ACGUacguTtRrYysSWwKkMmBbDdHhVvNn-]
;

NON_STANDARD_CODE
:
	'"'
	| '?'
	| ']'
	| '~'
	| '['
	| '_'
	| '+'
	| '='
	| '/'
	| '4'
	| '7'
	| 'P'
	| 'O'
	| 'I'
;

EDBN_CODE
:
	'.'
	| '('
	| ')'
	| '['
	| ']'
	| '{'
	| '}'
	| '<'
	| '>'
	| [a-zA-Z]
;

LINE1BPSEQCT
:
'Filename' .*? '\r'? '\n'
;

LINE2BPSEQCT
:
'Organism' .*? '\r'? '\n'
;

LINE3BPSEQCT
:
'Accession' .*? '\r'? '\n'
;

LINE4BPSEQCT
:
'Citation' .*? '\r'? '\n'
;

LINE5CT
:
	.*? ('ENERGY' | 'Energy' | 'dG') .*? '\r'? '\n'
;

LINE_COMMENT
:
	'#' .*? '\r'? '\n' -> skip
; // Match "#" stuff '\n' 

WS
:
	[ \t\r\n]+ -> skip
; // skip spaces, tabs, newlines

