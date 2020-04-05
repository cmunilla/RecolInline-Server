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
