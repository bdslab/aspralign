***************************************************************************
ASPRAlign - Algebraic Structural Pseudoknot RNA Alignment - version 0.9
***************************************************************************

ASPRAling version 0.9 builds Algebraic RNA Trees and Structural RNA Trees
or align Structural RNA Trees of RNA secondary structures with or without
any kind of pseudoknot. 

Default input file format is Extended Dot-Bracket Notation. 
See https://www.tbi.univie.ac.at/RNA/ViennaRNA/doc/html/
rna_structure_notations.html

An alternative input file format is Arc Annotated Sequence, similar to 
the Extended Dot-Bracket Notation format in which the weak bonds are 
expressed as a list 

(i_1,j_1);(i_2,j_2); ... ;(i_m,j_m) 

where each index i_k, j_k belongs to the interval [1,n] (n is the length
of the primary sequence) and i_k < j_k + 1 for all k.

See folder "examples" for some sample input files in both notations
coming from public databases or from the paper: Michela Quadrini, Luca 
Tesei, Emanuela Merelli "An Algebraic Language for RNA Pseudoknots 
Comparison" 

Default output format is a linearised tree of the form 

("node-label", [list-of-children])

An alternative output format is LaTeX code that can be processed with 
LaTeX to produce a graphical representation of the tree in a pdf file. 

Default output stream is Standard Output. Output can be sent to a file 
using option -o 

***************************************************************************
INSTALLATION and USE
***************************************************************************

Download the executable jar file ASPRAlign.jar from the folder 
"executable-jar": click on the folder and then on the file; select 
"View Raw" and give permission to download the .jar file (if any). Once 
obtained the jar file, it can be run from a terminal window with the command

java -jar ASPRAlign.jar 

The executable jar runs on every Linux, Windows and Mac OS platform
in which a Java SE Runtime Environment 8 is installed. 

For information and installing the Java Runtime Environment see
http://www.oracle.com/technetwork/java/javase/downloads/index.html

Command line options are illustrated in the following:

usage: java -jar ASPRAlign.jar [-a <input-file1 input-file2>] [-c] [-d]
       [-e] [-g <input-file>] [-h] [-i] [-l] [-o <output-file>] [-r] [-s
       <input-file>]

 -a,--align <input-file1 input-file2>   Align two given structures
                                        producing alignment tree and
                                        distance
 -c,--chkpair                           Check the presence of only
                                        standard Watson-Crick and wobble
                                        base pairing (disabled by default)
 -d,--outdist                           Output only distance, no alignment
                                        tree (works only with option -a)
 -e,--showscores                        Show current values of edit scores
                                        used for alignment
 -g,--alg <input-file>                  Produce the algebraic RNA tree
                                        corresponding to the given
                                        structure
 -h,--help                              Show usage information
 -i,--info                              Show license and other info
 -l,--latexout                          Output in LaTeX format instead of
                                        linearised tree
 -o,--out <output-file>                 Output result on the given file
                                        instead of standard output
 -r,--aasinput                          Input arc annotated sequence
                                        file(s) instead of dot bracket
                                        notation file(s)
 -s,--struct <input-file>               Produce the structural RNA tree
                                        corresponding to the given
                                        structure
Usage examples:

>java -jar ASPRAlign.jar -r -g aas1.txt -l -o aas1.tex

Produce file aas1.tex containing the LaTeX code to draw the algebraic RNA
tree corresponding to the RNA secondary structure given in the Arc
Annotated Sequence file aas1.txt

>java -jar ASPRAlign.jar -a rna1.dbn.txt rna2.dbn.txt

Print on the standard output the linearised alignment tree of the two
structural RNA trees corresponding to the two RNA secondary structures
given in the Extended Dot-Bracket Notation files rna1.dbn.txt and 
rna2.dbn.txt

***************************************************************************
COPYRIGHT and LICENSE
***************************************************************************

ASPRAling Copyright (C) 2018 Michela Quadrini, Luca Tesei, Emanuela
Merelli - BioShape and Data Science Lab at the University of Camerino,
Italy - http://www.emanuelamerelli.eu/bigdata/

This program is free software: you can redistribute it and/or modify it
under the terms of the GNU General Public License as published by the Free
Software Foundation, either version 3 of the License, or any later
version.

This program is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
for more details.

You should have received a copy of the GNU General Public License along
with this program.  If not, see <http://www.gnu.org/licenses/>.

***************************************************************************
CONTACT INFORMATION
***************************************************************************

Please report any issue to luca.tesei@unicam.it or to Luca Tesei, Polo
Informatico, via Madonna delle Carceri 9, 62032 Camerino (MC) Italy.

Luca Tesei, September 2018
