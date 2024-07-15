<<<<<<< HEAD
# ASPRAlign - Algebraic Structural Pseudoknot RNA Alignment 

@version 1.0

ASPRAling builds Algebraic RNA Trees and Structural RNA Trees
or calculate ASPRA Distance by aligning Structural RNA Trees of RNA secondary 
structures with arbitrary pseudoknots.

If you use ASPRAlign please cite use:

- Quadrini, M., Tesei, L., Merelli, E. An algebraic language for RNA 
pseudoknots comparison. BMC Bioinformatics 20 (Suppl 4), 161 (2019).
<https://doi.org/10.1186/s12859-019-2689-5>
- Quadrini, M., Tesei, L., Merelli, E. ASPRAlign: a tool for the 
alignment of RNA secondary structures with arbitrary pseudoknots, 
Bioinformatics, Volume 36, Issue 11, June 2020, Pages 3578–3579, 
<https://doi.org/10.1093/bioinformatics/btaa147>


## Accepted Input file formats for RNA secondary structures 
* Extended Dot-Bracket Notation. See 
<https://www.tbi.univie.ac.at/RNA/ViennaRNA/doc/html/rna_structure_notations.html>
* Extended Dot-Bracket Notation without sequence (only structure)
* Arc Annotated Sequence, similar to the Extended Dot-Bracket Notation format, 
in which the weak bonds are expressed as a list `(i_1,j_1);(i_2,j_2); ... ;(i_m,j_m)` where each index i_k, j_k belongs to the interval [1,n] (n is the length
of the primary sequence) and i_k < j_k + 1 for all k.
* Arc Annotated Sequence without sequence (only structure)
* BPSEQ format, with or without header information, see <https://www.ibi.vu.nl/programs/k2nwww/static/data_formats.html>
* CT format, with or without header information, see <https://www.ibi.vu.nl/programs/k2nwww/static/data_formats.html>

All lines starting with \# are considered comments. File format is automatically
detected from the text file, any extension is accepted. 

## ASPRAlign is distributed with two executable jar files
* ASPRAlign.jar (basic 
comparator and tree builder) and 
* ASPRAlignWorkbench.jar (workbench comparator)

## ASPRAlign.jar usage examples

* `> java -jar ASPRAlign.jar -g aas1.txt -l -o aas1.tex`

Produce file `aas1.tex` containing the LaTeX code to draw the algebraic RNA
tree corresponding to the RNA secondary structure given in the Arc
Annotated Sequence file `aas1.txt`

* `> java -jar ASPRAlign.jar -a rna1.dbn.txt rna2.dbn.txt`

Print on the standard output the linearised alignment tree of the two
structural RNA trees corresponding to the two RNA secondary structures
given in the Extended Dot-Bracket Notation files `rna1.dbn.txt` and
`rna2.dbn.txt`

See folder `examples` for some sample input files in both notations
coming from public databases or from the paper: Michela Quadrini, Luca 
Tesei, Emanuela Merelli "An algebraic language for RNA pseudoknots 
comparison", BMC Bioinformatics 20, Article number: 161 (2019).
https://bmcbioinformatics.biomedcentral.com/articles/10.1186/s12859-019-2689-5

## Output formats

Default output format is a linearised tree description of the form 

`("node-label", [list-of-children])`

An alternative output format is `LaTeX` code that can be processed with 
LaTeX to produce a graphical representation of the tree in a pdf file.
LaTeX format should be used for relative small structures, otherwise
the pdf file may not be produced or may be not readable.  

Default output stream is Standard Output. Output can be sent to a file 
using option -o 

## Configuration

The costs for the basic operations of alignment are specified in the default
configuration file `ASPRAlign-config.txt`. The values can be changed directly 
in this file or by using a different configuration file specified with 
option -n. The default configuration file must reside in the same folder in 
which the command is launched, while the file specified with option -n 
can reside in any folder.

## ASPRAlignWorkbench.jar usage examples

* `> java -jar ASPRAlignWorkbench.jar -f TestWorkBench1`

Processes all the files in folder "`TestWorkBench1`". Each file is read as
an RNA secondary structure with arbitrary pseudoknots. Comma-separated values 
files "`ASPRAlignProcessedStructures.csv`" and "`ASPRAlignComparisonResults.csv`"
are created in the folder "`TestWorkBench1`". The former contains the
description of all the structures that were found and correctly processed.
The latter contains, for each pair of processed structures, the ASPRA
Distance between the two structures and execution time information.

* `>java -jar ASPRAlignWorkbench.jar -f TestWorkBench1 -o stucts.csv
cmpr.csv -n my-config.txt`

Processes all the files in folder `TestWorkBench1` as above but produce
the description of processed structures in file `structs.csv` and
comparison results in file `cmpr.csv`. Instead of using
`ASPRAling-config.txt` default configuration file, use `my-config.txt` as
configuration file.

See folder `examples` for some sample input folders containing structures 
coming from public databases.

# Installation

Download the zip file of last version of ASPRAlign from folder `download` at <https://github.com/bdslab/aspralign/>

Direct link: <https://github.com/bdslab/aspralign/tree/master/download>

