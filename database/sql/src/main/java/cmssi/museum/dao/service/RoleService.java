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
package cmssi.museum.dao.service;

import javax.ejb.Stateless;

import cmssi.museum.controler.api.DomainRole;
import cmssi.museum.dao.entity.Museum;
import cmssi.museum.dao.entity.Role;

/**
 * An extended {@link ReadService} dedicated to {@link Role} entities
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
@Stateless
public class RoleService extends ReadService<Role> {
	
	/**
	 * Constructor
	 */
	public RoleService(){
		super(Role.class);
	}

	/**
	 * Returns the integer identifier of the {@link Role} of the {@link User} whose identifier
	 * is passed as parameter for the specified {@link Domain} and {@link Museum}
	 * 
	 * @param idMuseum the Integer identifier of the {@link Museum}
	 * @param idDomain the Integer identifier of the {@link Domain}
	 * @param idUser the Integer identifier of the {@link User}
	 * 
	 * @return the integer identifier of the {@link Role} for the specified  {@link User},
	 * {@link Domain} and {@link Museum}
	 */
	public int getRoleId(Integer idMuseum, Integer idDomain, Integer idUser)	{
		var query = getSession().getNamedQuery("FindRoleIdentifier");
		query.setParameter("idMuseumFk", idMuseum);
		query.setParameter("idDomainFk", idDomain);
		query.setParameter("idUserFk", idUser);
		return (Integer) query.uniqueResult();
	}
	
	/**
	 * Returns the {@link DomainRole} mapped to the {@link Role} whose integer identifier is
	 * passed as parameter
	 * 
	 * @param idRole the Integer identifier of the {@link Role}
	 * 
	 * @return {@link DomainRole} for the specified {@link Role}
	 */
	public DomainRole getDomainRole(int idRole) {
		Role role = super.get(idRole);
		String label = role.getLabel();
		return DomainRole.valueOf(label);		
	}
}
