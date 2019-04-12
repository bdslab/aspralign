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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import fr.orsay.lri.varna.models.treealign.TreeAlignLabelDistanceAsymmetric;

/**
 * Define a proper scoring function for the alignment of two structural RNA
 * trees.
 * 
 * @author Luca Tesei
 * 
 */
public class ScoringFunction implements TreeAlignLabelDistanceAsymmetric<String, String> {

	/**
	 * Default name of configuration file.
	 */
	public static final String DEFAULT_PROPERTY_FILE = "ASPRAlign-config.txt";

	private String propertyFileName;
	private double insertOperatorCost;
	private double deleteOperatorCost;
	private double replaceOperatorCost;
	private double insertHairpinCost;
	private double deleteHairpinCost;
	private final double REPLACE_HAIRPIN_WITH_HAIRPIN = 0;
	private final double REPLACE_HAIRPIN_WITH_OPERATOR = Double.POSITIVE_INFINITY;
	private double crossingMismatchCost;

	/**
	 * Create a scoring function according to the costs specified in the given
	 * property file.
	 * 
	 * @param propertyFileName the configuration file to be used to load the costs
	 *                         associated to each operation
	 * @throws NullPointerException if the property file name is null
	 */
	public ScoringFunction(String propertyFileName) {
		// create and load default properties
		Properties props = new Properties();
		// set default values
		props.setProperty("INSERT_OPERATOR_COST", "100");
		props.setProperty("DELETE_OPERATOR_COST", "100");
		props.setProperty("REPLACE_OPERATOR_COST", "100");
		props.setProperty("INSERT_HAIRPIN_COST", "100");
		props.setProperty("DELETE_HAIRPIN_COST", "100");
		props.setProperty("CROSSING_MISMATCH_COST", "1");
		// load properties from file
		if (propertyFileName == null)
			throw new NullPointerException("Property file name null");
		this.propertyFileName = propertyFileName;
		try (FileInputStream in = new FileInputStream(propertyFileName)) {
			props.load(in);
			System.err.println("Configuration file " + propertyFileName + " loaded.");
		} catch (FileNotFoundException e1) {
			System.err.println("WARNING: Configuration file " + propertyFileName
					+ " not found... usign default configuration values");
		} catch (IOException e) {
			System.err.println("WARNING: Error reading configuration file " + propertyFileName
					+ " ... usign default configuration values");
		}

		// defaultProps loaded or taken as default
		try {
			this.insertOperatorCost = Double.parseDouble(props.getProperty("INSERT_OPERATOR_COST"));
		} catch (NumberFormatException e) {
			System.err.println("WARNING: configuration value of INSERT_OPERATOR_COST is "
					+ props.getProperty("INSERT_OPERATOR_COST") + " and cannot be converted to a valid number... "
					+ "using default value 100.0");
			this.insertOperatorCost = 100;
		}

		try {
			this.deleteOperatorCost = Double.parseDouble(props.getProperty("DELETE_OPERATOR_COST"));
		} catch (NumberFormatException e) {
			System.err.println("WARNING: configuration value of DELETE_OPERATOR_COST is "
					+ props.getProperty("DELETE_OPERATOR_COST") + " and cannot be converted to a valid number... "
					+ "using default value 100.0");
			this.deleteOperatorCost = 100;
		}

		try {
			this.replaceOperatorCost = Double.parseDouble(props.getProperty("REPLACE_OPERATOR_COST"));
		} catch (NumberFormatException e) {
			System.err.println("WARNING: configuration value of REPLACE_OPERATOR_COST is "
					+ props.getProperty("REPLACE_OPERATOR_COST") + " and cannot be converted to a valid number... "
					+ "using default value 100.0");
			this.replaceOperatorCost = 100;
		}

		try {
			this.insertHairpinCost = Double.parseDouble(props.getProperty("INSERT_HAIRPIN_COST"));
		} catch (NumberFormatException e) {
			System.err.println(
					"WARNING: configuration value of INSERT_HAIRPIN_COST is " + props.getProperty("INSERT_HAIRPIN_COST")
							+ " and cannot be converted to a valid number... " + "using default value 100.0");
			this.insertHairpinCost = 100;
		}

		try {
			this.deleteHairpinCost = Double.parseDouble(props.getProperty("DELETE_HAIRPIN_COST"));
		} catch (NumberFormatException e) {
			System.err.println(
					"WARNING: configuration value of DELETE_HAIRPIN_COST is " + props.getProperty("DELETE_HAIRPIN_COST")
							+ " and cannot be converted to a valid number... " + "using default value 100.0");
			this.deleteHairpinCost = 100;
		}

		try {
			this.crossingMismatchCost = Double.parseDouble(props.getProperty("CROSSING_MISMATCH_COST"));
		} catch (NumberFormatException e) {
			System.err.println("WARNING: configuration value of CROSSING_MISMATCH_COST is "
					+ props.getProperty("CROSSING_MISMATCH_COST") + " and cannot be converted to a valid number... "
					+ "using default value 1.0");
			this.crossingMismatchCost = 1;
		}
	}

