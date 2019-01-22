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

import fr.orsay.lri.varna.models.treealign.*;
import it.unicam.cs.bdslab.aspralign.ArcAnnotatedSequence.WeakBond;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Builder for an algebraic RNA tree and a structural RNA tree corresponding to a
 * given arc annotated sequence.
 * 
 * An algebraic RNA tree is the derived tree of a tree grammar whose operators
 * are defined among hairpin loops of an RNA secondary structure. The crossing
 * operator permits to represent any possible pseudoknot in secondary
 * structures.
 * 
 * A structural RNA tree is a tree description of the structural part of an RNA
 * secondary structure that may contain any kind of pseudoknot. It is used to
 * align secondary structures and calculate the distance between them.
 * 
 * @author Luca Tesei
 *
 */
public class AlgebraicRNATreeBuilder {
	// original Arc Annotated Sequence
	private final ArcAnnotatedSequence aas;
	// algebraic RNA tree
	private Tree<String> algebraicTree;
	// structural RNA tree
	private Tree<String> structuralTree;

	/**
	 * Construct an algebraic RNA tree builder starting from an arc annotated
	 * sequence.
	 * 
	 * @param aas an arc annotated sequence
	 */
	public AlgebraicRNATreeBuilder(ArcAnnotatedSequence aas) {
		assert aas != null : "Null Arc Annotated Sequence at Algebraic RNA Tree construction";
		this.aas = aas;
	}

	/**
	 * @return the original arc annotated sequence
	 */
	public ArcAnnotatedSequence getAas() {
		return this.aas;
	}

	/**
	 * @return the algebraic RNA tree built form the arc annotated sequence
	 */
	public Tree<String> getAlgebraicRNATree() {
		if (this.algebraicTree == null) {
			this.algebraicTree = new Tree<String>();
			this.algebraicTree.setValue(Operators.ALGEBRAIC_TREE_ROOT_LABEL);
			buildAlgebraic();
		}
		return this.algebraicTree;
	}

	/*
	 * Main building method. It constructs the algebraic RNA tree root childs and
	 * finds the outermost pseudoloop. Then the recursive builder is started on this
	 * pseudoloop to construct the full algebraic RNA tree recursively.
	 */
	private void buildAlgebraic() {
		// create array for counting openings and closings of loops, by default it is
		// initialized to zeroes, starts at 1, position 0 not used
		int[] c = new int[this.aas.getNucleotides().length() + 1];

		// initialize the counting array
		init(c);

		// init indexes for later recursion call
		int l = 1; // left index, starts at 1, position 0 not used
		int r = this.aas.getNucleotides().length(); // right index

		// determine the head of the structure
		String head;
		while (c[l] == 0)
			l++;
		if (l == 1)
			// empty head
			head = "";
		else
			head = this.aas.getNucleotides().substring(0, l - 1);

		// determine the tail of the structure
		String tail;
		while (c[r] == 0)
			r--;
		r++; // last closing loop has 0 count, but belongs to the loop, so it's not part of
				// the tail
		if (r == this.aas.getNucleotides().length())
			tail = "";
		else
			tail = this.aas.getNucleotides().substring(r, this.aas.getNucleotides().length());

		// create the children of the root
		ArrayList<Tree<String>> rootChilds = new ArrayList<Tree<String>>();
		Tree<String> h = new Tree<String>();
		h.setValue(head);
		rootChilds.add(h);
		Tree<String> ct = new Tree<String>();
		rootChilds.add(ct);
		Tree<String> t = new Tree<String>();
		t.setValue(tail);
		rootChilds.add(t);
		this.algebraicTree.replaceChildrenListBy(rootChilds);

		// the largest pseudoloop is now identified by the interval [l,r]
		assert c[l] == 1 && c[r] == 0 : "Largest pseudoloop at [" + l + "," + r + "]\nCounting array: " + c;

		// create an empty list of zero intervals to detect concatenations
		ArrayList<Interval> zi = new ArrayList<Interval>();

		// find zero intervals in the outermost pseudoloop, if any
		detectZeroIntervals(c, zi, l, r);

		// start the recursive construction of the algebraic RNA Tree on the node ct
		recBuildAlgebraic(ct, c, zi, l, r);
	}

