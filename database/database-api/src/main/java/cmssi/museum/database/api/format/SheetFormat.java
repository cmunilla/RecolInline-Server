/* 
 * Copyright 2019-2020 Christophe Vincent Munilla, FR Micro Entreprise - All Rights Reserved
 * 
 * RecolInline Tools Suite
 * @contact cmunilla@cmssi.fr
 */
package cmssi.museum.database.api.format;

import cmssi.museum.controler.api.format.JsonStringFormat;
import cmssi.museum.controler.api.ArtifactSheet;

/**
 * Extended {@link JsonStringFormat} dedicated to sheet data structure format
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
public class SheetFormat extends JsonStringFormat<ModelFormat> implements ArtifactSheet<FieldFormat,ModelFormat> {

	private String signature;
	private String hash;

	/**
	 * Constructor
	 */
	public SheetFormat() {
		super();
	}

	@Override
	public String format() {
		var chars = new char[] { ' ' , ',' };
		StringBuilder builder = new StringBuilder();
		if(this.signature != null) {
			builder.append("\"sign\" : \"");
			builder.append(this.signature);
			builder.append('"');
			builder.append(chars[1]);
		}
		if(this.hash != null) {
			builder.append("\"hash\" : \"");
			builder.append(this.hash);
			builder.append('"');
			builder.append(chars[1]);
		}
		builder.append('"').append(this.getName()).append("\" : {");
		super.elements.forEach(e -> {
			String eformat = e.format();
			if(eformat!=null) {
				builder.append(chars[0]);
				builder.append(eformat);
				chars[0] = chars[1];
			}
		});
		builder.append(" }");
		return builder.toString();
	}

	/**
	 * Defines the String name of this SheetFormat
	 * 
	 * @param name this SheetFormat's name
	 */
	public void setName(String name) {
		super.name = name;
	}
	
	@Override
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	@Override
	public void setHash(String hash) {
		this.hash = hash;
	}

	@Override
	public String getSignature() {
		return this.signature;
	}

	@Override
	public String getHash() {
		return this.hash;
	}
}
