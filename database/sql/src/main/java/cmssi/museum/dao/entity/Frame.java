package cmssi.museum.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * In the data model a Frame defines a view of a 
 * Domain or a Field
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.2
 */
@Entity
@Table(name = "Frame")
public class Frame implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7992732352887894862L;

	/**
     * the Frame's Integer identifier
     */
    @Id
    @GeneratedValue
    @Column
    private Integer idFrame; 

	/**
     * the Integer value defining the vertical position
     * in the parent container table
     */
    @Column
    private Integer row; 

	/**
     * the Integer value defining the horizontal position
     * in the parent container table
     */
    @Column
    private Integer column; 

    /**
     * Constructor
     */
    public Frame() {}
    
    /**
     * Constructor
     * 
     * @param idFrame the Integer identifier of the Frame
     * to be instantiated
     * @param row the Integer defining the vertical 
     * position of the Frame to be instantiated
     * @param column the Integer defining the horizontal 
     * position of the Frame to be instantiated
     */
    public Frame(Integer idFrame, Integer row, Integer column)
    {
    	this.idFrame = idFrame;
    	this.row = row;
    	this.column = column;
    }

	/**
	 * Return this Frame's Integer identifier
	 * 
	 * @return the Integer identifier of this Frame
	 */
	public Integer getIdFrame() 
	{
		return idFrame;
	}

	/**
	 * Defines this Frame's Integer identifier
	 * 
	 * @param idFrame the Integer identifier to be
	 * set
	 */
	public void setIdFrame(Integer idFrame)
	{
		this.idFrame = idFrame;
	}

	/**
	 * Returns this Frame's vertical position 
	 * in the parent container table
	 *  
	 * @return the vertical position in the 
	 * parent container table
	 */
	public Integer getRow() {
		return row;
	}
	
	/**
	 * Defines this Frame's vertical position 
	 * in the parent container table
	 *  
	 * @param row the vertical position in the 
	 * parent container table
	 */
	public void setRow(Integer row) {
		this.row = row;
	}

	/**
	 * Returns this Frame's horizontal position 
	 * in the parent container table
	 *  
	 * @return the horizontal position in the 
	 * parent container table
	 */
	public Integer getColumn() {
		return column;
	}

	/**
	 * Defines this Frame's horizontal position 
	 * in the parent container table
	 *  
	 * @param column the horizontal position in the 
	 * parent container table
	 */
	public void setColumn(Integer column) {
		this.column = column;
	}
}
