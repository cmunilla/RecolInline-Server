package cmssi.museum.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * In the data model a Line represents all or a part (depending 
 * whether the Field and Sheet keys couple is unique in the table 
 * or not) of the content of a Field for a specific Sheet.
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.2
 */
@Entity
@Table(name = "Line")
public class Line implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2768107901772252081L;

	/**
     * the Line's Integer identifier
     */
    @Id
    @GeneratedValue
    @Column
    private Integer idLine; 

	/**
     * the Integer identifier of the Field
     * this Line belongs to
     */
    @Column
    private Integer idField; 

	/**
     * the Integer identifier of the Sheet this Line
     * belongs to
     */
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
     * @param idLine the Integer identifier of the Line to
     * be instantiated
     * @param idField the Integer identifier of the Field the
     * Line to be instantiated belongs to
     * @param idSheet the Integer identifier of the Sheet the
     * Line to be instantiated belongs to
     * @param line the String value of the Line to be 
     * instantiated
     */
    public Line(Integer idLine, Integer idField, Integer idSheet, String line)
    {
    	this.idLine = idLine;
    	this.idField = idField;
    	this.idSheet = idSheet;
    	this.line = line;
    }

	/**
	 * @return the idLine
	 */
	public Integer getIdLine()
	{
		return idLine;
	}

	/**
	 * @param idLine the idLine to set
	 */
	public void setIdLine(Integer idLine) 
	{
		this.idLine = idLine;
	}

	/**
	 * @return the idField
	 */
	public Integer getIdField() 
	{
		return idField;
	}

	/**
	 * @param idField the idField to set
	 */
	public void setIdField(Integer idField)
	{
		this.idField = idField;
	}

	/**
	 * @return the idSheet
	 */
	public Integer getIdSheet() 
	{
		return idSheet;
	}

	/**
	 * Defines the Integer identifier of the Sheet this
     * Line belongs to
     * 
	 * @param idSheet the Integer identifier of this Line
	 * idSheet to set
	 */
	public void setIdSheet(Integer idSheet) 
	{
		this.idSheet = idSheet;
	}

	/**
	 * Returns this Line's String value
	 * 
	 * @return the String value of this Line
	 */
	public String getLine()
	{
		return line;
	}

	/**
	 * Defines this Line's String value
	 * 
	 * @param line the String value to be set
	 */
	public void setLine(String line) 
	{
		this.line = line;
	}
}