	private void recBuildAlgebraic(Tree<String> ct, int[] c, ArrayList<Interval> zi, int l, int r) {
		assert c[l] == 1 && c[r] == 0 : "Pseudoloop bounds error while parsing at [" + l + "," + r
				+ "]\nCounting array: " + c;
		// decompose pseudoloop [l,r]

		// determine if the pseudoloop is a nesting
		if (this.aas.p[r] == l) {
			// nesting case
			ct.setValue(Operators.NESTING_LABEL);

			// decrease counting array according to the elimination of this hairpin
			for (int i = l; i < r; i++)
				c[i]--;

			// init k and indexes for later recursion call
			int k = 2;
			int lp = l + 1;
			int rp = r;

			// determine the starting of the next loop on the left and increment k
			while (c[lp] == 0 && lp < rp) {
				lp++;
				k++;
			}
			if (lp == rp) {
				// no subloops of this nesting, there will be no more complex subtrees

				// revert to just a single hairpin
				ct.setValue(Operators.HAIRPIN_LABEL);
				ArrayList<Tree<String>> child = new ArrayList<Tree<String>>();
				Tree<String> q = new Tree<String>();
				String label = this.aas.getNucleotides().charAt(l - 1) + "*"
						+ this.aas.getNucleotides().substring(l, r - 1) + this.aas.getNucleotides().charAt(r - 1) + "*";
				q.setValue(label);
				// use the following line to obtain the string without the "*"s
				// q.setValue(this.aas.getNucleotides().substring(l - 1, r));
				child.add(q);
				ct.replaceChildrenListBy(child);

				// end recursion
				return;
			}

			// determine the ending of the last loop on the right
			while (c[rp] == 0)
				rp--;
			rp++; // last closing loop has 0 count, but belongs to the loop

			// the new pseudoloop to consider has bounds [lp,rp]
			assert c[lp] == 1 && c[rp] == 0 : "Determined wrong pseudoloop at [" + lp + "," + rp + "]\nCounting array: "
					+ c;

			// create the empty node for building the rest of the tree on the left
			Tree<String> rest = new Tree<String>();

			// create node with operator and k
			Tree<String> op = new Tree<String>();
			op.setValue("(" + Operators.NESTING_LABEL + "," + k + ")");

			// create hairpin subtree
			Tree<String> h = createHairpinTree(l, r);

			// update tree
			ArrayList<Tree<String>> nestChilds = new ArrayList<Tree<String>>();
			nestChilds.add(rest);
			nestChilds.add(op);
			nestChilds.add(h);
			ct.replaceChildrenListBy(nestChilds);

			// create an empty list of zero intervals to detect concatenations
			ArrayList<Interval> zip = new ArrayList<Interval>();

			// find zero intervals in the new pseudoloop, if any
			detectZeroIntervals(c, zip, lp, rp);

			// recursive construction of the algebraic RNA subTree on the node rest
			recBuildAlgebraic(rest, c, zip, lp, rp);
		} else {
			// not nesting case
			if (zi.isEmpty()) {
				// crossing
				ct.setValue(Operators.CROSSING_LABEL);

				// left end of the rightmost crossing hairpin
				int lpp = this.aas.p[r];
				// index for the right end of the new pseudoloop
				int rp = r;
				// value of k for this crossing
				int k = lpp - l + 1;

				// decrease counting array according to the elimination of this hairpin
				for (int i = lpp; i < r; i++)
					c[i]--;

				// determine the ending of the last loop on the right
				while (c[rp] == 0)
					rp--;
				rp++; // last closing loop has 0 count, but belongs to the loop

				// the new pseudoloop to consider has bounds [l,rp]
				assert c[l] == 1 && c[rp] == 0 : "Determined wrong pseudoloop at [" + l + "," + rp
						+ "]\nCounting array: " + c;

				// create the empty node for building the rest of the tree on the left
				Tree<String> rest = new Tree<String>();

				// create node with operator and k
				Tree<String> op = new Tree<String>();
				op.setValue("(" + Operators.CROSSING_LABEL + "," + k + ")");

				// create hairpin subtree
				Tree<String> h = createHairpinTree(lpp, r);

				// update tree
				ArrayList<Tree<String>> crossChilds = new ArrayList<Tree<String>>();
				crossChilds.add(rest);
				crossChilds.add(op);
				crossChilds.add(h);
				ct.replaceChildrenListBy(crossChilds);

				// create an empty list of zero intervals to detect concatenations
				ArrayList<Interval> zip = new ArrayList<Interval>();

				// find zero intervals in the new pseudoloop, if any
				detectZeroIntervals(c, zip, l, rp);

				// recursive construction of the algebraic RNA subTree on the node rest
				recBuildAlgebraic(rest, c, zip, l, rp);

			} else {
				// concatenation
				ct.setValue(Operators.CONCATENATION_LABEL);

				// get the rightmost zero interval
				Interval rmzi = zi.get(zi.size() - 1);
				zi.remove(zi.size() - 1);

				// find boundaries of the right pseudoloop and of the left pseudoloop
				int ll = l;
				int lr = rmzi.i - 1;
				int rl = rmzi.j;
				int rr = r;

				// the new right pseudoloop to consider has bounds [rl,rr]
				assert c[rl] == 1 && c[rr] == 0 : "Determined wrong pseudoloop at [" + rl + "," + rr
						+ "]\nCounting array: " + c;

				// create the node for building the left part
				Tree<String> left = new Tree<String>();

				// create node with primary sequence in the middle
				Tree<String> seq = new Tree<String>();
				if (rmzi.isEmpty())
					seq.setValue("(" + Operators.CONCATENATION_LABEL + ",)");
				else
					seq.setValue("(" + Operators.CONCATENATION_LABEL + ","
							+ this.aas.getNucleotides().substring(lr, rl - 1) + ")");

				// create node for building the right part
				Tree<String> right = new Tree<String>();

				// update tree
				ArrayList<Tree<String>> concChilds = new ArrayList<Tree<String>>();
				concChilds.add(left);
				concChilds.add(seq);
				concChilds.add(right);
				ct.replaceChildrenListBy(concChilds);

				// create an empty list of zero intervals to detect concatenations in the right
				// pseudoloop
				ArrayList<Interval> zir = new ArrayList<Interval>();

				// find zero intervals in the right pseudoloop, if any
				detectZeroIntervals(c, zir, rl, rr);

				// recursive construction of the algebraic RNA subTree on the right
				recBuildAlgebraic(right, c, zir, rl, rr);

				// recursive construction of the algebraic RNA subTree on the left
				recBuildAlgebraic(left, c, zi, ll, lr);
			}
		}
	}

