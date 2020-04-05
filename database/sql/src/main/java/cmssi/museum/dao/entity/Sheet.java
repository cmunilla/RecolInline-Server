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
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
 
/**
 * A Sheet is an instance of a Domain. It is so dedicated to 
 * the description of an artefact
 * 
 * @author cmunilla@cmssi.fr 
 * @version 0.3
 */

@SqlResultSetMappings({
	@SqlResultSetMapping(
        name = "integerCounterMapping", 
        columns = { @ColumnResult(name = "NBR") }
    ),
	@SqlResultSetMapping(
	    name="fieldSkeleton",
	    entities={
	    	@EntityResult(
	    	    entityClass = FieldSkeleton.class, 
	    	    fields={
			        @FieldResult(name="idModel", column="idModel"),
			        @FieldResult(name="idField", column="idField"),
			        @FieldResult(name="idType", column="idType"),
			        @FieldResult(name="idVisibility", column="idVisibility"),
			        @FieldResult(name="labelModel", column="labelModel"),
			        @FieldResult(name="labelField", column="labelField"),
			        @FieldResult(name="labelType", column="labelType"),
			        @FieldResult(name="labelVisibility", column="labelVisibility"),
			        @FieldResult(name="constraints", column="constraints"),
			        @FieldResult(name="isText", column="isText")
		        }
	    	)
	    }
	)
})
@NamedNativeQueries({
	@NamedNativeQuery(
		name = "SheetFields",
		query = "CALL GetSheetFields(:idMuseumFk,:idDomainFk,:idUserFk)",
		resultSetMapping="fieldSkeleton"
	),
	@NamedNativeQuery(
		name = "SheetsFromMuseum",
		query = "CALL GetSheetsFromMuseum(:idMuseumFk,:fstIndex,:lstIndex)",
		resultClass = Sheet.class
	),
	@NamedNativeQuery(
		name = "SheetsFromDomainFromMuseum",
		query = "CALL GetSheetsFromDomainFromMuseum(:idMuseumFk,:idDomainFk,:fstIndex,:lstIndex)",
		resultClass = Sheet.class
	),
	@NamedNativeQuery(
		name = "SheetsCountFromMuseum",
		query = "CALL GetSheetsCountFromMuseum(:idMuseumFk)",	
		resultSetMapping = "integerCounterMapping"
	),
	@NamedNativeQuery(
		name = "SheetsCountFromDomainFromMuseum",
		query = "CALL GetSheetsCountFromDomainFromMuseum(:idMuseumFk,:idDomainFk)",	
		resultSetMapping = "integerCounterMapping"
	)
})
@Entity
@Table(name = "Sheet")
public class Sheet implements Serializable {
	
	/**
	 * Generated long ID
	 */
	private static final long serialVersionUID = -9120946162721048799L;

	/**
     * the Sheet's Integer identifier
     */
    @Id
    @GeneratedValue
    @Column
    private Integer idSheet; 
    
    /**
     * the Integer identifier of the Domain this sheet is an instance of
     */
    @Column
    private Integer idDomain; 
    
    /**
     * the Integer identifier of the Museum this sheet belongs to
     */
    @Column
    private Integer idMuseum; 
    
    /**
     * the Model's String name
     */
    @Column
    private String label;
     
    /**
     * Constructor
     */
    public Sheet() {}
     
    /**
     * Constructor
     * 
     * @param idSheet the Integer identifier of the Sheet to be instantiated
     * @param idDomain the Integer identifier of the Domain the Sheet
     * to be instantiated is an instance of
     * @param idMuseum the Integer identifier of the Museum the artifact 
     * described by the Sheet to be instantiated belongs to
     * @param label the String label of the Sheet to be instantiated
     */
    public Sheet(Integer idSheet, Integer idDomain,Integer idMuseum, String label)
    {
        this.idSheet = idSheet;
        this.idDomain = idDomain;
        this.idMuseum = idMuseum;
        this.label = label;
    }
    
	/**
	 * @return the idSheet
	 */
	public Integer getIdSheet() {
		return idSheet;
	}

	/**
	 * @param idSheet the idSheet to set
	 */
	public void setIdSheet(Integer idSheet) {
		this.idSheet = idSheet;
	}
	
	/**
	 * @return the idDomain
	 */
	public Integer getIdDomain(){
		return idDomain;
	}

	/**
	 * @param idDomain the idDomain to set
	 */
	public void setIdDomain(Integer idDomain) {
		this.idDomain = idDomain;
	}

	/**
	 * @return the idDomain
	 */
	public Integer getIdMuseum(){
		return idMuseum;
	}

	/**
	 * @param idMuseum the idMiuseum to set
	 */
	public void setIdMuseum(Integer idMuseum) {
		this.idMuseum = idMuseum;
	}
    
    /**
     * Defines the String name of this Sheet
     * 
     * @param label the String name to be set
     */
    public void setLabel(String label){
    	this.label = label;
    }
    
    /**
     * Returns the String name of this Sheet
     * 
     * @return this Sheet's String name
     */
    public String getLabel() {
    	return this.label;
    }
}