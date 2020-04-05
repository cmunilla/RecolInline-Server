/* 
 * Copyright 2019-2020 Christophe Vincent Munilla, FR Micro Entreprise - All Rights Reserved
 * 
 * RecolInline Tools Suite
 * @contact cmunilla@cmssi.fr
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
	 * @param s
	 */
	public UnauthorizedSheetAccessException(String s) {
		super(s);
	}

	/**
	 * @param cause
	 */
	public UnauthorizedSheetAccessException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UnauthorizedSheetAccessException(String message, Throwable cause) {
		super(message, cause);
	}

}
