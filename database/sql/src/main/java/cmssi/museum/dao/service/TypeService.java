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
package cmssi.museum.dao.service;

import java.util.List;

import javax.ejb.Stateless;
import org.hibernate.Query;
import cmssi.museum.dao.entity.Type;

/**
 * the TypeService is an extended CRUDService dedicated to {@link Type} entities
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
@Stateless
public class TypeService extends CRUDService<Type> {
	
	/**
	 * Constructor
	 */
	public TypeService(){
		super(Type.class);
	}
	
	/**
	 * Returns the List of {@link Type}s attached to the {@link Domain}
	 * whose Integer identifier is passed as parameter
	 * 
	 * @param idDomain the Integer identifier of the {@link Domain}
	 * 
	 * @return the List of {@link Type}s for the specified {@link Domain}
	 */
	public List<Type> getTypes(Integer idDomain)
	{
		Query query = getSession().getNamedQuery("TypesFromDomain");
		query.setParameter("idDomainFk", idDomain);
		return (List<Type>) query.list();
	}
	
}
