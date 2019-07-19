package cmssi.museum.dao.entity;
 
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
 
/**
 * In the data model a Role allows to define access policy 
 * of the Fields of a Domain for a specific User:
 * 
 *   - ADMINISTRATOR : allowed to create Models
 *   - VALIDATOR: allowed to validate Sheets
 *   - WRITER: allowed to write a Field
 *   - READER: allowed to read a Field
 *   - NONE: no access rights
 *   
 * @author cmunilla@cmssi.fr 
 * @version 0.3
 */
@Entity
@Table(name = "Role")
public class Role implements Serializable
{
	/**
	 * Generated long ID
	 */
	private static final long serialVersionUID = 6775433342450905880L;

	/**
     * the Role's Integer identifier
     */
    @Id
    @GeneratedValue
    @Column
    private Integer idRole; 
    
    /**
     * the Role's String name
     */
    @Column
    private String label;
     
    /**
     * Constructor
     */
    public Role() {}
     
    /**
     * Constructor
     * 
     * @param idRole the Integer identifier of the Role 
     * to be instantiated
     * @param label the String name of the Role to be
     * instantiated
     */
    public Role(Integer idRole, String label){
        this.idRole = idRole;
        this.label = label;
    }

    /**
	 * Returns the Integer identifier of this Role
	 * 
	 * @return this Role's the Integer identifier 
	 */
	public Integer getIdRole() {
		return idRole;
	}

	/**
	 * Defines the Integer identifier of this Role
	 
	 * @param idRole this Role's the Integer identifier 
	 */
	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}
    
    /**
     * Defines the String label of this Role
     * 
     * @param label the String label to be set
     */
    public void setLabel(String label) {
    	this.label = label;
    }
    
    /**
     * Returns the String label of this Role
     * 
     * @return this Role's String label
     */
    public String getLabel() {
    	return this.label;
    }
}