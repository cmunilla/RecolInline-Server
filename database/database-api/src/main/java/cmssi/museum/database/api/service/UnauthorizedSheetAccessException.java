/*
 * MIT License
 *
 * Copyright (c) 2019 - 2020  Christophe Munilla
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package cmssi.museum.database.api.service;


/**
 * Triggered when an unauthorized access to a sheet data structure is detected
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
public class UnauthorizedSheetAccessException extends Exception {

	/**
	 * Generated long serial ID 
	 */
	private static final long serialVersionUID = 563678762326884525L;

	/**
	 * Constructor
	 * 
	 * @param message the error message
	 */
	public UnauthorizedSheetAccessException(String message) {
		super(message);
	}

	/**
	 * Constructor
	 * 
	 * @param cause the {@link Throwable} cause of the UnauthorizedSheetAccessException to be
	 * instantiated
	 */
	public UnauthorizedSheetAccessException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor
	 * 
	 * @param message the error message
	 * @param cause the {@link Throwable} cause of the UnauthorizedSheetAccessException to be
	 * instantiated
	 */
	public UnauthorizedSheetAccessException(String message, Throwable cause) {
		super(message, cause);
	}

}
