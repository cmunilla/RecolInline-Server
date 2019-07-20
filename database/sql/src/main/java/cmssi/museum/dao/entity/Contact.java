package cmssi.museum.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

/**
 *  
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
@NamedNativeQueries({
	@NamedNativeQuery(
	name = "ContactsFromUser",
	query = "CALL GetUserContacts(:idUserFk)",
	resultClass = Contact.class
	),
	@NamedNativeQuery(
	name = "ContactsFromMuseum",
	query = "CALL GetMuseumContacts(:idMuseumFk)",
	resultClass = Contact.class
	)
})
@Entity
@Table(name = "Contact")
public class Contact implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2760793687551220600L;
	/**
     * the Contact's Integer identifier
     */
    @Id
    @GeneratedValue
    @Column
    private Integer idContact; 
    /**
     * the Contact's String label (office, personal, ...etc)
     */
    @Column
    private String label; 
    /**
     * the Contact's String "postal" address
     */
    @Column
    private String address; 
    /**
     * the Contact's String email address
     */
    @Column
    private String email;  
    /**
     * the Contact's String phone number
     */
    @Column
    private String phone; 


    /**
     * Constructor
     */
    public Contact() {}
    
    /**
     * Constructor
     * 
     * @param idContact the Integer identifier of the Contact to be 
     * instantiated
     * @param label the String label of the Contact to be instantiated
     * @param address the String address of the Contact to be instantiated
     * @param email the String email of the Contact to be instantiated
     * @param phone the String formated phone number of the Contact to be 
     * instantiated
     */
    public Contact(Integer idContact, String label, String address, String email, String phone) {
    	this.idContact = idContact;
    	this.label = label;
    	this.address = address;
    	this.email = email;
    	this.phone = phone;
    }

	/**
	 * Returns the Integer identifier of this Contact
	 * 
	 * @return the Integer identifier of this Contact
	 */
	public Integer getIdContact() {
		return this.idContact;
	}

	/**
	 * Defines the Integer identifier of this Contact
	 * 
	 * @param idContact the Integer identifier of this Contact 
	 * to be set
	 */
	public void setIdContact(Integer idContact) {
		this.idContact = idContact;
	}

	/**
	 * Returns the String label of this Contact
	 * 
	 * @return the String label of this Contact
	 */
	public String getLabel() {
		return this.label;
	}

	/**
	 * Defines the String label of this Contact
	 * 
	 * @param label the String label of this Contact 
	 * to be set
	 */
	public void setLabel(String label) {
		this.label = label;
	}


	/**
	 * Returns the String address of this Contact
	 * 
	 * @return the String address of this Contact
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * Defines the String address of this Contact
	 * 
	 * @param label the String address of this Contact 
	 * to be set
	 */
	public void setAddress(String address) {
		this.address = address;
	}


	/**
	 * Returns the String email address of this Contact
	 * 
	 * @return the String email address of this Contact
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Defines the String email address of this Contact
	 * 
	 * @param label the String email address of this Contact 
	 * to be set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Returns the String formated phone number of this Contact
	 * 
	 * @return the String formated phone number of this Contact
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * Defines the String formated phone number of this Contact
	 * 
	 * @param label the String formated phone number of this Contact 
	 * to be set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
