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

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.persistence.Id;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * the CRUDService allows to create, read, update, and delete persistent 
 * instances of a specific entity type (i.e. @Entity annotated)
 * 
 * <T> the handled entity Type 
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
@SuppressWarnings("unchecked")
abstract class CRUDService<T> extends ReadService<T>
{	

	private String identityFieldSetterName;
	
	/**
	 * Constructor
	 * 
	 * @param entityType the handled &lt;T&gt; entity Type
	 */
	public CRUDService(Class<T> entityType){
		super(entityType);
		Field[] fields = entityType.getDeclaredFields();
		for(Field field : fields) {
			//field.setAccessible(true);
			Id idAnnotation = field.getAnnotation(Id.class);
			if(idAnnotation != null) {
				this.identityFieldSetterName = new StringBuilder().append("set").append(
					field.getName().substring(0,1).toUpperCase()).append(
							field.getName().substring(1)).toString();
				break;
			}
		}
	}

	/**
	 * Returns the String name of the method allowing to define the
	 * identifier of the handled &lt;T&gt; entity Type 
	 * 
	 * @return the &lt;T&gt; entity Type identity setter method's name
	 */
	protected String getIdentityFieldSetterName() {
		return this.identityFieldSetterName;
	}
	
	/**
	 * Adds the entity instance passed as parameter in 
	 * the associated persistence store
	 * 
	 * @param added the entity to be added
	 * 
	 * @return the Serializable identifier of the  newly created 
	 * entity in the associated persistence store
	 */
	public <K extends Serializable> K add(T added){
		if(added == null) {
			return null;
		}
		Session session = getSession();
		Transaction t = session.beginTransaction();
		K k = null;
		t.begin();
		try {			
			k = (K) session.save(added);
			t.commit();			
			return k;
		}catch(HibernateException e) {
			t.rollback();
			e.printStackTrace();
		} finally {
			if(t.wasCommitted()) {
				try {
					Method m = super.getEntityType().getMethod(
					this.getIdentityFieldSetterName(), new Class[] {k.getClass()});
					m.invoke(added, k);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	/**
	 * Updates the entity instance passed as parameter in 
	 * the associated persistence store
	 * 
	 * @param updated the entity to be updated
	 * 
	 * @return true if the entity has been updated in the
	 * associated persistence store - false otherwise
	 */
	public boolean update(T updated){
		if(updated == null) {
			return false;
		}
		Session session = getSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(updated);
			t.commit();
			return true;
		}catch(HibernateException e) {
			t.rollback();
		}
		return false;
	}

	/**
	 * Removes the entity instance passed as parameter from
	 * the associated persistence store
	 * 
	 * @param removed the entity to be removed
	 * 
	 * @return true if the entity has been removed from the
	 * associated persistence store - false otherwise
	 */
	public boolean remove(T removed){
		if(removed == null) {
			return false;
		}
		Session session = getSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(removed);
			t.commit();
			return true;
		}catch(HibernateException e) {
			t.rollback();
		}
		return false;
	}
}