	/*
	 * Initialize the counting array c. The counting array is defined as follows:
	 * the count starts at zero. At any start of an hairpin loop in the secondary
	 * structure the counting increases by one. At any stop of an hairpin loop in
	 * the secondary structure the counting decreases by one. All the positions are
	 * assigned the corresponding count. If in the position there is not a start or
	 * a stop of an hairpin loop the current value of count is assigned.
	 */
	private void init(int[] c) {
		int count = 0;
		for (int i = 1; i <= this.aas.getNucleotides().length(); i++) {
			if (loop_start(i)) {
				// a loop has just started in the arc annotated sequence, so count is
				// incremented
				count++;
				c[i] = count;
			} else if (loop_stop(i)) {
				// a loop has just stopped in the arc annotated sequence, so count is
				// decremented
				count--;
				c[i] = count;
			} else // no star/stop of a loop, the value of count is kept
				c[i] = count;
		}
		assert count == 0 : "Value of count after initialization of counting array: " + count + "\nCounting array: "
				+ c;
	}

	/*
	 * Tells if at position i there is the starting of an hairpin loop of the
	 * secondary structure represented by the original arc annotated sequence.
	 */
	private boolean loop_start(int i) {
		return this.aas.p[i] != 0 && this.aas.p[i] > i;
	}

	/*
	 * Tells if at position i there is the ending of an hairpin loop of the
	 * secondary structure represented by the original arc annotated sequence.
	 */
	private boolean loop_stop(int i) {
		return this.aas.p[i] != 0 && this.aas.p[i] < i;
	}

