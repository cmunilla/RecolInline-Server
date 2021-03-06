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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * In the data model a Field provides an information
 * about an artefact
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
@Entity
@Table(name = "Field")
public class Field implements Serializable {
	
	/**
	 * Generated long ID
	 */
	private static final long serialVersionUID = 2496563128427651953L;

	/**
     * the Field's Integer identifier
     */
    @Id
    @GeneratedValue
    @Column
    private Integer idField; 

	/**
     * the Integer identifier of the Type
     * of this Field
     */
    @Column
    private Integer idType; 
    
    /**
     * the Integer identifier of the Model this Field belongs to
     */
    @Column
    private Integer idModel; 

    /**
     * the Field's String name
     */
    @Column
    private String label;
    
    /**
     * Constructor
     */
    public Field() {}
    
    /**
     * Constructor
     * 
     * @param idField the Integer identifier of the Field
     * to be instantiated
     * @param idType the Integer identifier of the Type of
     * the Field to be instantiated
     * @param label the String name of the Field to be instantiated
     */
    public Field(Integer idField, Integer idModel, Integer idType, String label) {
    	this.idField = idField;
    	this.idModel = idModel;
    	this.idType = idType;
    	this.label = label;
    }

	/**
	 * Return this Field's Integer identifier
	 * 
	 * @return the Integer identifier of this Field
	 */
	public Integer getIdField() {
		return idField;
	}

	/**
	 * Defines this Field's Integer identifier
	 * 
	 * @param idField the Integer identifier to be
	 * set
	 */
	public void setIdField(Integer idField){
		this.idField = idField;
	}

	/**
	 * Returns the Integer identifier of the Type
	 * applying to this Field
	 * 
	 * @return the Integer identifier of this Field's
	 * Type
	 */
	public Integer getIdType() {
		return idType;
	}

	/**
	 * Defines the Integer identifier of the Type
	 * applying to this Field
	 * 
	 * @param idType the Integer identifier of the
	 * Type to be set
	 */
	public void setIdType(Integer idType) {
		this.idType = idType;
	}
	/**
	 * Returns the Integer identifier of the Model
	 * this Field belongs to
	 * 
	 * @return the Integer identifier of the Model holding
	 * this Field
	 */
	public Integer getIdModel() {
		return idModel;
	}

	/**
	 * Defines the Integer identifier of the Model
	 * this Field belongs to
	 * 
	 * @param idModel the Integer identifier of the
	 * Model holding this Field
	 */
	public void setIdModel(Integer idModel) {
		this.idModel = idModel;
	}
	
	/**
	 * Returns the String label of this Field
	 * 
	 * @return this Field's String label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Defines the String label of this Field
	 * 
	 * @param label the String label to be set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
}
