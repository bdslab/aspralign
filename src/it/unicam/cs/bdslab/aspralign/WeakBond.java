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

/**
 * Simple class whose objects contain a pair of indexes that identify a weak
 * bond in a secondary structure.
 * 
 * @author Luca Tesei
 *
 */
public class WeakBond implements Comparable<WeakBond> {

    private final int left; // first member of the bond
    private final int right; // second member of the bond

    /**
     * Construct a pair of indexes representing the weak bond.
     * 
     * @param left  left index (starting with 1)
     * 
     * @param right right index (starting with 1)
     * 
     * @throws RNAInputFileParserException if the left position is less than 1 or if
     *                                     the left position is greater than or
     *                                     equal to the right position.
     */
    public WeakBond(int left, int right) throws RNAInputFileParserException {
	// index checks
	if (left < 1)
	    throw new RNAInputFileParserException("Weak Bond: left index: " + left + " less than 1.");
	if (left >= right)
	    throw new RNAInputFileParserException(
		    "Weak Bond: left index: " + left + " greater than or equal to right index: " + right);
	// checks passed
	this.left = left;
	this.right = right;
    }

    /**
     * @return the left index
     */
    public int getLeft() {
	return left;
    }

    /**
     * @return the right index
     */
    public int getRight() {
	return right;
    }

    /**
     * Determine if this WeakBond crosses with a given other WeakBond.
     * 
     * @param wb the WeakBond to check for crossing with this WeakBond
     * @return true if this WeakBond crosses with the WeakBond {@code wb}
     * @throws NullPointerException     if the passed WeakBond is null
     * @throws IllegalArgumentException if the passed WeakBond is equal to this
     *                                  WeakBond
     */
    protected boolean crossesWith(WeakBond wb) {
	if (wb == null)
	    throw new NullPointerException("Passed Weak Bond was null");
	if (this.equals(wb))
	    throw new IllegalArgumentException("Passed Weak Bond was equal to this one");
	if (wb.left < this.left && this.left < wb.right)
	    return true;
	if (this.left < wb.left && wb.left < this.right)
	    return true;
	return false;
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

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + left;
	result = prime * result + right;
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof WeakBond)) {
	    return false;
	}
	WeakBond other = (WeakBond) obj;
	if (left != other.left) {
	    return false;
	}
	if (right != other.right) {
	    return false;
	}
	return true;
    }

    /*
     * The greater the right index the greater the WeakBond.
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

}
