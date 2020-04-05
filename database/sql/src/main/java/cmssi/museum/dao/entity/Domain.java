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
import javax.persistence.Table;
 
/**
 * In the data model a Domain gathers a set of Models, as well as Types and 
 * Users. A Domain always inherits from another one (exception made of the 
 * 'ARTIFACT' one), meaning that Types and Users are also inherited. By default 
 * a new Domain inherits from the 'ARTIFACT' one, for which the set of raw Types 
 * have been defined, as well as an anonymous user.
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
@Entity
@Table(name = "Domain")
public class Domain implements Serializable
{
	/**
	 * Generated longID
	 */
	private static final long serialVersionUID = 5895287014740306651L;
	
	/**
     * the Domain's Integer identifier
     */
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idDomain; 
    
    /**
     * the Integer identifier of the 
     * Domain this one inherits from
     */
    @Column
    private Integer refDomain;
    
    /**
     * the Domain's String name
     */
    @Column
    private String label;
     
    /**
     * Constructor
     */
    public Domain() {}
     
    /**
     * Constructor
     * 
     * @param idDomain the Integer identifier of the Domain to be
     * instantiated
     * @param refDomain the Integer identifier of the Domain of 
     * whose will inherit the one to be instantiated
     * @param label the String name of the Domain to be
     * instantiated
     */
    public Domain(Integer idDomain, Integer refDomain, String label) {
        this.idDomain = idDomain;
        this.refDomain = refDomain;
        this.label = label;
    }

    /**
     * Defines the Integer identifier of this Domain
     * 
     * @param idDomain the Integer identifier to be set
     */
    public void setIdDomain(Integer idDomain) {
    	this.idDomain = idDomain;
    }
    
    /**
     * Returns the Integer identifier of this Domain
     * 
     * @return this Domain's Integer identifier
     */
    public Integer getIdDomain() {
    	return this.idDomain;
    }
    
    /**
     * Defines the Integer identifier of the Domain
     * this one inherits from 
     * 
     * @param refDomain the Integer identifier of 
     * the inherited Domain
     */
    public void setRefDomain(Integer refDomain){
    	this.refDomain = refDomain;
    }
    
    /**
     * Returns the Integer identifier of the Domain
     * this one inherits from 
     * 
     * @return the Integer identifier of the inherited
     * Domain
     */
    public Integer getRefDomain(){
    	if(this.refDomain == null)
    	{
    		return new Integer(0);
    	}
    	return this.refDomain;
    }
    
    /**
     * Defines the String name of this Domain
     * 
     * @param label the String name to be set
     */
    public void setLabel(String label){
    	this.label = label;
    }
    
    /**
     * Returns the String name of this Domain
     * 
     * @return this Domain's String name
     */
    public String getLabel() {
    	return this.label;
    }
}