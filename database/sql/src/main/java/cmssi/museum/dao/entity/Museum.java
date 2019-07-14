package cmssi.museum.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.2
 */
@Entity
@Table(name = "Museum")
public class Museum implements Serializable
{

	/**
     * the Museum's Integer identifier
     */
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idMuseum; 
    
    /**
     * Constructor
     */
    public Museum()
    {}

    /**
     * Constructor
     * 
     * @param idMuseum the Integer identifier of the Museum
     * to be instantiated
     */
    public Museum(Integer idMuseum){
    	this.idMuseum = idMuseum;
    }
    
	/**
	 * Returns the Integer identifier of this Museum
	 * 
	 * @return this Museum's Integer identifier
	 */
	public Integer getIdMuseum() 
	{
		return this.idMuseum;
	}
}
