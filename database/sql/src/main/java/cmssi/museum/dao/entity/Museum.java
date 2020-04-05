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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
@Entity
@Table(name = "Museum")
public class Museum implements Serializable
{
	/**
	 * Generated long ID
	 */
	private static final long serialVersionUID = 7294999563957975906L;
	
	/**
     * the Museum's Integer identifier
     */
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idMuseum; 
    
    /**
     * the Museum's name label
     */
    @Column
    private String label; 
    
    /**
     * Constructor
     */
    public Museum(){}

    /**
     * Constructor
     * 
     * @param idMuseum the Integer identifier of the Museum
     * to be instantiated
     * @param label the String name of the Museum to be 
     * instantiated
     */
    public Museum(Integer idMuseum, String label){
    	this.idMuseum = idMuseum;
    	this.label = label;
    }
    
	/**
	 * Returns the Integer identifier of this Museum
	 * 
	 * @return this Museum's Integer identifier
	 */
	public Integer getIdMuseum() {
		return this.idMuseum;
	}
	
	/**
	 * Defines the Integer identifier of this Museum
	 * 
	 * @param idMuseum the Integer identifier to be set
	 */
	public void setIdMuseum(Integer idMuseum) {
		this.idMuseum = idMuseum;
	}

	/**
	 * Returns the String name of this Museum
	 * 
	 * @return this Museum's String name
	 */
	public String getLabel() {
		return this.label;
	}

	/**
	 * Defines the String name of this Museum
	 * 
	 * @param label the String name to be set
	 */
	public void setLabel( String label) {
		this.label = label;
	}
}