	/*
	 * Finds all the (possibly empty) zero intervals inside the pseudoloop [l,r]. A
	 * zero interval is a section of the primary sequence that separates two
	 * concatenated pseudoloops. It is called zero interval because in the counting
	 * array the count goes to zero before the end of the pseudoloop. A zero
	 * interval always starts at the first position after the count went to zero. It
	 * stops at the first position in which the counting raises to one again. If
	 * these positions coincide the zero interval is empty. All the found intervals
	 * are put into the list zi, which is assumed to be empty at the calling time.
	 */
	private void detectZeroIntervals(int[] c, ArrayList<Interval> zi, int l, int r) {
		assert l < r : "Empty pseudoloop while detecting zero intervals at [" + l + "," + r + "]";
		assert c[l] == 1 && c[r] == 0 : "Pseudoloop bounds error while detecting zero intervals at [" + l + "," + r
				+ "]\nCounting array: " + c;
		assert zi.isEmpty() : "Not empty zero interval list while detecting zero intervals: " + zi;
		int i = l;
		do {
			// search for the next zero interval
			while (c[i] != 0)
				i++;
			if (i == r)
				break; // reached end of the interval, stop searching more zero intervals
			i++;
			// determine start of the zero interval
			int start = i;
			// search for the end of the zero interval
			while (c[i] == 0)
				i++;
			// determine the stop of the zero interva, if start == stop, the zero interval
			// is empty
			int stop = i;
			// create the interval and add it to the list
			Interval interval = new Interval(start, stop);
			zi.add(interval);
		} while (true);
	}

	private Tree<String> createHairpinTree(int l, int r) {
		Tree<String> t = new Tree<String>();
		t.setValue(Operators.HAIRPIN_LABEL);
		ArrayList<Tree<String>> c = new ArrayList<Tree<String>>();
		Tree<String> h = new Tree<String>();
		String label = this.aas.getNucleotides().charAt(l - 1) + "*" + this.aas.getNucleotides().substring(l, r - 1)
				+ this.aas.getNucleotides().charAt(r - 1) + "*";
		h.setValue(label);
		// use the following line to obtain the string without the "*"s
		// h.setValue(this.aas.getNucleotides().substring(l - 1, r));
		c.add(h);
		t.replaceChildrenListBy(c);
		return t;
	}

	/**
	 * @return the structural RNA tree built from the arc annotated sequence
	 */
	public Tree<String> getStructuralRNATree() {
		if (this.structuralTree == null)
			buildStructural();
		return this.structuralTree;

	}

	private void buildStructural() {
		// create array for counting openings and closings of loops, by default it is
		// initialized to zeroes, starts at 1, position 0 not used
		int[] c = new int[this.aas.getNucleotides().length() + 1];

		// initialize the counting array
		init(c);

		// init indexes for later recursion call
		int l = 1; // left index, starts at 1, position 0 not used
		int r = this.aas.getNucleotides().length(); // right index

		// move l to the start of the structure
		while (c[l] == 0)
			l++;

		// move r to the tail of the structure
		while (c[r] == 0)
			r--;
		r++; // last closing loop has 0 count, but belongs to the loop, so it's not part of
				// the tail

		// the largest pseudoloop is now identified by the interval [l,r]
		assert c[l] == 1 && c[r] == 0 : "Largest pseudoloop at [" + l + "," + r + "]\nCounting array: " + c;

		// create an empty list of zero intervals to detect concatenations
		ArrayList<Interval> zi = new ArrayList<Interval>();

		// find zero intervals in the outermost pseudoloop, if any
		detectZeroIntervals(c, zi, l, r);

		// create a copy of the list of weak bounds, ordered by the right index (default
		// ordering of WeakBond)
		List<ArcAnnotatedSequence.WeakBond> bounds = new ArrayList<>();
		bounds.addAll(this.aas.getBonds());
		Collections.sort(bounds);

		// create the root node of the structural RNA tree
		Tree<String> t = new Tree<String>();

		// start the recursive construction of the algebraic RNA Tree on the node ct
		recBuildStructural(t, bounds, c, zi, l, r);

		// assign to the root of this tree
		this.structuralTree = t;
	}

