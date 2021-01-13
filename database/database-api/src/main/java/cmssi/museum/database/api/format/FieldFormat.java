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

import cmssi.museum.controler.api.ArtifactField;
import cmssi.museum.controler.api.FieldVisibility;
import cmssi.museum.controler.api.format.JsonStringFormat;

/**
 * Extended {@link JsonStringFormat} dedicated to field data structure format
 *  
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
public class FieldFormat extends JsonStringFormat<String> implements ArtifactField {

	protected int identifier;

	protected String constraints = null;

	protected String labelType = null;
	
	protected FieldVisibility visibility = null;

	/**
	 * Constructor
	 * 
	 * @param name the String name of the FieldFormat to be instantiated
	 */
	public FieldFormat(String name) {
		super(name);
	}

	/**
	 * Defines the set of JSON formated constraints applying on the {@link 
	 * cmssi.museum.dao.entity.Field} described by this FieldFormat
	 * 
	 * @param constraints the JSON formated description of the applying constraints
	 */
	public void setConstraints(String constraints) {
		this.constraints = constraints;
	}
	
	/**
	 * Returns the set of JSON formated constraints applying on the {@link 
	 * cmssi.museum.dao.entity.Field} described by this FieldFormat
	 * 
	 * @return the JSON formated description of the applying constraints
	 */
	public String getConstraints() {
		return this.constraints;
	}
	
	/**
     * Defines the String name of the {@link cmssi.museum.dao.entity.Type} of the 
     * {@link cmssi.museum.dao.entity.Field} whose described by this FieldFormat
     * 
     * @param labelType the String name of the {@link cmssi.museum.dao.entity.Type} 
     * of the described {@link cmssi.museum.dao.entity.Field}
     */
	public void setLabelType(String labelType) {
		this.labelType = labelType;
	}

	/**
     * Returns the String name of the {@link cmssi.museum.dao.entity.Type} of the 
     * {@link cmssi.museum.dao.entity.Field} whose described by this FieldFormat
     * 
     * @return the String name of the {@link cmssi.museum.dao.entity.Type} 
     * of the described {@link cmssi.museum.dao.entity.Field}
     */
	public String getLabelType() {
		return this.labelType;
	}
	
	/**
     * Defines the String name of the {@link cmssi.museum.dao.entity.Visibility} 
     * applying on the {@link cmssi.museum.dao.entity.Field} described 
     * by this FieldFormat, for the requirer {@link cmssi.museum.dao.entity.User}
     * 
     * @param labelVisibility the String name of the {@link cmssi.museum.dao.entity.Visibility} 
     * applying on the described {@link cmssi.museum.dao.entity.Field} 
     */
	public void setVisibility(String labelVisibility) {
		this.visibility = FieldVisibility.valueOf(labelVisibility);
	}	

	/**
     * Returns the {@link FieldVisibility} applying on the {@link cmssi.museum.dao.entity.Field} 
     * described by this FieldFormat, for the requirer {@link cmssi.museum.dao.entity.User}
     * 
     * @return the {@link FieldVisibility} applying on the described {@link 
     * cmssi.museum.dao.entity.Field} 
     */
	@Override
	public FieldVisibility getVisibility() {
		return visibility;
	}

	/**
	 * Defines the Integer identifier of the field described by this FieldFormat
	 * 
	 * @param identifier the described field's Integer identifier
	 */
	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}

	/**
	 * Returns the Integer identifier of the field described by this FieldFormat
	 * 
	 * @return the described field's Integer identifier
	 */
	public int getIdentifier() {
		return this.identifier;
	}
	
	/**
	 * Returns the String formated value of the {@link cmssi.museum.dao.entity.Field} 
	 * whose format is described by this FieldFormat and for the associated {@link 
	 * cmssi.museum.dao.entity.Sheet}
	 * 
	 * @return the String formated value of the described {@link cmssi.museum.dao.entity.Field} 
	 */
	public String doFormat() {
		return String.valueOf(super.elements.get(0));
	}
	
	@Override
	public String format() {
		var sep = ',';
		StringBuilder builder = new StringBuilder();
		builder.append('"').append(this.getName()).append("\" : {");
		builder.append("\"id\" : ");
		builder.append(this.identifier);

		if (!FieldVisibility.HIDDEN.equals(this.getVisibility())) {
			builder.append(sep);
			builder.append("\"value\" : ");
			builder.append(doFormat());
		}
		if(this.getConstraints() != null) {
			builder.append(sep);
			builder.append("\"constraints\" :");
			builder.append(this.getConstraints());
		}
		builder.append(sep);
		builder.append("\"visibility\" : \"");
		builder.append(this.getVisibility().name());
		builder.append(sep);
		builder.append("\"type\" : \"");
		builder.append(this.getLabelType());
		builder.append("\" }");
		return builder.toString();
	}
}
