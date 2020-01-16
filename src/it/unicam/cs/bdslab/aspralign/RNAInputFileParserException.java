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
 * @author Luca Tesei
 *
 */
public class RNAInputFileParserException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4540612561494091099L;

	/**
	 * 
	 */
	public RNAInputFileParserException() {
	}

	/**
	 * @param message Description message
	 */
	public RNAInputFileParserException(String message) {
		super(message);
	}

	/**
	 * @param cause Cause of the exception
	 */
	public RNAInputFileParserException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message Description message
	 * @param cause Cause of the exception
	 */
	public RNAInputFileParserException(String message, Throwable cause) {
		super(message, cause);
	}

}
