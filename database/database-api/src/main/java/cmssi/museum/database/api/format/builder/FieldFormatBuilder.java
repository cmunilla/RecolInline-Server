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
package cmssi.museum.database.api.format.builder;

import cmssi.museum.controler.api.format.JsonStringFormat;
import cmssi.museum.database.api.format.FieldFormat;
import cmssi.museum.database.api.format.SheetFormat;
import cmssi.lyson.annotation.LysonMapping;
import cmssi.lyson.handler.mapping.MappingConfiguration;

/**
 * Extended {@link JsonStringFormat} dedicated to field data structure format
 *  
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
public class FieldFormatBuilder {

	@LysonMapping(mapping=MappingConfiguration.IDENTITY_MAPPING)
	protected String name;

	@LysonMapping(mapping="id")
	protected int identifier;
	
	@LysonMapping(mapping="constraints")
	protected String constraints;

	@LysonMapping(mapping="type")
	protected String type;
	
	@LysonMapping(mapping="visibility")
	protected String visibility;
	
	@LysonMapping(mapping="value")
	protected String value;

	/**
	 * Constructor
	 */
	public FieldFormatBuilder() {
	}

	/**
	 * Defines the String name of the related {@link cmssi.museum.dao.entity.Field} described 
	 * by this FieldFormatBuilder
	 * 
	 * @param name the String name of the described {@link cmssi.museum.dao.entity.Field}
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Defines the set of JSON formated constraints applying on the related {@link 
	 * cmssi.museum.dao.entity.Field} described by this FieldFormatBuilder
	 * 
	 * @param constraints the JSON formated description of the applying constraints
	 */
	public void setConstraints(String constraints) {
		this.constraints = constraints;
	}
	
	/**
     * Defines the String name of the {@link cmssi.museum.dao.entity.Type} of the 
     * related {@link cmssi.museum.dao.entity.Field} described by this FieldFormat
     * 
     * @param labelType the String name of the {@link cmssi.museum.dao.entity.Type} 
     * of the related {@link cmssi.museum.dao.entity.Field}
     */
	public void setType(String type) {
		this.type = type;
	}

	/**
     * Defines the String name of the {@link cmssi.museum.dao.entity.Visibility} 
     * applying on the related {@link cmssi.museum.dao.entity.Field} described 
     * by this FieldFormatBuilder
     * 
     * @param labelVisibility the String name of the {@link cmssi.museum.dao.entity.Visibility} 
     * applying on the related described {@link cmssi.museum.dao.entity.Field} 
     */
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}	

	/**
	 * Defines the String value of the related {@link cmssi.museum.dao.entity.Field} described 
	 * by this FieldFormatBuilder
	 * 
	 * @param value the String value of the related {@link cmssi.museum.dao.entity.Field}
	 */
	public void setValue(String value) {
		this.value = value;
	}	

	/**
	 * Defines the Integer identifier of the related {@link FieldFormat} described by this FieldFormatBuilder
	 * 
	 * @param identifier the Integer identifier of the described {@link FieldFormat}
	 */
	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	/**
	 * Returns the {@link FieldFormat} built on this FieldFormatBuilder 
	 * 
	 * @return the {@link FieldFormat} from this FieldFormatBuilder
	 */
	public FieldFormat build() {
		FieldFormat fieldFormat  = new FieldFormat(this.name);
		fieldFormat.setLabelType(this.type);
		fieldFormat.setConstraints(this.constraints);
		fieldFormat.setIdentifier(this.identifier);
		fieldFormat.setVisibility(this.visibility);
		return fieldFormat;
	}

	/**
	 * Returns the String value of the related {@link FieldFormat} described by this FieldFormatBuilder
	 * 
	 * @return the String value of the related {@link FieldFormat}
	 */
	public String getValue() {
		return this.value;
	}
}
