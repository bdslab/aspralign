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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * Representation of an RNA secondary structure with any kind of pseudoknot. It
 * consists of the nucleotide primary sequence and of a list of weak bounds
 * given as pairs of positions in the primary sequence. Positions start at 1 and
 * end at the length of the primary sequence.
 * 
 * @author Luca Tesei
 *
 */
public class ArcAnnotatedSequence {

	private String nucleotides; // primary structure
	private List<WeakBond> bounds; // weak bounds

	// array of pointers of the weak bounds; 0 value means null pointer; meaningful
	// indexes of the array start at 1, position 0 is not used; if p[i] = j and i <
	// j then there is a weak bound (i,j), otherwise it is (j,i); i must be not
	// equal to j in both cases; no more than one pointer is allowed in RNA
	// secondary structures, so one array is sufficient to represent all weak
	// bounds.
	protected int[] p;

	public ArcAnnotatedSequence(String nucleotides, String bounds, boolean basePairsCheck)
			throws IllegalArgumentException {
		// convert to upper case and trim for convenience
		this.nucleotides = nucleotides.toUpperCase().trim();
		// check bases in the primary structure - IUPAC nucleotide codes
		for (int i = 0; i < this.nucleotides.length(); i++)
			switch (this.nucleotides.charAt(i)) {
			case 'A':
			case 'U':
			case 'C':
			case 'G':
			case 'T':
			case 'R':
			case 'Y':
			case 'S':
			case 'W':
			case 'K':
			case 'M':
			case 'B':
			case 'D':
			case 'H':
			case 'V':
			case 'N':
			case '.':
			case '-':
				break;
			default:
				throw new IllegalArgumentException(
						"INPUT ERROR: primary structure contains an unkwnown nucleotide code at position " + (i + 1));
			}
		// parse bounds and convert them into WeakBond objects, check indexes and base
		// pairs constraints
		convertBounds(bounds, basePairsCheck);
		if (this.bounds.isEmpty())
			throw new IllegalArgumentException(
					"INPUT ERROR: Arc Annotated Sequence contains no weak bounds, ther is no RNA secondary structure");
		// initialize array p and checks that each nucleotide is involved in not more
		// than one weak bound
		this.p = new int[this.nucleotides.length() + 1]; // position 0 is not used
		for (WeakBond b : this.bounds) {
			if (p[b.left] != 0)
				throw new IllegalArgumentException("INPUT ERROR: Weak Bound (" + b.left + "," + b.right
						+ ") has left nucleotide involved in more than one weak bound");
			if (p[b.right] != 0)
				throw new IllegalArgumentException("INPUT ERROR: Weak Bound (" + b.left + "," + b.right
						+ ") has right nucleotide involved in more than one weak bound");
			p[b.left] = b.right;
			p[b.right] = b.left;
		}

	}

	/*
	 * Parse the input string containing the pairs of weak bounds and transform them
	 * into an ordered list of objects of the class WeakBond, checking that all
	 * indexes are positive integers. Put the list in this.bounds.
	 */
	private void convertBounds(String bounds, boolean basePairsCheck) {
		this.bounds = new ArrayList<WeakBond>();
		// regular expression for matching (n,n);(n,n); ... ;(n,n)
		// NOTE THAT white spaces are NOT admitted
		// The following is the regexp that permits white spaces, but for long strings
		// to match it produces a stack overflow
		// String regexp =
		// "\\s*\\(\\s*\\d+\\s*\\,\\s*\\d+\\s*\\)\\s*(\\s*;\\s*\\(\\s*\\d+\\s*\\,\\s*\\d+\\s*\\))*";
		// The following is the regexp that does not admit spaces
		String regexp = "\\(\\d+\\,\\d+\\)(;\\(\\d+\\,\\d+\\))*";
		// format check
		bounds = bounds.trim(); // delete possible spaces befor and after
		if (!bounds.matches(regexp))
			throw new IllegalArgumentException(
					"INPUT ERROR: list of weak bounds does not respect format (n,n);(n,n); ... ;(n,n)");
		// split of the pairs
		String[] pairs = bounds.split(";");
		// check and creation of WeakBounds
		for (int i = 0; i < pairs.length; i++) {
			int left, right;
			String pair = pairs[i].trim();
			String[] el = pair.substring(1, pair.length() - 1).split("\\,");
			try {
				left = Integer.parseInt(el[0].trim());
				if (left <= 0)
					throw new NumberFormatException();
				right = Integer.parseInt(el[1].trim());
				if (right <= 0)
					throw new NumberFormatException();
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException(
						"INPUT ERROR: an index of the " + (i + 1) + "-th weak bound is not a positive integer");
			}
			// create the bound
			WeakBond b = null;
			try {
				b = new WeakBond(this, left, right, basePairsCheck);
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException(
						"INPUT ERROR: at weak bound (" + left + "," + right + "): " + e.getMessage());
			}
			// add the weak bound to the list
			this.bounds.add(b);

		}
		// order weak bounds
		Collections.sort(this.bounds);
	}

