/* 
 * Copyright 2019-2020 Christophe Vincent Munilla, FR Micro Entreprise - All Rights Reserved
 * 
 * RecolInline Tools Suite
 * @contact cmunilla@cmssi.fr
 */
package cmssi.museum.database.api.format;

import cmssi.museum.controler.api.format.JsonStringFormat;
import cmssi.museum.controler.api.ArtifactField;
import cmssi.museum.controler.api.FieldVisibility;

/**
 * Extended {@link JsonStringFormat} dedicated to field data structure format
 *  
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
public class FieldFormat extends JsonStringFormat<String> implements ArtifactField {

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
		StringBuilder builder = new StringBuilder();
		builder.append('"').append(this.getName()).append("\" : {");

		if (!FieldVisibility.HIDDEN.equals(this.visibility)) {
			builder.append("\"value\" : ");
			builder.append(doFormat());		
			builder.append(", ");
		}
		if(this.constraints != null) {
			builder.append("\"constraints\" :");
			builder.append(this.constraints);		
			builder.append(", ");
		}
		builder.append(" , \"visibility\" : \"");
		builder.append(this.visibility.name());
		builder.append(" , \"type\" : \"");
		builder.append(this.labelType);
		builder.append("\" }");
		return builder.toString();
	}
}