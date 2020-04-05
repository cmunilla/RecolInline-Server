/* 
 * Copyright 2019-2020 Christophe Vincent Munilla, FR Micro Entreprise - All Rights Reserved
 * 
 * RecolInline Tools Suite
 * @contact cmunilla@cmssi.fr
 */
package cmssi.museum.database.api.format;

import cmssi.museum.controler.api.format.JsonStringFormat;

/**
 * Extended {@link JsonStringFormat} dedicated to field data structure holding textual value format
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
public class StringFieldFormat extends FieldFormat {

	/**
	 * Constructor
	 * 
	 * @param name the String name of the StringFieldFormat to be instantiated
	 */
	public StringFieldFormat(String name) {
		super(name);
	}
	
	@Override
	public String doFormat() {
		StringBuilder builder = new StringBuilder();
		builder.append('"');
		builder.append(super.elements.get(0));
		builder.append('"');
		return builder.toString();
	}
}
