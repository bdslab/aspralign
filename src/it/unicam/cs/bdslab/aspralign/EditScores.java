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

/**
 * Constants for the scores of edit operations in structural RNA tree alignment.
 * 
 * @author Luca Tesei
 *
 */
public interface EditScores {
	public static double OPERATOR_INSERTION_OR_DELETION = 100;
	public static double OPERATOR_REPLACEMENT = 100;
	public static double HAIRPIN_INSERTION_OR_DELETION = 100;
	public static double HAIRPIN_REPLACEMENT_WITH_HAIRPIN = 0;
	public static double HAIRPIN_REPLACEMENT_WITH_OPERATOR = Double.POSITIVE_INFINITY;

	public static double CROSSING_MISMATCH_SCORE = 1;

}
