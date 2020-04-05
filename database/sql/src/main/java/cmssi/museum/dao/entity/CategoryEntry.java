/*
 * MIT License
 *
 * Copyright (c) 2019 Christophe Munilla
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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
/**
 * In the data model a CategoryEntry wraps a 
 * String value associated to a Category
 *   
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
@Entity
@Table(name = "CategoryEntry")
public class CategoryEntry implements Serializable
{
	/**
	 * Generated long ID
	 */
	private static final long serialVersionUID = -6839048157409306750L;

	/**
     * the CategoryEntry's Integer identifier
     */
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idCategoryEntry; 
    
	/**
     * the Integer identifier of the 
     * Category to which belongs this CategoryEntry
     */
    @Column
    private Integer idCategory; 
    
    /**
     * the CategoryEntry's String value
     */
    @Column
    private String label;
     
    /**
     * Constructor
     */
    public CategoryEntry() {}
     
    /**
     * Constructor
     * 
     * @param idCategoryEntry the Integer identifier of the 
     * CategoryEntry to be instantiated
     * @param idCategory the Integer identifier of the Category 
     * to which belongs the CategoryEntry to be instantiated
     * @param label the String value of the CategoryEntry to be
     * instantiated
     */
    public CategoryEntry(Integer idCategoryEntry, Integer idCategory, String label) {
        this.idCategoryEntry = idCategoryEntry;
        this.idCategory = idCategory;
        this.label = label;
    }

    /**
	 * Returns the Integer identifier of this CategoryEntry
	 * 
	 * @return this CategoryEntry's the Integer identifier 
	 */
	public Integer getIdCategoryEntry() {
		return idCategory;
	}

	/**
	 * Defines the Integer identifier of this CategoryEntry
	 
	 * @param idCategory this CategoryEntry's the Integer 
	 * identifier 
	 */
	public void setIdCategoryEntry(Integer idCategoryEntry) {
		this.idCategoryEntry = idCategoryEntry;
	}
	
    /**
	 * Returns the Integer identifier of the Category to 
	 * which this CategoryEntry belongs
	 * 
	 * @return the Category's the Integer identifier for
	 * this CategoryEntry
	 */
	public Integer getIdCategory() {
		return idCategory;
	}

	/**
	 * Defines the Integer identifier of the Category to which
	 * this CategoryEntry belongs
	 
	 * @param idCategory the Category's the Integer identifier 
	 * for this CategoryEntry
	 */
	public void setIdCategory(Integer idCategory) {
		this.idCategory = idCategory;
	}

    /**
     * Defines the String value of this CategoryEntry
     * 
     * @param label the String value to be set
     */
    public void setLabel(String label) {
    	this.label = label;
    }
    
    /**
     * Returns the String value of this CategoryEntry
     * 
     * @return this CategoryEntry's String value
     */
    public String getLabel() {
    	return this.label;
    }
}