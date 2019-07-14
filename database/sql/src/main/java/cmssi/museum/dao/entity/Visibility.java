package cmssi.museum.dao.entity;
 
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
 
/**
 * In the data model a Visibility define an access policy: 
 * 
 * 		-HIDDEN : no access
 * 		-DISABLED : read access
 * 		-ENABLED : read/write access
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.2
 */
@Entity
@Table(name = "Visibility")
public class Visibility implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2957525947424833873L;

	/**
     * the Visibility's Integer identifier
     */
    @Id
    @GeneratedValue
    @Column
    private Integer idVisibility; 
    
    /**
     * the Visibility's String name
     */
    @Column
    private String label;
     
    /**
     * Constructor
     */
    public Visibility() {}
     
    /**
     * Constructor
     * 
     * @param idVisibility the Integer identifier of the Visibility 
     * to be instantiated
     * @param label the String name of the Visibility to be
     * instantiated
     */
    public Visibility(Integer idVisibility, String label)
    {
        this.idVisibility = idVisibility;
        this.label = label;
    }

    /**
	 * Returns the Integer identifier of this Visibility
	 * 
	 * @return this Visibility's the Integer identifier 
	 */
	public Integer getIdVisibility() 
	{
		return idVisibility;
	}

	/**
	 * Defines the Integer identifier of this Visibility
	 
	 * @param idVisibility this Visibility's the Integer 
	 * identifier 
	 */
	public void setIdVisibility(Integer idVisibility) 
	{
		this.idVisibility = idVisibility;
	}
    
    /**
     * Defines the String name of this Visibility
     * 
     * @param label the String name to be set
     */
    public void setLabel(String label)
    {
    	this.label = label;
    }
    
    /**
     * Returns the String name of this Visibility
     * 
     * @return this Visibility's String name
     */
    public String getLabel()
    {
    	return this.label;
    }
}