	/**
	 * @return the property file name
	 */
	public String getPropertyFileName() {
		return this.propertyFileName;
	}

	/**
	 * @return the insertOperatorCost
	 */
	public double getInsertOperatorCost() {
		return insertOperatorCost;
	}

	/**
	 * @return the deleteOperatorCost
	 */
	public double getDeleteOperatorCost() {
		return deleteOperatorCost;
	}

	/**
	 * @return the replaceOperatorCost
	 */
	public double getReplaceOperatorCost() {
		return replaceOperatorCost;
	}

	/**
	 * @return the insertHairpinCost
	 */
	public double getInsertHairpinCost() {
		return insertHairpinCost;
	}

	/**
	 * @return the deleteHairpinCost
	 */
	public double getDeleteHairpinCost() {
		return deleteHairpinCost;
	}

	/**
	 * @return the REPLACE_HAIRPIN_WITH_HAIRPIN
	 */
	public double getREPLACE_HAIRPIN_WITH_HAIRPIN() {
		return REPLACE_HAIRPIN_WITH_HAIRPIN;
	}

	/**
	 * @return the REPLACE_HAIRPIN_WITH_OPERATOR
	 */
	public double getREPLACE_HAIRPIN_WITH_OPERATOR() {
		return REPLACE_HAIRPIN_WITH_OPERATOR;
	}

	/**
	 * @return the crossingMismatchCost
	 */
	public double getCrossingMismatchCost() {
		return crossingMismatchCost;
	}

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
			return this.deleteOperatorCost;
		if (isOperator(y) && isGap(x))
			return this.insertOperatorCost;

		// (op,op') case
		if (isOperator(x) && isOperator(y)) {
			if (isCrossing(x) && isCrossing(y)) {
				// matching crossings, the cost is local and proportional to the crossing
				// mismatches
				int numberOfCrossingMismatches = getNumberOfCrossingMismatches(x, y);
				return this.crossingMismatchCost * numberOfCrossingMismatches;
			}
			// the operators are not two crossings
			if (x.equals(y))
				// the operators match
				return 0;
			// the operators do not match
			return this.replaceOperatorCost;
		}

		// h vs gap case
		if (isHairpin(x) && isGap(y))
			return this.deleteHairpinCost;
		if (isHairpin(y) && isGap(x))
			return this.insertHairpinCost;

		// (h,h') case
		if (isHairpin(x) && isHairpin(y))
			return this.REPLACE_HAIRPIN_WITH_HAIRPIN;
		if (isHairpin(y) && isHairpin(x))
			return this.REPLACE_HAIRPIN_WITH_HAIRPIN;

		// h vs other case
		if (isHairpin(x) && !isHairpin(y))
			return this.REPLACE_HAIRPIN_WITH_OPERATOR;
		if (isHairpin(y) && !isHairpin(x))
			return this.REPLACE_HAIRPIN_WITH_OPERATOR;

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
