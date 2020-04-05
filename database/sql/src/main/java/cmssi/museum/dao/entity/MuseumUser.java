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

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
@Entity
public class MuseumUser implements Serializable
{
	/**
	 * Generated Serial ID
	 */
	private static final long serialVersionUID = 6811719600206096480L;

	/**
     * the User's Integer identifier
     */
	@Id
    private Integer idUser; 

	/**
     * the Museum's Integer identifier
     */
	@Id
    private Integer idMuseum; 

	/**
     * the Role's Integer identifier
     */
    private Integer idRole; 

	/**
     * the User's String login
     */
    private String login; 

    /**
     * Constructor
     */
    public MuseumUser()
    {}

	/**
	 * Defines the Integer identifier of the Museum for which this 
	 * MuseumUser is registered
	 * 
	 * @param idMuseum the Museum's Integer identifier for this MuseumUser
	 */
	public void setIdMuseum(Integer idMuseum) 
	{
		this.idMuseum = idMuseum;
	}
	
	/**
	 * Returns the Integer identifier of the Museum for which this 
	 * MuseumUser is registered
	 * 
	 * @return the Museum's Integer identifier for this MuseumUser
	 */
	public Integer getIdMuseum() 
	{
		return this.idMuseum;
	}

	/**
	 * Defines the Integer identifier of the User for which this 
	 * MuseumUser is registered
	 * 
	 * @param idUser the User's Integer identifier for this MuseumUser
	 */
	public void setIdUser(Integer idUser) 
	{
		this.idUser = idUser;
	}

	/**
	 * Returns the Integer identifier of the User for which this 
	 * MuseumUser is registered
	 * 
	 * @return the User's Integer identifier for this MuseumUser
	 */
	public Integer getIdUser() 
	{
		return this.idUser;
	}

	/**
	 * Defines the Integer identifier of the Role of the User for which this 
	 * MuseumUser is registered
	 * 
	 * @param idRole the Integer identifier of the Role to be set
	 */
	public void setIdRole(Integer idRole) 
	{
	     this.idRole = idRole;
	}
	
	/**
	 * Returns the Integer identifier of the Role of the User for which this 
	 * MuseumUser is registered
	 * 
	 * @return the Role's Integer identifier for this MuseumUser
	 */
	public Integer getIdRole() 
	{
		return this.idRole;
	}

	/**
	 * Defines the String login of the User for which this 
	 * MuseumUser is registered
	 * 
	 * @param login the String login of the User
	 */
	public void setLogin(String login) 
	{
		this.login = login;
	}
	
	/**
	 * Returns the String login of the User for which this 
	 * MuseumUser is registered
	 * 
	 * @return the String login for this MuseumUser
	 */
	public String getLogin() 
	{
		return this.login;
	}
}