	/**
	 * @return the primary sequence
	 */
	public String getNucleotides() {
		return nucleotides;
	}

	/**
	 * @return the list of weak bonds in the secondary structure
	 */
	public List<WeakBond> getBounds() {
		return bounds;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ArcAnnotatedSequence [nucleotides=" + nucleotides + ", bounds=" + bounds + "]";
	}

	/*
	 * A pair of indexes in the boundaries of a primary structure representing a
	 * weak bond with source nucleotide and destination nucleotide. Pairs are
	 * ordered with respect to the right component.
	 * 
	 * @author Luca Tesei
	 * 
	 */
	protected class WeakBond implements Comparable<WeakBond> {
		private final int left; // first member of the bound
		private final int right; // second member of the bound

		/*
		 * Construct a pair.
		 * 
		 * @param aas reference to the arc annotated sequence to which this weak bound
		 * belongs
		 * 
		 * @param left left index (starting with 1)
		 * 
		 * @param right right index (starting with 1)
		 */
		protected WeakBond(ArcAnnotatedSequence aas, int left, int right, boolean basePairsCheck) {
			// perform all the checks
			// index checks
			if (left < 1 || left > aas.nucleotides.length())
				throw new IllegalArgumentException("left index out of bounds.");
			if (right < 1 || right > aas.nucleotides.length())
				throw new IllegalArgumentException("right index out of bounds.");
			if (left >= right)
				throw new IllegalArgumentException("left index greater than or equal to right one.");
			if (right == left + 1)
				throw new IllegalArgumentException("hairpin with less than three nucleotides.");
			// base pair check
			int index1 = left - 1; // adjustment of indexes wrt the zero-starting indexes of strings
			int index2 = right - 1;
			if (basePairsCheck) {
				switch (aas.nucleotides.charAt(index1)) {
				case 'A':
					if (aas.nucleotides.charAt(index2) != 'U')
						throw new IllegalArgumentException("base pair not allowed in RNA: "
								+ aas.nucleotides.charAt(index1) + "-" + aas.nucleotides.charAt(index2));
					else
						break;
				case 'U':
					if (aas.nucleotides.charAt(index2) != 'A' && aas.nucleotides.charAt(index2) != 'G')
						throw new IllegalArgumentException("base pair not allowed in RNA: "
								+ aas.nucleotides.charAt(index1) + "-" + aas.nucleotides.charAt(index2));
					else
						break;
				case 'C':
					if (aas.nucleotides.charAt(index2) != 'G')
						throw new IllegalArgumentException("base pair not allowed in RNA: "
								+ aas.nucleotides.charAt(index1) + "-" + aas.nucleotides.charAt(index2));
					else
						break;
				case 'G':
					if (aas.nucleotides.charAt(index2) != 'C' && aas.nucleotides.charAt(index2) != 'U')
						throw new IllegalArgumentException("base pair not allowed in RNA: "
								+ aas.nucleotides.charAt(index1) + "-" + aas.nucleotides.charAt(index2));
					else
						break;
				}
			}
			// all checks passed
			this.left = left;
			this.right = right;
		}

		/**
		 * @return the left
		 */
		protected int getLeft() {
			return left;
		}

		/**
		 * @return the right
		 */
		protected int getRight() {
			return right;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + left;
			result = prime * result + right;
			return result;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "WeakBond [left=" + left + ", right=" + right + "]";
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			WeakBond other = (WeakBond) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (left != other.left)
				return false;
			if (right != other.right)
				return false;
			return true;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		@Override
		public int compareTo(WeakBond o) {
			Integer right, oright;
			right = new Integer(this.right);
			oright = new Integer(o.right);
			return right.compareTo(oright);
		}

		protected ArcAnnotatedSequence getOuterType() {
			return ArcAnnotatedSequence.this;
		}

	}

}
