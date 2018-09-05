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
 * 
 * String constants for the algebraic operators.
 * 
 * @author Luca Tesei
 *
 */
public interface Operators {

	public static String CONCATENATION_LABEL = "CONC";
	public static String CROSSING_LABEL = "CROS";
	public static String NESTING_LABEL = "NEST";
	public static String ALGEBRAIC_TREE_ROOT_LABEL = "ROOT";
	public static String HAIRPIN_LABEL = "H";

	public static String ALGEBRAIC_TREE_ROOT_LABEL_LATEX = "\\leftrightarrows";
	public static String CONCATENATION_LABEL_LATEX = "\\odot";
	public static String CROSSING_LABEL_LATEX = "\\Join";
	public static String NESTING_LABEL_LATEX = "\\Cap";

}
