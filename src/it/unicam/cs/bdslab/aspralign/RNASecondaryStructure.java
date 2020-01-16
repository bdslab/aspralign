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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representation of an RNA secondary structure with any kind of pseudoknot. It
 * consists of the nucleotide primary sequence (optional) and of a list of weak
 * bonds given as pairs of positions in the primary sequence. Positions start at
 * 1 and end at the length of the primary sequence.
 * 
 * @author Luca Tesei
 *
 */
public class RNASecondaryStructure {
	// private and protected fields:

	// primary structure, when null this secondary structure has only the structure
	// representation. It can be used to generate the structural RNA tree and it can
	// be aligned with another structure.
	protected String sequence;

	// list of the weak bonds of this structure
	protected List<WeakBond> bonds;

	// length of the sequence; if this structure has no sequence a sub-approximation
	// is computed from the bonds
	protected int size;

	// accessory array to represent weak bonds with pointers; it is useful to
	// quickly access the indexes of the bonds in constant time. 0 value means null
	// pointer; meaningful indexes of the array start at 1, position 0 is not used.
	// If p[i] = j and i < j then there is a weak bond (i,j), otherwise, i.e., if
	// p[i] = j and i > j, there is a weak bond (j,i); always p[i] != i. No more
	// than one pointer for each position is allowed in RNA secondary structures, so
	// one array is sufficient to represent all weak bonds.
	protected int[] p;

	/**
	 * Create an empty secondary structure.
	 */
	public RNASecondaryStructure() {
		this.sequence = null;
		this.size = -1;
		this.bonds = new ArrayList<WeakBond>();
		this.p = null;
	}

	/**
	 * @return the sequence
	 */
	public String getSequence() {
		return sequence;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @return the bonds
	 */
	public List<WeakBond> getBonds() {
		return bonds;
	}

	/**
	 * Add a bond to this structure.
	 * 
	 * @param b the new bond to add
	 * 
	 * @throws RNAInputFileParserException if the indexes of the bonds are not
	 *                                     correct w.r.t. the other bonds or the
	 *                                     limits of the structure
	 */
	public void addBond(WeakBond b) {
		// check if the indexes of the new bond are already present in the current
		// list of bonds
		for (WeakBond wb : this.bonds)
			if (b.getLeft() == wb.getLeft())
				throw new RNAInputFileParserException("Weak Bond left index " + b.getLeft() + " is equal to bond ("
						+ wb.getLeft() + ", " + wb.getRight() + ") left index");
			else if (b.getLeft() == wb.getRight())
				throw new RNAInputFileParserException("Weak Bond left index " + b.getLeft() + " is equal to bond ("
						+ wb.getLeft() + ", " + wb.getRight() + ") right index");
			else if (b.getRight() == wb.getLeft())
				throw new RNAInputFileParserException("Weak Bond right index " + b.getRight() + " is equal to bond ("
						+ wb.getLeft() + ", " + wb.getRight() + ") left index");
			else if (b.getRight() == wb.getRight())
				throw new RNAInputFileParserException("Weak Bond right index " + b.getRight() + " is equal to bond ("
						+ wb.getLeft() + ", " + wb.getRight() + ") right index");
		// check or increase right limit
		if (this.sequence != null) {
			// the size is fixed to the length of the sequence
			if (b.getRight() > this.size)
				throw new RNAInputFileParserException(
						"Weak Bond right index " + b.getRight() + " is greater than the structure size " + this.size);
		} else
		// the size could increase by adding bonds because
		// in this structure the sequence was not set
		if (b.getRight() > this.size)
			// update size
			this.size = b.getRight();
		// checks done: add the bond
		this.bonds.add(b);
	}

	/**
	 * Order the list of bonds and initialise the array p of pointers for the bonds.
	 * To be called when all the weak bonds have been added to the structure.
	 * 
	 * @throws RNAInputFileParserException if called before the determination of the
	 *                                     structure size
	 */
	protected void finalise() {
		// order the bonds, using their natural order
		Collections.sort(this.bonds);
		// check size
		if (this.size == -1)
			throw new RNAInputFileParserException("Error in determining the size of the secondary structure");
		// create the array p
		this.p = new int[this.size + 1]; // position 0 is not used
		// initialise the array p
		for (WeakBond b : this.bonds) {
			p[b.getLeft()] = b.getRight();
			p[b.getRight()] = b.getLeft();
		}
	}

	/**
	 * Determine if this secondary structure is pseudoknotted.
	 * 
	 * @return true, if there are at least two crossing WeakBonds in the structure
	 * 
	 */
	public boolean isPseudoknotted() {
		for (int i = 0; i < this.bonds.size(); i++)
			for (int j = i + 1; j < this.bonds.size(); j++)
				if (this.bonds.get(i).crossesWith(this.bonds.get(j)))
					return true;
		return false;
	}

	/**
	 * Check if all the weak bonds in this structure are Watson-Crick or wobble
	 * pairs, only if this structure has a sequence specified.
	 * 
	 * @throws RNAInputFileParserException if at least one of the weak bonds in this
	 *                                     structure are not Watson-Crick or wobble
	 *                                     pairs
	 */
	public void checkBasePairs() {
		// check if this structure has a sequence
		if (this.sequence == null)
			// do nothing
			return;
		// check all the pairs
		for (WeakBond b : this.bonds) {
		// base pair check
		int index1 = b.getLeft() - 1; // adjustment of indexes wrt the zero-starting indexes of strings
		int index2 = b.getRight() - 1;
		switch (this.sequence.charAt(index1)) {
			case 'A':
				if (this.sequence.charAt(index2) != 'U')
					throw new RNAInputFileParserException("Base pair not allowed in RNA: " + this.sequence.charAt(index1)
							+ "-" + this.sequence.charAt(index2) + " at weak bond (" + b.getLeft() + ", " + b.getRight() + ")");
				else
					break;
			case 'U':
				if (this.sequence.charAt(index2) != 'A' && this.sequence.charAt(index2) != 'G')
					throw new RNAInputFileParserException("Base pair not allowed in RNA: " + this.sequence.charAt(index1)
					+ "-" + this.sequence.charAt(index2) + " at weak bond (" + b.getLeft() + ", " + b.getRight() + ")");
				else
					break;
			case 'C':
				if (this.sequence.charAt(index2) != 'G')
					throw new RNAInputFileParserException("Base pair not allowed in RNA: " + this.sequence.charAt(index1)
					+ "-" + this.sequence.charAt(index2) + " at weak bond (" + b.getLeft() + ", " + b.getRight() + ")");
				else
					break;
			case 'G':
				if (this.sequence.charAt(index2) != 'C' && this.sequence.charAt(index2) != 'U')
					throw new RNAInputFileParserException("Base pair not allowed in RNA: " + this.sequence.charAt(index1)
					+ "-" + this.sequence.charAt(index2) + " at weak bond (" + b.getLeft() + ", " + b.getRight() + ")");
				else
					break;
			}
		}
	}
}