	private void recBuildStructural(Tree<String> ct, List<WeakBond> bounds, int[] c, ArrayList<Interval> zi, int l,
			int r) {
		// checks
		assert c[l] == 1 && c[r] == 0 : "Pseudoloop bounds error while parsing at [" + l + "," + r
				+ "]\nCounting array: " + c;
		WeakBond lastBound = bounds.get(bounds.size() - 1);
		assert lastBound.getRight() == r : "Mismatch among indexes of arc annotated sequence and of "
				+ "determined loop in WeakBond: (" + l + "," + r + ") vs (" + lastBound.getLeft() + ","
				+ lastBound.getRight() + ")";

		// decompose pseudoloop [l,r]

		// determine if the pseudoloop is a nesting
		if (this.aas.p[r] == l) {
			// nesting case

			ct.setValue(Operators.NESTING_LABEL);

			// decrease counting array according to the elimination of this hairpin
			for (int i = l; i < r; i++)
				c[i]--;

			// init indexes for later recursion call
			int lp = l + 1;
			int rp = r;

			// determine the starting of the next loop on the left and increment k
			while (c[lp] == 0 && lp < rp)
				lp++;
			if (lp == rp) {
				// no subloops of this nesting, there will be no more complex subtrees
				assert bounds.size() == 1 : "Mismatch in base case of building structural RNA "
						+ "tree: size of list of bounds different from one";

				// revert to just a single hairpin
				ct.setValue(Operators.HAIRPIN_LABEL + "(" + lastBound.getLeft() + "," + lastBound.getRight() + ")");

				// end recursion
				return;
			}

			// determine the ending of the last loop on the right
			while (c[rp] == 0)
				rp--;
			rp++; // last closing loop has 0 count, but belongs to the loop

			// the new pseudoloop to consider has bounds [lp,rp]
			assert c[lp] == 1 && c[rp] == 0 : "Determined wrong pseudoloop at [" + lp + "," + rp + "]\nCounting array: "
					+ c;

			// create the empty node for building the rest of the tree on the left
			Tree<String> rest = new Tree<String>();

			// create hairpin subtree
			Tree<String> h = new Tree<String>();
			h.setValue(Operators.HAIRPIN_LABEL + "(" + lastBound.getLeft() + "," + lastBound.getRight() + ")");

			// update tree
			ArrayList<Tree<String>> nestChilds = new ArrayList<Tree<String>>();
			nestChilds.add(rest);
			nestChilds.add(h);
			ct.replaceChildrenListBy(nestChilds);

			// create an empty list of zero intervals to detect concatenations
			ArrayList<Interval> zip = new ArrayList<Interval>();

			// find zero intervals in the new pseudoloop, if any
			detectZeroIntervals(c, zip, lp, rp);

			// remove last bound from the list of bounds
			bounds.remove(bounds.size() - 1);

			// recursive construction of the structural RNA subTree on the node rest
			recBuildStructural(rest, bounds, c, zip, lp, rp);
		} else {
			// not nesting case
			if (zi.isEmpty()) {
				// crossing case

				// determine number of crossings and set label
				int numberOfCrossings = determineNumberOfCrossings(bounds);
				ct.setValue("(" + Operators.CROSSING_LABEL + "," + numberOfCrossings + ")");

				// left end of the rightmost crossing hairpin
				int lpp = this.aas.p[r];
				// index for the right end of the new pseudoloop
				int rp = r;

				// decrease counting array according to the elimination of this hairpin
				for (int i = lpp; i < r; i++)
					c[i]--;

				// determine the ending of the last loop on the right
				while (c[rp] == 0)
					rp--;
				rp++; // last closing loop has 0 count, but belongs to the loop

				// the new pseudoloop to consider has bounds [l,rp]
				assert c[l] == 1 && c[rp] == 0 : "Determined wrong pseudoloop at [" + l + "," + rp
						+ "]\nCounting array: " + c;

				// create the empty node for building the rest of the tree on the left
				Tree<String> rest = new Tree<String>();

				// create hairpin subtree
				Tree<String> h = new Tree<String>();
				h.setValue(Operators.HAIRPIN_LABEL + "(" + lastBound.getLeft() + "," + lastBound.getRight() + ")");

				// update tree
				ArrayList<Tree<String>> crossChilds = new ArrayList<Tree<String>>();
				crossChilds.add(rest);
				crossChilds.add(h);
				ct.replaceChildrenListBy(crossChilds);

				// create an empty list of zero intervals to detect concatenations
				ArrayList<Interval> zip = new ArrayList<Interval>();

				// find zero intervals in the new pseudoloop, if any
				detectZeroIntervals(c, zip, l, rp);

				// remove last bound from the list of bounds
				bounds.remove(bounds.size() - 1);

				// recursive construction of the algebraic RNA subTree on the node rest
				recBuildStructural(rest, bounds, c, zip, l, rp);

			} else {
				// concatenation
				ct.setValue(Operators.CONCATENATION_LABEL);

				// get the rightmost zero interval
				Interval rmzi = zi.get(zi.size() - 1);
				zi.remove(zi.size() - 1);

				// find boundaries of the right pseudoloop and of the left pseudoloop
				int ll = l;
				int lr = rmzi.i - 1;
				int rl = rmzi.j;
				int rr = r;

				// the new right pseudoloop to consider has bounds [rl,rr]
				assert c[rl] == 1 && c[rr] == 0 : "Determined wrong pseudoloop at [" + rl + "," + rr
						+ "]\nCounting array: " + c;

				// create the node for building the left part
				Tree<String> left = new Tree<String>();

				// create node for building the right part
				Tree<String> right = new Tree<String>();

				// update tree
				ArrayList<Tree<String>> concChilds = new ArrayList<Tree<String>>();
				concChilds.add(left);
				concChilds.add(right);
				ct.replaceChildrenListBy(concChilds);

				// create an empty list of zero intervals to detect concatenations in the right
				// pseudoloop
				ArrayList<Interval> zir = new ArrayList<Interval>();

				// find zero intervals in the right pseudoloop, if any
				detectZeroIntervals(c, zir, rl, rr);

				List<WeakBond> lbounds = new ArrayList<>();
				List<WeakBond> rbounds = new ArrayList<>();

				splitBounds(bounds, ll, lr, rl, rr, lbounds, rbounds);

				// recursive construction of the algebraic RNA subTree on the right
				recBuildStructural(right, rbounds, c, zir, rl, rr);

				// recursive construction of the algebraic RNA subTree on the left
				recBuildStructural(left, lbounds, c, zi, ll, lr);
			}
		}
	}

