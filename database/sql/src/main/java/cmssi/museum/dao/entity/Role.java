/*
 * MIT License
 *
 * Copyright (c) 2019 - 2020  Christophe Munilla
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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
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
@SqlResultSetMappings({
	@SqlResultSetMapping(
        name = "integerRoleIdentifier", 
        columns = { @ColumnResult(name = "RoleIdentifier") }
    )
})
@NamedNativeQueries({
    @NamedNativeQuery(
		name = "FindRoleIdentifier",
		query = "CALL GetIdRole(:idMuseumFk,:idDomainFk,:idUserFk)",
		resultSetMapping = "integerRoleIdentifier"
	)
})
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