and put it in any position of your drive. 

Unzip the file with the facilities of your operating system. The folder 
`ASPRAlign-<VersionNumber>` is created containing the following files:

- ASPRAlign.jar --- executable jar of the basic ASPRAlign comparison
- ASPRAlignWorkbench.jar --- executable jar for the ASPRAlign workbench comparator
- ASPRAlign-config.txt --- default ASPRAlign configuration file
- ASPRAlign-config-alternative.txt --- alternative ASPRAlign configuration file
- examples --- folder containing sample input and output files
- INSTALL.txt --- information on ASPRAlign installation
- README.md --- ASPRAlign description and usage information
- COPYING.txt --- copyright information
- LICENSE --- full GNU GPL Version 3 License
- CHANGELOG.txt --- information about the evolution of ASPRAlign versions

The executable jar files runs on every Linux, Windows and Mac OS platform
in which a Java SE Runtime Environment 8 is installed. 

For information and installing the Java Runtime Environment see
<http://www.oracle.com/technetwork/java/javase/downloads/index.html>

# Use

## Using ASPRAlign

Open a terminal window of your operating system and use the change directory 
(cd) command to move to a folder in which the executable jar(s) and the
configuration file(s) were placed. To launch the basic ASPRAlign comparator 
digit:

`> java -jar ASPRAlign.jar <options>`

The following <options> can be used:

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
    -n,--useconffile <conf-file>           Use the specified configuration
                                           file instead of the default one
    -o,--out <output-file>                 Output result on the given file
                                           instead of standard output
    -s,--struct <input-file>               Produce the structural RNA tree
                                           corresponding to the given
                                           structure

## Using ASPRAlignWorkbench

Open a terminal window of your operating system and use the change directory 
(cd) command to move to a folder in which the executable jar(s) and the
configuration file(s) were placed. To launch the basic ASPRAlignWorkbench 
comparator digit:

`> java -jar ASPRAlignWorkbench.jar <options>`

The following <options> can be used:

    -c,--chkpair                   Check the presence of only standard
                                   Watson-Crick and wobble base pairing
                                   (disabled by default)
    -e,--showscores                Show current values of edit scores used
                                   for alignment
    -f,--input <input-folder>      Process the files in the given folder
    -h,--help                      Show usage information
    -i,--info                      Show license and other info
    -n,--useconffile <conf-file>   Use the specified configuration file
                                   instead of the default one
    -o,--output <file-1 file-2>    Output structure descriptions on file-1
                                   and comparison results on file-2 instead
                                   of generating the default ouput files

# Copyright and License

ASPRAling Copyright (C) 2020 Michela Quadrini, Luca Tesei, Emanuela
Merelli - BioShape and Data Science Lab at the University of Camerino,
Italy - <http://www.emanuelamerelli.eu/bigdata/>
=======
# SERNAlign - Structural sEquence RNA secondary structure Alignment 

@version 1.0

SERNAlign builds Structural Sequences starting from RNA secondary structures 
with arbitrary pseudoknots and computes SERNA Distance by aligning two 
Structural Sequences. Structural sequences are an abstraction of the arc diagram 
of a secondary structure in which the sequence of nucleotides is not considered 
and the topology of the arcs is represented by a numerical sequence. For each
RNA secondary structure there is only ony corresponding structural sequence. 

If you use SERNAlign please cite:

- Tesei, L., Levi, F., Quadrini, M., Merelli, E. "Alignment of RNA Secondary 
Structures with Arbitrary Pseudoknots using Structural Sequences". Submitted
for publication. 2024


## Accepted Input file formats for RNA secondary structures 
* Extended Dot-Bracket Notation. See 
<https://www.tbi.univie.ac.at/RNA/ViennaRNA/doc/html/rna_structure_notations.html>
* Extended Dot-Bracket Notation without sequence (only structure)
* Arc Annotated Sequence, similar to the Extended Dot-Bracket Notation format, 
in which the weak bonds are expressed as a list `(i_1,j_1);(i_2,j_2); ... ;(i_m,j_m)` 
where each index i_k, j_k belongs to the interval [1,n] (n is the length
of the primary sequence) and i_k < j_k + 1 for all k.
* Arc Annotated Sequence without sequence (only structure)
* BPSEQ format, with or without header information, see <https://www.ibi.vu.nl/programs/k2nwww/static/data_formats.html>
* CT format, with or without header information, see <https://www.ibi.vu.nl/programs/k2nwww/static/data_formats.html>

All lines starting with \# are considered comments. File format is automatically
detected from the text file, any extension is accepted. 

# Installation

Download the zip file of last version of SERNAlign from folder `download` at <https://github.com/bdslab/sernalign/>

Direct link: <https://github.com/bdslab/sernalign/tree/master/download>

and put it in any position of your drive. 

Unzip the file with the facilities of your operating system. The folder 
`SERNAlign-<VersionNumber>` is created containing the following files:

- SERNAlign.jar --- executable jar of the basic SERNAlign comparison
- SERNAlignWorkbench.jar --- executable jar for the SERNAlign workbench comparator
- examples --- folder containing sample input and output files
- INSTALL.txt --- information on SERNAlign installation
- README.md --- SERNAlign description and usage information
- COPYING.txt --- copyright information
- LICENSE --- full GNU GPL Version 3 License
- CHANGELOG.txt --- information about the evolution of SERNAlign versions

The executable jar files runs on every Linux, Windows and Mac OS platform
in which a Java SE Runtime Environment 8 is installed. 

For information and installing the Java Runtime Environment see
<http://www.oracle.com/technetwork/java/javase/downloads/index.html>

## Using SERNAlign

Open a terminal window of your operating system and use the change directory 
(cd) command to move to a folder in which the executable jar(s) and the
configuration file(s) were placed. To launch the basic SERNAlign comparator 
digit:

`> java -jar SERNAlign.jar <options>`

The following <options> can be used:

	-a,--align <input-file1 input-file2>   Align two given structures
	                                       producing an alignment and
	                                       distance
	-d,--outdist                           Output only distance, no alignment
                                           (works only with option -a)
	-h,--help                              Show usage information
	-i,--info                              Show license and other info
	-o,--out <output-file>                 Output result on the given file
                                           instead of standard output
	-s,--struct <input-file>               Produce the structural sequence
                                           corresponding to the given
                                           structure

## Using SERNAlignWorkbench

Open a terminal window of your operating system and use the change directory 
(cd) command to move to a folder in which the executable jar(s) were placed. 
To launch the basic SERNAlignWorkbench comparator digit:

`> java -jar SERNAlignWorkbench.jar <options>`

The following <options> can be used:

	-f,--input <input-folder>     Process the files in the given folder
	-h,--help                     Show usage information
	-i,--info                     Show license and other info
	-o,--output <file-1 file-2>   Output structure descriptions on file-1 and
                                  comparison results on file-2 instead of
                                  generating the default ouput files

## SERNAlign.jar usage examples

Subfolder `examples`, distributed with SERNAlign, contains sample input files 
from the paper: Tesei, L., Levi, F., Quadrini, M., Merelli, E. "Alignment of RNA Secondary 
Structures with Arbitrary Pseudoknots using Structural Sequences". Submitted
for publication. 2024

* `> java -jar SERNAlign.jar -s examples/structS1.aas.txt`

Print on the standard output the structural sequence corresponding
to the RNA secondary structure given in the Arc Annotated Sequence file
`examples/structS1.aas.txt`.

In this particular case, the output is 

	[ 1, 1, 5, 3 ]


* `> java -jar SERNAlign.jar -a examples/structS1.aas.txt examples/structS2.aas.txt -o examples/alignmentS1S2.txt`

Write on the file `examples/alignmentS1S2.txt` the alignment and the SERNA 
distance of the structural sequences corresponding to the RNA secondary 
structures given in the Arc Annotated Sequence files `examples/structS1.aas.txt` and 
`examples/structS2.aas.txt`.

In this particular case the file contains: 

	(1, 1)(1, 1)(5, 5)(-, 5)(3, 2)

	Distance = 2

The sequence 

	(1, 1)(1, 1)(5, 5)(-, 5)(3, 2)

corresponds to the optimal alignmet 

	1 1 5 - 3
	1 1 5 5 2
	
between the structural sequences `1 1 5 3` and `1 1 5 5 2` where the edit operations
are `(1, 1)` (match), `(1, 1)` (match), `(5, 5)` (match), `(-, 5)` (insertion) 
and `(3, 2)` (mismatch/substitution). 

The SERNA distance, i.e., the total cost of these edit operations 
is 0 (match) + 0 (match) + 0 (match) + 1 (insertion) + 1 (substitution) = 2

## SERNAlignWorkbench.jar usage examples

* `> java -jar SERNAlignWorkbench.jar -f TestWorkBench1`

Processes all the files in folder "`TestWorkBench1`". Each file is read as
an RNA secondary structure with arbitrary pseudoknots. Comma-separated values 
files "`SERNAlignProcessedStructures.csv`" and "`SERNAlignComparisonResults.csv`"
are created in the folder "`TestWorkBench1`". The former contains the
description of all the structures that were found and correctly processed.
The latter contains, for each pair of processed structures, the SERNA
Distance between the two structures and execution time information.

* `>java -jar SERNAlignWorkbench.jar -f TestWorkBench1 -o stucts.csv
cmpr.csv`

Processes all the files in folder `TestWorkBench1` as above but produce
the description of processed structures in file `structs.csv` and
comparison results in file `cmpr.csv`. 

See folder `examples` for some sample input folders containing structures 
coming from public databases.

# Copyright and License

SERNAling Copyright (C) 2020  Luca Tesei, Francesca Levi, Michela Quadrini, 
Emanuela Merelli - BioShape and Data Science Lab at the University of 
Camerino, Italy - <http://www.emanuelamerelli.eu/bigdata/>
>>>>>>> refs/remotes/origin/master

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

# Contact Information

Please report any issue to luca.tesei@unicam.it or to Luca Tesei, Polo
Informatico, via Madonna delle Carceri 9, 62032 Camerino (MC) Italy.