	private void splitBounds(List<WeakBond> bounds, int ll, int lr, int rl, int rr, List<WeakBond> lbounds,
			List<WeakBond> rbounds) {
		for (WeakBond b : bounds) {
			if (ll <= b.getLeft() && b.getRight() <= lr)
				lbounds.add(b);
			else if (rl <= b.getLeft() && b.getRight() <= rr)
				rbounds.add(b);
			else
				assert false : "Error in splitting bounds weak bound (" + b.getLeft() + "," + b.getRight()
						+ ") not in range [" + ll + "," + lr + "] and not in range [" + rl + "," + rr + "]";
		}
		assert lbounds.size() > 0 : "Error in splitting bounds: left bounds list empty";
		assert rbounds.size() > 0 : "Error in splitting bounds: right bounds list empty";

	}

	private int determineNumberOfCrossings(List<WeakBond> bounds) {
		int n = 0;
		int lastBoundLeftIndex = bounds.get(bounds.size() - 1).getLeft();
		for (int i = bounds.size() - 2; i >= 0; i--) {
			WeakBond b = bounds.get(i);
			if (b.getLeft() < lastBoundLeftIndex && lastBoundLeftIndex < b.getRight())
				n++;
		}
		assert n > 0 : "Crossing number equal to zero!";
		return n;
	}

	/*
	 * Service class for holding zero intervals.
	 */
	protected class Interval {
		private int i;
		private int j;

		/**
		 * @param i
		 * @param j
		 */
		protected Interval(int i, int j) {
			assert i <= j : "Interval [" + i + "," + j + "]";
			this.i = i;
			this.j = j;
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
			result = prime * result + i;
			result = prime * result + j;
			return result;
		}

		/*
		 * A zero interval is empty if i == j
		 */
		protected boolean isEmpty() {
			return this.i == this.j;
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
			Interval other = (Interval) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (i != other.i)
				return false;
			if (j != other.j)
				return false;
			return true;
		}

		private AlgebraicRNATreeBuilder getOuterType() {
			return AlgebraicRNATreeBuilder.this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Interval [i=" + i + ", j=" + j + "]";
		}

	}

}
