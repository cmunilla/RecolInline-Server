/* 
 * Copyright 2019-2020 Christophe Vincent Munilla, FR Micro Entreprise - All Rights Reserved
 * 
 * RecolInline Tools Suite
 * @contact cmunilla@cmssi.fr
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
