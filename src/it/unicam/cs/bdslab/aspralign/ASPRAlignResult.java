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

import fr.orsay.lri.varna.models.treealign.AlignedNode;
import fr.orsay.lri.varna.models.treealign.Tree;
import fr.orsay.lri.varna.models.treealign.TreeAlign;
import fr.orsay.lri.varna.models.treealign.TreeAlignException;
import fr.orsay.lri.varna.models.treealign.TreeAlignResult;

/**
 * 
 * Object holding the result of the alignment of two structural RNA trees.
 * 
 * @author Luca Tesei
 *
 */
public class ASPRAlignResult {

	private final Tree<String> t1;
	private final Tree<String> t2;
	private final TreeAlignResult<String, String> result;
	private final ScoringFunction f;
	private final double distance;

	/**
	 * Align two structural RNA trees and construct the result.
	 * 
	 * @param t1 first structural RNA tree to align
	 * @param t2 second structural RNA tree to align
	 * @param f the scoring function to align the trees
	 * 
	 * @throws TreeAlignException alignment exception
	 */
	public ASPRAlignResult(Tree<String> t1, Tree<String> t2, ScoringFunction f) throws TreeAlignException {
		this.t1 = t1;
		this.t2 = t2;
		this.f = f;
		TreeAlign<String, String> al = new TreeAlign<String,String>(f);
		this.result = al.align(t1, t2);
		this.distance = result.getDistance();
	}

	/**
	 * @return the first structural RNA tree
	 */
	public Tree<String> getT1() {
		return t1;
	}

	/**
	 * @return the second structural RNA tree
	 */
	public Tree<String> getT2() {
		return t2;
	}
	
	/**
	 * @return the scoring function used to align the trees
	 */
	public ScoringFunction getScoringFunction() {
		return this.f;
	}

	/**
	 * 
	 * Return the distance of the aligned trees, i.e. the minimum cost of the
	 * operations to align them.
	 * 
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * 
	 * @return the alignment of the original structural RNA trees
	 */
	public Tree<AlignedNode<String, String>> getAlignedTree() {
		return this.result.getAlignment();
	}

}
