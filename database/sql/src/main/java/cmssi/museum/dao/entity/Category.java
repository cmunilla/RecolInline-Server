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
 * In the data model a Category allows to define a Type
 * to which is associated a set of possible values
 *   
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
@Entity
@Table(name = "Category")
public class Category implements Serializable
{
	/**
	 * Generated serial ID
	 */
	private static final long serialVersionUID = 7849383416876776112L;

	/**
     * the Category's Integer identifier
     */
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idCategory; 

    /**
     * the Category's cardinality
     */
    @Column
    private Integer cardinality;
    
    /**
     * the Category's String name
     */
    @Column
    private String label;
     
    /**
     * Constructor
     */
    public Category() {}
     
    /**
     * Constructor
     * 
     * @param idCategory the Integer identifier of the Category 
     * to be instantiated
     * @param label the String name of the Category to be
     * instantiated
     */
    public Category(Integer idCategory, Integer cardinality, String label)
    {
        this.idCategory = idCategory;
        this.cardinality = cardinality;
        this.label = label;
    }

    /**
	 * Returns the Integer identifier of this Category
	 * 
	 * @return this Category's the Integer identifier 
	 */
	public Integer getIdCategory() 
	{
		return idCategory;
	}

	/**
	 * Defines the Integer identifier of this Category
	 
	 * @param idCategory this Category's the Integer identifier 
	 */
	public void setIdCategory(Integer idCategory) 
	{
		this.idCategory = idCategory;
	}

	/**
	 * Returns the Integer cardinality applying on this
	 * Category
	 * 
	 * @return this Category's cardinality
	 */
	public Integer getCardinality()
	{
		return this.cardinality;
	}

	/**
	 * Defines the Integer cardinality applying on this
	 * Category
	 * 
	 * @param cardinality the cardinality applying on this
	 * Category
	 */
	public void setCardinality(Integer cardinality) 
	{
		this.cardinality = cardinality;
	}
	
    /**
     * Defines the String name of this Category
     * 
     * @param label the String name to be set
     */
    public void setLabel(String label)
    {
    	this.label = label;
    }
    
    /**
     * Returns the String name of this Category
     * 
     * @return this Category's String name
     */
    public String getLabel()
    {
    	return this.label;
    }
}