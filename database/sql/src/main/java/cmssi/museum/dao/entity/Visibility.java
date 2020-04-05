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
import javax.persistence.Id;
import javax.persistence.Table;
 
/**
 * In the data model a Visibility define an access policy:  * 
 * 		-HIDDEN : no access
 * 		-DISABLED : read access
 * 		-ENABLED : read/write access
 * 
 * @author cmunilla@cmssi.fr 
 * @version 0.3
 */
@Entity
@Table(name = "Visibility")
public class Visibility implements Serializable
{
	public static final String HIDDEN   = "HIDDEN";
	public static final String DISABLED = "DISABLED";
	public static final String ENABLED  = "ENABLED";
	
	/**
	 * Generated longID
	 */
	private static final long serialVersionUID = -2957525947424833873L;

	/**
     * the Visibility's Integer identifier
     */
    @Id
    @GeneratedValue
    @Column
    private Integer idVisibility; 
    
    /**
     * the Visibility's String name
     */
    @Column
    private String label;
     
    /**
     * Constructor
     */
    public Visibility() {}
     
    /**
     * Constructor
     * 
     * @param idVisibility the Integer identifier of the Visibility 
     * to be instantiated
     * @param label the String name of the Visibility to be
     * instantiated
     */
    public Visibility(Integer idVisibility, String label) {
        this.idVisibility = idVisibility;
        this.label = label;
    }

    /**
	 * Returns the Integer identifier of this Visibility
	 * 
	 * @return this Visibility's the Integer identifier 
	 */
	public Integer getIdVisibility() {
		return idVisibility;
	}

	/**
	 * Defines the Integer identifier of this Visibility
	 
	 * @param idVisibility this Visibility's the Integer 
	 * identifier 
	 */
	public void setIdVisibility(Integer idVisibility) {
		this.idVisibility = idVisibility;
	}
    
    /**
     * Defines the String name of this Visibility
     * 
     * @param label the String name to be set
     */
    public void setLabel(String label) {
    	this.label = label;
    }
    
    /**
     * Returns the String name of this Visibility
     * 
     * @return this Visibility's String name
     */
    public String getLabel() {
    	return this.label;
    }
}