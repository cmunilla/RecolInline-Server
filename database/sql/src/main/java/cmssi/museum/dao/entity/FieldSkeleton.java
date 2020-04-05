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
package cmssi.museum.dao.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * An Entity gathering all the properties relative to a {@link Field}
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
@Entity
public class FieldSkeleton implements Serializable
{
	/**
	 * Generated long ID
	 */
	private static final long serialVersionUID = -7350067258163911929L; 
    
    /**
     * the Integer identifier of the {@link Field} on which this FieldSkeleton is based
     */
    @Id
    private Integer idField;
    
    /**
     * the Integer identifier of the {@link Model} to which belongs to the {@link Field} 
     * on which this FieldSkeleton is based
     */
    private Integer idModel; 

    /**
     * the Integer identifier of the {@link Type} of the {@link Field} on which this 
     * FieldSkeleton is based
     */
    private Integer idType; 
    
    /**
     * the Integer identifier of the {@link Visibility} of the {@link Field} on which this 
     * FieldSkeleton is based, for a specific {@link Role} (the one of the requirer for which 
     * this FieldSkeleton was instantiated )
     */
    private Integer idVisibility; 

    /**
     * the String name of the {@link Model} to which belongs to the {@link Field} 
     * on which this FieldSkeleton is based
     */
    private String labelModel;

    /**
     * the String name of the {@link Field} on which this FieldSkeleton is based
     */
    private String labelField; 
    
    /**
     * the String name of the {@link Type} of the {@link Field} on which this 
     * FieldSkeleton is based
     */
    private String labelType; 

    /**
     * the String name of the {@link Visibility} of the {@link Field} on which this 
     * FieldSkeleton is based, for a specific {@link Role} (the one of the requirer for which 
     * this FieldSkeleton was instantiated )
     */
    private String labelVisibility; 
    
    /**
     * the JSON formated String of the constraints applying on the {@link Field} on which this 
     * FieldSkeleton is based
     */
    private String constraints; 

    /**
     * defines whether the content the {@link Field} on which this FieldSkeleton is based is 
     * a chars sequence (needed when converting into JSON data structure)
     */
    private Boolean isText;
    
    /**
     * Constructor
     */
    public FieldSkeleton() {}
     
    /**
     * Constructor
     * 
     * @param idModel the Integer identifier of the {@link Model} to which belong the {@link Field} 
     * on which the FieldSkeleton to be instantiated is based
     * @param idField the Integer identifier of the {@link Field} on which the FieldSkeleton to 
     * be instantiated is based
     * @param idType the Integer identifier of the {@link Type} of the {@link Field} on which the 
     * FieldSkeleton to be instantiated is based
     * @param idVisibility the Integer identifier of the {@link Visibility} applying on the {@link 
     * Field} on which the FieldSkeleton to be instantiated is based
     * @param labelModel the String name of the {@link Model} to which belong the {@link Field} 
     * on which the FieldSkeleton to be instantiated is based
     * @param labelField the String name of the {@link Field} on which the FieldSkeleton to 
     * be instantiated is based
     * @param labelType the String name of the {@link Type} of the {@link Field} on which the 
     * FieldSkeleton to be instantiated is based
     * @param labelVisibility the String name of the {@link Visibility} applying on the {@link 
     * Field} on which the FieldSkeleton to be instantiated is based
     * @param constraints the JSON formated String defining the constraints applying on the {@link 
     * Field} on which the FieldSkeleton to be instantiated is based
     * @param isText defines whether the content of the {@link Field} on which the FieldSkeleton 
     * to be instantiated is based is a chars sequence
     */
    public FieldSkeleton(Integer idModel, Integer idField, 
    	Integer idType, Integer idVisibility, String labelModel, String labelField, 
    	String labelType, String labelVisibility, String constraints, Boolean isText){
        this.idModel = idModel;
        this.idField = idField;
        this.idType = idType;
        this.idVisibility = idVisibility;
        this.labelModel = labelModel;
        this.labelField = labelField;
        this.labelType = labelType;
        this.labelVisibility = labelVisibility;
        this.constraints = constraints;
        this.isText = isText;   
    }

	/**
	 * Returns true if the Line wrapped by this FieldSkeleton is a chars sequence ; 
	 * Otherwise returns false
	 * 
	 * @return 
	 * <ul>
	 *     <li>true if the held value is a chars sequence</li>
	 *     <li>false otherwise</li>
	 * </ul>
	 */
	public boolean isText() {
		return this.isText;
	}

	/**
	 * Defines whether the Line wrapped by this FieldSkeleton is a chars sequence ;
	 *  
	 * @param isText 
	 * <ul>
	 *     <li>true if the held value is a chars sequence</li>
	 *     <li>false otherwise</li>
	 * </ul>
	 */
	public void isText(Boolean isText) {
		this.isText = isText;
	}

	/**
	 * Returns the Integer identifier of the {@link Model} to which belong the {@link Field} whose 
	 * the {@link Line} wrapped by this FieldSkeleton is the content
     * 
	 * @return the Integer identifier of the {@link Model} to which belong the {@link Field} whose 
	 * the {@link Line} is the content of
	 */
	public Integer getIdModel() {
		return idModel;
	}

