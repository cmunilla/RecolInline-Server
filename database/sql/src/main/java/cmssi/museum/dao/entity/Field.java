package cmssi.museum.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * In the data model a Field provides an information
 * about an artefact
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.2
 */
@Entity
@Table(name = "Field")
public class Field implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2496563128427651953L;

	/**
     * the Field's Integer identifier
     */
    @Id
    @GeneratedValue
    @Column
    private Integer idField; 

	/**
     * the Integer identifier of the Type
     * of this Field
     */
    @Column
    private Integer idType; 

	/**
     * the Integer identifier of the Frame
     * describing this Field
     */
    @Column
    private Integer idFrame; 

    /**
     * the Field's String name
     */
    @Column
    private String label;
    
    /**
     * Constructor
     */
    public Field() {}
    
    /**
     * Constructor
     * 
     * @param idField the Integer identifier of the Field
     * to be instantiated
     * @param idType the Integer identifier of the Type of
     * the Field to be instantiated
     * @param idFrame the Integer identifier of the Frame 
     * describing the Field to be instantiated
     * @param label the String name of the Field to be instantiated
     */
    public Field(Integer idField, Integer idType, Integer idFrame, String label)
    {
    	this.idField = idField;
    	this.idType = idType;
    	this.idFrame = idFrame;
    	this.label = label;
    }

	/**
	 * Return this Field's Integer identifier
	 * 
	 * @return the Integer identifier of this Field
	 */
	public Integer getIdField() 
	{
		return idField;
	}

	/**
	 * Defines this Field's Integer identifier
	 * 
	 * @param idField the Integer identifier to be
	 * set
	 */
	public void setIdField(Integer idField)
	{
		this.idField = idField;
	}

	/**
	 * Returns the Integer identifier of the Type
	 * of this Field
	 * 
	 * @return the Integer identifier of this Field's
	 * Type
	 */
	public Integer getIdType() 
	{
		return idType;
	}

	/**
	 * Defines the Integer identifier of the Type
	 * of this Field
	 * 
	 * @param idType the Integer identifier of the
	 * Type to be set
	 */
	public void setIdType(Integer idType) 
	{
		this.idType = idType;
	}

	/**
	 * Returns the Integer identifier of the Frame
	 * describing this Field
	 * 
	 * @return the Integer identifier of the Frame
	 * describing this field
	 */
	public Integer getIdFrame() 
	{
		return idFrame;
	}

	/**
	 * Defines the Integer identifier of the Frame
	 * describing this Field
	 * 
	 * @param idFrame the Integer identifier of the 
	 * Frame to be set
	 */
	public void setIdFrame(Integer idFrame) 
	{
		this.idFrame = idFrame;
	}

	/**
	 * Returns the String label of this Field
	 * 
	 * @return this Field's String label
	 */
	public String getLabel()
	{
		return label;
	}

	/**
	 * Defines the String label of this Field
	 * 
	 * @param label the String label to be set
	 */
	public void setLabel(String label) 
	{
		this.label = label;
	}
}
