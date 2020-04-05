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
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

/**
 * In the data model a Line represents the content of a {@link Field} 
 * for a specific {@link Sheet}.
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */

@NamedNativeQuery(
	name = "SheetLines",
	query = "CALL GetSheetsLines(:idSheetFk)",
	resultClass = Line.class
)
@Entity
@Table(name = "Line")
public class Line implements Serializable
{
	/**
	 * Generated long ID
	 */
	private static final long serialVersionUID = 2768107901772252081L;

	/**
     * the Integer identifier of the Field
     * this Line belongs to
     */
    @Id
    @Column
    private Integer idField; 

	/**
     * the Integer identifier of the Sheet this Line
     * belongs to
     */
    @Id
    @Column
    private Integer idSheet; 

    /**
     * the Line String value
     */
    @Column
    private String line;
    
    /**
     * 
     */
    public Line() {}
    
    /**
     * Constructor
     * 
     * @param idField the Integer identifier of the Field the
     * Line to be instantiated belongs to
     * @param idSheet the Integer identifier of the Sheet the
     * Line to be instantiated belongs to
     * @param line the String value of the Line to be 
     * instantiated
     */
    public Line(Integer idField, Integer idSheet, String line) {
    	this.idField = idField;
    	this.idSheet = idSheet;
    	this.line = line;
    }

	/**
	 * @return the idField
	 */
	public Integer getIdField() {
		return idField;
	}

	/**
	 * @param idField the idField to set
	 */
	public void setIdField(Integer idField) {
		this.idField = idField;
	}

	/**
	 * @return the idSheet
	 */
	public Integer getIdSheet() {
		return idSheet;
	}

	/**
	 * Defines the Integer identifier of the Sheet this
     * Line belongs to
     * 
	 * @param idSheet the Integer identifier of this Line
	 * idSheet to set
	 */
	public void setIdSheet(Integer idSheet) {
		this.idSheet = idSheet;
	}

	/**
	 * Returns this Line's String value
	 * 
	 * @return the String value of this Line
	 */
	public String getLine() {
		return line;
	}

	/**
	 * Defines this Line's String value
	 * 
	 * @param line the String value to be set
	 */
	public void setLine(String line)  {
		this.line = line;
	}
}
