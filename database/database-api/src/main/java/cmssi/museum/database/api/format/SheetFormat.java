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
package cmssi.museum.database.api.format;

import cmssi.museum.controler.api.format.JsonStringFormat;
import cmssi.museum.database.api.format.builder.SheetFormatBuilder;
import cmssi.lyson.LysonParser;
import cmssi.lyson.handler.mapping.MappingHandler;
import cmssi.museum.controler.api.ArtifactSheet;

/**
 * Extended {@link JsonStringFormat} dedicated to sheet data structure format
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
public class SheetFormat extends JsonStringFormat<ModelFormat> implements ArtifactSheet<FieldFormat,ModelFormat> {

	/**
	 * @param sheet
	 * @return
	 */
	public static SheetFormat valueOf(String sheet) {
		MappingHandler mapping = new MappingHandler(SheetFormatBuilder.class);
		new LysonParser(sheet).parse(mapping);
		SheetFormatBuilder builder = mapping.getMapped();
		return builder.build();
	}
	
	private int identifier;	
	
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
		var sep = ',';
		StringBuilder builder = new StringBuilder();
		builder.append('"').append(this.getName()).append("\" : {");
		builder.append("\"id\" : ");
		builder.append(this.identifier);
		if(this.hash != null) {
			builder.append(sep);
			builder.append("\"hash\" : \"");
			builder.append(this.hash);
			builder.append('"');
		}
		if(this.signature != null) {
			builder.append(sep);
			builder.append("\"sign\" : \"");
			builder.append(this.signature);
			builder.append('"');
		}
		super.elements.forEach(e -> {
			String eformat = e.format();
			if(eformat!=null) {
				builder.append(sep);
				builder.append(eformat);
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

	/**
	 * Defines the Integer identifier of the sheet described by this SheetFormat
	 * 
	 * @param identifier the described sheet's Integer identifier
	 */
	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}

	/**
	 * Returns the Integer identifier of the sheet described by this SheetFormat
	 * 
	 * @return the described sheet's Integer identifier
	 */
	public int getIdentifier() {
		return this.identifier;
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
