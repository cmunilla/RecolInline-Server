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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

/**
 * In the data model a Model gathers a set of Fields providing
 * a specific type of informations about an artefact (status, 
 * preservation interventions, location, loan, etc).
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
@NamedNativeQueries({
	@NamedNativeQuery(
	name = "ModelsFromDomain",
	query = "CALL GetModels(:idDomainFk)",
	resultClass = Model.class
	)
})
@Entity
@Table(name = "Model")
public class Model implements Serializable
{
	/**
	 * Generated long ID
	 */
	private static final long serialVersionUID = -7861106091595008569L;

	/**
     * the Model's Integer identifier
     */
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idModel; 

	/**
     * the Integer identifier of the Domain
     * this Model belongs to
     */
    @Column
    private Integer idDomain; 

    /**
     * the Model's String name
     */
    @Column
    private String label;
    

    /**
     * Constructor
     */
    public Model() {}
    
    /**
     * Constructor
     * 
     * @param idModel the Integer identifier of the Model
     * to be instantiated
     * @param idDomain the Integer identifier of the Domain 
     * to which belongs the Model to be instantiated
     * @param label the String name of the Model to be 
     * instantiated
     */
    public Model(Integer idModel, Integer idDomain, String label) {
    	this.idModel = idModel;
    	this.idDomain = idDomain;
    	this.label = label;
    }
    
	/**
	 * Return this Model's Integer identifier
	 * 
	 * @return the Integer identifier of this Model
	 */
	public Integer getIdModel() {
		return idModel;
	}

	/**
	 * Defines this Model's Integer identifier
	 * 
	 * @param idModel the Integer identifier to be
	 * set
	 */
	public void setIdModel(Integer idModel){
		this.idModel = idModel;
	}
	
	/**
	 * Returns the Integer identifier of the Domain
	 * to which belongs this Model
	 * 
	 * @return the Integer identifier of the Domain
	 * to which this Model belongs
	 */
	public Integer getIdDomain() {
		return idDomain;
	}

	/**
	 * Defines the Integer identifier of the Domain
	 * to which belongs this Model
	 * 
	 * @param idDomain the Integer identifier of the 
	 * Domain to be set
	 */
	public void setIdDomain(Integer idDomain) {
		this.idDomain = idDomain;
	}

	/**
	 * Returns the String name of this Model
	 * 
	 * @return this Model's String name
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Defines the String name of this Model
	 * 
	 * @param label the String name to be set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
}
