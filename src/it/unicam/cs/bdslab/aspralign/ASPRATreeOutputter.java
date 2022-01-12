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

import java.util.List;

import fr.orsay.lri.varna.models.treealign.AlignedNode;
import fr.orsay.lri.varna.models.treealign.Tree;

/**
 * 
 * Functions to translate algebraic RNA trees, structural RNA trees and aligned
 * structural RNA trees into LaTeX and Linearised Tree formats.
 * 
 * @author Luca Tesei
 *
 */
public class ASPRATreeOutputter {

    /**
     * Translate an algebraic RNA tree or a structural RNA tree into LaTeX code.
     * 
     * @param t the tree
     * @return LaTeX code representing the tree
     */
    public static String toLatex(Tree<String> t) {
	return "\\documentclass[border=10pt]{standalone} \\usepackage{forest} \\usepackage{amssymb}"
		+ "\\begin{document} \\begin{forest} for tree={draw, semithick, rounded corners, "
		+ "font = \\sffamily, top color = white, bottom color = white, grow = south, "
		+ "s sep = 4mm, l sep = 8mm,} " + recToLatex(t) + "];" + " \\end{forest} " + "\\end{document}";
    }

    /*
     * Recursive method to visit an algebraic RNA tree or a structural RNA tree and
     * generate corresponding LaTeX code.
     */
    private static String recToLatex(Tree<String> t) {
	StringBuffer latexTree = new StringBuffer();
	latexTree.append("[{" + transformLabel(t.getValue()) + "}");
	if (t.getChildren().size() > 0) {
	    List<Tree<String>> children = t.getChildren();
	    for (Tree<String> tp : children) {
		latexTree.append(recToLatex(tp) + "]");
	    }
	}
	return latexTree.toString();
    }

    /*
     * Transform concatenation, crossing and nesting labels into the corresponding
     * LaTeX math operators. Leave everything else unchanged.
     */
    private static String transformLabel(String label) {
	String prefix = "(";
	if (label.startsWith(prefix)) {
	    String[] inLabels = label.split(",");
	    String inLabel = inLabels[0].substring(1, inLabels[0].length());
	    if (inLabel.equals(Operators.CONCATENATION_LABEL))
		return "$(" + Operators.CONCATENATION_LABEL_LATEX + "," + inLabels[1] + "$";
	    if (inLabel.equals(Operators.CROSSING_LABEL))
		return "$(" + Operators.CROSSING_LABEL_LATEX + "," + inLabels[1] + "$";
	    if (inLabel.equals(Operators.NESTING_LABEL))
		return "$(" + Operators.NESTING_LABEL_LATEX + "," + inLabels[1] + "$";
	} else {
	    if (label.equals(Operators.ALGEBRAIC_TREE_ROOT_LABEL))
		return "$" + Operators.ALGEBRAIC_TREE_ROOT_LABEL_LATEX + "$";
	    if (label.equals(Operators.CONCATENATION_LABEL))
		return "$" + Operators.CONCATENATION_LABEL_LATEX + "$";
	    if (label.equals(Operators.CROSSING_LABEL))
		return "$" + Operators.CROSSING_LABEL_LATEX + "$";
	    if (label.equals(Operators.NESTING_LABEL))
		return "$" + Operators.NESTING_LABEL_LATEX + "$";
	}
	return label;
    }

    /**
     * Linearise an algebraic RNA tree or a structural RNA tree into a string. The
     * format is ("node-label", [list-of-children]).
     * 
     * @param t the tree
     * @return string representing the tree
     */
    public static String treeToString(Tree<String> t) {
	// DFS visit
	StringBuffer stringTree = new StringBuffer("(");
	stringTree.append("\"" + t.getValue() + "\"");
	if (t.getChildren().size() > 0) {
	    stringTree.append(", [");
	    List<Tree<String>> children = t.getChildren();
	    int i;
	    for (i = 0; i < children.size() - 1; i++) {
		Tree<String> tp = children.get(i);
		stringTree.append(treeToString(tp) + ", ");
	    }
	    Tree<String> tp = children.get(i);
	    stringTree.append(treeToString(tp) + "]");
	} else {
	    stringTree.append(", []");

	}
	stringTree.append(")");
	return stringTree.toString();
    }

    /**
     * Translate the alignment tree of two structural RNA trees into LaTeX code.
     * 
     * @param t the aligned tree
     * @return LaTeX code representing the aligned tree
     */
    public static String toLatexAligned(Tree<AlignedNode<String, String>> t) {
	return "\\documentclass[border=10pt]{standalone} \\usepackage{forest} \\usepackage{amssymb} "
		+ "\\begin{document} \\begin{forest} for tree={draw, semithick, rounded corners, "
		+ "font = \\sffamily, top color = white, bottom color = white, grow = south, "
		+ "s sep = 4mm, l sep = 8mm,} " + recToLatexAligned(t) + "];" + " \\end{forest} \\end{document}";
    }

    /*
     * 
     */
    private static String recToLatexAligned(Tree<AlignedNode<String, String>> t) {
	// DFS visit
	StringBuffer latexTree = new StringBuffer();
	AlignedNode<String, String> value = t.getValue();
	String left, right;
	if (value.getLeftNode() == null)
	    left = "-";
	else
	    left = value.getLeftNode().getValue();
	if (value.getRightNode() == null)
	    right = "-";
	else
	    right = value.getRightNode().getValue();
	latexTree.append("[{(" + transformLabel(left) + " , " + transformLabel(right) + ")}");
	if (t.getChildren().size() > 0) {
	    List<Tree<AlignedNode<String, String>>> children = t.getChildren();
	    for (Tree<AlignedNode<String, String>> tp : children)
		latexTree.append(recToLatexAligned(tp) + "]");
	}
	return latexTree.toString();
    }

    /**
     * 
     * Linearise an alignment tree of two structural RNA trees into a string. The
     * format is ("node-label", [list-of-children]).
     * 
     * @param t the aligned tree
     * @return string representing the aligned tree
     */
    public static String treeToStringAligned(Tree<AlignedNode<String, String>> t) {
	// DFS visit
	StringBuffer stringTree = new StringBuffer("(");
	AlignedNode<String, String> value = t.getValue();
	String left, right;
	if (value.getLeftNode() == null)
	    left = "-";
	else
	    left = value.getLeftNode().getValue();
	if (value.getRightNode() == null)
	    right = "-";
	else
	    right = value.getRightNode().getValue();
	stringTree.append("\"" + "(" + left + "," + right + ")" + "\"");
	if (t.getChildren().size() > 0) {
	    stringTree.append(", [");
	    List<Tree<AlignedNode<String, String>>> children = t.getChildren();
	    int i;
	    for (i = 0; i < children.size() - 1; i++) {
		Tree<AlignedNode<String, String>> tp = children.get(i);
		stringTree.append(treeToStringAligned(tp) + ", ");
	    }
	    Tree<AlignedNode<String, String>> tp = children.get(i);
	    stringTree.append(treeToStringAligned(tp) + "]");
	} else {
	    stringTree.append(", []");

	}
	stringTree.append(")");
	return stringTree.toString();

    }
}
