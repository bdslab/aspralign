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

import fr.orsay.lri.varna.models.treealign.TreeAlignLabelDistanceAsymmetric;

/**
 * Define a proper scoring function for the alignment of two structural RNA
 * trees.
 * 
 * @author Luca Tesei
 * 
 */
public class ScoringFunction implements TreeAlignLabelDistanceAsymmetric<String, String> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.orsay.lri.varna.models.treealign.TreeAlignLabelDistanceAsymmetric#f(java.
	 * lang.Object, java.lang.Object)
	 */
	@Override
	public double f(String Xvalue, String Yvalue) {
		String x = (Xvalue == null) ? "-" : Xvalue;
		String y = (Yvalue == null) ? "-" : Yvalue;

		// (gap,gap) ... not used by alignment, but called
		if (isGap(x) && isGap(y))
			return 0;

		// op vs gap case
		if (isOperator(x) && isGap(y))
			return EditScores.OPERATOR_INSERTION_OR_DELETION;
		if (isOperator(y) && isGap(x))
			return EditScores.OPERATOR_INSERTION_OR_DELETION;

		// (op,op') case
		if (isOperator(x) && isOperator(y)) {
			if (isCrossing(x) && isCrossing(y)) {
				// matching crossings, the cost is local and proportional to the crossing
				// mismatches
				int numberOfCrossingMismatches = getNumberOfCrossingMismatches(x, y);
				return EditScores.CROSSING_MISMATCH_SCORE * numberOfCrossingMismatches;
			}
			// the operators are not two crossings
			if (x.equals(y))
				// the operators match
				return 0;
			// the operators do not match
			return EditScores.OPERATOR_REPLACEMENT;
		}

		// h vs gap case
		if (isHairpin(x) && isGap(y))
			return EditScores.HAIRPIN_INSERTION_OR_DELETION;
		if (isHairpin(y) && isGap(x))
			return EditScores.HAIRPIN_INSERTION_OR_DELETION;

		// (h,h') case
		if (isHairpin(x) && isHairpin(y))
			return EditScores.HAIRPIN_REPLACEMENT_WITH_HAIRPIN;
		if (isHairpin(y) && isHairpin(x))
			return EditScores.HAIRPIN_REPLACEMENT_WITH_HAIRPIN;

		// h vs other case
		if (isHairpin(x) && !isHairpin(y))
			return EditScores.HAIRPIN_REPLACEMENT_WITH_OPERATOR;
		if (isHairpin(y) && !isHairpin(x))
			return EditScores.HAIRPIN_REPLACEMENT_WITH_OPERATOR;

		// unreachable code
		assert false : "Scoring function unreachable code reached: (" + x + "," + y + ")";
		return 0;
	}

	private boolean isHairpin(String s) {
		String regexp = Operators.HAIRPIN_LABEL + "\\(\\d+\\,\\d+\\)";
		return s.trim().matches(regexp);
	}

	private boolean isGap(String s) {
		return s.equals("-");
	}

	private boolean isConcOrNesting(String s) {
		if (s.equals(Operators.CONCATENATION_LABEL))
			return true;
		if (s.equals(Operators.NESTING_LABEL))
			return true;
		return false;
	}

	private boolean isCrossing(String s) {
		String regexp = "\\(" + Operators.CROSSING_LABEL + "\\,\\d+\\)";
		return s.trim().matches(regexp);
	}

	private boolean isOperator(String s) {
		return isConcOrNesting(s) || isCrossing(s);
	}

	private int getNumberOfCrossingMismatches(String s1, String s2) {
		String ss1[] = s1.trim().split(",");
		String ss2[] = s2.trim().split(",");

		int n1 = 0;
		int n2 = 0;
		try {
			n1 = Integer.parseInt(ss1[1].substring(0, ss1[1].length() - 1));
		} catch (NumberFormatException e) {
			assert false : "Wrong crossing label: " + s1;
		}
		try {
			n2 = Integer.parseInt(ss2[1].substring(0, ss2[1].length() - 1));
		} catch (NumberFormatException e) {
			assert false : "Wrong crossing label: " + s2;
		}
		return Math.abs(n1 - n2);
	}
}
