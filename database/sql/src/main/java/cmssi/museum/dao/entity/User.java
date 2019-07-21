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
import javax.persistence.Table;

/**
 * 
 * @author cmunilla@cmssi.fr 
 * @version 0.3
 */
@SuppressWarnings("unused")
@SqlResultSetMapping(
    name="museumUser",
    entities={
    	@EntityResult(entityClass = MuseumUser.class, fields={
	        @FieldResult(name="idMuseum", column="idMuseum"),
	        @FieldResult(name="idUser", column="idUser"),
	        @FieldResult(name="idRole", column="idRole"),
	        @FieldResult(name="login", column="login")
	        }
    	)
    }
)
@NamedNativeQueries({
	@NamedNativeQuery(
		name = "UsersFromMuseum",
		query = "CALL GetUsers(:idMuseumFk)",
		resultSetMapping="museumUser"
	)
})
@Entity
@Table(name = "User")
public class User implements Serializable {
	
	/**
	 * Generated long ID
	 */
	private static final long serialVersionUID = 4167726911160367986L;

	/**
     * the User's Integer identifier
     */
    @Id
    @GeneratedValue
    @Column
    private Integer idUser; 

	/**
     * the User's  String login
     */
    @Column
    private String login; 

	/**
     * the User's String password
     */
    @Column
    private String password; 
    
    /**
     * Constructor
     */
    public User() {}
    
    /**
     * Constructor
     * 
     * @param idUser the Integer identifier of the User
     * to be instantiated
     * @param login the String login of the user allowing to be identified 
     * by the system
     * @param password the Strig password of the user allowing to be identified 
     * by the system
     */
    public User(Integer idUser, String login, String password) {
    	this.idUser = idUser;
    	this.login = login;
    	this.password = password;
    }

	/**
	 * Returns the Integer identifier of this User
	 * 
	 * @return the Integer identifier of this User
	 */
	public Integer getIdUser() {
		return this.idUser;
	}

	/**
	 * Defines the Integer identifier of this User
	 * l
	 * 
	 * @param idUser the Integer identifier of this
	 * User to be set
	 */
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	/**
	 * Returns the String login this User
	 * 
	 * @return the String login of this User
	 */
	public String getLogin() {
		return this.login;
	}

	/**
	 * Defines the String login of this User
	 * 
	 * @param login the String login of this User to be set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Returns the encoded String password this User
	 * 
	 * @return the encoded String password of this User
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Defines the encoded String password of this User
	 * 
	 * @param password the String password of this User to be set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