	/**
	 * Defines the Integer identifier of the {@link Model} to which belong the {@link Field} whose 
	 * the {@link Line} wrapped by this FieldSkeleton is the content
     * 
	 * @param idModel the Integer identifier of the {@link Model} to which belong the {@link Field} whose 
	 * the {@link Line} is the content of
	 */
	public void setIdModel(Integer idModel) {
		this.idModel = idModel;
	}

	/**
	 * Returns the Integer identifier of the {@link Field} whose the {@link Line} wrapped by this 
	 * FieldSkeleton is the content of
     * 
	 * @return  the Integer identifier of the {@link Field} whose the {@link Line} is the content of
	 */
	public Integer getIdField() {
		return idField;
	}

	/**
	 * Defines the Integer identifier of the {@link Field} whose the {@link Line} wrapped by this 
	 * FieldSkeleton is the content of
     * 
	 * @param idField the Integer identifier of the {@link Field} whose the {@link Line} is the 
	 * content of
	 */
	public void setIdField(Integer idField) {
		this.idField = idField;
	}

	/**
	 * Returns the String name of the {@link Model} to which belong the {@link Field} whose the 
	 * {@link Line} wrapped by this FieldSkeleton is the content of
     * 
	 * @return the String name of the {@link Model} to which belong the {@link Field} whose the 
	 * {@link Line} is the content of
	 */
	public String getLabelModel() {
		return labelModel;
	}

	/**
	 * Defines the String name of the {@link Model} to which belong the {@link Field} whose the 
	 * {@link Line} wrapped by this FieldSkeleton is the content of
     * 
	 * @param labelModel the String name of the {@link Model} to which belong the {@link Field} whose the 
	 * {@link Line} is the content of
	 */
	public void setLabelModel(String labelModel) {
		this.labelModel = labelModel;
	}

	/**
	 * Returns the String name of this FieldSkeleton
     * 
	 * @return this FieldSkeleton's String name
	 */
	public String getLabelField() {
		return labelField;
	}

	/**
	 * Defines the String name of this FieldSkeleton
     * 
	 * @param labelField the String name to be defined
	 */
	public void setLabelField(String labelField) {
		this.labelField = labelField;
	}
	
	/**
     * Returns the Integer identifier of the {@link cmssi.museum.dao.entity.Type} of 
     * this FieldSkeleton
     * 
     * @return the Integer identifier of the {@link cmssi.museum.dao.entity.Type} of 
     * this FieldSkeleton
     */
	public Integer getIdType() {
		return idType;
	}

	/**
     * Defines the Integer identifier of the {@link cmssi.museum.dao.entity.Type} of 
     * this FieldSkeleton
     * 
     * @param idType the Integer identifier of the {@link cmssi.museum.dao.entity.Type} of 
     * this FieldSkeleton
     */
	public void setIdType(Integer idType) {
		this.idType = idType;
	}
	
	/**
     * Returns the Integer identifier of the {@link cmssi.museum.dao.entity.Visibility} applying 
     * on this FieldSkeleton
     * 
     * @return the Integer identifier of the {@link cmssi.museum.dao.entity.Visibility} applying 
     */	
	public Integer getIdVisibility() {
		return idVisibility;
	}

	/**
     * Defines the Integer identifier of the {@link cmssi.museum.dao.entity.Visibility} applying 
     * on this FieldSkeleton
     * 
     * @param idVisibility the Integer identifier of the {@link cmssi.museum.dao.entity.Visibility} 
     * applying 
     */	
	public void setIdVisibility(Integer idVisibility) {
		this.idVisibility = idVisibility;
	}

	/**
     * Returns the String name of the {@link cmssi.museum.dao.entity.Type} of 
     * this FieldSkeleton
     * 
     * @return the String name of the {@link cmssi.museum.dao.entity.Type} 
     * of this FieldSkeleton
     */
	public String getLabelType() {
		return labelType;
	}

	/**
     * Defines the String name of the {@link cmssi.museum.dao.entity.Type} of 
     * this FieldSkeleton
     * 
     * @param labelType the String name of the {@link cmssi.museum.dao.entity.Type} 
     * of this FieldSkeleton
     */
	public void setLabelType(String labelType) {
		this.labelType = labelType;
	}
	
	/**
     * Returns the String name of the {@link cmssi.museum.dao.entity.Visibility} applying 
     * on this FieldSkeleton
     * 
     * @return the String name of the {@link cmssi.museum.dao.entity.Visibility} applying
     */
	public String getLabelVisibility() {
		return this.labelVisibility;
	}

	/**
     * Defines the String name of the {@link cmssi.museum.dao.entity.Visibility} applying on 
     * this FieldSkeleton
     * 
     * @param labelVisibility the String name of the {@link cmssi.museum.dao.entity.Visibility} 
     * applying
     */
	public void setLabelVisibility(String labelVisibility) {
		this.labelVisibility = labelVisibility;
	}
	
	/**
     * Returns the JSON formated String specifying the constraints applying on 
     * this FieldSkeleton
     * 
     * @return the JSON formated String defining the constraints applying on 
     * this FieldSkeleton
     */
	public String getConstraints() {
		return constraints;
	}

	/**
     * Defines the JSON formated String defining the constraints applying on this 
     * FieldSkeleton
     * 
     * @param constraints the JSON formated String defining the constraints applying 
     * on this FieldSkeleton
     */
	public void setConstraints(String constraints) {
		this.constraints = constraints;
	}
}
