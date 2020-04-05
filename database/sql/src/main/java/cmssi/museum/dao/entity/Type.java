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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
 
/**
 * In the data model a Type characterizes the content of 
 * a Field. A Type is associated to a Domain because it is 
 * possible to create specific Types (new Category instances)
 * for it
 * 
 * @author cmunilla@cmssi.fr 
 * @version 0.3
 */
@NamedNativeQueries({
	@NamedNativeQuery(
	name = "TypesFromDomain",
	query = "CALL GetTypes(:idDomainFk)",
	resultClass = Type.class
	)
})
@Entity
@Table(name = "Type")
public class Type implements Serializable
{
	/**
	 * Generated serial identifier
	 */
	private static final long serialVersionUID = -3777228058711740000L;

	/**
     * the Type's Integer identifier
     */
    @Id
    @GeneratedValue
    @Column
    private Integer idType; 
    
    /**
     * the Integer identifier of the Domain this Type belongs to
     */
    @Column
    private Integer idDomain; 
    
    /**
     * the Type's String name
     */
    @Column
    private String label;
     
    /**
     * Constructor
     */
    public Type() {}
     
    /**
     * Constructor
     * 
     * @param idType the Integer identifier of the Type to be
     * instantiated
     * @param idDomain the Integer identifier of the Domain the
     * Type to be instantiated belongs to
     * @param label the String name of the Type to be
     * instantiated
     */
    public Type(Integer idType, Integer idDomain,String label)
    {
        this.idType = idType;
        this.idDomain = idDomain;
        this.label = label;
    }

    /**
	 * Returns the Integer identifier of this Types
	 * 
	 * @return this Type's the Integer identifier 
	 */
	public Integer getIdType() 
	{
		return idType;
	}

	/**
	 * Defines the Integer identifier of this Types
	 * 
	 * @param idType this Type's the Integer identifier 
	 */
	public void setIdType(Integer idType) 
	{
		this.idType = idType;
	}

	/**
	 * Returns the Integer identifier of the Domain
	 * this Types belongs to
	 * 
	 * @return the Integer identifier of 
	 * the Domain
	 */
	public Integer getIdDomain() 
	{
		return idDomain;
	}

	/**
	 * Defines the Integer identifier of the Domain
	 * this Types belongs to
	 * 
	 * @param idDomain the Integer identifier of 
	 * the Domain
	 */
	public void setIdDomain(Integer idDomain) 
	{
		this.idDomain = idDomain;
	}
    
    /**
     * Defines the String name of this Type
     * 
     * @param label the String name to be set
     */
    public void setLabel(String label)
    {
    	this.label = label;
    }
    
    /**
     * Returns the String name of this Type
     * 
     * @return this Type's String name
     */
    public String getLabel()
    {
    	return this.label;
    }
}